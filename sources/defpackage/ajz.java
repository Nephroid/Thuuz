package defpackage;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.internal.gv;

@zy
/* loaded from: classes.dex */
public class ajz implements ajy {
    private final ach a;
    private final gv b;

    public ajz(ach achVar, gv gvVar) {
        this.a = achVar;
        this.b = gvVar;
    }

    @Override // defpackage.ajy
    public void a(String str) {
        adn.a("An auto-clicking creative is blocked");
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        builder.path("//pagead2.googlesyndication.com/pagead/gen_204");
        builder.appendQueryParameter("id", "gmob-apps-blocked-navigation");
        if (!TextUtils.isEmpty(str)) {
            builder.appendQueryParameter("navigationURL", str);
        }
        if (this.a != null && this.a.b != null && !TextUtils.isEmpty(this.a.b.o)) {
            builder.appendQueryParameter("debugDialog", this.a.b.o);
        }
        acx.a(this.b.getContext(), this.b.i().b, builder.toString());
    }
}
