package com.bluelakeapi.model.rbac;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.bluelakeapi.model.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 员工表
 * </p>
 *
 * @author Auto Generate
 * @since 2025-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("employee")
public class EmployeeDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 登录邮箱
     */
    private String email;

    /**
     * 用户uid
     */
    private String employeeId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

}
