package com.dreamliner.retrofit.sample.entity.rsp;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Login {

    @SerializedName("id")
    private int id;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private Object password;
    @SerializedName("realName")
    private String realName;
    @SerializedName("state")
    private String state;
    @SerializedName("email")
    private String email;
    @SerializedName("phone")
    private String phone;
    @SerializedName("areaId")
    private Object areaId;
    @SerializedName("saltCode")
    private Object saltCode;
    @SerializedName("authType")
    private String authType;
    @SerializedName("departmentId")
    private int departmentId;
    @SerializedName("departmentName")
    private Object departmentName;
    @SerializedName("positionTitle")
    private Object positionTitle;
    @SerializedName("enforcementCardNumber")
    private Object enforcementCardNumber;
    @SerializedName("deptId")
    private int deptId;
    @SerializedName("createTime")
    private String createTime;
    @SerializedName("createUser")
    private int createUser;
    @SerializedName("updateUser")
    private int updateUser;
    @SerializedName("updateTime")
    private String updateTime;
    @SerializedName("deleteTime")
    private Object deleteTime;
    @SerializedName("deleteUser")
    private Object deleteUser;
    @SerializedName("role")
    private Role role;

    @Data
    public static class Role {
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("remark")
        private String remark;
        @SerializedName("sysDeptId")
        private int sysDeptId;
        @SerializedName("sysDeptName")
        private String sysDeptName;
        @SerializedName("createTime")
        private String createTime;
        @SerializedName("createUser")
        private int createUser;
        @SerializedName("updateTime")
        private String updateTime;
        @SerializedName("updateUser")
        private int updateUser;
        @SerializedName("deleteTime")
        private Object deleteTime;
        @SerializedName("deleteUser")
        private Object deleteUser;
        @SerializedName("state")
        private String state;
        @SerializedName("authType")
        private String authType;
    }
}
