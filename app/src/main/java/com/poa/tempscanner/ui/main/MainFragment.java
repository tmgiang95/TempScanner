package com.poa.tempscanner.ui.main;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.poa.tempscanner.MainActivity;
import com.poa.tempscanner.R;
import com.poa.tempscanner.ui.main.CDCSetting.CDCSettingFragment;
import com.poa.tempscanner.ui.main.EmailSetting.EmailSettingFragment;
import com.poa.tempscanner.ui.main.EmailSetting.EmailSettingModel;
import com.poa.tempscanner.ui.main.PrintSetting.PrintSettingFragment;
import com.poa.tempscanner.ui.main.PrintSetting.PrintSettingModel;
import com.poa.tempscanner.ui.main.UpdateSetting.EmailSetting.UpdateSettingFragment;
import com.poa.tempscanner.utils.Keys;
import com.poa.tempscanner.utils.PrintUtil;
import com.poa.tempscanner.utils.SharedPreferencesController;

import timber.log.Timber;

@SuppressWarnings("deprecation")
public class MainFragment extends Fragment implements View.OnClickListener {

    private MainViewModel mViewModel;

    private static final String ACTION_USB_PERMISSION =
            "com.android.example.USB_PERMISSION";

    UsbDevice device;

    private final BroadcastReceiver usbReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    UsbDevice device = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);

                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        if (device != null) {
                            //call method to set up device communication
                        }
                    } else {
                    }
                }
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        if (getActivity() != null) {
            getActivity().findViewById(R.id.tvEmailSettings).setOnClickListener(this);
            getActivity().findViewById(R.id.tvPrintBadgeSetting).setOnClickListener(this);
            getActivity().findViewById(R.id.tvCDCSetting).setOnClickListener(this);
            getActivity().findViewById(R.id.tvUpdate).setOnClickListener(this);
            getActivity().findViewById(R.id.tvVersion).setOnClickListener(this);
            getActivity().findViewById(R.id.imgHome).setOnClickListener(this);
        }
        EmailSettingModel emailSettingModel = new Gson().fromJson(SharedPreferencesController.with(getContext()).getString(Keys.EMAIL_SETTING_MODEL), EmailSettingModel.class);

        PrintSettingModel settingModel = new Gson().fromJson(SharedPreferencesController.with(getContext()).getString(Keys.PRINT_SETTING_MODEL), PrintSettingModel.class);
        if (settingModel != null) {
            Timber.e("PrintSettingModel: " + settingModel.toString());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvEmailSettings:
                goToEmailSettingPage();
                break;
            case R.id.tvPrintBadgeSetting:
                goToPrintSettingPage();
                break;
            case R.id.tvCDCSetting:
                goToCDCSettingPage();
                break;
            case R.id.tvUpdate:
                goToUpdatePage();
                break;
            case R.id.tvVersion:
                goToVersionPage();
                break;
            case R.id.imgHome:
                openMipsApp();
                break;
            default:
                break;
        }
    }

    private void goToEmailSettingPage() {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).showScreen(EmailSettingFragment.class.getName(), new Bundle());
        }
    }
    private void goToCDCSettingPage() {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).showScreen(CDCSettingFragment.class.getName(), new Bundle());
        }
    }

    private void goToPrintSettingPage() {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).showScreen(PrintSettingFragment.class.getName(), new Bundle());
        }

    }

    private void goToUpdatePage() {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).showScreen(UpdateSettingFragment.class.getName(), new Bundle());
        }

    }

    private void goToVersionPage() {
    }
//    private void goToHome(){
//        Intent startMain = new Intent(Intent.ACTION_MAIN);
//        startMain.addCategory(Intent.CATEGORY_HOME);
//        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(startMain);
//    }

    public void openMipsApp() {
        PackageManager packageManager = getContext().getPackageManager();
        Intent intent2 = packageManager.getLaunchIntentForPackage("com.neldtv.mips");
        Intent intent1 = intent2;
        if (intent2 == null) {
            intent1 = packageManager.getLaunchIntentForPackage("com.gate.mips");
        }
        if (intent1 != null) {
            this.startActivity(intent1);
        }
    }
}