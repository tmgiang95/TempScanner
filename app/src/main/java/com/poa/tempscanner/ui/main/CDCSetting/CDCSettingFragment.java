package com.poa.tempscanner.ui.main.CDCSetting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.CheckBoxPreference;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import com.google.gson.Gson;
import com.poa.tempscanner.App;
import com.poa.tempscanner.R;
import com.poa.tempscanner.utils.Keys;
import com.poa.tempscanner.utils.MailUtils;
import com.poa.tempscanner.utils.SharedPreferencesController;

public class CDCSettingFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceClickListener {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.cdc_preferences, rootKey);
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
        CDCSettingModel cdcSettingModel = App.getCDCModel();
        Preference preference = findPreference(key);

        if (preference instanceof SwitchPreferenceCompat) {
            SwitchPreferenceCompat switchPreferenceCompat = (SwitchPreferenceCompat) preference;
            switch (key) {
                case Keys.CDC_QUESTIONNAIRE:
                    cdcSettingModel.setCdcQuestionnaire(switchPreferenceCompat.isChecked());
                    break;
                case Keys.CDC_MASK:
                    cdcSettingModel.setCdcMask(switchPreferenceCompat.isChecked());
                    break;
            }

        }

        if (preference instanceof CheckBoxPreference) {
            CheckBoxPreference checkBoxPreference = (CheckBoxPreference) preference;
            switch (key) {
                case Keys.CDC_REQUIRE_EMPLOYEE:
                    cdcSettingModel.setCdcRequireRoleEmployee(checkBoxPreference.isChecked());
                    break;
                case Keys.CDC_REQUIRE_VISITOR:
                    cdcSettingModel.setCdcRequireRoleVisitor(checkBoxPreference.isChecked());
                    break;
                case Keys.CDC_REQUIRE_UNREGISTER:
                    cdcSettingModel.setCdcRequireRoleUnregister(checkBoxPreference.isChecked());
                    break;
            }
        }

        SharedPreferencesController.with(getActivity()).saveString(Keys.EMAIL_SETTING_MODEL, new Gson().toJson(cdcSettingModel));
    }


    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    @Override
    public boolean onPreferenceClick(Preference preference) {
        switch (preference.getKey()) {
            case "print_status":
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + preference.getKey());
        }
        return false;
    }

}
