package com.google.android.gms.games;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.jy;
import defpackage.kb;
import defpackage.qm;

/* loaded from: classes.dex */
public final class PlayerLevel implements SafeParcelable {
    public static final qm CREATOR = new qm();
    private final int a;
    private final int b;
    private final long c;
    private final long d;

    public PlayerLevel(int i, int i2, long j, long j2) {
        kb.a(j >= 0, "Min XP must be positive!");
        kb.a(j2 > j, "Max XP must be more than min XP!");
        this.a = i;
        this.b = i2;
        this.c = j;
        this.d = j2;
    }

    public PlayerLevel(int i, long j, long j2) {
        this(1, i, j, j2);
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public long c() {
        return this.c;
    }

    public long d() {
        return this.d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlayerLevel)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        PlayerLevel playerLevel = (PlayerLevel) obj;
        return jy.a(Integer.valueOf(playerLevel.b()), Integer.valueOf(b())) && jy.a(Long.valueOf(playerLevel.c()), Long.valueOf(c())) && jy.a(Long.valueOf(playerLevel.d()), Long.valueOf(d()));
    }

    public int hashCode() {
        return jy.a(Integer.valueOf(this.b), Long.valueOf(this.c), Long.valueOf(this.d));
    }

    public String toString() {
        return jy.a(this).a("LevelNumber", Integer.valueOf(b())).a("MinXp", Long.valueOf(c())).a("MaxXp", Long.valueOf(d())).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        qm.a(this, parcel, i);
    }
}
