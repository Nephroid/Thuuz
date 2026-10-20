package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class kj implements Parcelable.Creator<CompletionEvent> {
    public static void a(CompletionEvent completionEvent, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, completionEvent.a);
        ke.a(parcel, 2, (Parcelable) completionEvent.b, i, false);
        ke.a(parcel, 3, completionEvent.c, false);
        ke.a(parcel, 4, (Parcelable) completionEvent.d, i, false);
        ke.a(parcel, 5, (Parcelable) completionEvent.e, i, false);
        ke.a(parcel, 6, (Parcelable) completionEvent.f, i, false);
        ke.b(parcel, 7, completionEvent.g, false);
        ke.a(parcel, 8, completionEvent.h);
        ke.a(parcel, 9, completionEvent.i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public CompletionEvent createFromParcel(Parcel parcel) {
        int i = 0;
        IBinder iBinder = null;
        int b = a.b(parcel);
        ArrayList<String> arrayList = null;
        MetadataBundle metadataBundle = null;
        ParcelFileDescriptor parcelFileDescriptor = null;
        ParcelFileDescriptor parcelFileDescriptor2 = null;
        String str = null;
        DriveId driveId = null;
        int i2 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i2 = a.g(parcel, a);
                    break;
                case 2:
                    driveId = (DriveId) a.a(parcel, a, DriveId.CREATOR);
                    break;
                case 3:
                    str = a.o(parcel, a);
                    break;
                case 4:
                    parcelFileDescriptor2 = (ParcelFileDescriptor) a.a(parcel, a, ParcelFileDescriptor.CREATOR);
                    break;
                case 5:
                    parcelFileDescriptor = (ParcelFileDescriptor) a.a(parcel, a, ParcelFileDescriptor.CREATOR);
                    break;
                case 6:
                    metadataBundle = (MetadataBundle) a.a(parcel, a, MetadataBundle.CREATOR);
                    break;
                case 7:
                    arrayList = a.C(parcel, a);
                    break;
                case 8:
                    i = a.g(parcel, a);
                    break;
                case 9:
                    iBinder = a.p(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new CompletionEvent(i2, driveId, str, parcelFileDescriptor2, parcelFileDescriptor, metadataBundle, arrayList, i, iBinder);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public CompletionEvent[] newArray(int i) {
        return new CompletionEvent[i];
    }
}
