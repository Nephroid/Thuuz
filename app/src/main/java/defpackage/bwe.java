package defpackage;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Button;
import com.facebook.internal.NativeProtocol;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.friends.WebviewActivity;
import com.thuuz.gtv.activity.sports.ShareActivity;
import java.util.logging.Level;
import twitter4j.auth.RequestToken;

/* loaded from: classes.dex */
class bwe extends AsyncTask<Void, Void, String> {
    final /* synthetic */ bwd a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bwe(bwd bwdVar) {
        this.a = bwdVar;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        Button button;
        button = this.a.a.h;
        button.setEnabled(false);
        this.a.a.e(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String doInBackground(Void... voidArr) {
        String str;
        Throwable th;
        RequestToken requestToken;
        RequestToken requestToken2;
        int i = -1;
        try {
            ShareActivity.SHARE_TYPE share_type = ShareActivity.SHARE_TYPE.SHARE_TYPE_GAME;
            if (this.a.a.getIntent() != null && this.a.a.getIntent().hasExtra("share_type")) {
                share_type = ShareActivity.SHARE_TYPE.a(this.a.a.getIntent().getIntExtra("share_type", ShareActivity.SHARE_TYPE.SHARE_TYPE_GAME.a()));
            }
            if (this.a.a.getIntent() != null && this.a.a.getIntent().hasExtra("share_game_id")) {
                i = this.a.a.getIntent().getIntExtra("share_game_id", -1);
            }
            String str2 = "thuuz://twitter_oauth?share_type=" + share_type.a() + "&share_game_id=" + i;
            this.a.a.f = this.a.a.j().bN().getOAuthRequestToken(str2);
            requestToken = this.a.a.f;
            str = requestToken.getAuthenticationURL();
        } catch (Throwable th2) {
            str = null;
            th = th2;
        }
        try {
            IService F = this.a.a.F();
            if (F != null) {
                requestToken2 = this.a.a.f;
                F.a(requestToken2);
            }
        } catch (Throwable th3) {
            th = th3;
            this.a.a.e().log(Level.SEVERE, "problem: ", th);
            return str;
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(String str) {
        Button button;
        Button button2;
        Button button3;
        try {
            try {
                if (str == null) {
                    if (this.a.a.d()) {
                        this.a.a.b("Problem with Twitter", bbt.pls_try_again_later);
                    }
                    try {
                        return;
                    } catch (Throwable th) {
                        return;
                    }
                }
                this.a.a.j().z(null);
                this.a.a.j().y(null);
                Intent intent = new Intent(this.a.a.k(), (Class<?>) WebviewActivity.class);
                intent.putExtra(NativeProtocol.IMAGE_URL_KEY, str);
                intent.putExtra("title", "Twitter Sign-in");
                intent.addFlags(1073741824);
                intent.addFlags(268435456);
                this.a.a.startActivity(intent);
                try {
                    this.a.a.e(false);
                    button3 = this.a.a.h;
                    button3.setEnabled(true);
                } catch (Throwable th2) {
                    this.a.a.e().log(Level.SEVERE, "problem: ", th2);
                }
            } catch (Throwable th3) {
                this.a.a.e().log(Level.SEVERE, "problem: ", th3);
                try {
                    this.a.a.e(false);
                    button2 = this.a.a.h;
                    button2.setEnabled(true);
                } catch (Throwable th4) {
                    this.a.a.e().log(Level.SEVERE, "problem: ", th4);
                }
            }
        } finally {
            try {
                this.a.a.e(false);
                button = this.a.a.h;
                button.setEnabled(true);
            } catch (Throwable th5) {
                this.a.a.e().log(Level.SEVERE, "problem: ", th5);
            }
        }
    }
}
