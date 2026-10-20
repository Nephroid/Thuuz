package defpackage;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v4.os.EnvironmentCompat;
import android.widget.Button;
import android.widget.TextView;
import com.thuuz.gtv.activity.AbstractActivity;
import java.util.Date;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class baw extends bzy {
    boolean a;
    Boolean b;
    private AbstractActivity c;
    private Date d;
    private Date e;
    private int f;
    private int g;
    private int h;
    private boolean i;
    private boolean j;

    public baw(AbstractActivity abstractActivity) {
        try {
            this.c = abstractActivity;
            d();
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    public boolean a(boolean z) {
        try {
            if (this.a) {
                return false;
            }
            return b(z);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
            return false;
        }
    }

    private boolean b(boolean z) {
        c();
        if (this.b == null) {
            Uri parse = Uri.parse(String.format("market://details?id=%s", this.c.getPackageName()));
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(parse);
            PackageManager packageManager = this.c.getPackageManager();
            if (packageManager != null) {
                this.b = Boolean.valueOf(packageManager.queryIntentActivities(intent, 0).size() > 0);
            } else {
                this.b = false;
            }
        }
        if (!this.b.booleanValue() || !z || !b()) {
            return false;
        }
        a();
        return true;
    }

    private void a() {
        CharSequence charSequence;
        try {
            if (!this.c.isDestroyed()) {
                this.a = true;
                Dialog dialog = new Dialog(this.c);
                Resources resources = this.c.getResources();
                CharSequence charSequence2 = EnvironmentCompat.MEDIA_UNKNOWN;
                try {
                    PackageManager packageManager = this.c.getPackageManager();
                    if (packageManager != null) {
                        charSequence2 = packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.c.getPackageName(), 0));
                    }
                    charSequence = charSequence2;
                } catch (Throwable th) {
                    charSequence = EnvironmentCompat.MEDIA_UNKNOWN;
                }
                dialog.setContentView(bbr.appirater);
                dialog.setTitle(String.format(resources.getString(bbt.APPIRATER_MESSAGE_TITLE), charSequence));
                dialog.setOnDismissListener(new bax(this));
                ((TextView) dialog.findViewById(bbq.appirater_message_area)).setText(String.format(resources.getString(bbt.APPIRATER_MESSAGE), charSequence));
                Button button = (Button) dialog.findViewById(bbq.appirater_rate_button);
                button.setText(String.format(resources.getString(bbt.APPIRATER_RATE_BUTTON), charSequence));
                Button button2 = (Button) dialog.findViewById(bbq.appirater_rate_later_button);
                Button button3 = (Button) dialog.findViewById(bbq.appirater_cancel_button);
                button.setOnClickListener(new bay(this, dialog));
                button2.setOnClickListener(new baz(this, dialog));
                button3.setOnClickListener(new bba(this, dialog));
                try {
                    this.c.a(dialog);
                    if (!this.c.isDestroyed()) {
                        dialog.show();
                        this.c.j().k("/rateapp");
                    }
                } catch (Throwable th2) {
                    getLogger().log(Level.INFO, "problem showing rater dialog, probably because another activity (like splash screen) is covering the parent right after parent resumed: " + th2);
                }
            }
        } catch (Throwable th3) {
            getLogger().log(Level.SEVERE, "problem: ", th3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Dialog dialog) {
        dialog.dismiss();
        this.a = false;
    }

    private boolean b() {
        if (this.j || this.i) {
            return false;
        }
        Date date = new Date();
        if (date.getTime() - this.d.getTime() < 432000000 || this.f < 5 || this.g < -1) {
            return false;
        }
        return this.e == null || this.e.getTime() - date.getTime() >= 86400000;
    }

    private void c() {
        int i;
        try {
            PackageManager packageManager = this.c.getPackageManager();
            i = packageManager != null ? packageManager.getPackageInfo(this.c.getPackageName(), 0).versionCode : -1;
        } catch (Throwable th) {
            i = -1;
        }
        if (this.h == -1) {
            this.h = i;
        }
        if (!this.i && !this.j) {
            if (this.d == null) {
                this.d = new Date();
            }
            this.f++;
        }
        e();
    }

    private void d() {
        try {
            this.c.getResources();
            SharedPreferences sharedPreferences = this.c.getSharedPreferences(this.c.getPackageName(), 0);
            if (sharedPreferences.contains("APPIRATER_FIRST_USE_DATE")) {
                long j = sharedPreferences.getLong("APPIRATER_FIRST_USE_DATE", -1L);
                if (-1 != j) {
                    this.d = new Date(j);
                }
                long j2 = sharedPreferences.getLong("APPIRATER_REMINDER_REQUEST_DATE", -1L);
                if (-1 != j2) {
                    this.e = new Date(j2);
                }
                this.f = sharedPreferences.getInt("APPIRATER_USE_COUNT", 0);
                this.g = sharedPreferences.getInt("APPIRATER_SIG_EVENT_COUNT", 0);
                this.h = sharedPreferences.getInt("APPIRATER_CURRENT_VERSION", 0);
                this.i = sharedPreferences.getBoolean("APPIRATER_RATED_CURRENT_VERSION", false);
                this.j = sharedPreferences.getBoolean("APPIRATER_DECLINED_TO_RATE", false);
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem loading app ratings: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        try {
            this.c.getResources();
            SharedPreferences.Editor edit = this.c.getSharedPreferences(this.c.getPackageName(), 0).edit();
            edit.putLong("APPIRATER_FIRST_USE_DATE", this.d != null ? this.d.getTime() : -1L);
            edit.putLong("APPIRATER_REMINDER_REQUEST_DATE", this.e != null ? this.e.getTime() : -1L);
            edit.putInt("APPIRATER_USE_COUNT", this.f);
            edit.putInt("APPIRATER_SIG_EVENT_COUNT", this.g);
            edit.putInt("APPIRATER_CURRENT_VERSION", this.h);
            edit.putBoolean("APPIRATER_RATED_CURRENT_VERSION", this.i);
            edit.putBoolean("APPIRATER_DECLINED_TO_RATE", this.j);
            edit.commit();
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem saving app ratings: ", th);
        }
    }
}
