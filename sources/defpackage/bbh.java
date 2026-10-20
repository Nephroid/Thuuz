package defpackage;

import com.thuuz.gtv.App;
import java.io.File;

/* loaded from: classes.dex */
public class bbh extends as {
    final /* synthetic */ App a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bbh(App app, File file) {
        super(file);
        this.a = app;
    }

    @Override // defpackage.as, defpackage.u
    public synchronized v a(String str) {
        v vVar;
        try {
            vVar = super.a(str);
        } catch (OutOfMemoryError e) {
            this.a.bX();
            vVar = null;
        }
        return vVar;
    }
}
