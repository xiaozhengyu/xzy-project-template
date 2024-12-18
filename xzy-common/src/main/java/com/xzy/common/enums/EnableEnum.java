package com.xzy.common.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author xzy.xiao
 * @since 2024/12/17  18:26
 */

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum EnableEnum {

    /**
     * 禁用
     */
    NO(0),
    /**
     * 启用
     */
    YES(1);

    private final Integer code;

    public static EnableEnum parse(Integer code) {
        return Stream.of(EnableEnum.values())
                .filter(o -> o.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }
}
