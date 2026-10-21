package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.avg;
import defpackage.avx;
import defpackage.kb;

/* loaded from: classes.dex */
public class DataItemAssetParcelable implements avg, SafeParcelable {
    public static final Parcelable.Creator<DataItemAssetParcelable> CREATOR = new avx();
    public final int a;
    private final String b;
    private final String c;

    public DataItemAssetParcelable(int i, String str, String str2) {
        this.a = i;
        this.b = str;
        this.c = str2;
    }

    public DataItemAssetParcelable(avg avgVar) {
        this.a = 1;
        this.b = (String) kb.a(avgVar.b());
        this.c = (String) kb.a(avgVar.c());
    }

    @Override // defpackage.avg
    public String b() {
        return this.b;
    }

    @Override // defpackage.avg
    public String c() {
        return this.c;
    }

    @Override // defpackage.ik
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public avg a() {
        return this;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DataItemAssetParcelable[");
        sb.append("@");
        sb.append(Integer.toHexString(hashCode()));
        if (this.b == null) {
            sb.append(",noid");
        } else {
            sb.append(",");
            sb.append(this.b);
        }
        sb.append(", key=");
        sb.append(this.c);
        sb.append("]");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        avx.a(this, parcel, i);
    }
}
