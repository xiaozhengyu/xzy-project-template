package com.xzy.common.exception;

import com.xzy.common.enums.ResultCodeEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * @author xzy.xiao
 * @since 2024/12/17  18:26
 */

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SysException extends RuntimeException {

    private static final long serialVersionUID = 7394235346622909521L;

    final Integer code;

    public SysException(String message) {
        super(message);
        this.code = ResultCodeEnum.SYS_ERROR.getCode();
    }
}
