package com.poa.tempscanner.ui.main.UpdateSetting.EmailSetting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import com.google.gson.Gson;
import com.poa.tempscanner.App;
import com.poa.tempscanner.R;
import com.poa.tempscanner.utils.Keys;
import com.poa.tempscanner.utils.SharedPreferencesController;

public class UpdateSettingFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceClickListener {
    SwitchPreferenceCompat updateSwitchPref;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.update_preferences, rootKey);
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
    }

    private void init(){
        updateSwitchPref = findPreference("auto_update");
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
        UpdateSettingModel updateSettingModel = App.getUpdateModel();
        Preference preference = findPreference(key);


        if (preference != null && preference instanceof SwitchPreferenceCompat) {
            SwitchPreferenceCompat preference1 = (SwitchPreferenceCompat) preference;
            switch (key) {
                case Keys.AUTO_UPDATE:
                    updateSettingModel.setAutoUpdate(preference1.isChecked());
                    break;
            }
        }

        SharedPreferencesController.with(getActivity()).saveString(Keys.UPDATE_SETTING_MODEL, new Gson().toJson(updateSettingModel));
    }


    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    @Override
    public boolean onPreferenceClick(Preference preference) {
        switch (preference.getKey()){
            case "print_status" :
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + preference.getKey());
        }
        return false;
    }

}
