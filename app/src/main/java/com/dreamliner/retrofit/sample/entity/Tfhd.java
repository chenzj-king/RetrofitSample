package com.dreamliner.retrofit.sample.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author chenzj
 * @Title: Tfhd
 * @Description: 类的描述 -
 * @date 2016/11/11 9:51
 * @email admin@chenzhongjin.cn
 */
public class Tfhd implements Serializable {

    /**
     * glbm : 440100000400
     * bmmc : 广州市交警支队车辆管理所
     * hpzl : 02
     * hpzlStr : 小型汽车
     * subhd : AP10H0 ~ AP19H9
     * tfrq : 2017-04-12 09:00
     */

    @SerializedName("glbm")
    private String glbm;
    @SerializedName("bmmc")
    private String bmmc;
    @SerializedName("hpzl")
    private String hpzl;
    @SerializedName("hpzlStr")
    private String hpzlStr;
    @SerializedName("subhd")
    private String subhd;
    @SerializedName("tfrq")
    private String tfrq;

    public String getGlbm() {
        return glbm;
    }

    public void setGlbm(String glbm) {
        this.glbm = glbm;
    }

    public String getBmmc() {
        return bmmc;
    }

    public void setBmmc(String bmmc) {
        this.bmmc = bmmc;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public String getHpzlStr() {
        return hpzlStr;
    }

    public void setHpzlStr(String hpzlStr) {
        this.hpzlStr = hpzlStr;
    }

    public String getSubhd() {
        return subhd;
    }

    public void setSubhd(String subhd) {
        this.subhd = subhd;
    }

    public String getTfrq() {
        return tfrq;
    }

    public void setTfrq(String tfrq) {
        this.tfrq = tfrq;
    }

    @Override
    public String toString() {
        return "Tfhd{" +
                "glbm='" + glbm + '\'' +
                ", bmmc='" + bmmc + '\'' +
                ", hpzl='" + hpzl + '\'' +
                ", hpzlStr='" + hpzlStr + '\'' +
                ", subhd='" + subhd + '\'' +
                ", tfrq='" + tfrq + '\'' +
                '}';
    }
}
