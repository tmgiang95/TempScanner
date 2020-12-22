package com.poa.tempscanner.mail;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.poa.tempscanner.model.SMTPServerConfig;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SendMailTask extends AsyncTask {

    private ProgressDialog statusDialog;
    private Activity sendMailActivity;
    private Context context;
    private ProgressDialog progress;
    public SendMailTask() {

    }

    protected void onPreExecute() {
    }

    @Override
    protected Object doInBackground(Object... args) {
        try {
            if (args.length == 6) {
                context = (Context) args[4];
            }

            Log.i("SendMailTask", "About to instantiate GMail...");
            GMail androidEmail;
            if (args.length == 5)
                androidEmail = new GMail((SMTPServerConfig)args[0],
                        Stream.of(args[1].toString().split(","))
                        .collect(Collectors.toList()), args[2].toString(),
                        args[3].toString(), args[4].toString());
            else if (args.length == 6)
                androidEmail = new GMail((SMTPServerConfig)args[0],
                        Stream.of(args[1].toString().split(","))
                                .collect(Collectors.toList()), args[2].toString(),
                        args[3].toString());
             else {
                androidEmail = new GMail((SMTPServerConfig) args[0],
                        Stream.of(args[1].toString().split(","))
                                .collect(Collectors.toList()), args[2].toString(),
                        args[3].toString());
            }

            publishProgress("Preparing mail message....");
            androidEmail.createEmailMessage();
            publishProgress("Sending email....");

            androidEmail.sendEmail();

            publishProgress("Email sent successfully");

            Log.i("SendMailTask", "Mail Sent.");
            return "Success";
        } catch (Exception e) {

            publishProgress("Sending email failed "+e.getMessage());
            Log.e("SendMailTask", e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void onProgressUpdate(Object... values) {
        if (context != null)
            Toast.makeText(context, values[0].toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPostExecute(Object result) {
    }

}
