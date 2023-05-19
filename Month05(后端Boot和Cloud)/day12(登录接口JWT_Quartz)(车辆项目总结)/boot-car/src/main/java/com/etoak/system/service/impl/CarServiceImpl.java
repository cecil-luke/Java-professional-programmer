package com.etoak.system.service.impl;

import cn.hutool.core.date.DateUtil;
import com.etoak.common.dto.CarDTO;
import com.etoak.common.exception.ParamException;
import com.etoak.common.utils.ValidatorUtil;
import com.etoak.common.vo.CarVO;
import com.etoak.common.vo.PageVO;
import com.etoak.system.entity.Car;
import com.etoak.system.mapper.AreaMapper;
import com.etoak.system.mapper.CarMapper;
import com.etoak.system.service.CarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

  @Autowired
  CarMapper carMapper;

  @Transactional(rollbackFor = Exception.class)
  @Override
  public boolean addCar(Car car) {
    // 校验参数
    ValidatorUtil.validate(car);

    setLocation(car);

    // 设置创建时间
    car.setCreateTime(DateUtil.now());
    car.setUpdateTime(DateUtil.now());

    // 添加信息
    return save(car);
  }

  private void setLocation(Car car) {
    /* 设置location(省名称-市名称) */
    List<Integer> idList = Arrays.asList(car.getProvince(), car.getCity());
    List<String> nameList = areaMapper.getNameList(idList);
    if (CollectionUtils.isEmpty(nameList) || nameList.size() != 2) {
      throw new ParamException("省市区参数错误！");
    }

    // [山东省, 济南市] 转成 "山东省-济南市"
    car.setLocation(StringUtils.join(nameList, "-"));
  }

  @Override
  public PageVO<CarDTO> getList(int pageNumber, int pageSize, CarVO carVO) {

    // 处理价格参数
    this.handlePriceList(carVO);

    // 处理车龄参数
    this.handleCarAge(carVO);

    // 设置分页条件
    PageHelper.startPage(pageNumber, pageSize);

    // 查询数据
    List<CarDTO> carDTOList = carMapper.getList(carVO);

    // 创建PageInfo
    PageInfo<CarDTO> pageInfo = new PageInfo<>(carDTOList);

    // 返回结果
    return new PageVO<>(pageInfo.getPageNum(), pageInfo.getPageSize(), carDTOList, pageInfo.getTotal());
  }

  private void handleCarAge(CarVO carVO) {
    String carAge = carVO.getCarAge();
    if (StringUtils.isNotEmpty(carAge)) {
      // "0-1" => ["0", "1"]
      String[] carAgeArray = carAge.split("-");

      LocalDate now = LocalDate.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

      if (!StringUtils.equals(carAgeArray[0], "0")) {
        LocalDate localDate = now.minusYears(Long.valueOf(carAgeArray[0]));
        String leDate = formatter.format(localDate);
        carVO.setLeDate(leDate);
      }

      if (!StringUtils.equals(carAgeArray[1], "0")) {
        LocalDate localDate = now.minusYears(Long.valueOf(carAgeArray[1]));
        String geDate = formatter.format(localDate);
        carVO.setGeDate(geDate);
      }
    }
  }

  private void handlePriceList(CarVO carVO) {
    // 价格参数['0-3', '5-8']
    List<String> priceList = carVO.getPriceList();

    if (CollectionUtils.isNotEmpty(priceList)) {
      List<Map<String, Double>> mapList = priceList.stream().map(priceString -> {
        Map<String, Double> priceMap = new HashMap<>();

        String[] priceArray = priceString.split("-");
        priceMap.put("min", Double.valueOf(priceArray[0]));
        priceMap.put("max", Double.valueOf(priceArray[1]));

        return priceMap;
      }).collect(Collectors.toList());

      carVO.setPriceMapList(mapList);
    }
  }

  @Transactional
  @Override
  public boolean deleteCar(List<Long> idList) {
    return carMapper.updateStatus(idList) > 0;
  }

  @Transactional
  @Override
  public boolean updateCar(long id, Car car) {
    Car savedCar = getById(id);
    if (ObjectUtils.isEmpty(savedCar)) {
      throw new ParamException("没有要更新的车辆信息！");
    }

    /* 当province和city都不为空时修改归属地 */
    if (ObjectUtils.isNotEmpty(car.getProvince()) && ObjectUtils.isNotEmpty(car.getCity())) {
      this.setLocation(car);
    }

    // 设置更新时间
    car.setUpdateTime(DateUtil.now());

    // 重新设置id
    car.setId(id);

    // 更新
    return updateById(car);
  }

  @Override
  public List<String> getBrand() {
    return carMapper.getBrand();
  }

  @Override
  public List<String> getSeries(String brand) {
    return carMapper.getSeries(brand);
  }
}
