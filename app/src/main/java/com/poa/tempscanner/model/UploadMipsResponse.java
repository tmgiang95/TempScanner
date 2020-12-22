package com.poa.tempscanner.model;

import com.google.gson.annotations.SerializedName;

public class UploadMipsResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("msg")
    private String msg;

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setCode(int paramInt) {
        this.code = paramInt;
    }

    public void setMsg(String paramString) {
        this.msg = paramString;
    }
}
