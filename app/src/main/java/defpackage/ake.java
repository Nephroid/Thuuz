package defpackage;

import com.google.android.gms.location.DetectedActivity;
import java.util.Comparator;

/* loaded from: classes.dex */
public final class ake implements Comparator<DetectedActivity> {
    @Override // java.util.Comparator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(DetectedActivity detectedActivity, DetectedActivity detectedActivity2) {
        int compareTo = Integer.valueOf(detectedActivity2.b()).compareTo(Integer.valueOf(detectedActivity.b()));
        return compareTo == 0 ? Integer.valueOf(detectedActivity.a()).compareTo(Integer.valueOf(detectedActivity2.a())) : compareTo;
    }
}
