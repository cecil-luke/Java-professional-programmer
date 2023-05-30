package com.etoak.service;

public interface StorageService {

  /**
   * 扣减库存
   *
   * @param productCode
   * @param count
   */
  void deduct(String productCode, int count);

}
