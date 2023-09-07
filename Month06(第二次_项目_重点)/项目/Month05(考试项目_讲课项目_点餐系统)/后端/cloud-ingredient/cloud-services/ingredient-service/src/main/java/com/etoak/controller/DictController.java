package com.etoak.controller;

import com.etoak.common.vo.Result;
import com.etoak.entity.Dict;
import com.etoak.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 字典接口服务
 */
@RestController
@RequestMapping("/dict")
public class DictController {

    @Autowired
    DictService dictService;

    /**
     * 字典列表接口
     * get /dict/list?type=
     *
     * @param type
     * @return
     */
    @GetMapping("/list")
    public Result list(@RequestParam String type) {
        List<Dict> dictList = dictService.getList(type);
        return Result.success(dictList);
    }

}
