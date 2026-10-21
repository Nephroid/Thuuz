package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aji;
import defpackage.kb;
import defpackage.kg;
import defpackage.kr;
import defpackage.lw;

/* loaded from: classes.dex */
public class DriveId implements SafeParcelable {
    public static final Parcelable.Creator<DriveId> CREATOR = new kg();
    public final int a;
    public final String b;
    public final long c;
    public final long d;
    private volatile String e = null;

    public DriveId(int i, String str, long j, long j2) {
        this.a = i;
        this.b = str;
        kb.b(!"".equals(str));
        kb.b((str == null && j == -1) ? false : true);
        this.c = j;
        this.d = j2;
    }

    public final String a() {
        if (this.e == null) {
            this.e = "DriveId:" + Base64.encodeToString(b(), 10);
        }
        return this.e;
    }

    final byte[] b() {
        kr krVar = new kr();
        krVar.a = this.a;
        krVar.b = this.b == null ? "" : this.b;
        krVar.c = this.c;
        krVar.d = this.d;
        return aji.a(krVar);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DriveId)) {
            return false;
        }
        DriveId driveId = (DriveId) obj;
        if (driveId.d == this.d) {
            return (driveId.c == -1 && this.c == -1) ? driveId.b.equals(this.b) : driveId.c == this.c;
        }
        lw.a("DriveId", "Attempt to compare invalid DriveId detected. Has local storage been cleared?");
        return false;
    }

    public int hashCode() {
        return this.c == -1 ? this.b.hashCode() : (String.valueOf(this.d) + String.valueOf(this.c)).hashCode();
    }

    public String toString() {
        return a();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        kg.a(this, parcel, i);
    }
}
