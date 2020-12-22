package com.poa.tempscanner.mips;

import android.content.Context;
import com.poa.tempscanner.utils.GsonUtil;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import java.lang.reflect.InvocationTargetException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {
    public static <T> T getApi(ApiTypes paramApiTypes, Context paramContext) {
        try {
            return (T)paramApiTypes.getApiType().getConstructor(new Class[] { Context.class }).newInstance(new Object[] { paramContext });
        } catch (InstantiationException instantiationException) {
            instantiationException.printStackTrace();
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        } catch (NoSuchMethodException noSuchMethodException) {
            noSuchMethodException.printStackTrace();
        } catch (InvocationTargetException invocationTargetException) {
            invocationTargetException.printStackTrace();
        }
        return null;
    }

    public static Retrofit getRetrofit(Context paramContext, String paramString) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = (new OkHttpClient.Builder()).addInterceptor((Interceptor)httpLoggingInterceptor).build();
        return (new Retrofit.Builder()).baseUrl(paramString).addConverterFactory((Converter.Factory)GsonConverterFactory.create(GsonUtil.INSTANCE.getInstance())).addCallAdapterFactory((CallAdapter.Factory)RxJava3CallAdapterFactory.create()).client(okHttpClient).build();
    }

    public enum ApiTypes {
        MIPS_API(MipsApi.class);

        private final Class<? extends RetrofitApi> apiClass;

//        static {
//            ApiTypes apiTypes = new ApiTypes(MipsApi.class);
//            MIPS_API = apiTypes;
//             new ApiTypes[] { apiTypes };
//        }

        ApiTypes(Class<? extends RetrofitApi> param1Class) {
            this.apiClass = param1Class;
        }

        Class<? extends RetrofitApi> getApiType() {
            return this.apiClass;
        }
    }
}