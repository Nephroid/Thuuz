package com.google.android.gms.games.internal.multiplayer;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.InvitationEntity;
import com.google.android.gms.games.multiplayer.Participant;
import defpackage.ix;
import defpackage.jy;
import defpackage.qw;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class ZInvitationCluster implements SafeParcelable, Invitation {
    public static final qw CREATOR = new qw();
    private final int a;
    private final ArrayList<InvitationEntity> b;

    public ZInvitationCluster(int i, ArrayList<InvitationEntity> arrayList) {
        this.a = i;
        this.b = arrayList;
        m();
    }

    private void m() {
        ix.a(!this.b.isEmpty());
        InvitationEntity invitationEntity = this.b.get(0);
        int size = this.b.size();
        for (int i = 1; i < size; i++) {
            ix.a(invitationEntity.f().equals(this.b.get(i).f()), "All the invitations must be from the same inviter");
        }
    }

    public int b() {
        return this.a;
    }

    public ArrayList<Invitation> c() {
        return new ArrayList<>(this.b);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public String e() {
        return this.b.get(0).e();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ZInvitationCluster)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        ZInvitationCluster zInvitationCluster = (ZInvitationCluster) obj;
        if (zInvitationCluster.b.size() != this.b.size()) {
            return false;
        }
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            if (!this.b.get(i).equals(zInvitationCluster.b.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public Participant f() {
        return this.b.get(0).f();
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public long g() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public int h() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public int hashCode() {
        return jy.a(this.b.toArray());
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public int i() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public int j() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    @Override // defpackage.ik
    /* renamed from: k, reason: merged with bridge method [inline-methods] */
    public Invitation a() {
        return this;
    }

    @Override // defpackage.rf
    public ArrayList<Participant> l() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    @Override // com.google.android.gms.games.multiplayer.Invitation
    public Game r_() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        qw.a(this, parcel, i);
    }
}
