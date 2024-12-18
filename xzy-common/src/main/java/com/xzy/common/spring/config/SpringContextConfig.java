package com.xzy.common.spring.config;

import com.xzy.common.spring.SpringContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xzy.xiao
 * @since 2024/12/17  18:26
 */

@Configuration
public class SpringContextConfig {

    @Bean
    @ConditionalOnMissingBean
    public SpringContext springContext() {
        return new SpringContext();
    }
}
