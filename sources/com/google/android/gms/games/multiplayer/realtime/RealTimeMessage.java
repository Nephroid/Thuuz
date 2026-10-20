package com.google.android.gms.games.multiplayer.realtime;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.kb;
import defpackage.rg;

/* loaded from: classes.dex */
public final class RealTimeMessage implements Parcelable {
    public static final Parcelable.Creator<RealTimeMessage> CREATOR = new rg();
    private final String a;
    private final byte[] b;
    private final int c;

    private RealTimeMessage(Parcel parcel) {
        this(parcel.readString(), parcel.createByteArray(), parcel.readInt());
    }

    public /* synthetic */ RealTimeMessage(Parcel parcel, rg rgVar) {
        this(parcel);
    }

    public RealTimeMessage(String str, byte[] bArr, int i) {
        this.a = (String) kb.a(str);
        this.b = (byte[]) ((byte[]) kb.a(bArr)).clone();
        this.c = i;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeByteArray(this.b);
        parcel.writeInt(this.c);
    }
}
