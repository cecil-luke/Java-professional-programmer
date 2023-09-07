package com.etoak.controller;

import com.etoak.common.vo.PageVO;
import com.etoak.common.vo.Result;
import com.etoak.entity.Ingredient;
import com.etoak.service.IngredientService;
import com.etoak.vo.IngredientVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Ingredient前端控制器
 *
 * @author et2302
 * @since 2023-06-14
 */
@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    IngredientService ingredientService;

    /**
     * 添加接口
     * post /ingredient
     */
    @PostMapping
    public Result add(@RequestBody Ingredient ingredient) {
        ingredientService.add(ingredient);
        return Result.success(StringUtils.EMPTY);
    }

    /**
     * 分页查询接口
     * get /ingredient
     *
     * @param pageNumber
     * @param pageSize
     * @param ingredient
     * @return
     */
    @GetMapping
    public Result getList(@RequestParam(required = false, defaultValue = "1") int pageNumber,
                          @RequestParam(required = false, defaultValue = "10") int pageSize,
                          Ingredient ingredient) {
        PageVO<IngredientVO> pageVO = ingredientService.getList(pageNumber, pageSize, ingredient);
        return Result.success(pageVO);
    }

    @GetMapping("/list")
    public Result getList() {
        return Result.success(ingredientService.getTypeList());
    }

    /**
     * 删除接口
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id) {
        ingredientService.deleteIngredient(id);
        return Result.success(StringUtils.EMPTY);
    }

    /**
     * 更新接口
     * put /ingredient/{id}
     */
    @PutMapping("/{id}")
    public Result update(@PathVariable int id, @RequestBody Ingredient ingredient) {
        ingredientService.updateIngredient(id, ingredient);
        return Result.success(StringUtils.EMPTY);
    }

}

