package com.zhugso.common.exception;


import com.zhugso.common.result.ResultCodeEnum;
import com.zhugso.common.result.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exception(Exception e){
        log.error("全局异常信息:{}", e.getMessage(), e);
        ResultCodeEnum codeEnum = ResultCodeEnum.getResultMessageEnum(e.getMessage());
        if(codeEnum != null)
            return ResultData.fail(codeEnum);
        return ResultData.fail(ResultCodeEnum.SERVER_ERROR.getCode(), e.getMessage());
    }

}
