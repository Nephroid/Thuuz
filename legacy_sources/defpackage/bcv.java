package defpackage;

import android.os.Binder;
import com.thuuz.gtv.ThuuzService;

/* loaded from: classes.dex */
public class bcv extends Binder {
    final /* synthetic */ ThuuzService a;

    public bcv(ThuuzService thuuzService) {
        this.a = thuuzService;
    }

    public ThuuzService a() {
        return this.a;
    }
}
