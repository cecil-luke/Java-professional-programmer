package com.etoak.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.etoak.system.entity.Dict;
import com.etoak.system.mapper.DictMapper;
import com.etoak.system.service.DictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务实现类
 *
 * @author et2301
 * @since 2023-05-15
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

  @Override
  public List<Dict> getList(String type) {
    QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
    queryWrapper.select("name", "value")
      .eq("type", type).orderByAsc("sort");

    // list()方法在IService接口中
    return list(queryWrapper);
  }
}
