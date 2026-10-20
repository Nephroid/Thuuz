package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.afl;
import defpackage.afo;

/* loaded from: classes.dex */
public class jd implements SafeParcelable {
    public static final afl CREATOR = new afl();
    private final int a;
    private final jf b;

    public jd(int i, jf jfVar) {
        this.a = i;
        this.b = jfVar;
    }

    private jd(jf jfVar) {
        this.a = 1;
        this.b = jfVar;
    }

    public static jd a(afo<?, ?> afoVar) {
        if (afoVar instanceof jf) {
            return new jd((jf) afoVar);
        }
        throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
    }

    public int a() {
        return this.a;
    }

    public jf b() {
        return this.b;
    }

    public afo<?, ?> c() {
        if (this.b != null) {
            return this.b;
        }
        throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        afl aflVar = CREATOR;
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        afl aflVar = CREATOR;
        afl.a(this, parcel, i);
    }
}
