package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import defpackage.jy;
import defpackage.qc;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class DataSourcesResult implements SafeParcelable {
    public static final Parcelable.Creator<DataSourcesResult> CREATOR = new qc();
    private final int a;
    private final List<DataSource> b;
    private final Status c;

    public DataSourcesResult(int i, List<DataSource> list, Status status) {
        this.a = i;
        this.b = Collections.unmodifiableList(list);
        this.c = status;
    }

    private boolean a(DataSourcesResult dataSourcesResult) {
        return this.c.equals(dataSourcesResult.c) && jy.a(this.b, dataSourcesResult.b);
    }

    public List<DataSource> a() {
        return this.b;
    }

    public Status b() {
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
        return this == obj || ((obj instanceof DataSourcesResult) && a((DataSourcesResult) obj));
    }

    public int hashCode() {
        return jy.a(this.c, this.b);
    }

    public String toString() {
        return jy.a(this).a("status", this.c).a("dataSets", this.b).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        qc.a(this, parcel, i);
    }
}
