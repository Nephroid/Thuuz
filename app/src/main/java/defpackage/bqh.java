package defpackage;
// [removed legacy tracker import]
// [removed legacy tracker import]
// [removed legacy tracker import]
// [removed legacy tracker import]
import java.util.logging.Level;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bqh implements Request.GraphUserCallback {
    final /* synthetic */ bqg a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bqh(bqg bqgVar) {
        this.a = bqgVar;
    }

    @Override // com.facebook.Request.GraphUserCallback
    public void onCompleted(GraphUser graphUser, Response response) {
        boolean z;
        boolean z2;
        try {
            if (this.a.b() != null) {
                FacebookRequestError error = response.getError();
                if (error == null) {
                    this.a.a(graphUser);
                    z2 = this.a.h;
                    if (z2) {
                        this.a.a(true);
                        return;
                    }
                    return;
                }
                this.a.getLogger().info("failure getting profile from FB: " + error);
                z = this.a.g;
                if (z) {
                    this.a.a(error);
                }
                this.a.a(true);
            }
        } catch (Throwable th) {
            this.a.getLogger().log(Level.SEVERE, "problem handling profile callback: ", th);
        }
    }
}
