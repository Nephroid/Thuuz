package defpackage;

import android.view.View;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
class bka implements View.OnClickListener {
    final /* synthetic */ ArrayList a;
    final /* synthetic */ bjy b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bka(bjy bjyVar, ArrayList arrayList) {
        this.b = bjyVar;
        this.a = arrayList;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Logger logger;
        try {
            if (this.a.size() > 1) {
                this.b.a.handleMultipleRecord(this.a);
            } else {
                this.b.a.handleRecord((bpa) this.a.get(0), view);
            }
        } catch (Throwable th) {
            logger = this.b.a.getLogger();
            logger.log(Level.SEVERE, "problem: ", th);
        }
    }
}
