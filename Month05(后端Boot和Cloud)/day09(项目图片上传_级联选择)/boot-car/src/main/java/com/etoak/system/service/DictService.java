package com.etoak.system.service;

import com.etoak.system.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * DictService服务类
 *
 * @author et2301
 * @since 2023-05-15
 */
public interface DictService extends IService<Dict> {

  /**
   * 根据字典类型获取字典列表
   *
   * @param type
   * @return
   */
  List<Dict> getList(String type);

}
