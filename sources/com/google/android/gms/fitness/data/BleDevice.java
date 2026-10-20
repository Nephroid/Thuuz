package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.samsung.android.sdk.richnotification.Utilities;
import defpackage.agz;
import defpackage.jy;
import defpackage.oj;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class BleDevice implements SafeParcelable {
    public static final Parcelable.Creator<BleDevice> CREATOR = new oj();
    private final int a;
    private final String b;
    private final String c;
    private final List<String> d;
    private final List<DataType> e;

    public BleDevice(int i, String str, String str2, List<String> list, List<DataType> list2) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = Collections.unmodifiableList(list);
        this.e = Collections.unmodifiableList(list2);
    }

    private boolean a(BleDevice bleDevice) {
        return this.c.equals(bleDevice.c) && this.b.equals(bleDevice.b) && agz.a(bleDevice.d, this.d) && agz.a(this.e, bleDevice.e);
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public List<String> c() {
        return this.d;
    }

    public List<DataType> d() {
        return this.e;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int e() {
        return this.a;
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof BleDevice) && a((BleDevice) obj));
    }

    public int hashCode() {
        return jy.a(this.c, this.b, this.d, this.e);
    }

    public String toString() {
        return jy.a(this).a(Utilities.DB_KEY_IMAGE_NAME, this.c).a("address", this.b).a("dataTypes", this.e).a("supportedProfiles", this.d).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        oj.a(this, parcel, i);
    }
}
