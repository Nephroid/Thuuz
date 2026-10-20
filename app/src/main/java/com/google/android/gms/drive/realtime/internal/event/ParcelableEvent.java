package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.no;
import java.util.List;

/* loaded from: classes.dex */
public class ParcelableEvent implements SafeParcelable {
    public static final Parcelable.Creator<ParcelableEvent> CREATOR = new no();
    public final int a;
    public final String b;
    public final String c;
    public final List<String> d;
    public final boolean e;
    public final String f;
    public final String g;
    public final TextInsertedDetails h;
    public final TextDeletedDetails i;
    public final ValuesAddedDetails j;
    public final ValuesRemovedDetails k;
    public final ValuesSetDetails l;
    public final ValueChangedDetails m;
    public final ReferenceShiftedDetails n;
    public final ObjectChangedDetails o;

    public ParcelableEvent(int i, String str, String str2, List<String> list, boolean z, String str3, String str4, TextInsertedDetails textInsertedDetails, TextDeletedDetails textDeletedDetails, ValuesAddedDetails valuesAddedDetails, ValuesRemovedDetails valuesRemovedDetails, ValuesSetDetails valuesSetDetails, ValueChangedDetails valueChangedDetails, ReferenceShiftedDetails referenceShiftedDetails, ObjectChangedDetails objectChangedDetails) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = list;
        this.e = z;
        this.f = str3;
        this.g = str4;
        this.h = textInsertedDetails;
        this.i = textDeletedDetails;
        this.j = valuesAddedDetails;
        this.k = valuesRemovedDetails;
        this.l = valuesSetDetails;
        this.m = valueChangedDetails;
        this.n = referenceShiftedDetails;
        this.o = objectChangedDetails;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        no.a(this, parcel, i);
    }
}
