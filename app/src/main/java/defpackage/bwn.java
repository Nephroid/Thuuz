package defpackage;

import android.widget.ScrollView;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.settings.loc.TVProviderFragment;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bwn implements bwr {
    private final WeakReference<AbstractActivity> a;
    private final WeakReference<TVProviderFragment> b;
    private bxx c;

    public bwn(AbstractActivity abstractActivity, TVProviderFragment tVProviderFragment) {
        this.a = new WeakReference<>(abstractActivity);
        this.b = new WeakReference<>(tVProviderFragment);
    }

    @Override // defpackage.bwr
    public void aG() {
        TVProviderFragment tVProviderFragment;
        AbstractActivity abstractActivity = this.a.get();
        if (abstractActivity != null && !abstractActivity.isDestroyed() && (tVProviderFragment = this.b.get()) != null && tVProviderFragment.getZipEditText().getText() != null) {
            new bxy(abstractActivity, tVProviderFragment.getZipEditText().getText().toString(), tVProviderFragment).execute(new Void[0]);
        }
    }

    @Override // defpackage.bwr
    public void g(String str) {
        AbstractActivity abstractActivity = this.a.get();
        if (abstractActivity != null && !abstractActivity.isDestroyed()) {
            try {
                TVProviderFragment tVProviderFragment = this.b.get();
                if (tVProviderFragment != null) {
                    tVProviderFragment.getSelectProviderLayout().setVisibility(8);
                    bxx bxxVar = this.c;
                    if (bxxVar != null) {
                        bxxVar.cancel(true);
                        abstractActivity.e().info("canceling ongoing TvProvidersTask");
                        abstractActivity.e(false);
                    }
                    this.c = new bxx(abstractActivity, tVProviderFragment);
                    this.c.execute(str);
                }
            } catch (Throwable th) {
                abstractActivity.e().log(Level.SEVERE, "Cannot handle: ", th);
            }
        }
    }

    @Override // defpackage.bwr
    public boolean a(bpu bpuVar) {
        AbstractActivity abstractActivity;
        boolean z;
        if (bpuVar == null || (abstractActivity = this.a.get()) == null || abstractActivity.isDestroyed()) {
            return false;
        }
        boolean z2 = !abstractActivity.j().e() && bpuVar.o();
        if (z2) {
            z = z2;
        } else {
            z = !abstractActivity.j().e() && bpuVar.f();
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.bwr
    public void aH() {
        AbstractActivity abstractActivity = this.a.get();
        if (abstractActivity != 0 && !abstractActivity.isDestroyed()) {
            ((bwr) abstractActivity).aH();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.bwr
    public void aI() {
        AbstractActivity abstractActivity = this.a.get();
        if (abstractActivity != 0 && !abstractActivity.isDestroyed()) {
            ((bwr) abstractActivity).aI();
        }
    }

    @Override // defpackage.bwr
    public void aJ() {
        this.c = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.bwr
    public void aK() {
        AbstractActivity abstractActivity = this.a.get();
        if (abstractActivity != 0 && !abstractActivity.isDestroyed()) {
            ((bwr) abstractActivity).aK();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.bwr
    public ScrollView aM() {
        AbstractActivity abstractActivity = this.a.get();
        if (abstractActivity == 0 || abstractActivity.isDestroyed()) {
            return null;
        }
        return ((bwr) abstractActivity).aM();
    }
}
