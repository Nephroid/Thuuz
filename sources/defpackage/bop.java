package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.sports.ShareActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bop extends AsyncTask<Void, Void, Integer> {
    final IService a;
    final /* synthetic */ ShareActivity b;
    private final boolean c;

    public bop(ShareActivity shareActivity, IService iService) {
        this(shareActivity, iService, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public bop(ShareActivity shareActivity, IService iService, boolean z) {
        this.b = shareActivity;
        this.c = z;
        this.a = iService;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        this.b.e(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        int i;
        int i2 = -1;
        try {
            App j = this.b.j();
            IService iService = this.a;
            i = this.b.p;
            i2 = new byn(j, iService, i).l();
        } catch (Throwable th) {
            this.b.e().log(Level.SEVERE, "problem: ", th);
        }
        return Integer.valueOf(i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        try {
            if (this.b.d() && num.intValue() != 0) {
                this.b.a("Cannot Download Rivals List", bbt.pls_try_again_later);
            } else if (this.c) {
                new bou(this.b, null).execute(new Void[0]);
            } else {
                this.b.c();
            }
        } catch (Throwable th) {
            this.b.e().log(Level.SEVERE, "problem: ", th);
        } finally {
            this.b.e(false);
        }
    }
}
