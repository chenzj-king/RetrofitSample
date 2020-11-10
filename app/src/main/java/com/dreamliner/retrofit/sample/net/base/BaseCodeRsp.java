package com.dreamliner.retrofit.sample.net.base;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class BaseCodeRsp {

    @SerializedName("code")
    private int code;

    @SerializedName("desc")
    private String desc;
}
