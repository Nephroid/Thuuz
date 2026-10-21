package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.avf;
import defpackage.avg;
import defpackage.avy;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class m implements avf, SafeParcelable {
    public static final Parcelable.Creator<m> CREATOR = new avy();
    public final int a;
    private final Uri b;
    private final Map<String, avg> c;
    private byte[] d;

    public m(int i, Uri uri, Bundle bundle, byte[] bArr) {
        this.a = i;
        this.b = uri;
        HashMap hashMap = new HashMap();
        bundle.setClassLoader(DataItemAssetParcelable.class.getClassLoader());
        for (String str : bundle.keySet()) {
            hashMap.put(str, (DataItemAssetParcelable) bundle.getParcelable(str));
        }
        this.c = hashMap;
        this.d = bArr;
    }

    public String a(boolean z) {
        StringBuilder sb = new StringBuilder("DataItemParcelable[");
        sb.append("@");
        sb.append(Integer.toHexString(hashCode()));
        sb.append(",dataSz=" + (this.d == null ? "null" : Integer.valueOf(this.d.length)));
        sb.append(", numAssets=" + this.c.size());
        sb.append(", uri=" + this.b);
        if (!z) {
            sb.append("]");
            return sb.toString();
        }
        sb.append("]\n  assets: ");
        for (String str : this.c.keySet()) {
            sb.append("\n    " + str + ": " + this.c.get(str));
        }
        sb.append("\n  ]");
        return sb.toString();
    }

    @Override // defpackage.ik
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public m a() {
        return this;
    }

    public Uri c() {
        return this.b;
    }

    public byte[] d() {
        return this.d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bundle e() {
        Bundle bundle = new Bundle();
        bundle.setClassLoader(DataItemAssetParcelable.class.getClassLoader());
        for (Map.Entry<String, avg> entry : this.c.entrySet()) {
            bundle.putParcelable(entry.getKey(), new DataItemAssetParcelable(entry.getValue()));
        }
        return bundle;
    }

    public String toString() {
        return a(Log.isLoggable("DataItem", 3));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        avy.a(this, parcel, i);
    }
}
