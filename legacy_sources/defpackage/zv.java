package defpackage;

import com.google.android.gms.ads.doubleclick.PublisherAdView;

@zy
/* loaded from: classes.dex */
public final class zv extends zp {
    private final dh a;
    private final PublisherAdView b;

    public zv(dh dhVar, PublisherAdView publisherAdView) {
        this.a = dhVar;
        this.b = publisherAdView;
    }

    @Override // defpackage.zo
    public void a(zl zlVar) {
        this.a.a(this.b, new zu(zlVar));
    }

    @Override // defpackage.zo
    public boolean a(String str, String str2) {
        return this.a.a(this.b, str, str2);
    }
}
