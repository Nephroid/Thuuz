package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.nb;
import java.util.Locale;

/* loaded from: classes.dex */
public class FieldWithSortOrder implements SafeParcelable {
    public static final nb CREATOR = new nb();
    public final String a;
    public final boolean b;
    public final int c;

    public FieldWithSortOrder(int i, String str, boolean z) {
        this.c = i;
        this.a = str;
        this.b = z;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        Locale locale = Locale.US;
        Object[] objArr = new Object[2];
        objArr[0] = this.a;
        objArr[1] = this.b ? "ASC" : "DESC";
        return String.format(locale, "FieldWithSortOrder[%s %s]", objArr);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        nb.a(this, parcel, i);
    }
}
