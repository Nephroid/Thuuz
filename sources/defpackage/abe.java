package defpackage;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.internal.fi;
import com.google.android.gms.internal.fk;

@zy
/* loaded from: classes.dex */
public final class abe extends abc implements gz, ha {
    private final abb a;
    private final abf b;
    private final Object c;

    public abe(Context context, fi fiVar, abb abbVar) {
        super(fiVar, abbVar);
        this.c = new Object();
        this.a = abbVar;
        this.b = new abf(context, this, this, fiVar.k.d);
        this.b.a();
    }

    @Override // defpackage.gz
    public void C_() {
        adn.a("Disconnected from remote ad request service.");
    }

    @Override // defpackage.gz
    public void a(Bundle bundle) {
        e();
    }

    @Override // defpackage.ha
    public void a(gw gwVar) {
        this.a.a(new fk(0));
    }

    @Override // defpackage.abc
    public void c() {
        synchronized (this.c) {
            if (this.b.c() || this.b.h()) {
                this.b.b();
            }
        }
    }

    @Override // defpackage.abc
    public abj d() {
        abj abjVar;
        synchronized (this.c) {
            try {
                abjVar = this.b.l();
            } catch (IllegalStateException e) {
                abjVar = null;
            }
        }
        return abjVar;
    }
}
