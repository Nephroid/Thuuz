package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.activity.nobar.OnboardPage6TeamsActivity;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class blk extends AsyncTask<Void, Void, bmb> {
    private final String a;
    private final WeakReference<OnboardPage6TeamsActivity> b;

    public blk(String str, OnboardPage6TeamsActivity onboardPage6TeamsActivity) {
        this.a = str;
        this.b = new WeakReference<>(onboardPage6TeamsActivity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public bmb doInBackground(Void... voidArr) {
        OnboardPage6TeamsActivity onboardPage6TeamsActivity = this.b.get();
        if (onboardPage6TeamsActivity == null) {
            return new bmb(null, -1);
        }
        try {
            bzs bzsVar = new bzs(onboardPage6TeamsActivity.j(), onboardPage6TeamsActivity.F(), onboardPage6TeamsActivity.j().aY().a(this.a));
            bzsVar.l();
            onboardPage6TeamsActivity.o = bzsVar.x().toString();
            return new bmb(bzsVar.c(), bzsVar.v());
        } catch (Throwable th) {
            onboardPage6TeamsActivity.e().log(Level.SEVERE, "cannot handle: ", th);
            return new bmb(null, -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(bmb bmbVar) {
        bll bllVar;
        OnboardPage6TeamsActivity onboardPage6TeamsActivity = this.b.get();
        if (onboardPage6TeamsActivity != null) {
            try {
                if (bmbVar.b == 0) {
                    onboardPage6TeamsActivity.n = bmbVar.a;
                    onboardPage6TeamsActivity.k = true;
                    bllVar = onboardPage6TeamsActivity.g;
                    bllVar.a(onboardPage6TeamsActivity.j().x());
                }
                onboardPage6TeamsActivity.b(bmbVar);
            } catch (Throwable th) {
                onboardPage6TeamsActivity.e().log(Level.SEVERE, "cannot handle: ", th);
            }
        }
    }
}
