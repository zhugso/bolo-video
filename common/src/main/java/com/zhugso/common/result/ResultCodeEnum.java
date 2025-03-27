package com.zhugso.common.result;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ResultCodeEnum {
//1xx：相关信息
//2xx：操作成功
//3xx：重定向
//4xx：客户端错误
//5xx：服务器错误
//            200 OK - [GET]：服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）。
//            201 CREATED - [POST/PUT/PATCH]：用户新建或修改数据成功。
//            202 Accepted - [*]：表示一个请求已经进入后台排队（异步任务）
//            204 NO CONTENT - [DELETE]：用户删除数据成功。
//            400 INVALID REQUEST - [POST/PUT/PATCH]：用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的。
//            401 Unauthorized - [*]：表示用户没有权限（令牌、用户名、密码错误）。
//            403 Forbidden - [*] 表示用户得到授权（与401错误相对），但是访问是被禁止的。
//            404 NOT FOUND - [*]：用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的。
//            406 Not Acceptable - [GET]：用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）。
//            410 Gone -[GET]：用户请求的资源被永久删除，且不会再得到的。
//            422 Unprocesable entity - [POST/PUT/PATCH] 当创建一个对象时，发生一个验证错误。
//            500 INTERNAL SERVER ERROR - [*]：服务器发生错误，用户将无法判断发出的请求是否成功。


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
