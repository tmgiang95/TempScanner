package com.poa.tempscanner.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import androidx.preference.PreferenceManager;

import com.google.gson.Gson;
import com.poa.tempscanner.App;
import com.poa.tempscanner.R;
import com.poa.tempscanner.mail.SendMailTask;
import com.poa.tempscanner.model.MipsData;
import com.poa.tempscanner.model.SMTPServerConfig;
import com.poa.tempscanner.ui.main.EmailSetting.EmailSettingModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

public class MailUtils {
    private static Map<String, String> getCidImageMap(Context paramContext, MipsData paramMipsData) {
        if (paramMipsData.getCheckPic() != null) {
            Bitmap bitmap = ImageUtil.convertBase64ToBitmap(paramMipsData.getCheckPic());
            if (bitmap != null) {
                File file = paramContext.getExternalFilesDir("checkPhotos");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("checkPhoto");
                stringBuilder.append(paramMipsData.getUserId());
                stringBuilder.append(".jpg");
                file = new File(file, stringBuilder.toString());
                try {
                    file.delete();
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
                hashMap.put("VISIT_PHOTO", file.getAbsolutePath());
                return (Map)hashMap;
            }
        }
        return null;
    }

    public static String getEmailContent(Context paramContext, MipsData paramMipsData, float paramFloat, boolean paramBoolean1, boolean paramBoolean2, int paramInt, String paramString1, String paramString2) {
        float f;
        String str7 = loadStringFromRawResource(paramContext.getResources(), R.raw.email);
        String str1 = paramMipsData.getName();
        boolean bool = TextUtils.isEmpty(str1);
        String str6 = "";
        if (!bool) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(str1);
            str1 = stringBuilder.toString();
        } else {
            str1 = "";
        }
        String str5 = str1;
        if (paramBoolean2) {
            int i = paramMipsData.getType();
            if (i == 3) {
                str5 = "Registered user";
            } else if (i == 2) {
                str5 = "User in blacklist";
            } else {
                str5 = "Visitor / Stranger";
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str1);
            stringBuilder.append("<br>");
            stringBuilder.append(str5);
            str5 = stringBuilder.toString();
        }
        str1 = paramMipsData.getUserId();
        StringBuilder stringBuilder5 = new StringBuilder();
        stringBuilder5.append(str5);
        stringBuilder5.append("<br>User ID: ");
        stringBuilder5.append(str1);
        String str8 = stringBuilder5.toString();
        try {
            f = Float.parseFloat(paramMipsData.getTemperature());
        } catch (Exception exception) {
            f = 0.0F;
        }
        str1 = str6;
        if (f > 0.0F) {
            StringBuilder stringBuilder;
            if (paramInt == 0) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(f);
                str1 = "&#8451;";
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(Utils.celsiusToFahrenheit(f));
                str1 = "&#8457;";
            }
            stringBuilder.append(str1);
            str1 = stringBuilder.toString();
            stringBuilder = new StringBuilder();
            stringBuilder.append("<br>Temperature: ");
            stringBuilder.append(str1);
            str1 = stringBuilder.toString();
        }
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(str8);
        stringBuilder3.append(str1);
        String str4 = stringBuilder3.toString();
        paramInt = paramMipsData.getMask();
        if (paramInt != 0) {
            if (paramInt != 1) {
                str1 = "Not Detected";
            } else {
                str1 = "Yes";
            }
        } else {
            str1 = "No";
        }
        StringBuilder stringBuilder4 = new StringBuilder();
        stringBuilder4.append(str4);
        stringBuilder4.append("<br>Mask: ");
        stringBuilder4.append(str1);
        str1 = stringBuilder4.toString();
        String str3 = DateFormat.getDateTimeInstance(0, 2).format(Long.valueOf(paramMipsData.getCheckTime()));
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str1);
        stringBuilder2.append("<br>Time: ");
        stringBuilder2.append(str3);
        str3 = stringBuilder2.toString();
        str1 = str3;
        if (!TextUtils.isEmpty(paramString2)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str3);
            stringBuilder.append("<br>Device name: ");
            stringBuilder.append(paramString2);
            str1 = stringBuilder.toString();
        }
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(str1);
        String str2 = stringBuilder1.toString();
        if (paramBoolean1) {
            str1 = "block";
        } else {
            str1 = "none";
        }
        str7.replace("%SHOW_USER_PHOTO|%", str1);
        if (f >= paramFloat) {
            if (paramInt == 0) {
                str1 = "A high temperature and no mask alert has been triggered for the following user:";
            } else {
                str1 = "A high temperature alert has been triggered for the following user:";
            }
        } else {
            str1 = "A no mask alert has been triggered for the following user:";
        }
        return str7.replace("%USERDATA|%", str2).replace("%CONTENT|%", str1);
    }


    private static SMTPServerConfig getSMTPServerConfig() {
        EmailSettingModel emailSettingModel = new Gson().fromJson(SharedPreferencesController.with(App.getAppContext()).getString(Keys.EMAIL_SETTING_MODEL), EmailSettingModel.class);

        boolean bool = emailSettingModel.isUseCustomStmp();
        SMTPServerConfig sMTPServerConfig = new SMTPServerConfig();
        if (!bool) {
            sMTPServerConfig.setServer("smtp.gmail.com");
            sMTPServerConfig.setPort("465");
            sMTPServerConfig.setUser("poa.no.reply");
            sMTPServerConfig.setPassword("6dW9s3jRFmz4KcYq");
            sMTPServerConfig.setSSL(true);
            sMTPServerConfig.setTLS(false);
            return sMTPServerConfig;
        }
        sMTPServerConfig.setServer(emailSettingModel.getSmtpServer());
        sMTPServerConfig.setPort(emailSettingModel.getSmtpPort());
        sMTPServerConfig.setUser(emailSettingModel.getSmtpUser());
        sMTPServerConfig.setFromEmail(emailSettingModel.getSmtpFrom());
        sMTPServerConfig.setPassword(emailSettingModel.getSmtpPassword());
        sMTPServerConfig.setSSL(emailSettingModel.isSmtpSsl());
        sMTPServerConfig.setTLS(emailSettingModel.isSmtpTls());
        return sMTPServerConfig;
    }

    public static String loadStringFromRawResource(Resources paramResources, int paramInt) {
        InputStream inputStream = paramResources.openRawResource(paramInt);
        String str = streamToString(inputStream);
        try {
            inputStream.close();
            return str;
        } catch (IOException iOException) {
            return str;
        }
    }
    public static boolean isGTTempORNoMask(MipsData paramMipsData,float paramFloat){
        EmailSettingModel emailSettingModel = new Gson().fromJson(SharedPreferencesController.with(App.getAppContext()).getString(Keys.EMAIL_SETTING_MODEL), EmailSettingModel.class);
        float f;
        try {
            f = Float.parseFloat(paramMipsData.getTemperature());
        } catch (Exception exception) {
            f = -1.0F;
        }
        boolean bool = emailSettingModel.isEmailMask();

        return (paramFloat > 0.0F && paramFloat < f) || (bool && paramMipsData.getMask() == 0);
    }


