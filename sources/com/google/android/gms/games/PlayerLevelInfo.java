package com.google.android.gms.games;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.jy;
import defpackage.kb;
import defpackage.qn;

/* loaded from: classes.dex */
public final class PlayerLevelInfo implements SafeParcelable {
    public static final qn CREATOR = new qn();
    private final int a;
    private final long b;
    private final long c;
    private final PlayerLevel d;
    private final PlayerLevel e;

    public PlayerLevelInfo(int i, long j, long j2, PlayerLevel playerLevel, PlayerLevel playerLevel2) {
        kb.a(j != -1);
        kb.a(playerLevel);
        kb.a(playerLevel2);
        this.a = i;
        this.b = j;
        this.c = j2;
        this.d = playerLevel;
        this.e = playerLevel2;
    }

    public PlayerLevelInfo(long j, long j2, PlayerLevel playerLevel, PlayerLevel playerLevel2) {
        this(1, j, j2, playerLevel, playerLevel2);
    }

    public int a() {
        return this.a;
    }

    public long b() {
        return this.b;
    }

    public long c() {
        return this.c;
    }

    public PlayerLevel d() {
        return this.d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PlayerLevel e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlayerLevelInfo)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        PlayerLevelInfo playerLevelInfo = (PlayerLevelInfo) obj;
        return jy.a(Long.valueOf(this.b), Long.valueOf(playerLevelInfo.b)) && jy.a(Long.valueOf(this.c), Long.valueOf(playerLevelInfo.c)) && jy.a(this.d, playerLevelInfo.d) && jy.a(this.e, playerLevelInfo.e);
    }

    public int hashCode() {
        return jy.a(Long.valueOf(this.b), Long.valueOf(this.c), this.d, this.e);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        qn.a(this, parcel, i);
    }
}
