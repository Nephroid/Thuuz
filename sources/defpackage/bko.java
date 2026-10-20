package defpackage;

import android.widget.ListView;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
class bko implements Runnable {
    final /* synthetic */ bkn a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bko(bkn bknVar) {
        this.a = bknVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Logger logger;
        ListView listView;
        Logger logger2;
        try {
            int calcListHeight = this.a.a.calcListHeight(this.a.a.buttons);
            listView = this.a.a.buttonListView;
            int measuredHeight = listView.getMeasuredHeight();
            if (calcListHeight != measuredHeight) {
                logger2 = this.a.a.getLogger();
                logger2.info("handler asking for re-layout, highlight list height current/target: " + measuredHeight + "/" + calcListHeight);
                this.a.a.confirmListSize();
            }
        } catch (Throwable th) {
            logger = this.a.a.getLogger();
            logger.log(Level.SEVERE, "problem: ", th);
        }
    }
}
