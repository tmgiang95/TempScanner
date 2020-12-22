package com.poa.tempscanner;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener;
import com.poa.tempscanner.base.BaseActivity;
import com.poa.tempscanner.dialog.DialogCallback;
import com.poa.tempscanner.dialog.DialogEnterPassword;
import com.poa.tempscanner.service.FloatingActionButtonService;
import com.poa.tempscanner.service.FloatingWidgetService;
import com.poa.tempscanner.service.WebService;
import com.poa.tempscanner.ui.main.MainFragment;
import com.poa.tempscanner.utils.Constants;
import com.poa.tempscanner.utils.FileUtil;

import timber.log.Timber;


public class MainActivity extends BaseActivity implements DialogCallback {
    private static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    private static final int PERMISSIONS_REQUEST = 1;
    private DialogEnterPassword dialogEnterPassword;

    @Override
    public int getLayoutRedId() {
        return R.layout.main_activity;
    }

    @Override
    public int getFragmentContainerResId() {
        return R.id.container;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            showScreen(MainFragment.class.getName(), new Bundle());
        }

        setToolbar();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Dexter.withContext(this)
                    .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.FOREGROUND_SERVICE, Manifest.permission.RECEIVE_BOOT_COMPLETED)
                    .withListener(new BaseMultiplePermissionsListener())
                    .check();
        } else {
            Dexter.withContext(this)
                    .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.RECEIVE_BOOT_COMPLETED)
                    .withListener(new BaseMultiplePermissionsListener())
                    .check();
        }

        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(MainActivity.this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 1234);
            } else {
                Intent intent = new Intent(MainActivity.this, FloatingWidgetService.class);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(intent);
                }
                else {
                    startService(intent);
                }

            }
        } else {
            Intent intent = new Intent(MainActivity.this, FloatingWidgetService.class);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent);
            }
            else {
                startService(intent);
            }
        }

        Intent intent = new Intent(MainActivity.this, WebService.class);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);
        }
        else {
            startService(intent);
        }

    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (shouldShowRequestPermissionRationale(PERMISSION_CAMERA)) {
                Toast.makeText(
                        MainActivity.this,
                        "Camera permission is required for this demo",
                        Toast.LENGTH_LONG)
                        .show();
            }
            requestPermissions(new String[]{PERMISSION_CAMERA}, PERMISSIONS_REQUEST);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234) {
            Intent intent = new Intent(MainActivity.this, FloatingWidgetService.class);
            startService(intent);
        }
    }

    private void setToolbar() {
        //getting the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);

        //setting the title
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dialogEnterPassword == null) {
            dialogEnterPassword = DialogEnterPassword.newInstance();
        }
        dialogEnterPassword.setCallback(this);
        if (!dialogEnterPassword.isAdded()) {
            dialogEnterPassword.show(getSupportFragmentManager(), DialogEnterPassword.class.getName());
        }
    }

    @Override
    public void onClickOK(String password) {
        if (password.equalsIgnoreCase(FileUtil.getAppPassword(getApplicationContext()))) {
            if (dialogEnterPassword != null) {
                dialogEnterPassword.dismissDialog();
            }
        } else {
            // Do nothing
        }
    }
    @Override
    public void onClickCancel() {
        dialogEnterPassword.dismiss();
        this.finish();
    }

}