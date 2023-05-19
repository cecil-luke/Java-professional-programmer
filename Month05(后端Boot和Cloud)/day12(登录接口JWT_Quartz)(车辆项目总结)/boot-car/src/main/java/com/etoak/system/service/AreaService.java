package com.etoak.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.common.vo.AreaVO;
import com.etoak.system.entity.Area;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author et2301
 * @since 2023-05-15
 */
public interface AreaService extends IService<Area> {

  /**
   * 省市区级联列表
   *
   * @return
   */
  List<AreaVO> getList();
}
