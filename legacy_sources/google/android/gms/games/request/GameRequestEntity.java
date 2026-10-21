package com.google.android.gms.games.request;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import defpackage.jy;
import defpackage.ro;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public final class GameRequestEntity implements SafeParcelable, GameRequest {
    public static final ro CREATOR = new ro();
    private final int a;
    private final GameEntity b;
    private final PlayerEntity c;
    private final byte[] d;
    private final String e;
    private final ArrayList<PlayerEntity> f;
    private final int g;
    private final long h;
    private final long i;
    private final Bundle j;
    private final int k;

    public GameRequestEntity(int i, GameEntity gameEntity, PlayerEntity playerEntity, byte[] bArr, String str, ArrayList<PlayerEntity> arrayList, int i2, long j, long j2, Bundle bundle, int i3) {
        this.a = i;
        this.b = gameEntity;
        this.c = playerEntity;
        this.d = bArr;
        this.e = str;
        this.f = arrayList;
        this.g = i2;
        this.h = j;
        this.i = j2;
        this.j = bundle;
        this.k = i3;
    }

    public GameRequestEntity(GameRequest gameRequest) {
        this.a = 2;
        this.b = new GameEntity(gameRequest.e());
        this.c = new PlayerEntity(gameRequest.f());
        this.e = gameRequest.d();
        this.g = gameRequest.i();
        this.h = gameRequest.j();
        this.i = gameRequest.k();
        this.k = gameRequest.l();
        byte[] h = gameRequest.h();
        if (h == null) {
            this.d = null;
        } else {
            this.d = new byte[h.length];
            System.arraycopy(h, 0, this.d, 0, h.length);
        }
        List<Player> n = gameRequest.n();
        int size = n.size();
        this.f = new ArrayList<>(size);
        this.j = new Bundle();
        for (int i = 0; i < size; i++) {
            Player a = n.get(i).a();
            String f_ = a.f_();
            this.f.add((PlayerEntity) a);
            this.j.putInt(f_, gameRequest.a_(f_));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(GameRequest gameRequest) {
        return jy.a(gameRequest.e(), gameRequest.n(), gameRequest.d(), gameRequest.f(), c(gameRequest), Integer.valueOf(gameRequest.i()), Long.valueOf(gameRequest.j()), Long.valueOf(gameRequest.k()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(GameRequest gameRequest, Object obj) {
        if (!(obj instanceof GameRequest)) {
            return false;
        }
        if (gameRequest == obj) {
            return true;
        }
        GameRequest gameRequest2 = (GameRequest) obj;
        return jy.a(gameRequest2.e(), gameRequest.e()) && jy.a(gameRequest2.n(), gameRequest.n()) && jy.a(gameRequest2.d(), gameRequest.d()) && jy.a(gameRequest2.f(), gameRequest.f()) && Arrays.equals(c(gameRequest2), c(gameRequest)) && jy.a(Integer.valueOf(gameRequest2.i()), Integer.valueOf(gameRequest.i())) && jy.a(Long.valueOf(gameRequest2.j()), Long.valueOf(gameRequest.j())) && jy.a(Long.valueOf(gameRequest2.k()), Long.valueOf(gameRequest.k()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String b(GameRequest gameRequest) {
        return jy.a(gameRequest).a("Game", gameRequest.e()).a("Sender", gameRequest.f()).a("Recipients", gameRequest.n()).a("Data", gameRequest.h()).a("RequestId", gameRequest.d()).a("Type", Integer.valueOf(gameRequest.i())).a("CreationTimestamp", Long.valueOf(gameRequest.j())).a("ExpirationTimestamp", Long.valueOf(gameRequest.k())).toString();
    }

    private static int[] c(GameRequest gameRequest) {
        List<Player> n = gameRequest.n();
        int size = n.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = gameRequest.a_(n.get(i).f_());
        }
        return iArr;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public int a_(String str) {
        return this.j.getInt(str, 0);
    }

    public int b() {
        return this.a;
    }

    public Bundle c() {
        return this.j;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public String d() {
        return this.e;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public Game e() {
        return this.b;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public Player f() {
        return this.c;
    }

    @Override // defpackage.ik
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public GameRequest a() {
        return this;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public byte[] h() {
        return this.d;
    }

    public int hashCode() {
        return a(this);
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public int i() {
        return this.g;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public long j() {
        return this.h;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public long k() {
        return this.i;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public int l() {
        return this.k;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public List<Player> n() {
        return new ArrayList(this.f);
    }

    public String toString() {
        return b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ro.a(this, parcel, i);
    }
}
