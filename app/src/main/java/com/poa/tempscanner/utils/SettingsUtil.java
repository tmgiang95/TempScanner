package com.poa.tempscanner.utils;


import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;
//import
//import com.lamasatech.visipoint.util.DeviceUtils;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\000>\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\020\b\n\000\n\002\020\013\n\002\b4\n\002\030\002\n\002\b\b\n\002\020\007\n\000\n\002\030\002\n\002\b\004\n\002\020\002\n\002\b\002\b\002\030\0002\0020\001B\007\b\002\006\002\020\002J\016\020;\032\0020\b2\006\020<\032\0020=J\020\020>\032\004\030\0010\0042\006\020<\032\0020=J\020\020?\032\0020\0042\006\020<\032\0020=H\007J\020\020@\032\0020\0062\006\020<\032\0020=H\002J\020\020A\032\0020\0062\006\020<\032\0020=H\002J$\020B\032\0020\0042\b\020<\032\004\030\0010=2\b\020C\032\004\030\0010\0042\b\020D\032\004\030\0010\004J\020\020E\032\0020F2\006\020G\032\0020HH\007J)\020I\032\0020\b2\b\020<\032\004\030\0010=2\b\020C\032\004\030\0010\0042\b\020D\032\004\030\0010\b\006\002\020JJ\020\020K\032\0020\b2\006\020G\032\0020HH\007J\030\020L\032\0020M2\006\020<\032\0020=2\b\020N\032\004\030\0010\004R\016\020\003\032\0020\004X\006\002\n\000R\016\020\005\032\0020\006X\006\002\n\000R\016\020\007\032\0020\bX\006\002\n\000R\016\020\t\032\0020\bX\006\002\n\000R\016\020\n\032\0020\bX\006\002\n\000R\016\020\013\032\0020\bX\006\002\n\000R\016\020\f\032\0020\bX\006\002\n\000R\016\020\r\032\0020\bX\006\002\n\000R\016\020\016\032\0020\bX\006\002\n\000R\016\020\017\032\0020\bX\006\002\n\000R\016\020\020\032\0020\bX\006\002\n\000R\016\020\021\032\0020\bX\006\002\n\000R\016\020\022\032\0020\bX\006\002\n\000R\016\020\023\032\0020\006X\006\002\n\000R\016\020\024\032\0020\004X\006\002\n\000R\016\020\025\032\0020\004X\006\002\n\000R\016\020\026\032\0020\006X\006\002\n\000R\016\020\027\032\0020\004X\006\002\n\000R\016\020\030\032\0020\004X\006\002\n\000R\016\020\031\032\0020\004X\006\002\n\000R\016\020\032\032\0020\004X\006\002\n\000R\016\020\033\032\0020\004X\006\002\n\000R\016\020\034\032\0020\004X\006\002\n\000R\016\020\035\032\0020\004X\006\002\n\000R\016\020\036\032\0020\004X\006\002\n\000R\016\020\037\032\0020\004X\006\002\n\000R\016\020 \032\0020\004X\006\002\n\000R\016\020!\032\0020\004X\006\002\n\000R\016\020\"\032\0020\004X\006\002\n\000R\016\020#\032\0020\004X\006\002\n\000R\016\020$\032\0020\004X\006\002\n\000R\016\020%\032\0020\004X\006\002\n\000R\016\020&\032\0020\004X\006\002\n\000R\016\020'\032\0020\004X\006\002\n\000R\016\020(\032\0020\004X\006\002\n\000R\016\020)\032\0020\004X\006\002\n\000R\016\020*\032\0020\004X\006\002\n\000R\016\020+\032\0020\004X\006\002\n\000R\016\020,\032\0020\004X\006\002\n\000R\016\020-\032\0020\004X\006\002\n\000R\016\020.\032\0020\004X\006\002\n\000R\016\020/\032\0020\004X\006\002\n\000R\016\0200\032\0020\004X\006\002\n\000R\016\0201\032\0020\004X\006\002\n\000R\016\0202\032\0020\004X\006\002\n\000R\016\0203\032\0020\004X\006\002\n\000R\016\0204\032\0020\004X\006\002\n\000R\016\0205\032\0020\004X\006\002\n\000R\016\0206\032\0020\004X\006\002\n\000R\016\0207\032\0020\004X\006\002\n\000R\016\0208\032\0020\004X\006\002\n\000R\016\0209\032\0020\004X\006\002\n\000R\016\020:\032\0020\004X\006\002\n\000\006O"}, d2 = {"Lcom/lamasatech/visipoint/ui/activites/settings/util/SettingsUtil;", "", "()V", "ACTIVATION_TOKEN_SALT", "", "DEFAULT_IS_BODY_TEMP_ALARM", "", "DEFAULT_IS_EMAIL_ALERT", "", "DEFAULT_IS_PRINT_BADGE", "DEFAULT_IS_REGISTERED_USER_PRINT_BADGE", "DEFAULT_IS_SHOW_COMPANY_NAME_PRINT_BADGE", "DEFAULT_IS_SHOW_SCAN_IMAGE_PRINT_BADGE", "DEFAULT_IS_SHOW_SCAN_TIME_PRINT_BADGE", "DEFAULT_IS_SHOW_TEMP_PRINT_BADGE", "DEFAULT_IS_SHOW_USER_NAME_PRINT_BADGE", "DEFAULT_IS_SHOW_USER_TYPE_PRINT_BADGE", "DEFAULT_IS_UNREGISTERED_PRINT_BADGE", "DEFAULT_IS_VISITOR_PRINT_BADGE", "DEFAULT_IS_WEARING_MASK", "DEFAULT_PASSWORD", "DEFAULT_STANDARD_TEMP", "DEFAULT_STRANGER_MODE", "PREF_KEY_ACTIVATION_TOKEN", "PREF_KEY_COMPANY_NAME", "PREF_KEY_DEVICE_ID", "PREF_KEY_DEVICE_NAME", "PREF_KEY_EMAIL_ALERT_RECIPIENTS", "PREF_KEY_ID", "PREF_KEY_IS_BODY_TEMP_ALARM", "PREF_KEY_IS_CUSTOM_SMTP_SERVER", "PREF_KEY_IS_EMAIL_ALERT", "PREF_KEY_IS_MASK_EMAIL_ALERT", "PREF_KEY_IS_OTHER_USER_EMAIL_ALERT", "PREF_KEY_IS_PRINT_BADGE", "PREF_KEY_IS_REGISTERED_USER_EMAIL_ALERT", "PREF_KEY_IS_REGISTERED_USER_PRINT_BADGE", "PREF_KEY_IS_SHOW_COMPANY_NAME_PRINT_BADGE", "PREF_KEY_IS_SHOW_PHOTO_EMAIL_ALERT", "PREF_KEY_IS_SHOW_SCAN_IMAGE_PRINT_BADGE", "PREF_KEY_IS_SHOW_SCAN_TIME_PRINT_BADGE", "PREF_KEY_IS_SHOW_TEMP_PRINT_BADGE", "PREF_KEY_IS_SHOW_USER_NAME_PRINT_BADGE", "PREF_KEY_IS_SHOW_USER_TYPE_EMAIL_ALERT", "PREF_KEY_IS_SHOW_USER_TYPE_PRINT_BADGE", "PREF_KEY_IS_SMTP_SSL", "PREF_KEY_IS_SMTP_TLS", "PREF_KEY_IS_UNREGISTERED_PRINT_BADGE", "PREF_KEY_IS_VISITOR_PRINT_BADGE", "PREF_KEY_IS_WEARING_MASK", "PREF_KEY_PASSWORD", "PREF_KEY_SEND_TEST_EMAIL_ALERT", "PREF_KEY_SMTP_PASS", "PREF_KEY_SMTP_PORT", "PREF_KEY_SMTP_SERVER", "PREF_KEY_SMTP_USER", "PREF_KEY_STANDARD_TEMP", "PREF_KEY_STRANGER_MODE", "PREF_KEY_TEMP_SHOW_MODE", "checkActivationToken", "context", "Landroid/content/Context;", "getActivationToken", "getCompanyName", "getDeviceId", "getId", "getSettingValue", "key", "defaultValue", "getStandardTemperature", "", "prefs", "Landroid/content/SharedPreferences;", "isAutoUpdateOn", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Boolean;)Z", "isDetectMask", "saveActivationToken", "", "token", "app_release"}, k = 1, mv = {1, 1, 16})
public final class SettingsUtil {
    private static final String ACTIVATION_TOKEN_SALT = "VisipointToKen";

