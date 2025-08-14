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
//                "group",
//                "channel",
//                "user_group_relation",
//                "user_channel_relation",
//                "private_group",
//                "channel_message"
//                "qrcode_reserve",
                "test_data"
        });
        MybatisPlusGenerator.run(generatorProperties);
    }

}
