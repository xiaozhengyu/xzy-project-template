package com.xzy.user.provider.service;

import com.xzy.common.model.Result;
import com.xzy.common.validator.HibernateValidatorHelper;
import com.xzy.user.api.model.req.UserDisableReq;
import com.xzy.user.api.model.req.UserEnableReq;
import com.xzy.user.api.model.req.UserGetReq;
import com.xzy.user.api.model.resp.UserGetResp;
import com.xzy.user.api.service.UserService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xzy.xiao
 * @since 2024/12/19  14:13
 */
@RestController
public class UserServiceImpl implements UserService {

    @Override
    public Result<UserGetResp> get(UserGetReq req) {
        HibernateValidatorHelper.verify(req);

        // TODO：[xzy]

        return Result.success();
    }

    @Override
    public Result<Void> enable(UserEnableReq req) {
        HibernateValidatorHelper.verify(req);

        // TODO：[xzy]

        return Result.success();
    }

    @Override
    public Result<Void> disable(UserDisableReq req) {
        HibernateValidatorHelper.verify(req);

        // TODO：[xzy]

        return Result.success();
    }

}
