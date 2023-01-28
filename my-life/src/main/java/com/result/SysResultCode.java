package com.result;

public enum SysResultCode implements ResultCode {


    SUCCESS(1, "成功"),
    FAIL(0, "失败"),


    THIRD_NETWORK_ERROR(10000, "第三方网络错误，请稍后重试"),

    TOKEN_EXPIRED(10001, "token has expired"),

    TOKEN_EMPTY(10002, "token is empty"),

    TENANCY_IS_EMPTY(10003, "租户id为空"),

    TENANCY_NOT_EXIST(10003, "找不到租户信息"),

    TENANCY_USER_PERMISSION(10003, "没有权限"),

    PLATFORM_CANT_DELETE(10003, "平台租户不能删除"),

    RESOURCE_DELETE_FAIL(20001, "删除失败"),


    QC_FAIL(10007,"获取腾讯云信息失败"),

    TENANCY_NOT_NORMAL(30001, "商户不存在或被禁用"),




    ;

    private int code;

    private String msg;


    SysResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }


    @Override
    public String getMsg() {
        return msg;
    }

}
