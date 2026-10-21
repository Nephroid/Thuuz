package defpackage;

import android.content.Context;
import android.os.AsyncTask;
// [removed legacy tracker import]
import com.thuuz.gtv.activity.AbstractActivity;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bqn extends AsyncTask<Void, Void, bqo> {
    private final String a;
    private final WeakReference<AbstractActivity> b;
    private boolean c;
    private boolean d;

    public bqn(String str, AbstractActivity abstractActivity) {
        this.b = new WeakReference<>(abstractActivity);
        this.a = str;
    }

    public bqn(String str, AbstractActivity abstractActivity, boolean z) {
        this(str, abstractActivity);
        this.d = z;
    }

    public bqn(String str, AbstractActivity abstractActivity, boolean z, boolean z2) {
        this(str, abstractActivity, z);
        this.c = z2;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        AbstractActivity abstractActivity = this.b.get();
        if (abstractActivity != null && abstractActivity.d()) {
            abstractActivity.e(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.thuuz.gtv.activity.AbstractActivity] */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.thuuz.gtv.activity.AbstractActivity] */
    /* JADX WARN: Type inference failed for: r0v7, types: [bqo] */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public bqo doInBackground(Void... voidArr) {
        int i;
        int i2;
        AbstractActivity abstractActivity = this.b.get();
        if (abstractActivity == 0 || abstractActivity.isDestroyed()) {
            return null;
        }
        boolean aZ = this.c ? false : abstractActivity.j().aZ();
        String str = "";
        if (aZ) {
            str = " with DELETE on, ";
        }
        try {
            abstractActivity.e().info("starting attach" + str + " for id: " + this.a);
            bqe aY = abstractActivity.j().aY();
            bzr bzrVar = new bzr(abstractActivity.j(), aY.a(this.a), aZ);
            int l = bzrVar.l();
            boolean z = l == 0;
            if (aZ && l == -23) {
                z = true;
                i = 0;
            } else {
                i = l;
            }
            if (!z) {
                i2 = i;
            } else if (aZ) {
                abstractActivity.e().info("Facebook account DISCONNECTED.");
                HashMap hashMap = new HashMap();
                Context applicationContext = abstractActivity.j().getApplicationContext();
                abstractActivity.j().getClass();
                AppsFlyerLib.a(applicationContext, "facebook_off", hashMap);
                i2 = i;
            } else {
                abstractActivity.e().info("Facebook account CONNECTED.");
                bpw c = aY.c();
                if (c != null) {
                    HashMap hashMap2 = new HashMap();
                    Context applicationContext2 = abstractActivity.j().getApplicationContext();
                    abstractActivity.j().getClass();
                    AppsFlyerLib.a(applicationContext2, "facebook_on", hashMap2);
                    abstractActivity.j().c(aY.b(), c.a());
                    i2 = i;
                } else {
                    abstractActivity.e().warning("cannot get cached social user after connection message");
                    i2 = -1;
                }
            }
            abstractActivity = new bqo(i2, bzrVar.c(), aZ);
            return abstractActivity;
        } catch (Throwable th) {
            abstractActivity.e().log(Level.SEVERE, "problem: ", th);
            return new bqo(-1, "", aZ);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(bqo bqoVar) {
        AbstractActivity abstractActivity = this.b.get();
        if (abstractActivity != null) {
            try {
                if (abstractActivity.isDestroyed()) {
                    return;
                }
                if (!abstractActivity.d() || bqoVar == null) {
                    return;
                }
                if (bqoVar.b == 0) {
                    if (bqoVar.c) {
                        abstractActivity.d("Facebook Disconnected", "Facebook has been disconnected from your Thuuz account.");
                    } else if (this.d) {
                        abstractActivity.d("Facebook Connected", "Now start sharing great games with your friends.");
                    }
                    abstractActivity.setResult(-1);
                    abstractActivity.ac();
                } else {
                    boolean z = bqoVar.c;
                    if (bqoVar.b == -41) {
                        abstractActivity.c("Failed to Connect", "This Facebook profile is already used elsewhere, connected to Thuuz account: " + bqoVar.a);
                        abstractActivity.setResult(-1);
                    } else {
                        abstractActivity.setResult(0);
                        String str = z ? "Disconnect" : "Connect";
                        if (abstractActivity.d()) {
                            abstractActivity.b("Failed to " + str, bbt.pls_try_again_later);
                        }
                    }
                    abstractActivity.ad();
                }
            } catch (Throwable th) {
                abstractActivity.e().log(Level.SEVERE, "problem: ", th);
            } finally {
                abstractActivity.e(false);
            }
        }
    }
}
