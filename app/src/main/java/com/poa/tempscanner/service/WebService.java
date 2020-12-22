package com.poa.tempscanner.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.poa.tempscanner.MainActivity;
import com.poa.tempscanner.R;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import timber.log.Timber;

public class WebService extends Service {

    public static final int PORT = 8085;

    public static Context context;

    public static boolean isOn;

    private WebServer nano;

    private static final Class<?>[] mSetForegroundSignature = new Class[]{boolean.class};
    private static final Class<?>[] mStartForegroundSignature = new Class[]{
            int.class, Notification.class};
    private static final Class<?>[] mStopForegroundSignature = new Class[]{boolean.class};

    private NotificationManager mNM;
    private Method mSetForeground;
    private Method mStartForeground;
    private Method mStopForeground;
    private Object[] mSetForegroundArgs = new Object[1];
    private Object[] mStartForegroundArgs = new Object[2];
    private Object[] mStopForegroundArgs = new Object[1];

    void invokeMethod(Method method, Object[] args) {
        Timber.d("Foreground service invokeMethod");
        try {
            mStartForeground.invoke(this, mStartForegroundArgs);
        } catch (InvocationTargetException | IllegalAccessException e) {
            // Should not happen.
            Timber.w("WebService.invokeMethod - Unable to invoke method %s", e.toString());
        }
    }

    /**
     * This is a wrapper around the new startForeground method, using the older
     * APIs if it is not available.
     */
    void startForegroundCompat(int id, Notification notification) {
        // If we have the new startForeground API, then use it.
        Timber.e("startForegroundCompat");
        if (mStartForeground != null) {
            Timber.e("startForegroundCompat %s", id);
            mStartForegroundArgs[0] = id;
            mStartForegroundArgs[1] = notification;
            invokeMethod(mStartForeground, mStartForegroundArgs);
            return;
        }

        // Fall back on the old API.
        Timber.e("startForegroundCompat old API");
        mSetForegroundArgs[0] = Boolean.TRUE;
        invokeMethod(mSetForeground, mSetForegroundArgs);

        mNM.notify(id, notification);
    }

    /**
     * This is a wrapper around the new stopForeground method, using the older
     * APIs if it is not available.
     */
    void stopForegroundCompat(int id) {
        // If we have the new stopForeground API, then use it.
        if (mStopForeground != null) {
            mStopForegroundArgs[0] = Boolean.TRUE;
            try {
                mStopForeground.invoke(this, mStopForegroundArgs);
            } catch (InvocationTargetException e) {
                // Should not happen.
            } catch (IllegalAccessException e) {
                // Should not happen.
            }
            return;
        }

        // Fall back on the old API. Note to cancel BEFORE changing the
        // foreground state, since we could be killed at that point.
        mNM.cancel(id);
        mSetForegroundArgs[0] = Boolean.FALSE;
        invokeMethod(mSetForeground, mSetForegroundArgs);
    }


    private void start() {
        try {
            Timber.e("==== Start web service");
            this.nano.start();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            return;
        } catch (IOException iOException) {
            iOException.printStackTrace();
            return;
        }
    }

    public IBinder onBind(Intent paramIntent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onCreate() {
        super.onCreate();
        context = getBaseContext();

        // Start web server
        isOn = true;
        this.nano = new WebServer(getApplicationContext(), 8086);
        start();

//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent =
//                PendingIntent.getActivity(this, 0, notificationIntent, 0);
//
//        Notification notification =
//                new Notification.Builder(this, getString(R.string.service_started))
//                        .setContentTitle(getText(R.string.app_name))
//                        .setContentText(getText(R.string.app_name))
//                        .setSmallIcon(R.drawable.poa_logo)
//                        .setContentIntent(pendingIntent)
//                        .build();
//
//        startForeground(R.string.service_id, notification);

        // Foreground services
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        try {
            Timber.e("startForeground 2222");
            mStartForeground = getClass().getMethod("startForeground",
                    mStartForegroundSignature);
            mStopForeground = getClass().getMethod("stopForeground",
                    mStopForegroundSignature);
        } catch (NoSuchMethodException e) {
            // Running on an older platform.
            Timber.e("startForeground 33333");
            mStartForeground = mStopForeground = null;
            return;
        }
        try {
            mSetForeground = getClass().getMethod("setForeground",
                    mSetForegroundSignature);
            Timber.e("setForeground 2222");
        } catch (NoSuchMethodException e) {
            Timber.e("setForeground 3333");
            throw new IllegalStateException(
                    "OS doesn't have Service.startForeground OR Service.setForeground!");
        }

        CharSequence title = getString(R.string.app_name);
        CharSequence text = getText(R.string.service_started);
        int iconId = R.drawable.poa_logo;
        String channelId = title.toString(); // API > 25

        Notification notification;
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        if (Build.VERSION.SDK_INT >= 11) {
            Notification.Builder builder;
            // API >= 25 create channel
            if (Build.VERSION.SDK_INT >= 26) {
                NotificationChannel channel = new NotificationChannel(channelId, title, NotificationManager.IMPORTANCE_DEFAULT);
                channel.setDescription(text.toString());
                mNM.createNotificationChannel(channel);
            }

            builder = Build.VERSION.SDK_INT >= 26 ? new Notification.Builder(this, channelId) : new Notification.Builder(this);
            builder.setSmallIcon(iconId)
                    .setContentText(text)
                    .setContentTitle(title)
                    .setWhen(System.currentTimeMillis())
                    .setContentIntent(contentIntent);
            if (Build.VERSION.SDK_INT >= 16) {
                if (Build.VERSION.SDK_INT < 26) {
                    builder.setPriority(Notification.PRIORITY_DEFAULT);
                }
                notification = builder.build();
            } else {
                notification = builder.getNotification();
            }
        } else {
            notification = new NotificationCompat.Builder(this).setSmallIcon(iconId)
                    .setContentText(text)
                    .setContentTitle(title)
                    .setWhen(System.currentTimeMillis())
                    .setContentIntent(contentIntent)
                    .build();
        }

        startForegroundCompat(R.string.service_started, notification);
    }

    public void onDestroy() {
//        stopForegroundCompat(R.string.service_started);
        super.onDestroy();
        this.nano.stop();
        isOn = false;
    }

    public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2) {
        return Service.START_STICKY;
    }

}