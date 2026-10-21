package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.snapshot.SnapshotContents;

/* loaded from: classes.dex */
public class rp implements Parcelable.Creator<SnapshotContents> {
    public static void a(SnapshotContents snapshotContents, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, (Parcelable) snapshotContents.b(), i, false);
        ke.a(parcel, 1000, snapshotContents.a());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public SnapshotContents createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        Contents contents = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    contents = (Contents) a.a(parcel, a, Contents.CREATOR);
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
        return new SnapshotContents(i, contents);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public SnapshotContents[] newArray(int i) {
        return new SnapshotContents[i];
    }
}
