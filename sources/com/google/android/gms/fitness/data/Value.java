package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.os.EnvironmentCompat;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.jy;
import defpackage.kb;
import defpackage.pb;

/* loaded from: classes.dex */
public final class Value implements SafeParcelable {
    public static final Parcelable.Creator<Value> CREATOR = new pb();
    private final int a;
    private final int b;
    private boolean c;
    private float d;

    public Value(int i, int i2, boolean z, float f) {
        this.a = i;
        this.b = i2;
        this.c = z;
        this.d = f;
    }

    private boolean a(Value value) {
        if (this.b != value.b || this.c != value.c) {
            return false;
        }
        switch (this.b) {
            case 1:
                return c() == value.c();
            case 2:
                return d() == value.d();
            default:
                return this.d == value.d;
        }
    }

    public boolean a() {
        return this.c;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        kb.a(this.b == 1, "Value is not in int format");
        return Float.floatToRawIntBits(this.d);
    }

    public float d() {
        kb.a(this.b == 2, "Value is not in float format");
        return this.d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int e() {
        return this.a;
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof Value) && a((Value) obj));
    }

    public float f() {
        return this.d;
    }

    public int hashCode() {
        return jy.a(Float.valueOf(this.d), Integer.valueOf(this.b), Boolean.valueOf(this.c));
    }

    public String toString() {
        switch (this.b) {
            case 1:
                return Integer.toString(c());
            case 2:
                return Float.toString(d());
            default:
                return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        pb.a(this, parcel, i);
    }
}
