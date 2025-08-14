package com.bluelakeapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.bluelakeapi.dao")
public class BlueLakeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlueLakeApiApplication.class, args);
    }

    @Autowired
    private DataSource dataSource;

    @Bean
    public CommandLineRunner testConnection() {
        return args -> {
            try (Connection conn = dataSource.getConnection()) {
                System.out.println("✅ 数据库连接成功：" + conn.getMetaData().getURL());
            } catch (Exception e) {
                System.err.println("❌ 数据库连接失败：" + e.getMessage());
            }
        };
    }


}
