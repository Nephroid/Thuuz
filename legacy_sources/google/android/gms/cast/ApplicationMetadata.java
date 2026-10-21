package com.google.android.gms.cast;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aev;
import defpackage.gt;
import defpackage.jy;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class ApplicationMetadata implements SafeParcelable {
    public static final Parcelable.Creator<ApplicationMetadata> CREATOR = new gt();
    String a;
    String b;
    List<WebImage> c;
    public List<String> d;
    String e;
    Uri f;
    private final int g;

    private ApplicationMetadata() {
        this.g = 1;
        this.c = new ArrayList();
        this.d = new ArrayList();
    }

    public ApplicationMetadata(int i, String str, String str2, List<WebImage> list, List<String> list2, String str3, Uri uri) {
        this.g = i;
        this.a = str;
        this.b = str2;
        this.c = list;
        this.d = list2;
        this.e = str3;
        this.f = uri;
    }

    public int a() {
        return this.g;
    }

    public String b() {
        return this.a;
    }

    public String c() {
        return this.b;
    }

    public String d() {
        return this.e;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Uri e() {
        return this.f;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ApplicationMetadata)) {
            return false;
        }
        ApplicationMetadata applicationMetadata = (ApplicationMetadata) obj;
        return aev.a(this.a, applicationMetadata.a) && aev.a(this.c, applicationMetadata.c) && aev.a(this.b, applicationMetadata.b) && aev.a(this.d, applicationMetadata.d) && aev.a(this.e, applicationMetadata.e) && aev.a(this.f, applicationMetadata.f);
    }

    public List<WebImage> f() {
        return this.c;
    }

    public int hashCode() {
        return jy.a(Integer.valueOf(this.g), this.a, this.b, this.c, this.d, this.e, this.f);
    }

    public String toString() {
        return this.b;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        gt.a(this, parcel, i);
    }
}
