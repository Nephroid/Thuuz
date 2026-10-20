package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aie;
import defpackage.kb;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public final class ms implements SafeParcelable {
    public final int e;
    public final String f;
    public final int g;
    public static final ms a = a("test_type", 1);
    public static final ms b = a("trellis_store", 2);
    public static final ms c = a("labeled_place", 6);
    public static final Set<ms> d = Collections.unmodifiableSet(new HashSet(Arrays.asList(a, b, c)));
    public static final aie CREATOR = new aie();

    public ms(int i, String str, int i2) {
        kb.a(str);
        this.e = i;
        this.f = str;
        this.g = i2;
    }

    private static ms a(String str, int i) {
        return new ms(0, str, i);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        aie aieVar = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ms)) {
            return false;
        }
        ms msVar = (ms) obj;
        return this.f.equals(msVar.f) && this.g == msVar.g;
    }

    public int hashCode() {
        return this.f.hashCode();
    }

    public String toString() {
        return this.f;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        aie aieVar = CREATOR;
        aie.a(this, parcel, i);
    }
}
