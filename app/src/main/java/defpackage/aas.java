package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.internal.fk;
import com.google.android.gms.internal.gv;
import org.apache.http.protocol.HTTP;

@zy
/* loaded from: classes.dex */
public class aas implements Runnable {
    protected final gv a;
    protected boolean b;
    protected boolean c;
    private final Handler d;
    private final long e;
    private long f;
    private ads g;
    private final int h;
    private final int i;

    public aas(ads adsVar, gv gvVar, int i, int i2) {
        this(adsVar, gvVar, i, i2, 200L, 50L);
    }

    public aas(ads adsVar, gv gvVar, int i, int i2, long j, long j2) {
        this.e = j;
        this.f = j2;
        this.d = new Handler(Looper.getMainLooper());
        this.a = gvVar;
        this.g = adsVar;
        this.b = false;
        this.c = false;
        this.h = i2;
        this.i = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ long c(aas aasVar) {
        long j = aasVar.f - 1;
        aasVar.f = j;
        return j;
    }

    public void a() {
        this.d.postDelayed(this, this.e);
    }

    public void a(fk fkVar) {
        a(fkVar, new aee(this, this.a, fkVar.q));
    }

    public void a(fk fkVar, aee aeeVar) {
        this.a.setWebViewClient(aeeVar);
        this.a.loadDataWithBaseURL(TextUtils.isEmpty(fkVar.b) ? null : acx.a(fkVar.b), fkVar.c, "text/html", HTTP.UTF_8, null);
    }

    public synchronized void b() {
        this.b = true;
    }

    public synchronized boolean c() {
        return this.b;
    }

    public boolean d() {
        return this.c;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.a == null || c()) {
            this.g.a(this.a);
        } else {
            new aat(this, this.a).execute(new Void[0]);
        }
    }
}
