package defpackage;

import android.view.View;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class sz implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ sy b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public sz(sy syVar, View view) {
        this.b = syVar;
        this.a = view;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.b(this.a);
    }
}
