package com.etoak.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 
 * </p>
 *
 * @author et2302
 * @since 2023-06-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotBlank(message = "必填")
    private String username;

    private String password;

    @NotBlank(message = "必填")
    private String email;

    private Integer age;

    /**
     * 1:激活状态, 0:未激活状态
     */
    private Integer state;

    private Long visitCount;

    private Integer money;

    private String createTime;


}
