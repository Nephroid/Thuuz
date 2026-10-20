package defpackage;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.event.EventEntity;

/* loaded from: classes.dex */
public class qp implements Parcelable.Creator<EventEntity> {
    public static void a(EventEntity eventEntity, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, eventEntity.j_(), false);
        ke.a(parcel, 1000, eventEntity.k());
        ke.a(parcel, 2, eventEntity.c(), false);
        ke.a(parcel, 3, eventEntity.d(), false);
        ke.a(parcel, 4, (Parcelable) eventEntity.e(), i, false);
        ke.a(parcel, 5, eventEntity.f(), false);
        ke.a(parcel, 6, (Parcelable) eventEntity.g(), i, false);
        ke.a(parcel, 7, eventEntity.h());
        ke.a(parcel, 8, eventEntity.i(), false);
        ke.a(parcel, 9, eventEntity.j());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public EventEntity createFromParcel(Parcel parcel) {
        boolean z = false;
        String str = null;
        int b = a.b(parcel);
        long j = 0;
        PlayerEntity playerEntity = null;
        String str2 = null;
        Uri uri = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        int i = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    str5 = a.o(parcel, a);
                    break;
                case 2:
                    str4 = a.o(parcel, a);
                    break;
                case 3:
                    str3 = a.o(parcel, a);
                    break;
                case 4:
                    uri = (Uri) a.a(parcel, a, Uri.CREATOR);
                    break;
                case 5:
                    str2 = a.o(parcel, a);
                    break;
                case 6:
                    playerEntity = (PlayerEntity) a.a(parcel, a, PlayerEntity.CREATOR);
                    break;
                case 7:
                    j = a.i(parcel, a);
                    break;
                case 8:
                    str = a.o(parcel, a);
                    break;
                case 9:
                    z = a.c(parcel, a);
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
        return new EventEntity(i, str5, str4, str3, uri, str2, playerEntity, j, str, z);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public EventEntity[] newArray(int i) {
        return new EventEntity[i];
    }
}
