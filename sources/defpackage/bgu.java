package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.feed.FeedFragment;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bgu extends AsyncTask<Void, Void, Integer> {
    final /* synthetic */ FeedFragment a;

    private bgu(FeedFragment feedFragment) {
        this.a = feedFragment;
    }

    public /* synthetic */ bgu(FeedFragment feedFragment, bgr bgrVar) {
        this(feedFragment);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        AbstractActivity fragsActivity;
        fragsActivity = this.a.getFragsActivity();
        if (fragsActivity != null) {
            fragsActivity.e(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        Logger logger;
        AbstractActivity fragsActivity;
        try {
            fragsActivity = this.a.getFragsActivity();
            if (fragsActivity == null || fragsActivity.isDestroyed()) {
                return -1;
            }
            for (long j = 0; !fragsActivity.j().bn() && j < 3000; j += 100) {
                Thread.sleep(100L);
            }
            if (fragsActivity.j().bn()) {
                return 0;
            }
            return Integer.valueOf(new bzj(true, fragsActivity.j()).l());
        } catch (Throwable th) {
            logger = this.a.getLogger();
            logger.log(Level.SEVERE, "Cannot get first cookie: ", th);
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        AbstractActivity fragsActivity;
        fragsActivity = this.a.getFragsActivity();
        if (fragsActivity != null && !fragsActivity.isDestroyed()) {
            if (num.intValue() == 0) {
                this.a.doRefresh(false);
            } else if (fragsActivity.d()) {
                fragsActivity.a("Cannot Contact Server", bbt.pls_try_refresh_later);
            }
            fragsActivity.e(false);
        }
    }
}
