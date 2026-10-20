package defpackage;

import android.os.AsyncTask;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import com.thuuz.gtv.settings.SettingsSocialRegisterActivity;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* loaded from: classes.dex */
class bur extends AsyncTask<Void, Void, Integer> {
    final WeakReference<SettingsSocialRegisterActivity> a;
    final /* synthetic */ buq b;
    private final bpw c;

    public bur(buq buqVar, bpw bpwVar) {
        this.b = buqVar;
        this.c = bpwVar;
        this.a = new WeakReference<>(buqVar.a);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        Button button;
        if (this.a.get() != null) {
            button = this.b.a.l;
            button.setEnabled(false);
            this.b.a.e(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        Throwable th;
        int i;
        EditText editText;
        EditText editText2;
        EditText editText3;
        EditText editText4;
        try {
        } catch (Throwable th2) {
            th = th2;
            i = -1;
        }
        if (this.a.get() != null) {
            editText = this.b.a.i;
            Editable text = editText.getText();
            editText2 = this.b.a.j;
            Editable text2 = editText2.getText();
            editText3 = this.b.a.g;
            Editable text3 = editText3.getText();
            editText4 = this.b.a.h;
            Editable text4 = editText4.getText();
            if (text == null || text2 == null || text3 == null || text4 == null) {
                i = -1;
            } else {
                String trim = text3.toString().trim();
                String trim2 = text4.toString().trim();
                String trim3 = text.toString().trim();
                bzn bznVar = new bzn(this.b.a.j(), this.b.a.F(), trim, trim2, trim3, this.b.a.j().d(trim3, text2.toString()), this.b.a.j().aY().a(this.c.a()));
                int l = bznVar.l();
                try {
                    if (bznVar.y()) {
                        if (this.b.a.j().aX()) {
                            this.b.a.e().info("login causes google push change");
                            this.b.a.j().ap();
                            i = l;
                        } else {
                            this.b.a.j().ar();
                        }
                    }
                    i = l;
                } catch (Throwable th3) {
                    i = l;
                    th = th3;
                    this.b.a.e().log(Level.SEVERE, "problem: ", th);
                    return Integer.valueOf(i);
                }
            }
            return Integer.valueOf(i);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        Button button;
        Button button2;
        Button button3;
        if (this.a.get() != null && this.b.a.d()) {
            if (num == null) {
                num = -1;
            }
            try {
                try {
                    if (num.intValue() == 0) {
                        if (this.b.a.d()) {
                            this.b.a.d("Welcome to Thuuz!", "");
                            this.b.a.setResult(1);
                            this.b.a.finish();
                        }
                    } else {
                        this.b.a.e().warning("failed reg: " + num);
                        if (num.intValue() == -40) {
                            this.b.a.aG();
                        } else if (this.b.a.d()) {
                            this.b.a.b("Failed to Register", bbt.pls_try_again_later);
                        }
                    }
                    if (num.intValue() != -40) {
                        button3 = this.b.a.l;
                        button3.setEnabled(true);
                    }
                    this.b.a.e(false);
                } catch (Throwable th) {
                    this.b.a.e().log(Level.SEVERE, "problem: ", th);
                    if (num.intValue() != -40) {
                        button = this.b.a.l;
                        button.setEnabled(true);
                    }
                    this.b.a.e(false);
                }
            } catch (Throwable th2) {
                if (num.intValue() != -40) {
                    button2 = this.b.a.l;
                    button2.setEnabled(true);
                }
                this.b.a.e(false);
                throw th2;
            }
        }
    }
}
