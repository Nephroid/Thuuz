package com.samsung.android.sdk.richnotification;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import com.samsung.android.sdk.SsdkInterface;
import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.SsdkVendorCheck;

/* loaded from: classes.dex */
public class Srn implements SsdkInterface {
    private static final int VERSION_CODE = 1;
    private static final String VERSION_NAME = "1.0.0";

    @Override // com.samsung.android.sdk.SsdkInterface
    public void initialize(Context context) {
        if (context == null) {
            throw new NullPointerException("context is null.");
        }
        if (Build.VERSION.SDK_INT < 17) {
            throw new SsdkUnsupportedException("Device is not supported", 1);
        }
        if (!SsdkVendorCheck.isSamsungDevice()) {
            throw new SsdkUnsupportedException("Vendor is not supported", 0);
        }
        try {
            insertLog(context);
        } catch (SecurityException e) {
            throw new SecurityException("com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY permission is required");
        }
    }

    @Override // com.samsung.android.sdk.SsdkInterface
    public boolean isFeatureEnabled(int i) {
        return false;
    }

    @Override // com.samsung.android.sdk.SsdkInterface
    public int getVersionCode() {
        return 1;
    }

    @Override // com.samsung.android.sdk.SsdkInterface
    public String getVersionName() {
        return VERSION_NAME;
    }

    private void insertLog(Context context) {
        int i = -1;
        try {
            i = context.getPackageManager().getPackageInfo("com.samsung.android.providers.context", 128).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("SM_SDK", "Could not find ContextProvider");
        }
        Log.d("SM_SDK", "versionCode: " + i);
        if (i > 1) {
            if (context.checkCallingOrSelfPermission("com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY") != 0) {
                throw new SecurityException();
            }
            ContentValues contentValues = new ContentValues();
            String name = getClass().getPackage().getName();
            String str = String.valueOf(context.getPackageName()) + "#" + getVersionCode();
            contentValues.put("app_id", name);
            contentValues.put("feature", str);
            Intent intent = new Intent();
            intent.setAction("com.samsung.android.providers.context.log.action.USE_APP_FEATURE_SURVEY");
            intent.putExtra("data", contentValues);
            intent.setPackage("com.samsung.android.providers.context");
            context.sendBroadcast(intent);
            return;
        }
        Log.d("SM_SDK", "Add com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY permission");
    }
}
