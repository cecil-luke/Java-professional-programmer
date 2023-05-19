package com.etoak.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class AreaVO {

  private Integer id;

  private String name;

  private List<AreaVO> children;

}
