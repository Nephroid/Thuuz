package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.player.MostRecentGameInfo;
import com.google.android.gms.games.internal.player.MostRecentGameInfoRef;
import defpackage.im;
import defpackage.qy;

/* loaded from: classes.dex */
public final class PlayerRef extends im implements Player {
    private final qy c;
    private final PlayerLevelInfo d;
    private final MostRecentGameInfoRef e;

    public PlayerRef(DataHolder dataHolder, int i) {
        this(dataHolder, i, null);
    }

    public PlayerRef(DataHolder dataHolder, int i, String str) {
        super(dataHolder, i);
        this.c = new qy(str);
        this.e = new MostRecentGameInfoRef(dataHolder, i, this.c);
        if (!p()) {
            this.d = null;
            return;
        }
        int c = c(this.c.k);
        int c2 = c(this.c.n);
        PlayerLevel playerLevel = new PlayerLevel(c, b(this.c.l), b(this.c.m));
        this.d = new PlayerLevelInfo(b(this.c.j), b(this.c.p), playerLevel, c != c2 ? new PlayerLevel(c2, b(this.c.m), b(this.c.o)) : playerLevel);
    }

    private boolean p() {
        return (i(this.c.j) || b(this.c.j) == -1) ? false : true;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.games.Player
    public String e() {
        return e(this.c.d);
    }

    @Override // defpackage.im
    public boolean equals(Object obj) {
        return PlayerEntity.a(this, obj);
    }

    @Override // com.google.android.gms.games.Player
    public Uri f() {
        return h(this.c.e);
    }

    @Override // com.google.android.gms.games.Player
    public String f_() {
        return e(this.c.a);
    }

    @Override // com.google.android.gms.games.Player
    public String g() {
        return e(this.c.f);
    }

    @Override // com.google.android.gms.games.Player
    public String g_() {
        return e(this.c.b);
    }

    @Override // com.google.android.gms.games.Player
    public long h() {
        return b(this.c.g);
    }

    @Override // com.google.android.gms.games.Player
    public Uri h_() {
        return h(this.c.c);
    }

    @Override // defpackage.im
    public int hashCode() {
        return PlayerEntity.a(this);
    }

    @Override // com.google.android.gms.games.Player
    public long i() {
        if (!a(this.c.i) || i(this.c.i)) {
            return -1L;
        }
        return b(this.c.i);
    }

    @Override // com.google.android.gms.games.Player
    public int j() {
        return c(this.c.h);
    }

    @Override // com.google.android.gms.games.Player
    public boolean k() {
        return d(this.c.s);
    }

    @Override // com.google.android.gms.games.Player
    public String l() {
        return e(this.c.q);
    }

    @Override // com.google.android.gms.games.Player
    public PlayerLevelInfo m() {
        return this.d;
    }

    @Override // com.google.android.gms.games.Player
    public MostRecentGameInfo n() {
        if (i(this.c.t)) {
            return null;
        }
        return this.e;
    }

    @Override // defpackage.ik
    /* renamed from: o, reason: merged with bridge method [inline-methods] */
    public Player a() {
        return new PlayerEntity(this);
    }

    public String toString() {
        return PlayerEntity.b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ((PlayerEntity) a()).writeToParcel(parcel, i);
    }
}
