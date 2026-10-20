package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.afm;
import defpackage.afn;
import defpackage.afo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class jf implements afo<String, Integer>, SafeParcelable {
    public static final afm CREATOR = new afm();
    private final int a;
    private final HashMap<String, Integer> b;
    private final HashMap<Integer, String> c;
    private final ArrayList<a> d;

    /* loaded from: classes.dex */
    public final class a implements SafeParcelable {
        public static final afn CREATOR = new afn();
        public final int a;
        public final String b;
        public final int c;

        public a(int i, String str, int i2) {
            this.a = i;
            this.b = str;
            this.c = i2;
        }

        a(String str, int i) {
            this.a = 1;
            this.b = str;
            this.c = i;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            afn afnVar = CREATOR;
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            afn afnVar = CREATOR;
            afn.a(this, parcel, i);
        }
    }

    public jf() {
        this.a = 1;
        this.b = new HashMap<>();
        this.c = new HashMap<>();
        this.d = null;
    }

    public jf(int i, ArrayList<a> arrayList) {
        this.a = i;
        this.b = new HashMap<>();
        this.c = new HashMap<>();
        this.d = null;
        a(arrayList);
    }

    private void a(ArrayList<a> arrayList) {
        Iterator<a> it = arrayList.iterator();
        while (it.hasNext()) {
            a next = it.next();
            a(next.b, next.c);
        }
    }

    public int a() {
        return this.a;
    }

    public jf a(String str, int i) {
        this.b.put(str, Integer.valueOf(i));
        this.c.put(Integer.valueOf(i), str);
        return this;
    }

    @Override // defpackage.afo
    public String a(Integer num) {
        String str = this.c.get(num);
        return (str == null && this.b.containsKey("gms_unknown")) ? "gms_unknown" : str;
    }

    public ArrayList<a> b() {
        ArrayList<a> arrayList = new ArrayList<>();
        for (String str : this.b.keySet()) {
            arrayList.add(new a(str, this.b.get(str).intValue()));
        }
        return arrayList;
    }

    @Override // defpackage.afo
    public int c() {
        return 7;
    }

    @Override // defpackage.afo
    public int d() {
        return 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        afm afmVar = CREATOR;
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        afm afmVar = CREATOR;
        afm.a(this, parcel, i);
    }
}
