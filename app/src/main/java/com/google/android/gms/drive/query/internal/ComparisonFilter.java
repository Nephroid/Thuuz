package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import defpackage.ly;
import defpackage.mz;
import defpackage.nd;
import defpackage.ne;

/* loaded from: classes.dex */
public class ComparisonFilter<T> extends AbstractFilter {
    public static final mz CREATOR = new mz();
    public final Operator a;
    public final MetadataBundle b;
    public final int c;
    final ly<T> d;

    public ComparisonFilter(int i, Operator operator, MetadataBundle metadataBundle) {
        this.c = i;
        this.a = operator;
        this.b = metadataBundle;
        this.d = (ly<T>) nd.a(metadataBundle);
    }

    public T a() {
        return (T) this.b.a(this.d);
    }

    @Override // com.google.android.gms.drive.query.Filter
    public <F> F a(ne<F> neVar) {
        return neVar.b(this.a, this.d, a());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        mz.a(this, parcel, i);
    }
}
