package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.settings.SettingsChangeEmailActivity;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* loaded from: classes.dex */
class bsk extends AsyncTask<Void, Void, Integer> {
    final WeakReference<SettingsChangeEmailActivity> a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ bsj d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bsk(bsj bsjVar, String str, String str2) {
        this.d = bsjVar;
        this.b = str;
        this.c = str2;
        this.a = new WeakReference<>(this.d.a);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        if (this.a.get() != null) {
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
        i = new byd(this.d.a.j(), this.b, this.c).l();
        return Integer.valueOf(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        String str;
        String string;
        if (this.a.get() != null && this.d.a.d()) {
            if (num == null) {
                num = -1;
            }
            try {
                if (num.intValue() == 0) {
                    this.d.a.d("Email Changed", "Please remember the new one: " + this.b);
                    this.d.a.setResult(-1);
                    this.d.a.finish();
                } else {
                    if (num.intValue() == -10) {
                        str = "Authentication Failed";
                        string = "Please enter your current password.";
                    } else {
                        str = "Failed to Change";
                        string = this.d.a.getResources().getString(bbt.pls_try_again_later);
                    }
                    this.d.a.c(str, string);
                }
            } catch (Throwable th) {
                this.d.a.e().log(Level.SEVERE, "problem: ", th);
            } finally {
                this.d.a.e(false);
            }
        }
    }
}
