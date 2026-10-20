package defpackage;

import android.app.PendingIntent;
import android.location.Location;
import android.location.LocationManager;
import android.net.http.Headers;
import android.os.Build;
import com.thuuz.gtv.App;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bzf extends bya {
    public static final Pattern a = Pattern.compile("\\d{5}");
    private TreeSet<bpz> b;
    private final PendingIntent c;

    public bzf(App app, PendingIntent pendingIntent) {
        super("local_teams", app, false);
        this.b = new TreeSet<>();
        this.c = pendingIntent;
    }

    @Override // defpackage.bya
    public int o() {
        int i = 0;
        try {
            int v = v();
            a(0);
            double[] b = b();
            String bp = k().bp();
            boolean z = bp != null && a.matcher(bp).matches();
            if (v() != 0 && !z) {
                return v();
            }
            if (z && !k().cc()) {
                a("postal_code", bp);
            } else if (b != null) {
                a("latitude", String.valueOf(b[0]));
                a("longitude", String.valueOf(b[1]));
                i = v;
            } else {
                a(-1);
                return v();
            }
            a(i);
            return super.o();
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bya
    public int b(JSONObject jSONObject) {
        try {
            this.b.addAll(new bzw(k()).b(jSONObject.toString()));
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
            a(-1);
        }
        return v();
    }

    public double[] b() {
        boolean z = true;
        try {
            if (k().e("android.permission.ACCESS_COARSE_LOCATION")) {
                LocationManager locationManager = (LocationManager) k().getSystemService(Headers.LOCATION);
                if (locationManager != null) {
                    z = locationManager.isProviderEnabled("network");
                    if (!z) {
                        getLogger().info("NO enabled network provider!");
                        a(-666);
                    }
                    if (Build.VERSION.SDK_INT >= 9 && this.c != null) {
                        getLogger().info("requesting single loc update");
                        locationManager.requestSingleUpdate("network", this.c);
                    }
                    Location lastKnownLocation = locationManager.getLastKnownLocation("network");
                    if (lastKnownLocation == null && k().e() && (Build.VERSION.SDK_INT < 14 || k().e("android.permission.ACCESS_FINE_LOCATION"))) {
                        try {
                            lastKnownLocation = locationManager.getLastKnownLocation("static");
                        } catch (Throwable th) {
                            getLogger().info("rejected static provider: " + th);
                        }
                    }
                    if (lastKnownLocation != null) {
                        a(0);
                        return new double[]{lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude()};
                    }
                    getLogger().info("no recent location");
                } else {
                    getLogger().info("no location manager");
                }
            } else {
                getLogger().info("no perm for location");
            }
        } catch (Throwable th2) {
            if (!k().bQ() && z) {
                getLogger().log(Level.SEVERE, "problem getting LocationManager info: ", th2);
            }
        }
        return null;
    }

    public TreeSet<bpz> c() {
        return this.b;
    }
}
