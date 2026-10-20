package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.activity.AbstractActivity;
import java.lang.ref.WeakReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bqq extends AsyncTask<Void, Void, Integer> {
    private final String a;
    private final WeakReference<AbstractActivity> b;
    private final boolean c;

    public bqq(String str, AbstractActivity abstractActivity, boolean z) {
        this.a = str;
        this.b = new WeakReference<>(abstractActivity);
        this.c = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPreExecute() {
        AbstractActivity abstractActivity = this.b.get();
        if (abstractActivity != null) {
            abstractActivity.e(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        AbstractActivity abstractActivity = this.b.get();
        if (abstractActivity == null) {
            return -1;
        }
        Logger e = abstractActivity.e();
        try {
            return Integer.valueOf(new bzg(this.a, abstractActivity.j()).l());
        } catch (Throwable th) {
            e.log(Level.SEVERE, "problem: ", th);
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        AbstractActivity abstractActivity = this.b.get();
        if (abstractActivity != null) {
            try {
                if (!abstractActivity.isDestroyed()) {
                    if (num.intValue() == 0) {
                        abstractActivity.e().info("Signed in using Facebook account.");
                        if (!abstractActivity.isDestroyed()) {
                            if (abstractActivity.j().aC()) {
                                abstractActivity.d("Facebook Connected", "Now start sharing great games with your friends.");
                            }
                            abstractActivity.setResult(1);
                            abstractActivity.finish();
                        }
                    } else if (num.intValue() == -10) {
                        abstractActivity.e().info("No Thuuz account yet associated with this Facebook ID.");
                        abstractActivity.Q();
                        abstractActivity.e().info("launched to social registration page; finishing FB connect-button activity");
                        if (this.c) {
                            abstractActivity.finish();
                        }
                    } else if (abstractActivity.d()) {
                        abstractActivity.b("Facebook Connection Failed", bbt.pls_try_again_later);
                    }
                }
            } catch (Throwable th) {
                abstractActivity.e().log(Level.SEVERE, "problem: ", th);
            } finally {
                abstractActivity.e(false);
            }
        }
    }
}
