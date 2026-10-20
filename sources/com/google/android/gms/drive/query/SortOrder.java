package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.internal.FieldWithSortOrder;
import defpackage.mx;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class SortOrder implements SafeParcelable {
    public static final Parcelable.Creator<SortOrder> CREATOR = new mx();
    public final List<FieldWithSortOrder> a;
    public final boolean b;
    public final int c;

    public SortOrder(int i, List<FieldWithSortOrder> list, boolean z) {
        this.c = i;
        this.a = list;
        this.b = z;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return String.format(Locale.US, "SortOrder[%s, %s]", TextUtils.join(",", this.a), Boolean.valueOf(this.b));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        mx.a(this, parcel, i);
    }
}
