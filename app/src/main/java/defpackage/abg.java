package defpackage;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import com.google.android.gms.internal.av;
import com.google.android.gms.internal.ay;
import com.google.android.gms.internal.gt;
import java.util.List;

@zy
/* loaded from: classes.dex */
public final class abg {
    public final Bundle a;
    public final av b;
    public final ay c;
    public final String d;
    public final ApplicationInfo e;
    public final PackageInfo f;
    public final String g;
    public final String h;
    public final Bundle i;
    public final gt j;
    public final int k;
    public final List<String> l;
    public final Bundle m;
    public final boolean n;

    public abg(Bundle bundle, av avVar, ay ayVar, String str, ApplicationInfo applicationInfo, PackageInfo packageInfo, String str2, String str3, gt gtVar, Bundle bundle2, List<String> list, Bundle bundle3, boolean z) {
        this.a = bundle;
        this.b = avVar;
        this.c = ayVar;
        this.d = str;
        this.e = applicationInfo;
        this.f = packageInfo;
        this.g = str2;
        this.h = str3;
        this.j = gtVar;
        this.i = bundle2;
        this.n = z;
        if (list == null || list.size() <= 0) {
            this.k = 0;
            this.l = null;
        } else {
            this.k = 2;
            this.l = list;
        }
        this.m = bundle3;
    }
}
