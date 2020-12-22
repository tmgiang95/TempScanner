package com.poa.tempscanner.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.preference.PreferenceManager;

import com.example.questionnairelibrary.DetectorActivity;
import com.google.gson.Gson;
import com.poa.tempscanner.MainActivity;
import com.poa.tempscanner.model.MipsData;
import com.poa.tempscanner.model.UploadMipsResponse;
import com.poa.tempscanner.model.WebServerData;

import com.poa.tempscanner.utils.MailUtils;
import com.poa.tempscanner.utils.PrintUtil;
import com.poa.tempscanner.utils.SettingsUtil;

import fi.iki.elonen.NanoHTTPD;
import timber.log.Timber;

import java.io.IOException;
import java.util.HashMap;

public class WebServer extends NanoHTTPD {

    public static final String METHOD_UPLOAD_MIPS_GATE_RECORD = "/uploadMipsGateRecord";

    private Context context;

    private Gson gson;

    public WebServer(Context paramContext, int paramInt) {
        super(paramInt);
        this.context = paramContext;
        this.gson = new Gson();
        WebServerData.getInstance().getMutableLiveData().setValue("Hi From Server");
        Timber.e("WebServer started");
    }

    private void handleMipsData(MipsData paramMipsData) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("open VP for Name: ");
        stringBuilder1.append(paramMipsData);
        String str1 = stringBuilder1.toString();
        String str2 = "";
        if (str1 != null) {
            str1 = paramMipsData.getName();
        } else {
            str1 = "";
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("open VP for Name: ");
        stringBuilder2.append(paramMipsData);
        str1 = str2;
        if (stringBuilder2.toString() != null)
            str1 = paramMipsData.getName();
        Timber.e("WebServer " + str1);
        if (paramMipsData != null) {
            Context context = this.context;
            Timber.e("Print card");

            if (MailUtils.isGTTempORNoMask(paramMipsData, SettingsUtil.getStandardTemperature(PreferenceManager.getDefaultSharedPreferences(context)))) {
                MailUtils.sendTemperatureWarningIfNeeded(context, paramMipsData, false, SettingsUtil.getStandardTemperature(PreferenceManager.getDefaultSharedPreferences(context)));
            } else {
                Intent dialogIntent = new Intent(context, DetectorActivity.class);
                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(dialogIntent);
            }

            PrintUtil.print(context, paramMipsData);
        }
    }

    public NanoHTTPD.Response serve(NanoHTTPD.IHTTPSession paramIHTTPSession) {
        Timber.e("WebServer serve");
        String str = paramIHTTPSession.getUri();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("serve Uri: ");
        stringBuilder.append(paramIHTTPSession.getUri());
        HashMap<String, String> hashMap = new HashMap<String, String>();
        NanoHTTPD.Method method = paramIHTTPSession.getMethod();
        if (NanoHTTPD.Method.POST.equals(method) || NanoHTTPD.Method.PUT.equals(method))
            try {
                paramIHTTPSession.parseBody(hashMap);
            } catch (IOException | fi.iki.elonen.NanoHTTPD.ResponseException iOException) {

            }
        if (str.equalsIgnoreCase("/printCard")) {
//            new ArrayList();
            UploadMipsResponse uploadMipsResponse = new UploadMipsResponse();
            uploadMipsResponse.setCode(9);
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("serve files: ");
            stringBuilder1.append(this.gson.toJson(hashMap));
            String str1 = (String) hashMap.get("postData");
            if (str1 != null)
                try {
                    MipsData mipsData = (MipsData) this.gson.fromJson(str1, MipsData.class);

                    Timber.e("Data print " + mipsData.toString());
                    if (mipsData != null) {
                        uploadMipsResponse.setCode(0);
                        stringBuilder1 = new StringBuilder();
                        stringBuilder1.append("mipsData not Null: ");
                        stringBuilder1.append(mipsData.getName());
                        handleMipsData(mipsData);
                    }
                } catch (Exception exception) {
                    stringBuilder1 = new StringBuilder();
                    stringBuilder1.append("error: ");
                    stringBuilder1.append(exception.getMessage());
                }
            return newFixedLengthResponse((NanoHTTPD.Response.IStatus) NanoHTTPD.Response.Status.OK, "application/json", this.gson.toJson(uploadMipsResponse));
//            return newFixedLengthResponse((NanoHTTPD.Response.IStatus)NanoHTTPD.Response.Status.OK, "application/json", "Hello World");
        }
        return newFixedLengthResponse("Not Supported");
    }
}

