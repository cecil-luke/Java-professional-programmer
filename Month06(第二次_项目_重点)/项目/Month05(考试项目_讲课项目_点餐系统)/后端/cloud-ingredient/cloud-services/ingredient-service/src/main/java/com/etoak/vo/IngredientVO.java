package com.etoak.vo;

import com.etoak.entity.Ingredient;
import lombok.Data;

@Data
public class IngredientVO extends Ingredient {

    /**
     * 主要食材名称
     */
    private String mainIngredientName;

    /**
     * 辅助食材名称
     */
    private String minorIngredientName;

    /**
     * 调料名称
     */
    private String seasoningName;

}
