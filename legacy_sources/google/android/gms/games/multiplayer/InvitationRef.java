package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import com.google.android.gms.games.Game;
import defpackage.im;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class InvitationRef extends im implements Invitation {
    private final Game c;
    private final ParticipantRef d;
    private final ArrayList<Participant> e;

    @Override // defpackage.ik
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public Invitation a() {
        return new InvitationEntity(this);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public String e() {
        return e("external_invitation_id");
    }

    @Override // defpackage.im
    public boolean equals(Object obj) {
        return InvitationEntity.a(this, obj);
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public Participant f() {
        return this.d;
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public long g() {
        return Math.max(b("creation_timestamp"), b("last_modified_timestamp"));
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public int h() {
        return c("type");
    }

    @Override // defpackage.im
    public int hashCode() {
        return InvitationEntity.a(this);
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public int i() {
        return c("variant");
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public int j() {
        if (d("has_automatch_criteria")) {
            return c("automatch_max_players");
        }
        return 0;
    }

    @Override // defpackage.rf
    public ArrayList<Participant> l() {
        return this.e;
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public Game r_() {
        return this.c;
    }

    public String toString() {
        return InvitationEntity.b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ((InvitationEntity) a()).writeToParcel(parcel, i);
    }
}
