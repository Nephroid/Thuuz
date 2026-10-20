package defpackage;

import android.widget.TextView;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.fragments.GameListFragment;
import com.thuuz.gtv.data.GameData;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bgx implements Runnable {
    final /* synthetic */ bpo a;
    final /* synthetic */ GameListFragment b;

    public bgx(GameListFragment gameListFragment, bpo bpoVar) {
        this.b = gameListFragment;
        this.a = bpoVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        AbstractActivity fragsActivity;
        Logger logger;
        bms bmsVar;
        bms bmsVar2;
        TextView textView;
        TextView textView2;
        TextView textView3;
        fragsActivity = this.b.getFragsActivity();
        try {
            if (fragsActivity == null) {
                return;
            }
            if (this.a == null || this.a.b()) {
                if (fragsActivity.d()) {
                    fragsActivity.c(bbt.unable_to_update, bbt.pls_try_refresh_later);
                }
                return;
            }
            String l = this.a.l();
            if (l != null && l.length() > 0) {
                textView3 = this.b.title;
                textView3.setText(l);
            }
            bmsVar = this.b.adapter;
            List<Integer> c = bmsVar.c();
            c.clear();
            c.addAll(this.a.c());
            IService F = fragsActivity.F();
            if (F != null) {
                HashMap hashMap = new HashMap();
                for (Integer num : c) {
                    GameData b = F.b(num);
                    if (b != null) {
                        hashMap.put(num, b);
                    }
                }
                Collections.sort(c, new bmr(fragsActivity.j(), hashMap));
            }
            bmsVar2 = this.b.adapter;
            bmsVar2.notifyDataSetChanged();
            boolean z = c.size() == 0;
            if (z) {
                textView2 = this.b.empty;
                textView2.setText("No Live Games");
            }
            textView = this.b.empty;
            textView.setVisibility(z ? 0 : 8);
        } catch (Throwable th) {
            logger = this.b.getLogger();
            logger.log(Level.SEVERE, "problem: ", th);
        } finally {
            fragsActivity.e(false);
            this.b.setTaskFinished();
        }
    }
}
