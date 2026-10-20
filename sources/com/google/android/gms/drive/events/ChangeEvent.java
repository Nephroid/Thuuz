package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import defpackage.ki;
import java.util.Locale;

/* loaded from: classes.dex */
public final class ChangeEvent implements SafeParcelable, ResourceEvent {
    public static final Parcelable.Creator<ChangeEvent> CREATOR = new ki();
    public final int a;
    public final DriveId b;
    public final int c;

    public ChangeEvent(int i, DriveId driveId, int i2) {
        this.a = i;
        this.b = driveId;
        this.c = i2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return String.format(Locale.US, "ChangeEvent [id=%s,changeFlags=%x]", this.b, Integer.valueOf(this.c));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ki.a(this, parcel, i);
    }
}