//    public static void sendTemperatureWarningIfNeeded(Context paramContext, MipsData paramMipsData, boolean paramBoolean, float paramFloat) {
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
//        EmailSettingModel emailSettingModel = new Gson().fromJson(SharedPreferencesController.with(App.getAppContext()).getString(Keys.EMAIL_SETTING_MODEL), EmailSettingModel.class);
//
//        if (emailSettingModel.isEmailAlert()) {
//            String str = emailSettingModel.getEmailRecipient();
//            if (!TextUtils.isEmpty(str)) {
//                float f;
//                try {
//                    f = Float.parseFloat(paramMipsData.getTemperature());
//                } catch (Exception exception) {
//                    f = -1.0F;
//                }
//                boolean bool = emailSettingModel.isEmailMask();
//                if ((paramFloat > 0.0F && paramFloat < f) || (bool && paramMipsData.getMask() == 0) || paramBoolean) {
//
//                    bool = emailSettingModel.isEmailRegisterUser();
//                    boolean bool1 = emailSettingModel.isVisitorStranger();
//                    int i = paramMipsData.getType();
//                    if ((bool && i == 3) || (bool1 && i != 3) || paramBoolean) {
//                        Map map;
//                        paramBoolean = emailSettingModel.isShowPhoto();
//                        bool = emailSettingModel.isShowUserType();
//                        if (paramBoolean) {
//                            map = getCidImageMap(paramContext, paramMipsData);
//                        } else {
//                            map = null;
//                        }
//                        String str2 = "Company name";
//                        StringBuilder stringBuilder = new StringBuilder();
//                        stringBuilder.append(str2);
//                        stringBuilder.append(" - Temperature And Mask Warning - ");
//                        stringBuilder.append(paramMipsData.getName());
//                        str2 = stringBuilder.toString();
//                        String str1 = getEmailContent(paramContext, paramMipsData, paramFloat, paramBoolean, bool, Integer.parseInt(sharedPreferences.getString("tempShowMode", String.valueOf(0))), "mac", sharedPreferences.getString("deviceName", null));
//                        SMTPServerConfig sMTPServerConfig = getSMTPServerConfig();
//
//                        try {
//                            if (map != null) {
//                                new SendMailTask().execute(sMTPServerConfig, emailSettingModel.getEmailRecipient(), "POA Kiosk Alert", str1, map.get("VISIT_PHOTO"));
//                            }
//                            else
//                                new SendMailTask().execute(sMTPServerConfig, emailSettingModel.getEmailRecipient(), "POA Kiosk Alert", str1);
//
//                        } catch (Exception e) {
//                            Log.e("SendMail", e.getMessage(), e);
//                        }
//
//                    }
//                }
//            }
//        }
//    }

    public static void sendTemperatureWarningIfNeeded(Context paramContext, MipsData paramMipsData, boolean paramBoolean, float paramFloat) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
        EmailSettingModel emailSettingModel = new Gson().fromJson(SharedPreferencesController.with(App.getAppContext()).getString(Keys.EMAIL_SETTING_MODEL), EmailSettingModel.class);

        if (emailSettingModel.isEmailAlert()) {
            String str = emailSettingModel.getEmailRecipient();
            if (!TextUtils.isEmpty(str)) {
                float f;
                try {
                    f = Float.parseFloat(paramMipsData.getTemperature());
                } catch (Exception exception) {
                    f = -1.0F;
                }
                boolean bool = emailSettingModel.isEmailMask();
                if ((paramFloat > 0.0F && paramFloat < f) || (bool && paramMipsData.getMask() == 0) || paramBoolean) {

                    bool = emailSettingModel.isEmailRegisterUser();
                    boolean bool1 = emailSettingModel.isVisitorStranger();
                    int i = paramMipsData.getType();
                    if ((bool && i == 3) || (bool1 && i != 3) || paramBoolean) {
                        Map map;
                        paramBoolean = emailSettingModel.isShowPhoto();
                        bool = emailSettingModel.isShowUserType();
                        if (paramBoolean) {
                            map = getCidImageMap(paramContext, paramMipsData);
                        } else {
                            map = null;
                        }
                        String str2 = "Company name";
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(str2);
                        stringBuilder.append(" - Temperature And Mask Warning - ");
                        stringBuilder.append(paramMipsData.getName());
                        str2 = stringBuilder.toString();
                        String str1 = getEmailContent(paramContext, paramMipsData, paramFloat, paramBoolean, bool, Integer.parseInt(sharedPreferences.getString("tempShowMode", String.valueOf(0))), "mac", sharedPreferences.getString("deviceName", null));
                        SMTPServerConfig sMTPServerConfig = getSMTPServerConfig();

                        try {
                            if (map != null) {
                                new SendMailTask().execute(sMTPServerConfig, emailSettingModel.getEmailRecipient(), "POA Kiosk Alert", str1, map.get("VISIT_PHOTO"));
                            } else
                                new SendMailTask().execute(sMTPServerConfig, emailSettingModel.getEmailRecipient(), "POA Kiosk Alert", str1);

                        } catch (Exception e) {
                            Log.e("SendMail", e.getMessage(), e);
                        }

                    }
                }
            }
        }
    }

    public static String sendTestEmail(){
        EmailSettingModel emailSettingModel = new Gson().fromJson(SharedPreferencesController.with(App.getAppContext()).getString(Keys.EMAIL_SETTING_MODEL), EmailSettingModel.class);

        String sampleString = "Test email";
        SMTPServerConfig sMTPServerConfig = getSMTPServerConfig();
        try {
             new SendMailTask().execute(sMTPServerConfig, emailSettingModel.getEmailRecipient(), "Test", sampleString, App.getAppContext(), true);
        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }
        return null;
    }

    private static String streamToString(InputStream paramInputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while (true) {
                String str = bufferedReader.readLine();
                if (str != null) {
                    StringBuilder stringBuilder1 = new StringBuilder();
                    stringBuilder1.append(str);
                    stringBuilder1.append("\n");
                    stringBuilder.append(stringBuilder1.toString());
                    continue;
                }
                break;
            }
        } catch (IOException iOException) {}
        return stringBuilder.toString();
    }

    public static void writeStringAsFile(Context paramContext, String paramString1, String paramString2) {
        try {
            File file = new File(paramContext.getExternalFilesDir("download"), paramString2);
            file.delete();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(paramString1);
            fileWriter.close();
            return;
        } catch (IOException iOException) {
            iOException.printStackTrace();
            return;
        }
    }
}
