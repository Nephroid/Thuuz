package defpackage;

import android.widget.BaseAdapter;
import java.util.logging.Level;

/* loaded from: classes.dex */
class bqm implements Runnable {
    final /* synthetic */ BaseAdapter a;
    final /* synthetic */ bql b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bqm(bql bqlVar, BaseAdapter baseAdapter) {
        this.b = bqlVar;
        this.a = baseAdapter;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.a.notifyDataSetChanged();
        } catch (Throwable th) {
            this.b.a.getLogger().log(Level.WARNING, "problem: ", th);
        }
    }
}
