package com.etoak.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.etoak.common.exception.ParamException;
import com.etoak.properties.ImageProperties;
import com.etoak.service.UploadService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    ImageProperties imageProperties;

    @Override
    public String uploadImage(MultipartFile file) {
        /* 1、验证文件是否为空 */
        if (ObjectUtils.isEmpty(file) || file.isEmpty()) {
            throw new ParamException("文件不能为空");
        }

        /* 2、验证类型 */
        String contentType = file.getContentType();
        List<String> types = imageProperties.getTypes();
        if (!types.contains(contentType)) {
            String message = types.stream()
                    .map(type -> type.substring(type.indexOf("/") + 1))
                    .collect(Collectors.joining("、"));
            throw new ParamException("仅支持" + message + "类型");
        }

        /* 3、创建文件名称 */
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uuid = IdUtil.simpleUUID();
        String filename = StringUtils.join(uuid, extension);

        /* 4、创建上传目录 */
        File uploadDir = new File(imageProperties.getDir());
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 5、创建目标文件
        File destFile = new File(uploadDir, filename);

        /* 6、执行文件上传 */
        try {
            file.transferTo(destFile);
        } catch (IOException e) {
            throw new ParamException("上传失败！");
        }

        /* 7、返回结果 */
        String prefix = imageProperties.getPrefix();
        return prefix.endsWith("/") ? StringUtils.join(prefix, filename)
                : StringUtils.join(prefix, "/", filename);
    }
}
