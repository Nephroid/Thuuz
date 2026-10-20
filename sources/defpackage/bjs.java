package defpackage;

import android.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import com.thuuz.gtv.activity.game_page.GamePageTvFragment;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bjs implements AdapterView.OnItemClickListener {
    final /* synthetic */ ArrayList a;
    final /* synthetic */ AlertDialog b;
    final /* synthetic */ GamePageTvFragment c;

    public bjs(GamePageTvFragment gamePageTvFragment, ArrayList arrayList, AlertDialog alertDialog) {
        this.c = gamePageTvFragment;
        this.a = arrayList;
        this.b = alertDialog;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Logger logger;
        Logger logger2;
        Logger logger3;
        try {
            try {
                this.c.handleRecord((bpa) this.a.get(i), view);
            } finally {
                try {
                    this.b.dismiss();
                } catch (Throwable th) {
                    logger3 = this.c.getLogger();
                    logger3.log(Level.SEVERE, "cannot handle: ", th);
                }
            }
        } catch (Throwable th2) {
            logger = this.c.getLogger();
            logger.log(Level.SEVERE, "cannot show mult. watch-now list", th2);
            try {
                this.b.dismiss();
            } catch (Throwable th3) {
                logger2 = this.c.getLogger();
                logger2.log(Level.SEVERE, "cannot handle: ", th3);
            }
        }
    }
}
