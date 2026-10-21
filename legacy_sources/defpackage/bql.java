package defpackage;

import android.graphics.Bitmap;
import android.os.Handler;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bql implements Runnable {
    final /* synthetic */ bqk a;
    private String b;
    private String c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bql(bqk bqkVar, String str, String str2) {
        this.a = bqkVar;
        this.b = str;
        this.c = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        Set set;
        Map map;
        List list;
        Handler handler;
        try {
            Bitmap a = bzz.a(this.c);
            if (a != null) {
                map = this.a.b;
                map.put(this.b, a);
                list = this.a.d;
                Iterator it = new ArrayList(list).iterator();
                while (it.hasNext()) {
                    BaseAdapter baseAdapter = (BaseAdapter) it.next();
                    handler = this.a.e;
                    handler.post(new bqm(this, baseAdapter));
                }
            }
            set = this.a.c;
            set.remove(this.b);
        } catch (Throwable th) {
            this.a.getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }
}
