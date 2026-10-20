package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.iv;
import defpackage.jy;

/* loaded from: classes.dex */
public final class WebImage implements SafeParcelable {
    public static final Parcelable.Creator<WebImage> CREATOR = new iv();
    private final int a;
    private final Uri b;
    private final int c;
    private final int d;

    public WebImage(int i, Uri uri, int i2, int i3) {
        this.a = i;
        this.b = uri;
        this.c = i2;
        this.d = i3;
    }

    public int a() {
        return this.a;
    }

    public Uri b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof WebImage)) {
            return false;
        }
        WebImage webImage = (WebImage) obj;
        return jy.a(this.b, webImage.b) && this.c == webImage.c && this.d == webImage.d;
    }

    public int hashCode() {
        return jy.a(this.b, Integer.valueOf(this.c), Integer.valueOf(this.d));
    }

    public String toString() {
        return String.format("Image %dx%d %s", Integer.valueOf(this.c), Integer.valueOf(this.d), this.b.toString());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        iv.a(this, parcel, i);
    }
}
