package com.thuuz.gtv.data;

import android.content.Context;
import android.content.Intent;
// [removed legacy tracker import]
import com.thuuz.gtv.App;
import defpackage.bpu;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Locations extends HashMap<Integer, bpu> {
    private App app;

    public Locations(String str, App app) {
        this.app = app;
        if (str != null) {
            try {
                if (str.length() != 0) {
                    JSONArray jSONArray = new JSONObject(str).getJSONArray("locations");
                    for (int i = 0; i < jSONArray.length(); i++) {
                        f(new bpu(jSONArray.getJSONObject(i)));
                    }
                }
            } catch (Throwable th) {
                d().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    public bpu a(Integer num) {
        return get(num);
    }

    private void f(bpu bpuVar) {
        put(bpuVar.a(), bpuVar);
        f();
    }

    public void b(Integer num) {
        int size = size();
        remove(num);
        if (size >= 1 && size() < 1) {
            try {
                HashMap hashMap = new HashMap();
                Context applicationContext = this.app.getApplicationContext();
                this.app.getClass();
                AppsFlyerLib.a(applicationContext, "tv_off", hashMap);
            } catch (Exception e) {
                d().severe("problem with AppsFlyer event in deleteLocation:" + e);
            }
        }
        f();
    }

    private Integer b() {
        Integer num = 1;
        while (get(num) != null) {
            num = Integer.valueOf(num.intValue() + 1);
        }
        return num;
    }

    private String c() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator<bpu> it = values().iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().ar());
            }
            jSONObject.put("locations", jSONArray);
            return jSONObject.toString();
        } catch (Throwable th) {
            return null;
        }
    }

    private Logger d() {
        Logger logger = Logger.getLogger(getClass().getSimpleName() + ":thuuz");
        if (logger.getLevel() != App.aV()) {
            logger.setLevel(App.aV());
        }
        return logger;
    }

    public bpu a() {
        int i = e().X().getInt("my_location", -1);
        if (i == -1) {
            return null;
        }
        return a(Integer.valueOf(i));
    }

    public void a(bpu bpuVar) {
        a(bpuVar, true);
    }

    public void a(bpu bpuVar, boolean z) {
        try {
            bpu a = e().ax().a();
            if (bpuVar == null) {
                e().g("my_location");
            } else {
                e().a("my_location", bpuVar.a().intValue());
            }
            if (a != null || bpuVar != null) {
                if (a == null || bpuVar == null || !a.d().equals(bpuVar.d())) {
                    if (bpuVar != null) {
                        e().x(bpuVar.c());
                        if (z) {
                            e().bm();
                        }
                    }
                    Intent intent = new Intent("loc_set");
                    if (a != null) {
                        intent.putExtra("old", a.a());
                    }
                    e().sendBroadcast(intent, "com.thuuz.permission.local.broadcast");
                }
            }
        } catch (Throwable th) {
            d().log(Level.SEVERE, "problem: ", th);
        }
    }

    private App e() {
        return this.app;
    }

    private void f() {
        e().b("locations", c());
    }

    public bpu a(String str, String str2, String str3, String str4, String str5) {
        int size = size();
        bpu bpuVar = new bpu(b(), str, str2, str3, str4, str5);
        f(bpuVar);
        if (size() == 1) {
            a(bpuVar);
        }
        if (size < 1 && size() >= 1) {
            try {
                HashMap hashMap = new HashMap();
                Context applicationContext = this.app.getApplicationContext();
                this.app.getClass();
                AppsFlyerLib.a(applicationContext, "tv_on", hashMap);
            } catch (Exception e) {
                d().severe("problem with AppsFlyer event in updateLocation:" + e);
            }
        }
        return bpuVar;
    }

    public void b(bpu bpuVar) {
        f(bpuVar);
    }

    public boolean a(String str) {
        Iterator<bpu> it = values().iterator();
        while (it.hasNext()) {
            if (it.next().d().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean c(bpu bpuVar) {
        bpu a = a();
        if (bpuVar == null) {
            if (a == null) {
                return true;
            }
            return false;
        }
        return bpuVar.equals(a);
    }

    public bpu d(bpu bpuVar) {
        if (bpuVar == null) {
            return null;
        }
        return a(bpuVar.b(), bpuVar.c(), bpuVar.d(), bpuVar.e(), bpuVar.k());
    }

    public bpu e(bpu bpuVar) {
        if (bpuVar == null) {
            return null;
        }
        if (a(bpuVar.a()) == null) {
            return d(bpuVar);
        }
        b(bpuVar);
        return bpuVar;
    }
}
