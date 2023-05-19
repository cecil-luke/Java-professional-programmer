package com.etoak.common.vo;

import com.etoak.system.entity.Car;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 封装查询条件
 */
@Data
public class CarVO extends Car {

  /**
   * 接收客户端传入的参数
   * 参数举例: ['0-3', '5-8']
   */
  private List<String> priceList;

  /**
   * 传入到SQL的条件, 由priceList参数转换过来的
   * 举例: priceList =  ['0-3', '5-8']
   * 那么: priceMapList = [ {min=0, max=3}, {min=5, max=8} ]
   */
  private List<Map<String, Double>> priceMapList;

  /**
   * 客户端传入的车龄参数 0-1 或者 1-3 或者 10-0
   */
  private String carAge;

  /**
   * 如果carAge = 1-3
   * 那么leDate = '2022-05-18'  (当前时间为2023.05.18)
   * <=2022
   */
  private String leDate;

  /**
   * 如果carAge = 1-3
   * 那么geDate = '2020-05-18'  (当前时间为2023.05.18)
   * >= 2020
   */
  private String geDate;

}
