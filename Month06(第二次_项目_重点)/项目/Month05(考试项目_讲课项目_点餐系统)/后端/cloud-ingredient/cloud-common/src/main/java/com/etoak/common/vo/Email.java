package com.etoak.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Lushisan
 * @Date 2023/6/25 17:36
 * @Title: Email
 * @Description:
 * @Package com.etoak.common.vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email {

    /**
     * 收件人
     */
    private String receiver;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;
}
