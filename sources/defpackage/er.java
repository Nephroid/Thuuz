package defpackage;

import android.content.Context;
import android.util.DisplayMetrics;

/* loaded from: classes.dex */
class er implements fp {
    private static er a;
    private static Object b = new Object();
    private final Context c;

    protected er(Context context) {
        this.c = context;
    }

    public static er a() {
        er erVar;
        synchronized (b) {
            erVar = a;
        }
        return erVar;
    }

    public static void a(Context context) {
        synchronized (b) {
            if (a == null) {
                a = new er(context);
            }
        }
    }

    @Override // defpackage.fp
    public String a(String str) {
        if (str != null && str.equals("&sr")) {
            return b();
        }
        return null;
    }

    protected String b() {
        DisplayMetrics displayMetrics = this.c.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels + "x" + displayMetrics.heightPixels;
    }
}
