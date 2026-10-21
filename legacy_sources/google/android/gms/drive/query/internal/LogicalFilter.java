package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.ne;
import defpackage.nh;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class LogicalFilter extends AbstractFilter {
    public static final Parcelable.Creator<LogicalFilter> CREATOR = new nh();
    public final Operator a;
    public final List<FilterHolder> b;
    public final int c;

    public LogicalFilter(int i, Operator operator, List<FilterHolder> list) {
        this.c = i;
        this.a = operator;
        this.b = list;
    }

    @Override // com.google.android.gms.drive.query.Filter
    public <T> T a(ne<T> neVar) {
        ArrayList arrayList = new ArrayList();
        Iterator<FilterHolder> it = this.b.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().a().a(neVar));
        }
        return neVar.b(this.a, arrayList);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        nh.a(this, parcel, i);
    }
}
