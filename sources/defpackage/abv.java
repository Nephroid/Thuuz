package defpackage;

import android.content.Context;
import com.google.android.gms.internal.ay;
import com.google.android.gms.internal.fi;
import com.google.android.gms.internal.gv;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class abv implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ fi b;
    final /* synthetic */ aby c;
    final /* synthetic */ ads d;
    final /* synthetic */ String e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abv(Context context, fi fiVar, aby abyVar, ads adsVar, String str) {
        this.a = context;
        this.b = fiVar;
        this.c = abyVar;
        this.d = adsVar;
        this.e = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        gv a = gv.a(this.a, new ay(), false, false, null, this.b.k);
        a.setWillNotDraw(true);
        this.c.a(a);
        adq f = a.f();
        f.a("/invalidRequest", this.c.a);
        f.a("/loadAdURL", this.c.b);
        f.a("/log", ux.g);
        f.a(this.d);
        adn.a("Loading the JS library.");
        a.loadUrl(this.e);
    }
}
