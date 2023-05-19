package com.etoak.system.controller;


import com.etoak.common.dto.CarDTO;
import com.etoak.common.vo.CarVO;
import com.etoak.common.vo.PageVO;
import com.etoak.common.vo.ResultVO;
import com.etoak.system.entity.Car;
import com.etoak.system.service.CarService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

  /**
   * 查询接口
   * get /car
   */
  @GetMapping
  public ResultVO getList(
    @RequestParam(required = false, defaultValue = "1") int pageNumber,
    @RequestParam(required = false, defaultValue = "10") int pageSize,
    CarVO carVO) {
    PageVO<CarDTO> pageVO = carService.getList(pageNumber, pageSize, carVO);
    return ResultVO.success(pageVO);
  }

  /**
   * 删除接口
   */
  @DeleteMapping
  public ResultVO delete(@RequestBody List<Long> idList) {
    boolean isSuccess = carService.deleteCar(idList);
    if (!isSuccess) {
      return ResultVO.failed("删除失败！");
    }
    return ResultVO.success(StringUtils.EMPTY);
  }

  /**
   * 更新接口
   * put /car/{id}
   */
  @PutMapping("/{id}")
  public ResultVO update(@PathVariable long id, @RequestBody Car car) {
    boolean isSuccess = carService.updateCar(id, car);
    if (!isSuccess) {
      return ResultVO.failed("更新失败！");
    }
    return ResultVO.success(StringUtils.EMPTY);
  }

  /**
   * 品牌列表接口
   * get /car/brand
   */
  @GetMapping("/brand")
  public ResultVO<List<String>> getBrand() {
    return ResultVO.success(carService.getBrand());
  }

  /**
   * 车系列表接口
   *
   * @param brand
   * @return
   */
  @GetMapping("/series")
  public ResultVO<List<String>> getSeries(String brand) {
    return ResultVO.success(carService.getSeries(brand));
  }

}

