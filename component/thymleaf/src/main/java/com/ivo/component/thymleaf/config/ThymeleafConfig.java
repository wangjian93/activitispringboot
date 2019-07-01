package com.ivo.component.thymleaf.config;

import com.ivo.component.thymleaf.TimoDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: wj
 * @Date: 2019-06-11 00:12
 * @Version 1.0
 */
@Configuration
public class ThymeleafConfig {
    /**
     * 配置自定义的CusDialect，用于整合thymeleaf模板
     */
    @Bean
    public TimoDialect getTimoDialect(){
        return new TimoDialect();
    }
}
