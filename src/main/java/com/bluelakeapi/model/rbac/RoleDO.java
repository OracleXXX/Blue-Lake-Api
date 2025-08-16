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
 * 角色表
 * </p>
 *
 * @author Auto Generate
 * @since 2025-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("role")
public class RoleDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 角色展示名称
     */
    private String displayName;

    /**
     * 角色等级
     */
    private Integer level;

    /**
     * 角色后端代码
     */
    private String name;

}
