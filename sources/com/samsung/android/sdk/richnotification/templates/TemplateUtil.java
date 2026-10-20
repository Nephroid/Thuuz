package com.samsung.android.sdk.richnotification.templates;

import android.graphics.Bitmap;
import android.util.Log;
import com.samsung.android.sdk.richnotification.SrnImageAsset;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class TemplateUtil {
    private static final String TAG = TemplateUtil.class.getName();
    private static Method sSetCompressFormatMethod;

    TemplateUtil() {
    }

    static {
        try {
            sSetCompressFormatMethod = SrnImageAsset.class.getDeclaredMethod("setCompressFormat", Bitmap.CompressFormat.class);
            sSetCompressFormatMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setCompressFormat(SrnImageAsset srnImageAsset, Bitmap.CompressFormat compressFormat) {
        try {
            sSetCompressFormatMethod.invoke(srnImageAsset, compressFormat);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
}
