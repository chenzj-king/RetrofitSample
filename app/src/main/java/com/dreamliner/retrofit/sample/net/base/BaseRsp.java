package com.dreamliner.retrofit.sample.net.base;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenzj
 * @Title: BaseResponse
 * @Description: 类的描述 -
 * @date 2017/3/1 16:18
 * @email admin@chenzhongjin.cn
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseRsp<T> extends BaseCodeRsp {

    @SerializedName("data")
    private T data;

    @SerializedName("datas")
    private T dataList;
}
