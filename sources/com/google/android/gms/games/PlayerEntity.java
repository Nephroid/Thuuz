package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.games.internal.player.MostRecentGameInfo;
import com.google.android.gms.games.internal.player.MostRecentGameInfoEntity;
import defpackage.ix;
import defpackage.jy;
import defpackage.qk;
import defpackage.ql;

/* loaded from: classes.dex */
public final class PlayerEntity extends GamesDowngradeableSafeParcel implements Player {
    public static final Parcelable.Creator<PlayerEntity> CREATOR = new qk();
    private final int a;
    private final String b;
    private final String c;
    private final Uri d;
    private final Uri e;
    private final long f;
    private final int g;
    private final long h;
    private final String i;
    private final String j;
    private final String k;
    private final MostRecentGameInfoEntity l;
    private final PlayerLevelInfo m;
    private final boolean n;

    public PlayerEntity(int i, String str, String str2, Uri uri, Uri uri2, long j, int i2, long j2, String str3, String str4, String str5, MostRecentGameInfoEntity mostRecentGameInfoEntity, PlayerLevelInfo playerLevelInfo, boolean z) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = uri;
        this.i = str3;
        this.e = uri2;
        this.j = str4;
        this.f = j;
        this.g = i2;
        this.h = j2;
        this.k = str5;
        this.n = z;
        this.l = mostRecentGameInfoEntity;
        this.m = playerLevelInfo;
    }

    public PlayerEntity(Player player) {
        this.a = 11;
        this.b = player.f_();
        this.c = player.g_();
        this.d = player.h_();
        this.i = player.e();
        this.e = player.f();
        this.j = player.g();
        this.f = player.h();
        this.g = player.j();
        this.h = player.i();
        this.k = player.l();
        this.n = player.k();
        MostRecentGameInfo n = player.n();
        this.l = n == null ? null : new MostRecentGameInfoEntity(n);
        this.m = player.m();
        ix.a((Object) this.b);
        ix.a((Object) this.c);
        ix.a(this.f > 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(Player player) {
        return jy.a(player.f_(), player.g_(), player.h_(), player.f(), Long.valueOf(player.h()), player.l(), player.m());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(Player player, Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        if (player == obj) {
            return true;
        }
        Player player2 = (Player) obj;
        return jy.a(player2.f_(), player.f_()) && jy.a(player2.g_(), player.g_()) && jy.a(player2.h_(), player.h_()) && jy.a(player2.f(), player.f()) && jy.a(Long.valueOf(player2.h()), Long.valueOf(player.h())) && jy.a(player2.l(), player.l()) && jy.a(player2.m(), player.m());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String b(Player player) {
        return jy.a(player).a("PlayerId", player.f_()).a("DisplayName", player.g_()).a("IconImageUri", player.h_()).a("IconImageUrl", player.e()).a("HiResImageUri", player.f()).a("HiResImageUrl", player.g()).a("RetrievedTimestamp", Long.valueOf(player.h())).a("Title", player.l()).a("LevelInfo", player.m()).toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.games.Player
    public String e() {
        return this.i;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    @Override // com.google.android.gms.games.Player
    public Uri f() {
        return this.e;
    }

    @Override // com.google.android.gms.games.Player
    public String f_() {
        return this.b;
    }

    @Override // com.google.android.gms.games.Player
    public String g() {
        return this.j;
    }

    @Override // com.google.android.gms.games.Player
    public String g_() {
        return this.c;
    }

    @Override // com.google.android.gms.games.Player
    public long h() {
        return this.f;
    }

    @Override // com.google.android.gms.games.Player
    public Uri h_() {
        return this.d;
    }

    public int hashCode() {
        return a(this);
    }

    @Override // com.google.android.gms.games.Player
    public long i() {
        return this.h;
    }

    @Override // com.google.android.gms.games.Player
    public int j() {
        return this.g;
    }

    @Override // com.google.android.gms.games.Player
    public boolean k() {
        return this.n;
    }

    @Override // com.google.android.gms.games.Player
    public String l() {
        return this.k;
    }

    @Override // com.google.android.gms.games.Player
    public PlayerLevelInfo m() {
        return this.m;
    }

    @Override // com.google.android.gms.games.Player
    public MostRecentGameInfo n() {
        return this.l;
    }

    public int o() {
        return this.a;
    }

    @Override // defpackage.ik
    /* renamed from: p, reason: merged with bridge method [inline-methods] */
    public Player a() {
        return this;
    }

    public String toString() {
        return b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (!d()) {
            ql.a(this, parcel, i);
            return;
        }
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d == null ? null : this.d.toString());
        parcel.writeString(this.e != null ? this.e.toString() : null);
        parcel.writeLong(this.f);
    }
}
