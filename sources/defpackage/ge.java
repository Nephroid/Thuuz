package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.analytics.t;
import com.google.android.gms.internal.hb;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
class ge implements Runnable {
    final /* synthetic */ Map a;
    final /* synthetic */ gd b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ge(gd gdVar, Map map) {
        this.b = gdVar;
        this.a = map;
    }

    @Override // java.lang.Runnable
    public void run() {
        Context context;
        boolean c;
        String str;
        et etVar;
        String b;
        List<hb> list;
        String str2;
        this.b.d((Map<String, String>) this.a);
        if (TextUtils.isEmpty((CharSequence) this.a.get("&cid"))) {
            this.a.put("&cid", fj.a().a("&cid"));
        }
        context = this.b.h;
        if (dz.a(context).c()) {
            return;
        }
        c = this.b.c((Map<String, String>) this.a);
        if (c) {
            return;
        }
        str = this.b.e;
        if (!TextUtils.isEmpty(str)) {
            t.a().a(true);
            Map map = this.a;
            eg egVar = new eg();
            str2 = this.b.e;
            map.putAll(egVar.d(str2).a());
            t.a().a(false);
            this.b.e = null;
        }
        this.b.e(this.a);
        Map<String, String> a = gl.a((Map<String, String>) this.a);
        etVar = this.b.g;
        long longValue = Long.valueOf((String) this.a.get("&ht")).longValue();
        b = this.b.b((Map<String, String>) this.a);
        list = this.b.d;
        etVar.a(a, longValue, b, list);
    }
}