    public static final int DEFAULT_IS_BODY_TEMP_ALARM = 1;

    public static final boolean DEFAULT_IS_EMAIL_ALERT = false;

    public static final boolean DEFAULT_IS_PRINT_BADGE = false;

    public static final boolean DEFAULT_IS_REGISTERED_USER_PRINT_BADGE = true;

    public static final boolean DEFAULT_IS_SHOW_COMPANY_NAME_PRINT_BADGE = true;

    public static final boolean DEFAULT_IS_SHOW_SCAN_IMAGE_PRINT_BADGE = true;

    public static final boolean DEFAULT_IS_SHOW_SCAN_TIME_PRINT_BADGE = true;

    public static final boolean DEFAULT_IS_SHOW_TEMP_PRINT_BADGE = true;

    public static final boolean DEFAULT_IS_SHOW_USER_NAME_PRINT_BADGE = true;

    public static final boolean DEFAULT_IS_SHOW_USER_TYPE_PRINT_BADGE = true;

    public static final boolean DEFAULT_IS_UNREGISTERED_PRINT_BADGE = false;

    public static final boolean DEFAULT_IS_VISITOR_PRINT_BADGE = false;

    public static final int DEFAULT_IS_WEARING_MASK = 0;

    public static final String DEFAULT_PASSWORD = "123456";

