package defpackage;
// [removed legacy tracker import]
import java.util.logging.Level;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bqf implements Runnable {
    final /* synthetic */ bqe a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bqf(bqe bqeVar) {
        this.a = bqeVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Session f;
        try {
            Session activeSession = Session.getActiveSession();
            if (activeSession != null) {
                this.a.getLogger().info("fb logout clearing token from fb system cache");
                activeSession.closeAndClearTokenInformation();
            }
            this.a.getLogger().info("fb logout resetting session, clearing social ID from stored prefs");
            f = this.a.f();
            Session.setActiveSession(f);
            this.a.a((bpw) null);
            this.a.d().o((String) null);
        } catch (Throwable th) {
            this.a.getLogger().log(Level.WARNING, "problem logging out of facebook: ", th);
        }
    }
}
