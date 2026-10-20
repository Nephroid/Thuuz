package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.LocationRequest;
import defpackage.ahu;
import defpackage.jy;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class lz implements SafeParcelable {
    public LocationRequest b;
    public boolean c;
    public boolean d;
    public boolean e;
    public List<lr> f;
    public final String g;
    private final int h;
    public static final List<lr> a = Collections.emptyList();
    public static final ahu CREATOR = new ahu();

    public lz(int i, LocationRequest locationRequest, boolean z, boolean z2, boolean z3, List<lr> list, String str) {
        this.h = i;
        this.b = locationRequest;
        this.c = z;
        this.d = z2;
        this.e = z3;
        this.f = list;
        this.g = str;
    }

    public int a() {
        return this.h;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof lz)) {
            return false;
        }
        lz lzVar = (lz) obj;
        return jy.a(this.b, lzVar.b) && this.c == lzVar.c && this.d == lzVar.d && this.e == lzVar.e && jy.a(this.f, lzVar.f);
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.b.toString());
        sb.append(" requestNlpDebugInfo=");
        sb.append(this.c);
        sb.append(" restorePendingIntentListeners=");
        sb.append(this.d);
        sb.append(" triggerUpdate=");
        sb.append(this.e);
        sb.append(" clients=");
        sb.append(this.f);
        if (this.g != null) {
            sb.append(" tag=");
            sb.append(this.g);
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ahu.a(this, parcel, i);
    }
}
