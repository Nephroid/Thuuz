package defpackage;

import com.thuuz.gtv.App;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bzz extends bzy {
    private static Logger a;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00b4 A[Catch: Throwable -> 0x00b8, TRY_LEAVE, TryCatch #9 {Throwable -> 0x00b8, blocks: (B:49:0x00af, B:43:0x00b4), top: B:48:0x00af }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00af A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12, types: [java.io.BufferedInputStream] */
    /* JADX WARN: Type inference failed for: r2v13, types: [java.util.logging.Logger] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.BufferedInputStream] */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.util.logging.Logger] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10, types: [java.util.logging.Level] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.util.logging.Level] */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v9, types: [java.io.InputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Bitmap a(java.lang.String r9) {
        /*
            r1 = 0
            java.net.URL r0 = new java.net.URL     // Catch: java.lang.Throwable -> L6d java.lang.Throwable -> Lab
            r0.<init>(r9)     // Catch: java.lang.Throwable -> L6d java.lang.Throwable -> Lab
            java.net.URLConnection r0 = r0.openConnection()     // Catch: java.lang.Throwable -> L6d java.lang.Throwable -> Lab
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch: java.lang.Throwable -> L6d java.lang.Throwable -> Lab
            r2 = 0
            r0.setInstanceFollowRedirects(r2)     // Catch: java.lang.Throwable -> L6d java.lang.Throwable -> Lab
            java.net.URL r2 = new java.net.URL     // Catch: java.lang.Throwable -> L6d java.lang.Throwable -> Lab
            java.lang.String r3 = "Location"
            java.lang.String r0 = r0.getHeaderField(r3)     // Catch: java.lang.Throwable -> L6d java.lang.Throwable -> Lab
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L6d java.lang.Throwable -> Lab
            java.lang.String r0 = r2.toString()     // Catch: java.lang.Throwable -> L6d java.lang.Throwable -> Lab
            java.lang.String r2 = "http://graph.facebook.com"
            boolean r2 = r0.startsWith(r2)     // Catch: java.lang.Throwable -> L6d java.lang.Throwable -> Lab
            if (r2 == 0) goto L2f
            java.lang.String r2 = "http://graph.facebook.com"
            java.lang.String r3 = "https://graph.facebook.com"
            java.lang.String r0 = r0.replace(r2, r3)     // Catch: java.lang.Throwable -> L6d java.lang.Throwable -> Lab
        L2f:
            java.net.URL r2 = new java.net.URL     // Catch: java.lang.Throwable -> L6d java.lang.Throwable -> Lab
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L6d java.lang.Throwable -> Lab
            java.net.URLConnection r0 = r2.openConnection()     // Catch: java.lang.Throwable -> L6d java.lang.Throwable -> Lab
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch: java.lang.Throwable -> L6d java.lang.Throwable -> Lab
            r0.connect()     // Catch: java.lang.Throwable -> L6d java.lang.Throwable -> Lab
            java.io.InputStream r3 = r0.getInputStream()     // Catch: java.lang.Throwable -> L6d java.lang.Throwable -> Lab
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> Lc5 java.lang.Throwable -> Lca
            r2.<init>(r3)     // Catch: java.lang.Throwable -> Lc5 java.lang.Throwable -> Lca
            caa r0 = new caa     // Catch: java.lang.Throwable -> Lc7 java.lang.Throwable -> Ld0
            r0.<init>(r3)     // Catch: java.lang.Throwable -> Lc7 java.lang.Throwable -> Ld0
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeStream(r0)     // Catch: java.lang.Throwable -> Lc7 java.lang.Throwable -> Ld0
            r2.close()     // Catch: java.lang.Throwable -> Lc7 java.lang.Throwable -> Ld5
            r3.close()     // Catch: java.lang.Throwable -> Lc7 java.lang.Throwable -> Ld5
            if (r2 == 0) goto L5a
            r2.close()     // Catch: java.lang.Throwable -> L60
        L5a:
            if (r3 == 0) goto L5f
            r3.close()     // Catch: java.lang.Throwable -> L60
        L5f:
            return r0
        L60:
            r1 = move-exception
            java.util.logging.Logger r2 = a()
            java.util.logging.Level r3 = java.util.logging.Level.WARNING
            java.lang.String r4 = "cannot close streams of image from server : "
            r2.log(r3, r4, r1)
            goto L5f
        L6d:
            r0 = move-exception
            r2 = r1
            r3 = r1
            r8 = r0
            r0 = r1
            r1 = r8
        L73:
            java.util.logging.Logger r4 = a()     // Catch: java.lang.Throwable -> Lc7
            java.util.logging.Level r5 = java.util.logging.Level.WARNING     // Catch: java.lang.Throwable -> Lc7
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lc7
            r6.<init>()     // Catch: java.lang.Throwable -> Lc7
            java.lang.String r7 = "Error downloading image: "
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch: java.lang.Throwable -> Lc7
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Lc7
            java.lang.StringBuilder r1 = r6.append(r1)     // Catch: java.lang.Throwable -> Lc7
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Lc7
            r4.log(r5, r1)     // Catch: java.lang.Throwable -> Lc7
            if (r2 == 0) goto L98
            r2.close()     // Catch: java.lang.Throwable -> L9e
        L98:
            if (r3 == 0) goto L5f
            r3.close()     // Catch: java.lang.Throwable -> L9e
            goto L5f
        L9e:
            r1 = move-exception
            java.util.logging.Logger r2 = a()
            java.util.logging.Level r3 = java.util.logging.Level.WARNING
            java.lang.String r4 = "cannot close streams of image from server : "
            r2.log(r3, r4, r1)
            goto L5f
        Lab:
            r0 = move-exception
            r3 = r1
        Lad:
            if (r1 == 0) goto Lb2
            r1.close()     // Catch: java.lang.Throwable -> Lb8
        Lb2:
            if (r3 == 0) goto Lb7
            r3.close()     // Catch: java.lang.Throwable -> Lb8
        Lb7:
            throw r0
        Lb8:
            r1 = move-exception
            java.util.logging.Logger r2 = a()
            java.util.logging.Level r3 = java.util.logging.Level.WARNING
            java.lang.String r4 = "cannot close streams of image from server : "
            r2.log(r3, r4, r1)
            goto Lb7
        Lc5:
            r0 = move-exception
            goto Lad
        Lc7:
            r0 = move-exception
            r1 = r2
            goto Lad
        Lca:
            r0 = move-exception
            r2 = r1
            r8 = r0
            r0 = r1
            r1 = r8
            goto L73
        Ld0:
            r0 = move-exception
            r8 = r0
            r0 = r1
            r1 = r8
            goto L73
        Ld5:
            r1 = move-exception
            goto L73
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bzz.a(java.lang.String):android.graphics.Bitmap");
    }

    public static Logger a() {
        if (a == null) {
            a = Logger.getLogger(bzz.class.getSimpleName() + ":thuuz");
            if (a.getLevel() != App.aV()) {
                a.setLevel(App.aV());
            }
        }
        return a;
    }
}
