package com.imooc.pojo;

import com.imooc.pojo.supers.BaseEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表 
 * </p>
 *
 * @author QG Code Generate
 * @since 2022-04-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Users extends BaseEntity {

    private static final long serialVersionUID=1L;

    private String userId;
    /**
     * 用户名 用户名
     */
    private String username;

    /**
     * 密码 密码
     */
    private String password;

    /**
     * 昵称 昵称
     */
    private String nickname;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 头像
     */
    private String face;

    /**
     * 手机号 手机号
     */
    private String mobile;

    /**
     * 邮箱地址 邮箱地址
     */
    private String email;

    /**
     * 性别 性别 1:男  0:女  2:保密
     */
    private Integer sex;

    /**
     * 生日 生日
     */
    private LocalDate birthday;



}
