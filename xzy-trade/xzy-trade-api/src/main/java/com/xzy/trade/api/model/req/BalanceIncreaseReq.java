package com.xzy.trade.api.model.req;

import com.xzy.common.model.OptRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author xzy.xiao
 * @since 2024/12/19  14:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
public class BalanceIncreaseReq extends OptRequest {

    private static final long serialVersionUID = -9159933607260916679L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 金额（单位：分）
     */
    private Long amount;
}

