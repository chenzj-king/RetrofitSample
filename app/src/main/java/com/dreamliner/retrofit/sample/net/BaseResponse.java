package com.dreamliner.retrofit.sample.net;

import com.google.gson.annotations.SerializedName;

/**
 * @author chenzj
 * @Title: BaseResponse
 * @Description: 类的描述 -
 * @date 2017/3/1 16:18
 * @email admin@chenzhongjin.cn
 */
public class BaseResponse<T> {

    @SerializedName("err")
    private int mErrorCode = -1;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("data")
    private T data;

    @SerializedName("total")
    private int total;

    public int getErrorCode() {
        return mErrorCode;
    }

    public void setErrorCode(int errorCode) {
        mErrorCode = errorCode;
    }

    public String getMsg() {
        return mMsg;
    }

    public void setMsg(String msg) {
        mMsg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "mErrorCode=" + mErrorCode +
                ", mMsg='" + mMsg + '\'' +
                ", data=" + data +
                ", total=" + total +
                '}';
    }
}
