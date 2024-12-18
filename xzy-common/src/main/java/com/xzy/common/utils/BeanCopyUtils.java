package com.xzy.common.utils;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author xzy.xiao
 * @since 2024/12/17  18:26
 */

@SuppressWarnings("DuplicatedCode")
@Slf4j
public class BeanCopyUtils {

    public static void copy(Object source, Object target) {
        if (source == null || target == null) {
            return;
        }

        try {
            BeanUtil.copyProperties(source, target);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void copy(Object source, Object target, String... ignoreProperties) {
        if (source == null || target == null) {
            return;
        }

        try {
            BeanUtil.copyProperties(source, target, ignoreProperties);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static <T> T copy(Object source, Class<T> targetClass) {
        if (source == null || targetClass == null) {
            return null;
        }

        try {
            T target = targetClass.newInstance();
            BeanUtil.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static <T> T copy(Object source, Class<T> targetClass, String... ignoreProperties) {
        if (source == null || targetClass == null) {
            return null;
        }

        try {
            T target = targetClass.newInstance();
            BeanUtil.copyProperties(source, target, ignoreProperties);
            return target;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static <T> List<T> copyList(List<?> source, Class<T> targetClass) {
        if (CollectionUtils.isEmpty(source) || targetClass == null) {
            return Collections.emptyList();
        }

        List<T> list = new ArrayList<>();
        for (Object o : source) {
            T target = copy(o, targetClass);
            if (target != null) {
                list.add(target);
            }
        }
        return list;
    }

    public static <T> List<T> copyList(List<?> source, Class<T> targetClass, String... ignoreProperties) {
        if (CollectionUtils.isEmpty(source) || targetClass == null) {
            return Collections.emptyList();
        }

        List<T> list = new ArrayList<>();
        for (Object o : source) {
            T target = copy(o, targetClass, ignoreProperties);
            if (target != null) {
                list.add(target);
            }
        }
        return list;
    }

}
