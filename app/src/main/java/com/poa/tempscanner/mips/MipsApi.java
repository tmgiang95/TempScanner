package com.poa.tempscanner.mips;


import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;
//import com.bugfender.sdk.Bugfender;
import com.poa.tempscanner.App;
import com.poa.tempscanner.utils.GsonUtil;
import com.poa.tempscanner.utils.Utils;
//import com.lamasatech.visipoint.sharedhelpers.util.UtilsKt;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;

public class MipsApi extends RetrofitApi {
    private MipsApiService service;

    public MipsApi(Context paramContext) {
        this.service = (MipsApiService)RetrofitApi.getRetrofit(paramContext, Utils.getCompleteUrl(Utils.getIPAddress(true), "8080/")).create(MipsApiService.class);
    }

    public void getConfig(String paramString) {
        Call<BaseResponse<String>> call = this.service.getConfig(paramString);
        try {
            Response response = call.execute();
            if (response != null) {
                BaseResponse<String> baseResponse = (BaseResponse)response.body();
                if (baseResponse != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(baseResponse.getResult());
                    stringBuilder.append(" ");
                    stringBuilder.append(baseResponse.getData());
//                    Bugfender.sendUserFeedback("getConfig", stringBuilder.toString());
                    ConfigParameters configParameters = (ConfigParameters)GsonUtil.INSTANCE.getInstance().fromJson(baseResponse.getData(), ConfigParameters.class);
                    SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences((Context)App.getAppContext()).edit();
                    editor.putString("deviceId", configParameters.getDeviceId());
                    String str2 = configParameters.getCompanyName();
//                    String str1 = str2;
//                    if (".equalsIgnoreCase(str2))
//                    str1 = null;
                    editor.putString("companyName", str2);
                    editor.apply();
                    return;
                }
            }
        } catch (IOException iOException) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ERROR: ");
            stringBuilder.append(iOException.getMessage());
//            Bugfender.sendUserFeedback("getConfig", stringBuilder.toString());
        }
    }

    public void getTempConfig(String paramString) {
        Call<BaseResponse<String>> call = this.service.getTempConfig(paramString);
        try {
            Response response = call.execute();
            if (response != null) {
                BaseResponse<String> baseResponse = (BaseResponse)response.body();
                if (baseResponse != null) {
                    TemperatureConfig temperatureConfig = (TemperatureConfig)GsonUtil.INSTANCE.getInstance().fromJson(baseResponse.getData(), TemperatureConfig.class);
                    SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences((Context)App.getAppContext()).edit();
                    editor.putInt("isBodyTempAlarm", temperatureConfig.isBodyTempAlarm());
                    editor.putInt("isWearingMask", temperatureConfig.isWearingMask().intValue());
                    editor.putString("standardBodyTemp", temperatureConfig.getStandardBodyTemp());
                    editor.apply();
                }
            }
            return;
        } catch (IOException iOException) {
            return;
        }
    }

    public void setConfig(String paramString, ConfigParameters paramConfigParameters) {
        this.service.setConfig(paramString, GsonUtil.INSTANCE.getInstance().toJson(paramConfigParameters)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith((SingleObserver)new DisposableSingleObserver<BaseResponse<String>>() {
            public void onError(Throwable param1Throwable) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("ERROR: ");
                stringBuilder.append(param1Throwable.getMessage());
//                Bugfender.sendUserFeedback("SetConfig", stringBuilder.toString());
            }

            public void onSuccess(BaseResponse<String> param1BaseResponse) {
//                Bugfender.sendUserFeedback("SetConfig", param1BaseResponse.getResult());
            }
        });
    }

    public void setIdentifyCallback(String paramString) {
        String str = Utils.getIPAddress(true);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Utils.getCompleteUrl(str, String.valueOf(8086)));
        stringBuilder.append("/printCard");
        str = stringBuilder.toString();
        this.service.setIdentifyCallback(paramString, str).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith((SingleObserver)new DisposableSingleObserver<BaseResponse<String>>() {
            public void onError(Throwable param1Throwable) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("ERROR: ");
                stringBuilder.append(param1Throwable.getMessage());
//                Bugfender.sendUserFeedback("setIdentifyCallback", stringBuilder.toString());
            }

            public void onSuccess(BaseResponse<String> param1BaseResponse) {
//                Bugfender.sendUserFeedback("setIdentifyCallback", param1BaseResponse.getResult());
            }
        });
    }

    public void setPassword(String paramString1, String paramString2) {
        this.service.setPassword(paramString1, paramString2).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith((SingleObserver)new DisposableSingleObserver<BaseResponse<String>>() {
            public void onError(Throwable param1Throwable) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("ERROR: ");
                stringBuilder.append(param1Throwable.getMessage());
//                Bugfender.sendUserFeedback("setPassword", stringBuilder.toString());
            }

            public void onSuccess(BaseResponse<String> param1BaseResponse) {
//                Bugfender.sendUserFeedback("setPassword", param1BaseResponse.getResult());
            }
        });
    }
}
