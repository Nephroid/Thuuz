package defpackage;

import android.app.AlertDialog;
import android.os.AsyncTask;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.settings.loc.TVProviderFragment;
import com.thuuz.gtv.util.ServerChoice;
import com.thuuz.tv.TVDeviceManager;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bwl extends AsyncTask<Void, Void, List<bqc>> {
    private final WeakReference<AbstractActivity> a;
    private final WeakReference<TVProviderFragment> b;
    private String c;
    private final int d;
    private final WeakReference<bpu> e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bwl(AbstractActivity abstractActivity, int i, bpu bpuVar, TVProviderFragment tVProviderFragment) {
        this.a = new WeakReference<>(abstractActivity);
        this.d = i;
        this.e = new WeakReference<>(bpuVar);
        this.b = new WeakReference<>(tVProviderFragment);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        TVProviderFragment tVProviderFragment;
        AbstractActivity abstractActivity = this.a.get();
        if (abstractActivity != null && !abstractActivity.isDestroyed() && (tVProviderFragment = this.b.get()) != null) {
            try {
                abstractActivity.e(true);
                tVProviderFragment.showDiscoveryInProgress(true);
            } catch (Throwable th) {
                abstractActivity.e().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public List<bqc> doInBackground(Void... voidArr) {
        AbstractActivity abstractActivity = this.a.get();
        if (abstractActivity == null || abstractActivity.isDestroyed()) {
            return null;
        }
        Logger e = abstractActivity.e();
        try {
            if (!abstractActivity.T()) {
                this.c = "Wifi is disconnected";
                return null;
            }
            File filesDir = abstractActivity.getFilesDir();
            bpu bpuVar = this.e.get();
            if (bpuVar == null) {
                bpuVar = abstractActivity.j().ax().a(Integer.valueOf(this.d));
            }
            if (bpuVar == null) {
                this.c = "Cannot get location for id: " + this.d;
                e.severe(this.c);
                return null;
            }
            String k = bpuVar.k();
            if (k == null || k.length() == 0) {
                this.c = "Location lacks type";
                return null;
            }
            TVDeviceManager tVDeviceManager = new TVDeviceManager(filesDir, e, ServerChoice.f());
            if (!tVDeviceManager.a(true)) {
                if (tVDeviceManager.c() < 100) {
                    this.c = "Internal storage is full; please reduce stored apps/files";
                } else {
                    this.c = "Cannot connect remote control. Please try again later.";
                }
                return null;
            }
            e.info("discovering devices of type: " + k);
            String discoverDevices = tVDeviceManager.discoverDevices(k);
            if (discoverDevices == null) {
                this.c = "Cannot discover devices";
                return null;
            }
            bpq bpqVar = new bpq(new JSONObject(discoverDevices));
            ArrayList arrayList = new ArrayList();
            int f = bpqVar.f("code");
            if (f != 63) {
                if (f == 1) {
                    JSONArray j = bpqVar.j("dev_list");
                    for (int i = 0; i < j.length(); i++) {
                        arrayList.add(new bqc(j.getJSONObject(i)));
                    }
                    return arrayList;
                }
                this.c = bpqVar.e("message");
                if (this.c == null || this.c.length() == 0) {
                    this.c = "Internal error code: " + f;
                }
                return null;
            }
            return arrayList;
        } catch (Throwable th) {
            e.log(Level.SEVERE, "problem: ", th);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(List<bqc> list) {
        TVProviderFragment tVProviderFragment;
        String str;
        boolean z;
        AbstractActivity abstractActivity = this.a.get();
        if (abstractActivity != null && !abstractActivity.isDestroyed() && (tVProviderFragment = this.b.get()) != null) {
            try {
                abstractActivity.e(false);
                tVProviderFragment.showDiscoveryInProgress(false);
                if (list == null) {
                    if ("Wifi is disconnected".equals(this.c)) {
                        tVProviderFragment.showNeedWifiMessage(true);
                        z = false;
                    } else {
                        abstractActivity.e().severe("problem discovering tv, null tvDevices: " + this.c);
                        tVProviderFragment.showDiscoverySection(true);
                        z = true;
                    }
                    abstractActivity.c("Discovery Failed", this.c);
                    if (z) {
                        abstractActivity.finish();
                        return;
                    }
                    return;
                }
                if (list.size() > 0) {
                    tVProviderFragment.onDevicesDiscovered(list);
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(abstractActivity);
                bpu bpuVar = this.e.get();
                if (bpuVar == null || !bpuVar.p()) {
                    str = "Dish Hopper";
                } else {
                    str = "DIRECTV";
                }
                String string = abstractActivity.getResources().getString(bbt.discover_trouble, str);
                builder.setTitle(abstractActivity.getResources().getString(bbt.discover_not_found_title, str));
                builder.setMessage(string);
                builder.setCancelable(true);
                AlertDialog create = builder.create();
                create.setButton(-1, "Ok", new bwm(this, create));
                abstractActivity.a(create);
                if (!abstractActivity.isDestroyed()) {
                    create.show();
                    tVProviderFragment.showDiscoverySection(true);
                }
            } catch (Throwable th) {
                abstractActivity.e().log(Level.SEVERE, "problem: ", th);
            }
        }
    }
}
