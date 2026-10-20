package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.ne;
import defpackage.nj;

/* loaded from: classes.dex */
public class NotFilter extends AbstractFilter {
    public static final Parcelable.Creator<NotFilter> CREATOR = new nj();
    public final FilterHolder a;
    public final int b;

    public NotFilter(int i, FilterHolder filterHolder) {
        this.b = i;
        this.a = filterHolder;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.drive.query.Filter
    public <T> T a(ne<T> neVar) {
        return (T) neVar.a(this.a.a().a(neVar));
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        nj.a(this, parcel, i);
    }
}
