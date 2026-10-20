package defpackage;

import android.os.AsyncTask;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.Request;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.thuuz.gtv.App;
import com.thuuz.gtv.activity.AbstractActivity;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bqg extends bzy implements Session.StatusCallback {
    private final WeakReference<AbstractActivity> a;
    private App b;
    private SessionState c;
    private bqp d;
    private boolean e;
    private boolean f = true;
    private boolean g = true;
    private boolean h = true;

    public bqg(AbstractActivity abstractActivity) {
        this.a = new WeakReference<>(abstractActivity);
        this.b = abstractActivity.j();
    }

    @Override // com.facebook.Session.StatusCallback
    public void call(Session session, SessionState sessionState, Exception exc) {
        try {
            AbstractActivity b = b();
            if (b != null) {
                if (sessionState == null) {
                    getLogger().severe("no state in callback?");
                    return;
                }
                if (this.c == null) {
                    this.c = sessionState;
                    getLogger().info("starting session state: " + sessionState);
                    return;
                }
                if (exc != null) {
                    boolean z = exc instanceof FacebookOperationCanceledException;
                    if (!z) {
                        getLogger().info("FB exception: " + exc);
                    }
                    if (this.e) {
                        this.e = false;
                        a(z, exc.toString());
                        return;
                    } else {
                        if (z) {
                            getLogger().info("user canceled FB auth");
                            if (this.h) {
                                a(true);
                                return;
                            }
                            return;
                        }
                        if (this.f && b.d()) {
                            b.b("Problem with Facebook login", bbt.pls_try_again_later);
                            return;
                        }
                        return;
                    }
                }
                SessionState sessionState2 = this.c;
                if (sessionState.equals(this.c)) {
                    getLogger().info("ignoring redundant call for state:" + sessionState);
                    return;
                }
                getLogger().info("transitioning from/to FB state: " + this.c + " / " + sessionState);
                this.c = sessionState;
                if (this.e && SessionState.OPENED == sessionState2 && sessionState == SessionState.OPENED_TOKEN_UPDATED) {
                    this.e = false;
                    a();
                } else if (sessionState.isOpened()) {
                    getLogger().info("FB session opened, calling for profile with session: " + session);
                    a(session);
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    protected void a(boolean z, String str) {
    }

    protected void a() {
    }

    public void a(Session session) {
        boolean z = true;
        try {
            AbstractActivity b = b();
            if (b != null) {
                bqh bqhVar = new bqh(this);
                if (this.d != null) {
                    AsyncTask.Status status = this.d.getStatus();
                    switch (bqi.a[status.ordinal()]) {
                        case 1:
                            break;
                        case 2:
                            this.d.cancel(true);
                            break;
                        case 3:
                            try {
                                this.d.get(1L, TimeUnit.SECONDS);
                            } catch (Throwable th) {
                                z = false;
                            }
                            if (!z) {
                                getLogger().info("canceling fb request in order to re-request");
                                this.d.cancel(true);
                                break;
                            }
                            break;
                        default:
                            getLogger().severe("unknown task status: " + status);
                            break;
                    }
                }
                a(false);
                this.d = new bqp(Request.newMeRequest(session, bqhVar), b);
                this.d.execute(new Void[0]);
            }
        } catch (Throwable th2) {
            getLogger().log(Level.SEVERE, "problem: ", th2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(FacebookRequestError facebookRequestError) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(GraphUser graphUser) {
        getLogger().info("success fetching profile from FB. Saving social user info.");
        String id = graphUser.getId();
        c().o(id);
        c().aY().a(new bpw(id, graphUser.getFirstName(), graphUser.getLastName(), (String) graphUser.asMap().get("email")));
    }

    public void a(boolean z) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractActivity b() {
        return this.a.get();
    }

    public App c() {
        return this.b;
    }

    public void b(boolean z) {
        this.e = z;
        if (this.c == null) {
            Session activeSession = Session.getActiveSession();
            if (activeSession == null) {
                activeSession = new Session(c());
                Session.setActiveSession(activeSession);
            }
            if (!activeSession.isOpened()) {
                getLogger().severe("expected open fb session");
            }
            this.c = activeSession.getState();
            getLogger().info("setting previous state to current: " + this.c);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(String str, AbstractActivity abstractActivity, String str2) {
        getLogger().info("re-associating fb user: " + str + " that is different than previously associated: " + str2);
        new bqn(str2, abstractActivity, false, true).execute(new Void[0]);
    }

    public void c(boolean z) {
        this.f = z;
    }

    public void d(boolean z) {
        this.g = z;
    }

    public void d() {
        Session activeSession = Session.getActiveSession();
        if (activeSession != null) {
            this.c = activeSession.getState();
        }
    }

    public void e(boolean z) {
        this.h = z;
    }

    public void a(App app) {
        this.b = app;
    }
}
