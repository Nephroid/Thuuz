package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.games.internal.multiplayer.ZInvitationCluster;
import com.google.android.gms.games.multiplayer.InvitationEntity;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class qw implements Parcelable.Creator<ZInvitationCluster> {
    public static void a(ZInvitationCluster zInvitationCluster, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.c(parcel, 1, zInvitationCluster.c(), false);
        ke.a(parcel, 1000, zInvitationCluster.b());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ZInvitationCluster createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    arrayList = a.c(parcel, a, InvitationEntity.CREATOR);
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
        return new ZInvitationCluster(i, arrayList);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ZInvitationCluster[] newArray(int i) {
        return new ZInvitationCluster[i];
    }
}
