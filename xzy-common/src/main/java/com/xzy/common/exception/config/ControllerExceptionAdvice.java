package com.xzy.common.exception.config;

import com.xzy.common.enums.ResultCodeEnum;
import com.xzy.common.exception.BizException;
import com.xzy.common.exception.ParamException;
import com.xzy.common.exception.SysException;
import com.xzy.common.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 *
 * @author xzy.xiao
 * @since 2024/12/17  18:26
 */

@Slf4j
@ControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public Result<?> handleMethodNotSupportedException(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        log.error("{} request \"{}\" occur HttpRequestMethodNotSupportedException, parameter = {}, message = {}", request.getMethod(), request.getRequestURI(),
                parseRequestParameter(request), exception.getMessage());
        return Result.error(ResultCodeEnum.SYS_ERROR.getCode(), "请求异常");
    }

    @ExceptionHandler({BindException.class, MethodArgumentTypeMismatchException.class, MethodArgumentNotValidException.class})
    @ResponseBody
    public Result<?> handleBindException(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        log.error("{} request \"{}\" occur ParameterNotValidException, parameter = {}, message = {}", request.getMethod(), request.getRequestURI(),
                parseRequestParameter(request), exception.getMessage());
        BindingResult bindingResult = null;
        if (exception instanceof BindException) {
            bindingResult = ((BindException) exception).getBindingResult();
        }

        StringJoiner msg = new StringJoiner(" ");
        List<FieldError> fieldErrorList = bindingResult == null ? Collections.emptyList() : bindingResult.getFieldErrors();
        fieldErrorList.forEach(fieldError -> {
            if (fieldError.isBindingFailure()) {
                StringBuilder str = new StringBuilder();
                msg.add(str.append("参数:[").append(fieldError.getObjectName()).append(".").append(fieldError.getField()).append("]的传入值:[")
                        .append(fieldError.getRejectedValue()).append("]与预期的字段类型不匹配."));
            } else {
                msg.add(fieldError.getDefaultMessage());
            }
        });

        if (StringUtils.isNotEmpty(msg.toString())) {
            // 自定义状态返回
            return Result.paramError(msg.toString());
        }
        return Result.paramError("参数错误");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Result<?> handleConstraintViolationException(HttpServletRequest request, HttpServletResponse response, ConstraintViolationException exception) {
        log.error("{} request \"{}\" occur ConstraintViolationException, parameter = {}, message = {}", request.getMethod(), request.getRequestURI(),
                parseRequestParameter(request), exception.getMessage());
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        String message = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
        return Result.paramError(message);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public Result<?> handleMethodArgumentTypeMismatchException(HttpServletRequest request, MissingServletRequestParameterException exception) {
        log.error("{} request \"{}\" occur MissingServletRequestParameterException, parameter = {}, message = {}", request.getMethod(), request.getRequestURI(),
                parseRequestParameter(request), exception.getMessage());

        log.error("handle methodArgumentTypeMismatchException：{}, uri：{}", exception.getMessage(), request.getRequestURI());
        return Result.paramError("缺少参数");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Result<?> handleHttpMessageNotReadableException(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        log.error("{} request \"{}\" occur HttpMessageNotReadableException, parameter = {}, message = {}", request.getMethod(), request.getRequestURI(),
                parseRequestParameter(request), exception.getMessage());
        return Result.paramError("参数格式错误");
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public Result<?> handleMaxUploadSizeExceededException(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        log.error("{} request \"{}\" occur MaxUploadSizeExceededException, message = {}", request.getMethod(), request.getRequestURI(), exception.getMessage());
        return Result.paramError("上传文件过大");
    }

    @ExceptionHandler({IllegalArgumentException.class, ParamException.class})
    @ResponseBody
    public Result<?> handleParamException(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        log.error("{} request \"{}\" occur ParamException, parameter = {}, message = {}", request.getMethod(), request.getRequestURI(),
                parseRequestParameter(request), exception.getMessage());
        return Result.paramError(exception.getMessage());
    }

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public Result<?> handleBizException(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        log.error("{} request \"{}\" occur BizException, parameter = {}, message = {}", request.getMethod(), request.getRequestURI(),
                parseRequestParameter(request), exception.getMessage());
        BizException bizException = (BizException) exception;
        return Result.error(bizException.getCode(), bizException.getMessage());
    }

    @ExceptionHandler({SysException.class, Exception.class})
    @ResponseBody
    public Result<?> handleException(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        log.error("{} request \"{}\" occur SysException, parameter = {}, message = {}", request.getMethod(), request.getRequestURI(),
                parseRequestParameter(request), exception.getMessage(), exception);
        return Result.sysError();
    }

    public Object parseRequestParameter(HttpServletRequest request) {
        // 获取请求体内容
        if (request instanceof ContentCachingRequestWrapper) {
            ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
            String requestBody = new String(cachingRequest.getContentAsByteArray(), StandardCharsets.UTF_8);
            // 去掉空格、回车和换行符
            requestBody = requestBody.replaceAll("\\s+", "").replaceAll("[\\r\\n]", "");
            return requestBody;
        } else {
            Map<String, String> params = new HashMap<>();
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                params.put(paramName, request.getParameter(paramName));
            }
            return params;
        }
    }
}
