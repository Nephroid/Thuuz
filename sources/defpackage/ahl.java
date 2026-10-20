package defpackage;

import android.app.PendingIntent;
import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.internal.lz;
import com.google.android.gms.internal.mb;
import com.google.android.gms.internal.me;
import com.google.android.gms.internal.mg;
import com.google.android.gms.internal.mi;
import com.google.android.gms.internal.mk;
import com.google.android.gms.internal.mm;
import com.google.android.gms.internal.mq;
import com.google.android.gms.internal.ms;
import com.google.android.gms.internal.mw;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.c;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;

/* loaded from: classes.dex */
public interface ahl extends IInterface {
    Location a();

    Location a(String str);

    void a(long j, boolean z, PendingIntent pendingIntent);

    void a(ahi ahiVar, String str);

    void a(akk akkVar);

    void a(PendingIntent pendingIntent);

    void a(PendingIntent pendingIntent, ahi ahiVar, String str);

    void a(Location location);

    void a(Location location, int i);

    void a(lz lzVar, akk akkVar);

    void a(lz lzVar, PendingIntent pendingIntent);

    void a(mg mgVar, mw mwVar, PendingIntent pendingIntent);

    void a(mi miVar, mw mwVar, aif aifVar);

    void a(mk mkVar, mw mwVar);

    void a(mm mmVar, mw mwVar, PendingIntent pendingIntent);

    void a(mq mqVar, mw mwVar, aif aifVar);

    void a(ms msVar, LatLngBounds latLngBounds, List<String> list, mw mwVar, aif aifVar);

    void a(mw mwVar, PendingIntent pendingIntent);

    void a(LocationRequest locationRequest, akk akkVar);

    void a(LocationRequest locationRequest, akk akkVar, String str);

    void a(LocationRequest locationRequest, PendingIntent pendingIntent);

    void a(LatLng latLng, mi miVar, mw mwVar, aif aifVar);

    void a(LatLngBounds latLngBounds, int i, mi miVar, mw mwVar, aif aifVar);

    void a(LatLngBounds latLngBounds, int i, String str, mi miVar, mw mwVar, aif aifVar);

    void a(String str, mw mwVar, aif aifVar);

    void a(String str, LatLngBounds latLngBounds, me meVar, mw mwVar, aif aifVar);

    void a(List<mb> list, PendingIntent pendingIntent, ahi ahiVar, String str);

    void a(boolean z);

    void a(String[] strArr, ahi ahiVar, String str);

    IBinder b();

    c b(String str);

    void b(PendingIntent pendingIntent);

    void b(mw mwVar, PendingIntent pendingIntent);

    void b(String str, mw mwVar, aif aifVar);

    IBinder c();
}
