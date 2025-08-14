package com.bluelakeapi.config;

import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusPageConfig {

    /**
     * pagehelper的分页插件
     */
    @Bean
    public PageInterceptor pageInterceptor() {
        return new PageInterceptor();
    }

}
