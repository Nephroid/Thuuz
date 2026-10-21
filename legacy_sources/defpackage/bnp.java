package defpackage;

import com.thuuz.gtv.activity.sports.GuideActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bnp implements Runnable {
    final /* synthetic */ bpo a;
    final /* synthetic */ GuideActivity b;

    public bnp(GuideActivity guideActivity, bpo bpoVar) {
        this.b = guideActivity;
        this.a = bpoVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean aX;
        try {
            GuideActivity guideActivity = this.b;
            aX = this.b.aX();
            new bnu(guideActivity, aX, null).execute(this.a);
        } catch (Throwable th) {
            this.b.e().log(Level.SEVERE, "Cannot handle: " + th, th);
        }
    }
}
