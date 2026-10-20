package defpackage;

import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public abstract class br implements Runnable {
    private static AtomicInteger d = new AtomicInteger(0);
    protected WeakReference<Context> a;
    private String b;
    private ScheduledExecutorService c;

    public abstract String a();

    protected abstract void a(String str, int i);

    protected abstract void a(Map<String, String> map);

    public br(Context context, String str, ScheduledExecutorService scheduledExecutorService) {
        this.a = null;
        this.a = new WeakReference<>(context);
        this.b = str;
        this.c = scheduledExecutorService;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x022e  */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r3v11, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v18 */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            Method dump skipped, instructions count: 576
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.br.run():void");
    }
}
