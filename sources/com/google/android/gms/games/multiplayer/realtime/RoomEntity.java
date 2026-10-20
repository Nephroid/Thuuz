package com.google.android.gms.games.multiplayer.realtime;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import defpackage.jy;
import defpackage.ri;
import defpackage.rj;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class RoomEntity extends GamesDowngradeableSafeParcel implements Room {
    public static final Parcelable.Creator<RoomEntity> CREATOR = new ri();
    private final int a;
    private final String b;
    private final String c;
    private final long d;
    private final int e;
    private final String f;
    private final int g;
    private final Bundle h;
    private final ArrayList<ParticipantEntity> i;
    private final int j;

    public RoomEntity(int i, String str, String str2, long j, int i2, String str3, int i3, Bundle bundle, ArrayList<ParticipantEntity> arrayList, int i4) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = j;
        this.e = i2;
        this.f = str3;
        this.g = i3;
        this.h = bundle;
        this.i = arrayList;
        this.j = i4;
    }

    public RoomEntity(Room room) {
        this.a = 2;
        this.b = room.v_();
        this.c = room.w_();
        this.d = room.x_();
        this.e = room.e();
        this.f = room.f();
        this.g = room.g();
        this.h = room.h();
        ArrayList<Participant> l = room.l();
        int size = l.size();
        this.i = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            this.i.add((ParticipantEntity) l.get(i).a());
        }
        this.j = room.i();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(Room room) {
        return jy.a(room.v_(), room.w_(), Long.valueOf(room.x_()), Integer.valueOf(room.e()), room.f(), Integer.valueOf(room.g()), room.h(), room.l(), Integer.valueOf(room.i()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(Room room, Object obj) {
        if (!(obj instanceof Room)) {
            return false;
        }
        if (room == obj) {
            return true;
        }
        Room room2 = (Room) obj;
        return jy.a(room2.v_(), room.v_()) && jy.a(room2.w_(), room.w_()) && jy.a(Long.valueOf(room2.x_()), Long.valueOf(room.x_())) && jy.a(Integer.valueOf(room2.e()), Integer.valueOf(room.e())) && jy.a(room2.f(), room.f()) && jy.a(Integer.valueOf(room2.g()), Integer.valueOf(room.g())) && jy.a(room2.h(), room.h()) && jy.a(room2.l(), room.l()) && jy.a(Integer.valueOf(room2.i()), Integer.valueOf(room.i()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String b(Room room) {
        return jy.a(room).a("RoomId", room.v_()).a("CreatorId", room.w_()).a("CreationTimestamp", Long.valueOf(room.x_())).a("RoomStatus", Integer.valueOf(room.e())).a("Description", room.f()).a("Variant", Integer.valueOf(room.g())).a("AutoMatchCriteria", room.h()).a("Participants", room.l()).a("AutoMatchWaitEstimateSeconds", Integer.valueOf(room.i())).toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public int e() {
        return this.e;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public String f() {
        return this.f;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public int g() {
        return this.g;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public Bundle h() {
        return this.h;
    }

    public int hashCode() {
        return a(this);
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public int i() {
        return this.j;
    }

    public int j() {
        return this.a;
    }

    @Override // defpackage.ik
    /* renamed from: k, reason: merged with bridge method [inline-methods] */
    public Room a() {
        return this;
    }

    @Override // defpackage.rf
    public ArrayList<Participant> l() {
        return new ArrayList<>(this.i);
    }

    public String toString() {
        return b(this);
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public String v_() {
        return this.b;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public String w_() {
        return this.c;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (!d()) {
            rj.a(this, parcel, i);
            return;
        }
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeLong(this.d);
        parcel.writeInt(this.e);
        parcel.writeString(this.f);
        parcel.writeInt(this.g);
        parcel.writeBundle(this.h);
        int size = this.i.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            this.i.get(i2).writeToParcel(parcel, i);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.Room
    public long x_() {
        return this.d;
    }
}
