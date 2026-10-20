package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.nx;

/* loaded from: classes.dex */
public class ParcelableCollaborator implements SafeParcelable {
    public static final Parcelable.Creator<ParcelableCollaborator> CREATOR = new nx();
    public final int a;
    public final boolean b;
    public final boolean c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public final String h;

    public ParcelableCollaborator(int i, boolean z, boolean z2, String str, String str2, String str3, String str4, String str5) {
        this.a = i;
        this.b = z;
        this.c = z2;
        this.d = str;
        this.e = str2;
        this.f = str3;
        this.g = str4;
        this.h = str5;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ParcelableCollaborator) {
            return this.d.equals(((ParcelableCollaborator) obj).d);
        }
        return false;
    }

    public int hashCode() {
        return this.d.hashCode();
    }

    public String toString() {
        return "Collaborator [isMe=" + this.b + ", isAnonymous=" + this.c + ", sessionId=" + this.d + ", userId=" + this.e + ", displayName=" + this.f + ", color=" + this.g + ", photoUrl=" + this.h + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        nx.a(this, parcel, i);
    }
}
