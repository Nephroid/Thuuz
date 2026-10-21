package defpackage;

import android.content.Intent;
import android.os.PowerManager;
import com.thuuz.gtv.ThuuzService;
import com.thuuz.gtv.activity.fantasy.PlayerActivity;
import java.util.logging.Level;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bbx implements Runnable {
    final /* synthetic */ Intent a;
    final /* synthetic */ ThuuzService b;

    public bbx(ThuuzService thuuzService, Intent intent) {
        this.b = thuuzService;
        this.a = intent;
    }

    @Override // java.lang.Runnable
    public void run() {
        PowerManager.WakeLock wakeLock;
        PowerManager.WakeLock wakeLock2;
        int i;
        try {
            wakeLock2 = this.b.C;
            wakeLock2.acquire();
            this.b.m().a(System.currentTimeMillis());
            String stringExtra = this.a.getStringExtra("sender");
            String stringExtra2 = this.a.getStringExtra("msg");
            String stringExtra3 = this.a.getStringExtra("gid");
            String stringExtra4 = this.a.getStringExtra("type");
            String stringExtra5 = this.a.getStringExtra("json");
            if ("Share".equals(stringExtra4) || "FSShare".equals(stringExtra4)) {
                if (stringExtra5 == null) {
                    this.b.a(stringExtra, stringExtra2, stringExtra3);
                } else if (!"FSTeam".equals(new bpq(new JSONObject(stringExtra5)).e("source"))) {
                    this.b.a(stringExtra, stringExtra2, stringExtra3);
                }
            } else if ("Invite".equals(stringExtra4)) {
                this.b.b(stringExtra, stringExtra2);
            } else if ("Follow".equals(stringExtra4)) {
                this.b.c(stringExtra, stringExtra2);
            } else if ("GameReminder".equals(stringExtra4)) {
                this.b.H().info("received game reminder alert: " + stringExtra2);
                this.b.a(stringExtra2, stringExtra3, (String) null, stringExtra4);
            } else if ("ScoreEvent".equals(stringExtra4)) {
                this.b.H().info("received score event alert: " + stringExtra2);
                this.b.a(stringExtra2, stringExtra3, (String) null, stringExtra4);
            } else if ("ScoreUpdate".equals(stringExtra4)) {
                this.b.H().info("received score update alert: " + stringExtra2);
                this.b.a(stringExtra2, stringExtra3, (String) null, stringExtra4);
            } else if ("FSPlayer".equals(stringExtra4) || "Player".equals(stringExtra4)) {
                this.b.H().info("received player alert: " + stringExtra2);
                int f = new bpq(new JSONObject(stringExtra5)).f("id");
                try {
                    i = this.a.hasExtra("id") ? Integer.parseInt(this.a.getStringExtra("id")) : -1;
                } catch (Throwable th) {
                    this.b.H().log(Level.SEVERE, "problem: ", th);
                    i = -1;
                }
                StringBuilder sb = new StringBuilder("got alert for player: ");
                sb.append(f);
                if (i != -1) {
                    sb.append(" with gameID: ");
                    sb.append(i);
                }
                this.b.H().warning(sb.toString());
                Intent intent = new Intent(this.b, (Class<?>) PlayerActivity.class);
                intent.setAction(f + "");
                intent.setFlags(402653184);
                intent.putExtra("playerId", f);
                intent.putExtra("params", stringExtra5);
                this.b.a(stringExtra2, intent, stringExtra4);
            } else if (stringExtra2 != null) {
                this.b.H().warning("handling unknown typed alert, all extras: " + bzy.logAllStringExtras(this.a));
                this.b.a(stringExtra2, stringExtra3, (String) null, "");
            } else {
                this.b.H().warning("cannot handle typed alert, all extras: " + bzy.logAllStringExtras(this.a));
            }
        } catch (Throwable th2) {
            this.b.H().log(Level.SEVERE, "cannot handle typed alert: ", th2);
        } finally {
            this.b.stopForeground(true);
            wakeLock = this.b.C;
            wakeLock.release();
        }
    }
}
