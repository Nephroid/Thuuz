package defpackage;

import android.os.CountDownTimer;
import android.widget.ScrollView;
import com.thuuz.gtv.settings.loc.TVProviderFragment;

/* loaded from: classes.dex */
public class bxd extends CountDownTimer {
    final /* synthetic */ ScrollView a;
    final /* synthetic */ int b;
    final /* synthetic */ TVProviderFragment c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bxd(TVProviderFragment tVProviderFragment, long j, long j2, ScrollView scrollView, int i) {
        super(j, j2);
        this.c = tVProviderFragment;
        this.a = scrollView;
        this.b = i;
    }

    @Override // android.os.CountDownTimer
    public void onTick(long j) {
        this.a.scrollTo(0, this.a.getScrollY() - (this.b / 20));
    }

    @Override // android.os.CountDownTimer
    public void onFinish() {
    }
}
