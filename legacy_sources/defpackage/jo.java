package defpackage;

import android.util.Log;

/* loaded from: classes.dex */
public final class jo {
    private final String a;

    public jo(String str) {
        this.a = (String) kb.a(str);
    }

    public void a(String str, String str2) {
        if (a(5)) {
            Log.w(str, str2);
        }
    }

    public boolean a(int i) {
        return Log.isLoggable(this.a, i);
    }
}
