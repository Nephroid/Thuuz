package com.google.android.gms.identity.intents;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.identity.intents.model.CountrySpecification;
import defpackage.rt;
import java.util.List;

/* loaded from: classes.dex */
public final class UserAddressRequest implements SafeParcelable {
    public static final Parcelable.Creator<UserAddressRequest> CREATOR = new rt();
    public List<CountrySpecification> a;
    private final int b;

    UserAddressRequest() {
        this.b = 1;
    }

    public UserAddressRequest(int i, List<CountrySpecification> list) {
        this.b = i;
        this.a = list;
    }

    public int a() {
        return this.b;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        rt.a(this, parcel, i);
    }
}
