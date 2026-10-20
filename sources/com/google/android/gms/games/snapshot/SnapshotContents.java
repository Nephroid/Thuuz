package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.Contents;
import defpackage.rp;

/* loaded from: classes.dex */
public final class SnapshotContents implements SafeParcelable {
    private final int b;
    private Contents c;
    private static final Object a = new Object();
    public static final rp CREATOR = new rp();

    public SnapshotContents(int i, Contents contents) {
        this.b = i;
        this.c = contents;
    }

    public int a() {
        return this.b;
    }

    public Contents b() {
        return this.c;
    }

    public boolean c() {
        return this.c == null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        rp.a(this, parcel, i);
    }
}
