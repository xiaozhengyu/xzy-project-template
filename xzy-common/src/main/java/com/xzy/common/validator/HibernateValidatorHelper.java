package com.xzy.common.validator;

import com.alibaba.fastjson.JSON;
import com.xzy.common.exception.ParamException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * JSR-303的Hibernate实现
 */
@Slf4j
public class HibernateValidatorHelper {

    private static final Validator VALIDATOR;

    static {
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            VALIDATOR = validatorFactory.getValidator();
        }
    }

    /**
     * 校验
     *
     * @param value 待校验对象
     */
    public static <V> void verify(V value) {
        if (value == null) {
            return;
        }
        Collection<ConstraintViolation<V>> constraintViolations = parseConstraintViolation(value);
        if (CollectionUtils.isNotEmpty(constraintViolations)) {
            doVerify(constraintViolations, value);
        }
        // 个性的参数校验
        Method method = ReflectionUtils.findMethod(value.getClass(), "verify");
        if (method != null) {
            ReflectionUtils.invokeMethod(method, value);
        }
    }

    public static <V> void verifyAll(V value) {
        if (value == null) {
            return;
        }
        Collection<ConstraintViolation<V>> constraintViolations = parseConstraintViolation(value);
        if (CollectionUtils.isEmpty(constraintViolations)) {
            return;
        }
        List<String> messages = doVerifyAll(constraintViolations);
        // 个性的参数校验
        Method method = ReflectionUtils.findMethod(value.getClass(), "verify");
        if (method != null) {
            try {
                ReflectionUtils.invokeMethod(method, value);
            } catch (ParamException e) {
                messages.add(e.getMessage());
            }
        }
        if (CollectionUtils.isNotEmpty(messages)) {
            log.error("HibernateValidator Exception, Message={}, Params={}, Class={}", messages, JSON.toJSONString(value), value.getClass().getName());
            throw new ParamException(StringUtils.join(messages, "\n"));
        }
    }

    private static <V> Collection<ConstraintViolation<V>> parseConstraintViolation(V value) {
        Set<ConstraintViolation<V>> constraintViolationSet = VALIDATOR.validate(value, VGroups.class, Default.class);
        if (CollectionUtils.isEmpty(constraintViolationSet)) {
            return null;
        }

        // 按属性定义顺序校验
        final List<String> fieldNameList = getFields(value);
        List<ConstraintViolation<V>> constraintViolationList = new ArrayList<>(constraintViolationSet);
        // 按属性定义顺序将ConstraintViolation排序
        constraintViolationList.sort((o1, o2) -> {
            int io1 = fieldNameList.indexOf(o1.getPropertyPath().toString());
            int io2 = fieldNameList.indexOf(o2.getPropertyPath().toString());
            return io1 - io2;
        });

        return constraintViolationList;
    }

    /**
     * 校验
     */
    private static <V> void doVerify(Collection<ConstraintViolation<V>> collection, V value) {
        for (ConstraintViolation<V> violation : collection) {
            log.error("HibernateValidator Exception, Message={}, Params={}", violation.getMessage(), JSON.toJSONString(value));
            throw new ParamException(violation.getMessage());
        }
    }

    /**
     * 校验
     */
    private static <V> List<String> doVerifyAll(Collection<ConstraintViolation<V>> collection) {
        List<String> messages = new ArrayList<>();
        for (ConstraintViolation<V> violation : collection) {
            messages.add(violation.getMessage());
        }
        return messages;
    }

    /**
     * 获取属性（按顺序）
     */
    private static <V> List<String> getFields(V value) {
        List<String> fieldNameList = new ArrayList<>();
        Class<?> clazz = value.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            fieldNameList.add(field.getName());
        }
        return fieldNameList;
    }

}