package com.google.android.gms.games.internal.request;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.GameRequestEntity;
import defpackage.ix;
import defpackage.jy;
import defpackage.qz;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class GameRequestCluster implements SafeParcelable, GameRequest {
    public static final qz CREATOR = new qz();
    private final int a;
    private final ArrayList<GameRequestEntity> b;

    public GameRequestCluster(int i, ArrayList<GameRequestEntity> arrayList) {
        this.a = i;
        this.b = arrayList;
        o();
    }

    private void o() {
        ix.a(!this.b.isEmpty());
        GameRequestEntity gameRequestEntity = this.b.get(0);
        int size = this.b.size();
        for (int i = 1; i < size; i++) {
            GameRequestEntity gameRequestEntity2 = this.b.get(i);
            ix.a(gameRequestEntity.i() == gameRequestEntity2.i(), "All the requests must be of the same type");
            ix.a(gameRequestEntity.f().equals(gameRequestEntity2.f()), "All the requests must be from the same sender");
        }
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public int a_(String str) {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public int b() {
        return this.a;
    }

    public ArrayList<GameRequest> c() {
        return new ArrayList<>(this.b);
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public String d() {
        return this.b.get(0).d();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public Game e() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GameRequestCluster)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        GameRequestCluster gameRequestCluster = (GameRequestCluster) obj;
        if (gameRequestCluster.b.size() != this.b.size()) {
            return false;
        }
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            if (!this.b.get(i).equals(gameRequestCluster.b.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public Player f() {
        return this.b.get(0).f();
    }

    @Override // com.google.android.gms.games.request.GameRequest
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public ArrayList<Player> n() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public byte[] h() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    public int hashCode() {
        return jy.a(this.b.toArray());
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public int i() {
        return this.b.get(0).i();
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public long j() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public long k() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public int l() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    @Override // defpackage.ik
    /* renamed from: m, reason: merged with bridge method [inline-methods] */
    public GameRequest a() {
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        qz.a(this, parcel, i);
    }
}
