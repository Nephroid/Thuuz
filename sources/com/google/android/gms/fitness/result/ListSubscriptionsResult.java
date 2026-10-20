package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Subscription;
import defpackage.jy;
import defpackage.qe;
import java.util.List;

/* loaded from: classes.dex */
public class ListSubscriptionsResult implements SafeParcelable {
    public static final Parcelable.Creator<ListSubscriptionsResult> CREATOR = new qe();
    private final int a;
    private final List<Subscription> b;
    private final Status c;

    public ListSubscriptionsResult(int i, List<Subscription> list, Status status) {
        this.a = i;
        this.b = list;
        this.c = status;
    }

    private boolean a(ListSubscriptionsResult listSubscriptionsResult) {
        return this.c.equals(listSubscriptionsResult.c) && jy.a(this.b, listSubscriptionsResult.b);
    }

    public List<Subscription> a() {
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
        return this == obj || ((obj instanceof ListSubscriptionsResult) && a((ListSubscriptionsResult) obj));
    }

    public int hashCode() {
        return jy.a(this.c, this.b);
    }

    public String toString() {
        return jy.a(this).a("status", this.c).a("dataSets", this.b).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        qe.a(this, parcel, i);
    }
}
