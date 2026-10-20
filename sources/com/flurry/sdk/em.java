package com.flurry.sdk;

import android.telephony.TelephonyManager;
import java.util.Arrays;

/* loaded from: classes.dex */
public class em {
    private static final String a = em.class.getSimpleName();
    private static byte[] b;

    public static byte[] a() {
        if (b != null) {
            return b;
        }
        if (eg.a().b().checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
            return null;
        }
        b();
        return b;
    }

    private static void b() {
        String deviceId;
        TelephonyManager telephonyManager = (TelephonyManager) eg.a().b().getSystemService("phone");
        if (telephonyManager != null && (deviceId = telephonyManager.getDeviceId()) != null && deviceId.trim().length() > 0) {
            try {
                byte[] d = fh.d(deviceId);
                if (d != null && d.length == 20) {
                    b = d;
                } else {
                    ex.a(6, a, "sha1 is not 20 bytes long: " + Arrays.toString(d));
                }
            } catch (Exception e) {
                ex.a(6, a, "Exception in generateHashedImei()");
            }
        }
    }
}
