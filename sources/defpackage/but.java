package defpackage;

import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.view.View;
import com.thuuz.gtv.settings.SettingsSoundsActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class but implements View.OnClickListener {
    final /* synthetic */ SettingsSoundsActivity a;

    private but(SettingsSoundsActivity settingsSoundsActivity) {
        this.a = settingsSoundsActivity;
    }

    public /* synthetic */ but(SettingsSoundsActivity settingsSoundsActivity, bus busVar) {
        this(settingsSoundsActivity);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            Intent intent = new Intent("android.intent.action.RINGTONE_PICKER");
            intent.putExtra("android.intent.extra.ringtone.TYPE", 2);
            Uri defaultUri = RingtoneManager.getDefaultUri(2);
            intent.putExtra("android.intent.extra.ringtone.SHOW_DEFAULT", true);
            intent.putExtra("android.intent.extra.ringtone.DEFAULT_URI", defaultUri);
            intent.putExtra("android.intent.extra.ringtone.TITLE", "Notification Sounds");
            String G = this.a.j().G();
            if (G == null) {
                intent.putExtra("android.intent.extra.ringtone.EXISTING_URI", defaultUri);
            } else if (G.equals("")) {
                intent.putExtra("android.intent.extra.ringtone.EXISTING_URI", (Uri) null);
            } else {
                intent.putExtra("android.intent.extra.ringtone.EXISTING_URI", Uri.parse(G));
            }
            this.a.k().startActivityForResult(intent, 352);
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem setting sound choice: ", th);
        }
    }
}
