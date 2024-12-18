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
public enum DeletedEnum {

    /**
     * 未删除
     */
    NORMAL(0),
    /**
     * 已删除
     */
    DELETED(1);

    private final Integer code;

    public static DeletedEnum parse(Integer code) {
        return Stream.of(DeletedEnum.values())
                .filter(o -> o.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }
}
