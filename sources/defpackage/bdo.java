package defpackage;

import android.content.ComponentName;
import android.os.IBinder;
import com.thuuz.gtv.ThuuzService;
import com.thuuz.gtv.activity.AbstractActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bdo extends cat {
    final /* synthetic */ AbstractActivity a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bdo(AbstractActivity abstractActivity, AbstractActivity abstractActivity2) {
        super(abstractActivity2);
        this.a = abstractActivity;
    }

    @Override // defpackage.cat, android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            this.a.r = false;
            this.a.s = true;
            if (this.a.isDestroyed()) {
                this.a.unbindService(this);
            } else {
                ThuuzService a = ((bcv) iBinder).a();
                this.a.a(a);
                a.a(this.a);
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "bind problem: ", th);
        }
    }
}
