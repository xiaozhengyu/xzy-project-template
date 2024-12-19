package com.xzy.trade.api.service;

import com.xzy.common.model.Result;
import com.xzy.trade.api.model.req.BalanceDecreaseReq;
import com.xzy.trade.api.model.req.BalanceIncreaseReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 余额
 *
 * @author xzy.xiao
 * @since 2024/12/19  13:42
 */
@FeignClient(name = "${xzy.trade.provider:xzy-trade-provider}")
public interface BalanceService {

    @PostMapping("/balance/increase")
    Result<Void> increase(@RequestBody BalanceIncreaseReq req);

    @PostMapping("/balance/decrease")
    Result<Void> decrease(@RequestBody BalanceDecreaseReq req);
}
