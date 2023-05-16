package com.etoak.system.controller;


import com.etoak.common.vo.ResultVO;
import com.etoak.system.entity.Car;
import com.etoak.system.service.CarService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * CarController
 *
 * @author et2301
 * @since 2023-05-16
 */
@RestController
@RequestMapping("/car")
public class CarController {

  @Autowired
  CarService carService;

  /**
   * 添加接口
   * post /car
   */
  @PostMapping
  public ResultVO addCar(@RequestBody Car car) {
    boolean isSuccess = carService.addCar(car);
    if (!isSuccess) {
      return ResultVO.failed("添加失败！");
    }
    return ResultVO.success(StringUtils.EMPTY);
  }

}

