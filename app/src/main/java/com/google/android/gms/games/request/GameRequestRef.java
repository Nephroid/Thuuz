package com.google.android.gms.games.request;

import android.os.Parcel;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;
import defpackage.im;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class GameRequestRef extends im implements GameRequest {
    private final int c;

    @Override // com.google.android.gms.games.request.GameRequest
    public int a_(String str) {
        for (int i = this.b; i < this.b + this.c; i++) {
            int a = this.a.a(i);
            if (this.a.c("recipient_external_player_id", i, a).equals(str)) {
                return this.a.b("recipient_status", i, a);
            }
        }
        return -1;
    }

    @Override // defpackage.ik
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public GameRequest a() {
        return new GameRequestEntity(this);
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public String d() {
        return e("external_request_id");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public Game e() {
        return new GameRef(this.a, this.b);
    }

    @Override // defpackage.im
    public boolean equals(Object obj) {
        return GameRequestEntity.a(this, obj);
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public Player f() {
        return new PlayerRef(this.a, b(), "sender_");
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public byte[] h() {
        return g("data");
    }

    @Override // defpackage.im
    public int hashCode() {
        return GameRequestEntity.a(this);
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public int i() {
        return c("type");
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public long j() {
        return b("creation_timestamp");
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public long k() {
        return b("expiration_timestamp");
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public int l() {
        return c("status");
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public List<Player> n() {
        ArrayList arrayList = new ArrayList(this.c);
        for (int i = 0; i < this.c; i++) {
            arrayList.add(new PlayerRef(this.a, this.b + i, "recipient_"));
        }
        return arrayList;
    }

    public String toString() {
        return GameRequestEntity.b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ((GameRequestEntity) a()).writeToParcel(parcel, i);
    }
}
