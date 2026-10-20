package defpackage;

import android.view.View;
import android.widget.Toast;
import com.thuuz.gtv.ThuuzService;
import com.thuuz.gtv.settings.SettingsSoundsActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bcd implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ ThuuzService b;

    public bcd(ThuuzService thuuzService, View view) {
        this.b = thuuzService;
        this.a = view;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            SettingsSoundsActivity.TOAST_TYPE I = this.b.m().I();
            if (I != null) {
                Toast toast = new Toast(this.b.m());
                toast.setGravity(81, 0, 200);
                toast.setDuration(I.a());
                toast.setView(this.a);
                toast.show();
                if (I == SettingsSoundsActivity.TOAST_TYPE.DOUBLE_LONG) {
                    Toast toast2 = new Toast(this.b.m());
                    toast2.setGravity(81, 0, 200);
                    toast2.setDuration(I.a());
                    toast2.setView(this.a);
                    toast2.show();
                }
                if (I == SettingsSoundsActivity.TOAST_TYPE.TRIPLE_LONG) {
                    Toast toast3 = new Toast(this.b.m());
                    toast3.setGravity(81, 0, 200);
                    toast3.setDuration(I.a());
                    toast3.setView(this.a);
                    toast3.show();
                }
            }
        } catch (Throwable th) {
            this.b.H().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
