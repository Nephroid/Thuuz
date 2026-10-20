package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.drive.internal.OnEventResponse;

/* loaded from: classes.dex */
public class kw implements Parcelable.Creator<OnEventResponse> {
    public static void a(OnEventResponse onEventResponse, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, onEventResponse.a);
        ke.a(parcel, 2, onEventResponse.b);
        ke.a(parcel, 3, (Parcelable) onEventResponse.c, i, false);
        ke.a(parcel, 5, (Parcelable) onEventResponse.d, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public OnEventResponse createFromParcel(Parcel parcel) {
        CompletionEvent completionEvent;
        ChangeEvent changeEvent;
        int i;
        int i2;
        CompletionEvent completionEvent2 = null;
        int i3 = 0;
        int b = a.b(parcel);
        ChangeEvent changeEvent2 = null;
        int i4 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    CompletionEvent completionEvent3 = completionEvent2;
                    changeEvent = changeEvent2;
                    i = i3;
                    i2 = a.g(parcel, a);
                    completionEvent = completionEvent3;
                    break;
                case 2:
                    i2 = i4;
                    ChangeEvent changeEvent3 = changeEvent2;
                    i = a.g(parcel, a);
                    completionEvent = completionEvent2;
                    changeEvent = changeEvent3;
                    break;
                case 3:
                    i = i3;
                    i2 = i4;
                    CompletionEvent completionEvent4 = completionEvent2;
                    changeEvent = (ChangeEvent) a.a(parcel, a, ChangeEvent.CREATOR);
                    completionEvent = completionEvent4;
                    break;
                case 4:
                default:
                    a.b(parcel, a);
                    completionEvent = completionEvent2;
                    changeEvent = changeEvent2;
                    i = i3;
                    i2 = i4;
                    break;
                case 5:
                    completionEvent = (CompletionEvent) a.a(parcel, a, CompletionEvent.CREATOR);
                    changeEvent = changeEvent2;
                    i = i3;
                    i2 = i4;
                    break;
            }
            i4 = i2;
            i3 = i;
            changeEvent2 = changeEvent;
            completionEvent2 = completionEvent;
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new OnEventResponse(i4, i3, changeEvent2, completionEvent2);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public OnEventResponse[] newArray(int i) {
        return new OnEventResponse[i];
    }
}
