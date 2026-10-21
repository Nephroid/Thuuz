package defpackage;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.settings.loc.SettingsEditLocationActivity;
import com.thuuz.gtv.settings.loc.TVProviderFragment;
import com.thuuz.gtv.util.ServerChoice;
import com.thuuz.tv.TVDeviceManager;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/* loaded from: classes.dex */
class bwk extends AsyncTask<Void, Void, Integer> {
    private final WeakReference<AbstractActivity> a;
    private final WeakReference<TVProviderFragment> b;
    private File c;
    private final bqc d;
    private Logger e;
    private String f;
    private final int g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bwk(AbstractActivity abstractActivity, bqc bqcVar, int i, TVProviderFragment tVProviderFragment) {
        this.a = new WeakReference<>(abstractActivity);
        this.d = bqcVar;
        this.g = i;
        this.b = new WeakReference<>(tVProviderFragment);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        AbstractActivity abstractActivity = this.a.get();
        if (abstractActivity != null && !abstractActivity.isDestroyed()) {
            abstractActivity.e(true);
            this.e = abstractActivity.e();
            this.c = abstractActivity.getFilesDir();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        int i;
        AbstractActivity abstractActivity = this.a.get();
        if (abstractActivity == null || abstractActivity.isDestroyed()) {
            return -1;
        }
        try {
            if (!this.d.e()) {
                try {
                    WifiInfo connectionInfo = ((WifiManager) abstractActivity.getSystemService("wifi")).getConnectionInfo();
                    if (connectionInfo == null) {
                        this.f = "Wifi info unavailable";
                        i = -1;
                    } else {
                        String ssid = connectionInfo.getSSID();
                        String replace = connectionInfo.getMacAddress().toLowerCase().replace("-", "").replace(":", "");
                        this.d.ar().put("wifi", ssid);
                        this.d.ar().put("mac", replace);
                        i = 0;
                    }
                    return i;
                } catch (Throwable th) {
                    this.e.log(Level.SEVERE, "problem: ", th);
                    this.f = th.toString();
                    return -1;
                }
            }
            WifiInfo connectionInfo2 = ((WifiManager) abstractActivity.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo2 == null || !abstractActivity.T()) {
                this.f = "Wifi connection required";
                return -1;
            }
            String ssid2 = connectionInfo2.getSSID();
            String replace2 = connectionInfo2.getMacAddress().toLowerCase().replace("-", "").replace(":", "");
            String str = Build.BRAND + "/" + Build.MODEL;
            String bw = abstractActivity.j().bw();
            if (bw.length() == 0) {
                bw = abstractActivity.j().aU();
            }
            if (bw.length() == 0) {
                bw = abstractActivity.j().bx();
            }
            String N = bw.length() == 0 ? abstractActivity.j().N() : bw;
            if (N.length() == 0) {
                N = Build.MODEL;
            }
            TVDeviceManager tVDeviceManager = new TVDeviceManager(this.c, this.e, ServerChoice.f());
            if (!tVDeviceManager.a()) {
                if (tVDeviceManager.c() < 100) {
                    this.f = "Internal storage is full; please reduce stored apps/files";
                } else {
                    this.f = "Cannot connect remote control. Please try again later.";
                }
                return -1;
            }
            this.d.ar().put("mac_address", replace2);
            this.d.ar().put("client_name", N);
            this.d.ar().put("client_type", str);
            this.d.ar().put("filepath", tVDeviceManager.b());
            this.d.ar().put("receiver", SettingsEditLocationActivity.h(replace2));
            String h = this.d.h();
            this.e.info("calling startPair with: " + h);
            String startPairing = tVDeviceManager.startPairing(h);
            bpq bpqVar = new bpq(new JSONObject(startPairing));
            if (bpqVar.f("code") == 1) {
                this.d.a(bpqVar);
                this.d.ar().put("wifi", ssid2);
                return 0;
            }
            this.e.info("failed startPair: " + startPairing);
            this.f = bpqVar.e("message");
            return -1;
        } catch (Throwable th2) {
            this.e.log(Level.SEVERE, "problem: ", th2);
            this.f = th2.toString();
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        TVProviderFragment tVProviderFragment;
        AbstractActivity abstractActivity = this.a.get();
        if (abstractActivity != null && !abstractActivity.isDestroyed() && (tVProviderFragment = this.b.get()) != null) {
            try {
                abstractActivity.e(false);
                if (num.intValue() < 0) {
                    abstractActivity.c("Cannot Attach", this.f);
                } else if (this.d.e()) {
                    tVProviderFragment.showPairingDialog(this.d);
                } else {
                    bpu a = abstractActivity.j().ax().a(Integer.valueOf(this.g));
                    if (a == null) {
                        abstractActivity.c("Cannot Save", "Location for id: " + this.g + " not found; please try again later");
                    } else {
                        a.a(this.d);
                        abstractActivity.j().ax().e(a);
                        abstractActivity.finish();
                    }
                }
            } catch (Throwable th) {
                this.e.log(Level.SEVERE, "problem: ", th);
            }
        }
    }
}
