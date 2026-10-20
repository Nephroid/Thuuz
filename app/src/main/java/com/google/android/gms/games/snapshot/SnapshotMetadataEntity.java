package com.google.android.gms.games.snapshot;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import defpackage.jy;
import defpackage.rs;

/* loaded from: classes.dex */
public final class SnapshotMetadataEntity implements SafeParcelable, SnapshotMetadata {
    public static final rs CREATOR = new rs();
    private final int a;
    private final GameEntity b;
    private final PlayerEntity c;
    private final String d;
    private final Uri e;
    private final String f;
    private final String g;
    private final String h;
    private final long i;
    private final long j;
    private final float k;
    private final String l;

    public SnapshotMetadataEntity(int i, GameEntity gameEntity, PlayerEntity playerEntity, String str, Uri uri, String str2, String str3, String str4, long j, long j2, float f, String str5) {
        this.a = i;
        this.b = gameEntity;
        this.c = playerEntity;
        this.d = str;
        this.e = uri;
        this.f = str2;
        this.k = f;
        this.g = str3;
        this.h = str4;
        this.i = j;
        this.j = j2;
        this.l = str5;
    }

    public SnapshotMetadataEntity(SnapshotMetadata snapshotMetadata) {
        this.a = 3;
        this.b = new GameEntity(snapshotMetadata.B_());
        this.c = new PlayerEntity(snapshotMetadata.c());
        this.d = snapshotMetadata.d();
        this.e = snapshotMetadata.e();
        this.f = snapshotMetadata.f();
        this.k = snapshotMetadata.g();
        this.g = snapshotMetadata.i();
        this.h = snapshotMetadata.j();
        this.i = snapshotMetadata.k();
        this.j = snapshotMetadata.l();
        this.l = snapshotMetadata.h();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(SnapshotMetadata snapshotMetadata) {
        return jy.a(snapshotMetadata.B_(), snapshotMetadata.c(), snapshotMetadata.d(), snapshotMetadata.e(), Float.valueOf(snapshotMetadata.g()), snapshotMetadata.i(), snapshotMetadata.j(), Long.valueOf(snapshotMetadata.k()), Long.valueOf(snapshotMetadata.l()), snapshotMetadata.h());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(SnapshotMetadata snapshotMetadata, Object obj) {
        if (!(obj instanceof SnapshotMetadata)) {
            return false;
        }
        if (snapshotMetadata == obj) {
            return true;
        }
        SnapshotMetadata snapshotMetadata2 = (SnapshotMetadata) obj;
        return jy.a(snapshotMetadata2.B_(), snapshotMetadata.B_()) && jy.a(snapshotMetadata2.c(), snapshotMetadata.c()) && jy.a(snapshotMetadata2.d(), snapshotMetadata.d()) && jy.a(snapshotMetadata2.e(), snapshotMetadata.e()) && jy.a(Float.valueOf(snapshotMetadata2.g()), Float.valueOf(snapshotMetadata.g())) && jy.a(snapshotMetadata2.i(), snapshotMetadata.i()) && jy.a(snapshotMetadata2.j(), snapshotMetadata.j()) && jy.a(Long.valueOf(snapshotMetadata2.k()), Long.valueOf(snapshotMetadata.k())) && jy.a(Long.valueOf(snapshotMetadata2.l()), Long.valueOf(snapshotMetadata.l())) && jy.a(snapshotMetadata2.h(), snapshotMetadata.h());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String b(SnapshotMetadata snapshotMetadata) {
        return jy.a(snapshotMetadata).a("Game", snapshotMetadata.B_()).a("Owner", snapshotMetadata.c()).a("SnapshotId", snapshotMetadata.d()).a("CoverImageUri", snapshotMetadata.e()).a("CoverImageUrl", snapshotMetadata.f()).a("CoverImageAspectRatio", Float.valueOf(snapshotMetadata.g())).a("Description", snapshotMetadata.j()).a("LastModifiedTimestamp", Long.valueOf(snapshotMetadata.k())).a("PlayedTime", Long.valueOf(snapshotMetadata.l())).a("UniqueName", snapshotMetadata.h()).toString();
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public Game B_() {
        return this.b;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public Player c() {
        return this.c;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public String d() {
        return this.d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public Uri e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public String f() {
        return this.f;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public float g() {
        return this.k;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public String h() {
        return this.l;
    }

    public int hashCode() {
        return a(this);
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public String i() {
        return this.g;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public String j() {
        return this.h;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public long k() {
        return this.i;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotMetadata
    public long l() {
        return this.j;
    }

    public int m() {
        return this.a;
    }

    @Override // defpackage.ik
    /* renamed from: n, reason: merged with bridge method [inline-methods] */
    public SnapshotMetadata a() {
        return this;
    }

    public String toString() {
        return b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        rs.a(this, parcel, i);
    }
}
