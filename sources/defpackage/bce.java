package defpackage;

import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.ThuuzService;
import com.thuuz.gtv.data.FetchRequest;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bce extends bza {
    final /* synthetic */ ThuuzService a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bce(ThuuzService thuuzService, App app, FetchRequest fetchRequest, IService iService, boolean z) {
        super(app, fetchRequest, iService, z);
        this.a = thuuzService;
    }

    @Override // defpackage.bya
    protected void a() {
        try {
            a(0);
            d(true);
            bzc bzcVar = new bzc(this, new byz(this), new byy(this));
            getLogger().info("retrying task for cookie");
            k().bW().a(bzcVar);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }
}
