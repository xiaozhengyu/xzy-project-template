package com.xzy.user.api.model.req;

import com.xzy.common.model.OptRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @author xzy.xiao
 * @since 2024/12/19  14:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class UserEnableReq extends OptRequest {

    private static final long serialVersionUID = -77523532049603699L;

    @NotNull(message = "id不能为空")
    private Long id;
}
