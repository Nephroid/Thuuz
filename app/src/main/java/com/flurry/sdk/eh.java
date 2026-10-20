package com.flurry.sdk;

import android.location.Criteria;

/* loaded from: classes.dex */
public class eh {
    public static final Integer a = 159;
    public static final String b = null;
    public static final Boolean c = true;
    public static final Boolean d = true;
    public static final String e = null;
    public static final Boolean f = true;
    public static final Criteria g = null;
    public static final Long h = 10000L;
    public static final Boolean i = true;
    public static final Long j = 0L;
    public static final Byte k = (byte) -1;
    private static ei l;

    public static synchronized ei a() {
        ei eiVar;
        synchronized (eh.class) {
            if (l == null) {
                l = new ei();
                b();
            }
            eiVar = l;
        }
        return eiVar;
    }

    private static void b() {
        if (l == null) {
            l = new ei();
        }
        l.a("AgentVersion", (Object) a);
        l.a("VesionName", (Object) b);
        l.a("CaptureUncaughtExceptions", (Object) c);
        l.a("UseHttps", (Object) d);
        l.a("ReportUrl", (Object) e);
        l.a("ReportLocation", (Object) f);
        l.a("LocationCriteria", (Object) g);
        l.a("ContinueSessionMillis", (Object) h);
        l.a("LogEvents", (Object) i);
        l.a("Age", (Object) j);
        l.a("Gender", (Object) k);
        l.a("UserId", (Object) "");
    }
}
