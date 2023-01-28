package com.result;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.util.JsonUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author chenanlian
 */
@ApiModel()
public class ResponseResult<T> {

    @ApiModelProperty(value = "结果码", notes = "1:成功;0:失败;")
    private int code;

    @ApiModelProperty(value = "结果信息")
    private String msg;


    @ApiModelProperty(value = "数据")
    private T data;

    public ResponseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public boolean isSuccess() {
        return Objects.equals(this.code, SysResultCode.SUCCESS.getCode());
    }

    /**
     * 成功结果，data 为null
     *
     * @return
     */
    public static <T> ResponseResult<T> buildSuccess() {
        return new ResponseResult<T>(SysResultCode.SUCCESS.getCode(), SysResultCode.SUCCESS.getMsg());
    }

    public static <T> ResponseResult<T> success() {
        return new ResponseResult<T>(SysResultCode.SUCCESS.getCode(), SysResultCode.SUCCESS.getMsg());
    }

    public static <T> ResponseResult<T> success(T data) {
        return buildSuccess(data);
    }

    public static <T> ResponseResult<T> buildFail() {
        return new ResponseResult<T>(SysResultCode.FAIL.getCode(), SysResultCode.FAIL.getMsg());
    }

    public static <T> ResponseResult<T> buildFail(String msg) {
        return new ResponseResult<T>(SysResultCode.FAIL.getCode(), msg);
    }

    public static <T> ResponseResult<T> buildFail(int code, String msg) {
        return new ResponseResult<T>(code, msg);
    }

    public static <T> ResponseResult<List<T>> emptyList() {
        return ResponseResult.success(Collections.emptyList());
    }

    public static <T> ResponseResult<IPage<T>> emptyPage() {
        IPage<T> page = new Page<>();
        page.setSize(0);
        page.setTotal(0);
        page.setCurrent(0);
        page.setRecords(Collections.emptyList());
        page.setPages(1);
        return buildSuccess(page);
    }

    public static <T> ResponseResult<IPage<T>> emptyPage(int total) {
        IPage<T> page = new Page<>();
        page.setSize(0);
        page.setTotal(total);
        page.setCurrent(0);
        page.setRecords(Collections.emptyList());
        page.setPages(1);
        return buildSuccess(page);
    }

    public static <T> ResponseResult<EPage<T>> emptyEPage() {
        return buildSuccess(new EPage<T>(0, 1, 0, 0, Collections.emptyList()));
    }

    /**
     * 成功结果
     *
     * @param data data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> buildSuccess(T data) {
        return new ResponseResult<T>(SysResultCode.SUCCESS.getCode(), SysResultCode.SUCCESS.getMsg(), data);
    }

    public static <T> ResponseResult<T> buildSuccess(String msg, T data) {
        return new ResponseResult<T>(SysResultCode.SUCCESS.getCode(), msg, data);
    }


    /**
     * 根据系统结果枚举生成结果，data为null
     *
     * @return
     */
    public static <T> ResponseResult<T> build(ResultCode resultCode) {

        return new ResponseResult<T>(resultCode.getCode(), resultCode.getMsg());
    }


    public static <T> ResponseResult<T> build(ResultCode resultCode, T data) {
        return new ResponseResult<T>(resultCode.getCode(), resultCode.getMsg(), data);
    }


    public static <T> ResponseResult<T> build(int code, String msg, T data) {
        return new ResponseResult<T>(code, msg, data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JsonUtil.entity2Json(this);
    }
}
