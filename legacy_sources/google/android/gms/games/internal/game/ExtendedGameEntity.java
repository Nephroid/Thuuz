package com.google.android.gms.games.internal.game;

import android.os.Parcel;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;
import defpackage.jy;
import defpackage.qs;
import defpackage.qt;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class ExtendedGameEntity extends GamesDowngradeableSafeParcel implements ExtendedGame {
    public static final qt CREATOR = new qs();
    private final int a;
    private final GameEntity b;
    private final int c;
    private final boolean d;
    private final int e;
    private final long f;
    private final long g;
    private final String h;
    private final long i;
    private final String j;
    private final ArrayList<GameBadgeEntity> k;
    private final SnapshotMetadataEntity l;

    public ExtendedGameEntity(int i, GameEntity gameEntity, int i2, boolean z, int i3, long j, long j2, String str, long j3, String str2, ArrayList<GameBadgeEntity> arrayList, SnapshotMetadataEntity snapshotMetadataEntity) {
        this.a = i;
        this.b = gameEntity;
        this.c = i2;
        this.d = z;
        this.e = i3;
        this.f = j;
        this.g = j2;
        this.h = str;
        this.i = j3;
        this.j = str2;
        this.k = arrayList;
        this.l = snapshotMetadataEntity;
    }

    public ExtendedGameEntity(ExtendedGame extendedGame) {
        this.a = 2;
        Game m_ = extendedGame.m_();
        this.b = m_ == null ? null : new GameEntity(m_);
        this.c = extendedGame.l_();
        this.d = extendedGame.e();
        this.e = extendedGame.f();
        this.f = extendedGame.g();
        this.g = extendedGame.h();
        this.h = extendedGame.i();
        this.i = extendedGame.j();
        this.j = extendedGame.k();
        SnapshotMetadata l = extendedGame.l();
        this.l = l != null ? new SnapshotMetadataEntity(l) : null;
        ArrayList<GameBadge> k_ = extendedGame.k_();
        int size = k_.size();
        this.k = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            this.k.add((GameBadgeEntity) k_.get(i).a());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(ExtendedGame extendedGame) {
        return jy.a(extendedGame.m_(), Integer.valueOf(extendedGame.l_()), Boolean.valueOf(extendedGame.e()), Integer.valueOf(extendedGame.f()), Long.valueOf(extendedGame.g()), Long.valueOf(extendedGame.h()), extendedGame.i(), Long.valueOf(extendedGame.j()), extendedGame.k());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(ExtendedGame extendedGame, Object obj) {
        if (!(obj instanceof ExtendedGame)) {
            return false;
        }
        if (extendedGame == obj) {
            return true;
        }
        ExtendedGame extendedGame2 = (ExtendedGame) obj;
        return jy.a(extendedGame2.m_(), extendedGame.m_()) && jy.a(Integer.valueOf(extendedGame2.l_()), Integer.valueOf(extendedGame.l_())) && jy.a(Boolean.valueOf(extendedGame2.e()), Boolean.valueOf(extendedGame.e())) && jy.a(Integer.valueOf(extendedGame2.f()), Integer.valueOf(extendedGame.f())) && jy.a(Long.valueOf(extendedGame2.g()), Long.valueOf(extendedGame.g())) && jy.a(Long.valueOf(extendedGame2.h()), Long.valueOf(extendedGame.h())) && jy.a(extendedGame2.i(), extendedGame.i()) && jy.a(Long.valueOf(extendedGame2.j()), Long.valueOf(extendedGame.j())) && jy.a(extendedGame2.k(), extendedGame.k());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String b(ExtendedGame extendedGame) {
        return jy.a(extendedGame).a("Game", extendedGame.m_()).a("Availability", Integer.valueOf(extendedGame.l_())).a("Owned", Boolean.valueOf(extendedGame.e())).a("AchievementUnlockedCount", Integer.valueOf(extendedGame.f())).a("LastPlayedServerTimestamp", Long.valueOf(extendedGame.g())).a("PriceMicros", Long.valueOf(extendedGame.h())).a("FormattedPrice", extendedGame.i()).a("FullPriceMicros", Long.valueOf(extendedGame.j())).a("FormattedFullPrice", extendedGame.k()).a("Snapshot", extendedGame.l()).toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public boolean e() {
        return this.d;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public int f() {
        return this.e;
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public long g() {
        return this.f;
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public long h() {
        return this.g;
    }

    public int hashCode() {
        return a(this);
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public String i() {
        return this.h;
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public long j() {
        return this.i;
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public String k() {
        return this.j;
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public ArrayList<GameBadge> k_() {
        return new ArrayList<>(this.k);
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public SnapshotMetadata l() {
        return this.l;
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    public int l_() {
        return this.c;
    }

    public int m() {
        return this.a;
    }

    @Override // com.google.android.gms.games.internal.game.ExtendedGame
    /* renamed from: n, reason: merged with bridge method [inline-methods] */
    public GameEntity m_() {
        return this.b;
    }

    @Override // defpackage.ik
    /* renamed from: o, reason: merged with bridge method [inline-methods] */
    public ExtendedGame a() {
        return this;
    }

    public String toString() {
        return b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (!d()) {
            qt.a(this, parcel, i);
            return;
        }
        this.b.writeToParcel(parcel, i);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d ? 1 : 0);
        parcel.writeInt(this.e);
        parcel.writeLong(this.f);
        parcel.writeLong(this.g);
        parcel.writeString(this.h);
        parcel.writeLong(this.i);
        parcel.writeString(this.j);
        int size = this.k.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            this.k.get(i2).writeToParcel(parcel, i);
        }
    }
}
