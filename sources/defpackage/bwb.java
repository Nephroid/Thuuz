package defpackage;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import com.appsflyer.AppsFlyerLib;
import com.facebook.internal.ServerProtocol;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.settings.SettingsTwitterOauthActivity;
import java.util.HashMap;
import java.util.logging.Level;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/* loaded from: classes.dex */
public class bwb extends AsyncTask<Void, Void, String> {
    private final AbstractActivity a;
    private final RequestToken b;
    private final String c;
    private final Uri d;

    public bwb(AbstractActivity abstractActivity, RequestToken requestToken, String str, Uri uri) {
        this.a = abstractActivity;
        this.b = requestToken;
        this.c = str;
        this.d = uri;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String doInBackground(Void... voidArr) {
        AccessToken oAuthAccessToken;
        try {
            oAuthAccessToken = this.a.j().bN().getOAuthAccessToken(this.b, this.c);
        } catch (Throwable th) {
            bzy.getStaticLogger(SettingsTwitterOauthActivity.class).log(Level.SEVERE, "problem in ConvertTwitterOauth: ", th);
        }
        if (oAuthAccessToken != null) {
            try {
                HashMap hashMap = new HashMap();
                Context applicationContext = this.a.j().getApplicationContext();
                this.a.j().getClass();
                AppsFlyerLib.a(applicationContext, "twitter_on", hashMap);
            } catch (Throwable th2) {
                bzy.getStaticLogger(SettingsTwitterOauthActivity.class).log(Level.SEVERE, "problem in AppsFlyer event: ", th2);
            }
            bzy.getStaticLogger(SettingsTwitterOauthActivity.class).info("saving twitter token");
            String token = oAuthAccessToken.getToken();
            String tokenSecret = oAuthAccessToken.getTokenSecret();
            this.a.j().y(token);
            this.a.j().z(tokenSecret);
            this.a.j().bN();
            return ServerProtocol.DIALOG_RETURN_SCOPES_TRUE;
        }
        bzy.getStaticLogger(SettingsTwitterOauthActivity.class).warning("cannot get twitter access token");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(String str) {
        try {
            if (str == null) {
                this.a.setResult(8);
            } else {
                this.a.setResult(-1);
            }
            SettingsTwitterOauthActivity.b(this.d, this.a, this.c);
            this.a.finish();
        } catch (Throwable th) {
            bzy.getStaticLogger(SettingsTwitterOauthActivity.class).log(Level.SEVERE, "problem: ", th);
        }
    }
}
