package com.google.android.gms.games.multiplayer;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import defpackage.jy;
import defpackage.rc;
import defpackage.rd;

/* loaded from: classes.dex */
public final class ParticipantEntity extends GamesDowngradeableSafeParcel implements Participant {
    public static final Parcelable.Creator<ParticipantEntity> CREATOR = new rc();
    private final int a;
    private final String b;
    private final String c;
    private final Uri d;
    private final Uri e;
    private final int f;
    private final String g;
    private final boolean h;
    private final PlayerEntity i;
    private final int j;
    private final ParticipantResult k;
    private final String l;
    private final String m;

    public ParticipantEntity(int i, String str, String str2, Uri uri, Uri uri2, int i2, String str3, boolean z, PlayerEntity playerEntity, int i3, ParticipantResult participantResult, String str4, String str5) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = uri;
        this.e = uri2;
        this.f = i2;
        this.g = str3;
        this.h = z;
        this.i = playerEntity;
        this.j = i3;
        this.k = participantResult;
        this.l = str4;
        this.m = str5;
    }

    public ParticipantEntity(Participant participant) {
        this.a = 3;
        this.b = participant.k();
        this.c = participant.f();
        this.d = participant.g();
        this.e = participant.i();
        this.f = participant.s_();
        this.g = participant.t_();
        this.h = participant.e();
        Player l = participant.l();
        this.i = l == null ? null : new PlayerEntity(l);
        this.j = participant.u_();
        this.k = participant.m();
        this.l = participant.h();
        this.m = participant.j();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(Participant participant) {
        return jy.a(participant.l(), Integer.valueOf(participant.s_()), participant.t_(), Boolean.valueOf(participant.e()), participant.f(), participant.g(), participant.i(), Integer.valueOf(participant.u_()), participant.m(), participant.k());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(Participant participant, Object obj) {
        if (!(obj instanceof Participant)) {
            return false;
        }
        if (participant == obj) {
            return true;
        }
        Participant participant2 = (Participant) obj;
        return jy.a(participant2.l(), participant.l()) && jy.a(Integer.valueOf(participant2.s_()), Integer.valueOf(participant.s_())) && jy.a(participant2.t_(), participant.t_()) && jy.a(Boolean.valueOf(participant2.e()), Boolean.valueOf(participant.e())) && jy.a(participant2.f(), participant.f()) && jy.a(participant2.g(), participant.g()) && jy.a(participant2.i(), participant.i()) && jy.a(Integer.valueOf(participant2.u_()), Integer.valueOf(participant.u_())) && jy.a(participant2.m(), participant.m()) && jy.a(participant2.k(), participant.k());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String b(Participant participant) {
        return jy.a(participant).a("ParticipantId", participant.k()).a("Player", participant.l()).a("Status", Integer.valueOf(participant.s_())).a("ClientAddress", participant.t_()).a("ConnectedToRoom", Boolean.valueOf(participant.e())).a("DisplayName", participant.f()).a("IconImage", participant.g()).a("IconImageUrl", participant.h()).a("HiResImage", participant.i()).a("HiResImageUrl", participant.j()).a("Capabilities", Integer.valueOf(participant.u_())).a("Result", participant.m()).toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public boolean e() {
        return this.h;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public String f() {
        return this.i == null ? this.c : this.i.g_();
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public Uri g() {
        return this.i == null ? this.d : this.i.h_();
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public String h() {
        return this.i == null ? this.l : this.i.e();
    }

    public int hashCode() {
        return a(this);
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public Uri i() {
        return this.i == null ? this.e : this.i.f();
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public String j() {
        return this.i == null ? this.m : this.i.g();
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public String k() {
        return this.b;
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public Player l() {
        return this.i;
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public ParticipantResult m() {
        return this.k;
    }

    public int n() {
        return this.a;
    }

    @Override // defpackage.ik
    /* renamed from: o, reason: merged with bridge method [inline-methods] */
    public Participant a() {
        return this;
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public int s_() {
        return this.f;
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public String t_() {
        return this.g;
    }

    public String toString() {
        return b(this);
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public int u_() {
        return this.j;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (!d()) {
            rd.a(this, parcel, i);
            return;
        }
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d == null ? null : this.d.toString());
        parcel.writeString(this.e != null ? this.e.toString() : null);
        parcel.writeInt(this.f);
        parcel.writeString(this.g);
        parcel.writeInt(this.h ? 1 : 0);
        parcel.writeInt(this.i != null ? 1 : 0);
        if (this.i != null) {
            this.i.writeToParcel(parcel, i);
        }
    }
}
