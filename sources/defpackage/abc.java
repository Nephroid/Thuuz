package defpackage;

import android.os.RemoteException;
import com.google.android.gms.internal.fi;
import com.google.android.gms.internal.fk;

@zy
/* loaded from: classes.dex */
public abstract class abc extends acq {
    private final fi a;
    private final abb b;

    public abc(fi fiVar, abb abbVar) {
        this.a = fiVar;
        this.b = abbVar;
    }

    private static fk a(abj abjVar, fi fiVar) {
        try {
            return abjVar.a(fiVar);
        } catch (RemoteException e) {
            adn.d("Could not fetch ad response from ad request service.", e);
            return null;
        } catch (NullPointerException e2) {
            adn.d("Could not fetch ad response from ad request service due to an Exception.", e2);
            return null;
        } catch (SecurityException e3) {
            adn.d("Could not fetch ad response from ad request service due to an Exception.", e3);
            return null;
        } catch (Throwable th) {
            acl.a(th);
            return null;
        }
    }

    @Override // defpackage.acq
    public final void a() {
        fk a;
        try {
            abj d = d();
            if (d == null) {
                a = new fk(0);
            } else {
                a = a(d, this.a);
                if (a == null) {
                    a = new fk(0);
                }
            }
            c();
            this.b.a(a);
        } catch (Throwable th) {
            c();
            throw th;
        }
    }

    @Override // defpackage.acq
    public final void b() {
        c();
    }

    public abstract void c();

    public abstract abj d();
}
