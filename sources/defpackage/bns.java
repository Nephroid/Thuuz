package defpackage;

import com.thuuz.gtv.activity.sports.GuideActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bns implements Runnable {
    final /* synthetic */ GuideActivity a;

    public bns(GuideActivity guideActivity) {
        this.a = guideActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.a.c();
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: " + th, th);
        }
    }
}
