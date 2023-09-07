package com.etoak.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.etoak.entity.Dict;

import java.util.List;

public interface DictService extends IService<Dict> {

    /**
     * 根据字典类型查询字典列表
     *
     * @param type
     * @return
     */
    List<Dict> getList(String type);

}
