package com.xzy.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;

/**
 * @author xzy.xiao
 * @since 2024/12/17  18:26
 */

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public abstract class PageRequest extends BaseRequest {
    private static final long serialVersionUID = -8170758585501761750L;

    /**
     * 当前页码
     */
    private long current = 1L;
    /**
     * 每页显示条数
     */
    @Max(value = 10000, message = "pageSize不超过{value}")
    private long pageSize = 10L;
    /**
     * 指定排序字段
     */
    private String sortField;
    /**
     * 排序规则：正序=asc 倒序=desc
     */
    private String sortOrder;
}
