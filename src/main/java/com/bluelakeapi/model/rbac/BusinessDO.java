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
 * 业务表
 * </p>
 *
 * @author Auto Generate
 * @since 2025-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("business")
public class BusinessDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 业务名称
     */
    private String name;

    /**
     * 业务展示名称
     */
    private String displayName;

    /**
     * 业务描述
     */
    private String description;


}
