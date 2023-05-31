package com.etoak.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.etoak.entity.Storage;
import com.etoak.mapper.StorageMapper;
import com.etoak.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class StorageServiceImpl implements StorageService {

  @Autowired
  StorageMapper storageMapper;

  @Override
  public void deduct(String productCode, int count) {
    // 先获取库存
    QueryWrapper<Storage> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("product_code", productCode);
    Storage storage = storageMapper.selectOne(queryWrapper);
    if (!ObjectUtils.isEmpty(storage)) {
      Integer storageCount = storage.getCount();

      // 扣减后的库存
      count = storageCount - count;
      if (count < 0) {
        throw new RuntimeException("库存不足！");
      }

      storage.setCount(count);
      storageMapper.updateById(storage);
    }
  }
}
