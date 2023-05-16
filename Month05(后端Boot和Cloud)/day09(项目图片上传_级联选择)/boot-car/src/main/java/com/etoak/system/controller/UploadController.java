package com.etoak.system.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.etoak.common.properties.ImageProperties;
import com.etoak.common.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/upload")
@Slf4j
public class UploadController {

  @Autowired
  ImageProperties imageProperties;

  /**
   * 图片上传接口
   * post /upload/image
   */
  @PostMapping("/image")
  public ResultVO<String> uploadImage(MultipartFile file) {
    /* 验证图片大小 */
    if (ObjectUtils.isEmpty(file) || file.isEmpty()) {
      return ResultVO.failed("文件不能为空！");
    }

    // 验证文件类型
    String contentType = file.getContentType();
    if (!imageProperties.getTypes().contains(contentType)) {
      String msg = imageProperties.getTypes().stream()
        .map(type -> type.substring(type.lastIndexOf("/") + 1))
        .collect(Collectors.joining("、"));
      return ResultVO.failed("仅支持" + msg + "格式的图片！");
    }

    /* 修改图片名称 */
    String originalFilename = file.getOriginalFilename();
    String filename = IdUtil.simpleUUID() +
      originalFilename.substring(originalFilename.lastIndexOf("."));

    /* 上传目录 */
    File destDir = new File(imageProperties.getDir());
    if (!destDir.exists()) {
      destDir.mkdirs();
    }

    // 目标文件
    File destFile = new File(destDir, filename);

    try {
      // 上传
      file.transferTo(destFile);
    } catch (IOException e) {
      log.error(e.getMessage(), e);
      return ResultVO.failed("图片上传失败！");
    }

    String prefix = imageProperties.getPathPrefix();
    String path = prefix.endsWith("/") ?
      prefix + filename : prefix + "/" + filename;

    return ResultVO.success(path);
  }
}
