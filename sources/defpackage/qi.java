package defpackage;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.games.GameEntity;

/* loaded from: classes.dex */
public final class qi extends qj {
    @Override // defpackage.qj, android.os.Parcelable.Creator
    /* renamed from: a */
    public GameEntity createFromParcel(Parcel parcel) {
        Integer c;
        boolean b;
        boolean a;
        c = GameEntity.c();
        b = GameEntity.b(c);
        if (!b) {
            a = GameEntity.a(GameEntity.class.getCanonicalName());
            if (!a) {
                String readString = parcel.readString();
                String readString2 = parcel.readString();
                String readString3 = parcel.readString();
                String readString4 = parcel.readString();
                String readString5 = parcel.readString();
                String readString6 = parcel.readString();
                String readString7 = parcel.readString();
                Uri parse = readString7 == null ? null : Uri.parse(readString7);
                String readString8 = parcel.readString();
                Uri parse2 = readString8 == null ? null : Uri.parse(readString8);
                String readString9 = parcel.readString();
                return new GameEntity(5, readString, readString2, readString3, readString4, readString5, readString6, parse, parse2, readString9 == null ? null : Uri.parse(readString9), parcel.readInt() > 0, parcel.readInt() > 0, parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), false, false, null, null, null, false, false, false, null);
            }
        }
        return super.createFromParcel(parcel);
    }
}
