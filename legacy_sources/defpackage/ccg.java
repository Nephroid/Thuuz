package defpackage;

import android.widget.Toast;
import com.thuuz.gtv.widget.WidgetRemoteViewService;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class ccg implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ WidgetRemoteViewService b;

    public ccg(WidgetRemoteViewService widgetRemoteViewService, String str) {
        this.b = widgetRemoteViewService;
        this.a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            Toast.makeText(this.b.b(), this.a, 1).show();
        } catch (Throwable th) {
            this.b.a().log(Level.SEVERE, "Cannot handle: ", th);
            this.b.j();
        }
    }
}
