package com.etoak.system.service.impl;

import cn.hutool.core.date.DateUtil;
import com.etoak.common.exception.ParamException;
import com.etoak.common.utils.ValidatorUtil;
import com.etoak.system.entity.Car;
import com.etoak.system.mapper.AreaMapper;
import com.etoak.system.mapper.CarMapper;
import com.etoak.system.service.CarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * CarService服务实现类
 *
 * @author et2301
 * @since 2023-05-16
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {

  @Autowired
  AreaMapper areaMapper;

  @Transactional(rollbackFor = Exception.class)
  @Override
  public boolean addCar(Car car) {
    // 校验参数
    ValidatorUtil.validate(car);

    /* 设置location(省名称-市名称) */
    List<Integer> idList = Arrays.asList(car.getProvince(), car.getCity());
    List<String> nameList = areaMapper.getNameList(idList);
    if (CollectionUtils.isEmpty(nameList) || nameList.size() != 2) {
      throw new ParamException("省市区参数错误！");
    }

    // [山东省, 济南市] 转成 "山东省-济南市"
    car.setLocation(StringUtils.join(nameList, "-"));

    // 设置创建时间
    car.setCreateTime(DateUtil.now());
    car.setUpdateTime(DateUtil.now());

    // 添加信息
    return save(car);
  }
}
