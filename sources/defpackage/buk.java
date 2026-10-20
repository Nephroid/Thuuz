package defpackage;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import com.thuuz.gtv.settings.SettingsRegisterActivity;
import java.lang.ref.WeakReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class buk extends AsyncTask<Void, Void, Integer> {
    final WeakReference<SettingsRegisterActivity> a;

    private buk(SettingsRegisterActivity settingsRegisterActivity) {
        this.a = new WeakReference<>(settingsRegisterActivity);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        Button button;
        SettingsRegisterActivity settingsRegisterActivity = this.a.get();
        if (settingsRegisterActivity != null && !settingsRegisterActivity.isDestroyed()) {
            button = settingsRegisterActivity.j;
            button.setEnabled(false);
            settingsRegisterActivity.e(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        int i;
        EditText editText;
        Editable text;
        EditText editText2;
        Editable text2;
        EditText editText3;
        Editable text3;
        EditText editText4;
        Editable text4;
        SettingsRegisterActivity settingsRegisterActivity = this.a.get();
        if (settingsRegisterActivity == null || settingsRegisterActivity.isDestroyed()) {
            return -1;
        }
        Logger e = settingsRegisterActivity.e();
        try {
            editText = settingsRegisterActivity.f;
            text = editText.getText();
            editText2 = settingsRegisterActivity.g;
            text2 = editText2.getText();
            editText3 = settingsRegisterActivity.h;
            text3 = editText3.getText();
            editText4 = settingsRegisterActivity.i;
            text4 = editText4.getText();
        } catch (Throwable th) {
            e.log(Level.SEVERE, "problem: ", th);
        }
        if (text != null && text2 != null && text3 != null && text4 != null) {
            String trim = text3.toString().trim();
            String trim2 = text4.toString().trim();
            String trim3 = text.toString().trim();
            i = new bzn(settingsRegisterActivity.j(), settingsRegisterActivity.F(), trim, trim2, trim3, settingsRegisterActivity.j().d(trim3, text2.toString()), null).l();
            return Integer.valueOf(i);
        }
        i = -1;
        return Integer.valueOf(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        Button button;
        SettingsRegisterActivity settingsRegisterActivity = this.a.get();
        if (settingsRegisterActivity != null && !settingsRegisterActivity.isDestroyed()) {
            if (num == null) {
                num = -1;
            }
            if (num.intValue() == 0) {
                settingsRegisterActivity.j().a("account", "register", settingsRegisterActivity.j().aU(), 0L);
                if (settingsRegisterActivity.j().aX()) {
                    settingsRegisterActivity.e().info("login causes google push change");
                    settingsRegisterActivity.j().ap();
                } else {
                    settingsRegisterActivity.j().ar();
                }
            }
            try {
                if (!settingsRegisterActivity.isDestroyed()) {
                    button = settingsRegisterActivity.j;
                    button.setEnabled(true);
                    if (num.intValue() == 0) {
                        if (settingsRegisterActivity.d()) {
                            settingsRegisterActivity.d("Welcome to Thuuz!", "");
                            settingsRegisterActivity.setResult(1);
                            settingsRegisterActivity.finish();
                        }
                    } else {
                        settingsRegisterActivity.e().warning("failed reg: " + num);
                        if (num.intValue() == -40) {
                            try {
                                AlertDialog.Builder builder = new AlertDialog.Builder(settingsRegisterActivity);
                                builder.setTitle("Email Recognized").setCancelable(true).setMessage("Please sign in instead of registering.");
                                AlertDialog create = builder.create();
                                create.setButton(-1, "Sign In", new bul(this, settingsRegisterActivity, create));
                                settingsRegisterActivity.a(create);
                                create.show();
                            } catch (Throwable th) {
                                settingsRegisterActivity.e().log(Level.SEVERE, "Problem: ", th);
                            }
                        } else if (settingsRegisterActivity.d()) {
                            settingsRegisterActivity.b("Failed to Register", bbt.pls_try_again_later);
                        }
                    }
                }
            } catch (Throwable th2) {
                settingsRegisterActivity.e().log(Level.SEVERE, "problem: ", th2);
            } finally {
                settingsRegisterActivity.e(false);
            }
        }
    }
}
