package defpackage;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.internal.av;
import com.google.android.gms.internal.bj;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

@zy
/* loaded from: classes.dex */
public class to {
    public static final to a = new to();

    private to() {
    }

    public static to a() {
        return a;
    }

    public av a(Context context, ud udVar) {
        Date a2 = udVar.a();
        long time = a2 != null ? a2.getTime() : -1L;
        String b = udVar.b();
        int c = udVar.c();
        Set<String> d = udVar.d();
        List unmodifiableList = !d.isEmpty() ? Collections.unmodifiableList(new ArrayList(d)) : null;
        boolean a3 = udVar.a(context);
        int k = udVar.k();
        Location e = udVar.e();
        Bundle a4 = udVar.a(co.class);
        boolean f = udVar.f();
        String g = udVar.g();
        dw h = udVar.h();
        return new av(4, time, a4, c, unmodifiableList, a3, k, f, g, h != null ? new bj(h) : null, e, b, udVar.j());
    }
}
