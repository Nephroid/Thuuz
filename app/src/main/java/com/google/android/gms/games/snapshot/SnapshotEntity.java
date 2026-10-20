package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.jy;
import defpackage.rq;

/* loaded from: classes.dex */
public final class SnapshotEntity implements SafeParcelable, Snapshot {
    public static final rq CREATOR = new rq();
    private final int a;
    private final SnapshotMetadataEntity b;
    private final SnapshotContents c;

    public SnapshotEntity(int i, SnapshotMetadata snapshotMetadata, SnapshotContents snapshotContents) {
        this.a = i;
        this.b = new SnapshotMetadataEntity(snapshotMetadata);
        this.c = snapshotContents;
    }

    static int a(Snapshot snapshot) {
        return jy.a(snapshot.b(), snapshot.c());
    }

    static boolean a(Snapshot snapshot, Object obj) {
        if (!(obj instanceof Snapshot)) {
            return false;
        }
        if (snapshot == obj) {
            return true;
        }
        Snapshot snapshot2 = (Snapshot) obj;
        return jy.a(snapshot2.b(), snapshot.b()) && jy.a(snapshot2.c(), snapshot.c());
    }

    static String b(Snapshot snapshot) {
        return jy.a(snapshot).a("Metadata", snapshot.b()).a("HasContents", Boolean.valueOf(snapshot.c() != null)).toString();
    }

    private boolean f() {
        return this.c.c();
    }

    @Override // com.google.android.gms.games.snapshot.Snapshot
    public SnapshotMetadata b() {
        return this.b;
    }

    @Override // com.google.android.gms.games.snapshot.Snapshot
    public SnapshotContents c() {
        if (f()) {
            return null;
        }
        return this.c;
    }

    public int d() {
        return this.a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // defpackage.ik
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public Snapshot a() {
        return this;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public int hashCode() {
        return a(this);
    }

    public String toString() {
        return b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        rq.a(this, parcel, i);
    }
}
