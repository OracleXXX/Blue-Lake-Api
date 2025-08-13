package com.bluelakeapi.mybatis;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;
/**
 * 自动读取数据库表结构生成Do+Mapper+Dao文件
 */

public class MybatisPlusGenerator {

    public static void run(GeneratorProperties generatorProperties) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(generatorProperties.getDbUrl());
        dataSourceConfig.setDriverName(generatorProperties.getDbDriverName());
        dataSourceConfig.setUsername(generatorProperties.getDbUser());
        dataSourceConfig.setPassword(generatorProperties.getDbPwd());
        StrategyConfig strategyConfig = getStrategyConfig(generatorProperties.getTablePrefix(), generatorProperties.getTables());
        executeJava(dataSourceConfig, generatorProperties.getProjectModelName(), generatorProperties.getMybatisModelPath(), generatorProperties.getMybatisMapperPath(), generatorProperties.getMybatisManagerPath(), strategyConfig);
    }

    private static StrategyConfig getStrategyConfig(String tablePrefix, String[] tables) {
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(tablePrefix);
        strategy.setSkipView(true);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(tables);
        strategy.setControllerMappingHyphenStyle(true);
        return strategy;
    }

    private static void executeJava(DataSourceConfig dataSourceConfig, String projectModule, String modelPath, String mapperPath, String servicePath, StrategyConfig strategyConfig) {

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        if (StringUtils.isNotBlank(projectModule)) {
            projectPath += "/" + projectModule + "/src/main/java";
        } else {
            projectPath += "/src/main/java";
        }
        gc.setOutputDir(projectPath);
        gc.setOpen(false);
        gc.setFileOverride(true);
        gc.setServiceName("%sManager");
        gc.setServiceImplName(null);
        gc.setControllerName(null);
        gc.setXmlName(null);
        gc.setMapperName("%sMapper");
        gc.setEntityName("%sDO");
        gc.setAuthor("Auto Generate");
        gc.setDateType(DateType.ONLY_DATE);

        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        mpg.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("");
        pc.setEntity(modelPath);
        pc.setMapper(mapperPath);
        if (servicePath != null) {
            pc.setService(servicePath);
        }
        pc.setServiceImpl(null);
        pc.setController(null);
        mpg.setPackageInfo(pc);

        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setController(null);
        if (servicePath != null) {
            templateConfig.setService("/templates/manager.java.vm");
        } else {
            templateConfig.setService(null);
        }
        templateConfig.setServiceImpl(null);
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);
        mpg.setStrategy(strategyConfig);
        mpg.execute();
    }

}
