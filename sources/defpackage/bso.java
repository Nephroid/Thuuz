package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.settings.SettingsChangePassActivity;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* loaded from: classes.dex */
class bso extends AsyncTask<Void, Void, Integer> {
    final WeakReference<SettingsChangePassActivity> a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ bsn d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bso(bsn bsnVar, String str, String str2) {
        this.d = bsnVar;
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
        i = new bye(this.d.a.j(), this.b, this.c).l();
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
                    this.d.a.d("Password Changed", "Please remember the new one.");
                    this.d.a.setResult(-1);
                    this.d.a.finish();
                } else {
                    if (num.intValue() == -10) {
                        str = "Authentication Failed";
                        string = "Please check the password.";
                    } else {
                        str = "Failed to Change";
                        string = this.d.a.getResources().getString(bbt.pls_try_again_later);
                    }
                    this.d.a.c(str, string);
                }
                this.d.a.e(false);
            } catch (Throwable th) {
                this.d.a.e().log(Level.SEVERE, "problem: ", th);
            }
        }
    }
}
