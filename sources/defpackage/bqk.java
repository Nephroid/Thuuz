package defpackage;

import android.graphics.Bitmap;
import android.os.Handler;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bqk extends bzy {
    private Handler e;
    private Map<String, Bitmap> b = Collections.synchronizedMap(new HashMap());
    private Set<String> c = Collections.synchronizedSet(new HashSet());
    private List<BaseAdapter> d = new ArrayList();
    private ExecutorService a = new ThreadPoolExecutor(0, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());

    public bqk(Handler handler) {
        this.e = handler;
    }

    public void a(BaseAdapter baseAdapter) {
        this.d.add(baseAdapter);
    }

    public void a() {
        try {
            this.c.clear();
            this.a.shutdownNow();
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    public Bitmap a(String str, String str2) {
        Bitmap bitmap;
        if (str != null && !this.a.isShutdown()) {
            try {
                bitmap = this.b.get(str);
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "problem: ", th);
            }
            if (bitmap == null) {
                if (str2 == null) {
                    return null;
                }
                if (!this.c.contains(str)) {
                    this.a.submit(new bql(this, str, str2));
                    this.c.add(str);
                }
                return null;
            }
            return bitmap;
        }
        return null;
    }
}
