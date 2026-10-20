package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.ake;
import defpackage.akf;
import java.util.Comparator;

/* loaded from: classes.dex */
public class DetectedActivity implements SafeParcelable {
    public int b;
    public int c;
    private final int d;
    public static final Comparator<DetectedActivity> a = new ake();
    public static final akf CREATOR = new akf();

    public DetectedActivity(int i, int i2, int i3) {
        this.d = i;
        this.b = i2;
        this.c = i3;
    }

    private int a(int i) {
        if (i > 9) {
            return 4;
        }
        return i;
    }

    public int a() {
        return a(this.b);
    }

    public int b() {
        return this.c;
    }

    public int c() {
        return this.d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "DetectedActivity [type=" + a() + ", confidence=" + this.c + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        akf.a(this, parcel, i);
    }
}
