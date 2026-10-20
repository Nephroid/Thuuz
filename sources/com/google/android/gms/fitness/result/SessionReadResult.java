package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.data.q;
import defpackage.jy;
import defpackage.qf;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class SessionReadResult implements SafeParcelable {
    public static final Parcelable.Creator<SessionReadResult> CREATOR = new qf();
    private final int a;
    private final List<Session> b;
    private final List<q> c;
    private final Status d;

    public SessionReadResult(int i, List<Session> list, List<q> list2, Status status) {
        this.a = i;
        this.b = list;
        this.c = Collections.unmodifiableList(list2);
        this.d = status;
    }

    private boolean a(SessionReadResult sessionReadResult) {
        return this.d.equals(sessionReadResult.d) && jy.a(this.b, sessionReadResult.b) && jy.a(this.c, sessionReadResult.c);
    }

    public List<Session> a() {
        return this.b;
    }

    public List<q> b() {
        return this.c;
    }

    public Status c() {
        return this.d;
    }

    public int d() {
        return this.a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof SessionReadResult) && a((SessionReadResult) obj));
    }

    public int hashCode() {
        return jy.a(this.d, this.b, this.c);
    }

    public String toString() {
        return jy.a(this).a("status", this.d).a("sessions", this.b).a("sessionDataSets", this.c).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        qf.a(this, parcel, i);
    }
}
