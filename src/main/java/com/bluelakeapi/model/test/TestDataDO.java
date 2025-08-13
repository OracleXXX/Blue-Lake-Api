package com.bluelakeapi.model.test;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.bluelakeapi.model.BaseDO;
import lombok.*;

/**
 * <p>
 * 测试表
 * </p>
 *
 * @author Auto Generate
 * @since 2025-08-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("test_data")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestDataDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 业务名称
     */
    private String name;


}
