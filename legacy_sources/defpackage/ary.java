package defpackage;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ary implements ComponentCallbacks2 {
    final /* synthetic */ arv a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ary(arv arvVar) {
        this.a = arvVar;
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        if (i == 20) {
            this.a.a();
        }
    }
}
