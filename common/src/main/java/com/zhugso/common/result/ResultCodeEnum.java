package com.zhugso.common.result;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ResultCodeEnum {
//    /*操作失败*/
//    RC999("999", "操作XX失败"),
//
    /*操作成功*/
    SUCCESS("200", "成功"),
    FAIL("201", "失败"),

    ACCOUNT_EXIST_ERROR("301", "用户已存在"),
    ACCOUNT_NOT_EXIST_ERROR("302", "用户不存在"),
    ACCOUNT_ERROR("303", "用户名或密码错误"),

    LOGIN_AUTH("304", "未登录"),

    CAPTCHA_CODE_ERROR("308","验证码错误"),
    CAPTCHA_CODE_NOT_FOUNT("309","未输入验证码"),


    SERVER_ERROR("501", "系统异常，请稍后重试"),


    TOKEN_INVALID("601", "token无效"),
    ;

    private final String code;
    private final String message;

    ResultCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResultCodeEnum getResultCodeEnumV1(String code) {
        for (ResultCodeEnum element : ResultCodeEnum.values()) {
            if(element.getCode().equalsIgnoreCase(code)){
                return element;
            }
        }
        return null;
    }

    public static ResultCodeEnum getResultCodeEnumV2(String code){
        return Arrays.stream(ResultCodeEnum.values()).filter(x -> x.getCode().equalsIgnoreCase(code)).findFirst().orElse(null);
    }

//    public static void main(String[] args) {
//        System.out.println(ReturnCodeEnum.getReturnCodeEnumV1("200"));
//        System.out.println(ReturnCodeEnum.getReturnCodeEnumV1("200").getCode());
//        System.out.println(ReturnCodeEnum.getReturnCodeEnumV1("200").getMessage());
//
//        System.out.println(ReturnCodeEnum.getReturnCodeEnumV1("404"));
//        System.out.println(ReturnCodeEnum.getReturnCodeEnumV1("404").getCode());
//        System.out.println(ReturnCodeEnum.getReturnCodeEnumV1("404").getMessage());
//    }

}
