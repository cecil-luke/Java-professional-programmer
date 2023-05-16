package com.etoak.system.controller;


import com.etoak.common.vo.AreaVO;
import com.etoak.common.vo.ResultVO;
import com.etoak.system.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author et2301
 * @since 2023-05-15
 */
@RestController
@RequestMapping("/area")
public class AreaController {

  @Autowired
  AreaService areaService;

  @GetMapping("/list")
  public ResultVO getList() {
    List<AreaVO> areaVOList = areaService.getList();
    return ResultVO.success(areaVOList);
  }

}

