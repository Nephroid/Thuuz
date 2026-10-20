package com.google.android.gms.games.quest;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.jy;
import defpackage.rm;

/* loaded from: classes.dex */
public final class MilestoneEntity implements SafeParcelable, Milestone {
    public static final rm CREATOR = new rm();
    private final int a;
    private final String b;
    private final long c;
    private final long d;
    private final byte[] e;
    private final int f;
    private final String g;

    public MilestoneEntity(int i, String str, long j, long j2, byte[] bArr, int i2, String str2) {
        this.a = i;
        this.b = str;
        this.c = j;
        this.d = j2;
        this.e = bArr;
        this.f = i2;
        this.g = str2;
    }

    public MilestoneEntity(Milestone milestone) {
        this.a = 4;
        this.b = milestone.z_();
        this.c = milestone.c();
        this.d = milestone.f();
        this.f = milestone.e();
        this.g = milestone.d();
        byte[] g = milestone.g();
        if (g == null) {
            this.e = null;
        } else {
            this.e = new byte[g.length];
            System.arraycopy(g, 0, this.e, 0, g.length);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(Milestone milestone) {
        return jy.a(milestone.z_(), Long.valueOf(milestone.c()), Long.valueOf(milestone.f()), Integer.valueOf(milestone.e()), milestone.d());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(Milestone milestone, Object obj) {
        if (!(obj instanceof Milestone)) {
            return false;
        }
        if (milestone == obj) {
            return true;
        }
        Milestone milestone2 = (Milestone) obj;
        return jy.a(milestone2.z_(), milestone.z_()) && jy.a(Long.valueOf(milestone2.c()), Long.valueOf(milestone.c())) && jy.a(Long.valueOf(milestone2.f()), Long.valueOf(milestone.f())) && jy.a(Integer.valueOf(milestone2.e()), Integer.valueOf(milestone.e())) && jy.a(milestone2.d(), milestone.d());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String b(Milestone milestone) {
        return jy.a(milestone).a("MilestoneId", milestone.z_()).a("CurrentProgress", Long.valueOf(milestone.c())).a("TargetProgress", Long.valueOf(milestone.f())).a("State", Integer.valueOf(milestone.e())).a("CompletionRewardData", milestone.g()).a("EventId", milestone.d()).toString();
    }

    @Override // com.google.android.gms.games.quest.Milestone
    public long c() {
        return this.c;
    }

    @Override // com.google.android.gms.games.quest.Milestone
    public String d() {
        return this.g;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.games.quest.Milestone
    public int e() {
        return this.f;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    @Override // com.google.android.gms.games.quest.Milestone
    public long f() {
        return this.d;
    }

    @Override // com.google.android.gms.games.quest.Milestone
    public byte[] g() {
        return this.e;
    }

    public int h() {
        return this.a;
    }

    public int hashCode() {
        return a(this);
    }

    @Override // defpackage.ik
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public Milestone a() {
        return this;
    }

    public String toString() {
        return b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        rm.a(this, parcel, i);
    }

    @Override // com.google.android.gms.games.quest.Milestone
    public String z_() {
        return this.b;
    }
}
