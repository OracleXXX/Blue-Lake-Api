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
 * 角色和权限关系表
 * </p>
 *
 * @author Auto Generate
 * @since 2025-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("role_permission")
public class RolePermissionDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关联权限id
     */
    private Integer permissionId;

    /**
     * 关联角色id
     */
    private Integer roleId;

}
