package defpackage;

import android.content.DialogInterface;
import com.thuuz.gtv.activity.sports.GuideActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bnj implements DialogInterface.OnCancelListener {
    final /* synthetic */ GuideActivity a;

    public bnj(GuideActivity guideActivity) {
        this.a = guideActivity;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        try {
            this.a.setResult(0);
            this.a.av();
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
