package com.poa.tempscanner.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Base64;
import android.view.View;

import java.io.ByteArrayOutputStream;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

public class ImageUtil {
    public static final Bitmap badgeToBitmap(View paramView) {
        Bitmap bitmap;
        Intrinsics.checkParameterIsNotNull(paramView, "view");
        if (paramView.getLayoutParams() != null && (paramView.getLayoutParams()).width > 0 && (paramView.getLayoutParams()).height > 0) {
            bitmap = Bitmap.createBitmap((paramView.getLayoutParams()).width, (paramView.getLayoutParams()).height, Bitmap.Config.ARGB_8888);
        } else if (paramView.getWidth() > 0 && paramView.getHeight() > 0) {
            bitmap = Bitmap.createBitmap(paramView.getWidth(), paramView.getHeight(), Bitmap.Config.ARGB_8888);
        } else {
            bitmap = Bitmap.createBitmap(paramView.getMeasuredWidth(), paramView.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        }
        paramView.draw(new Canvas(bitmap));
        if (bitmap != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(String.valueOf(bitmap.getWidth()));
            stringBuilder.append(" : ");
            stringBuilder.append(bitmap.getHeight());
        }
        Intrinsics.checkExpressionValueIsNotNull(bitmap, "bitmap");
        return rotateBitmap(bitmap, 90.0F);
    }

    public static final String bitmapToBase64(Bitmap paramBitmap) {
        Intrinsics.checkParameterIsNotNull(paramBitmap, "bitmap");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        paramBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
    }

    public static final Bitmap convertBase64ToBitmap(String paramString) {
        Intrinsics.checkParameterIsNotNull(paramString, "b64");
        byte[] arrayOfByte = paramString.getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(arrayOfByte, "(this as java.lang.String).getBytes(charset)");
        arrayOfByte = Base64.decode(arrayOfByte, 0);
        return BitmapFactory.decodeByteArray(arrayOfByte, 0, arrayOfByte.length);
    }

    public static final Bitmap rotateBitmap(Bitmap paramBitmap, float paramFloat) {
        Intrinsics.checkParameterIsNotNull(paramBitmap, "source");
        Matrix matrix = new Matrix();
        matrix.postRotate(paramFloat);
        return Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), matrix, true);
    }
}
