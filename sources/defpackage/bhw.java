package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.activity.friends.ExpertsActivity;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bhw extends AsyncTask<Void, Void, Integer> {
    final /* synthetic */ ExpertsActivity a;

    private bhw(ExpertsActivity expertsActivity) {
        this.a = expertsActivity;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        this.a.e(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        int i;
        Throwable th;
        byh byhVar;
        List list;
        List list2;
        try {
            byhVar = new byh(this.a.j());
            i = byhVar.l();
        } catch (Throwable th2) {
            i = -1;
            th = th2;
        }
        try {
            if (byhVar.y()) {
                list = this.a.f;
                list.clear();
                list2 = this.a.f;
                list2.addAll(byhVar.b());
            }
        } catch (Throwable th3) {
            th = th3;
            this.a.e().log(Level.SEVERE, "problem: ", th);
            return Integer.valueOf(i);
        }
        return Integer.valueOf(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        try {
            if (this.a.d()) {
                if (num.intValue() == 0) {
                    this.a.aG();
                } else if (this.a.d()) {
                    this.a.b("Cannot Contact Server", bbt.pls_try_again_later);
                }
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        } finally {
            this.a.i = null;
            this.a.e(false);
        }
    }
}
