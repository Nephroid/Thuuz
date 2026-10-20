package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.kb;
import defpackage.qr;
import defpackage.re;

/* loaded from: classes.dex */
public final class ParticipantResult implements SafeParcelable {
    public static final re CREATOR = new re();
    private final int a;
    private final String b;
    private final int c;
    private final int d;

    public ParticipantResult(int i, String str, int i2, int i3) {
        this.a = i;
        this.b = (String) kb.a(str);
        kb.a(qr.a(i2));
        this.c = i2;
        this.d = i3;
    }

    public ParticipantResult(String str, int i, int i2) {
        this(1, str, i, i2);
    }

    public int a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        re.a(this, parcel, i);
    }
}
