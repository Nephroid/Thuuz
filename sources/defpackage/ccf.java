package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.thuuz.gtv.widget.ScreenOnService;
import com.thuuz.gtv.widget.WidgetProvider;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class ccf extends BroadcastReceiver {
    final /* synthetic */ ScreenOnService a;

    private ccf(ScreenOnService screenOnService) {
        this.a = screenOnService;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            this.a.stopForeground(true);
            Intent intent2 = new Intent(context, (Class<?>) WidgetProvider.class);
            intent2.setAction("com.thuuz.widget.Refresh");
            intent2.putExtra("from_screen_on", 1);
            context.sendBroadcast(intent2);
            ScreenOnService.a(this.a.getCacheDir());
            if (this.a.a().isLoggable(Level.INFO)) {
                this.a.a().info("screen on service calling stop self");
            }
            this.a.stopSelf();
        } catch (Throwable th) {
            this.a.a().log(Level.SEVERE, "widget cannot handle screen_on receive: ", th);
            this.a.b();
        }
    }
}
