package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.aev;
import defpackage.gu;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class CastDevice implements SafeParcelable {
    public static final Parcelable.Creator<CastDevice> CREATOR = new gu();
    public String a;
    private final int b;
    private String c;
    private Inet4Address d;
    private String e;
    private String f;
    private String g;
    private int h;
    private List<WebImage> i;
    private int j;
    private int k;

    private CastDevice() {
        this(3, null, null, null, null, null, -1, new ArrayList(), 0, -1);
    }

    public CastDevice(int i, String str, String str2, String str3, String str4, String str5, int i2, List<WebImage> list, int i3, int i4) {
        this.b = i;
        this.c = str;
        this.a = str2;
        if (this.a != null) {
            try {
                InetAddress byName = InetAddress.getByName(this.a);
                if (byName instanceof Inet4Address) {
                    this.d = (Inet4Address) byName;
                }
            } catch (UnknownHostException e) {
                this.d = null;
            }
        }
        this.e = str3;
        this.f = str4;
        this.g = str5;
        this.h = i2;
        this.i = list;
        this.j = i3;
        this.k = i4;
    }

    public int a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.e;
    }

    public String d() {
        return this.f;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String e() {
        return this.g;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CastDevice)) {
            return false;
        }
        CastDevice castDevice = (CastDevice) obj;
        return b() == null ? castDevice.b() == null : aev.a(this.c, castDevice.c) && aev.a(this.d, castDevice.d) && aev.a(this.f, castDevice.f) && aev.a(this.e, castDevice.e) && aev.a(this.g, castDevice.g) && this.h == castDevice.h && aev.a(this.i, castDevice.i) && this.j == castDevice.j && this.k == castDevice.k;
    }

    public int f() {
        return this.h;
    }

    public List<WebImage> g() {
        return Collections.unmodifiableList(this.i);
    }

    public int h() {
        return this.j;
    }

    public int hashCode() {
        if (this.c == null) {
            return 0;
        }
        return this.c.hashCode();
    }

    public int i() {
        return this.k;
    }

    public String toString() {
        return String.format("\"%s\" (%s)", this.e, this.c);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        gu.a(this, parcel, i);
    }
}
