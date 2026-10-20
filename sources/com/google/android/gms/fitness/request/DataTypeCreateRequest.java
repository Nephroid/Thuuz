package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Field;
import com.samsung.android.sdk.richnotification.Utilities;
import defpackage.jy;
import defpackage.pn;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class DataTypeCreateRequest implements SafeParcelable {
    public static final Parcelable.Creator<DataTypeCreateRequest> CREATOR = new pn();
    private final int a;
    private final String b;
    private final List<Field> c;

    public DataTypeCreateRequest(int i, String str, List<Field> list) {
        this.a = i;
        this.b = str;
        this.c = Collections.unmodifiableList(list);
    }

    private boolean a(DataTypeCreateRequest dataTypeCreateRequest) {
        return jy.a(this.b, dataTypeCreateRequest.b) && jy.a(this.c, dataTypeCreateRequest.c);
    }

    public String a() {
        return this.b;
    }

    public List<Field> b() {
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
        return obj == this || ((obj instanceof DataTypeCreateRequest) && a((DataTypeCreateRequest) obj));
    }

    public int hashCode() {
        return jy.a(this.b, this.c);
    }

    public String toString() {
        return jy.a(this).a(Utilities.DB_KEY_IMAGE_NAME, this.b).a("fields", this.c).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        pn.a(this, parcel, i);
    }
}
