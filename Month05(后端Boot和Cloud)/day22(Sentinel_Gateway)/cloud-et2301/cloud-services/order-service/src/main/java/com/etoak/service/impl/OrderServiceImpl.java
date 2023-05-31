package com.etoak.service.impl;

import com.etoak.api.StorageServiceApi;
import com.etoak.common.ResultVO;
import com.etoak.entity.Order;
import com.etoak.entity.Storage;
import com.etoak.exception.StorageException;
import com.etoak.mapper.OrderMapper;
import com.etoak.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  OrderMapper orderMapper;

  /**
   * 扣减库存远程服务的代理对象
   */
  @Autowired
  StorageServiceApi storageServiceApi;

  @Override
  public void create(Order order) {
    // 调用库存服务(http接口: /storage/deduct)
    Storage storage = new Storage();
    storage.setProductCode(order.getProductCode());
    storage.setCount(order.getCount());

    // storageServiceApi.deduct3(order.getProductCode(), order.getCount());

    ResultVO resultVO = storageServiceApi.deduct(storage);
    if (resultVO.getCode() != 200) {
      throw new StorageException(resultVO.getMessage());
    }

    // 创建订单
    orderMapper.insert(order);
  }
}
