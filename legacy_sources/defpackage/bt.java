package defpackage;

import android.content.Context;
// [removed legacy tracker import]
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
public class bt implements Runnable {
    private Context a;
    private String b;
    private String c;
    private String d;
    private String e;
    private ExecutorService f;
    private boolean g;

    public /* synthetic */ bt(Context context, String str, String str2, String str3, String str4, boolean z, ExecutorService executorService, bq bqVar) {
        this(context, str, str2, str3, str4, z, executorService);
    }

    private bt(Context context, String str, String str2, String str3, String str4, boolean z, ExecutorService executorService) {
        this.a = context;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.g = z;
        this.f = executorService;
    }

    @Override // java.lang.Runnable
    public void run() {
        AppsFlyerLib.a(this.a, this.b, this.c, this.d, this.e, this.g);
        this.f.shutdown();
    }
}
