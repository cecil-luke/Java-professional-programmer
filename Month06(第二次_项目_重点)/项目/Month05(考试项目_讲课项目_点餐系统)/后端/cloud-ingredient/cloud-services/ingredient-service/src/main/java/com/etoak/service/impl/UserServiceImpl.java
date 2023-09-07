package com.etoak.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.etoak.common.constant.CommonConstant;
import com.etoak.common.constant.QueueConstant;
import com.etoak.common.exception.ParamException;
import com.etoak.common.util.JwtUtil;
import com.etoak.common.util.ValidatorUtil;
import com.etoak.common.vo.Email;
import com.etoak.context.LoginUserContext;
import com.etoak.entity.User;
import com.etoak.mapper.UserMapper;
import com.etoak.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.etoak.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 *  服务实现类
 *
 * @author et2302
 * @since 2023-06-20
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public String login(UserVO userVO) {
        // 校验
        ValidatorUtil.validate(userVO);

        // 验证码
        String key = CommonConstant.REDIS_CODE_PREFIX + userVO.getUuid();
        String savedCode = stringRedisTemplate.opsForValue().get(key);
        if (!StringUtils.equals(userVO.getCode(), savedCode)) {
            throw new ParamException("验证码错误或已过期！");
        }

        /* 验证用户名 */
        User user = this.getUser(userVO.getUsername());
        if (ObjectUtils.isEmpty(user)) {
            log.warn("username error！");
            throw new ParamException("用户名或密码错误！");
        }

        /* 验证密码 */
        String password = MD5.create().digestHex(userVO.getPassword());
        if (!StringUtils.equals(password, user.getPassword())) {
            throw new ParamException("用户名或密码错误！");
        }

        // 将登录用户写入Redis 30分钟过期
        stringRedisTemplate.opsForValue().set(CommonConstant.REDIS_LOGIN_PREFIX + user.getUsername(),
                JSONUtil.toJsonStr(user),
                JwtUtil.EXPIRE,
                TimeUnit.MILLISECONDS);

        // 创建JWT
        return JwtUtil.createToken(userVO.getUsername());
    }

    @Override
    public User getUser(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return getOne(queryWrapper);
    }

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Transactional
    @Override
    public boolean register(User user) {
        // 校验
        ValidatorUtil.validate(user);

        /**
         * 检验用户是否存在
         */
        User saveUser = getUser(user.getUsername());
        if(ObjectUtils.isNotEmpty(saveUser)) {
            throw new ParamException("用户已存在！");
        }

        // 密码
        user.setPassword(MD5.create().digestHex("123456"));

        // 创建时间
        user.setCreateTime(DateUtil.now());

        boolean isSuccess =  this.save(user);

        // 发送消息到邮件队列(收件人、邮件主题、邮件内容、发件人)
        Email email = new Email(user.getEmail(),
                "激活邮件！",
                "请点击右侧链接激活：http://localhost:8080/ingredient-sys/user/active?username=" + user.getUsername()
        );
        // 消息
        String message = JSONUtil.toJsonStr(email);

        rabbitTemplate.convertAndSend(QueueConstant.EMAIL_EXCHANGE, QueueConstant.EMIL_KEY, message);

        return isSuccess;
    }

    @Override
    public void logout() {
        User loginUser = LoginUserContext.getLoginUser();
        String redisKey = CommonConstant.REDIS_LOGIN_PREFIX + loginUser.getUsername();
        stringRedisTemplate.delete(redisKey);
    }
}
