package defpackage;

import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.Map;

/* loaded from: classes.dex */
public class bv implements Runnable {
    Map<String, String> a;
    boolean b;
    private String c;
    private WeakReference<Context> d;

    public /* synthetic */ bv(String str, Map map, Context context, boolean z, bq bqVar) {
        this(str, map, context, z);
    }

    private bv(String str, Map<String, String> map, Context context, boolean z) {
        this.d = null;
        this.c = str;
        this.a = map;
        this.d = new WeakReference<>(context);
        this.b = z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x00dd, code lost:
    
        if (r8 != null) goto L36;
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            Method dump skipped, instructions count: 293
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bv.run():void");
    }
}
