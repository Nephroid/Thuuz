package defpackage;

import com.android.volley.Request;

/* loaded from: classes.dex */
public class af implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ long b;
    final /* synthetic */ Request c;

    public af(Request request, String str, long j) {
        this.c = request;
        this.a = str;
        this.b = j;
    }

    @Override // java.lang.Runnable
    public void run() {
        an anVar;
        an anVar2;
        anVar = this.c.a;
        anVar.a(this.a, this.b);
        anVar2 = this.c.a;
        anVar2.a(toString());
    }
}
