package com.flurry.sdk;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import com.flurry.android.FlurryAgent;
import com.flurry.sdk.cl;
import com.flurry.sdk.ei;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes.dex */
public class bx implements cl.a, ei.a, fd, Thread.UncaughtExceptionHandler {
    private static final String a = bx.class.getSimpleName();
    private static bx b;
    private boolean d;
    private cl e;
    private cd g;
    private String c = "";
    private Map<String, cl> f = new HashMap();

    /* loaded from: classes.dex */
    public class a {
        public int a;
    }

    private bx() {
        fe.a().a(this);
        eq.a().a(this);
        p();
    }

    private void p() {
        ei a2 = eh.a();
        this.d = ((Boolean) a2.a("CaptureUncaughtExceptions")).booleanValue();
        a2.a("CaptureUncaughtExceptions", (ei.a) this);
        ex.a(4, a, "initSettings, CrashReportingEnabled = " + this.d);
        String str = (String) a2.a("VesionName");
        a2.a("VesionName", (ei.a) this);
        ep.a(str);
        ex.a(4, a, "initSettings, VersionName = " + str);
    }

    @Override // com.flurry.sdk.ei.a
    public void a(String str, Object obj) {
        if (str.equals("CaptureUncaughtExceptions")) {
            this.d = ((Boolean) obj).booleanValue();
            ex.a(4, a, "onSettingUpdate, CrashReportingEnabled = " + this.d);
        } else {
            if (str.equals("VesionName")) {
                String str2 = (String) obj;
                ep.a(str2);
                ex.a(4, a, "onSettingUpdate, VersionName = " + str2);
                return;
            }
            ex.a(6, a, "onSettingUpdate internal error!");
        }
    }

    public static bx a() {
        if (b == null) {
            b = new bx();
        }
        return b;
    }

    public int b() {
        int intValue = ((Integer) eh.a().a("AgentVersion")).intValue();
        ex.a(4, a, "getAgentVersion() = " + intValue);
        return intValue;
    }

    int c() {
        return 3;
    }

    int d() {
        return 4;
    }

    int e() {
        return 0;
    }

    String f() {
        return this.c;
    }

    public String g() {
        String str;
        if (f().length() > 0) {
            str = ".";
        } else {
            str = "";
        }
        return String.format(Locale.getDefault(), "Flurry_Android_%d_%d.%d.%d%s%s", Integer.valueOf(b()), Integer.valueOf(c()), Integer.valueOf(d()), Integer.valueOf(e()), str, f());
    }

    public void a(Context context, String str) {
        cl clVar;
        fe.a().b();
        en.a().b();
        q();
        if (this.f.isEmpty()) {
            en.a().c();
        }
        if (this.f.containsKey(str)) {
            clVar = this.f.get(str);
        } else {
            clVar = new cl(context, str, this);
            clVar.a(b(context));
            this.f.put(str, clVar);
        }
        clVar.c();
        a(clVar);
    }

    public void a(Context context) {
        cl h = h();
        if (h != null) {
            h.d();
        }
    }

    private void q() {
        if (this.g == null) {
            this.g = new cd();
        }
    }

    private Map<String, List<String>> b(Context context) {
        Bundle extras;
        if (!(context instanceof Activity) || (extras = ((Activity) context).getIntent().getExtras()) == null) {
            return null;
        }
        ex.a(3, a, "Launch Options Bundle is present " + extras.toString());
        HashMap hashMap = new HashMap();
        for (String str : extras.keySet()) {
            if (str != null) {
                Object obj = extras.get(str);
                String obj2 = obj != null ? obj.toString() : "null";
                hashMap.put(str, new ArrayList(Arrays.asList(obj2)));
                ex.a(3, a, "Launch options Key: " + str + ". Its value: " + obj2);
            }
        }
        return hashMap;
    }

    void a(cl clVar) {
        this.e = clVar;
    }

    public cl h() {
        return this.e;
    }

    public void a(String str) {
        cl h = h();
        if (h != null) {
            h.a(str, null, false);
        }
    }

    public void a(String str, Map<String, String> map) {
        cl h = h();
        if (h != null) {
            h.a(str, map, false);
        }
    }

    public void a(String str, boolean z) {
        cl h = h();
        if (h != null) {
            h.a(str, null, z);
        }
    }

    public void a(String str, Map<String, String> map, boolean z) {
        cl h = h();
        if (h != null) {
            h.a(str, map, z);
        }
    }

    public void b(String str) {
        cl h = h();
        if (h != null) {
            h.a(str, (Map<String, String>) null);
        }
    }

    public void b(String str, Map<String, String> map) {
        cl h = h();
        if (h != null) {
            h.a(str, map);
        }
    }

    @Deprecated
    public void a(String str, String str2, String str3) {
        StackTraceElement[] stackTraceElementArr;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null || stackTrace.length <= 2) {
            stackTraceElementArr = stackTrace;
        } else {
            stackTraceElementArr = new StackTraceElement[stackTrace.length - 2];
            System.arraycopy(stackTrace, 2, stackTraceElementArr, 0, stackTraceElementArr.length);
        }
        Throwable th = new Throwable(str2);
        th.setStackTrace(stackTraceElementArr);
        cl h = h();
        if (h != null) {
            h.a(str, str2, str3, th);
        }
    }

    public void a(String str, String str2, Throwable th) {
        cl h = h();
        if (h != null) {
            h.a(str, str2, th.getClass().getName(), th);
        }
    }

    public void c(String str) {
        cl h = h();
        if (h != null) {
            h.a(str, null, false);
        }
    }

    public void c(String str, Map<String, String> map) {
        cl h = h();
        if (h != null) {
            h.a(str, map, false);
        }
    }

    public void i() {
        cl h = h();
        if (h != null) {
            h.g();
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        th.printStackTrace();
        if (this.d) {
            String str = "";
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null && stackTrace.length > 0) {
                StringBuilder sb = new StringBuilder();
                if (th.getMessage() != null) {
                    sb.append(" (" + th.getMessage() + ")\n");
                }
                str = sb.toString();
            } else if (th.getMessage() != null) {
                str = th.getMessage();
            }
            FlurryAgent.onError("uncaught", str, th);
        }
        for (cl clVar : new HashMap(this.f).values()) {
            if (clVar != null) {
                clVar.d();
                clVar.e();
            }
        }
        en.a().f();
    }

    public String j() {
        cl h = h();
        if (h == null) {
            return null;
        }
        return h.k();
    }

    public String k() {
        cl h = h();
        if (h == null) {
            return null;
        }
        return h.l();
    }

    public String l() {
        cl h = h();
        if (h == null) {
            return null;
        }
        return h.m();
    }

    public static int m() {
        return 0;
    }

    public Location n() {
        return en.a().e();
    }

    public cd o() {
        return this.g;
    }

    @Override // com.flurry.sdk.fd
    public void b(boolean z) {
    }

    @Override // com.flurry.sdk.cl.a
    public void d(String str) {
        if (!this.f.containsKey(str)) {
            ex.a(6, a, "Ended session is not in the session map! Maybe it was already destroyed.");
        } else {
            cl h = h();
            if (h != null && TextUtils.equals(h.k(), str)) {
                a((cl) null);
            }
            this.f.remove(str);
        }
        if (this.f.isEmpty()) {
            ex.a(5, a, "LocationProvider is going to be unsubscribed");
            en.a().d();
        }
    }

    public void a(boolean z) {
        ex.a(z);
    }
}
