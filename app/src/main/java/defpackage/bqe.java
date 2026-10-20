package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.util.Base64;
// [removed legacy tracker import]
// [removed legacy tracker import]
// [removed legacy tracker import]
// [removed legacy tracker import]
// [removed legacy tracker import]
// [removed legacy tracker import]
import com.thuuz.gtv.App;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.sports.ShareActivity;
import com.thuuz.gtv.util.ServerChoice;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bqe extends bzy {
    public static final String[] a = {"email"};
    public static final List<String> b = Arrays.asList(a);
    public static final String[] c = {"publish_actions", "email"};
    public static final String d = ServerChoice.q;
    private App e;
    private bpw f = null;

    public bqe(App app) {
        this.e = null;
        this.e = app;
    }

    public boolean a() {
        Session activeSession = Session.getActiveSession();
        return activeSession != null && activeSession.isOpened() && activeSession.getAccessToken() != null && activeSession.getAccessToken().length() > 0 && Utility.isSubset(b, activeSession.getPermissions());
    }

    public synchronized void a(Session.StatusCallback statusCallback, AbstractActivity abstractActivity) {
        try {
            Session activeSession = Session.getActiveSession();
            if (activeSession == null) {
                activeSession = f();
                Session.setActiveSession(f());
            } else if (SessionState.CREATED != activeSession.getState()) {
                activeSession = f();
                Session.setActiveSession(activeSession);
            }
            if (!a()) {
                if (abstractActivity == null) {
                    getLogger().severe("fb auth was called without activity context");
                }
                if (activeSession.isOpened()) {
                    getLogger().info("have perms: " + activeSession.getPermissions() + "; requesting our usual initial perms, setting session listener to expect it...");
                    if (statusCallback instanceof bqg) {
                        ((bqg) statusCallback).b(true);
                    }
                    activeSession.requestNewReadPermissions(new Session.NewPermissionsRequest(abstractActivity, b));
                } else {
                    getLogger().info("attempting auth freshly");
                    Session.OpenRequest openRequest = new Session.OpenRequest(abstractActivity);
                    if (statusCallback != null) {
                        openRequest.setCallback(statusCallback);
                    }
                    openRequest.setPermissions(b);
                    activeSession.openForRead(openRequest);
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Session f() {
        return new Session(d());
    }

    public void a(boolean z) {
        Thread thread = new Thread(new bqf(this));
        if (z) {
            thread.start();
        } else {
            thread.run();
        }
    }

    public String b() {
        return "facebook";
    }

    public void a(AbstractActivity abstractActivity, Request.GraphUserListCallback graphUserListCallback) {
        getLogger().log(Level.INFO, "get facebook friends");
        new bqp(Request.newMyFriendsRequest(Session.getActiveSession(), graphUserListCallback), abstractActivity).execute(new Void[0]);
    }

    public void a(String str, String str2, AbstractActivity abstractActivity, Request.Callback callback, bqg bqgVar) {
        Session activeSession = Session.getActiveSession();
        if (activeSession == null) {
            getLogger().info("creating session in share facebook post");
            activeSession = f();
            Session.setActiveSession(activeSession);
        }
        if (e()) {
            getLogger().info("calling fb to share facebook post...");
            Request newStatusUpdateRequest = Request.newStatusUpdateRequest(activeSession, str2, callback);
            Bundle bundle = new Bundle();
            bundle.putString("link", str);
            bundle.putString("message", str2);
            newStatusUpdateRequest.setParameters(bundle);
            new bqp(newStatusUpdateRequest, abstractActivity).execute(new Void[0]);
            return;
        }
        if (a()) {
            getLogger().info("requesting new publish perm, setting session listener to expect it...");
            bqgVar.b(true);
            activeSession.requestNewPublishPermissions(new Session.NewPermissionsRequest(abstractActivity, (List<String>) Arrays.asList(c)));
        } else {
            getLogger().info("need auth to share, have session: " + activeSession);
            if (abstractActivity instanceof ShareActivity) {
                getLogger().info("setting ShareActivity to be ready to share after authenticating");
                ((ShareActivity) abstractActivity).h(true);
                a((Session.StatusCallback) null, abstractActivity);
            }
        }
    }

    public void a(Context context, String str, WebDialog.OnCompleteListener onCompleteListener) {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("link", d);
            new WebDialog.RequestsDialogBuilder(context, Session.getActiveSession(), bundle).setOnCompleteListener(onCompleteListener).setTo(str).setMessage("Check out Thuuz and you'll never miss an exciting game.").build().show();
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
            if (onCompleteListener != null) {
                onCompleteListener.onComplete(new Bundle(), new FacebookException(th.getMessage()));
            }
        }
    }

    public void a(bpw bpwVar) {
        this.f = bpwVar;
    }

    public bpw c() {
        return this.f;
    }

    public String a(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("prov", b());
            jSONObject.put("sid", str);
            Session activeSession = Session.getActiveSession();
            if (activeSession == null) {
                return "";
            }
            jSONObject.put("tok", activeSession.getAccessToken());
            return "B" + Base64.encodeToString(jSONObject.toString().getBytes(), 8);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
            return "";
        }
    }

    public App d() {
        return this.e;
    }

    public boolean e() {
        Session activeSession = Session.getActiveSession();
        return activeSession != null && activeSession.getPermissions().contains("publish_actions");
    }
}
