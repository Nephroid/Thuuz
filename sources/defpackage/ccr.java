package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.viewpagerindicator.LinePageIndicator;

/* loaded from: classes.dex */
public final class ccr implements Parcelable.Creator<LinePageIndicator.SavedState> {
    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public LinePageIndicator.SavedState createFromParcel(Parcel parcel) {
        return new LinePageIndicator.SavedState(parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public LinePageIndicator.SavedState[] newArray(int i) {
        return new LinePageIndicator.SavedState[i];
    }
}
