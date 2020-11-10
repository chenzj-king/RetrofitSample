package com.dreamliner.retrofit.sample.entity.rsp;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Device {

    @SerializedName("id")
    private int id;
    @SerializedName("deviceCode")
    private String deviceCode;
    @SerializedName("deviceModel")
    private String deviceModel;
    @SerializedName("deviceName")
    private String deviceName;
    @SerializedName("masterDeviceId")
    private int masterDeviceId;
    @SerializedName("masterDeviceAccountId")
    private String masterDeviceAccountId;
    @SerializedName("masterDeviceCode")
    private String masterDeviceCode;
    @SerializedName("masterDeviceName")
    private String masterDeviceName;
    @SerializedName("buildingId")
    private int buildingId;
    @SerializedName("buildingName")
    private String buildingName;
    @SerializedName("hoodId")
    private int hoodId;
    @SerializedName("hoodName")
    private String hoodName;
    @SerializedName("hoodAddress")
    private Object hoodAddress;
    @SerializedName("officeId")
    private int officeId;
    @SerializedName("officeName")
    private String officeName;
    @SerializedName("unitName")
    private Object unitName;
    @SerializedName("storeName")
    private Object storeName;
    @SerializedName("address")
    private String address;
    @SerializedName("lng")
    private String lng;
    @SerializedName("lat")
    private String lat;
    @SerializedName("areaId")
    private String areaId;
    @SerializedName("cityId")
    private Object cityId;
    @SerializedName("provinceId")
    private Object provinceId;
    @SerializedName("ventQuantity")
    private int ventQuantity;
    @SerializedName("state")
    private String state;
    @SerializedName("pmYy")
    private Object pmYy;
    @SerializedName("pmKlw")
    private Object pmKlw;
    @SerializedName("pmFjw")
    private Object pmFjw;
    @SerializedName("onlineState")
    private String onlineState;
    @SerializedName("troubleState")
    private String troubleState;
    @SerializedName("cleanerAlarmState")
    private String cleanerAlarmState;
    @SerializedName("fanAlarmState")
    private String fanAlarmState;
    @SerializedName("reportTime")
    private Object reportTime;
    @SerializedName("serverTime")
    private Object serverTime;
    @SerializedName("createUser")
    private int createUser;
    @SerializedName("updateUser")
    private Object updateUser;
    @SerializedName("createTime")
    private String createTime;
    @SerializedName("updateTime")
    private String updateTime;
    @SerializedName("enterpriseId")
    private int enterpriseId;
    @SerializedName("departmentId")
    private int departmentId;
    @SerializedName("deptId")
    private int deptId;
    @SerializedName("ventQuantityStart")
    private Object ventQuantityStart;
    @SerializedName("ventQuantityEnd")
    private Object ventQuantityEnd;
}