    public static final String DEFAULT_STANDARD_TEMP = "37.3";

    public static final int DEFAULT_STRANGER_MODE = 0;

    public static final SettingsUtil INSTANCE = new SettingsUtil();

    private static final String PREF_KEY_ACTIVATION_TOKEN = "activationToken";

    public static final String PREF_KEY_COMPANY_NAME = "companyName";

    public static final String PREF_KEY_DEVICE_ID = "deviceId";

    public static final String PREF_KEY_DEVICE_NAME = "deviceName";

    public static final String PREF_KEY_EMAIL_ALERT_RECIPIENTS = "emailAlertRecipients";

    public static final String PREF_KEY_ID = "id";

    public static final String PREF_KEY_IS_BODY_TEMP_ALARM = "isBodyTempAlarm";

    public static final String PREF_KEY_IS_CUSTOM_SMTP_SERVER = "isCustomSmtpServer";

    public static final String PREF_KEY_IS_EMAIL_ALERT = "isEmailAlert";

    public static final String PREF_KEY_IS_MASK_EMAIL_ALERT = "isMaskEmailAlert";

    public static final String PREF_KEY_IS_OTHER_USER_EMAIL_ALERT = "isOtherUserEmailAlert";

    public static final String PREF_KEY_IS_PRINT_BADGE = "isPrintBadge";

    public static final String PREF_KEY_IS_REGISTERED_USER_EMAIL_ALERT = "isRegisteredUserEmailAlert";

    public static final String PREF_KEY_IS_REGISTERED_USER_PRINT_BADGE = "isRegisteredUserPrintBadge";

    public static final String PREF_KEY_IS_SHOW_COMPANY_NAME_PRINT_BADGE = "isShowCompanyNamePrintBadge";

    public static final String PREF_KEY_IS_SHOW_PHOTO_EMAIL_ALERT = "isShowPhotoEmailAlert";

    public static final String PREF_KEY_IS_SHOW_SCAN_IMAGE_PRINT_BADGE = "isShowScanImagePrintBadge";

    public static final String PREF_KEY_IS_SHOW_SCAN_TIME_PRINT_BADGE = "isShowScanTimePrintBadge";

    public static final String PREF_KEY_IS_SHOW_TEMP_PRINT_BADGE = "isShowTemperaturePrintBadge";

    public static final String PREF_KEY_IS_SHOW_USER_NAME_PRINT_BADGE = "isShowUserNamePrintBadge";

    public static final String PREF_KEY_IS_SHOW_USER_TYPE_EMAIL_ALERT = "isShowUserTypeEmailAlert";

    public static final String PREF_KEY_IS_SHOW_USER_TYPE_PRINT_BADGE = "isShowUserTypePrintBadge";

    public static final String PREF_KEY_IS_SMTP_SSL = "isSmtpSsl";

    public static final String PREF_KEY_IS_SMTP_TLS = "isSmtpTls";

    public static final String PREF_KEY_IS_UNREGISTERED_PRINT_BADGE = "isUnregisteredPrintBadge";

    public static final String PREF_KEY_IS_VISITOR_PRINT_BADGE = "isVisitorPrintBadge";

    public static final String PREF_KEY_IS_WEARING_MASK = "isWearingMask";

    public static final String PREF_KEY_PASSWORD = "password";

    public static final String PREF_KEY_SEND_TEST_EMAIL_ALERT = "sendTestEmailAlert";

    public static final String PREF_KEY_SMTP_PASS = "smtpPass";

