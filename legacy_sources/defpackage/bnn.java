package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.sports.GuideActivity;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bnn implements Runnable {
    final /* synthetic */ GuideActivity a;

    public bnn(GuideActivity guideActivity) {
        this.a = guideActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        Map bb;
        Set i;
        try {
            bb = this.a.bb();
            Iterator it = bb.keySet().iterator();
            while (it.hasNext()) {
                cbn cbnVar = (cbn) ((View) bb.get((Integer) it.next())).getTag();
                bms bmsVar = (bms) cbnVar.e.getGameListAdapter();
                if (bmsVar.f()) {
                    bmsVar.clear();
                }
                if (bmsVar.isEmpty()) {
                    i = this.a.i(cbnVar.a.intValue());
                    if (i.size() > 0) {
                        this.a.e().info("after orientation, filled code: " + cbnVar.a + " with  " + i.size() + "games");
                        bmsVar.c().addAll(i);
                        this.a.c((List<Integer>) bmsVar.c());
                        bmsVar.notifyDataSetChanged();
                    }
                }
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
