package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aha;
import defpackage.jy;
import defpackage.kb;
import defpackage.oi;

/* loaded from: classes.dex */
public final class a implements SafeParcelable {
    private final int b;
    private final String c;
    private final String d;
    private final String e;
    public static final a a = new a("com.google.android.gms", String.valueOf(6111000), null);
    public static final Parcelable.Creator<a> CREATOR = new oi();

    public a(int i, String str, String str2, String str3) {
        this.b = i;
        this.c = (String) kb.a(str);
        this.d = "";
        this.e = str3;
    }

    public a(String str, String str2, String str3) {
        this(1, str, "", str3);
    }

    private boolean a(a aVar) {
        return this.c.equals(aVar.c) && jy.a(this.d, aVar.d) && jy.a(this.e, aVar.e);
    }

    public String a() {
        return this.c;
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a d() {
        return new a(aha.a(this.c), aha.a(this.d), aha.a(this.e));
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int e() {
        return this.b;
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof a) && a((a) obj));
    }

    public int hashCode() {
        return jy.a(this.c, this.d, this.e);
    }

    public String toString() {
        return String.format("Application{%s:%s:%s}", this.c, this.d, this.e);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        oi.a(this, parcel, i);
    }
}
