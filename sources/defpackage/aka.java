package defpackage;

import android.content.Context;
import android.view.MotionEvent;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

@zy
/* loaded from: classes.dex */
class aka implements aci, Runnable {
    private ajw d;
    private final List<Object[]> b = new Vector();
    private final AtomicReference<aci> c = new AtomicReference<>();
    CountDownLatch a = new CountDownLatch(1);

    public aka(ajw ajwVar) {
        this.d = ajwVar;
        if (adm.b()) {
            act.a(this);
        } else {
            run();
        }
    }

    private void b() {
        if (this.b.isEmpty()) {
            return;
        }
        for (Object[] objArr : this.b) {
            if (objArr.length == 1) {
                this.c.get().a((MotionEvent) objArr[0]);
            } else if (objArr.length == 3) {
                this.c.get().a(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue(), ((Integer) objArr[2]).intValue());
            }
        }
    }

    @Override // defpackage.aci
    public String a(Context context) {
        a();
        aci aciVar = this.c.get();
        if (aciVar == null) {
            return "";
        }
        b();
        return aciVar.a(context);
    }

    @Override // defpackage.aci
    public String a(Context context, String str) {
        a();
        aci aciVar = this.c.get();
        if (aciVar == null) {
            return "";
        }
        b();
        return aciVar.a(context, str);
    }

    protected void a() {
        try {
            this.a.await();
        } catch (InterruptedException e) {
            adn.d("Interrupted during GADSignals creation.", e);
        }
    }

    @Override // defpackage.aci
    public void a(int i, int i2, int i3) {
        aci aciVar = this.c.get();
        if (aciVar == null) {
            this.b.add(new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
        } else {
            b();
            aciVar.a(i, i2, i3);
        }
    }

    protected void a(aci aciVar) {
        this.c.set(aciVar);
    }

    @Override // defpackage.aci
    public void a(MotionEvent motionEvent) {
        aci aciVar = this.c.get();
        if (aciVar == null) {
            this.b.add(new Object[]{motionEvent});
        } else {
            b();
            aciVar.a(motionEvent);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            a(afh.a(this.d.e.b, this.d.c));
        } finally {
            this.a.countDown();
            this.d = null;
        }
    }
}
