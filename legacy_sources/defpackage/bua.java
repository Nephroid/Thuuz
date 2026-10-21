package defpackage;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.thuuz.gtv.settings.SettingsLoginActivity;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* loaded from: classes.dex */
class bua extends AsyncTask<Void, Void, Integer> {
    final WeakReference<SettingsLoginActivity> a;
    final /* synthetic */ btz b;
    private String c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bua(btz btzVar) {
        this.b = btzVar;
        this.a = new WeakReference<>(this.b.a);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        Button button;
        if (this.a.get() != null) {
            button = this.b.a.i;
            button.setEnabled(false);
            this.b.a.e(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        int i;
        EditText editText;
        EditText editText2;
        try {
        } catch (Throwable th) {
            this.b.a.e().log(Level.SEVERE, "problem: ", th);
            i = -1;
        }
        if (this.a.get() != null) {
            editText = this.b.a.f;
            String trim = editText.getText().toString().trim();
            editText2 = this.b.a.g;
            this.c = this.b.a.j().d(trim, editText2.getText().toString());
            i = new bzg(this.b.a.j(), this.c).l();
            return Integer.valueOf(i);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        Button button;
        EditText editText;
        TextView textView;
        button = this.b.a.i;
        button.setEnabled(true);
        SettingsLoginActivity settingsLoginActivity = this.a.get();
        if (settingsLoginActivity != null && this.b.a.d()) {
            if (num == null) {
                num = -1;
            }
            try {
                if (num.intValue() == 0) {
                    settingsLoginActivity.j().a("account", "sign_in", settingsLoginActivity.j().aU(), 0L);
                    editText = this.b.a.f;
                    String obj = editText.getText().toString();
                    if (obj != null) {
                        obj = obj.trim();
                    }
                    this.b.a.j().b("username", obj);
                    this.b.a.j().s(this.c);
                    textView = this.b.a.h;
                    textView.setVisibility(4);
                    if (this.b.a.j().aZ()) {
                        this.b.a.aM();
                    } else {
                        this.b.a.aN();
                    }
                } else if (num.intValue() == -10) {
                    cad.b(this.b.a, "Authentication Failed", "Please check your email and password");
                } else {
                    cad.b(this.b.a, "Communication Failure", this.b.a.getResources().getString(bbt.pls_try_again_later));
                }
            } catch (Throwable th) {
                this.b.a.e().log(Level.SEVERE, "problem: ", th);
            } finally {
                this.b.a.e(false);
            }
        }
    }
}
