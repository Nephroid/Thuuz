package com.flurry.sdk;

import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.text.TextUtils;
import java.io.Closeable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.protocol.HTTP;

/* loaded from: classes.dex */
public final class fh {
    private static final String a = fh.class.getSimpleName();

    public static String a(String str) {
        return a(str, MotionEventCompat.ACTION_MASK);
    }

    public static String a(String str, int i) {
        if (str == null) {
            return "";
        }
        return str.length() > i ? str.substring(0, i) : str;
    }

    public static String b(String str) {
        try {
            return URLEncoder.encode(str, HTTP.UTF_8);
        } catch (UnsupportedEncodingException e) {
            ex.a(5, a, "Cannot encode '" + str + "'");
            return "";
        }
    }

    public static String c(String str) {
        try {
            return URLDecoder.decode(str, HTTP.UTF_8);
        } catch (UnsupportedEncodingException e) {
            ex.a(5, a, "Cannot decode '" + str + "'");
            return "";
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
            }
        }
    }

    public static byte[] d(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(str.getBytes(), 0, str.length());
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            ex.a(6, a, "Unsupported SHA1: " + e.getMessage());
            return null;
        }
    }

    public static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        for (byte b : bArr) {
            sb.append(cArr[(byte) ((b & 240) >> 4)]);
            sb.append(cArr[(byte) (b & 15)]);
        }
        return sb.toString();
    }

    public static boolean a(long j) {
        if (j != 0 && System.currentTimeMillis() > j) {
            return false;
        }
        return true;
    }

    public static boolean a(Intent intent) {
        return eg.a().c().queryIntentActivities(intent, 65536).size() > 0;
    }

    public static boolean b(Intent intent) {
        if (intent == null) {
            return false;
        }
        return eg.a().b().getPackageName().equals(intent.resolveActivity(eg.a().c()).getPackageName());
    }

    public static String e(String str) {
        return str.replace("'", "\\'").replace("\\n", "").replace("\\r", "").replace("\\t", "");
    }

    public static Map<String, String> f(String str) {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            for (String str2 : str.split("&")) {
                String[] split = str2.split("=");
                if (!split[0].equals("event")) {
                    hashMap.put(c(split[0]), c(split[1]));
                }
            }
        }
        return hashMap;
    }
}
