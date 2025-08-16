package com.bluelakeapi.mybatis;


import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;

/**
 * 脚本生成DAO相关文件
 */
public class BLueLakeGenerator {

    /**
     * 测试表 如果文件已存在，会覆盖 慎用
     */
    @Test
    public void runTestData() {
        GeneratorProperties generatorProperties = new GeneratorProperties();
        generatorProperties.setProjectDirName("Blue-Lake-Api");
        generatorProperties.setMybatisMapperPath("com.bluelakeapi.dao.test");
        generatorProperties.setMybatisModelPath("com.bluelakeapi.model.test");
        generatorProperties.setMybatisManagerPath("com.bluelakeapi.manager.test");
        generatorProperties.setDbUrl("jdbc:mysql://localhost:3306/bluelake?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2b8");
        generatorProperties.setDbUser("root");
        generatorProperties.setDbPwd("123456");
        generatorProperties.setDbDriverName("com.mysql.cj.jdbc.Driver");
        generatorProperties.setTables(new String[]{
                "test_data"
        });
        MybatisPlusGenerator.run(generatorProperties);
    }
    @Test
    public void runRbac() {
        GeneratorProperties generatorProperties = new GeneratorProperties();
        generatorProperties.setProjectDirName("Blue-Lake-Api");
        generatorProperties.setMybatisMapperPath("com.bluelakeapi.dao.rbac");
        generatorProperties.setMybatisModelPath("com.bluelakeapi.model.rbac");
        generatorProperties.setMybatisManagerPath("com.bluelakeapi.manager.rbac");
        generatorProperties.setDbUrl("jdbc:mysql://localhost:3306/bluelake?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2b8");
        generatorProperties.setDbUser("root");
        generatorProperties.setDbPwd("123456");
        generatorProperties.setDbDriverName("com.mysql.cj.jdbc.Driver");
        generatorProperties.setTables(new String[]{
                // "business"
                // "employee"
                // "employee_role"
                // "permission"
                // "role"
                "role_permission"
        });
        MybatisPlusGenerator.run(generatorProperties);
    }

}
