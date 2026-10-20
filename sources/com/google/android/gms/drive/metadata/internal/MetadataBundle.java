package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.jy;
import defpackage.kb;
import defpackage.lw;
import defpackage.ly;
import defpackage.mn;
import defpackage.mq;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public final class MetadataBundle implements SafeParcelable {
    public static final Parcelable.Creator<MetadataBundle> CREATOR = new mq();
    public final int a;
    public final Bundle b;

    public MetadataBundle(int i, Bundle bundle) {
        this.a = i;
        this.b = (Bundle) kb.a(bundle);
        this.b.setClassLoader(getClass().getClassLoader());
        ArrayList arrayList = new ArrayList();
        for (String str : this.b.keySet()) {
            if (mn.a(str) == null) {
                arrayList.add(str);
                lw.a("MetadataBundle", "Ignored unknown metadata field in bundle: " + str);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.b.remove((String) it.next());
        }
    }

    public <T> T a(ly<T> lyVar) {
        return lyVar.a(this.b);
    }

    public Set<ly<?>> a() {
        HashSet hashSet = new HashSet();
        Iterator<String> it = this.b.keySet().iterator();
        while (it.hasNext()) {
            hashSet.add(mn.a(it.next()));
        }
        return hashSet;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MetadataBundle)) {
            return false;
        }
        MetadataBundle metadataBundle = (MetadataBundle) obj;
        Set<String> keySet = this.b.keySet();
        if (!keySet.equals(metadataBundle.b.keySet())) {
            return false;
        }
        for (String str : keySet) {
            if (!jy.a(this.b.get(str), metadataBundle.b.get(str))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 1;
        Iterator<String> it = this.b.keySet().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            i = this.b.get(it.next()).hashCode() + (i2 * 31);
        }
    }

    public String toString() {
        return "MetadataBundle [values=" + this.b + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        mq.a(this, parcel, i);
    }
}
