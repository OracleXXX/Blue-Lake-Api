package com.bluelakeapi.constant;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum RoleTypeEnum {
    /**
     * 角色类型
     */
    SUPER_MANAGER(1, "super_admin", "超级管理员"),
    BUSINESS_STAFF(50, "business_staff", "业务人员"),
    COMMERCIAL_STAFF(50, "commercial_staff", "商务人员"),
    PROJECT_STAFF(50, "project_staff", "项目人员"),
    FINANCIAL_STAFF(50, "financial_staff", "财务人员"),
    HR(50, "hr", "人事行政"),
    MANAGER(50, "manager", "领导管理股东层"),
    SALES_CHANNEL(50, "sales_channel", "渠道"),
    CLIENT(100, "client", "客户"),
    ;

    private final String name;
    private final String displayName;
    private final Integer level;
    public static final List names = Stream.of(
            SUPER_MANAGER.getName(),
            MANAGER.getName()
    ).collect(Collectors.toList());

    RoleTypeEnum(Integer level, String name, String displayName) {
        this.level = level;
        this.name = name;
        this.displayName = displayName;
    }

    public Integer getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public HashMap<String, Integer> getMapNameToLevel() {
        HashMap<String, Integer> res = new HashMap<>();
        res.put(SUPER_MANAGER.getName(), SUPER_MANAGER.getLevel());
        res.put(MANAGER.getName(), MANAGER.getLevel());

        return res;
    }


}
