package defpackage;

import android.os.Parcel;
import com.google.android.gms.maps.model.Tile;

/* loaded from: classes.dex */
public class aqi {
    public static void a(Tile tile, Parcel parcel, int i) {
        int a = ke.a(parcel);
        ke.a(parcel, 1, tile.a());
        ke.a(parcel, 2, tile.a);
        ke.a(parcel, 3, tile.b);
        ke.a(parcel, 4, tile.c, false);
        ke.a(parcel, a);
    }
}
