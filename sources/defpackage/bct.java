package defpackage;

import com.thuuz.gtv.ThuuzService;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.game_page.GamePageActivity;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bct {
    final /* synthetic */ ThuuzService a;
    private String b;
    private int c;
    private WeakReference<AbstractActivity> d;

    public bct(ThuuzService thuuzService, AbstractActivity abstractActivity) {
        this.a = thuuzService;
        try {
            this.d = new WeakReference<>(abstractActivity);
            this.b = abstractActivity.getClass().getSimpleName();
            if (abstractActivity instanceof GamePageActivity) {
                this.c = ((GamePageActivity) abstractActivity).aO();
            }
        } catch (Throwable th) {
            thuuzService.H().log(Level.SEVERE, "problem: ", th);
        }
    }
}
