package com.etoak.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.entity.Dict;
import com.etoak.mapper.DictMapper;
import com.etoak.service.DictService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    public List<Dict> getList(String type) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "value")
                .eq("type", type)
                .orderByAsc("sort");

        return this.list(queryWrapper);
    }
}
