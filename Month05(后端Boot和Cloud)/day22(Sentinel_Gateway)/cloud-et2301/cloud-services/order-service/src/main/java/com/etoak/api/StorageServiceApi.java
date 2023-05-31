package com.etoak.api;

import com.etoak.api.fallback.StorageServiceApiFallback;
import com.etoak.common.ResultVO;
import com.etoak.entity.Storage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 声明式HTTP客户端
 * name或value属性的值: 要调用的服务的服务名称(spring.application.name的值)
 *
 * fallback属性: 调用远程服务失败之后执行StorageServiceApiFallback对应的方法
 */
@FeignClient(name = "storage-service", fallback = StorageServiceApiFallback.class)
public interface StorageServiceApi {

  /**
   * 扣减库存接口
   */
  @PostMapping("/storage/deduct")
  ResultVO deduct(@RequestBody Storage storage);

  /**
   * 扣减库存接口
   * 注解@SpringQueryMap: 允许客户端使用Java Bean的方式调用远程的http+form的接口
   */
  @PostMapping("/storage/deduct2")
  ResultVO deduct2(@SpringQueryMap Storage storage);

  /**
   *
   */
  @PostMapping("/storage/deduct2")
  ResultVO deduct3(@RequestParam("productCode") String productCode,
                   @RequestParam("count") int count);

}
