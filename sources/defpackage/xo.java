package defpackage;

import android.view.ViewGroup;
import android.view.ViewParent;
import com.google.android.gms.internal.dk;
import com.google.android.gms.internal.gv;

@zy
/* loaded from: classes.dex */
public final class xo {
    public final int a;
    public final ViewGroup.LayoutParams b;
    public final ViewGroup c;

    public xo(gv gvVar) {
        this.b = gvVar.getLayoutParams();
        ViewParent parent = gvVar.getParent();
        if (!(parent instanceof ViewGroup)) {
            throw new dk.a("Could not get the parent of the WebView for an overlay.");
        }
        this.c = (ViewGroup) parent;
        this.a = this.c.indexOfChild(gvVar);
        this.c.removeView(gvVar);
        gvVar.a(true);
    }
}
