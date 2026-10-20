package defpackage;

import android.content.Context;
import com.google.android.gms.internal.gt;

/* loaded from: classes.dex */
class sp implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ gt b;
    final /* synthetic */ ada c;
    final /* synthetic */ String d;
    final /* synthetic */ so e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public sp(so soVar, Context context, gt gtVar, ada adaVar, String str) {
        this.e = soVar;
        this.a = context;
        this.b = gtVar;
        this.c = adaVar;
        this.d = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.e.a(this.a, this.b, this.c).a(this.d);
    }
}
