package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;
import defpackage.jy;
import defpackage.pd;
import defpackage.pp;
import defpackage.pq;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class StartBleScanRequest implements SafeParcelable {
    public static final Parcelable.Creator<StartBleScanRequest> CREATOR = new pd();
    private final int a;
    private final List<DataType> b;
    private final pp c;
    private final int d;

    public StartBleScanRequest(int i, List<DataType> list, IBinder iBinder, int i2) {
        this.a = i;
        this.b = list;
        this.c = pq.a(iBinder);
        this.d = i2;
    }

    public List<DataType> a() {
        return Collections.unmodifiableList(this.b);
    }

    public IBinder b() {
        return this.c.asBinder();
    }

    public int c() {
        return this.d;
    }

    public int d() {
        return this.a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return jy.a(this).a("dataTypes", this.b).a("timeoutSecs", Integer.valueOf(this.d)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        pd.a(this, parcel, i);
    }
}
