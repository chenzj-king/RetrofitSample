package com.dreamliner.retrofit.sample.entity.netbody;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chenzj on 2017/4/11.
 */

public class GetTfhdBody {

    @SerializedName("page")
    private int page = 1;
    @SerializedName("size")
    private int size = 1000;
    @SerializedName("glbm")
    private String glbm = "440100000400";
    @SerializedName("hpzl")
    private String hpzl = "02";
    @SerializedName("type")
    private String type = "0";
    @SerializedName("startTime")
    private String startTime = "2018-01-01";
    @SerializedName("endTime")
    private String endTime = "2018-12-31";

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getGlbm() {
        return glbm;
    }

    public void setGlbm(String glbm) {
        this.glbm = glbm;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
