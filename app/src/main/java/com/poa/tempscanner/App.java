package com.poa.tempscanner;

import android.Manifest;
import android.app.Application;
import android.content.Context;

import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener;
import com.poa.tempscanner.service.SyncMipsConfigWorker;
import com.poa.tempscanner.service.WebService;
import com.poa.tempscanner.ui.main.CDCSetting.CDCSettingModel;
import com.poa.tempscanner.ui.main.EmailSetting.EmailSettingModel;
import com.poa.tempscanner.ui.main.PrintSetting.PrintSettingModel;

import com.poa.tempscanner.ui.main.UpdateSetting.EmailSetting.UpdateSettingModel;
import com.poa.tempscanner.utils.FileUtil;
import timber.log.Timber;

public class App extends Application {

    private static Context appContext;

    private static PrintSettingModel settingModel;

    private static CDCSettingModel cdcSettingModel;

    private static EmailSettingModel emailSettingModel;

    private static UpdateSettingModel updateSettingModel;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        appContext = getApplicationContext();
        settingModel = new PrintSettingModel();
        emailSettingModel = new EmailSettingModel();
        updateSettingModel = new UpdateSettingModel();
        cdcSettingModel = new CDCSettingModel();
        Companion.integrateWithMIPSApp(appContext);
    }

    public static Context getAppContext() {
        return appContext;
    }

    public static PrintSettingModel getSettingModel() {
        return settingModel;
    }

    public static EmailSettingModel getEmailModel() {
        return emailSettingModel;
    }
    public static CDCSettingModel getCDCModel() {
        return cdcSettingModel;
    }

    public static UpdateSettingModel getUpdateModel() {
        return updateSettingModel;
    }
    public static final class Companion {
        private Companion() {}

        public final Context applicationContext() {
            return getAppContext();
        }

        public static final void integrateWithMIPSApp(Context param1Context) {
//            Intrinsics.checkParameterIsNotNull(param1Context, "context");
            FileUtil.appendStringToFile("WebServer: integrateWithMIPSApp");
//            if (Build.VERSION.SDK_INT < 23 || Settings.canDrawOverlays(param1Context))
//                param1Context.startService(new Intent(param1Context, FloatingWidgetService.class));
//            param1Context.startService(new Intent(param1Context, WebService.class));
            SyncMipsConfigWorker.Companion.scheduleSyncMipsConfigJob(param1Context, 0L);
//            PrinterManager.getInstance(param1Context).scanUsbDevices();
        }
    }

}
