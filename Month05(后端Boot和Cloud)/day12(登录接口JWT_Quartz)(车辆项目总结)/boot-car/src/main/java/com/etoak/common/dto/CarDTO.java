package com.etoak.common.dto;

import com.etoak.system.entity.Car;
import lombok.Data;

/**
 * 封装车辆查询结果
 */
@Data
public class CarDTO extends Car {

  private String levelName;

  private String gearboxName;

  private String dispName;

}
