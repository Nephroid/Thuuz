package defpackage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;

/* JADX INFO: Add missing generic type declarations: [T] */
/* loaded from: classes.dex */
class adi<T> implements Callable<T> {
    final /* synthetic */ String a;
    final /* synthetic */ adj b;
    final /* synthetic */ adg c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public adi(adg adgVar, String str, adj adjVar) {
        this.c = adgVar;
        this.a = str;
        this.b = adjVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12, types: [int] */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9 */
    @Override // java.util.concurrent.Callable
    public T call() {
        HttpURLConnection httpURLConnection;
        ?? r1 = 0;
        r1 = 0;
        r1 = 0;
        try {
            try {
                httpURLConnection = (HttpURLConnection) new URL(this.a).openConnection();
            } catch (Throwable th) {
                th = th;
            }
        } catch (MalformedURLException e) {
            e = e;
        } catch (IOException e2) {
            e = e2;
        }
        try {
            httpURLConnection.connect();
            r1 = httpURLConnection.getResponseCode();
        } catch (MalformedURLException e3) {
            r1 = httpURLConnection;
            e = e3;
            adn.d("Error making HTTP request.", e);
            if (r1 != 0) {
                r1.disconnect();
            }
            return (T) this.b.b();
        } catch (IOException e4) {
            r1 = httpURLConnection;
            e = e4;
            adn.d("Error making HTTP request.", e);
            if (r1 != 0) {
                r1.disconnect();
            }
            return (T) this.b.b();
        } catch (Throwable th2) {
            r1 = httpURLConnection;
            th = th2;
            if (r1 != 0) {
                r1.disconnect();
            }
            throw th;
        }
        if (r1 < 200 || r1 > 299) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return (T) this.b.b();
        }
        T t = (T) this.b.b(httpURLConnection.getInputStream());
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
        return t;
    }
}
