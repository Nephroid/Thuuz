package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import org.jraf.android.backport.switchwidget.TwoStatePreference;

/* loaded from: classes.dex */
public final class cdq implements Parcelable.Creator<TwoStatePreference.SavedState> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public TwoStatePreference.SavedState createFromParcel(Parcel parcel) {
        return new TwoStatePreference.SavedState(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public TwoStatePreference.SavedState[] newArray(int i) {
        return new TwoStatePreference.SavedState[i];
    }
}
