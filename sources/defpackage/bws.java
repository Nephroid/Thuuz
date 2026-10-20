package defpackage;

import android.app.Dialog;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.widget.Button;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.data.Locations;
import com.thuuz.gtv.util.ServerChoice;
import com.thuuz.tv.TVDeviceManager;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/* loaded from: classes.dex */
class bws extends AsyncTask<Void, Void, Integer> {
    private final WeakReference<AbstractActivity> a;
    private File b;
    private final bqc c;
    private Logger d;
    private String e;
    private final Locations f;
    private final String g;
    private final int h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bws(AbstractActivity abstractActivity, bqc bqcVar, Locations locations, String str, int i) {
        this.a = new WeakReference<>(abstractActivity);
        this.c = bqcVar;
        this.f = locations;
        this.g = str;
        this.h = i;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        AbstractActivity abstractActivity = this.a.get();
        if (abstractActivity != null && !abstractActivity.isDestroyed()) {
            abstractActivity.e(true);
            this.d = abstractActivity.e();
            this.b = abstractActivity.getFilesDir();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        AbstractActivity abstractActivity = this.a.get();
        if (abstractActivity == null || abstractActivity.isDestroyed()) {
            return -1;
        }
        try {
            WifiInfo connectionInfo = ((WifiManager) abstractActivity.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo == null) {
                this.e = "Wifi info unavailable";
                return -1;
            }
            String ssid = connectionInfo.getSSID();
            Logger e = abstractActivity.e();
            String replace = connectionInfo.getMacAddress().toLowerCase().replace("-", "").replace(":", "");
            if (this.c.e()) {
                TVDeviceManager tVDeviceManager = new TVDeviceManager(this.b, e, ServerChoice.f());
                if (!tVDeviceManager.a()) {
                    if (tVDeviceManager.c() < 100) {
                        this.e = "Internal storage is full; please reduce stored apps/files";
                    } else {
                        this.e = "Cannot connect remote control. Please try again later.";
                    }
                    return -1;
                }
                this.c.ar().put("pin", this.g);
                this.c.ar().remove("code");
                String h = this.c.h();
                e.info("calling finishPair: " + h);
                String finishPairing = tVDeviceManager.finishPairing(h);
                bpq bpqVar = new bpq(new JSONObject(finishPairing));
                int f = bpqVar.f("code");
                if (f == 1) {
                    this.c.a(bpqVar);
                } else {
                    this.e = bpqVar.e("message");
                    e.info("failed finishPair: " + finishPairing);
                    if (f != 0) {
                        return Integer.valueOf(f);
                    }
                    return -1;
                }
            }
            bpu a = this.f.a(Integer.valueOf(this.h));
            if (a != null) {
                this.c.ar().put("wifi", ssid);
                this.c.ar().put("mac", replace);
                a.a(this.c);
                this.f.b(a);
            }
            return 0;
        } catch (Throwable th) {
            this.d.log(Level.SEVERE, "problem: ", th);
            this.e = th.toString();
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        AbstractActivity abstractActivity = this.a.get();
        if (abstractActivity != 0 && !abstractActivity.isDestroyed()) {
            try {
                abstractActivity.e(false);
                Dialog U = abstractActivity.U();
                if (num.intValue() == 36) {
                    abstractActivity.c("Pairing code timed out", "Please try again");
                    abstractActivity.f(false);
                    if (U != null && U.isShowing()) {
                        U.dismiss();
                        return;
                    }
                    return;
                }
                if (num.intValue() == 15) {
                    if (abstractActivity.d()) {
                        abstractActivity.b("TV is too busy", bbt.pls_try_again_later);
                    }
                    abstractActivity.f(false);
                    if (U != null && U.isShowing()) {
                        U.dismiss();
                        return;
                    }
                    return;
                }
                if (num.intValue() == 17) {
                    abstractActivity.c("TV in standby", "Please turn on your receiver");
                    abstractActivity.f(false);
                    if (U != null && U.isShowing()) {
                        U.dismiss();
                        return;
                    }
                    return;
                }
                if (num.intValue() != 0) {
                    abstractActivity.c("Cannot Pair", this.e);
                    if (U != null && U.isShowing()) {
                        ((Button) U.findViewById(bbq.pair_button)).setEnabled(true);
                        return;
                    }
                    return;
                }
                abstractActivity.j().a("miscellaneous", "tv_device_paired", this.c.f(), 0L);
                ((bwr) abstractActivity).aK();
            } catch (Throwable th) {
                this.d.log(Level.SEVERE, "problem: ", th);
            }
        }
    }
}
