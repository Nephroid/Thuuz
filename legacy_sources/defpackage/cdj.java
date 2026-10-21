package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.viewpagerindicator.UnderlinePageIndicator;

/* loaded from: classes.dex */
public final class cdj implements Parcelable.Creator<UnderlinePageIndicator.SavedState> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public UnderlinePageIndicator.SavedState createFromParcel(Parcel parcel) {
        return new UnderlinePageIndicator.SavedState(parcel, null);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public UnderlinePageIndicator.SavedState[] newArray(int i) {
        return new UnderlinePageIndicator.SavedState[i];
    }
}
