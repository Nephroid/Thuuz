package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
// [removed legacy tracker import]
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;
import defpackage.jy;
import defpackage.ka;
import defpackage.pm;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class DataSourcesRequest implements SafeParcelable {
    public static final Parcelable.Creator<DataSourcesRequest> CREATOR = new pm();
    private final int a;
    private final List<DataType> b;
    private final List<Integer> c;
    private final boolean d;

    public DataSourcesRequest(int i, List<DataType> list, List<Integer> list2, boolean z) {
        this.a = i;
        this.b = list;
        this.c = list2;
        this.d = z;
    }

    public List<DataType> a() {
        return Collections.unmodifiableList(this.b);
    }

    public boolean b() {
        return this.d;
    }

    public int c() {
        return this.a;
    }

    public List<Integer> d() {
        return this.c;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        ka a = jy.a(this).a("dataTypes", this.b).a("sourceTypes", this.c);
        if (this.d) {
            a.a("includeDbOnlySources", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        }
        return a.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        pm.a(this, parcel, i);
    }
}
