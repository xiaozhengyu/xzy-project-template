package com.xzy.trade.provider.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author xzy.xiao
 * @since 2024/12/19  14:37
 */
@EnableFeignClients(basePackages = {
        "com.xzy.user.api"
})
@EnableDiscoveryClient
@Configuration
public class SpringCloudConfig {
}
