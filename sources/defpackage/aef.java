package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.hb;

/* loaded from: classes.dex */
public final class aef implements Parcelable.Creator<hb> {
    @Override // android.os.Parcelable.Creator
    @Deprecated
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public hb createFromParcel(Parcel parcel) {
        return new hb(parcel);
    }

    @Override // android.os.Parcelable.Creator
    @Deprecated
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public hb[] newArray(int i) {
        return new hb[i];
    }
}
