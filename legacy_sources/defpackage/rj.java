package defpackage;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class rj implements Parcelable.Creator<RoomEntity> {
    public static void a(RoomEntity roomEntity, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, roomEntity.v_(), false);
        ke.a(parcel, 1000, roomEntity.j());
        ke.a(parcel, 2, roomEntity.w_(), false);
        ke.a(parcel, 3, roomEntity.x_());
        ke.a(parcel, 4, roomEntity.e());
        ke.a(parcel, 5, roomEntity.f(), false);
        ke.a(parcel, 6, roomEntity.g());
        ke.a(parcel, 7, roomEntity.h(), false);
        ke.c(parcel, 8, roomEntity.l(), false);
        ke.a(parcel, 9, roomEntity.i());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public RoomEntity createFromParcel(Parcel parcel) {
        int i = 0;
        ArrayList arrayList = null;
        int b = a.b(parcel);
        long j = 0;
        Bundle bundle = null;
        int i2 = 0;
        String str = null;
        int i3 = 0;
        String str2 = null;
        String str3 = null;
        int i4 = 0;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    str3 = a.o(parcel, a);
                    break;
                case 2:
                    str2 = a.o(parcel, a);
                    break;
                case 3:
                    j = a.i(parcel, a);
                    break;
                case 4:
                    i3 = a.g(parcel, a);
                    break;
                case 5:
                    str = a.o(parcel, a);
                    break;
                case 6:
                    i2 = a.g(parcel, a);
                    break;
                case 7:
                    bundle = a.q(parcel, a);
                    break;
                case 8:
                    arrayList = a.c(parcel, a, ParticipantEntity.CREATOR);
                    break;
                case 9:
                    i = a.g(parcel, a);
                    break;
                case 1000:
                    i4 = a.g(parcel, a);
                    break;
                default:
                    a.b(parcel, a);
                    break;
            }
        }
        if (parcel.dataPosition() != b) {
            throw new a.C0003a("Overread allowed size end=" + b, parcel);
        }
        return new RoomEntity(i4, str3, str2, j, i3, str, i2, bundle, arrayList, i);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public RoomEntity[] newArray(int i) {
        return new RoomEntity[i];
    }
}
