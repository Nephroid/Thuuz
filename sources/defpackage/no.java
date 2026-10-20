package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.widget.ViewDragHelper;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.realtime.internal.event.ObjectChangedDetails;
import com.google.android.gms.drive.realtime.internal.event.ParcelableEvent;
import com.google.android.gms.drive.realtime.internal.event.ReferenceShiftedDetails;
import com.google.android.gms.drive.realtime.internal.event.TextDeletedDetails;
import com.google.android.gms.drive.realtime.internal.event.TextInsertedDetails;
import com.google.android.gms.drive.realtime.internal.event.ValueChangedDetails;
import com.google.android.gms.drive.realtime.internal.event.ValuesAddedDetails;
import com.google.android.gms.drive.realtime.internal.event.ValuesRemovedDetails;
import com.google.android.gms.drive.realtime.internal.event.ValuesSetDetails;
import java.util.ArrayList;
import org.apache.http.protocol.HTTP;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public class no implements Parcelable.Creator<ParcelableEvent> {
    public static void a(ParcelableEvent parcelableEvent, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, parcelableEvent.a);
        ke.a(parcel, 2, parcelableEvent.b, false);
        ke.a(parcel, 3, parcelableEvent.c, false);
        ke.b(parcel, 4, parcelableEvent.d, false);
        ke.a(parcel, 5, parcelableEvent.e);
        ke.a(parcel, 6, parcelableEvent.f, false);
        ke.a(parcel, 7, parcelableEvent.g, false);
        ke.a(parcel, 8, (Parcelable) parcelableEvent.h, i, false);
        ke.a(parcel, 9, (Parcelable) parcelableEvent.i, i, false);
        ke.a(parcel, 10, (Parcelable) parcelableEvent.j, i, false);
        ke.a(parcel, 11, (Parcelable) parcelableEvent.k, i, false);
        ke.a(parcel, 12, (Parcelable) parcelableEvent.l, i, false);
        ke.a(parcel, 13, (Parcelable) parcelableEvent.m, i, false);
        ke.a(parcel, 14, (Parcelable) parcelableEvent.n, i, false);
        ke.a(parcel, 15, (Parcelable) parcelableEvent.o, i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ParcelableEvent createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        ArrayList<String> arrayList = null;
        boolean z = false;
        String str3 = null;
        String str4 = null;
        TextInsertedDetails textInsertedDetails = null;
        TextDeletedDetails textDeletedDetails = null;
        ValuesAddedDetails valuesAddedDetails = null;
        ValuesRemovedDetails valuesRemovedDetails = null;
        ValuesSetDetails valuesSetDetails = null;
        ValueChangedDetails valueChangedDetails = null;
        ReferenceShiftedDetails referenceShiftedDetails = null;
        ObjectChangedDetails objectChangedDetails = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    i = a.g(parcel, a);
                    break;
                case 2:
                    str = a.o(parcel, a);
                    break;
                case 3:
                    str2 = a.o(parcel, a);
                    break;
                case 4:
                    arrayList = a.C(parcel, a);
                    break;
                case 5:
                    z = a.c(parcel, a);
                    break;
                case 6:
                    str3 = a.o(parcel, a);
                    break;
                case 7:
                    str4 = a.o(parcel, a);
                    break;
                case 8:
                    textInsertedDetails = (TextInsertedDetails) a.a(parcel, a, TextInsertedDetails.CREATOR);
                    break;
                case 9:
                    textDeletedDetails = (TextDeletedDetails) a.a(parcel, a, TextDeletedDetails.CREATOR);
                    break;
                case 10:
                    valuesAddedDetails = (ValuesAddedDetails) a.a(parcel, a, ValuesAddedDetails.CREATOR);
                    break;
                case 11:
                    valuesRemovedDetails = (ValuesRemovedDetails) a.a(parcel, a, ValuesRemovedDetails.CREATOR);
                    break;
                case DateTimeConstants.DECEMBER /* 12 */:
                    valuesSetDetails = (ValuesSetDetails) a.a(parcel, a, ValuesSetDetails.CREATOR);
                    break;
                case HTTP.CR /* 13 */:
                    valueChangedDetails = (ValueChangedDetails) a.a(parcel, a, ValueChangedDetails.CREATOR);
                    break;
                case 14:
                    referenceShiftedDetails = (ReferenceShiftedDetails) a.a(parcel, a, ReferenceShiftedDetails.CREATOR);
                    break;
                case ViewDragHelper.EDGE_ALL /* 15 */:
                    objectChangedDetails = (ObjectChangedDetails) a.a(parcel, a, ObjectChangedDetails.CREATOR);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new ParcelableEvent(i, str, str2, arrayList, z, str3, str4, textInsertedDetails, textDeletedDetails, valuesAddedDetails, valuesRemovedDetails, valuesSetDetails, valueChangedDetails, referenceShiftedDetails, objectChangedDetails);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ParcelableEvent[] newArray(int i) {
        return new ParcelableEvent[i];
    }
}
