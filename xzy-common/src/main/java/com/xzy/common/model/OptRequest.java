package com.xzy.common.model;


import com.xzy.common.validator.VGroups;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @author xzy.xiao
 * @since 2024/12/17  18:26
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public abstract class OptRequest extends BaseRequest {

    private static final long serialVersionUID = -6218804135640336343L;

    /**
     * 操作用户
     */
    @NotBlank(message = "optUser为空", groups = VGroups.A.class)
    private String optUser;
}
