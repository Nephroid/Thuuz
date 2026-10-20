package defpackage;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;

/* loaded from: classes.dex */
public class rr implements Parcelable.Creator<SnapshotMetadataChange> {
    public static void a(SnapshotMetadataChange snapshotMetadataChange, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, snapshotMetadataChange.b(), false);
        ke.a(parcel, 1000, snapshotMetadataChange.a());
        ke.a(parcel, 2, snapshotMetadataChange.c(), false);
        ke.a(parcel, 4, (Parcelable) snapshotMetadataChange.e(), i, false);
        ke.a(parcel, 5, (Parcelable) snapshotMetadataChange.d(), i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public SnapshotMetadataChange createFromParcel(Parcel parcel) {
        Uri uri = null;
        int b = a.b(parcel);
        int i = 0;
        com.google.android.gms.common.data.a aVar = null;
        Long l = null;
        String str = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    str = a.o(parcel, a);
                    break;
                case 2:
                    l = a.j(parcel, a);
                    break;
                case 4:
                    uri = (Uri) a.a(parcel, a, Uri.CREATOR);
                    break;
                case 5:
                    aVar = (com.google.android.gms.common.data.a) a.a(parcel, a, com.google.android.gms.common.data.a.CREATOR);
                    break;
                case 1000:
                    i = a.g(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new SnapshotMetadataChange(i, str, l, aVar, uri);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public SnapshotMetadataChange[] newArray(int i) {
        return new SnapshotMetadataChange[i];
    }
}
