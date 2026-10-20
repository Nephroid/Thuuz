package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.viewpagerindicator.TitlePageIndicator;

/* loaded from: classes.dex */
public final class cdg implements Parcelable.Creator<TitlePageIndicator.SavedState> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public TitlePageIndicator.SavedState createFromParcel(Parcel parcel) {
        return new TitlePageIndicator.SavedState(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public TitlePageIndicator.SavedState[] newArray(int i) {
        return new TitlePageIndicator.SavedState[i];
    }
}
