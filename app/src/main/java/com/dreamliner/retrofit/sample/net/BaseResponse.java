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

    @SerializedName("message")
    private String message;
    @SerializedName("code")
    private int code;
    @SerializedName("data")
    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
