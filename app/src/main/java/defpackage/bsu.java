package defpackage;

import android.os.AsyncTask;
import android.widget.Button;
import com.thuuz.gtv.settings.SettingsEditAccountActivity;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bsu extends AsyncTask<Void, Void, Integer> {
    final WeakReference<SettingsEditAccountActivity> a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ bst d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bsu(bst bstVar, String str, String str2) {
        this.d = bstVar;
        this.b = str;
        this.c = str2;
        this.a = new WeakReference<>(this.d.a);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        Button button;
        if (this.a.get() != null) {
            button = this.d.a.h;
            button.setEnabled(false);
            this.d.a.e(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        int i;
        try {
        } catch (Throwable th) {
            this.d.a.e().log(Level.SEVERE, "problem: ", th);
            i = -1;
        }
        if (this.a.get() == null) {
            return null;
        }
        i = new bzm(this.d.a.j(), this.b, this.c).l();
        return Integer.valueOf(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        Button button;
        String string;
        String string2;
        button = this.d.a.h;
        button.setEnabled(true);
        if (this.a.get() != null && this.d.a.d()) {
            if (num == null) {
                num = -1;
            }
            try {
                if (num.intValue() == 0) {
                    this.d.a.d("Success: saved name change", "");
                    this.d.a.finish();
                } else {
                    if (num.intValue() == -40) {
                        string = "Email Already Used";
                        string2 = "Please either sign in or use a different one.";
                    } else {
                        string = this.d.a.getResources().getString(bbt.unable_to_update);
                        string2 = this.d.a.getResources().getString(bbt.pls_try_again_later);
                    }
                    this.d.a.c(string, string2);
                }
            } catch (Throwable th) {
                this.d.a.e().log(Level.SEVERE, "problem: ", th);
            } finally {
                this.d.a.e(false);
            }
        }
    }
}
