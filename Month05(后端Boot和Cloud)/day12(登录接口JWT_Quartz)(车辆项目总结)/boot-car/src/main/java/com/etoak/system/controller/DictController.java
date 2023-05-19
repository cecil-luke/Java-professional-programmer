package com.etoak.system.controller;


import com.etoak.common.vo.ResultVO;
import com.etoak.system.entity.Dict;
import com.etoak.system.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * DictController
 *
 * @author et2301
 * @since 2023-05-15
 */
@RestController
@RequestMapping("/dict")
public class DictController {

  @Autowired
  DictService dictService;

  /**
   * 字典接口
   * get /dict/list
   */
  @GetMapping("/list")
  public ResultVO<List<Dict>> getList(String type) {
    List<Dict> dictList = dictService.getList(type);
    return ResultVO.success(dictList);
  }

}

