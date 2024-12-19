package com.xzy.user.api.model.resp;

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
public class UserGetResp implements Serializable {

    private static final long serialVersionUID = -8093995133158351723L;

    private Long id;
    private String username;
    private String phone;
    private String email;
}
