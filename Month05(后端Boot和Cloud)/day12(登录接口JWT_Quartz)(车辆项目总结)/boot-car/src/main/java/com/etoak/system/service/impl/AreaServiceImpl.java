package com.etoak.system.service.impl;

import com.etoak.common.vo.AreaVO;
import com.etoak.system.entity.Area;
import com.etoak.system.mapper.AreaMapper;
import com.etoak.system.service.AreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author et2301
 * @since 2023-05-15
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {

  @Autowired
  AreaMapper areaMapper;

  @Override
  public List<AreaVO> getList() {
    return areaMapper.getList();
  }
}
