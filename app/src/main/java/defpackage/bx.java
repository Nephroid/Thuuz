package defpackage;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.appsflyer.AppsFlyerLib;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.protocol.HTTP;

/* loaded from: classes.dex */
public class bx extends AsyncTask<String, Void, String> {
    public Map<String, String> a;
    private String b = null;
    private boolean c = false;
    private Context d;

    public bx(Context context) {
        this.d = context;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String doInBackground(String... strArr) {
        try {
            URL url = new URL(strArr[0]);
            if (!bw.a().e()) {
                Log.i("AppsFlyer_3.0", "call stats = " + url + "parameters = " + this.a.toString());
            }
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setReadTimeout(30000);
            httpsURLConnection.setConnectTimeout(30000);
            httpsURLConnection.setRequestMethod(HttpPost.METHOD_NAME);
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setRequestProperty(HTTP.CONTENT_TYPE, "application/text");
            OutputStream outputStream = httpsURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, HTTP.UTF_8));
            bufferedWriter.write(AppsFlyerLib.a(this.a));
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            httpsURLConnection.connect();
            if (httpsURLConnection.getResponseCode() == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    this.b += readLine;
                }
                if (!bw.a().e()) {
                    Log.i("AppsFlyer_3.0", "Status 200 ok");
                }
            }
        } catch (MalformedURLException e) {
            if (!bw.a().e()) {
                Log.i("AppsFlyer_3.0", "MalformedURLException: " + e.toString());
            }
        } catch (ProtocolException e2) {
            if (!bw.a().e()) {
                Log.i("AppsFlyer_3.0", "ProtocolException: " + e2.toString());
            }
        } catch (IOException e3) {
            if (!bw.a().e()) {
                Log.i("AppsFlyer_3.0", "IOException: " + e3.toString());
            }
        } catch (Exception e4) {
            if (!bw.a().e()) {
                Log.i("AppsFlyer_3.0", "Exception: " + e4.toString());
            }
        }
        return this.b;
    }

    @Override // android.os.AsyncTask
    protected void onCancelled() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(String str) {
        if (this.c) {
            Log.i("AppsFlyer_3.0", "Connection error");
        } else {
            Log.i("AppsFlyer_3.0", "Connection call succeeded");
        }
    }
}
