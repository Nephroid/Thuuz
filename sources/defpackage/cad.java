package defpackage;

import android.app.AlertDialog;
import com.thuuz.gtv.activity.AbstractActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class cad {
    public static void a(AbstractActivity abstractActivity, String str, String str2) {
        a(abstractActivity, str, str2, true);
    }

    public static void b(AbstractActivity abstractActivity, String str, String str2) {
        a(abstractActivity, str, str2, false);
    }

    private static void a(AbstractActivity abstractActivity, String str, String str2, boolean z) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(abstractActivity);
            builder.setTitle(str).setCancelable(true).setMessage(str2);
            AlertDialog create = builder.create();
            create.setButton(-1, "Ok", new cae(create, z, abstractActivity));
            if (z) {
                create.setOnCancelListener(new caf(abstractActivity));
            }
            abstractActivity.a(create);
            create.show();
        } catch (Throwable th) {
            abstractActivity.e().log(Level.SEVERE, "Problem with alert: ", th);
        }
    }
}
