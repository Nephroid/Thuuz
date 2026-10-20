package defpackage;

import android.content.ComponentCallbacks;
import android.content.res.Configuration;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ajs implements ComponentCallbacks {
    final /* synthetic */ ajr a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ajs(ajr ajrVar) {
        this.a = ajrVar;
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        ajw ajwVar;
        ajw ajwVar2;
        ajw ajwVar3;
        ajw ajwVar4;
        ajwVar = this.a.c;
        if (ajwVar != null) {
            ajwVar2 = this.a.c;
            if (ajwVar2.j != null) {
                ajwVar3 = this.a.c;
                if (ajwVar3.j.b != null) {
                    ajwVar4 = this.a.c;
                    ajwVar4.j.b.a();
                }
            }
        }
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
    }
}
