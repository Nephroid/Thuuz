package com.google.android.gms.auth.api;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.gr;
import java.util.List;

/* loaded from: classes.dex */
public class GoogleAuthApiRequest implements SafeParcelable {
    public static final gr CREATOR = new gr();
    public final int a;
    public String b;
    public String c;
    public String d;
    public String e;
    public Bundle f;
    public String g;
    public List<String> h;
    public String i;
    public int j;
    public Bundle k;
    public byte[] l;
    public long m;

    public GoogleAuthApiRequest(int i, String str, String str2, String str3, String str4, Bundle bundle, String str5, List<String> list, String str6, int i2, Bundle bundle2, byte[] bArr, long j) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = bundle;
        this.g = str5;
        this.h = list;
        this.i = str6;
        this.j = i2;
        this.k = bundle2;
        this.l = bArr;
        this.m = j;
    }

    public String a() {
        return this.g;
    }

    public String b() {
        if (this.h.size() == 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.h.size()) {
                return stringBuffer.toString();
            }
            stringBuffer.append(this.h.get(i2));
            if (i2 != this.h.size() - 1) {
                stringBuffer.append(" ");
            }
            i = i2 + 1;
        }
    }

    public String c() {
        String b = b();
        if (b == null) {
            return null;
        }
        return this.i + b;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "{ API: " + this.b + "/" + this.c + ", Scope: " + c() + ", Account: " + a() + " }";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        gr.a(this, parcel, i);
    }
}
