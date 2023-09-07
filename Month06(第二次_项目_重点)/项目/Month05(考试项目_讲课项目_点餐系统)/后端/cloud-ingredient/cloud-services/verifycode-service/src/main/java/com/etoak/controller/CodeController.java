package com.etoak.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import com.etoak.common.constant.CommonConstant;
import com.etoak.common.vo.Result;
import com.etoak.vo.CodeVO;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/code")
@Slf4j
public class CodeController {

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/getCode")
    public Result getCode() {
        // 创建验证码文本
        String text = defaultKaptcha.createText();

        /*
           将文本内容写入redis - string类型  设置有效期(5分钟)
           key = code:uuid的值
           value = 创建验证码文本
         */
        String uuid = IdUtil.simpleUUID();
        String key = CommonConstant.REDIS_CODE_PREFIX + uuid;
        stringRedisTemplate.opsForValue()
                .set(key, text, 5, TimeUnit.MINUTES);

        // 验证码图片(将验证码写入图片)
        BufferedImage image = defaultKaptcha.createImage(text);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", outputStream);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Result.failed("验证码创建失败！");
        }

        System.out.println(uuid + "-" + text);

        String base64Code = Base64.encode(outputStream.toByteArray());
        String codeUrl = "data:image/png;base64," + base64Code;
        return Result.success(new CodeVO(uuid, codeUrl));
    }

}
