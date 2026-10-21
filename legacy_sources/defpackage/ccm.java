package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.viewpagerindicator.CirclePageIndicator;

/* loaded from: classes.dex */
public final class ccm implements Parcelable.Creator<CirclePageIndicator.SavedState> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public CirclePageIndicator.SavedState createFromParcel(Parcel parcel) {
        return new CirclePageIndicator.SavedState(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public CirclePageIndicator.SavedState[] newArray(int i) {
        return new CirclePageIndicator.SavedState[i];
    }
}
