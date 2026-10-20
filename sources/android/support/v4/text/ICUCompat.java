package android.support.v4.text;

import android.os.Build;

/* loaded from: classes.dex */
public class ICUCompat {
    private static final ICUCompatImpl IMPL;

    /* loaded from: classes.dex */
    interface ICUCompatImpl {
        String addLikelySubtags(String str);

        String getScript(String str);
    }

    /* loaded from: classes.dex */
    class ICUCompatImplBase implements ICUCompatImpl {
        ICUCompatImplBase() {
        }

        @Override // android.support.v4.text.ICUCompat.ICUCompatImpl
        public String getScript(String str) {
            return null;
        }

        @Override // android.support.v4.text.ICUCompat.ICUCompatImpl
        public String addLikelySubtags(String str) {
            return str;
        }
    }

    /* loaded from: classes.dex */
    class ICUCompatImplIcs implements ICUCompatImpl {
        ICUCompatImplIcs() {
        }

        @Override // android.support.v4.text.ICUCompat.ICUCompatImpl
        public String getScript(String str) {
            return ICUCompatIcs.getScript(str);
        }

        @Override // android.support.v4.text.ICUCompat.ICUCompatImpl
        public String addLikelySubtags(String str) {
            return ICUCompatIcs.addLikelySubtags(str);
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 14) {
            IMPL = new ICUCompatImplIcs();
        } else {
            IMPL = new ICUCompatImplBase();
        }
    }

    public static String getScript(String str) {
        return IMPL.getScript(str);
    }

    public static String addLikelySubtags(String str) {
        return IMPL.addLikelySubtags(str);
    }
}
