package com.dreamliner.retrofit.sample.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TfhdResult {

    @SerializedName("hdms")
    private String hdms;
    @SerializedName("list")
    private ListBean list;

    public String getHdms() {
        return hdms;
    }

    public void setHdms(String hdms) {
        this.hdms = hdms;
    }

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public static class ListBean {
        @SerializedName("content")
        private List<Tfhd> content;

        public List<Tfhd> getContent() {
            return content;
        }

        public void setContent(List<Tfhd> content) {
            this.content = content;
        }
    }
}
