package defpackage;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.games.internal.player.MostRecentGameInfoEntity;

/* loaded from: classes.dex */
public class qx implements Parcelable.Creator<MostRecentGameInfoEntity> {
    public static void a(MostRecentGameInfoEntity mostRecentGameInfoEntity, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, mostRecentGameInfoEntity.q_(), false);
        ke.a(parcel, 1000, mostRecentGameInfoEntity.h());
        ke.a(parcel, 2, mostRecentGameInfoEntity.c(), false);
        ke.a(parcel, 3, mostRecentGameInfoEntity.d());
        ke.a(parcel, 4, (Parcelable) mostRecentGameInfoEntity.e(), i, false);
        ke.a(parcel, 5, (Parcelable) mostRecentGameInfoEntity.f(), i, false);
        ke.a(parcel, 6, (Parcelable) mostRecentGameInfoEntity.g(), i, false);
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public MostRecentGameInfoEntity createFromParcel(Parcel parcel) {
        Uri uri = null;
        int b = a.b(parcel);
        int i = 0;
        long j = 0;
        Uri uri2 = null;
        Uri uri3 = null;
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    str2 = a.o(parcel, a);
                    break;
                case 2:
                    str = a.o(parcel, a);
                    break;
                case 3:
                    j = a.i(parcel, a);
                    break;
                case 4:
                    uri3 = (Uri) a.a(parcel, a, Uri.CREATOR);
                    break;
                case 5:
                    uri2 = (Uri) a.a(parcel, a, Uri.CREATOR);
                    break;
                case 6:
                    uri = (Uri) a.a(parcel, a, Uri.CREATOR);
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
        return new MostRecentGameInfoEntity(i, str2, str, j, uri3, uri2, uri);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public MostRecentGameInfoEntity[] newArray(int i) {
        return new MostRecentGameInfoEntity[i];
    }
}
