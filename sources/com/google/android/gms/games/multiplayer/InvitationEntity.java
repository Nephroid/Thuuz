package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import defpackage.jy;
import defpackage.kb;
import defpackage.ra;
import defpackage.rb;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class InvitationEntity extends GamesDowngradeableSafeParcel implements Invitation {
    public static final Parcelable.Creator<InvitationEntity> CREATOR = new ra();
    private final int a;
    private final GameEntity b;
    private final String c;
    private final long d;
    private final int e;
    private final ParticipantEntity f;
    private final ArrayList<ParticipantEntity> g;
    private final int h;
    private final int i;

    public InvitationEntity(int i, GameEntity gameEntity, String str, long j, int i2, ParticipantEntity participantEntity, ArrayList<ParticipantEntity> arrayList, int i3, int i4) {
        this.a = i;
        this.b = gameEntity;
        this.c = str;
        this.d = j;
        this.e = i2;
        this.f = participantEntity;
        this.g = arrayList;
        this.h = i3;
        this.i = i4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InvitationEntity(Invitation invitation) {
        this.a = 2;
        this.b = new GameEntity(invitation.r_());
        this.c = invitation.e();
        this.d = invitation.g();
        this.e = invitation.h();
        this.h = invitation.i();
        this.i = invitation.j();
        String k = invitation.f().k();
        Participant participant = null;
        ArrayList<Participant> l = invitation.l();
        int size = l.size();
        this.g = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            Participant participant2 = l.get(i);
            if (participant2.k().equals(k)) {
                participant = participant2;
            }
            this.g.add((ParticipantEntity) participant2.a());
        }
        kb.a(participant, "Must have a valid inviter!");
        this.f = (ParticipantEntity) participant.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(Invitation invitation) {
        return jy.a(invitation.r_(), invitation.e(), Long.valueOf(invitation.g()), Integer.valueOf(invitation.h()), invitation.f(), invitation.l(), Integer.valueOf(invitation.i()), Integer.valueOf(invitation.j()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(Invitation invitation, Object obj) {
        if (!(obj instanceof Invitation)) {
            return false;
        }
        if (invitation == obj) {
            return true;
        }
        Invitation invitation2 = (Invitation) obj;
        return jy.a(invitation2.r_(), invitation.r_()) && jy.a(invitation2.e(), invitation.e()) && jy.a(Long.valueOf(invitation2.g()), Long.valueOf(invitation.g())) && jy.a(Integer.valueOf(invitation2.h()), Integer.valueOf(invitation.h())) && jy.a(invitation2.f(), invitation.f()) && jy.a(invitation2.l(), invitation.l()) && jy.a(Integer.valueOf(invitation2.i()), Integer.valueOf(invitation.i())) && jy.a(Integer.valueOf(invitation2.j()), Integer.valueOf(invitation.j()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String b(Invitation invitation) {
        return jy.a(invitation).a("Game", invitation.r_()).a("InvitationId", invitation.e()).a("CreationTimestamp", Long.valueOf(invitation.g())).a("InvitationType", Integer.valueOf(invitation.h())).a("Inviter", invitation.f()).a("Participants", invitation.l()).a("Variant", Integer.valueOf(invitation.i())).a("AvailableAutoMatchSlots", Integer.valueOf(invitation.j())).toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public String e() {
        return this.c;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public Participant f() {
        return this.f;
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public long g() {
        return this.d;
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public int h() {
        return this.e;
    }

    public int hashCode() {
        return a(this);
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public int i() {
        return this.h;
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public int j() {
        return this.i;
    }

    public int k() {
        return this.a;
    }

    @Override // defpackage.rf
    public ArrayList<Participant> l() {
        return new ArrayList<>(this.g);
    }

    @Override // defpackage.ik
    /* renamed from: m, reason: merged with bridge method [inline-methods] */
    public Invitation a() {
        return this;
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public Game r_() {
        return this.b;
    }

    public String toString() {
        return b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (!d()) {
            rb.a(this, parcel, i);
            return;
        }
        this.b.writeToParcel(parcel, i);
        parcel.writeString(this.c);
        parcel.writeLong(this.d);
        parcel.writeInt(this.e);
        this.f.writeToParcel(parcel, i);
        int size = this.g.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            this.g.get(i2).writeToParcel(parcel, i);
        }
    }
}
