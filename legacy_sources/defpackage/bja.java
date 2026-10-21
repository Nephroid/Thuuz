package defpackage;

import android.app.AlertDialog;
import android.location.Location;
import android.location.LocationManager;
import android.net.http.Headers;
import android.view.View;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.game_page.AbstractTvFragment;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bja implements View.OnClickListener {
    final /* synthetic */ AbstractTvFragment a;

    public bja(AbstractTvFragment abstractTvFragment) {
        this.a = abstractTvFragment;
    }

    public void a(AbstractActivity abstractActivity) {
        Logger logger;
        Logger logger2;
        if (abstractActivity.j().e("android.permission.ACCESS_COARSE_LOCATION")) {
            try {
                Location lastKnownLocation = ((LocationManager) abstractActivity.getSystemService(Headers.LOCATION)).getLastKnownLocation("network");
                if (lastKnownLocation == null) {
                    this.a.showSportsBarDeprecated();
                } else {
                    this.a.showSportsBarLocations(lastKnownLocation);
                }
                return;
            } catch (Throwable th) {
                logger = this.a.getLogger();
                logger.log(Level.SEVERE, "problem getting location for sports bar: " + th);
                this.a.showSportsBarDeprecated();
                return;
            }
        }
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(abstractActivity.k());
            builder.setTitle("Thuuz Error").setCancelable(true).setMessage("You have declined location services for Thuuz.  To enable go to Settings | Apps | Thuuz Sports | Permissions and turn Location on.");
            AlertDialog create = builder.create();
            create.setButton(-1, "Ok", new bjb(this, create));
            abstractActivity.a(create);
            create.show();
        } catch (Throwable th2) {
            logger2 = this.a.getLogger();
            logger2.log(Level.SEVERE, "problem: ", th2);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Logger logger;
        AbstractActivity fragsActivity;
        try {
            fragsActivity = this.a.getFragsActivity();
            if (fragsActivity != null) {
                bqy.a(new bjc(this));
                if (!fragsActivity.b("android.permission.ACCESS_COARSE_LOCATION", true)) {
                    a(fragsActivity);
                }
            }
        } catch (Throwable th) {
            logger = this.a.getLogger();
            logger.log(Level.SEVERE, "problem: ", th);
        }
    }
}
