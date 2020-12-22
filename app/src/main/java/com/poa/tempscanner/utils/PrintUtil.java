package com.poa.tempscanner.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.preference.PreferenceManager;

import com.brother.ptouch.sdk.LabelInfo;
import com.brother.ptouch.sdk.Printer;
import com.brother.ptouch.sdk.PrinterInfo;
import com.brother.ptouch.sdk.PrinterStatus;
import com.google.gson.Gson;
import com.poa.tempscanner.App;
import com.poa.tempscanner.R;
import com.poa.tempscanner.model.CreateSampleData;
import com.poa.tempscanner.model.MipsData;
import com.poa.tempscanner.model.PrintModel;
import com.poa.tempscanner.ui.main.Print.PrintThread;
import com.poa.tempscanner.ui.main.Print.TSPrinter;
import com.poa.tempscanner.ui.main.PrintSetting.PrintSettingModel;

import timber.log.Timber;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import static com.poa.tempscanner.utils.PermissionUtil.checkAndGrantUsbDevicePermission;


public class PrintUtil {
    public static final Printer printer = new Printer();

    private static UsbManager usbManager;

    private static UsbDevice usbDevice;

    private static SharedPreferences pref;

    private static HashMap<String, TSPrinter> connectedPrinterDevices;

    private static String tempEnd = "°C";

    private static String userType = "";

    private static String currentTemp = "";

    private static Context context;

    private static boolean checkUSBPermission(Context paramContext, Printer paramPrinter) {
        usbManager = (UsbManager) paramContext.getSystemService(Context.USB_SERVICE);
        usbDevice = paramPrinter.getUsbDevice(usbManager);
        return (usbDevice == null) ? false : usbManager.hasPermission(usbDevice);
    }


    public static void initPrinter(Context context) {

            initPrinterInfo(context);

    }

    public static boolean scanUsbDevices(Context context, Printer printer) {
        boolean a = false;
        try {
               a = checkUsbDeviceConnection(context, printer, true);
        } catch (Exception exception) {
            Log.e("autopermission", exception.getMessage());
        }
        return  a;
    }

    public static boolean checkConnection(UsbDevice paramUsbDevice) {
        return ("Brother".equalsIgnoreCase(paramUsbDevice.getManufacturerName()) && paramUsbDevice.getProductName().toLowerCase().contains("ql"));
    }

    public static boolean checkUsbDeviceConnection(Context context, Printer printer, boolean paramBoolean) {
        UsbManager usbManager = (UsbManager)context.getSystemService("usb");
        UsbDevice usbDevice = printer.getUsbDevice(usbManager);

        boolean bool = checkConnection(usbDevice);
        if (checkAndGrantUsbDevicePermission(context, usbManager, usbDevice) && bool && paramBoolean) {
            if (connectedPrinterDevices == null)
                connectedPrinterDevices = new HashMap<String, TSPrinter>();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(usbDevice.getManufacturerName());
            stringBuilder.append(usbDevice.getProductId());
            String str = stringBuilder.toString();
            if (!connectedPrinterDevices.containsKey(str)) {
                connectedPrinterDevices.put(str, null);
            }
        }
        HashMap<String, TSPrinter> hashMap = connectedPrinterDevices;
        if (hashMap != null && hashMap.size() > 0) {
            paramBoolean = true;
        } else {
            paramBoolean = false;
        }
        return paramBoolean;
    }
    private static PrinterInfo.Model getModel(Context context) {
        usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        usbDevice = printer.getUsbDevice(usbManager);
        if (usbDevice != null)
            try {
                String str = usbDevice.getProductName();
                return PrinterInfo.Model.valueOf(str.replace("-", "_"));
            } catch (Exception exception) {
                return null;
            }
        return null;
    }

    public static PrinterInfo initPrinterInfo(Context context) {
        final PrinterInfo settings = printer.getPrinterInfo();
        settings.printerModel = getModel(context);
        settings.port = PrinterInfo.Port.USB;
        settings.enabledTethering = true;
        settings.printMode = PrinterInfo.PrintMode.FIT_TO_PAGE;
        settings.isAutoCut = true;
        settings.isSpecialTape = false;
        settings.orientation = PrinterInfo.Orientation.LANDSCAPE;
        settings.workPath = context.getCacheDir().getPath();
        settings.isCutAtEnd = true;
        settings.isHalfCut = false;
        settings.isSpecialTape = false;
        settings.labelNameIndex = 0;
        printer.setPrinterInfo(settings);
        settings.labelNameIndex = (printer.getLabelInfo()).labelNameIndex;
        printer.setPrinterInfo(settings);

        return settings;
    }

