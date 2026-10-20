package defpackage;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import com.thuuz.gtv.App;
import com.thuuz.gtv.activity.fantasy.FantasyInviteActivity;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bff extends AsyncTask<Void, Void, Integer> {
    private final WeakReference<FantasyInviteActivity> a;

    private bff(FantasyInviteActivity fantasyInviteActivity) {
        this.a = new WeakReference<>(fantasyInviteActivity);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        FantasyInviteActivity fantasyInviteActivity = this.a.get();
        if (fantasyInviteActivity != null && !fantasyInviteActivity.isDestroyed()) {
            fantasyInviteActivity.e(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        EditText editText;
        int i;
        FantasyInviteActivity fantasyInviteActivity = this.a.get();
        if (fantasyInviteActivity == null || fantasyInviteActivity.isDestroyed()) {
            return -1;
        }
        try {
            App j = fantasyInviteActivity.j();
            editText = fantasyInviteActivity.h;
            String trim = editText.getText().toString().trim();
            i = fantasyInviteActivity.g;
            return Integer.valueOf(new bym(j, trim, i).l());
        } catch (Throwable th) {
            fantasyInviteActivity.e().log(Level.SEVERE, "problem: ", th);
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        Button button;
        int i;
        int i2;
        FantasyInviteActivity fantasyInviteActivity = this.a.get();
        if (fantasyInviteActivity != null) {
            try {
                if (fantasyInviteActivity.isDestroyed()) {
                    return;
                }
                button = fantasyInviteActivity.f;
                button.setEnabled(true);
                if (fantasyInviteActivity.d()) {
                    if (num.intValue() == -85) {
                        fantasyInviteActivity.c("Login Expired", "Please login again");
                        i = fantasyInviteActivity.i;
                        i2 = fantasyInviteActivity.g;
                        fantasyInviteActivity.a(i, i2);
                    } else if (num.intValue() != 0) {
                        fantasyInviteActivity.b("Failed to Invite", bbt.pls_try_again_later);
                    } else {
                        fantasyInviteActivity.d("Invitation Was Sent", "");
                        fantasyInviteActivity.finish();
                    }
                }
            } catch (Throwable th) {
                fantasyInviteActivity.e().log(Level.SEVERE, "problem: ", th);
            } finally {
                fantasyInviteActivity.e(false);
            }
        }
    }
}
