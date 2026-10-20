package defpackage;

import com.google.android.gms.internal.gv;

/* loaded from: classes.dex */
final class abw implements ads {
    final /* synthetic */ String a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abw(String str) {
        this.a = str;
    }

    @Override // defpackage.ads
    public void a(gv gvVar) {
        String format = String.format("javascript:%s(%s);", "AFMA_buildAdURL", this.a);
        adn.d("About to execute: " + format);
        gvVar.loadUrl(format);
    }
}
