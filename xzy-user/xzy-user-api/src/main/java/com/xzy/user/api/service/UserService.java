package com.xzy.user.api.service;

import com.xzy.common.model.Result;
import com.xzy.user.api.model.req.UserDisableReq;
import com.xzy.user.api.model.req.UserEnableReq;
import com.xzy.user.api.model.req.UserGetReq;
import com.xzy.user.api.model.resp.UserGetResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 用户
 *
 * @author xzy.xiao
 * @since 2024/12/19  13:37
 */
@FeignClient(name = "${xzy.user.provider:xzy-user-provider}")
public interface UserService {

    @PostMapping("/user/get")
    Result<UserGetResp> get(@RequestBody UserGetReq req);

    @PostMapping("/user/enable")
    Result<Void> enable(@RequestBody UserEnableReq req);

    @PostMapping("/user/disable")
    Result<Void> disable(@RequestBody UserDisableReq req);
}
