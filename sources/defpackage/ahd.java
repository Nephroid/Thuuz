package defpackage;

import android.content.Context;

/* loaded from: classes.dex */
public class ahd {
    private final Context a;
    private final String b;
    private final ahw<ahl> c;
    private final String d;
    private ahe e = null;

    private ahd(Context context, String str, String str2, ahw<ahl> ahwVar) {
        this.a = context;
        this.b = str;
        this.c = ahwVar;
        this.d = str2;
    }

    public static ahd a(Context context, String str, String str2, ahw<ahl> ahwVar) {
        return new ahd(context, str, str2, ahwVar);
    }
}
