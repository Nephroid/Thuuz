package defpackage;

import android.content.Context;
import android.location.Location;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.android.gms.internal.fi;
import com.google.android.gms.internal.fk;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@zy
/* loaded from: classes.dex */
public final class abu extends abk {
    private static final Object a = new Object();
    private static abu b;
    private final Context c;
    private final ace d;
    private final vu e;
    private final uj f;

    abu(Context context, uj ujVar, vu vuVar, ace aceVar) {
        this.c = context;
        this.d = aceVar;
        this.e = vuVar;
        this.f = ujVar;
    }

    public static abu a(Context context, uj ujVar, vu vuVar, ace aceVar) {
        abu abuVar;
        synchronized (a) {
            if (b == null) {
                b = new abu(context.getApplicationContext(), ujVar, vuVar, aceVar);
            }
            abuVar = b;
        }
        return abuVar;
    }

    private static ads a(String str) {
        return new abw(str);
    }

    public static fk a(Context context, String str, String str2, String str3, acc accVar) {
        int responseCode;
        try {
            acb acbVar = new acb();
            adn.a("AdRequestServiceImpl: Sending request: " + str2);
            URL url = new URL(str2);
            long elapsedRealtime = SystemClock.elapsedRealtime();
            URL url2 = url;
            int i = 0;
            while (true) {
                HttpURLConnection httpURLConnection = (HttpURLConnection) url2.openConnection();
                try {
                    acx.a(context, str, false, httpURLConnection);
                    if (!TextUtils.isEmpty(str3)) {
                        httpURLConnection.addRequestProperty("x-afma-drt-cookie", str3);
                    }
                    if (accVar != null && !TextUtils.isEmpty(accVar.c())) {
                        httpURLConnection.setDoOutput(true);
                        byte[] bytes = accVar.c().getBytes();
                        httpURLConnection.setFixedLengthStreamingMode(bytes.length);
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                        bufferedOutputStream.write(bytes);
                        bufferedOutputStream.close();
                    }
                    responseCode = httpURLConnection.getResponseCode();
                    Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
                    if (responseCode >= 200 && responseCode < 300) {
                        String url3 = url2.toString();
                        String a2 = acx.a(new InputStreamReader(httpURLConnection.getInputStream()));
                        a(url3, headerFields, a2, responseCode);
                        acbVar.a(url3, headerFields, a2);
                        return acbVar.a(elapsedRealtime);
                    }
                    a(url2.toString(), headerFields, (String) null, responseCode);
                    if (responseCode < 300 || responseCode >= 400) {
                        break;
                    }
                    String headerField = httpURLConnection.getHeaderField("Location");
                    if (TextUtils.isEmpty(headerField)) {
                        adn.e("No location header to follow redirect.");
                        return new fk(0);
                    }
                    url2 = new URL(headerField);
                    i++;
                    if (i > 5) {
                        adn.e("Too many redirects.");
                        return new fk(0);
                    }
                    acbVar.a(headerFields);
                } finally {
                    httpURLConnection.disconnect();
                }
            }
            adn.e("Received error HTTP response code: " + responseCode);
            return new fk(0);
        } catch (IOException e) {
            adn.e("Error while connecting to ad server: " + e.getMessage());
            return new fk(2);
        }
    }

    private static fk a(Context context, uj ujVar, vu vuVar, ace aceVar, fi fiVar) {
        String string;
        adn.a("Starting ad request from service.");
        vuVar.a();
        acd acdVar = new acd(context);
        if (acdVar.l == -1) {
            adn.a("Device is offline.");
            return new fk(2);
        }
        aby abyVar = new aby(fiVar.f.packageName);
        if (fiVar.c.c != null && (string = fiVar.c.c.getString("_ad")) != null) {
            return abx.a(context, fiVar, string);
        }
        Location a2 = vuVar.a(250L);
        String a3 = ujVar.a();
        String a4 = abx.a(fiVar, acdVar, a2, ujVar.b(), ujVar.c());
        if (a4 == null) {
            return new fk(0);
        }
        adm.a.post(new abv(context, fiVar, abyVar, a(a4), a3));
        try {
            acc accVar = abyVar.a().get(10L, TimeUnit.SECONDS);
            if (accVar == null) {
                return new fk(0);
            }
            if (accVar.a() != -2) {
                return new fk(accVar.a());
            }
            return a(context, fiVar.k.b, accVar.d(), accVar.f() ? aceVar.a(fiVar.g.packageName) : null, accVar);
        } catch (Exception e) {
            return new fk(0);
        }
    }

    private static void a(String str, Map<String, List<String>> map, String str2, int i) {
        if (adn.a(2)) {
            adn.d("Http Response: {\n  URL:\n    " + str + "\n  Headers:");
            if (map != null) {
                for (String str3 : map.keySet()) {
                    adn.d("    " + str3 + ":");
                    Iterator<String> it = map.get(str3).iterator();
                    while (it.hasNext()) {
                        adn.d("      " + it.next());
                    }
                }
            }
            adn.d("  Body:");
            if (str2 != null) {
                for (int i2 = 0; i2 < Math.min(str2.length(), 100000); i2 += 1000) {
                    adn.d(str2.substring(i2, Math.min(str2.length(), i2 + 1000)));
                }
            } else {
                adn.d("    null");
            }
            adn.d("  Response Code:\n    " + i + "\n}");
        }
    }

    @Override // defpackage.abj
    public fk a(fi fiVar) {
        return a(this.c, this.f, this.e, this.d, fiVar);
    }
}
