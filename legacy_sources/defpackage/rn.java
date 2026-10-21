package defpackage;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.widget.ViewDragHelper;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.quest.MilestoneEntity;
import com.google.android.gms.games.quest.QuestEntity;
import java.util.ArrayList;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.LangUtils;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public class rn implements Parcelable.Creator<QuestEntity> {
    public static void a(QuestEntity questEntity, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, (Parcelable) questEntity.j(), i, false);
        ke.a(parcel, 2, questEntity.A_(), false);
        ke.a(parcel, 3, questEntity.m());
        ke.a(parcel, 4, (Parcelable) questEntity.e(), i, false);
        ke.a(parcel, 5, questEntity.f(), false);
        ke.a(parcel, 6, questEntity.d(), false);
        ke.a(parcel, 7, questEntity.n());
        ke.a(parcel, 8, questEntity.o());
        ke.a(parcel, 9, (Parcelable) questEntity.g(), i, false);
        ke.a(parcel, 10, questEntity.h(), false);
        ke.a(parcel, 12, questEntity.c(), false);
        ke.a(parcel, 13, questEntity.p());
        ke.a(parcel, 14, questEntity.q());
        ke.a(parcel, 15, questEntity.k());
        ke.c(parcel, 17, questEntity.i(), false);
        ke.a(parcel, 16, questEntity.l());
        ke.a(parcel, 1000, questEntity.r());
        ke.a(parcel, a);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public QuestEntity createFromParcel(Parcel parcel) {
        int b = a.b(parcel);
        int i = 0;
        GameEntity gameEntity = null;
        String str = null;
        long j = 0;
        Uri uri = null;
        String str2 = null;
        String str3 = null;
        long j2 = 0;
        long j3 = 0;
        Uri uri2 = null;
        String str4 = null;
        String str5 = null;
        long j4 = 0;
        long j5 = 0;
        int i2 = 0;
        int i3 = 0;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < b) {
            int a = a.a(parcel);
            switch (a.a(a)) {
                case 1:
                    gameEntity = (GameEntity) a.a(parcel, a, GameEntity.CREATOR);
                    break;
                case 2:
                    str = a.o(parcel, a);
                    break;
                case 3:
                    j = a.i(parcel, a);
                    break;
                case 4:
                    uri = (Uri) a.a(parcel, a, Uri.CREATOR);
                    break;
                case 5:
                    str2 = a.o(parcel, a);
                    break;
                case 6:
                    str3 = a.o(parcel, a);
                    break;
                case 7:
                    j2 = a.i(parcel, a);
                    break;
                case 8:
                    j3 = a.i(parcel, a);
                    break;
                case 9:
                    uri2 = (Uri) a.a(parcel, a, Uri.CREATOR);
                    break;
                case 10:
                    str4 = a.o(parcel, a);
                    break;
                case DateTimeConstants.DECEMBER /* 12 */:
                    str5 = a.o(parcel, a);
                    break;
                case HTTP.CR /* 13 */:
                    j4 = a.i(parcel, a);
                    break;
                case 14:
                    j5 = a.i(parcel, a);
                    break;
                case ViewDragHelper.EDGE_ALL /* 15 */:
                    i2 = a.g(parcel, a);
                    break;
                case 16:
                    i3 = a.g(parcel, a);
                    break;
                case LangUtils.HASH_SEED /* 17 */:
                    arrayList = a.c(parcel, a, MilestoneEntity.CREATOR);
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
        return new QuestEntity(i, gameEntity, str, j, uri, str2, str3, j2, j3, uri2, str4, str5, j4, j5, i2, i3, arrayList);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public QuestEntity[] newArray(int i) {
        return new QuestEntity[i];
    }
}
