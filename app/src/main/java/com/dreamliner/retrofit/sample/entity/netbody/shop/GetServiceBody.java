package com.dreamliner.retrofit.sample.entity.netbody.shop;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chenzj on 2017/4/11.
 */

public class GetServiceBody {

    @SerializedName("pageSize")
    private String pageSize = "10";
    @SerializedName("pageIndex")
    private String pageIndex;
    @SerializedName("longitude")
    private String longitude = "113.263292";
    @SerializedName("latitude")
    private String latitude = "23.382048";

    public GetServiceBody(String pageIndex) {
        this.pageIndex = pageIndex;
    }
}
