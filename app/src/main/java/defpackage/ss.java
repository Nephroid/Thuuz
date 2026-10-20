package defpackage;

import com.google.android.gms.internal.gv;
import org.json.JSONObject;

/* loaded from: classes.dex */
class ss implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ JSONObject b;
    final /* synthetic */ sr c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ss(sr srVar, String str, JSONObject jSONObject) {
        this.c = srVar;
        this.a = str;
        this.b = jSONObject;
    }

    @Override // java.lang.Runnable
    public void run() {
        gv gvVar;
        gvVar = this.c.a;
        gvVar.a(this.a, this.b);
    }
}
