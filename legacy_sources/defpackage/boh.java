package defpackage;

import android.os.AsyncTask;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import com.thuuz.gtv.activity.sports.ShareActivity;
import java.util.Map;
import java.util.logging.Level;
import twitter4j.TwitterException;

/* loaded from: classes.dex */
public class boh extends AsyncTask<Void, Void, String> {
    final /* synthetic */ ShareActivity a;

    public boh(ShareActivity shareActivity) {
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
        boolean z;
        String str;
        Map map;
        EditText editText;
        EditText editText2;
        int i;
        ShareActivity.SHARE_TYPE share_type;
        TextView textView;
        int i2;
        boolean z2 = false;
        try {
            editText = this.a.s;
            if (editText.getText() != null) {
                editText2 = this.a.s;
                String trim = editText2.getText().toString().trim();
                int length = trim.length();
                i = this.a.h;
                if (length > i) {
                    i2 = this.a.h;
                    trim = trim.substring(0, i2);
                }
                share_type = this.a.o;
                if (share_type == ShareActivity.SHARE_TYPE.SHARE_TYPE_GAME) {
                    trim = trim + " ";
                }
                StringBuilder append = new StringBuilder().append(trim);
                textView = this.a.g;
                this.a.j().bN().updateStatus(append.append((Object) textView.getText()).toString());
                z2 = true;
            }
            z = true;
            str = null;
        } catch (TwitterException e) {
            this.a.e().log(Level.WARNING, "problem with twitter: " + e);
            if (e.toString().contains("Received authentication challenge is null")) {
                this.a.j().bM();
                str = "Twitter permissions seem to have been revoked";
                z = false;
            } else {
                String errorMessage = e.getErrorMessage();
                if (errorMessage == null || errorMessage.length() == 0) {
                    errorMessage = e.toString();
                }
                String str2 = "Problem accessing Twitter: " + errorMessage;
                z = true;
                str = str2;
            }
        } catch (Throwable th) {
            if (th.getMessage() == null || !th.getMessage().contains("Authentication credentials are missing")) {
                this.a.e().log(Level.SEVERE, "problem with twitter: ", th);
            } else {
                this.a.e().log(Level.WARNING, "problem with twitter: " + th);
            }
            String str3 = "Problem accessing Twitter: " + th;
            z = true;
            str = str3;
        }
        map = this.a.y;
        map.put(ShareActivity.SHARE_TO.SHARE_TO_TWITTER, new bot(this.a, z2, str));
        if (z) {
            return null;
        }
        return "no_cred";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(String str) {
        CheckBox checkBox;
        if ("no_cred".equals(str)) {
            this.a.c("Twitter Sign-in Expired", "Please try again (sign in to Twitter)");
            checkBox = this.a.f;
            checkBox.setChecked(false);
        }
        this.a.aK();
        this.a.e(false);
    }
}
