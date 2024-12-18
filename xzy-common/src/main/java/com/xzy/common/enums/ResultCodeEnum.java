package com.xzy.common.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xzy.xiao
 * @since 2024/12/17  18:26
 */

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ResultCodeEnum {

    SUCCESS(200, "ok"),

    INVALID_PARAM(400, "参数错误"),

    SYS_ERROR(500, "系统异常"),

    NO_LOGIN(401, "未登录"),

    NO_PERMISSION(403, "权限不足");

    private final Integer code;
    private final String message;

}
