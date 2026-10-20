package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;

/* loaded from: classes.dex */
public final class rg implements Parcelable.Creator<RealTimeMessage> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public RealTimeMessage createFromParcel(Parcel parcel) {
        return new RealTimeMessage(parcel, null);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public RealTimeMessage[] newArray(int i) {
        return new RealTimeMessage[i];
    }
}
