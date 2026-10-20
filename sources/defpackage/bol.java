package defpackage;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.thuuz.gtv.activity.sports.ShareActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bol extends AsyncTask<Void, Void, String> {
    final /* synthetic */ ShareActivity a;

    public bol(ShareActivity shareActivity) {
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
        String str;
        Throwable th;
        String str2;
        String str3;
        String str4 = "";
        try {
            str2 = this.a.r;
            if (str2 != null) {
                str3 = this.a.r;
                if (str3.length() != 0) {
                    str4 = this.a.r;
                    if (str4.startsWith("http://")) {
                        str4 = str4.substring("http://".length());
                    }
                    bzq bzqVar = new bzq(this.a.j(), str4);
                    bzqVar.l();
                    if (bzqVar.y()) {
                        str4 = bzqVar.b();
                        this.a.m = true;
                    }
                    str = str4;
                    try {
                        if (str.startsWith("http://")) {
                            return str.substring("http://".length());
                        }
                        return str;
                    } catch (Throwable th2) {
                        th = th2;
                        this.a.e().log(Level.SEVERE, "problem: ", th);
                        return str;
                    }
                }
            }
            return "";
        } catch (Throwable th3) {
            str = str4;
            th = th3;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(String str) {
        String aN;
        EditText editText;
        Button button;
        View view;
        try {
            ShareActivity shareActivity = this.a;
            aN = this.a.aN();
            shareActivity.f(str, aN);
            editText = this.a.s;
            editText.setEnabled(true);
            button = this.a.v;
            button.setEnabled(true);
            view = this.a.w;
            view.setVisibility(8);
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        } finally {
            this.a.e(false);
            this.a.n = null;
        }
    }
}
