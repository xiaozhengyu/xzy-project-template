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
public class BizException extends RuntimeException {

    private static final long serialVersionUID = -3759546351029021044L;

    private final Integer code;

    public BizException(String message) {
        super(message);
        this.code = ResultCodeEnum.SYS_ERROR.getCode();
    }

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
