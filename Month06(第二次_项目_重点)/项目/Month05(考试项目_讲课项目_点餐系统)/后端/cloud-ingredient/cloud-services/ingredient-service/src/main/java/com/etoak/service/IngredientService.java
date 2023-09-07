package com.etoak.service;

import com.etoak.common.vo.PageVO;
import com.etoak.entity.Ingredient;
import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.vo.IngredientTypeVO;
import com.etoak.vo.IngredientVO;

import java.util.List;
import java.util.Map;

/**
 * 菜品、食材服务类
 *
 * @author et2302
 * @since 2023-06-14
 */
public interface IngredientService extends IService<Ingredient> {

    /**
     * 添加菜品、食材
     *
     * @param ingredient
     * @return
     */
    boolean add(Ingredient ingredient);

    /**
     * 列表查询
     *
     * @param pageNumber
     * @param pageSize
     * @param ingredient
     * @return
     */
    PageVO<IngredientVO> getList(int pageNumber, int pageSize, Ingredient ingredient);

    /**
     * 封装所有非菜品类型列表
     *
     * @return
     */
    Map<String, List<IngredientTypeVO>> getTypeList();

    /**
     * 删除菜品或食材
     *
     * @param id
     * @return
     */
    boolean deleteIngredient(int id);

    /**
     * 更新菜品或食材
     *
     * @param id
     * @param ingredient
     * @return
     */
    boolean updateIngredient(int id, Ingredient ingredient);

}
