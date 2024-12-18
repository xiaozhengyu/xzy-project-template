package com.xzy.common.exception.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author xzy.xiao
 * @since 2024/12/17  18:26
 */

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({ControllerExceptionAdviceImport.class})
public @interface EnableControllerExceptionAdvice {

    /**
     * 是否打印body参数
     * <p>
     * 为true时会注入一个解析Body参数的Filter。如果项目中已存在类似RequestWrapper功能的Filter时，将无法打印body参数，谨慎使用！
     */
    boolean printBodyParameters() default true;
}
