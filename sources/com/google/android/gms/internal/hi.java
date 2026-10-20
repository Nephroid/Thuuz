package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.ael;
import defpackage.aep;
import defpackage.aeq;
import defpackage.kb;

/* loaded from: classes.dex */
public class hi implements SafeParcelable {
    public final int b;
    public final String c;
    public final hq d;
    public final int e;
    public final byte[] f;
    public static final int a = Integer.parseInt("-1");
    public static final ael CREATOR = new ael();
    private static final hq g = new aeq("SsbContext").a(true).a("blob").a();

    public hi(int i, String str, hq hqVar, int i2, byte[] bArr) {
        kb.b(i2 == a || aep.a(i2) != null, "Invalid section type " + i2);
        this.b = i;
        this.c = str;
        this.d = hqVar;
        this.e = i2;
        this.f = bArr;
        String a2 = a();
        if (a2 != null) {
            throw new IllegalArgumentException(a2);
        }
    }

    public String a() {
        if (this.e != a && aep.a(this.e) == null) {
            return "Invalid section type " + this.e;
        }
        if (this.c == null || this.f == null) {
            return null;
        }
        return "Both content and blobContent set";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        ael aelVar = CREATOR;
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ael aelVar = CREATOR;
        ael.a(this, parcel, i);
    }
}
