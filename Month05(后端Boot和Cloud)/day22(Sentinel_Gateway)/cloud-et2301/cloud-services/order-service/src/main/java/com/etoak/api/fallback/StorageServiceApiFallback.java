package com.etoak.api.fallback;

import com.etoak.api.StorageServiceApi;
import com.etoak.common.ResultVO;
import com.etoak.entity.Storage;
import org.springframework.stereotype.Service;

@Service
public class StorageServiceApiFallback implements StorageServiceApi {

    @Override
    public ResultVO deduct(Storage storage) {
        return ResultVO.failed("库存不足");
    }

    @Override
    public ResultVO deduct2(Storage storage) {
        return ResultVO.failed("库存不足");
    }

    @Override
    public ResultVO deduct3(String productCode, int count) {
        return ResultVO.failed("库存不足");
    }
}
