package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Session;
import defpackage.jy;
import defpackage.qg;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class SessionStopResult implements SafeParcelable {
    public static final Parcelable.Creator<SessionStopResult> CREATOR = new qg();
    private final int a;
    private final Status b;
    private final List<Session> c;

    public SessionStopResult(int i, Status status, List<Session> list) {
        this.a = i;
        this.b = status;
        this.c = Collections.unmodifiableList(list);
    }

    private boolean a(SessionStopResult sessionStopResult) {
        return this.b.equals(sessionStopResult.b) && jy.a(this.c, sessionStopResult.c);
    }

    public Status a() {
        return this.b;
    }

    public List<Session> b() {
        return this.c;
    }

    public int c() {
        return this.a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof SessionStopResult) && a((SessionStopResult) obj));
    }

    public int hashCode() {
        return jy.a(this.b, this.c);
    }

    public String toString() {
        return jy.a(this).a("status", this.b).a("sessions", this.c).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        qg.a(this, parcel, i);
    }
}
