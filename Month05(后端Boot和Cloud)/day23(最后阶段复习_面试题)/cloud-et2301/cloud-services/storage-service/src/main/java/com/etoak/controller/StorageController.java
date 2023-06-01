package com.etoak.controller;

import com.etoak.common.ResultVO;
import com.etoak.entity.Storage;
import com.etoak.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/storage")
public class StorageController {

  @Autowired
  StorageService storageService;

  @PostMapping("/deduct")
  public ResultVO deduct(@RequestBody Storage storage) {
    storageService.deduct(storage.getProductCode(), storage.getCount());
    return ResultVO.success("");
  }

  /**
   * 接收POST + FORM参数
   * x-www-form-urlencoded
   */
  @PostMapping("/deduct2")
  public ResultVO deduct2(Storage storage) {
    storageService.deduct(storage.getProductCode(), storage.getCount());
    return ResultVO.success("");
  }

  /*
     @GetMapping("/getCount")
     public ResultVO getCount(int id) {

     }

     OpenFeign定义
     @GetMapping("/storage/getCount")
     ResultVO getCount(@RequestParam("id") int id);
   */

   /*
     @GetMapping("/{id}")
     public ResultVO getCount(@PathVariable int id) {

     }

     OpenFeign定义
     @GetMapping("/storage/{id}")
     ResultVO getCount(@PathVariable int id);
   */

}
