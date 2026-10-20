package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.ji;
import defpackage.afq;
import defpackage.afr;
import defpackage.afs;
import defpackage.kb;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public class jm implements SafeParcelable {
    public static final afr CREATOR = new afr();
    private final int a;
    private final HashMap<String, HashMap<String, ji.a<?, ?>>> b;
    private final ArrayList<a> c = null;
    private final String d;

    /* loaded from: classes.dex */
    public class a implements SafeParcelable {
        public static final afs CREATOR = new afs();
        public final int a;
        public final String b;
        public final ArrayList<b> c;

        public a(int i, String str, ArrayList<b> arrayList) {
            this.a = i;
            this.b = str;
            this.c = arrayList;
        }

        a(String str, HashMap<String, ji.a<?, ?>> hashMap) {
            this.a = 1;
            this.b = str;
            this.c = a(hashMap);
        }

        private static ArrayList<b> a(HashMap<String, ji.a<?, ?>> hashMap) {
            if (hashMap == null) {
                return null;
            }
            ArrayList<b> arrayList = new ArrayList<>();
            for (String str : hashMap.keySet()) {
                arrayList.add(new b(str, hashMap.get(str)));
            }
            return arrayList;
        }

        HashMap<String, ji.a<?, ?>> a() {
            HashMap<String, ji.a<?, ?>> hashMap = new HashMap<>();
            int size = this.c.size();
            for (int i = 0; i < size; i++) {
                b bVar = this.c.get(i);
                hashMap.put(bVar.b, bVar.c);
            }
            return hashMap;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            afs afsVar = CREATOR;
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            afs afsVar = CREATOR;
            afs.a(this, parcel, i);
        }
    }

    /* loaded from: classes.dex */
    public class b implements SafeParcelable {
        public static final afq CREATOR = new afq();
        public final int a;
        public final String b;
        public final ji.a<?, ?> c;

        public b(int i, String str, ji.a<?, ?> aVar) {
            this.a = i;
            this.b = str;
            this.c = aVar;
        }

        b(String str, ji.a<?, ?> aVar) {
            this.a = 1;
            this.b = str;
            this.c = aVar;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            afq afqVar = CREATOR;
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            afq afqVar = CREATOR;
            afq.a(this, parcel, i);
        }
    }

    public jm(int i, ArrayList<a> arrayList, String str) {
        this.a = i;
        this.b = a(arrayList);
        this.d = (String) kb.a(str);
        a();
    }

    private static HashMap<String, HashMap<String, ji.a<?, ?>>> a(ArrayList<a> arrayList) {
        HashMap<String, HashMap<String, ji.a<?, ?>>> hashMap = new HashMap<>();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            a aVar = arrayList.get(i);
            hashMap.put(aVar.b, aVar.a());
        }
        return hashMap;
    }

    public HashMap<String, ji.a<?, ?>> a(String str) {
        return this.b.get(str);
    }

    public void a() {
        Iterator<String> it = this.b.keySet().iterator();
        while (it.hasNext()) {
            HashMap<String, ji.a<?, ?>> hashMap = this.b.get(it.next());
            Iterator<String> it2 = hashMap.keySet().iterator();
            while (it2.hasNext()) {
                hashMap.get(it2.next()).a(this);
            }
        }
    }

    public int b() {
        return this.a;
    }

    public ArrayList<a> c() {
        ArrayList<a> arrayList = new ArrayList<>();
        for (String str : this.b.keySet()) {
            arrayList.add(new a(str, this.b.get(str)));
        }
        return arrayList;
    }

    public String d() {
        return this.d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        afr afrVar = CREATOR;
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String str : this.b.keySet()) {
            sb.append(str).append(":\n");
            HashMap<String, ji.a<?, ?>> hashMap = this.b.get(str);
            for (String str2 : hashMap.keySet()) {
                sb.append("  ").append(str2).append(": ");
                sb.append(hashMap.get(str2));
            }
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        afr afrVar = CREATOR;
        afr.a(this, parcel, i);
    }
}
