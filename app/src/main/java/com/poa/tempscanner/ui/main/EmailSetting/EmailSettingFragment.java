package com.poa.tempscanner.ui.main.EmailSetting;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import com.google.gson.Gson;
import com.poa.tempscanner.App;
import com.poa.tempscanner.R;
import com.poa.tempscanner.ui.main.Print.PrintThread;
import com.poa.tempscanner.utils.Keys;
import com.poa.tempscanner.utils.MailUtils;
import com.poa.tempscanner.utils.PrintUtil;
import com.poa.tempscanner.utils.SharedPreferencesController;

public class EmailSettingFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceClickListener {
    EditTextPreference emailRecipient;
    SwitchPreferenceCompat emailAlertSwitchPref;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.email_preferences, rootKey);
        boolean bool = false;
        EmailSettingModel emailSettingModel = new Gson().fromJson(SharedPreferencesController.with(App.getAppContext()).getString(Keys.EMAIL_SETTING_MODEL), EmailSettingModel.class);
        if (emailSettingModel != null)
            bool = emailSettingModel.isUseCustomStmp();
        if (bool) {
            findPreference(Keys.SMTP_SERVER).setVisible((Boolean) true);
            findPreference(Keys.SMTP_PORT).setVisible((Boolean) true);
            findPreference(Keys.SMTP_USER).setVisible((Boolean) true);
            findPreference(Keys.SMTP_PASSWORD).setVisible((Boolean) true);
            findPreference(Keys.SMTP_FROM).setVisible((Boolean) true);
            findPreference(Keys.SMTP_SSL).setVisible((Boolean) true);
            findPreference(Keys.SMTP_TLS).setVisible((Boolean) true);

        }

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
        findPreference(Keys.EMAIL_USE_CUSTOM_SMTP).setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                findPreference(Keys.SMTP_SERVER).setVisible((Boolean) newValue);
                findPreference(Keys.SMTP_PORT).setVisible((Boolean) newValue);
                findPreference(Keys.SMTP_USER).setVisible((Boolean) newValue);
                findPreference(Keys.SMTP_PASSWORD).setVisible((Boolean) newValue);
                findPreference(Keys.SMTP_FROM).setVisible((Boolean) newValue);
                findPreference(Keys.SMTP_SSL).setVisible((Boolean) newValue);
                findPreference(Keys.SMTP_TLS).setVisible((Boolean) newValue);
                return true;
            }
        });
        findPreference(Keys.SEND_TEST_EMAIL).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                 MailUtils.sendTestEmail();


                return true;
            }
        });

    }

    private void init(){
        emailRecipient = findPreference("email_recipient");
        emailAlertSwitchPref = findPreference("emailAlert");
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
        EmailSettingModel emailSettingModel = App.getEmailModel();
        Preference preference = findPreference(key);

        if (preference instanceof EditTextPreference){
            EditTextPreference editTextPreference =  (EditTextPreference)preference;
            switch (key) {
                case Keys.EMAIL_RECIPIENT:
                    emailSettingModel.setEmailRecipient(editTextPreference.getText());
                    break;

                case Keys.SMTP_SERVER:
                    emailSettingModel.setSmtpServer(editTextPreference.getText());

                    break;
                case Keys.SMTP_PORT:
                    emailSettingModel.setSmtpPort(editTextPreference.getText());
                    break;

                case Keys.SMTP_USER:
                    emailSettingModel.setSmtpUser(editTextPreference.getText());
                    break;

                case Keys.SMTP_PASSWORD:
                    emailSettingModel.setSmtpPassword(editTextPreference.getText());
                    break;

                case Keys.SMTP_FROM:
                    emailSettingModel.setSmtpFrom(editTextPreference.getText());
                    break;

            }

        }

        if (preference != null && preference instanceof SwitchPreferenceCompat) {
            SwitchPreferenceCompat preference1 = (SwitchPreferenceCompat) preference;
            switch (key) {
                case Keys.EMAIL_ALERT:
                    emailSettingModel.setEmailAlert(preference1.isChecked());
                    break;
                case Keys.EMAIL_USE_CUSTOM_SMTP:
                    emailSettingModel.setUseCustomStmp(preference1.isChecked());
                    break;
                case Keys.SMTP_SSL:
                    emailSettingModel.setSmtpSsl(preference1.isChecked());
                    break;
                case Keys.SMTP_TLS:
                    emailSettingModel.setSmtpTls(preference1.isChecked());
                    break;
                case Keys.EMAIL_NO_MASK:
                    emailSettingModel.setEmailMask(preference1.isChecked());
                    break;
                case Keys.EMAIL_REGISTER_USER:
                    emailSettingModel.setEmailRegisterUser(preference1.isChecked());
                    break;
                case Keys.EMAIL_VISITOR:
                    emailSettingModel.setVisitorStranger(preference1.isChecked());
                    break;
                case Keys.EMAIL_SHOW_PHOTO:
                    emailSettingModel.setShowPhoto(preference1.isChecked());
                    break;
                case Keys.EMAIL_SHOW_USER_TYPE:
                    emailSettingModel.setShowUserType(preference1.isChecked());
                    break;
                case Keys.EMAIL_SEND_TEXT:
                    emailSettingModel.setSendTestEmail(preference1.isChecked());
                    break;
            }
        }

        SharedPreferencesController.with(getActivity()).saveString(Keys.EMAIL_SETTING_MODEL, new Gson().toJson(emailSettingModel));
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
