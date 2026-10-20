package defpackage;

import android.os.AsyncTask;
import java.util.Iterator;
import java.util.logging.Level;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bzv extends AsyncTask<String, Void, caq> {
    final /* synthetic */ bzu a;
    private String b = null;
    private String c;

    public bzv(bzu bzuVar, String str) {
        this.a = bzuVar;
        this.c = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public caq doInBackground(String... strArr) {
        caq caqVar;
        Throwable th;
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        this.b = strArr[0];
        try {
            bzx bzxVar = new bzx(this.a.k(), this.b);
            bzxVar.l();
            if (!bzxVar.y()) {
                return null;
            }
            caqVar = new caq();
            try {
                caqVar.a(bzxVar.b());
                caqVar.a(true);
                return caqVar;
            } catch (Throwable th2) {
                th = th2;
                this.a.getLogger().log(Level.SEVERE, "problem: ", th);
                return caqVar;
            }
        } catch (Throwable th3) {
            caqVar = null;
            th = th3;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(caq caqVar) {
        String str;
        String str2 = null;
        try {
            if (caqVar != null) {
                Iterator<bpy> it = caqVar.a().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        str = null;
                        break;
                    }
                    bpy next = it.next();
                    if (this.c.equals(next.a())) {
                        str = next.b();
                        str2 = next.d();
                        break;
                    }
                }
                if (str != null) {
                    this.a.getLogger().warning("translating tv provider id into location: " + this.a.k().ax().a("home", this.b, this.c, str, str2));
                }
                this.a.k().g("pref_tv_provider_id");
                return;
            }
            this.a.getLogger().warning("cannot translate tv provider--cannot download list");
        } catch (Throwable th) {
            this.a.getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
