package defpackage;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@zy
/* loaded from: classes.dex */
public final class ud {
    public static final String a = adm.a("emulator");
    private final Date b;
    private final String c;
    private final int d;
    private final Set<String> e;
    private final Location f;
    private final boolean g;
    private final Bundle h;
    private final Map<Class<? extends Object>, Object> i;
    private final String j;
    private final dw k;
    private final int l;
    private final Set<String> m;

    public ud(ue ueVar) {
        this(ueVar, null);
    }

    public ud(ue ueVar, dw dwVar) {
        Date date;
        String str;
        int i;
        HashSet hashSet;
        Location location;
        boolean z;
        Bundle bundle;
        HashMap hashMap;
        String str2;
        int i2;
        HashSet hashSet2;
        date = ueVar.e;
        this.b = date;
        str = ueVar.f;
        this.c = str;
        i = ueVar.g;
        this.d = i;
        hashSet = ueVar.a;
        this.e = Collections.unmodifiableSet(hashSet);
        location = ueVar.h;
        this.f = location;
        z = ueVar.i;
        this.g = z;
        bundle = ueVar.b;
        this.h = bundle;
        hashMap = ueVar.c;
        this.i = Collections.unmodifiableMap(hashMap);
        str2 = ueVar.j;
        this.j = str2;
        this.k = dwVar;
        i2 = ueVar.k;
        this.l = i2;
        hashSet2 = ueVar.d;
        this.m = Collections.unmodifiableSet(hashSet2);
    }

    public Bundle a(Class<? extends dm> cls) {
        return this.h.getBundle(cls.getName());
    }

    public Date a() {
        return this.b;
    }

    public boolean a(Context context) {
        return this.m.contains(adm.a(context));
    }

    public String b() {
        return this.c;
    }

    public int c() {
        return this.d;
    }

    public Set<String> d() {
        return this.e;
    }

    public Location e() {
        return this.f;
    }

    public boolean f() {
        return this.g;
    }

    public String g() {
        return this.j;
    }

    public dw h() {
        return this.k;
    }

    public Map<Class<? extends Object>, Object> i() {
        return this.i;
    }

    public Bundle j() {
        return this.h;
    }

    public int k() {
        return this.l;
    }
}
