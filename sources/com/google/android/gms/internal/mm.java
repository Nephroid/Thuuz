package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aib;
import defpackage.jy;
import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.LogFactory;

/* loaded from: classes.dex */
public final class mm implements SafeParcelable {
    public static final aib CREATOR = new aib();
    public static final long a = TimeUnit.HOURS.toMillis(1);
    public final int b;
    private final mi c;
    private final long d;
    private final int e;

    public mm(int i, mi miVar, long j, int i2) {
        this.b = i;
        this.c = miVar;
        this.d = j;
        this.e = i2;
    }

    public mi a() {
        return this.c;
    }

    public long b() {
        return this.d;
    }

    public int c() {
        return this.e;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        aib aibVar = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof mm)) {
            return false;
        }
        mm mmVar = (mm) obj;
        return jy.a(this.c, mmVar.c) && this.d == mmVar.d && this.e == mmVar.e;
    }

    public int hashCode() {
        return jy.a(this.c, Long.valueOf(this.d), Integer.valueOf(this.e));
    }

    public String toString() {
        return jy.a(this).a("filter", this.c).a("interval", Long.valueOf(this.d)).a(LogFactory.PRIORITY_KEY, Integer.valueOf(this.e)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        aib aibVar = CREATOR;
        aib.a(this, parcel, i);
    }
}
