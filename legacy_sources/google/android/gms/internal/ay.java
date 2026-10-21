package com.google.android.gms.internal;

import android.content.Context;
import android.os.Parcel;
import android.util.DisplayMetrics;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.adm;
import defpackage.db;
import defpackage.dd;
import defpackage.tp;
import defpackage.zy;

@zy
/* loaded from: classes.dex */
public final class ay implements SafeParcelable {
    public static final tp CREATOR = new tp();
    public final int a;
    public final String b;
    public final int c;
    public final int d;
    public final boolean e;
    public final int f;
    public final int g;
    public final ay[] h;

    public ay() {
        this(2, "interstitial_mb", 0, 0, true, 0, 0, null);
    }

    public ay(int i, String str, int i2, int i3, boolean z, int i4, int i5, ay[] ayVarArr) {
        this.a = i;
        this.b = str;
        this.c = i2;
        this.d = i3;
        this.e = z;
        this.f = i4;
        this.g = i5;
        this.h = ayVarArr;
    }

    public ay(Context context, db dbVar) {
        this(context, new db[]{dbVar});
    }

    public ay(Context context, db[] dbVarArr) {
        int i;
        db dbVar = dbVarArr[0];
        this.a = 2;
        this.e = false;
        this.f = dbVar.b();
        this.c = dbVar.a();
        boolean z = this.f == -1;
        boolean z2 = this.c == -2;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (z) {
            this.g = a(displayMetrics);
            i = (int) (this.g / displayMetrics.density);
        } else {
            int i2 = this.f;
            this.g = adm.a(displayMetrics, this.f);
            i = i2;
        }
        int c = z2 ? c(displayMetrics) : this.c;
        this.d = adm.a(displayMetrics, c);
        if (z || z2) {
            this.b = i + "x" + c + "_as";
        } else {
            this.b = dbVar.toString();
        }
        if (dbVarArr.length <= 1) {
            this.h = null;
            return;
        }
        this.h = new ay[dbVarArr.length];
        for (int i3 = 0; i3 < dbVarArr.length; i3++) {
            this.h[i3] = new ay(context, dbVarArr[i3]);
        }
    }

    public ay(ay ayVar, ay[] ayVarArr) {
        this(2, ayVar.b, ayVar.c, ayVar.d, ayVar.e, ayVar.f, ayVar.g, ayVarArr);
    }

    public static int a(DisplayMetrics displayMetrics) {
        return displayMetrics.widthPixels;
    }

    public static int b(DisplayMetrics displayMetrics) {
        return (int) (c(displayMetrics) * displayMetrics.density);
    }

    private static int c(DisplayMetrics displayMetrics) {
        int i = (int) (displayMetrics.heightPixels / displayMetrics.density);
        if (i <= 400) {
            return 32;
        }
        return i <= 720 ? 50 : 90;
    }

    public db a() {
        return dd.a(this.f, this.c, this.b);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        tp.a(this, parcel, i);
    }
}
