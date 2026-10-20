package defpackage;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import com.thuuz.gtv.App;
import com.thuuz.gtv.settings.SettingsSocialRegisterActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class buo extends AsyncTask<Void, Void, Integer> {
    public String a;
    final /* synthetic */ SettingsSocialRegisterActivity b;

    public buo(SettingsSocialRegisterActivity settingsSocialRegisterActivity) {
        this.b = settingsSocialRegisterActivity;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        Button button;
        this.b.e(true);
        button = this.b.l;
        button.setEnabled(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        EditText editText;
        EditText editText2;
        EditText editText3;
        try {
            App j = this.b.j();
            editText = this.b.i;
            String trim = editText.getText().toString().trim();
            editText2 = this.b.j;
            this.a = j.d(trim, editText2.getText().toString().trim());
            if (new bzg(this.b.j(), this.a).l() == 0) {
                this.b.j().s(this.a);
                editText3 = this.b.i;
                String obj = editText3.getText().toString();
                if (obj != null) {
                    obj = obj.trim();
                }
                this.b.j().b("username", obj);
                bpw c = this.b.j().aY().c();
                if (c != null) {
                    return Integer.valueOf(new bzr(this.b.j(), this.b.j().aY().a(c.a())).l());
                }
            }
        } catch (Throwable th) {
            this.b.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        Button button;
        EditText editText;
        try {
            if (num.intValue() == 0) {
                this.b.c(bbt.welcome_back, "Your existing Thuuz account was associated with this Facebook identity");
                this.b.setResult(1);
                this.b.finish();
            } else {
                this.b.e().info("email found but pwd wrong");
                this.b.c("Account Already Exists", "Please enter the existing password for your Thuuz account.");
                this.b.c("Account Already Exists", "Please enter the existing password for your Thuuz account.");
                App j = this.b.j();
                editText = this.b.i;
                j.w(editText.getText().toString().trim());
            }
        } catch (Throwable th) {
            this.b.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
        this.b.e(false);
        button = this.b.l;
        button.setEnabled(true);
    }
}
