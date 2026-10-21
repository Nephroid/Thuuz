package defpackage;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

/* loaded from: classes.dex */
public final class jg extends iz<T>.jb<Boolean> {
    public final int b;
    public final Bundle c;
    public final IBinder d;
    final /* synthetic */ iz e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public jg(iz izVar, int i, IBinder iBinder, Bundle bundle) {
        super(izVar, true);
        this.e = izVar;
        this.b = i;
        this.d = iBinder;
        this.c = bundle;
    }

    protected void a() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Boolean bool) {
        Context context;
        je jeVar;
        jh jhVar;
        IInterface iInterface;
        jh jhVar2;
        je jeVar2;
        jh jhVar3;
        Context context2;
        je jeVar3;
        if (bool == null) {
            this.e.a(1);
            return;
        }
        switch (this.b) {
            case 0:
                try {
                    if (this.e.e().equals(this.d.getInterfaceDescriptor())) {
                        this.e.f = this.e.a(this.d);
                        iInterface = this.e.f;
                        if (iInterface != null) {
                            this.e.a(3);
                            jhVar2 = this.e.k;
                            jhVar2.a();
                            return;
                        }
                    }
                } catch (RemoteException e) {
                }
                context = this.e.d;
                jk a = jk.a(context);
                String d = this.e.d();
                jeVar = this.e.h;
                a.b(d, jeVar);
                this.e.h = null;
                this.e.a(1);
                this.e.f = null;
                jhVar = this.e.k;
                jhVar.a(new gw(8, null));
                return;
            case 10:
                this.e.a(1);
                throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
            default:
                PendingIntent pendingIntent = this.c != null ? (PendingIntent) this.c.getParcelable("pendingIntent") : null;
                jeVar2 = this.e.h;
                if (jeVar2 != null) {
                    context2 = this.e.d;
                    jk a2 = jk.a(context2);
                    String d2 = this.e.d();
                    jeVar3 = this.e.h;
                    a2.b(d2, jeVar3);
                    this.e.h = null;
                }
                this.e.a(1);
                this.e.f = null;
                jhVar3 = this.e.k;
                jhVar3.a(new gw(this.b, pendingIntent));
                return;
        }
    }
}
