package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;

@zy
/* loaded from: classes.dex */
public class abf extends iz<abj> {
    final int d;

    public abf(Context context, gz gzVar, ha haVar, int i) {
        super(context, gzVar, haVar, new String[0]);
        this.d = i;
    }

    @Override // defpackage.iz
    protected void a(js jsVar, jd jdVar) {
        jsVar.g(jdVar, this.d, i().getPackageName(), new Bundle());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.iz
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public abj a(IBinder iBinder) {
        return abk.a(iBinder);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.iz
    public String d() {
        return "com.google.android.gms.ads.service.START";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.iz
    public String e() {
        return "com.google.android.gms.ads.internal.request.IAdRequestService";
    }

    public abj l() {
        return (abj) super.k();
    }
}
