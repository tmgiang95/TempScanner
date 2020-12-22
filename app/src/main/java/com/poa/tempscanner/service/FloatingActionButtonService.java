package com.poa.tempscanner.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import com.poa.tempscanner.MainActivity;
import com.poa.tempscanner.R;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;

public class FloatingActionButtonService extends Service {

    private WindowManager windowManager;
    private WindowManager.LayoutParams mParams;
    private View widget;


    @Override
    public void onCreate() {
        super.onCreate();
        initView();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void initView() {
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        final LayoutInflater factory = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        widget = factory.inflate(R.layout.view_top, null);

        mParams = new WindowManager.LayoutParams();
        mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.gravity = Gravity.TOP;
        mParams.alpha = 1f;

        mParams.flags = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;// khong bi gioi han boi man hinh|Su duoc nut home
        } else {
            mParams.type = WindowManager.LayoutParams.TYPE_PHONE | WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;// noi tren all be mat
        }

        widget.setOnTouchListener(new View.OnTouchListener() {
            private GestureDetector gestureDetector = new GestureDetector(FloatingActionButtonService.this.getApplicationContext(), (GestureDetector.OnGestureListener) new GestureDetector.SimpleOnGestureListener() {
                public boolean onDoubleTap(MotionEvent param2MotionEvent) {
                    Intent intent = new Intent((Context) FloatingActionButtonService.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(FLAG_ACTIVITY_NO_HISTORY);
                    FloatingActionButtonService.this.startActivity(intent);
                    FloatingActionButtonService.this.stopSelf();
                    return false;
                }
            });

            @Override
            public boolean onTouch(View view, MotionEvent param1MotionEvent) {
                this.gestureDetector.onTouchEvent(param1MotionEvent);
                return false;
            }
        });

        windowManager.addView(widget, mParams);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (widget != null) {
            windowManager.removeView(widget);
            this.widget = null;
        }
    }

}
