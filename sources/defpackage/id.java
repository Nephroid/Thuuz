package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.Loader;

/* loaded from: classes.dex */
class id extends Loader<gw> implements ho, hp {
    public final hm a;
    private boolean b;
    private gw c;

    public id(Context context, hm hmVar) {
        super(context);
        this.a = hmVar;
    }

    private void b(gw gwVar) {
        this.c = gwVar;
        if (!isStarted() || isAbandoned()) {
            return;
        }
        deliverResult(gwVar);
    }

    public void a() {
        if (this.b) {
            this.b = false;
            if (!isStarted() || isAbandoned()) {
                return;
            }
            this.a.a();
        }
    }

    @Override // defpackage.ho
    public void a(int i) {
    }

    @Override // defpackage.ho
    public void a(Bundle bundle) {
        this.b = false;
        b(gw.a);
    }

    @Override // defpackage.hp, defpackage.ha
    public void a(gw gwVar) {
        this.b = true;
        b(gwVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.content.Loader
    public void onReset() {
        this.c = null;
        this.b = false;
        this.a.b((ho) this);
        this.a.b((hp) this);
        this.a.b();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.content.Loader
    public void onStartLoading() {
        super.onStartLoading();
        this.a.a((ho) this);
        this.a.a((hp) this);
        if (this.c != null) {
            deliverResult(this.c);
        }
        if (this.a.c() || this.a.d() || this.b) {
            return;
        }
        this.a.a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.content.Loader
    public void onStopLoading() {
        this.a.b();
    }
}
