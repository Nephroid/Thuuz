package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bde extends AsyncTask<Void, Void, Integer> {
    final /* synthetic */ AbstractActivity a;

    public bde(AbstractActivity abstractActivity) {
        this.a = abstractActivity;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        int i = -1;
        try {
            IService F = this.a.F();
            if (F != null) {
                i = F.B();
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
        return Integer.valueOf(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        bmd bmdVar;
        try {
            this.a.H = null;
            if (num.intValue() == 0) {
                IService F = this.a.F();
                if (F != null) {
                    this.a.G = F.o();
                }
                bmdVar = this.a.F;
                bmdVar.notifyDataSetChanged();
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
