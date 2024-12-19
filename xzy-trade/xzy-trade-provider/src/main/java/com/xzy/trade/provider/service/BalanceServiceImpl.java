package com.xzy.trade.provider.service;

import com.xzy.common.model.Result;
import com.xzy.common.validator.HibernateValidatorHelper;
import com.xzy.trade.api.model.req.BalanceDecreaseReq;
import com.xzy.trade.api.model.req.BalanceIncreaseReq;
import com.xzy.trade.api.service.BalanceService;
import com.xzy.user.api.service.UserService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xzy.xiao
 * @since 2024/12/19  13:51
 */
@RestController
public class BalanceServiceImpl implements BalanceService {

    @Resource
    private UserService userService;

    @Override
    public Result<Void> increase(BalanceIncreaseReq req) {
        HibernateValidatorHelper.verify(req);

        // TODO：[xzy]

        return null;
    }

    @Override
    public Result<Void> decrease(BalanceDecreaseReq req) {
        HibernateValidatorHelper.verify(req);

        // TODO：[xzy]

        return null;
    }
}
