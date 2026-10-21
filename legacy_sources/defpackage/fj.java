package defpackage;

import android.content.Context;
// [removed legacy tracker import]
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/* loaded from: classes.dex */
class fj implements fp {
    private static fj a;
    private static final Object b = new Object();
    private final Context c;
    private String d;
    private boolean e = false;
    private final Object f = new Object();

    protected fj(Context context) {
        this.c = context;
        f();
    }

    public static fj a() {
        fj fjVar;
        synchronized (b) {
            fjVar = a;
        }
        return fjVar;
    }

    public static void a(Context context) {
        synchronized (b) {
            if (a == null) {
                a = new fj(context);
            }
        }
    }

    private boolean b(String str) {
        try {
            gn.c("Storing clientId.");
            FileOutputStream openFileOutput = this.c.openFileOutput("gaClientId", 0);
            openFileOutput.write(str.getBytes());
            openFileOutput.close();
            return true;
        } catch (FileNotFoundException e) {
            gn.a("Error creating clientId file.");
            return false;
        } catch (IOException e2) {
            gn.a("Error writing to clientId file.");
            return false;
        }
    }

    private String e() {
        if (!this.e) {
            synchronized (this.f) {
                if (!this.e) {
                    gn.c("Waiting for clientId to load");
                    do {
                        try {
                            this.f.wait();
                        } catch (InterruptedException e) {
                            gn.a("Exception while waiting for clientId: " + e);
                        }
                    } while (!this.e);
                }
            }
        }
        gn.c("Loaded clientId");
        return this.d;
    }

    private void f() {
        new fk(this, "client_id_fetcher").start();
    }

    @Override // defpackage.fp
    public String a(String str) {
        if ("&cid".equals(str)) {
            return e();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String b() {
        String str;
        synchronized (this.f) {
            this.d = c();
            str = this.d;
        }
        return str;
    }

    protected String c() {
        String lowerCase = UUID.randomUUID().toString().toLowerCase();
        try {
            return !b(lowerCase) ? AppEventsConstants.EVENT_PARAM_VALUE_NO : lowerCase;
        } catch (Exception e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String d() {
        /*
            r6 = this;
            r0 = 0
            android.content.Context r1 = r6.c     // Catch: java.io.FileNotFoundException -> L42 java.io.IOException -> L54
            java.lang.String r2 = "gaClientId"
            java.io.FileInputStream r2 = r1.openFileInput(r2)     // Catch: java.io.FileNotFoundException -> L42 java.io.IOException -> L54
            r1 = 128(0x80, float:1.794E-43)
            byte[] r3 = new byte[r1]     // Catch: java.io.FileNotFoundException -> L42 java.io.IOException -> L54
            r1 = 0
            r4 = 128(0x80, float:1.794E-43)
            int r4 = r2.read(r3, r1, r4)     // Catch: java.io.FileNotFoundException -> L42 java.io.IOException -> L54
            int r1 = r2.available()     // Catch: java.io.FileNotFoundException -> L42 java.io.IOException -> L54
            if (r1 <= 0) goto L30
            java.lang.String r1 = "clientId file seems corrupted, deleting it."
            defpackage.gn.a(r1)     // Catch: java.io.FileNotFoundException -> L42 java.io.IOException -> L54
            r2.close()     // Catch: java.io.FileNotFoundException -> L42 java.io.IOException -> L54
            android.content.Context r1 = r6.c     // Catch: java.io.FileNotFoundException -> L42 java.io.IOException -> L54
            java.lang.String r2 = "gaClientId"
            r1.deleteFile(r2)     // Catch: java.io.FileNotFoundException -> L42 java.io.IOException -> L54
        L29:
            if (r0 != 0) goto L2f
            java.lang.String r0 = r6.c()
        L2f:
            return r0
        L30:
            if (r4 > 0) goto L44
            java.lang.String r1 = "clientId file seems empty, deleting it."
            defpackage.gn.a(r1)     // Catch: java.io.FileNotFoundException -> L42 java.io.IOException -> L54
            r2.close()     // Catch: java.io.FileNotFoundException -> L42 java.io.IOException -> L54
            android.content.Context r1 = r6.c     // Catch: java.io.FileNotFoundException -> L42 java.io.IOException -> L54
            java.lang.String r2 = "gaClientId"
            r1.deleteFile(r2)     // Catch: java.io.FileNotFoundException -> L42 java.io.IOException -> L54
            goto L29
        L42:
            r1 = move-exception
            goto L29
        L44:
            java.lang.String r1 = new java.lang.String     // Catch: java.io.FileNotFoundException -> L42 java.io.IOException -> L54
            r5 = 0
            r1.<init>(r3, r5, r4)     // Catch: java.io.FileNotFoundException -> L42 java.io.IOException -> L54
            r2.close()     // Catch: java.io.IOException -> L62 java.io.FileNotFoundException -> L65
            java.lang.String r0 = "Loaded client id from disk."
            defpackage.gn.c(r0)     // Catch: java.io.IOException -> L62 java.io.FileNotFoundException -> L65
            r0 = r1
            goto L29
        L54:
            r1 = move-exception
        L55:
            java.lang.String r1 = "Error reading clientId file, deleting it."
            defpackage.gn.a(r1)
            android.content.Context r1 = r6.c
            java.lang.String r2 = "gaClientId"
            r1.deleteFile(r2)
            goto L29
        L62:
            r0 = move-exception
            r0 = r1
            goto L55
        L65:
            r0 = move-exception
            r0 = r1
            goto L29
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.fj.d():java.lang.String");
    }
}
