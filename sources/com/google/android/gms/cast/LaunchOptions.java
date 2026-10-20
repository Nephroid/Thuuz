package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aev;
import defpackage.gv;
import defpackage.jy;
import java.util.Locale;

/* loaded from: classes.dex */
public class LaunchOptions implements SafeParcelable {
    public static final Parcelable.Creator<LaunchOptions> CREATOR = new gv();
    private final int a;
    private boolean b;
    private String c;

    public LaunchOptions() {
        this(1, false, aev.a(Locale.getDefault()));
    }

    public LaunchOptions(int i, boolean z, String str) {
        this.a = i;
        this.b = z;
        this.c = str;
    }

    public int a() {
        return this.a;
    }

    public boolean b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LaunchOptions)) {
            return false;
        }
        LaunchOptions launchOptions = (LaunchOptions) obj;
        return this.b == launchOptions.b && aev.a(this.c, launchOptions.c);
    }

    public int hashCode() {
        return jy.a(Boolean.valueOf(this.b), this.c);
    }

    public String toString() {
        return String.format("LaunchOptions(relaunchIfRunning=%b, language=%s)", Boolean.valueOf(this.b), this.c);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        gv.a(this, parcel, i);
    }
}
