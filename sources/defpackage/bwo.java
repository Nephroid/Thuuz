package defpackage;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.http.Headers;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import com.thuuz.gtv.App;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.settings.loc.SettingsEditLocationActivity;
import com.thuuz.gtv.settings.loc.TVProviderFragment;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class bwo extends AsyncTask<Void, Void, Void> implements ho, hp {
    public static final Pattern a = Pattern.compile("([0-9]{4}[1-9])|([0-9]{3}[1-9][0-9])|(20500)");
    private static hm e;
    private static boolean f;
    private final WeakReference<AbstractActivity> b;
    private final WeakReference<TVProviderFragment> c;
    private volatile boolean d;

    public bwo(AbstractActivity abstractActivity, TVProviderFragment tVProviderFragment) {
        this(abstractActivity, tVProviderFragment, true);
    }

    public bwo(AbstractActivity abstractActivity, TVProviderFragment tVProviderFragment, boolean z) {
        this.b = new WeakReference<>(abstractActivity);
        this.c = new WeakReference<>(tVProviderFragment);
        f = z;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        AbstractActivity abstractActivity = this.b.get();
        if (abstractActivity != null && !abstractActivity.isDestroyed()) {
            abstractActivity.e(true);
        }
    }

    private void b(AbstractActivity abstractActivity) {
        if (this.d) {
            abstractActivity.aa().postDelayed(new bwp(this), 10000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(AbstractActivity abstractActivity) {
        abstractActivity.e(false);
        this.d = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Void d(AbstractActivity abstractActivity) {
        try {
            if (abstractActivity.j().e("android.permission.ACCESS_COARSE_LOCATION") || abstractActivity.j().e("android.permission.ACCESS_FINE_LOCATION")) {
                try {
                    if (((LocationManager) abstractActivity.getSystemService(Headers.LOCATION)) != null) {
                        bzy.getStaticLogger(bwo.class).info("requesting loc update");
                        e = new hn(abstractActivity.k()).a((ho) this).a((hp) this).a(aki.a).b();
                        e.a();
                        this.d = true;
                        abstractActivity.e(true);
                        b(abstractActivity);
                    }
                } catch (Throwable th) {
                    bzy.getStaticLogger(bwo.class).log(Level.WARNING, "loc manager error: ", th);
                }
            }
        } catch (Throwable th2) {
            bzy.getStaticLogger(bwo.class).log(Level.SEVERE, "Cannot handle: ", th2);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Void doInBackground(Void... voidArr) {
        AbstractActivity abstractActivity = this.b.get();
        if (abstractActivity != null && !abstractActivity.isDestroyed()) {
            if (f) {
                bqy.a(new bwq(this, abstractActivity));
                if (!abstractActivity.b("android.permission.ACCESS_COARSE_LOCATION", true)) {
                    d(abstractActivity);
                }
            } else {
                d(abstractActivity);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Void r5) {
        TVProviderFragment tVProviderFragment;
        try {
            AbstractActivity abstractActivity = this.b.get();
            if (abstractActivity != null && !abstractActivity.isDestroyed() && (tVProviderFragment = this.c.get()) != null) {
                abstractActivity.e(false);
                String zipText = tVProviderFragment.getZipText();
                if (zipText == null || zipText.length() == 0) {
                    tVProviderFragment.focusOnZip();
                    abstractActivity.f(true);
                }
            }
        } catch (Throwable th) {
            bzy.getStaticLogger(bwo.class).log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    @Override // defpackage.ho
    public void a(Bundle bundle) {
        AbstractActivity abstractActivity = this.b.get();
        if (abstractActivity != null && !abstractActivity.isDestroyed()) {
            if (this.d) {
                c(abstractActivity);
            }
            TVProviderFragment tVProviderFragment = this.c.get();
            if (tVProviderFragment != null) {
                try {
                    abstractActivity.e(true);
                    abstractActivity.e().info("handling loc incoming...");
                    String a2 = a(abstractActivity);
                    if (a2 != null && tVProviderFragment.getZipText().length() == 0) {
                        tVProviderFragment.setZip(a2);
                    }
                } catch (Throwable th) {
                    abstractActivity.e().log(Level.SEVERE, "Cannot handle: ", th);
                } finally {
                    abstractActivity.e(false);
                    e.b();
                }
            }
        }
    }

    @Override // defpackage.ho
    public void a(int i) {
        e.b();
    }

    @Override // defpackage.hp, defpackage.ha
    public void a(gw gwVar) {
        e.b();
    }

    public static Location a(App app) {
        LocationManager locationManager;
        if (!app.e("android.permission.ACCESS_COARSE_LOCATION") || (locationManager = (LocationManager) app.getSystemService(Headers.LOCATION)) == null) {
            return null;
        }
        Location a2 = aki.b.a(e);
        if (a2 == null && app.e() && (Build.VERSION.SDK_INT < 14 || app.e("android.permission.ACCESS_FINE_LOCATION"))) {
            try {
                return locationManager.getLastKnownLocation("static");
            } catch (Throwable th) {
                bzy.getStaticLogger(SettingsEditLocationActivity.class).info("rejected 'static' loc provider: " + th);
            }
        }
        return a2;
    }

    public String a(AbstractActivity abstractActivity) {
        String a2;
        if (abstractActivity == null) {
            return null;
        }
        try {
            Location a3 = a(abstractActivity.j());
            if (a3 == null || (a2 = a(abstractActivity, a3)) == null) {
                return null;
            }
            bzy.getStaticLogger(SettingsEditLocationActivity.class).info("got zip: " + a2);
            return a2;
        } catch (Throwable th) {
            if (abstractActivity.j().bQ()) {
                return null;
            }
            bzy.getStaticLogger(SettingsEditLocationActivity.class).log(Level.SEVERE, "problem getting LocationManager info: ", th);
            return null;
        }
    }

    public String a(AbstractActivity abstractActivity, Location location) {
        String postalCode;
        try {
            bzy.getStaticLogger(SettingsEditLocationActivity.class).info("got loc: " + location);
            List<Address> fromLocation = new Geocoder(abstractActivity).getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            Address address = fromLocation.size() > 0 ? fromLocation.get(0) : null;
            if (address != null && (postalCode = address.getPostalCode()) != null && a.matcher(postalCode).matches()) {
                return postalCode.substring(0, 5);
            }
        } catch (IOException e2) {
            bzy.getStaticLogger(SettingsEditLocationActivity.class).log(Level.WARNING, "Geocoder error: " + e2);
        }
        return null;
    }
}
