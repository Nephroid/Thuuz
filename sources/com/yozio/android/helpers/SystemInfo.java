package com.yozio.android.helpers;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.yozio.android.Constants;
import com.yozio.android.YozioService;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: classes.dex */
public class SystemInfo {
    private static SystemInfo _instance = new SystemInfo();
    private Calendar _calendar;
    private HashMap<String, String> _systemDictionary;

    public static SystemInfo getInstance() {
        return _instance;
    }

    private SystemInfo() {
    }

    public HashMap<String, String> getSystemDictionary() {
        if (this._systemDictionary != null) {
            YozioService.log(Constants.LOG_LEVEL.INFO, "_systemDictionary already initialized");
            return this._systemDictionary;
        }
        YozioService.log(Constants.LOG_LEVEL.INFO, "initializing new _systemDictionary");
        this._systemDictionary = new HashMap<>();
        this._systemDictionary.put("fingerprint", Build.FINGERPRINT);
        this._systemDictionary.put("product", Build.PRODUCT);
        this._systemDictionary.put("model", Build.MODEL);
        this._systemDictionary.put("board", Build.BOARD);
        this._systemDictionary.put("brand", Build.BRAND);
        this._systemDictionary.put("device", Build.DEVICE);
        this._systemDictionary.put(ServerProtocol.DIALOG_PARAM_DISPLAY, Build.DISPLAY);
        this._systemDictionary.put("time", String.valueOf(Build.TIME));
        this._systemDictionary.put("cpu_abi", Build.CPU_ABI);
        this._systemDictionary.put("cpu_abi2", Build.CPU_ABI2);
        this._systemDictionary.put("manufacturer", Build.MANUFACTURER);
        this._systemDictionary.put("hardware", Build.MODEL);
        this._systemDictionary.put("os", "Android");
        this._systemDictionary.put("os_version", Build.VERSION.RELEASE);
        this._systemDictionary.put("country", Locale.getDefault().getCountry());
        this._systemDictionary.put("language", Locale.getDefault().getLanguage());
        PackageInfo packageInfo = getPackageInfo();
        if (packageInfo != null) {
            this._systemDictionary.put(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING, packageInfo.packageName);
            this._systemDictionary.put("app_version", packageInfo.versionName);
        }
        this._systemDictionary.put("time_zone_abbr", TimeZone.getDefault().getDisplayName(false, 0, Locale.getDefault()));
        this._systemDictionary.put("android_id", Settings.Secure.getString(YozioService.getInstance().getContext().getContentResolver(), "android_id"));
        this._systemDictionary.put("yozio_udid", getYozioUDID());
        try {
            String macAddress = getMacAddress();
            if (macAddress != null) {
                this._systemDictionary.put("mac_address", macAddress);
            }
        } catch (SecurityException e) {
            YozioService.log(Constants.LOG_LEVEL.WARN, e.getMessage());
            if (e.getMessage().indexOf("ACCESS_WIFI_STATE") > 0) {
                this._systemDictionary.put("mac_address", "N/A");
            }
        }
        this._systemDictionary.put("yozio_sdk_version", Constants.YOZIO_SDK_VERSION);
        YozioService.log(Constants.LOG_LEVEL.INFO, "_systemDictionary: " + this._systemDictionary.toString());
        return this._systemDictionary;
    }

    public PackageInfo getPackageInfo() {
        try {
            return YozioService.getInstance().getContext().getPackageManager().getPackageInfo(YozioService.getInstance().getContext().getPackageName(), 0);
        } catch (Exception e) {
            YozioService.log(Constants.LOG_LEVEL.ERROR, "failed to get PackageInfo!");
            return null;
        }
    }

    public String getMacAddress() {
        WifiInfo connectionInfo;
        WifiManager wifiManager = (WifiManager) YozioService.getInstance().getContext().getSystemService("wifi");
        if (wifiManager == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) {
            return null;
        }
        return connectionInfo.getMacAddress();
    }

    public long getCurrentTimeLong() {
        if (this._calendar == null) {
            this._calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        }
        return this._calendar.getTimeInMillis();
    }

    public String getYozioUDID() {
        String string = Settings.Secure.getString(YozioService.getInstance().getContext().getContentResolver(), "android_id");
        if (string == null || string.equals("9774d56d682e549c") || string.length() < 15) {
            SharedPreferences sharedPreferences = YozioService.getInstance().getContext().getSharedPreferences(Constants.YOZIO_SHARED_PREFERENCE_NAME, 0);
            String string2 = sharedPreferences.getString(Constants.YOZIO_SHARED_PREFERENCE_KEY_YOZIO_UDID, null);
            if (string2 == null) {
                String bigInteger = new BigInteger(64, new SecureRandom()).toString(16);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString(Constants.YOZIO_SHARED_PREFERENCE_KEY_YOZIO_UDID, bigInteger);
                edit.commit();
                return bigInteger;
            }
            return string2;
        }
        return string;
    }

    public String getUserAgent() {
        PackageInfo packageInfo = getPackageInfo();
        return packageInfo != null ? String.valueOf(packageInfo.packageName) + ' ' + packageInfo.versionName : "Yozio Android SDK";
    }

    public boolean canSendSMS() {
        return YozioService.getInstance().getContext().getPackageManager().hasSystemFeature("android.hardware.telephony");
    }
}
