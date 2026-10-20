package defpackage;

import android.location.Location;
import android.os.IInterface;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;

/* loaded from: classes.dex */
public interface alb extends IInterface {
    aov a(PolylineOptions polylineOptions);

    apb a(CircleOptions circleOptions);

    ape a(GroundOverlayOptions groundOverlayOptions);

    apk a(MarkerOptions markerOptions);

    apn a(PolygonOptions polygonOptions);

    apq a(TileOverlayOptions tileOverlayOptions);

    CameraPosition a();

    void a(int i);

    void a(int i, int i2, int i3, int i4);

    void a(ale aleVar);

    void a(amj amjVar);

    void a(amm ammVar);

    void a(amp ampVar);

    void a(ams amsVar);

    void a(amy amyVar);

    void a(anb anbVar);

    void a(ane aneVar);

    void a(anh anhVar);

    void a(ank ankVar);

    void a(ann annVar);

    void a(anq anqVar);

    void a(aoc aocVar, od odVar);

    void a(od odVar);

    void a(od odVar, int i, amd amdVar);

    void a(od odVar, amd amdVar);

    void a(boolean z);

    float b();

    void b(od odVar);

    boolean b(boolean z);

    float c();

    void c(boolean z);

    void d();

    void d(boolean z);

    void e();

    int f();

    boolean g();

    boolean h();

    boolean i();

    Location j();

    alz k();

    aln l();

    boolean m();

    aph n();
}
