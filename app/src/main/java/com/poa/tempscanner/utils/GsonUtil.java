package com.poa.tempscanner.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

//@Metadata(bv = {1, 0, 3}, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\005\b\002\030\0002\0020\001B\007\b\002\006\002\020\002J\b\020\b\032\0020\004H\002R$\020\005\032\004\030\0010\0042\b\020\003\032\004\030\0010\0048F@BX\016\006\b\n\000\032\004\b\006\020\007\006\t"}, d2 = {"Lcom/lamasatech/visipoint/sharedhelpers/util/GsonUtil;", "", "()V", "<set-?>", "Lcom/google/gson/Gson;", "instance", "getInstance", "()Lcom/google/gson/Gson;", "createInstance", "app_release"}, k = 1, mv = {1, 1, 16})
public final class GsonUtil {
    public static final GsonUtil INSTANCE;

    private static Gson instance;

    static {
        GsonUtil gsonUtil = new GsonUtil();
        INSTANCE = gsonUtil;
        instance = gsonUtil.createInstance();
    }

    private final Gson createInstance() {
        Gson gson = (new GsonBuilder()).create();
        Intrinsics.checkExpressionValueIsNotNull(gson, "GsonBuilder()\n  .create()");
        return gson;
    }

    public final Gson getInstance() {
        if (instance == null)
            instance = createInstance();
        return instance;
    }
}
