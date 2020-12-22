package com.poa.tempscanner.service;


import android.content.Context;
import androidx.work.ExistingWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.poa.tempscanner.mips.MipsApi;
import com.poa.tempscanner.mips.RetrofitApi;
import com.poa.tempscanner.utils.SettingsUtil;
//import com.lamasatech.visipoint.ui.activites.settings.util.SettingsUtil;
import com.poa.tempscanner.utils.FileUtil;
//import com.lamasatech.visipoint.webServer.MipsApi.RetrofitApi;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
//import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SyncMipsConfigWorker extends Worker {
    public static final Companion Companion = new Companion();

    public static final long JOB_NORMAL_INTERVAL_IN_SEC = 900L;

    public static final long JOB_RETRY_INTERVAL_IN_SEC = 900L;

    public static final String TAG = "SyncMipsConfigWorker";

    public SyncMipsConfigWorker(Context paramContext, WorkerParameters paramWorkerParameters) {
        super(paramContext, paramWorkerParameters);
    }

    private final ListenableWorker.Result syncMipsConfig(Context paramContext) {
        boolean bool;
        try {
            FileUtil.loadMissingMipsConfigFromFile(getApplicationContext());
            MipsApi mipsAPI = RetrofitApi.getApi(RetrofitApi.ApiTypes.MIPS_API, getApplicationContext());
//            Intrinsics.checkExpressionValueIsNotNull(object, "RetrofitApi.getApi(RetroapplicationContext)");
//            object = object;
            String str = SettingsUtil.INSTANCE.getSettingValue(paramContext, "password", "123456");
            mipsAPI.setIdentifyCallback(str);
            mipsAPI.getConfig(str);
            mipsAPI.getTempConfig(str);
            bool = true;
        } catch (Exception iOException) {
            iOException.printStackTrace();
            bool = false;
        }
        if (bool) {
            ListenableWorker.Result result1 = ListenableWorker.Result.success();
            Intrinsics.checkExpressionValueIsNotNull(result1, "Result.success()");
            return result1;
        }
        ListenableWorker.Result result = ListenableWorker.Result.retry();
        Intrinsics.checkExpressionValueIsNotNull(result, "Result.retry()");
        return result;
    }

    public ListenableWorker.Result doWork() {
        Context context = getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "applicationContext");
        ListenableWorker.Result result = syncMipsConfig(context);
        if (Intrinsics.areEqual(result.getClass(), ListenableWorker.Result.success().getClass())) {
            Companion.scheduleSyncMipsConfigJob(getApplicationContext(), 5L);
            return result;
        }
        Companion.scheduleSyncMipsConfigJob(getApplicationContext(), 5L);
        return result;
    }

    public static final class Companion {
        private Companion() {}

        public final void cancelJob(Context param1Context) {
            if (param1Context == null)
                Intrinsics.throwNpe();
            WorkManager.getInstance(param1Context).cancelAllWorkByTag("SyncMipsConfigWorker");
        }

        public final void scheduleSyncMipsConfigJob(Context param1Context, long param1Long) {
            System.out.println("start scheduleSyncMipsConfigJob");
            cancelJob(param1Context);
            WorkRequest workRequest = ((OneTimeWorkRequest.Builder)((OneTimeWorkRequest.Builder)(new OneTimeWorkRequest.Builder(SyncMipsConfigWorker.class)).setInitialDelay(param1Long, TimeUnit.SECONDS)).addTag("SyncMipsConfigWorker")).build();
            Intrinsics.checkExpressionValueIsNotNull(workRequest, "OneTimeWorkRequest.Build   ).addTag(TAG).build()");
            OneTimeWorkRequest oneTimeWorkRequest = (OneTimeWorkRequest)workRequest;
            if (param1Context == null)
                Intrinsics.throwNpe();
            WorkManager.getInstance(param1Context).beginUniqueWork("SyncMipsConfigWorker", ExistingWorkPolicy.REPLACE, oneTimeWorkRequest).enqueue();
        }
    }
}