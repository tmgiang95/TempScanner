package com.poa.tempscanner.ui.main.PrintSetting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import com.brother.ptouch.sdk.PrinterInfo;
import com.brother.ptouch.sdk.PrinterStatus;
import com.google.gson.Gson;
import com.poa.tempscanner.App;
import com.poa.tempscanner.R;
import com.poa.tempscanner.ui.main.Print.PrintThread;
import com.poa.tempscanner.utils.Keys;
import com.poa.tempscanner.utils.PrintUtil;
import com.poa.tempscanner.utils.SharedPreferencesController;

import java.io.IOException;

import timber.log.Timber;

public class PrintSettingFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceClickListener {
    Preference usbPref;
    SwitchPreferenceCompat printBadeSwPref;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (view != null) {
            view.setBackgroundColor(getResources().getColor(R.color.white));
            return view;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        checkPrinterStatus();
    }

    private void init(){
        usbPref = findPreference("print_status");
        usbPref.setOnPreferenceClickListener(this);
        printBadeSwPref = findPreference("print_badge");
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        PrintSettingModel settingModel = App.getSettingModel();
        SwitchPreferenceCompat preference = findPreference(key);
        if (preference != null) {
            switch (key) {
                case Keys.PRINT_BADGE:
                    settingModel.setPrintBadge(preference.isChecked());
                    break;

                case Keys.REGISTERED_USER:
                    settingModel.setRegisteredUser(preference.isChecked());
                    break;
                case Keys.REGISTERED_VISITOR:
                    settingModel.setRegisteredVisitor(preference.isChecked());
                    break;
                case Keys.UNREGISTERED_VISITOR:
                    settingModel.setUnregisteredVisitor(preference.isChecked());
                    break;
                case Keys.SCAN_IMAGE:
                    settingModel.setScanImage(preference.isChecked());
                    break;
                case Keys.USER_NAME:
                    settingModel.setUserName(preference.isChecked());
                    break;
                case Keys.USER_TYPE:
                    settingModel.setUserType(preference.isChecked());
                    break;
                case Keys.SCAN_TIME:
                    settingModel.setScanTime(preference.isChecked());
                    break;
                case Keys.TEMPERATURE:
                    settingModel.setTemperature(preference.isChecked());
                    break;
                case Keys.COMPANY:
                    settingModel.setCompany(preference.isChecked());
                    break;
            }
        }

        SharedPreferencesController.with(getActivity()).saveString(Keys.PRINT_SETTING_MODEL, new Gson().toJson(settingModel));
    }


    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    @Override
    public boolean onPreferenceClick(Preference preference) {
        switch (preference.getKey()){
            case "print_status" :
                checkPrinterStatus();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + preference.getKey());
        }
        return false;
    }

    private void checkPrinterStatus(){
        PrintThread printThread = new PrintThread(){
            @Override
            public void run() {
                    boolean result = (PrintUtil.scanUsbDevices(getContext(), PrintUtil.printer));
                    showResult(result);

            }
        };
        printThread.run();
    }

    public String showResult(boolean bool) {
        String result;
        if (bool) {
            result = getContext().getString(R.string.connected);
        } else {
            result = getContext().getString(R.string.no_connected);
        }
        getActivity().runOnUiThread(() -> {
            usbPref.setSummary(result);
        });
        return result;
    }
}
