package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.abg;
import defpackage.abh;
import defpackage.zy;
import java.util.List;

@zy
/* loaded from: classes.dex */
public final class fi implements SafeParcelable {
    public static final abh CREATOR = new abh();
    public final int a;
    public final Bundle b;
    public final av c;
    public final ay d;
    public final String e;
    public final ApplicationInfo f;
    public final PackageInfo g;
    public final String h;
    public final String i;
    public final String j;
    public final gt k;
    public final Bundle l;
    public final int m;
    public final List<String> n;
    public final Bundle o;
    public final boolean p;

    public fi(int i, Bundle bundle, av avVar, ay ayVar, String str, ApplicationInfo applicationInfo, PackageInfo packageInfo, String str2, String str3, String str4, gt gtVar, Bundle bundle2, int i2, List<String> list, Bundle bundle3, boolean z) {
        this.a = i;
        this.b = bundle;
        this.c = avVar;
        this.d = ayVar;
        this.e = str;
        this.f = applicationInfo;
        this.g = packageInfo;
        this.h = str2;
        this.i = str3;
        this.j = str4;
        this.k = gtVar;
        this.l = bundle2;
        this.m = i2;
        this.n = list;
        this.o = bundle3;
        this.p = z;
    }

    public fi(abg abgVar, String str) {
        this(abgVar.a, abgVar.b, abgVar.c, abgVar.d, abgVar.e, abgVar.f, str, abgVar.g, abgVar.h, abgVar.j, abgVar.i, abgVar.k, abgVar.l, abgVar.m, abgVar.n);
    }

    public fi(Bundle bundle, av avVar, ay ayVar, String str, ApplicationInfo applicationInfo, PackageInfo packageInfo, String str2, String str3, String str4, gt gtVar, Bundle bundle2, int i, List<String> list, Bundle bundle3, boolean z) {
        this(4, bundle, avVar, ayVar, str, applicationInfo, packageInfo, str2, str3, str4, gtVar, bundle2, i, list, bundle3, z);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        abh.a(this, parcel, i);
    }
}
