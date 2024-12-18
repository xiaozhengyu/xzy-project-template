package com.xzy.common.exception.config;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @author xzy.xiao
 * @since 2024/12/17  18:26
 */

public class ControllerExceptionAdviceImport implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        // 全局异常拦截
        registry.registerBeanDefinition("controllerExceptionAdvice", new RootBeanDefinition(ControllerExceptionAdvice.class));

        // 是否打印body参数
        Map<String, Object> attrs = metadata.getAnnotationAttributes(EnableControllerExceptionAdvice.class.getName());
        if (attrs == null) {
            return;
        }
        boolean printBodyParameters = (boolean) attrs.getOrDefault("printBodyParameters", false);
        if (printBodyParameters) {
            BeanDefinitionBuilder filterBeanDefinition = BeanDefinitionBuilder.genericBeanDefinition(FilterRegistrationBean.class);
            filterBeanDefinition.addPropertyReference("filter", "requestBodyCacheFilter");
            filterBeanDefinition.addPropertyValue("urlPatterns", "/*");
            registry.registerBeanDefinition("requestBodyCacheFilterRegistration", filterBeanDefinition.getBeanDefinition());
            registry.registerBeanDefinition("requestBodyCacheFilter", new RootBeanDefinition(RequestBodyCacheFilter.class));
        }
    }
}
