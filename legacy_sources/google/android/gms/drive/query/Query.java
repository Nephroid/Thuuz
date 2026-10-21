package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.internal.LogicalFilter;
import defpackage.mw;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class Query implements SafeParcelable {
    public static final Parcelable.Creator<Query> CREATOR = new mw();
    public final LogicalFilter a;
    public final String b;
    public final SortOrder c;
    public final List<String> d;
    public final int e;

    public Query(int i, LogicalFilter logicalFilter, String str, SortOrder sortOrder, List<String> list) {
        this.e = i;
        this.a = logicalFilter;
        this.b = str;
        this.c = sortOrder;
        this.d = list;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return String.format(Locale.US, "Query[%s,%s,PageToken=%s]", this.a, this.c, this.b);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        mw.a(this, parcel, i);
    }
}
