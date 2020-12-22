package com.poa.tempscanner.ui.main.Print;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import com.brother.ptouch.sdk.PrinterSpec;

public class UsbConnectionSetting {
    private static UsbManager mUsbManager;

    public UsbDevice mDevice;

    public final PrinterSpec mSpec;

    public UsbConnectionSetting(PrinterSpec paramPrinterSpec) {
        this.mSpec = paramPrinterSpec;
    }

    public static UsbManager getUsbManager() {
        return mUsbManager;
    }

    public static void setUsbManager(UsbManager paramUsbManager) {
        mUsbManager = paramUsbManager;
    }
}
