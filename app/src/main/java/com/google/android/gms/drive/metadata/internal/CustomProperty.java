package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import defpackage.kb;
import defpackage.ml;

/* loaded from: classes.dex */
public class CustomProperty implements SafeParcelable {
    public static final Parcelable.Creator<CustomProperty> CREATOR = new ml();
    public final int a;
    public final CustomPropertyKey b;
    public final String c;

    public CustomProperty(int i, CustomPropertyKey customPropertyKey, String str) {
        this.a = i;
        kb.a(customPropertyKey, "key");
        this.b = customPropertyKey;
        this.c = str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ml.a(this, parcel, i);
    }
}
