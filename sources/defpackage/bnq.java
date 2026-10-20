package defpackage;

import com.thuuz.gtv.activity.sports.GuideActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bnq implements Runnable {
    final /* synthetic */ GuideActivity a;

    public bnq(GuideActivity guideActivity) {
        this.a = guideActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.a.e(true);
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "cannot handle: " + th, th);
        }
    }
}
