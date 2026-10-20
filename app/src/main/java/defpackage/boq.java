package defpackage;

import android.view.View;
import android.widget.Button;
import com.facebook.FacebookRequestError;
import com.facebook.model.GraphUser;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.sports.ShareActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class boq extends bqg {
    public boq(AbstractActivity abstractActivity) {
        super(abstractActivity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bqg
    public void a(GraphUser graphUser) {
        boolean z;
        try {
            super.a(graphUser);
            String bd = c().bd();
            if (bd == null || bd.length() == 0) {
                getLogger().severe("ShareActivity has fb profile success, but invalid social id!");
                return;
            }
            ShareActivity shareActivity = (ShareActivity) b();
            if (shareActivity != null) {
                if (!shareActivity.j().aZ()) {
                    getLogger().info("attempting associate for fb user: " + graphUser);
                    shareActivity.a(bd, false);
                } else if (!c().bc().equals(bd)) {
                    getLogger().info("attempting re-associate for fb user: " + graphUser);
                    a(bd, shareActivity, c().bc());
                }
                z = shareActivity.k;
                if (z) {
                    getLogger().info("profile success has 'share' flag, proceeding to share...");
                    shareActivity.k = false;
                    shareActivity.aG();
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bqg
    public void a(FacebookRequestError facebookRequestError) {
        try {
            super.a(facebookRequestError);
            ShareActivity shareActivity = (ShareActivity) b();
            if (shareActivity != null) {
                shareActivity.c("Failed to Connect", "Facebook says: " + shareActivity.a(facebookRequestError));
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    @Override // defpackage.bqg
    public void a(boolean z) {
        Button button;
        View view;
        try {
            ShareActivity shareActivity = (ShareActivity) b();
            if (shareActivity != null) {
                button = shareActivity.v;
                button.setEnabled(z);
                int i = z ? 8 : 0;
                view = shareActivity.w;
                view.setVisibility(i);
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    @Override // defpackage.bqg
    protected void a() {
        try {
            getLogger().info("perms upgrade successful");
            if (c().aY().e()) {
                ShareActivity shareActivity = (ShareActivity) b();
                if (shareActivity != null) {
                    getLogger().info("sharing after perms upgrade...");
                    shareActivity.aG();
                }
            } else {
                getLogger().severe("onPermsUpgrade still has no pub perms?");
                ShareActivity shareActivity2 = (ShareActivity) b();
                if (shareActivity2 != null) {
                    shareActivity2.b("Failed to get Facebook 'write' permission", bbt.pls_try_again_later);
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    @Override // defpackage.bqg
    protected void a(boolean z, String str) {
        if (!z) {
            try {
                a(true);
                ShareActivity shareActivity = (ShareActivity) b();
                if (shareActivity != null) {
                    shareActivity.g(str);
                }
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "Cannot handle: ", th);
            }
        }
    }
}
