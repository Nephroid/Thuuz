package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.activity.friends.ExpertsActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bhx extends AsyncTask<Void, Void, Integer> {
    final bpc a;
    final /* synthetic */ ExpertsActivity b;

    public bhx(ExpertsActivity expertsActivity, bpc bpcVar) {
        this.b = expertsActivity;
        this.a = bpcVar;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        try {
            this.b.e(true);
        } catch (Throwable th) {
            this.b.e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        if (this.a == null) {
            this.b.e().severe("cannot find friend in Follow");
            return -1;
        }
        try {
            byv byvVar = new byv(this.b.j(), this.a.a());
            byvVar.l();
            return Integer.valueOf(byvVar.v());
        } catch (Throwable th) {
            this.b.e().log(Level.SEVERE, "problem: ", th);
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        try {
            if (this.b.d()) {
                if (num.intValue() == 0) {
                    this.a.b(true);
                    this.b.aG();
                } else {
                    this.b.b("Failed to Follow", bbt.pls_try_again_later);
                }
            }
        } catch (Throwable th) {
            this.b.e().log(Level.SEVERE, "problem: ", th);
        } finally {
            this.b.e(false);
        }
    }
}
