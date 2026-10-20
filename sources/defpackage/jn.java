package defpackage;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.accessibility.AccessibilityEventCompat;

/* loaded from: classes.dex */
public class jn {
    private static final Uri a = Uri.parse("http://plus.google.com/");
    private static final Uri b = a.buildUpon().appendPath("circles").appendPath("find").build();

    public static Intent a() {
        Intent intent = new Intent("com.google.android.clockwork.home.UPDATE_ANDROID_WEAR_ACTION");
        intent.setPackage("com.google.android.wearable.app");
        return intent;
    }

    public static Intent a(String str) {
        Uri fromParts = Uri.fromParts("package", str, null);
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(fromParts);
        return intent;
    }

    public static Intent b(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(c(str));
        intent.setPackage("com.android.vending");
        intent.addFlags(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END);
        return intent;
    }

    private static Uri c(String str) {
        return Uri.parse("market://details").buildUpon().appendQueryParameter("id", str).build();
    }
}
