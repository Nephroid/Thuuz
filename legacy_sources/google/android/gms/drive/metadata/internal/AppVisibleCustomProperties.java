package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.kb;
import defpackage.mi;
import defpackage.mj;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class AppVisibleCustomProperties implements SafeParcelable, Iterable<CustomProperty> {
    public static final Parcelable.Creator<AppVisibleCustomProperties> CREATOR = new mj();
    public static final AppVisibleCustomProperties a = new mi().a();
    public final int b;
    public final List<CustomProperty> c;

    public AppVisibleCustomProperties(int i, Collection<CustomProperty> collection) {
        this.b = i;
        kb.a(collection);
        this.c = new ArrayList(collection);
    }

    private AppVisibleCustomProperties(Collection<CustomProperty> collection) {
        this(1, collection);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // java.lang.Iterable
    public Iterator<CustomProperty> iterator() {
        return this.c.iterator();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        mj.a(this, parcel, i);
    }
}
