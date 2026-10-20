package defpackage;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.support.v4.os.EnvironmentCompat;
import android.widget.Button;
import android.widget.TextView;
import com.thuuz.gtv.activity.AbstractActivity;
import java.util.Date;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bbb extends bzy {
    boolean a;
    private AbstractActivity b;
    private Date c;
    private int d;
    private int e;
    private boolean f;
    private boolean g;

    public bbb(AbstractActivity abstractActivity) {
        try {
            this.b = abstractActivity;
            d();
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    public void a(boolean z) {
        try {
            if (!this.a) {
                b(z);
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    private void b(boolean z) {
        c();
        if (z && b()) {
            a();
        }
    }

    private void a() {
        CharSequence charSequence;
        try {
            if (!this.b.isDestroyed()) {
                this.a = true;
                Dialog dialog = new Dialog(this.b);
                Resources resources = this.b.getResources();
                CharSequence charSequence2 = EnvironmentCompat.MEDIA_UNKNOWN;
                try {
                    PackageManager packageManager = this.b.getPackageManager();
                    if (packageManager != null) {
                        charSequence2 = packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.b.getPackageName(), 0));
                    }
                    charSequence = charSequence2;
                } catch (Throwable th) {
                    charSequence = EnvironmentCompat.MEDIA_UNKNOWN;
                }
                dialog.setContentView(bbr.appirater);
                dialog.setTitle(String.format(resources.getString(bbt.APPIREFERRAL_MESSAGE_TITLE), charSequence));
                dialog.setOnDismissListener(new bbc(this));
                ((TextView) dialog.findViewById(bbq.appirater_message_area)).setText(String.format(resources.getString(bbt.APPIREFERRAL_MESSAGE), charSequence));
                Button button = (Button) dialog.findViewById(bbq.appirater_rate_button);
                button.setText(String.format(resources.getString(bbt.APPIREFERRAL_MESSAGE_TITLE), charSequence));
                ((Button) dialog.findViewById(bbq.appirater_rate_later_button)).setVisibility(8);
                Button button2 = (Button) dialog.findViewById(bbq.appirater_cancel_button);
                button.setOnClickListener(new bbd(this, dialog));
                button2.setOnClickListener(new bbe(this, dialog));
                try {
                    this.b.a(dialog);
                    if (!this.b.isDestroyed()) {
                        dialog.show();
                        this.b.j().k("/invitereminder");
                    }
                } catch (Throwable th2) {
                    getLogger().log(Level.INFO, "problem showing referral dialog, probably because another activity (like splash screen) is covering the parent right after parent resumed: " + th2);
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
        return !this.g && !this.f && new Date().getTime() - this.c.getTime() >= 691200000 && this.d >= 6;
    }

    private void c() {
        int i;
        try {
            PackageManager packageManager = this.b.getPackageManager();
            i = packageManager != null ? packageManager.getPackageInfo(this.b.getPackageName(), 0).versionCode : -1;
        } catch (Throwable th) {
            i = -1;
        }
        if (this.e == -1) {
            this.e = i;
        }
        if (!this.f && !this.g) {
            if (this.c == null) {
                this.c = new Date();
            }
            this.d++;
        }
        e();
    }

    private void d() {
        try {
            this.b.getResources();
            SharedPreferences sharedPreferences = this.b.getSharedPreferences(this.b.getPackageName(), 0);
            if (sharedPreferences.contains("APPIREFERRAL_FIRST_USE_DATE")) {
                long j = sharedPreferences.getLong("APPIREFERRAL_FIRST_USE_DATE", -1L);
                if (-1 != j) {
                    this.c = new Date(j);
                }
                this.d = sharedPreferences.getInt("APPIREFERRAL_USE_COUNT", 0);
                this.e = sharedPreferences.getInt("APPIREFERRAL_CURRENT_VERSION", 0);
                this.f = sharedPreferences.getBoolean("APPIREFERRAL_RATED_CURRENT_VERSION", false);
                this.g = sharedPreferences.getBoolean("APPIREFERRAL_DECLINED_TO_RATE", false);
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem loading app ratings: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        try {
            this.b.getResources();
            SharedPreferences.Editor edit = this.b.getSharedPreferences(this.b.getPackageName(), 0).edit();
            long j = -1;
            if (this.c != null) {
                j = this.c.getTime();
            }
            edit.putLong("APPIREFERRAL_FIRST_USE_DATE", j);
            edit.putInt("APPIREFERRAL_USE_COUNT", this.d);
            edit.putInt("APPIREFERRAL_CURRENT_VERSION", this.e);
            edit.putBoolean("APPIREFERRAL_RATED_CURRENT_VERSION", this.f);
            edit.putBoolean("APPIREFERRAL_DECLINED_TO_RATE", this.g);
            edit.commit();
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem saving app ratings: ", th);
        }
    }
}
