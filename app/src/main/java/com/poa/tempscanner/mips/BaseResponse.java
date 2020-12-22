package com.poa.tempscanner.mips;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {
    public static final String MESSAGE_UNAUTHORIZED = "unauthorized";

    public static final String TYPE_ERROR = "error";

    public static final String TYPE_SUCCESS = "success";

    @SerializedName("data")
    private T data;

    @SerializedName("result")
    private String result;

    @SerializedName("success")
    private boolean success;

    public BaseResponse(T paramT) {
        this.data = paramT;
    }

    public T getData() {
        return this.data;
    }

    public String getResult() {
        return this.result;
    }

    public void setData(T paramT) {
        this.data = paramT;
    }

    public void setResult(String paramString) {
        this.result = paramString;
    }

    public void setSuccess(boolean paramBoolean) {
        this.success = paramBoolean;
    }
}