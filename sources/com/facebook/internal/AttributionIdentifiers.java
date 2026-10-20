package com.facebook.internal;

import android.content.Context;
import android.net.Uri;
import android.os.Looper;
import com.facebook.FacebookException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class AttributionIdentifiers {
    private static final String ANDROID_ID_COLUMN_NAME = "androidid";
    private static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
    private static final int CONNECTION_RESULT_SUCCESS = 0;
    private static final long IDENTIFIER_REFRESH_INTERVAL_MILLIS = 3600000;
    private static final String LIMIT_TRACKING_COLUMN_NAME = "limit_tracking";
    private static AttributionIdentifiers recentlyFetchedIdentifiers;
    private String androidAdvertiserId;
    private String attributionId;
    private long fetchTime;
    private boolean limitTracking;
    private static final String TAG = AttributionIdentifiers.class.getCanonicalName();
    private static final Uri ATTRIBUTION_ID_CONTENT_URI = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");

    private static AttributionIdentifiers getAndroidId(Context context) {
        Object invokeMethodQuietly;
        AttributionIdentifiers attributionIdentifiers = new AttributionIdentifiers();
        try {
        } catch (Exception e) {
            Utility.logd("android_id", e);
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new FacebookException("getAndroidId cannot be called on the main thread.");
        }
        Method methodQuietly = Utility.getMethodQuietly("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", (Class<?>[]) new Class[]{Context.class});
        if (methodQuietly == null) {
            return attributionIdentifiers;
        }
        Object invokeMethodQuietly2 = Utility.invokeMethodQuietly(null, methodQuietly, context);
        if (!(invokeMethodQuietly2 instanceof Integer) || ((Integer) invokeMethodQuietly2).intValue() != 0) {
            return attributionIdentifiers;
        }
        Method methodQuietly2 = Utility.getMethodQuietly("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", (Class<?>[]) new Class[]{Context.class});
        if (methodQuietly2 != null && (invokeMethodQuietly = Utility.invokeMethodQuietly(null, methodQuietly2, context)) != null) {
            Method methodQuietly3 = Utility.getMethodQuietly(invokeMethodQuietly.getClass(), "getId", (Class<?>[]) new Class[0]);
            Method methodQuietly4 = Utility.getMethodQuietly(invokeMethodQuietly.getClass(), "isLimitAdTrackingEnabled", (Class<?>[]) new Class[0]);
            if (methodQuietly3 == null || methodQuietly4 == null) {
                return attributionIdentifiers;
            }
            attributionIdentifiers.androidAdvertiserId = (String) Utility.invokeMethodQuietly(invokeMethodQuietly, methodQuietly3, new Object[0]);
            attributionIdentifiers.limitTracking = ((Boolean) Utility.invokeMethodQuietly(invokeMethodQuietly, methodQuietly4, new Object[0])).booleanValue();
            return attributionIdentifiers;
        }
        return attributionIdentifiers;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00b5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.facebook.internal.AttributionIdentifiers getAttributionIdentifiers(android.content.Context r8) {
        /*
            r7 = 0
            com.facebook.internal.AttributionIdentifiers r0 = com.facebook.internal.AttributionIdentifiers.recentlyFetchedIdentifiers
            if (r0 == 0) goto L18
            long r0 = java.lang.System.currentTimeMillis()
            com.facebook.internal.AttributionIdentifiers r2 = com.facebook.internal.AttributionIdentifiers.recentlyFetchedIdentifiers
            long r2 = r2.fetchTime
            long r0 = r0 - r2
            r2 = 3600000(0x36ee80, double:1.7786363E-317)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 >= 0) goto L18
            com.facebook.internal.AttributionIdentifiers r0 = com.facebook.internal.AttributionIdentifiers.recentlyFetchedIdentifiers
        L17:
            return r0
        L18:
            com.facebook.internal.AttributionIdentifiers r6 = getAndroidId(r8)
            r0 = 3
            java.lang.String[] r2 = new java.lang.String[r0]     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> Lb1
            r0 = 0
            java.lang.String r1 = "aid"
            r2[r0] = r1     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> Lb1
            r0 = 1
            java.lang.String r1 = "androidid"
            r2[r0] = r1     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> Lb1
            r0 = 2
            java.lang.String r1 = "limit_tracking"
            r2[r0] = r1     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> Lb1
            android.content.ContentResolver r0 = r8.getContentResolver()     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> Lb1
            android.net.Uri r1 = com.facebook.internal.AttributionIdentifiers.ATTRIBUTION_ID_CONTENT_URI     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> Lb1
            r3 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5)     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> Lb1
            if (r1 == 0) goto L43
            boolean r0 = r1.moveToFirst()     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb
            if (r0 != 0) goto L4a
        L43:
            if (r1 == 0) goto L48
            r1.close()
        L48:
            r0 = r6
            goto L17
        L4a:
            java.lang.String r0 = "aid"
            int r0 = r1.getColumnIndex(r0)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb
            java.lang.String r2 = "androidid"
            int r2 = r1.getColumnIndex(r2)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb
            java.lang.String r3 = "limit_tracking"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb
            java.lang.String r0 = r1.getString(r0)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb
            r6.attributionId = r0     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb
            if (r2 <= 0) goto L7c
            if (r3 <= 0) goto L7c
            java.lang.String r0 = r6.getAndroidAdvertiserId()     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb
            if (r0 != 0) goto L7c
            java.lang.String r0 = r1.getString(r2)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb
            r6.androidAdvertiserId = r0     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb
            java.lang.String r0 = r1.getString(r3)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb
            boolean r0 = java.lang.Boolean.parseBoolean(r0)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb
            r6.limitTracking = r0     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lbb
        L7c:
            if (r1 == 0) goto L81
            r1.close()
        L81:
            long r0 = java.lang.System.currentTimeMillis()
            r6.fetchTime = r0
            com.facebook.internal.AttributionIdentifiers.recentlyFetchedIdentifiers = r6
            r0 = r6
            goto L17
        L8b:
            r0 = move-exception
            r1 = r7
        L8d:
            java.lang.String r2 = com.facebook.internal.AttributionIdentifiers.TAG     // Catch: java.lang.Throwable -> Lb9
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb9
            r3.<init>()     // Catch: java.lang.Throwable -> Lb9
            java.lang.String r4 = "Caught unexpected exception in getAttributionId(): "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch: java.lang.Throwable -> Lb9
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> Lb9
            java.lang.StringBuilder r0 = r3.append(r0)     // Catch: java.lang.Throwable -> Lb9
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> Lb9
            android.util.Log.d(r2, r0)     // Catch: java.lang.Throwable -> Lb9
            if (r1 == 0) goto Lae
            r1.close()
        Lae:
            r0 = r7
            goto L17
        Lb1:
            r0 = move-exception
            r1 = r7
        Lb3:
            if (r1 == 0) goto Lb8
            r1.close()
        Lb8:
            throw r0
        Lb9:
            r0 = move-exception
            goto Lb3
        Lbb:
            r0 = move-exception
            goto L8d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.AttributionIdentifiers.getAttributionIdentifiers(android.content.Context):com.facebook.internal.AttributionIdentifiers");
    }

    public String getAttributionId() {
        return this.attributionId;
    }

    public String getAndroidAdvertiserId() {
        return this.androidAdvertiserId;
    }

    public boolean isTrackingLimited() {
        return this.limitTracking;
    }
}
