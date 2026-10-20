package com.google.android.gms.games.snapshot;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.kb;
import defpackage.rr;

/* loaded from: classes.dex */
public final class SnapshotMetadataChange implements SafeParcelable {
    public static final rr CREATOR = new rr();
    public static final SnapshotMetadataChange a = new SnapshotMetadataChange();
    private final int b;
    private final String c;
    private final Long d;
    private final Uri e;
    private a f;

    SnapshotMetadataChange() {
        this(4, null, null, null, null);
    }

    public SnapshotMetadataChange(int i, String str, Long l, a aVar, Uri uri) {
        this.b = i;
        this.c = str;
        this.d = l;
        this.f = aVar;
        this.e = uri;
        if (this.f != null) {
            kb.a(this.e == null, "Cannot set both a URI and an image");
        } else if (this.e != null) {
            kb.a(this.f == null, "Cannot set both a URI and an image");
        }
    }

    public int a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public Long c() {
        return this.d;
    }

    public a d() {
        return this.f;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Uri e() {
        return this.e;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        rr.a(this, parcel, i);
    }
}
