package com.zhugso.common.result;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class ResultData<T> {

    private String code;
    private String message;
    private T data;
    private long timestamp;

    private ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    private static <T> ResultData<T> build(T data){
        ResultData<T> resultData = new ResultData<>();
        if(data != null){
            resultData.setData(data);
        }
        return resultData;
    }

    private static <T> ResultData<T> build(T data, String code, String message){
        ResultData<T> resultData = build(data);
        resultData.setCode(code);
        resultData.setMessage(message);
        return resultData;
    }

    public static <T> ResultData<T> success(T data){
        return build(data, ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage());
    }

    public static <T> ResultData<T> fail(ResultCodeEnum resultCodeEnum){
        return build(null, resultCodeEnum.getCode(), resultCodeEnum.getMessage());
    }
    public static <T> ResultData<T> fail(String code, String message){
        return build(null, code, message);
    }
}
