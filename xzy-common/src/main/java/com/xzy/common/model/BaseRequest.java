package com.xzy.common.model;


import com.xzy.common.exception.ParamException;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xzy.xiao
 * @since 2024/12/17  18:26
 */
@Data
public abstract class BaseRequest implements Serializable {

    private static final long serialVersionUID = -4735718388890829887L;

    public void verify() throws ParamException {
        // 子类自定义校验逻辑
    }
}
