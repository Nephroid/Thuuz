package defpackage;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import com.thuuz.gtv.settings.SettingsSoundsActivity;

/* loaded from: classes.dex */
public class bus implements View.OnClickListener {
    final /* synthetic */ SettingsSoundsActivity a;

    public bus(SettingsSoundsActivity settingsSoundsActivity) {
        this.a = settingsSoundsActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            Intent intent = new Intent("android.settings.CHANNEL_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", this.a.getPackageName());
            intent.putExtra("android.provider.extra.CHANNEL_ID", "default");
            this.a.startActivity(intent);
        }
    }
}
