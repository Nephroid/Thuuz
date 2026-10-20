package defpackage;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.gms.internal.ay;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

@zy
/* loaded from: classes.dex */
public final class adm {
    public static final Handler a = new Handler(Looper.getMainLooper());

    public static int a(Context context, int i) {
        return a(context.getResources().getDisplayMetrics(), i);
    }

    public static int a(DisplayMetrics displayMetrics, int i) {
        return (int) TypedValue.applyDimension(1, i, displayMetrics);
    }

    public static String a(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        if (string == null || a()) {
            string = "emulator";
        }
        return a(string);
    }

    public static String a(String str) {
        for (int i = 0; i < 2; i++) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(str.getBytes());
                return String.format(Locale.US, "%032X", new BigInteger(1, messageDigest.digest()));
            } catch (NoSuchAlgorithmException e) {
            }
        }
        return null;
    }

    public static void a(ViewGroup viewGroup, ay ayVar, String str) {
        a(viewGroup, ayVar, str, ViewCompat.MEASURED_STATE_MASK, -1);
    }

    private static void a(ViewGroup viewGroup, ay ayVar, String str, int i, int i2) {
        if (viewGroup.getChildCount() != 0) {
            return;
        }
        Context context = viewGroup.getContext();
        TextView textView = new TextView(context);
        textView.setGravity(17);
        textView.setText(str);
        textView.setTextColor(i);
        textView.setBackgroundColor(i2);
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setBackgroundColor(i);
        int a2 = a(context, 3);
        frameLayout.addView(textView, new FrameLayout.LayoutParams(ayVar.g - a2, ayVar.d - a2, 17));
        viewGroup.addView(frameLayout, ayVar.g, ayVar.d);
    }

    public static void a(ViewGroup viewGroup, ay ayVar, String str, String str2) {
        adn.e(str2);
        a(viewGroup, ayVar, str, -65536, ViewCompat.MEASURED_STATE_MASK);
    }

    public static boolean a() {
        return Build.DEVICE.startsWith("generic");
    }

    public static boolean b() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
