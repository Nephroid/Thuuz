package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.Filter;
import defpackage.nc;

/* loaded from: classes.dex */
public class FilterHolder implements SafeParcelable {
    public static final Parcelable.Creator<FilterHolder> CREATOR = new nc();
    public final int a;
    public final ComparisonFilter<?> b;
    public final FieldOnlyFilter c;
    public final LogicalFilter d;
    public final NotFilter e;
    public final InFilter<?> f;
    public final MatchAllFilter g;
    public final HasFilter h;
    private final Filter i;

    public FilterHolder(int i, ComparisonFilter<?> comparisonFilter, FieldOnlyFilter fieldOnlyFilter, LogicalFilter logicalFilter, NotFilter notFilter, InFilter<?> inFilter, MatchAllFilter matchAllFilter, HasFilter<?> hasFilter) {
        this.a = i;
        this.b = comparisonFilter;
        this.c = fieldOnlyFilter;
        this.d = logicalFilter;
        this.e = notFilter;
        this.f = inFilter;
        this.g = matchAllFilter;
        this.h = hasFilter;
        if (this.b != null) {
            this.i = this.b;
            return;
        }
        if (this.c != null) {
            this.i = this.c;
            return;
        }
        if (this.d != null) {
            this.i = this.d;
            return;
        }
        if (this.e != null) {
            this.i = this.e;
            return;
        }
        if (this.f != null) {
            this.i = this.f;
        } else if (this.g != null) {
            this.i = this.g;
        } else {
            if (this.h == null) {
                throw new IllegalArgumentException("At least one filter must be set.");
            }
            this.i = this.h;
        }
    }

    public Filter a() {
        return this.i;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return String.format("FilterHolder[%s]", this.i);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        nc.a(this, parcel, i);
    }
}
