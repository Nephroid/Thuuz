package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import defpackage.ly;
import defpackage.na;
import defpackage.nd;
import defpackage.ne;

/* loaded from: classes.dex */
public class FieldOnlyFilter extends AbstractFilter {
    public static final Parcelable.Creator<FieldOnlyFilter> CREATOR = new na();
    public final MetadataBundle a;
    public final int b;
    private final ly<?> c;

    public FieldOnlyFilter(int i, MetadataBundle metadataBundle) {
        this.b = i;
        this.a = metadataBundle;
        this.c = nd.a(metadataBundle);
    }

    @Override // com.google.android.gms.drive.query.Filter
    public <T> T a(ne<T> neVar) {
        return neVar.b(this.c);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        na.a(this, parcel, i);
    }
}
