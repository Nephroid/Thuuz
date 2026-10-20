package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import defpackage.akd;
import java.util.List;

/* loaded from: classes.dex */
public class ActivityRecognitionResult implements SafeParcelable {
    public static final akd CREATOR = new akd();
    public List<DetectedActivity> a;
    public long b;
    public long c;
    private final int d = 1;

    public ActivityRecognitionResult(int i, List<DetectedActivity> list, long j, long j2) {
        this.a = list;
        this.b = j;
        this.c = j2;
    }

    public int a() {
        return this.d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "ActivityRecognitionResult [probableActivities=" + this.a + ", timeMillis=" + this.b + ", elapsedRealtimeMillis=" + this.c + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        akd.a(this, parcel, i);
    }
}
