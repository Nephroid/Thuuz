package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.Session;
import defpackage.jy;
import defpackage.pv;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class SessionInsertRequest implements SafeParcelable {
    public static final Parcelable.Creator<SessionInsertRequest> CREATOR = new pv();
    private final int a;
    private final Session b;
    private final List<DataSet> c;
    private final List<DataPoint> d;

    public SessionInsertRequest(int i, Session session, List<DataSet> list, List<DataPoint> list2) {
        this.a = i;
        this.b = session;
        this.c = Collections.unmodifiableList(list);
        this.d = Collections.unmodifiableList(list2);
    }

    private boolean a(SessionInsertRequest sessionInsertRequest) {
        return jy.a(this.b, sessionInsertRequest.b) && jy.a(this.c, sessionInsertRequest.c) && jy.a(this.d, sessionInsertRequest.d);
    }

    public Session a() {
        return this.b;
    }

    public List<DataSet> b() {
        return this.c;
    }

    public List<DataPoint> c() {
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
        return obj == this || ((obj instanceof SessionInsertRequest) && a((SessionInsertRequest) obj));
    }

    public int hashCode() {
        return jy.a(this.b, this.c, this.d);
    }

    public String toString() {
        return jy.a(this).a("session", this.b).a("dataSets", this.c).a("aggregateDataPoints", this.d).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        pv.a(this, parcel, i);
    }
}
