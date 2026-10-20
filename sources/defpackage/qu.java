package defpackage;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.games.internal.game.GameBadgeEntity;

/* loaded from: classes.dex */
public final class qu extends qv {
    @Override // defpackage.qv, android.os.Parcelable.Creator
    /* renamed from: a */
    public GameBadgeEntity createFromParcel(Parcel parcel) {
        Integer c;
        boolean b;
        boolean a;
        c = GameBadgeEntity.c();
        b = GameBadgeEntity.b(c);
        if (!b) {
            a = GameBadgeEntity.a(GameBadgeEntity.class.getCanonicalName());
            if (!a) {
                int readInt = parcel.readInt();
                String readString = parcel.readString();
                String readString2 = parcel.readString();
                String readString3 = parcel.readString();
                return new GameBadgeEntity(1, readInt, readString, readString2, readString3 == null ? null : Uri.parse(readString3));
            }
        }
        return super.createFromParcel(parcel);
    }
}
