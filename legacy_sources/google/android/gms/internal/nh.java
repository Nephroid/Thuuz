package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.ail;

/* loaded from: classes.dex */
public class nh implements SafeParcelable {
    public static final ail CREATOR = new ail();
    public final int a;
    public final long b;
    public final String c;
    public final byte[] d;
    public final Bundle e;

    public nh(int i, long j, String str, byte[] bArr, Bundle bundle) {
        this.a = i;
        this.b = j;
        this.c = str;
        this.d = bArr;
        this.e = bundle;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("tag=").append(this.c).append(",");
        sb.append("eventTime=").append(this.b).append(",");
        if (this.e != null && !this.e.isEmpty()) {
            sb.append("keyValues=");
            for (String str : this.e.keySet()) {
                sb.append("(").append(str).append(",");
                sb.append(this.e.getString(str)).append(")");
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ail.a(this, parcel, i);
    }
}
