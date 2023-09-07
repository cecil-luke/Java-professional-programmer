package com.etoak.mapper;

import com.etoak.entity.Ingredient;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etoak.vo.IngredientTypeVO;
import com.etoak.vo.IngredientVO;

import java.util.List;

/**
 * IngredientMapperMapper接口
 *
 * @author et2302
 * @since 2023-06-14
 */
public interface IngredientMapper extends BaseMapper<Ingredient> {

    /**
     * 获取菜品、食材
     *
     * @param ingredient
     */
    List<IngredientVO> getList(Ingredient ingredient);

    /**
     * 获取食材
     *
     * @param ingredient
     * @return
     */
    List<IngredientVO> getIngredientList(Ingredient ingredient);

    /**
     * 获取所有非菜品类型列表
     *
     * @return
     */
    List<IngredientTypeVO> getTypeList();

    /**
     * 查询菜品中是否包含食材的id
     *
     * @param id
     * @return
     */
    List<String> getNameList(int id);

    int updateIngredient(Ingredient ingredient);

}
