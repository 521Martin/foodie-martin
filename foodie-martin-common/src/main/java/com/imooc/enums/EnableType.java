package com.imooc.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;

/**
 * 地址标准类型
 * Created by renfeng on 2020/8/5.
 */
@Getter
public enum EnableType implements IEnum<Integer> {

    NO_VALID(0, "无效"),

    VALID(1, "有效"),
    ;

    @EnumValue
    private Integer code;

    private String msg;

    EnableType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getValue() {
        return this.code;
    }

    public static EnableType valueOf(Integer code) {
        for (EnableType type : EnableType.values()) {
            if(type.getCode().equals(code)) {
                return type;
            }
        }
        throw new RuntimeException("未知的状态");
    }
}
