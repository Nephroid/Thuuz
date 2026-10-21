package com.google.android.gms.drive.events;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import defpackage.kj;
import java.util.ArrayList;
import java.util.Locale;

/* loaded from: classes.dex */
public final class CompletionEvent implements SafeParcelable, ResourceEvent {
    public static final Parcelable.Creator<CompletionEvent> CREATOR = new kj();
    public final int a;
    public final DriveId b;
    public final String c;
    public final ParcelFileDescriptor d;
    public final ParcelFileDescriptor e;
    public final MetadataBundle f;
    public final ArrayList<String> g;
    public final int h;
    public final IBinder i;
    private boolean j = false;
    private boolean k = false;
    private boolean l = false;

    public CompletionEvent(int i, DriveId driveId, String str, ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2, MetadataBundle metadataBundle, ArrayList<String> arrayList, int i2, IBinder iBinder) {
        this.a = i;
        this.b = driveId;
        this.c = str;
        this.d = parcelFileDescriptor;
        this.e = parcelFileDescriptor2;
        this.f = metadataBundle;
        this.g = arrayList;
        this.h = i2;
        this.i = iBinder;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return String.format(Locale.US, "CompletionEvent [id=%s, status=%s, trackingTag=%s]", this.b, Integer.valueOf(this.h), this.g == null ? "<null>" : "'" + TextUtils.join("','", this.g) + "'");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        kj.a(this, parcel, i);
    }
}
