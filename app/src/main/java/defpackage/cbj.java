package defpackage;

import android.animation.Animator;
import android.annotation.TargetApi;
import com.thuuz.gtv.view.HorizColumnScroll;
import java.lang.ref.WeakReference;

@TargetApi(11)
/* loaded from: classes.dex */
public class cbj implements Animator.AnimatorListener {
    private final WeakReference<HorizColumnScroll> a;
    private final Animator b;

    public cbj(HorizColumnScroll horizColumnScroll, Animator animator) {
        this.a = new WeakReference<>(horizColumnScroll);
        this.b = animator;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        HorizColumnScroll horizColumnScroll = this.a.get();
        if (horizColumnScroll != null) {
            horizColumnScroll.a(this.b);
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        HorizColumnScroll horizColumnScroll = this.a.get();
        if (horizColumnScroll != null) {
            horizColumnScroll.a(this.b);
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
    }
}
