package com.etoak.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVO<T> {

    /**
     * 页码
     */
    private Integer pageNumber;

    /**
     * 每页记录数
     */
    private Integer pageSize;

    /**
     * 记录
     */
    private List<T> rows;

    /**
     * 总数
     */
    private Long total;
}
