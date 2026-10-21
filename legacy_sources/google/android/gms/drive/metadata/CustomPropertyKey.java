package com.google.android.gms.drive.metadata;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.kb;
import defpackage.mf;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class CustomPropertyKey implements SafeParcelable {
    public static final Parcelable.Creator<CustomPropertyKey> CREATOR = new mf();
    private static final Pattern d = Pattern.compile("[\\w.!@$%^&*()/-]+");
    public final int a;
    public final String b;
    public final int c;

    public CustomPropertyKey(int i, String str, int i2) {
        boolean z = true;
        kb.a(str, "key");
        kb.b(d.matcher(str).matches(), "key name characters must be alphanumeric or one of .!@$%^&*()-_/");
        if (i2 != 0 && i2 != 1) {
            z = false;
        }
        kb.b(z, "visibility must be either PUBLIC or PRIVATE");
        this.a = i;
        this.b = str;
        this.c = i2;
    }

    public String a() {
        return this.b;
    }

    public int b() {
        return this.c;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CustomPropertyKey)) {
            return false;
        }
        CustomPropertyKey customPropertyKey = (CustomPropertyKey) obj;
        return customPropertyKey.a().equals(this.b) && customPropertyKey.b() == this.c;
    }

    public int hashCode() {
        return (this.b + this.c).hashCode();
    }

    public String toString() {
        return "CustomPropertyKey(" + this.b + "," + this.c + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        mf.a(this, parcel, i);
    }
}
