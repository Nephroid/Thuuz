package defpackage;

import android.view.View;
import com.thuuz.gtv.view.HorizColumnScroll;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class cbo implements View.OnClickListener {
    final /* synthetic */ HorizColumnScroll a;

    private cbo(HorizColumnScroll horizColumnScroll) {
        this.a = horizColumnScroll;
    }

    public /* synthetic */ cbo(HorizColumnScroll horizColumnScroll, cbl cblVar) {
        this(horizColumnScroll);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            this.a.a(this.a.e, this.a.c(this.a.getLeftVisibleIndex() - 1));
        } catch (Throwable th) {
            this.a.getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }
}
