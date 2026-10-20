package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import defpackage.me;
import defpackage.nd;
import defpackage.ne;
import defpackage.ng;
import java.util.Collection;

/* loaded from: classes.dex */
public class InFilter<T> extends AbstractFilter {
    public static final ng CREATOR = new ng();
    public final MetadataBundle a;
    public final int b;
    private final me<T> c;

    public InFilter(int i, MetadataBundle metadataBundle) {
        this.b = i;
        this.a = metadataBundle;
        this.c = (me) nd.a(metadataBundle);
    }

    public T a() {
        return (T) ((Collection) this.a.a(this.c)).iterator().next();
    }

    @Override // com.google.android.gms.drive.query.Filter
    public <F> F a(ne<F> neVar) {
        return neVar.b((me<me<T>>) this.c, (me<T>) a());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ng.a(this, parcel, i);
    }
}
