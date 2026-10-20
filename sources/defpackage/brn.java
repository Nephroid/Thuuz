package defpackage;

import android.content.DialogInterface;
import com.thuuz.gtv.settings.SettingsActivity;
import com.thuuz.gtv.util.ServerChoice;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class brn implements DialogInterface.OnClickListener {
    final /* synthetic */ ServerChoice a;
    final /* synthetic */ SettingsActivity b;

    public brn(SettingsActivity settingsActivity, ServerChoice serverChoice) {
        this.b = settingsActivity;
        this.a = serverChoice;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            switch (i) {
                case 0:
                    ServerChoice.a(ServerChoice.SERVER_TYPE_PROD.e(), this.b.k());
                    break;
                case 1:
                    ServerChoice.a(ServerChoice.SERVER_TYPE_WEB1.e(), this.b.k());
                    break;
                case 2:
                    ServerChoice.a(ServerChoice.SERVER_TYPE_DEV.e(), this.b.k());
                    break;
                case 3:
                    ServerChoice.a(ServerChoice.SERVER_TYPE_STAGE.e(), this.b.k());
                    break;
                case 4:
                    ServerChoice.a(ServerChoice.SERVER_TYPE_LELAND.e(), this.b.k());
                    break;
                case 5:
                    ServerChoice.a(ServerChoice.SERVER_TYPE_JORDAN.e(), this.b.k());
                    break;
                case 6:
                    ServerChoice.a(ServerChoice.SERVER_TYPE_TYLER.e(), this.b.k());
                    break;
                case 7:
                    ServerChoice.a(ServerChoice.SERVER_TYPE_BEN.e(), this.b.k());
                    break;
                case 8:
                    ServerChoice.a(ServerChoice.SERVER_TYPE_ESHITA.e(), this.b.k());
                    break;
                case 9:
                    ServerChoice.a(ServerChoice.SERVER_TYPE_JON.e(), this.b.k());
                    break;
            }
            this.b.j().a(0L);
            if (ServerChoice.l != this.a) {
                this.b.j().bo();
            }
            this.b.c();
        } catch (Throwable th) {
            this.b.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
