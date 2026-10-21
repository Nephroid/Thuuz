package defpackage;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.http.Headers;
import android.provider.CalendarContract;
import android.text.TextUtils;
import com.google.android.gms.internal.gv;
import java.util.Map;

@zy
/* loaded from: classes.dex */
public class wz {
    private final gv a;
    private final Map<String, String> b;
    private final Context c;
    private String d;
    private long e;
    private long f;
    private String g;
    private String h;

    public wz(gv gvVar, Map<String, String> map) {
        this.a = gvVar;
        this.b = map;
        this.c = gvVar.k();
        c();
    }

    private String a(String str) {
        return TextUtils.isEmpty(this.b.get(str)) ? "" : this.b.get(str);
    }

    private void c() {
        this.d = a("description");
        this.g = a("summary");
        this.e = acx.d(this.b.get("start"));
        this.f = acx.d(this.b.get("end"));
        this.h = a(Headers.LOCATION);
    }

    public void a() {
        if (!new ui(this.c).f()) {
            adn.e("This feature is not available on this version of the device.");
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this.c);
        builder.setTitle(acl.a(cv.create_calendar_title, "Create calendar event"));
        builder.setMessage(acl.a(cv.create_calendar_message, "Allow Ad to create a calendar event?"));
        builder.setPositiveButton(acl.a(cv.accept, "Accept"), new xa(this));
        builder.setNegativeButton(acl.a(cv.decline, "Decline"), new xb(this));
        builder.create().show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Intent b() {
        Intent data = new Intent("android.intent.action.EDIT").setData(CalendarContract.Events.CONTENT_URI);
        data.putExtra("title", this.g);
        data.putExtra("eventLocation", this.h);
        data.putExtra("description", this.d);
        data.putExtra("beginTime", this.e);
        data.putExtra("endTime", this.f);
        data.setFlags(268435456);
        return data;
    }
}