    public static void print(Context context, MipsData mipsData) {
        initPrinter(context);

        boolean b = checkUSBPermission(context, printer);
        UsbManager usbManagerz = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        UsbDevice usbDevice = printer.getUsbDevice(usbManagerz);
        boolean b1 = checkAndGrantUsbDevicePermission(context, usbManagerz, usbDevice);
        System.out.println("grant usb per" + b1);
        System.out.println("check usb permission " + b);
        PrintThread printThread = new PrintThread() {
            @Override
            public void run() {
                try {
//                    LabelInfo mLabelInfo = printer.getLabelInfo();
//                    mLabelInfo.labelNameIndex = 15;
//                    printer.setLabelInfo(mLabelInfo);
                    //printText(str);
                    printLable(new PrintModel(mipsData), context);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        printThread.start();
    }

    public static void printText(String text) throws IOException {
        final Bitmap textAsBitmap = textAsBitmap(text, 600, 35);
        printer.startCommunication();
        final PrinterStatus printImage = printer.printImage(textAsBitmap);
        printer.endCommunication();
        String string = null;
        if (printImage.errorCode != PrinterInfo.ErrorCode.ERROR_NONE) {
            string = printImage.errorCode.toString();
        }
        if (TextUtils.isEmpty((CharSequence) string)) {
            Timber.e("empty");
            return;
        }
        throw new IOException(string);
    }

    public static final float celsiusToFahrenheit(float paramFloat) {
        return (float)(new BigDecimal(String.valueOf(paramFloat * 9 / 5 + 32))).setScale(1, 4).doubleValue();
    }

    public static void printLable(PrintModel printModel, Context context) {
        boolean isPrint = false;
        final View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.view_template, (ViewGroup) null);
        ImageView imageView = view.findViewById(R.id.imgUser);
        TextView tvCompany = view.findViewById(R.id.tvCompany);
        TextView tvDateTime = view.findViewById(R.id.tvDateTime);
        TextView tvFullName = view.findViewById(R.id.tvFullName);
        TextView tvMessage = view.findViewById(R.id.tvMessage);
        TextView tvTempature = view.findViewById(R.id.tvTempature);
        PrintSettingModel settingModel = new Gson().fromJson(SharedPreferencesController.with(App.getAppContext()).getString(Keys.PRINT_SETTING_MODEL), PrintSettingModel.class);
        if (settingModel != null && settingModel.isPrintBadge()) {
            pref = PreferenceManager.getDefaultSharedPreferences(context);

            if (settingModel.isUserName()) {
                tvFullName.setText(printModel.getName());
            } else {
                tvFullName.setVisibility(View.INVISIBLE);
            }
            if (settingModel.isScanTime()) {
                String date = convertDate(printModel.getCheckTime().toString(), "EEEE, MMMM d, yyyy HH:mm:ss a");
                tvDateTime.setText(date);
            } else {
                tvDateTime.setVisibility(View.INVISIBLE);
            }
            if (settingModel.isScanImage()) {
                imageView.setImageBitmap(printModel.getBitmap());
            } else {
                imageView.setVisibility(View.INVISIBLE);
            }
                if ((printModel.getType() == -1 && settingModel.isUnregisteredVisitor())) userType = "Unregistered Visitor";
                if ((printModel.getType() == 1 && settingModel.isRegisteredVisitor())) userType = "Visitor";
                if ((printModel.getType() == 3 && settingModel.isRegisteredUser())) userType = "Registered user";
                tvCompany.setText(userType);
            if (settingModel.isTemperature()) {

                if (pref.getString("tempShowMode", new String()).equals("0")) {
                    tempEnd = "°C";
                    currentTemp = printModel.getTemperature();
                }
                else { tempEnd = "°F";
                    currentTemp = String.valueOf(celsiusToFahrenheit(Float.parseFloat(printModel.getTemperature())));
                }
                tvTempature.setText("Tempature: " + currentTemp + tempEnd);
            } else {
                tvTempature.setVisibility(View.INVISIBLE);
            }
            tvMessage.setText("THIS PASS MUST BE WORN ALL TIMES");
            Bitmap bitmap = getScreenViewBitmap(view);
            if ((printModel.getType() == -1 && settingModel.isUnregisteredVisitor())) isPrint = true;
            if ((printModel.getType() == 1 && settingModel.isRegisteredVisitor())) isPrint = true;
            if ((printModel.getType() == 3 && settingModel.isRegisteredUser())) isPrint = true;
            if (pref.getInt("isWearingMask", 0) != 1 || printModel.getMask() != 0)
                isPrint = true;
            else isPrint = false;
            if (pref.getInt("strangerMode", 0) == 0 && printModel.getType() == -1)
                isPrint = false;
            if (pref.getInt("isBodyTempAlarm", 1) == 1) {
                float f1;
                float f2;
                String str = pref.getString("standardBodyTemp", "37.3");
                try {
                    f2 = Float.parseFloat(str);
                    f1 = Float.parseFloat(printModel.getTemperature());
                } catch (Exception exception) {
                    f2 = Float.parseFloat("37.3");
                    f1 = 0.0F;
                }
                if (f1 >= f2)
                    isPrint = false;
            }

            try {
                if (isPrint){
                    printBitmap(bitmap);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    public static Bitmap textAsBitmap(final String s, final int n, final int n2) {
        final TextPaint textPaint = new TextPaint(1);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(-16777216);
        textPaint.setTextSize((float)n2);
        final StaticLayout staticLayout = new StaticLayout(s, textPaint, n, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        final Bitmap bitmap = Bitmap.createBitmap(n, staticLayout.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        final Paint paint = new Paint(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(-1);
        canvas.drawPaint(paint);
        canvas.save();
        canvas.translate(0.0f, 0.0f);
        staticLayout.draw(canvas);
        canvas.restore();
        return bitmap;
    }

    public static Bitmap getScreenViewBitmap(final View v) {
        v.setDrawingCacheEnabled(true);
        int width = 420;
        int height = 905;
        v.measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.AT_MOST));
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
        v.requestLayout();
        v.buildDrawingCache(true);
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false); // clear drawing cache
        return b;
    }

    public static void printBitmap(final Bitmap bitmap) throws IOException {
        final Bitmap textAsBitmap = bitmap;
        printer.startCommunication();
        final PrinterStatus printImage = printer.printImage(textAsBitmap);
        printer.endCommunication();
        String string = null;
        if (printImage.errorCode != PrinterInfo.ErrorCode.ERROR_NONE) {
            string = printImage.errorCode.toString();
        }
        if (TextUtils.isEmpty((CharSequence) string)) {
            Timber.e("empty");
            return;
        }
        throw new IOException(string);
    }

    public static String convertDate(String dateInMilliseconds, String dateFormat) {
        return DateFormat.format(dateFormat, Long.parseLong(dateInMilliseconds)).toString();
    }

}
