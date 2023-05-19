package com.etoak.system.mapper;

import com.etoak.common.dto.CarDTO;
import com.etoak.common.vo.CarVO;
import com.etoak.system.entity.Car;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * CarMapper 接口
 *
 * @author et2301
 * @since 2023-05-16
 */
public interface CarMapper extends BaseMapper<Car> {

  /**
   * 查询车辆列表
   *
   * @param carVO
   * @return
   */
  List<CarDTO> getList(CarVO carVO);

  /**
   * 更新状态
   *
   * @param idList
   */
  int updateStatus(@Param("idList") List<Long> idList);

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
