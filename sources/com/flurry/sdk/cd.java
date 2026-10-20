package com.flurry.sdk;

import android.widget.Toast;
import com.flurry.sdk.cf;
import com.flurry.sdk.ei;

/* loaded from: classes.dex */
public class cd extends cf implements ei.a {
    static String a;
    static String b = "http://data.flurry.com/aap.do";
    static String c = "https://data.flurry.com/aap.do";
    private boolean j;

    public cd() {
        this(null);
    }

    cd(cf.a aVar) {
        super("Analytics", cd.class.getSimpleName());
        this.i = "AnalyticsData_";
        b();
        a(aVar);
    }

    private void b() {
        ei a2 = eh.a();
        this.j = ((Boolean) a2.a("UseHttps")).booleanValue();
        a2.a("UseHttps", (ei.a) this);
        ex.a(4, d, "initSettings, UseHttps = " + this.j);
        String str = (String) a2.a("ReportUrl");
        a2.a("ReportUrl", (ei.a) this);
        b(str);
        ex.a(4, d, "initSettings, ReportUrl = " + str);
    }

    @Override // com.flurry.sdk.ei.a
    public void a(String str, Object obj) {
        if (str.equals("UseHttps")) {
            this.j = ((Boolean) obj).booleanValue();
            ex.a(4, d, "onSettingUpdate, UseHttps = " + this.j);
        } else {
            if (str.equals("ReportUrl")) {
                String str2 = (String) obj;
                b(str2);
                ex.a(4, d, "onSettingUpdate, ReportUrl = " + str2);
                return;
            }
            ex.a(6, d, "onSettingUpdate internal error!");
        }
    }

    private void b(String str) {
        if (str != null && !str.endsWith(".do")) {
            ex.a(5, d, "overriding analytics agent report URL without an endpoint, are you sure?");
        }
        a = str;
    }

    String a() {
        if (a != null) {
            return a;
        }
        if (this.j) {
            return c;
        }
        return b;
    }

    @Override // com.flurry.sdk.cf
    protected void a(byte[] bArr, String str, String str2) {
        String a2 = a();
        ex.a(4, d, "FlurryDataSender: start upload data " + bArr + " with id = " + str + " to " + a2);
        this.g.submit(new ce(a2, str, str2, bArr, new ch() { // from class: com.flurry.sdk.cd.1
            @Override // com.flurry.sdk.ch
            public void a(final int i, String str3, String str4, String str5) {
                ex.e(cf.d, "FlurryDataSender: report " + str4 + " sent. HTTP response: " + i + " : " + str3);
                if (ex.c() <= 3 && ex.d()) {
                    eg.a().a(new Runnable() { // from class: com.flurry.sdk.cd.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Toast.makeText(eg.a().b(), "SD HTTP Response Code: " + i, 0).show();
                        }
                    });
                }
                cd.this.a(str4, str5, i);
                cd.this.d();
            }

            @Override // com.flurry.sdk.ch
            public void a(String str3, String str4) {
                ex.e(cf.d, "FlurryDataSender: could not send report " + str3);
                cd.this.b(str3, str4);
            }
        }));
    }

    @Override // com.flurry.sdk.cf
    protected void a(String str, String str2, final int i) {
        this.f.submit(new Runnable() { // from class: com.flurry.sdk.cd.2
            @Override // java.lang.Runnable
            public void run() {
                cl h;
                if (i == 200 && (h = bx.a().h()) != null) {
                    h.b();
                }
            }
        });
        super.a(str, str2, i);
    }
}
