package defpackage;

import android.os.Bundle;
import android.os.IBinder;

/* loaded from: classes.dex */
public final class jd extends jq {
    private iz a;

    public jd(iz izVar) {
        this.a = izVar;
    }

    @Override // defpackage.jp
    public void a(int i, IBinder iBinder, Bundle bundle) {
        kb.a("onPostInitComplete can be called only once per call to getServiceFromBroker", this.a);
        this.a.a(i, iBinder, bundle);
        this.a = null;
    }
}
