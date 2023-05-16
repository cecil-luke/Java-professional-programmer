package com.etoak.system.service;

import com.etoak.system.entity.Car;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * CarService服务类
 *
 * @author et2301
 * @since 2023-05-16
 */
public interface CarService extends IService<Car> {

  /**
   * 添加车辆信息
   *
   * @return
   */
  boolean addCar(Car car);

}
