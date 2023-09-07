package com.etoak.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.etoak.common.constant.CommonConstant;
import com.etoak.common.exception.ParamException;
import com.etoak.common.group.DishesGroup;
import com.etoak.common.group.IngredientGroup;
import com.etoak.common.util.ValidatorUtil;
import com.etoak.common.vo.PageVO;
import com.etoak.entity.Ingredient;
import com.etoak.mapper.IngredientMapper;
import com.etoak.service.IngredientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.vo.IngredientTypeVO;
import com.etoak.vo.IngredientVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.Configuration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author et2302
 * @since 2023-06-14
 */
@Service
public class IngredientServiceImpl extends ServiceImpl<IngredientMapper, Ingredient> implements IngredientService {

    @Autowired
    IngredientMapper ingredientMapper;

    @Transactional
    @Override
    public boolean add(Ingredient ingredient) {
        // 校验
        this.validateIngredient(ingredient);

        // 验证菜品或食材名称
        String ingredientName = ingredient.getName();
        Ingredient savedIngredient = this.getIngredientByName(ingredientName);
        if (ObjectUtils.isNotEmpty(savedIngredient)) {
            throw new ParamException(ingredientName + "已存在，不能重复添加");
        }

        // 创建时间
        ingredient.setCreateTime(DateUtil.now());

        // 添加
        return this.save(ingredient);
    }

    @Override
    public PageVO<IngredientVO> getList(int pageNumber, int pageSize, Ingredient ingredient) {
        Integer ingredientType = ingredient.getIngredientType();
        List<IngredientVO> ingredientVOList;

        // 菜品
        if (ObjectUtils.isEmpty(ingredientType) || ingredientType.equals(CommonConstant.DISHES_TYPE)) {
            PageHelper.startPage(pageNumber, pageSize);
            ingredientVOList = ingredientMapper.getList(ingredient);
        } else {
            PageHelper.startPage(pageNumber, pageSize);
            ingredientVOList = ingredientMapper.getIngredientList(ingredient);
        }

        // 获取分页信息
        PageInfo<IngredientVO> pageInfo = new PageInfo<>(ingredientVOList);

        // 返回结果
        return new PageVO<>(pageInfo.getPageNum(),
                pageInfo.getPageSize(),
                ingredientVOList,
                pageInfo.getTotal());
    }

    @Override
    public Map<String, List<IngredientTypeVO>> getTypeList() {
        Map<String, List<IngredientTypeVO>> resultMap = new HashMap<>();
        List<IngredientTypeVO> typeList = ingredientMapper.getTypeList();
        if (CollectionUtils.isNotEmpty(typeList)) {
            typeList.stream()
                    .collect(Collectors.groupingBy(typeVO -> typeVO.getIngredientType(), Collectors.toList()))
                    .forEach((type, list) -> {
                        switch (type) {
                            case CommonConstant.MAIN_INGREDIENT_TYPE:
                                resultMap.put("mainIngredientList", list);
                                break;
                            case CommonConstant.MINOR_INGREDIENT_TYPE:
                                resultMap.put("minorIngredientList", list);
                                break;
                            case CommonConstant.SEASONING_TYPE:
                                resultMap.put("seasoningList", list);
                                break;
                        }
                    });
        }

        return resultMap;
    }

    @Transactional
    @Override
    public boolean deleteIngredient(int id) {
        Ingredient ingredient = getById(id);
        if (ObjectUtils.isEmpty(ingredient)) {
            throw new ParamException("没有要删除菜品或食材！");
        }

        /* 如果不是菜品, 则判断哪些菜品使用了这个id的食材 */
        if (!ingredient.getIngredientType().equals(CommonConstant.DISHES_TYPE)) {
            List<String> nameList = ingredientMapper.getNameList(id);
            if (CollectionUtils.isNotEmpty(nameList)) {
                throw new ParamException(StringUtils.join(nameList, "、") + "使用了这个食材！");
            }
        }

        // 删除状态
        ingredient.setDeleteState(2);

        // 更新时间
        ingredient.setUpdateTime(DateUtil.now());

        // 更新
        return updateById(ingredient);
    }

    @Transactional
    @Override
    public boolean updateIngredient(int id, Ingredient ingredient) {
        /* 验证有没有菜品 */
        Ingredient savedIngredient = getById(id);
        if (ObjectUtils.isEmpty(savedIngredient)) {
            throw new ParamException("没有要更新菜品或食材！");
        }

        // 校验参数
        this.validateIngredient(ingredient);

        // 校验名称(如果名称不一致, 直接更新; 如果名称一致并且id一致, 允许更新; )
        savedIngredient = this.getIngredientByName(ingredient.getName());

        if (ObjectUtils.isNotEmpty(savedIngredient)) {
            if (!savedIngredient.getIngredientType().equals(ingredient.getIngredientType())) {
                throw new ParamException("要修改的菜品或食材类型不匹配");
            }
            if (StringUtils.equals(savedIngredient.getName(), ingredient.getName())
                    && !savedIngredient.getId().equals(id)) {
                throw new ParamException("名称不能与其它菜品或食材一致");
            }
        }

        ingredient.setId(id);
        ingredient.setUpdateTime(DateUtil.now());

        // 更新菜品
        if (ingredient.getIngredientType().equals(CommonConstant.DISHES_TYPE)) {
            return updateById(ingredient);
        }

        // 更新食材
        return ingredientMapper.updateIngredient(ingredient) > 0;
    }

    private void validateIngredient(Ingredient ingredient) {
        Integer ingredientType = ingredient.getIngredientType();
        if (ObjectUtils.isNotEmpty(ingredientType)) {
            // 如果是菜品, 则都需要校验
            if (ingredientType.equals(CommonConstant.DISHES_TYPE)) {
                ValidatorUtil.validate(ingredient, DishesGroup.class);
            } else {
                ValidatorUtil.validate(ingredient, IngredientGroup.class);
            }
        } else {
            throw new ParamException("ingredientType必填");
        }
    }

    private Ingredient getIngredientByName(String name) {
        QueryWrapper<Ingredient> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name)
                .eq("delete_state", 1);
        return this.getOne(queryWrapper);
    }

}
