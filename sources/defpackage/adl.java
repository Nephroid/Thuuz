package defpackage;

import android.content.Context;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@zy
/* loaded from: classes.dex */
public final class adl extends acq {
    private final String a;
    private final Context b;
    private final String c;
    private String d;

    public adl(Context context, String str, String str2) {
        this.d = null;
        this.b = context;
        this.a = str;
        this.c = str2;
    }

    public adl(Context context, String str, String str2, String str3) {
        this.d = null;
        this.b = context;
        this.a = str;
        this.c = str2;
        this.d = str3;
    }

    @Override // defpackage.acq
    public void a() {
        try {
            adn.d("Pinging URL: " + this.c);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.c).openConnection();
            try {
                if (this.d == null) {
                    acx.a(this.b, this.a, true, httpURLConnection);
                } else {
                    acx.a(this.b, this.a, true, httpURLConnection, this.d);
                }
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode < 200 || responseCode >= 300) {
                    adn.e("Received non-success response code " + responseCode + " from pinging URL: " + this.c);
                }
            } finally {
                httpURLConnection.disconnect();
            }
        } catch (IOException e) {
            adn.e("Error while pinging URL: " + this.c + ". " + e.getMessage());
        } catch (IndexOutOfBoundsException e2) {
            adn.e("Error while parsing ping URL: " + this.c + ". " + e2.getMessage());
        }
    }

    @Override // defpackage.acq
    public void b() {
    }
}
