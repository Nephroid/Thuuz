package defpackage;

import com.thuuz.gtv.activity.sports.GuideActivity;
import com.thuuz.gtv.view.ListViewDpad;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bno implements Runnable {
    final /* synthetic */ ListViewDpad a;
    final /* synthetic */ GuideActivity b;

    public bno(GuideActivity guideActivity, ListViewDpad listViewDpad) {
        this.b = guideActivity;
        this.a = listViewDpad;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (!this.b.j().e()) {
                this.a.setSelection(0);
            }
        } catch (Throwable th) {
            this.b.e().log(Level.SEVERE, "problem setting initial selection: ", th);
        }
    }
}
