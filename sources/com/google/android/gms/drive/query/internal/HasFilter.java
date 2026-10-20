package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import defpackage.ly;
import defpackage.nd;
import defpackage.ne;
import defpackage.nf;

/* loaded from: classes.dex */
public class HasFilter<T> extends AbstractFilter {
    public static final nf CREATOR = new nf();
    public final MetadataBundle a;
    public final int b;
    final ly<T> c;

    public HasFilter(int i, MetadataBundle metadataBundle) {
        this.b = i;
        this.a = metadataBundle;
        this.c = (ly<T>) nd.a(metadataBundle);
    }

    public T a() {
        return (T) this.a.a(this.c);
    }

    @Override // com.google.android.gms.drive.query.Filter
    public <F> F a(ne<F> neVar) {
        return neVar.b((ly<ly<T>>) this.c, (ly<T>) a());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        nf.a(this, parcel, i);
    }
}
