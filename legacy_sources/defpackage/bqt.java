package defpackage;

import com.thuuz.gtv.App;
import java.util.logging.Level;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bqt {
    String a;
    final /* synthetic */ bqr b;
    private String c;

    private bqt(bqr bqrVar) {
        this.b = bqrVar;
        this.a = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ bqt(bqr bqrVar, bqs bqsVar) {
        this(bqrVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public caq a(String... strArr) {
        this.a = strArr[0];
        this.c = strArr[1];
        return a(this.a);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(caq caqVar) {
        if (caqVar != null) {
            try {
                if (caqVar.b()) {
                    this.b.a(caqVar, this.c);
                }
            } catch (Throwable th) {
                this.b.b().log(Level.SEVERE, "cannot post tv providers: ", th);
                return;
            }
        }
        this.b.b().info("cannot get providers. ");
    }

    private caq a(String str) {
        App app;
        try {
            app = this.b.g;
            bzx bzxVar = new bzx(app, str);
            bzxVar.l();
            return bzxVar.c();
        } catch (Throwable th) {
            this.b.b().log(Level.SEVERE, "problem: ", th);
            return null;
        }
    }
}
