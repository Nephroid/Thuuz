package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.view.View;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.iw;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes.dex */
public final class ClientSettings {
    private final ParcelableClientSettings a;
    private final View b;

    /* loaded from: classes.dex */
    public final class ParcelableClientSettings implements SafeParcelable {
        public static final iw CREATOR = new iw();
        private final int a;
        private final String b;
        private final List<String> c;
        private final int d;
        private final String e;

        public ParcelableClientSettings(int i, String str, List<String> list, int i2, String str2) {
            this.c = new ArrayList();
            this.a = i;
            this.b = str;
            this.c.addAll(list);
            this.d = i2;
            this.e = str2;
        }

        public ParcelableClientSettings(String str, Collection<String> collection, int i, String str2) {
            this(3, str, new ArrayList(collection), i, str2);
        }

        public String a() {
            return this.b;
        }

        public int b() {
            return this.d;
        }

        public String c() {
            return this.e;
        }

        public List<String> d() {
            return new ArrayList(this.c);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public int e() {
            return this.a;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            iw.a(this, parcel, i);
        }
    }

    public ClientSettings(String str, Collection<String> collection, int i, View view, String str2) {
        this.a = new ParcelableClientSettings(str, collection, i, str2);
        this.b = view;
    }

    public String a() {
        return this.a.a();
    }

    public List<String> b() {
        return this.a.d();
    }
}
