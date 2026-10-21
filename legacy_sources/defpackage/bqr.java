package defpackage;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
// [removed legacy tracker import]
import com.thuuz.gtv.App;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bqr extends ContentObserver {
    public static final String a;
    public static final Uri b;
    public static final String c;
    public static final String[] d;
    Handler e;
    private ContentResolver f;
    private final App g;

    static {
        if (cbd.a()) {
            a = "com.google.android.tv.provider";
            c = "default";
        } else {
            a = "com.google.tv.mediadevicesapp.MediaDevicesProvider";
            c = "is_default";
        }
        b = Uri.parse("content://" + a).buildUpon().appendPath("devices").build();
        d = new String[]{"X", AppEventsConstants.EVENT_PARAM_VALUE_NO, "L", "D", "R", "F"};
    }

    public bqr(Handler handler, ContentResolver contentResolver, App app) {
        super(handler);
        this.e = handler;
        this.f = contentResolver;
        this.g = app;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        try {
            super.onChange(z);
            b().info("mediaProvider changed");
            new Thread(new bqs(this)).start();
        } catch (Throwable th) {
            b().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:81:0x00fb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a() {
        /*
            Method dump skipped, instructions count: 307
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bqr.a():void");
    }

    private void a(String str, String str2) {
        bqt bqtVar = new bqt(this, null);
        bqtVar.a(bqtVar.a(str2, str));
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0049, code lost:
    
        if (r1.length() > 0) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(defpackage.bpy r8) {
        /*
            r7 = this;
            com.thuuz.gtv.App r0 = r7.g
            com.thuuz.gtv.data.Locations r0 = r0.ax()
            com.thuuz.gtv.App r1 = r7.g
            java.lang.String r2 = r1.bp()
            r3 = 0
            java.util.Set r1 = r0.keySet()
            java.util.Iterator r4 = r1.iterator()
        L15:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L6d
            java.lang.Object r1 = r4.next()
            java.lang.Integer r1 = (java.lang.Integer) r1
            java.lang.Object r1 = r0.get(r1)
            bpu r1 = (defpackage.bpu) r1
            java.lang.String r5 = r1.d()
            java.lang.String r6 = r8.a()
            if (r5 != r6) goto L15
            java.lang.String r5 = r1.c()
            if (r5 != r2) goto L15
            r0.a(r1)
            r1 = 1
        L3b:
            if (r1 != 0) goto L5e
            java.lang.String r3 = "from Google settings"
            java.lang.String r1 = r8.c()     // Catch: java.lang.Throwable -> L5f
            if (r1 == 0) goto L6b
            int r4 = r1.length()     // Catch: java.lang.Throwable -> L5f
            if (r4 <= 0) goto L6b
        L4b:
            java.lang.String r3 = r8.a()
            java.lang.String r4 = r8.b()
            java.lang.String r5 = r8.d()
            bpu r1 = r0.a(r1, r2, r3, r4, r5)
            r0.a(r1)
        L5e:
            return
        L5f:
            r1 = move-exception
            java.util.logging.Logger r4 = r7.b()
            java.util.logging.Level r5 = java.util.logging.Level.SEVERE
            java.lang.String r6 = "problem: "
            r4.log(r5, r6, r1)
        L6b:
            r1 = r3
            goto L4b
        L6d:
            r1 = r3
            goto L3b
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bqr.a(bpy):void");
    }

    private String a(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() < 5) {
            b().severe("cannot translate provider: " + str);
            return null;
        }
        if (str.startsWith("TMS:")) {
            str = str.substring(4);
        }
        if (str.contains(".")) {
            str = str.replace(".", "");
        }
        if (str.length() < 4) {
            b().severe("cannot translate provider: " + str);
            return null;
        }
        return str;
    }

    @Override // android.database.ContentObserver
    public boolean deliverSelfNotifications() {
        return true;
    }

    public Logger b() {
        Logger logger = Logger.getLogger(getClass().getSimpleName() + ":thuuz");
        if (logger.getLevel() != App.aV()) {
            logger.setLevel(App.aV());
        }
        return logger;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(caq caqVar, String str) {
        ArrayList<bpy> arrayList = new ArrayList();
        for (bpy bpyVar : caqVar.a()) {
            if (bpyVar.a().startsWith(str)) {
                arrayList.add(bpyVar);
            }
        }
        if (arrayList.size() == 0) {
            b().info("no matches found for " + str + " inside of set: " + Arrays.toString(caqVar.a().toArray()));
            return;
        }
        if (arrayList.size() == 1) {
            b().info("setting provider to: " + ((bpy) arrayList.get(0)).a());
            a((bpy) arrayList.get(0));
            return;
        }
        if (str.startsWith("DISH") || str.startsWith("DITV")) {
            String str2 = str + AppEventsConstants.EVENT_PARAM_VALUE_NO;
            for (bpy bpyVar2 : arrayList) {
                if (bpyVar2.a().equals(str2)) {
                    b().info("setting provider to: " + bpyVar2.a());
                    a(bpyVar2);
                    return;
                }
            }
        }
        for (bpy bpyVar3 : arrayList) {
            StringBuilder sb = new StringBuilder(str);
            for (String str3 : d) {
                sb.setLength(str.length());
                sb.append(str3);
                if (bpyVar3.a().equals(sb.toString())) {
                    b().info("setting provider to: " + bpyVar3.a());
                    a(bpyVar3);
                    return;
                }
            }
        }
    }

    protected boolean c() {
        return Looper.getMainLooper().equals(Looper.myLooper());
    }
}
