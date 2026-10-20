package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.ahz;
import defpackage.jy;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public final class mi implements SafeParcelable {
    public static final ahz CREATOR = new ahz();
    public final int a;
    public final List<mo> b;
    public final List<ms> c;
    public final List<String> d;
    private final String e;
    private final boolean f;
    private final Set<mo> g;
    private final Set<ms> h;
    private final Set<String> i;

    public mi(int i, List<mo> list, String str, boolean z, List<ms> list2, List<String> list3) {
        this.a = i;
        this.b = list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
        this.e = str == null ? "" : str;
        this.f = z;
        this.c = list2 == null ? Collections.emptyList() : Collections.unmodifiableList(list2);
        this.d = list3 == null ? Collections.emptyList() : Collections.unmodifiableList(list3);
        this.g = a(this.b);
        this.h = a(this.c);
        this.i = a(this.d);
    }

    private static <E> Set<E> a(List<E> list) {
        return list.isEmpty() ? Collections.emptySet() : Collections.unmodifiableSet(new HashSet(list));
    }

    @Deprecated
    public String a() {
        return this.e;
    }

    public boolean b() {
        return this.f;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        ahz ahzVar = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof mi)) {
            return false;
        }
        mi miVar = (mi) obj;
        return this.g.equals(miVar.g) && this.f == miVar.f && this.h.equals(miVar.h) && this.i.equals(miVar.i);
    }

    public int hashCode() {
        return jy.a(this.g, Boolean.valueOf(this.f), this.h, this.i);
    }

    public String toString() {
        return jy.a(this).a("types", this.g).a("placeIds", this.i).a("requireOpenNow", Boolean.valueOf(this.f)).a("requestedUserDataTypes", this.h).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ahz ahzVar = CREATOR;
        ahz.a(this, parcel, i);
    }
}
