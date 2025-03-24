package com.zhugso.common.exception;

import com.zhugso.common.result.ResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class BoloException extends RuntimeException{

    private String code;
    private String message;

    public BoloException(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }
}