    public static final String PREF_KEY_SMTP_PORT = "smtpPort";

    public static final String PREF_KEY_SMTP_SERVER = "smtpServer";

    public static final String PREF_KEY_SMTP_USER = "smtpUser";

    public static final String PREF_KEY_STANDARD_TEMP = "standardBodyTemp";

    public static final String PREF_KEY_STRANGER_MODE = "strangerMode";

    public static final String PREF_KEY_TEMP_SHOW_MODE = "tempShowMode";

//    @JvmStatic
//    public static final String getCompanyName(Context paramContext) {
//        Intrinsics.checkParameterIsNotNull(paramContext, "context");
//        String str2 = PreferenceManager.getDefaultSharedPreferences(paramContext).getString("companyName", null);
//        String str1 = str2;
//        if (TextUtils.isEmpty(str2))
//            str1 = paramContext.getString(2131820603);
//        Intrinsics.checkExpressionValueIsNotNull(str1, "companyName");
//        return str1;
//    }

    private final int getDeviceId(Context paramContext) {
        return PreferenceManager.getDefaultSharedPreferences(paramContext).getInt("deviceId", 86);
    }

    private final int getId(Context paramContext) {
        return PreferenceManager.getDefaultSharedPreferences(paramContext).getInt("id", 1);
    }

    @JvmStatic
    public static final float getStandardTemperature(SharedPreferences paramSharedPreferences) {
        Intrinsics.checkParameterIsNotNull(paramSharedPreferences, "prefs");
        String str = paramSharedPreferences.getString("standardBodyTemp", "37.3");
        try {
            Intrinsics.checkExpressionValueIsNotNull(str, "tempLimitString");
            return Float.parseFloat(str);
        } catch (Exception exception) {
            return Float.parseFloat("37.3");
        }
    }

    @JvmStatic
    public static final boolean isDetectMask(SharedPreferences paramSharedPreferences) {
        Intrinsics.checkParameterIsNotNull(paramSharedPreferences, "prefs");
        return paramSharedPreferences.getBoolean("isMaskEmailAlert", true);
    }

//    public final boolean checkActivationToken(Context paramContext) {
//        boolean bool;
//        Intrinsics.checkParameterIsNotNull(paramContext, "context");
//        String str = getActivationToken(paramContext);
//        CharSequence charSequence = str;
//        if (charSequence == null || StringsKt.isBlank(charSequence)) {
//            bool = true;
//        } else {
//            bool = false;
//        }
//        if (!bool) {
//            charSequence = new StringBuilder();
//            charSequence.append("VisipointToKen");
//            charSequence.append(DeviceUtils.INSTANCE.getSerialNumber());
//            if (Intrinsics.areEqual(str, UtilsKt.getSHA512(charSequence.toString())))
//                return true;
//            saveActivationToken(paramContext, null);
//        }
//        return false;
//    }

    public final String getActivationToken(Context paramContext) {
        Intrinsics.checkParameterIsNotNull(paramContext, "context");
        return PreferenceManager.getDefaultSharedPreferences(paramContext).getString("activationToken", null);
    }

    public final String getSettingValue(Context paramContext, String paramString1, String paramString2) {
        String str = PreferenceManager.getDefaultSharedPreferences(paramContext).getString(paramString1, paramString2);
        Intrinsics.checkExpressionValueIsNotNull(str, "PreferenceManager.getDefdefaultValue)");
        return str;
    }

    public final boolean isAutoUpdateOn(Context paramContext, String paramString, Boolean paramBoolean) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
        if (paramBoolean == null)
            Intrinsics.throwNpe();
        return sharedPreferences.getBoolean(paramString, paramBoolean.booleanValue());
    }

    public final void saveActivationToken(Context paramContext, String paramString) {
        boolean bool;
        Intrinsics.checkParameterIsNotNull(paramContext, "context");
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
        String str = (String)null;
        CharSequence charSequence = paramString;
        if (charSequence == null || StringsKt.isBlank(charSequence)) {
            bool = true;
        } else {
            bool = false;
        }
        if (!bool) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("VisipointToKen");
            if (paramString != null) {
                paramString = paramString.toUpperCase();
                Intrinsics.checkExpressionValueIsNotNull(paramString, "(this as java.lang.String).toUpperCase()");
                stringBuilder.append(paramString);
                str = Utils.getSHA512(stringBuilder.toString());
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
        }
        editor.putString("activationToken", str);
        editor.apply();
    }
}
