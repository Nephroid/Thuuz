package defpackage;

import org.json.JSONObject;

/* loaded from: classes.dex */
class sf implements Runnable {
    final /* synthetic */ JSONObject a;
    final /* synthetic */ sd b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public sf(sd sdVar, JSONObject jSONObject) {
        this.b = sdVar;
        this.a = jSONObject;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.a(this.a);
    }
}
