package defpackage;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;

/* loaded from: classes.dex */
public class ahq extends iz<ahl> {
    private final ahw<ahl> d;
    private final aho e;
    private final aii f;
    private final ahd g;
    private final aet h;
    private final String i;

    public ahq(Context context, Looper looper, String str, ho hoVar, hp hpVar, String str2, String str3) {
        this(context, looper, str, hoVar, hpVar, str2, str3, null);
    }

    public ahq(Context context, Looper looper, String str, ho hoVar, hp hpVar, String str2, String str3, String str4) {
        super(context, looper, hoVar, hpVar, new String[0]);
        this.d = new ahs(this);
        this.e = new aho(context, this.d);
        this.i = str2;
        this.f = new aii(str, this.d, str3);
        this.g = ahd.a(context, str3, str4, this.d);
        this.h = aet.a(context, this.d);
    }

    @Override // defpackage.iz
    protected void a(js jsVar, jd jdVar) {
        Bundle bundle = new Bundle();
        bundle.putString("client_name", this.i);
        jsVar.e(jdVar, 6111000, i().getPackageName(), bundle);
    }

    @Override // defpackage.iz, defpackage.hi
    public void b() {
        synchronized (this.e) {
            if (c()) {
                this.e.b();
                this.e.c();
            }
            super.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.iz
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public ahl a(IBinder iBinder) {
        return ahm.a(iBinder);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.iz
    public String d() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.iz
    public String e() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }

    public Location l() {
        return this.e.a();
    }
}
