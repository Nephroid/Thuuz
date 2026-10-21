package defpackage;

import android.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import com.thuuz.gtv.activity.game_page.GamePageTvFragment;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bjr implements AdapterView.OnItemClickListener {
    final /* synthetic */ ArrayList a;
    final /* synthetic */ bjx b;
    final /* synthetic */ AlertDialog c;
    final /* synthetic */ GamePageTvFragment d;

    public bjr(GamePageTvFragment gamePageTvFragment, ArrayList arrayList, bjx bjxVar, AlertDialog alertDialog) {
        this.d = gamePageTvFragment;
        this.a = arrayList;
        this.b = bjxVar;
        this.c = alertDialog;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Logger logger;
        Logger logger2;
        Logger logger3;
        try {
            try {
                this.b.a((bpa) this.a.get(i), null);
            } catch (Throwable th) {
                logger = this.d.getLogger();
                logger.log(Level.SEVERE, "cannot show mult. watch-now list", th);
                try {
                    this.c.dismiss();
                } catch (Throwable th2) {
                    logger2 = this.d.getLogger();
                    logger2.log(Level.SEVERE, "cannot handle: ", th2);
                }
            }
        } finally {
            try {
                this.c.dismiss();
            } catch (Throwable th3) {
                logger3 = this.d.getLogger();
                logger3.log(Level.SEVERE, "cannot handle: ", th3);
            }
        }
    }
}
