package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.CompletionEvent;
import defpackage.kw;

/* loaded from: classes.dex */
public class OnEventResponse implements SafeParcelable {
    public static final Parcelable.Creator<OnEventResponse> CREATOR = new kw();
    public final int a;
    public final int b;
    public final ChangeEvent c;
    public final CompletionEvent d;

    public OnEventResponse(int i, int i2, ChangeEvent changeEvent, CompletionEvent completionEvent) {
        this.a = i;
        this.b = i2;
        this.c = changeEvent;
        this.d = completionEvent;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        kw.a(this, parcel, i);
    }
}
