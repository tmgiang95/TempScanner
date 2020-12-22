package com.poa.tempscanner.mips;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MipsApiService {
    public static final String BASE_PORT = "8080/";

    @FormUrlEncoded
    @POST("getConfig")
    Call<BaseResponse<String>> getConfig(@Field("pass") String paramString);

    @FormUrlEncoded
    @POST("getTempAndMaskSetting")
    Call<BaseResponse<String>> getTempConfig(@Field("pass") String paramString);

    @FormUrlEncoded
    @POST("setConfig")
    Single<BaseResponse<String>> setConfig(@Field("pass") String paramString1, @Field("config") String paramString2);

    @FormUrlEncoded
    @POST("setIdentifyCallback")
    Single<BaseResponse<String>> setIdentifyCallback(@Field("pass") String paramString1, @Field("callbackUrl") String paramString2);

    @FormUrlEncoded
    @POST("setPassWord")
    Single<BaseResponse<String>> setPassword(@Field("oldPass") String paramString1, @Field("newPass") String paramString2);
}
