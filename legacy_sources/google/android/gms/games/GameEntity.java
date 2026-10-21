package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import defpackage.jy;
import defpackage.qi;
import defpackage.qj;

/* loaded from: classes.dex */
public final class GameEntity extends GamesDowngradeableSafeParcel implements Game {
    public static final Parcelable.Creator<GameEntity> CREATOR = new qi();
    private final int a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;
    private final Uri h;
    private final Uri i;
    private final Uri j;
    private final boolean k;
    private final boolean l;
    private final String m;
    private final int n;
    private final int o;
    private final int p;
    private final boolean q;
    private final boolean r;
    private final String s;
    private final String t;
    private final String u;
    private final boolean v;
    private final boolean w;
    private final boolean x;
    private final String y;

    public GameEntity(int i, String str, String str2, String str3, String str4, String str5, String str6, Uri uri, Uri uri2, Uri uri3, boolean z, boolean z2, String str7, int i2, int i3, int i4, boolean z3, boolean z4, String str8, String str9, String str10, boolean z5, boolean z6, boolean z7, String str11) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = str5;
        this.g = str6;
        this.h = uri;
        this.s = str8;
        this.i = uri2;
        this.t = str9;
        this.j = uri3;
        this.u = str10;
        this.k = z;
        this.l = z2;
        this.m = str7;
        this.n = i2;
        this.o = i3;
        this.p = i4;
        this.q = z3;
        this.r = z4;
        this.v = z5;
        this.w = z6;
        this.x = z7;
        this.y = str11;
    }

    public GameEntity(Game game) {
        this.a = 5;
        this.b = game.c_();
        this.d = game.e_();
        this.e = game.e();
        this.f = game.f();
        this.g = game.g();
        this.c = game.d_();
        this.h = game.h();
        this.s = game.i();
        this.i = game.j();
        this.t = game.k();
        this.j = game.l();
        this.u = game.m();
        this.k = game.n();
        this.l = game.q();
        this.m = game.r();
        this.n = game.s();
        this.o = game.t();
        this.p = game.u();
        this.q = game.v();
        this.r = game.w();
        this.v = game.o();
        this.w = game.p();
        this.x = game.x();
        this.y = game.y();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(Game game) {
        return jy.a(game.c_(), game.d_(), game.e_(), game.e(), game.f(), game.g(), game.h(), game.j(), game.l(), Boolean.valueOf(game.n()), Boolean.valueOf(game.q()), game.r(), Integer.valueOf(game.s()), Integer.valueOf(game.t()), Integer.valueOf(game.u()), Boolean.valueOf(game.v()), Boolean.valueOf(game.w()), Boolean.valueOf(game.o()), Boolean.valueOf(game.p()), Boolean.valueOf(game.x()), game.y());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(Game game, Object obj) {
        if (!(obj instanceof Game)) {
            return false;
        }
        if (game == obj) {
            return true;
        }
        Game game2 = (Game) obj;
        if (jy.a(game2.c_(), game.c_()) && jy.a(game2.d_(), game.d_()) && jy.a(game2.e_(), game.e_()) && jy.a(game2.e(), game.e()) && jy.a(game2.f(), game.f()) && jy.a(game2.g(), game.g()) && jy.a(game2.h(), game.h()) && jy.a(game2.j(), game.j()) && jy.a(game2.l(), game.l()) && jy.a(Boolean.valueOf(game2.n()), Boolean.valueOf(game.n())) && jy.a(Boolean.valueOf(game2.q()), Boolean.valueOf(game.q())) && jy.a(game2.r(), game.r()) && jy.a(Integer.valueOf(game2.s()), Integer.valueOf(game.s())) && jy.a(Integer.valueOf(game2.t()), Integer.valueOf(game.t())) && jy.a(Integer.valueOf(game2.u()), Integer.valueOf(game.u())) && jy.a(Boolean.valueOf(game2.v()), Boolean.valueOf(game.v()))) {
            if (jy.a(Boolean.valueOf(game2.w()), Boolean.valueOf(game.w() && jy.a(Boolean.valueOf(game2.o()), Boolean.valueOf(game.o())) && jy.a(Boolean.valueOf(game2.p()), Boolean.valueOf(game.p())))) && jy.a(Boolean.valueOf(game2.x()), Boolean.valueOf(game.x())) && jy.a(game2.y(), game.y())) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String b(Game game) {
        return jy.a(game).a("ApplicationId", game.c_()).a("DisplayName", game.d_()).a("PrimaryCategory", game.e_()).a("SecondaryCategory", game.e()).a("Description", game.f()).a("DeveloperName", game.g()).a("IconImageUri", game.h()).a("IconImageUrl", game.i()).a("HiResImageUri", game.j()).a("HiResImageUrl", game.k()).a("FeaturedImageUri", game.l()).a("FeaturedImageUrl", game.m()).a("PlayEnabledGame", Boolean.valueOf(game.n())).a("InstanceInstalled", Boolean.valueOf(game.q())).a("InstancePackageName", game.r()).a("AchievementTotalCount", Integer.valueOf(game.t())).a("LeaderboardCount", Integer.valueOf(game.u())).a("RealTimeMultiplayerEnabled", Boolean.valueOf(game.v())).a("TurnBasedMultiplayerEnabled", Boolean.valueOf(game.w())).a("AreSnapshotsEnabled", Boolean.valueOf(game.x())).a("ThemeColor", game.y()).toString();
    }

    @Override // defpackage.ik
    /* renamed from: A, reason: merged with bridge method [inline-methods] */
    public Game a() {
        return this;
    }

    @Override // com.google.android.gms.games.Game
    public String c_() {
        return this.b;
    }

    @Override // com.google.android.gms.games.Game
    public String d_() {
        return this.c;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.games.Game
    public String e() {
        return this.e;
    }

    @Override // com.google.android.gms.games.Game
    public String e_() {
        return this.d;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    @Override // com.google.android.gms.games.Game
    public String f() {
        return this.f;
    }

    @Override // com.google.android.gms.games.Game
    public String g() {
        return this.g;
    }

    @Override // com.google.android.gms.games.Game
    public Uri h() {
        return this.h;
    }

    public int hashCode() {
        return a(this);
    }

    @Override // com.google.android.gms.games.Game
    public String i() {
        return this.s;
    }

    @Override // com.google.android.gms.games.Game
    public Uri j() {
        return this.i;
    }

    @Override // com.google.android.gms.games.Game
    public String k() {
        return this.t;
    }

    @Override // com.google.android.gms.games.Game
    public Uri l() {
        return this.j;
    }

    @Override // com.google.android.gms.games.Game
    public String m() {
        return this.u;
    }

    @Override // com.google.android.gms.games.Game
    public boolean n() {
        return this.k;
    }

    @Override // com.google.android.gms.games.Game
    public boolean o() {
        return this.v;
    }

    @Override // com.google.android.gms.games.Game
    public boolean p() {
        return this.w;
    }

    @Override // com.google.android.gms.games.Game
    public boolean q() {
        return this.l;
    }

    @Override // com.google.android.gms.games.Game
    public String r() {
        return this.m;
    }

    @Override // com.google.android.gms.games.Game
    public int s() {
        return this.n;
    }

    @Override // com.google.android.gms.games.Game
    public int t() {
        return this.o;
    }

    public String toString() {
        return b(this);
    }

    @Override // com.google.android.gms.games.Game
    public int u() {
        return this.p;
    }

    @Override // com.google.android.gms.games.Game
    public boolean v() {
        return this.q;
    }

    @Override // com.google.android.gms.games.Game
    public boolean w() {
        return this.r;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (!d()) {
            qj.a(this, parcel, i);
            return;
        }
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h == null ? null : this.h.toString());
        parcel.writeString(this.i == null ? null : this.i.toString());
        parcel.writeString(this.j != null ? this.j.toString() : null);
        parcel.writeInt(this.k ? 1 : 0);
        parcel.writeInt(this.l ? 1 : 0);
        parcel.writeString(this.m);
        parcel.writeInt(this.n);
        parcel.writeInt(this.o);
        parcel.writeInt(this.p);
    }

    @Override // com.google.android.gms.games.Game
    public boolean x() {
        return this.x;
    }

    @Override // com.google.android.gms.games.Game
    public String y() {
        return this.y;
    }

    public int z() {
        return this.a;
    }
}
