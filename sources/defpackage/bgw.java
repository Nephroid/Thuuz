package defpackage;

import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.fragments.GameListFragment;

/* loaded from: classes.dex */
public class bgw implements Runnable {
    final /* synthetic */ GameListFragment a;

    public bgw(GameListFragment gameListFragment) {
        this.a = gameListFragment;
    }

    @Override // java.lang.Runnable
    public void run() {
        AbstractActivity fragsActivity;
        fragsActivity = this.a.getFragsActivity();
        if (fragsActivity != null) {
            fragsActivity.e(true);
        }
    }
}
