package defpackage;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.CheckBox;
// [removed legacy tracker import]
import com.thuuz.gtv.activity.sports.ShareActivity;
import com.thuuz.gtv.settings.SettingsTwitterOauthActivity;
import java.util.logging.Level;
import twitter4j.auth.Authorization;

/* loaded from: classes.dex */
public class bom extends AsyncTask<Void, Void, String> {
    final /* synthetic */ ShareActivity a;

    public bom(ShareActivity shareActivity) {
        this.a = shareActivity;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        this.a.e(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String doInBackground(Void... voidArr) {
        try {
            Authorization authorization = this.a.j().bN().getAuthorization();
            if (authorization == null) {
                return null;
            }
            if (authorization.isEnabled()) {
                return Response.SUCCESS_KEY;
            }
            return null;
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(String str) {
        String str2;
        String aN;
        CheckBox checkBox;
        ShareActivity.SHARE_TYPE share_type;
        int i;
        try {
            if (str != null) {
                str2 = this.a.r;
                if (str2 != null) {
                    this.a.aM();
                } else {
                    ShareActivity shareActivity = this.a;
                    aN = this.a.aN();
                    shareActivity.f("", aN);
                }
                checkBox = this.a.f;
                checkBox.setChecked(true);
            } else {
                Intent intent = new Intent(this.a.k(), (Class<?>) SettingsTwitterOauthActivity.class);
                share_type = this.a.o;
                intent.putExtra("share_type", share_type.a());
                i = this.a.p;
                intent.putExtra("share_game_id", i);
                this.a.startActivity(intent);
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        } finally {
            this.a.e(false);
        }
    }
}
