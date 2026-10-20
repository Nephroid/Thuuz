package defpackage;

import com.thuuz.gtv.activity.sports.GuideActivity;

/* loaded from: classes.dex */
public class bnm implements Runnable {
    final /* synthetic */ GuideActivity a;

    public bnm(GuideActivity guideActivity) {
        this.a = guideActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.c();
    }
}
