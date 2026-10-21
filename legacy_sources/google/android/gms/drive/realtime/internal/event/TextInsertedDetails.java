package com.google.android.gms.drive.realtime.internal.event;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.ns;

/* loaded from: classes.dex */
public class TextInsertedDetails implements SafeParcelable {
    public static final Parcelable.Creator<TextInsertedDetails> CREATOR = new ns();
    public final int a;
    public final int b;
    public final int c;

    public TextInsertedDetails(int i, int i2, int i3) {
        this.a = i;
        this.b = i2;
        this.c = i3;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ns.a(this, parcel, i);
    }
}
