package com.etoak.system.service;

import com.etoak.common.dto.CarDTO;
import com.etoak.common.vo.CarVO;
import com.etoak.common.vo.PageVO;
import com.etoak.system.entity.Car;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

  /**
   * 分页查询
   *
   * @param pageNumber
   * @param pageSize
   * @param carVO
   */
  PageVO<CarDTO> getList(int pageNumber, int pageSize, CarVO carVO);

  /**
   * 逻辑删除车辆列表
   *
   * @param idList
   * @return
   */
  boolean deleteCar(List<Long> idList);

  /**
   * 更新车辆信息
   *
   * @param id
   * @param car
   */
  boolean updateCar(long id, Car car);

  /**
   * 查询品牌列表
   *
   * @return
   */
  List<String> getBrand();

  /**
   * 查询车系列表
   *
   * @param brand 品牌参数
   * @return
   */
  List<String> getSeries(@Param("brand") String brand);

}
