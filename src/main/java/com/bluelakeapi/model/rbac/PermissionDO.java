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
 * 权限表
 * </p>
 *
 * @author Auto Generate
 * @since 2025-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("permission")
public class PermissionDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限操作
     */
    private String action;

    /**
     * 业务id
     */
    private Integer businessId;

    /**
     * 权限描述
     */
    private String description;

    /**
     * 权限名称
     */
    private String displayName;

    /**
     * 排序
     */
    private Integer sortNum;

}
