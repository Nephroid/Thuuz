package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import defpackage.ne;
import defpackage.ni;

/* loaded from: classes.dex */
public class MatchAllFilter extends AbstractFilter {
    public static final ni CREATOR = new ni();
    public final int a;

    public MatchAllFilter() {
        this(1);
    }

    public MatchAllFilter(int i) {
        this.a = i;
    }

    @Override // com.google.android.gms.drive.query.Filter
    public <F> F a(ne<F> neVar) {
        return neVar.b();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ni.a(this, parcel, i);
    }
}
