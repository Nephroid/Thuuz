package defpackage;

import android.app.Activity;
import android.app.PendingIntent;
import android.support.v4.widget.ViewDragHelper;
import org.apache.http.protocol.HTTP;
import org.joda.time.DateTimeConstants;

/* loaded from: classes.dex */
public final class gw {
    public static final gw a = new gw(0, null);
    private final PendingIntent b;
    private final int c;

    public gw(int i, PendingIntent pendingIntent) {
        this.c = i;
        this.b = pendingIntent;
    }

    private String e() {
        switch (this.c) {
            case 0:
                return "SUCCESS";
            case 1:
                return "SERVICE_MISSING";
            case 2:
                return "SERVICE_VERSION_UPDATE_REQUIRED";
            case 3:
                return "SERVICE_DISABLED";
            case 4:
                return "SIGN_IN_REQUIRED";
            case 5:
                return "INVALID_ACCOUNT";
            case 6:
                return "RESOLUTION_REQUIRED";
            case 7:
                return "NETWORK_ERROR";
            case 8:
                return "INTERNAL_ERROR";
            case 9:
                return "SERVICE_INVALID";
            case 10:
                return "DEVELOPER_ERROR";
            case 11:
                return "LICENSE_CHECK_FAILED";
            case DateTimeConstants.DECEMBER /* 12 */:
            default:
                return "unknown status code " + this.c;
            case HTTP.CR /* 13 */:
                return "CANCELED";
            case 14:
                return "TIMEOUT";
            case ViewDragHelper.EDGE_ALL /* 15 */:
                return "INTERRUPTED";
        }
    }

    public void a(Activity activity, int i) {
        if (a()) {
            activity.startIntentSenderForResult(this.b.getIntentSender(), i, null, 0, 0, 0);
        }
    }

    public boolean a() {
        return (this.c == 0 || this.b == null) ? false : true;
    }

    public boolean b() {
        return this.c == 0;
    }

    public int c() {
        return this.c;
    }

    public PendingIntent d() {
        return this.b;
    }

    public String toString() {
        return jy.a(this).a("statusCode", e()).a("resolution", this.b).toString();
    }
}
