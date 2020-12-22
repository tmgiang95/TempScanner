package com.poa.tempscanner.ui.main.Print;

import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.usb.UsbDevice;

import java.io.IOException;

public interface TSPrinter {
    boolean checkConnection(UsbDevice paramUsbDevice);

    void printImage(Context paramContext, Bitmap paramBitmap) throws IOException;

    void printText(Context paramContext, String paramString) throws IOException;
}
