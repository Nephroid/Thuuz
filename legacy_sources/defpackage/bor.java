package defpackage;

import android.view.View;
import android.widget.CheckBox;
import com.thuuz.gtv.activity.sports.ShareActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bor implements View.OnClickListener {
    final /* synthetic */ ShareActivity a;

    private bor(ShareActivity shareActivity) {
        this.a = shareActivity;
    }

    public /* synthetic */ bor(ShareActivity shareActivity, bof bofVar) {
        this(shareActivity);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            if (((CheckBox) view).isChecked()) {
                this.a.aI();
            } else {
                this.a.f("", "");
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
