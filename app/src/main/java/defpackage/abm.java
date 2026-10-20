package defpackage;

import android.content.Context;
import com.google.android.gms.internal.fk;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zy
/* loaded from: classes.dex */
public class abm extends acq {
    private final aav a;
    private final fk b;
    private final ach c;
    private final abo d;
    private final Object e;
    private Future<acg> f;

    abm(ach achVar, aav aavVar, abo aboVar) {
        this.e = new Object();
        this.c = achVar;
        this.b = achVar.b;
        this.a = aavVar;
        this.d = aboVar;
    }

    public abm(Context context, ajr ajrVar, so soVar, ach achVar, aav aavVar) {
        this(achVar, aavVar, new abo(context, ajrVar, soVar, new adg(), achVar));
    }

    private acg a(int i) {
        return new acg(this.c.a.c, null, null, i, null, null, this.b.l, this.b.k, this.c.a.i, false, null, null, null, null, null, this.b.i, this.c.d, this.b.g, this.c.f, this.b.n, this.b.o, this.c.h, null);
    }

    @Override // defpackage.acq
    public void a() {
        int i;
        acg acgVar;
        try {
            synchronized (this.e) {
                this.f = act.a(this.d);
            }
            acgVar = this.f.get(60000L, TimeUnit.MILLISECONDS);
            i = -2;
        } catch (InterruptedException e) {
            acgVar = null;
            i = -1;
        } catch (CancellationException e2) {
            acgVar = null;
            i = -1;
        } catch (ExecutionException e3) {
            i = 0;
            acgVar = null;
        } catch (TimeoutException e4) {
            adn.e("Timed out waiting for native ad.");
            i = 2;
            acgVar = null;
        }
        if (acgVar == null) {
            acgVar = a(i);
        }
        adm.a.post(new abn(this, acgVar));
    }

    @Override // defpackage.acq
    public void b() {
        synchronized (this.e) {
            if (this.f != null) {
                this.f.cancel(true);
            }
        }
    }
}
