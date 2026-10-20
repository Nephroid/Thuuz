package defpackage;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.games.PlayerEntity;

/* loaded from: classes.dex */
public final class qk extends ql {
    @Override // defpackage.ql, android.os.Parcelable.Creator
    /* renamed from: a */
    public PlayerEntity createFromParcel(Parcel parcel) {
        Integer c;
        boolean b;
        boolean a;
        c = PlayerEntity.c();
        b = PlayerEntity.b(c);
        if (!b) {
            a = PlayerEntity.a(PlayerEntity.class.getCanonicalName());
            if (!a) {
                String readString = parcel.readString();
                String readString2 = parcel.readString();
                String readString3 = parcel.readString();
                String readString4 = parcel.readString();
                return new PlayerEntity(11, readString, readString2, readString3 == null ? null : Uri.parse(readString3), readString4 == null ? null : Uri.parse(readString4), parcel.readLong(), -1, -1L, null, null, null, null, null, true);
            }
        }
        return super.createFromParcel(parcel);
    }
}
