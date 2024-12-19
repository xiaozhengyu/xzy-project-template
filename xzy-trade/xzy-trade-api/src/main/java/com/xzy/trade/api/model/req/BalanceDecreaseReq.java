package com.xzy.trade.api.model.req;

import com.xzy.common.model.OptRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author xzy.xiao
 * @since 2024/12/19  14:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
public class BalanceDecreaseReq extends OptRequest {

    private static final long serialVersionUID = -5410687084469519560L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 金额（单位：分）
     */
    private Long amount;
}
