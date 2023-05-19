package com.etoak.system.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import com.etoak.common.vo.ResultVO;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class CodeController {

  @Autowired
  DefaultKaptcha kaptcha;

  @Autowired
  StringRedisTemplate stringRedisTemplate;

  public static final String KEY_PREFIX = "code:";

  @GetMapping("/getCode")
  public ResultVO getCode() {
    // 验证码文本
    String codeText = kaptcha.createText();

    // 验证码图片
    BufferedImage image = kaptcha.createImage(codeText);

    // key => "code:uuid的值"   value => codeText
    String uuid = IdUtil.simpleUUID();
    String key = KEY_PREFIX + uuid;

    // 验证码两分钟过期
    stringRedisTemplate.opsForValue()
      .set(key, codeText, 2, TimeUnit.MINUTES);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try {
      ImageIO.write(image, "png", outputStream);
    } catch (IOException e) {
      return ResultVO.failed("验证码创建失败！");
    }

    String base64 = Base64.encode(outputStream.toByteArray());

    // png图片格式的base64编码
    String code = "data:image/png;base64," + base64;

    Map<Object, Object> data = MapUtil.builder()
      .put("uuid", uuid)
      .put("code", code)
      .build();

    System.out.println("uuid:" + uuid);
    System.out.println("code:" + codeText);

    return ResultVO.success(data);
  }

}
