package com.xzy.common.model;

import com.xzy.common.enums.ResultCodeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author xzy.xiao
 * @since 2024/12/17  18:26
 */

@Data
@Accessors(chain = true)
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1078230273720701952L;

    /**
     * 结果码
     */
    private Integer code;
    /**
     * 结果信息
     */
    private String message;
    /**
     * 结果数据
     */
    private T data;
    /**
     * 链路追踪id
     */
    private String traceId;

    private Result() {
    }

    public static <T> Result<T> sysError() {
        Result<T> result = new Result<>();
        result.setCode(ResultCodeEnum.SYS_ERROR.getCode());
        result.setMessage(ResultCodeEnum.SYS_ERROR.getMessage());
        return result;
    }

    public static <T> Result<T> paramError(String message) {
        Result<T> result = new Result<>();
        result.setCode(ResultCodeEnum.INVALID_PARAM.getCode());
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> error(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> error(String message) {
        Result<T> result = new Result<>();
        result.setCode(ResultCodeEnum.SYS_ERROR.getCode());
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        result.setData(null);
        return result;
    }

    public boolean isSuccess() {
        return Objects.equals(ResultCodeEnum.SUCCESS.getCode(), this.code);
    }

    public boolean isFailed() {
        return !isSuccess();
    }
}
