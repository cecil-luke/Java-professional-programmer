package com.etoak.controller;

import com.etoak.common.vo.Result;
import com.etoak.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    UploadService uploadService;

    /**
     * 图片上传接口
     */
    @PostMapping("/image")
    public Result uploadImage(MultipartFile file) {
        String path = uploadService.uploadImage(file);
        return Result.success(path);
    }
}
