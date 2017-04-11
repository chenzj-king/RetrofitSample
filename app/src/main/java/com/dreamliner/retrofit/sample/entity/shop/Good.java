package com.dreamliner.retrofit.sample.entity.shop;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenzj
 * @Title: Good
 * @Description: 类的描述 -
 * @date 2016/11/11 9:51
 * @email admin@chenzhongjin.cn
 */
public class Good implements Serializable {

    @SerializedName("goodId")
    private int goodId;
    @SerializedName("goodImage")
    private String goodImage;
    @SerializedName("goodImageSmall")
    private String goodImageSmall;
    @SerializedName("introduction")
    private String introduction;
    @SerializedName("isGoodServiceTime")
    private int isGoodServiceTime;
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private double price;
    @SerializedName("serviceIntroduction")
    private String serviceIntroduction;
    @SerializedName("serviceNotice")
    private String serviceNotice;
    @SerializedName("storeId")
    private int storeId;
    @SerializedName("storeMobile")
    private String storeMobile;
    @SerializedName("storeName")
    private String storeName;
    @SerializedName("storeTel")
    private String storeTel;
    @SerializedName("visitCount")
    private int visitCount;

    //3.2后新增的字段
    @SerializedName("goodTypeCode")
    private String goodTypeCode;
    @SerializedName("goodTypeName")
    private String goodTypeName;
    @SerializedName("imageList")
    private List<GoodImage> imageList;
    @SerializedName("storeAbbreviation")
    private String storeAbbreviation;
    @SerializedName("storeRole")
    private int storeRole;
    @SerializedName("distance")
    private int distance;
    @SerializedName("instructions")
    private String instructions;
    @SerializedName("instructionsTitle")
    private String instructionsTitle;

    //4.4增加的保险业务的跳转
    @SerializedName("goodBuyUrl")
    private String goodBuyUrl;

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public String getGoodImage() {
        return goodImage;
    }

    public void setGoodImage(String goodImage) {
        this.goodImage = goodImage;
    }

    public String getGoodImageSmall() {
        return goodImageSmall;
    }

    public void setGoodImageSmall(String goodImageSmall) {
        this.goodImageSmall = goodImageSmall;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getIsGoodServiceTime() {
        return isGoodServiceTime;
    }

    public void setIsGoodServiceTime(int isGoodServiceTime) {
        this.isGoodServiceTime = isGoodServiceTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getServiceIntroduction() {
        return serviceIntroduction;
    }

    public void setServiceIntroduction(String serviceIntroduction) {
        this.serviceIntroduction = serviceIntroduction;
    }

    public String getServiceNotice() {
        return serviceNotice;
    }

    public void setServiceNotice(String serviceNotice) {
        this.serviceNotice = serviceNotice;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreMobile() {
        return storeMobile;
    }

    public void setStoreMobile(String storeMobile) {
        this.storeMobile = storeMobile;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreTel() {
        return storeTel;
    }

    public void setStoreTel(String storeTel) {
        this.storeTel = storeTel;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public String getGoodTypeCode() {
        return goodTypeCode;
    }

    public void setGoodTypeCode(String goodTypeCode) {
        this.goodTypeCode = goodTypeCode;
    }

    public String getGoodTypeName() {
        return goodTypeName;
    }

    public void setGoodTypeName(String goodTypeName) {
        this.goodTypeName = goodTypeName;
    }

    public List<GoodImage> getImageList() {
        return imageList;
    }

    public void setImageList(List<GoodImage> imageList) {
        this.imageList = imageList;
    }

    public String getStoreAbbreviation() {
        return storeAbbreviation;
    }

    public void setStoreAbbreviation(String storeAbbreviation) {
        this.storeAbbreviation = storeAbbreviation;
    }

    public int getStoreRole() {
        return storeRole;
    }

    public void setStoreRole(int storeRole) {
        this.storeRole = storeRole;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getInstructionsTitle() {
        return instructionsTitle;
    }

    public void setInstructionsTitle(String instructionsTitle) {
        this.instructionsTitle = instructionsTitle;
    }

    public String getGoodBuyUrl() {
        return goodBuyUrl;
    }

    public void setGoodBuyUrl(String goodBuyUrl) {
        this.goodBuyUrl = goodBuyUrl;
    }

    @Override
    public String toString() {
        return "Good{" +
                "goodId=" + goodId +
                ", goodImage='" + goodImage + '\'' +
                ", goodImageSmall='" + goodImageSmall + '\'' +
                ", introduction='" + introduction + '\'' +
                ", isGoodServiceTime=" + isGoodServiceTime +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", serviceIntroduction='" + serviceIntroduction + '\'' +
                ", serviceNotice='" + serviceNotice + '\'' +
                ", storeId=" + storeId +
                ", storeMobile='" + storeMobile + '\'' +
                ", storeName='" + storeName + '\'' +
                ", storeTel='" + storeTel + '\'' +
                ", visitCount=" + visitCount +
                ", goodTypeCode='" + goodTypeCode + '\'' +
                ", goodTypeName='" + goodTypeName + '\'' +
                ", imageList=" + imageList +
                ", storeAbbreviation='" + storeAbbreviation + '\'' +
                ", storeRole=" + storeRole +
                ", distance=" + distance +
                ", instructions='" + instructions + '\'' +
                ", instructionsTitle='" + instructionsTitle + '\'' +
                ", goodBuyUrl='" + goodBuyUrl + '\'' +
                '}';
    }
}
