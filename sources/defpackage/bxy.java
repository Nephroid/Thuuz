package defpackage;

import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.settings.loc.TVProviderFragment;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bxy extends AsyncTask<Void, Void, String> {
    WeakReference<AbstractActivity> a;
    private final WeakReference<TVProviderFragment> b;
    private final String c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bxy(AbstractActivity abstractActivity, String str, TVProviderFragment tVProviderFragment) {
        this.a = new WeakReference<>(abstractActivity);
        this.b = new WeakReference<>(tVProviderFragment);
        this.c = str;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        AbstractActivity abstractActivity = this.a.get();
        if (abstractActivity != null && !abstractActivity.isDestroyed()) {
            abstractActivity.e(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String doInBackground(Void... voidArr) {
        AbstractActivity abstractActivity = this.a.get();
        if (abstractActivity == null || abstractActivity.isDestroyed()) {
            return null;
        }
        Logger e = abstractActivity.e();
        try {
            List<Address> fromLocationName = new Geocoder(abstractActivity).getFromLocationName(this.c, 1);
            if (fromLocationName != null && !fromLocationName.isEmpty()) {
                Address address = fromLocationName.get(0);
                if (address.getCountryCode().equals("US")) {
                    String locality = address.getLocality();
                    String adminArea = address.getAdminArea();
                    if (locality != null && adminArea != null) {
                        return locality + ", " + adminArea;
                    }
                    if (locality != null) {
                        return locality;
                    }
                    if (adminArea != null) {
                        return adminArea;
                    }
                }
            } else {
                e.info("zip code lookup failed!");
            }
        } catch (Throwable th) {
            e.log(Level.WARNING, "problem getting city from zip: " + th);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(String str) {
        AbstractActivity abstractActivity = this.a.get();
        if (abstractActivity == null || abstractActivity.isDestroyed()) {
            return;
        }
        TVProviderFragment tVProviderFragment = this.b.get();
        try {
            if (tVProviderFragment != null) {
                if (abstractActivity.isDestroyed()) {
                    return;
                }
                if (abstractActivity.d()) {
                    if (str != null) {
                        tVProviderFragment.setCityText(str);
                    }
                }
            }
        } catch (Throwable th) {
            abstractActivity.e().log(Level.SEVERE, "problem: ", th);
        } finally {
            abstractActivity.e(false);
        }
    }
}
