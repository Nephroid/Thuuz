package defpackage;

import com.google.android.gms.analytics.t;
import java.util.Map;

/* loaded from: classes.dex */
public class ee extends eg<ee> {
    public ee() {
        t.a().a(t.a.CONSTRUCT_EVENT);
        a("&t", "event");
    }

    public ee a(long j) {
        a("&ev", Long.toString(j));
        return this;
    }

    public ee a(String str) {
        a("&ec", str);
        return this;
    }

    @Override // defpackage.eg
    public /* bridge */ /* synthetic */ Map a() {
        return super.a();
    }

    public ee b(String str) {
        a("&ea", str);
        return this;
    }

    public ee c(String str) {
        a("&el", str);
        return this;
    }
}
