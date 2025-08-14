package com.bluelakeapi.mybatis;


import lombok.Data;
@Data
public class GeneratorProperties {

    private String projectDirName;

    private String projectModelName;

    private String mybatisModelPath;

    private String mybatisMapperPath;

    private String mybatisManagerPath;

    private String dbUrl;

    private String dbDriverName = "com.mysql.jdbc.Driver";

    private String dbUser;

    private String dbPwd;

    private String[] tables;

    private String tablePrefix = "";
}
