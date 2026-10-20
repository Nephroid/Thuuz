package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.internal.ji;
import defpackage.aio;
import defpackage.arb;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public final class nv extends jj implements arb {
    public static final aio CREATOR = new aio();
    private static final HashMap<String, ji.a<?, ?>> h = new HashMap<>();
    public final Set<Integer> a;
    public final int b;
    public String c;
    public nt d;
    public String e;
    public nt f;
    public String g;

    static {
        h.put("id", ji.a.d("id", 2));
        h.put("result", ji.a.a("result", 4, nt.class));
        h.put("startDate", ji.a.d("startDate", 5));
        h.put("target", ji.a.a("target", 6, nt.class));
        h.put("type", ji.a.d("type", 7));
    }

    public nv() {
        this.b = 1;
        this.a = new HashSet();
    }

    public nv(Set<Integer> set, int i, String str, nt ntVar, String str2, nt ntVar2, String str3) {
        this.a = set;
        this.b = i;
        this.c = str;
        this.d = ntVar;
        this.e = str2;
        this.f = ntVar2;
        this.g = str3;
    }

    @Override // com.google.android.gms.internal.ji
    protected boolean a(ji.a aVar) {
        return this.a.contains(Integer.valueOf(aVar.g()));
    }

    @Override // com.google.android.gms.internal.ji
    protected Object b(ji.a aVar) {
        switch (aVar.g()) {
            case 2:
                return this.c;
            case 3:
            default:
                throw new IllegalStateException("Unknown safe parcelable id=" + aVar.g());
            case 4:
                return this.d;
            case 5:
                return this.e;
            case 6:
                return this.f;
            case 7:
                return this.g;
        }
    }

    @Override // com.google.android.gms.internal.ji
    public HashMap<String, ji.a<?, ?>> b() {
        return h;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        aio aioVar = CREATOR;
        return 0;
    }

    @Override // defpackage.ik
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public nv a() {
        return this;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof nv)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        nv nvVar = (nv) obj;
        for (ji.a<?, ?> aVar : h.values()) {
            if (a(aVar)) {
                if (nvVar.a(aVar) && b(aVar).equals(nvVar.b(aVar))) {
                }
                return false;
            }
            if (nvVar.a(aVar)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        Iterator<ji.a<?, ?>> it = h.values().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            ji.a<?, ?> next = it.next();
            if (a(next)) {
                i = b(next).hashCode() + i2 + next.g();
            } else {
                i = i2;
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        aio aioVar = CREATOR;
        aio.a(this, parcel, i);
    }
}
