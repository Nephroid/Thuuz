package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.jy;
import defpackage.qx;

/* loaded from: classes.dex */
public final class MostRecentGameInfoEntity implements SafeParcelable, MostRecentGameInfo {
    public static final qx CREATOR = new qx();
    private final int a;
    private final String b;
    private final String c;
    private final long d;
    private final Uri e;
    private final Uri f;
    private final Uri g;

    public MostRecentGameInfoEntity(int i, String str, String str2, long j, Uri uri, Uri uri2, Uri uri3) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = j;
        this.e = uri;
        this.f = uri2;
        this.g = uri3;
    }

    public MostRecentGameInfoEntity(MostRecentGameInfo mostRecentGameInfo) {
        this.a = 2;
        this.b = mostRecentGameInfo.q_();
        this.c = mostRecentGameInfo.c();
        this.d = mostRecentGameInfo.d();
        this.e = mostRecentGameInfo.e();
        this.f = mostRecentGameInfo.f();
        this.g = mostRecentGameInfo.g();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(MostRecentGameInfo mostRecentGameInfo) {
        return jy.a(mostRecentGameInfo.q_(), mostRecentGameInfo.c(), Long.valueOf(mostRecentGameInfo.d()), mostRecentGameInfo.e(), mostRecentGameInfo.f(), mostRecentGameInfo.g());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(MostRecentGameInfo mostRecentGameInfo, Object obj) {
        if (!(obj instanceof MostRecentGameInfo)) {
            return false;
        }
        if (mostRecentGameInfo == obj) {
            return true;
        }
        MostRecentGameInfo mostRecentGameInfo2 = (MostRecentGameInfo) obj;
        return jy.a(mostRecentGameInfo2.q_(), mostRecentGameInfo.q_()) && jy.a(mostRecentGameInfo2.c(), mostRecentGameInfo.c()) && jy.a(Long.valueOf(mostRecentGameInfo2.d()), Long.valueOf(mostRecentGameInfo.d())) && jy.a(mostRecentGameInfo2.e(), mostRecentGameInfo.e()) && jy.a(mostRecentGameInfo2.f(), mostRecentGameInfo.f()) && jy.a(mostRecentGameInfo2.g(), mostRecentGameInfo.g());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String b(MostRecentGameInfo mostRecentGameInfo) {
        return jy.a(mostRecentGameInfo).a("GameId", mostRecentGameInfo.q_()).a("GameName", mostRecentGameInfo.c()).a("ActivityTimestampMillis", Long.valueOf(mostRecentGameInfo.d())).a("GameIconUri", mostRecentGameInfo.e()).a("GameHiResUri", mostRecentGameInfo.f()).a("GameFeaturedUri", mostRecentGameInfo.g()).toString();
    }

    @Override // com.google.android.gms.games.internal.player.MostRecentGameInfo
    public String c() {
        return this.c;
    }

    @Override // com.google.android.gms.games.internal.player.MostRecentGameInfo
    public long d() {
        return this.d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.games.internal.player.MostRecentGameInfo
    public Uri e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    @Override // com.google.android.gms.games.internal.player.MostRecentGameInfo
    public Uri f() {
        return this.f;
    }

    @Override // com.google.android.gms.games.internal.player.MostRecentGameInfo
    public Uri g() {
        return this.g;
    }

    public int h() {
        return this.a;
    }

    public int hashCode() {
        return a(this);
    }

    @Override // defpackage.ik
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public MostRecentGameInfo a() {
        return this;
    }

    @Override // com.google.android.gms.games.internal.player.MostRecentGameInfo
    public String q_() {
        return this.b;
    }

    public String toString() {
        return b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        qx.a(this, parcel, i);
    }
}
