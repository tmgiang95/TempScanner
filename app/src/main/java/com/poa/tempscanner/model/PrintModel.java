package com.poa.tempscanner.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import kotlinx.android.parcel.IgnoredOnParcel;

public class PrintModel {

    @SerializedName("mac")
    @Expose
    private String mac;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("checkTime")
    @Expose
    private Long checkTime;
    @SerializedName("timeZone")
    @Expose
    private String timeZone;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("cardNo")
    @Expose
    private String cardNo;
    @SerializedName("idCardNo")
    @Expose
    private String idCardNo;
    @SerializedName("temperature")
    @Expose
    private String temperature;
    @SerializedName("mask")
    @Expose
    private Integer mask;
    @SerializedName("passType")
    @Expose
    private Boolean passType;
    @SerializedName("totalFaceNumber")
    @Expose
    private Integer totalFaceNumber;
    @SerializedName("totalPeopleNumber")
    @Expose
    private Integer totalPeopleNumber;
    @SerializedName("extra")
    @Expose
    private String extra;
    @SerializedName("groupId")
    @Expose
    private Integer groupId;
    @SerializedName("checkPic")
    @Expose
    private String checkPic;

    private Bitmap bitmap;

    public PrintModel() {
    }

    public PrintModel(MipsData mipsData) {

        this.checkPic = mipsData.getCheckPic();
        this.mac = mipsData.getMac();
        this.userId = mipsData.getUserId();
        this.checkTime = mipsData.getCheckTime();
        this.name = mipsData.getName();
        this.type = mipsData.getType();
        this.cardNo = mipsData.getCardNo();
        this.temperature = mipsData.getTemperature();
        this.mask = mipsData.getMask();

    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Long checkTime) {
        this.checkTime = checkTime;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public Integer getMask() {
        return mask;
    }

    public void setMask(Integer mask) {
        this.mask = mask;
    }

    public Boolean getPassType() {
        return passType;
    }

    public void setPassType(Boolean passType) {
        this.passType = passType;
    }

    public Integer getTotalFaceNumber() {
        return totalFaceNumber;
    }

    public void setTotalFaceNumber(Integer totalFaceNumber) {
        this.totalFaceNumber = totalFaceNumber;
    }

    public Integer getTotalPeopleNumber() {
        return totalPeopleNumber;
    }

    public void setTotalPeopleNumber(Integer totalPeopleNumber) {
        this.totalPeopleNumber = totalPeopleNumber;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getCheckPic() {
        return checkPic;
    }

    public void setCheckPic(String checkPic) {
        this.checkPic = checkPic;
    }

    public Bitmap getBitmap() {
        if (checkPic != null && !checkPic.isEmpty()){
            return textToBitmap(checkPic);
        }
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public static Bitmap textToBitmap(String str){
        byte[] decodedString = Base64.decode(str, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return bitmap;
    }
}
