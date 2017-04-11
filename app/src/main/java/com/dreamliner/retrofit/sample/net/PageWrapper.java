package com.dreamliner.retrofit.sample.net;

import com.google.gson.annotations.SerializedName;

/**
 * @author chenzj
 * @Title: BaseResponse
 * @Description: 类的描述 -
 * @date 2017/3/1 16:18
 * @email admin@chenzhongjin.cn
 */
public class PageWrapper<T> {

    @SerializedName("data")
    private T data;
    private int total;

    public PageWrapper(T data, int total) {
        this.data = data;
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public int getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "PageWrapper{" +
                "data=" + data +
                ", total=" + total +
                '}';
    }
}
