package com.xzy.user.api.model.req;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author xzy.xiao
 * @since 2024/12/19  14:13
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class UserGetReq implements Serializable {

    private static final long serialVersionUID = -9211056627347521137L;

    private Long id;
    private String username;
    private String phone;
}
