package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.google.android.gms.internal.hb;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.impl.client.DefaultHttpClient;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class eo implements fc {
    private static final String a = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER);", "hits2", "hit_id", "hit_time", "hit_url", "hit_string", "hit_app_id");
    private final ep b;
    private volatile fq c;
    private final fd d;
    private final Context e;
    private final String f;
    private en g;
    private long h;
    private final int i;
    private afx j;

    /* JADX INFO: Access modifiers changed from: package-private */
    public eo(fd fdVar, Context context) {
        this(fdVar, context, "google_analytics_v4.db", 2000);
    }

    eo(fd fdVar, Context context, String str, int i) {
        this.e = context.getApplicationContext();
        this.f = str;
        this.d = fdVar;
        this.j = afy.c();
        this.b = new ep(this, this.e, this.f);
        this.c = new eu(new DefaultHttpClient(), this.e);
        this.h = 0L;
        this.i = i;
    }

    private SQLiteDatabase a(String str) {
        try {
            return this.b.getWritableDatabase();
        } catch (SQLiteException e) {
            gn.d(str);
            return null;
        }
    }

    static String a(Map<String, String> map) {
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            arrayList.add(gl.a(entry.getKey()) + "=" + gl.a(entry.getValue()));
        }
        return TextUtils.join("&", arrayList);
    }

    private void a(Map<String, String> map, long j, String str) {
        long j2;
        SQLiteDatabase a2 = a("Error opening database for putHit");
        if (a2 == null) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("hit_string", a(map));
        contentValues.put("hit_time", Long.valueOf(j));
        if (map.containsKey("AppUID")) {
            try {
                j2 = Long.parseLong(map.get("AppUID"));
            } catch (NumberFormatException e) {
                j2 = 0;
            }
        } else {
            j2 = 0;
        }
        contentValues.put("hit_app_id", Long.valueOf(j2));
        if (str == null) {
            str = "http://www.google-analytics.com/collect";
        }
        if (str.length() == 0) {
            gn.d("Empty path: not sending hit");
            return;
        }
        contentValues.put("hit_url", str);
        try {
            a2.insert("hits2", null, contentValues);
            this.d.a(false);
        } catch (SQLiteException e2) {
            gn.d("Error storing hit");
        }
    }

    private void a(Map<String, String> map, Collection<hb> collection) {
        String substring = "&_v".substring(1);
        if (collection != null) {
            for (hb hbVar : collection) {
                if ("appendVersion".equals(hbVar.a())) {
                    map.put(substring, hbVar.b());
                    return;
                }
            }
        }
    }

    private void f() {
        int b = (b() - this.i) + 1;
        if (b > 0) {
            List<String> a2 = a(b);
            gn.c("Store full, deleting " + a2.size() + " hits to make room.");
            a((String[]) a2.toArray(new String[0]));
        }
    }

    int a() {
        long a2 = this.j.a();
        if (a2 <= this.h + 86400000) {
            return 0;
        }
        this.h = a2;
        SQLiteDatabase a3 = a("Error opening database for deleteStaleHits.");
        if (a3 == null) {
            return 0;
        }
        int delete = a3.delete("hits2", "HIT_TIME < ?", new String[]{Long.toString(this.j.a() - 2592000000L)});
        this.d.a(b() == 0);
        return delete;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0041, code lost:
    
        if (r1.moveToFirst() != false) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0043, code lost:
    
        r9.add(java.lang.String.valueOf(r1.getLong(0)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0053, code lost:
    
        if (r1.moveToNext() != false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0055, code lost:
    
        if (r1 == null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0057, code lost:
    
        r1.close();
     */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0082  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    java.util.List<java.lang.String> a(int r14) {
        /*
            r13 = this;
            r10 = 0
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            if (r14 > 0) goto Lf
            java.lang.String r0 = "Invalid maxHits specified. Skipping"
            defpackage.gn.d(r0)
            r0 = r9
        Le:
            return r0
        Lf:
            java.lang.String r0 = "Error opening database for peekHitIds."
            android.database.sqlite.SQLiteDatabase r0 = r13.a(r0)
            if (r0 != 0) goto L19
            r0 = r9
            goto Le
        L19:
            java.lang.String r1 = "hits2"
            r2 = 1
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch: android.database.sqlite.SQLiteException -> L5c java.lang.Throwable -> L7e
            r3 = 0
            java.lang.String r4 = "hit_id"
            r2[r3] = r4     // Catch: android.database.sqlite.SQLiteException -> L5c java.lang.Throwable -> L7e
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            java.lang.String r7 = "%s ASC"
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch: android.database.sqlite.SQLiteException -> L5c java.lang.Throwable -> L7e
            r11 = 0
            java.lang.String r12 = "hit_id"
            r8[r11] = r12     // Catch: android.database.sqlite.SQLiteException -> L5c java.lang.Throwable -> L7e
            java.lang.String r7 = java.lang.String.format(r7, r8)     // Catch: android.database.sqlite.SQLiteException -> L5c java.lang.Throwable -> L7e
            java.lang.String r8 = java.lang.Integer.toString(r14)     // Catch: android.database.sqlite.SQLiteException -> L5c java.lang.Throwable -> L7e
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch: android.database.sqlite.SQLiteException -> L5c java.lang.Throwable -> L7e
            boolean r0 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L86 android.database.sqlite.SQLiteException -> L88
            if (r0 == 0) goto L55
        L43:
            r0 = 0
            long r2 = r1.getLong(r0)     // Catch: java.lang.Throwable -> L86 android.database.sqlite.SQLiteException -> L88
            java.lang.String r0 = java.lang.String.valueOf(r2)     // Catch: java.lang.Throwable -> L86 android.database.sqlite.SQLiteException -> L88
            r9.add(r0)     // Catch: java.lang.Throwable -> L86 android.database.sqlite.SQLiteException -> L88
            boolean r0 = r1.moveToNext()     // Catch: java.lang.Throwable -> L86 android.database.sqlite.SQLiteException -> L88
            if (r0 != 0) goto L43
        L55:
            if (r1 == 0) goto L5a
            r1.close()
        L5a:
            r0 = r9
            goto Le
        L5c:
            r0 = move-exception
            r1 = r10
        L5e:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L86
            r2.<init>()     // Catch: java.lang.Throwable -> L86
            java.lang.String r3 = "Error in peekHits fetching hitIds: "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> L86
            java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Throwable -> L86
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch: java.lang.Throwable -> L86
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L86
            defpackage.gn.d(r0)     // Catch: java.lang.Throwable -> L86
            if (r1 == 0) goto L5a
            r1.close()
            goto L5a
        L7e:
            r0 = move-exception
            r1 = r10
        L80:
            if (r1 == 0) goto L85
            r1.close()
        L85:
            throw r0
        L86:
            r0 = move-exception
            goto L80
        L88:
            r0 = move-exception
            goto L5e
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eo.a(int):java.util.List");
    }

    @Override // defpackage.fc
    public void a(long j) {
        SQLiteDatabase a2 = a("Error opening database for clearHits");
        if (a2 != null) {
            if (j == 0) {
                a2.delete("hits2", null, null);
            } else {
                a2.delete("hits2", "hit_app_id = ?", new String[]{Long.valueOf(j).toString()});
            }
            this.d.a(b() == 0);
        }
    }

    @Deprecated
    void a(Collection<gk> collection) {
        if (collection == null || collection.isEmpty()) {
            gn.d("Empty/Null collection passed to deleteHits.");
            return;
        }
        String[] strArr = new String[collection.size()];
        Iterator<gk> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            strArr[i] = String.valueOf(it.next().b());
            i++;
        }
        a(strArr);
    }

    @Override // defpackage.fc
    public void a(Map<String, String> map, long j, String str, Collection<hb> collection) {
        a();
        f();
        a(map, collection);
        a(map, j, str);
    }

    void a(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            gn.d("Empty hitIds passed to deleteHits.");
            return;
        }
        SQLiteDatabase a2 = a("Error opening database for deleteHits.");
        if (a2 != null) {
            try {
                a2.delete("hits2", String.format("HIT_ID in (%s)", TextUtils.join(",", Collections.nCopies(strArr.length, "?"))), strArr);
                this.d.a(b() == 0);
            } catch (SQLiteException e) {
                gn.d("Error deleting hits " + TextUtils.join(",", strArr));
            }
        }
    }

    int b() {
        Cursor cursor = null;
        SQLiteDatabase a2 = a("Error opening database for getNumStoredHits.");
        try {
            if (a2 != null) {
                try {
                    cursor = a2.rawQuery("SELECT COUNT(*) from hits2", null);
                    r0 = cursor.moveToFirst() ? (int) cursor.getLong(0) : 0;
                    if (cursor != null) {
                        cursor.close();
                    }
                } catch (SQLiteException e) {
                    gn.d("Error getting numStoredHits");
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
            return r0;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0044, code lost:
    
        if (r13.moveToFirst() != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0046, code lost:
    
        r12.add(new defpackage.gk(null, r13.getLong(0), r13.getLong(1)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x005d, code lost:
    
        if (r13.moveToNext() != false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x005f, code lost:
    
        if (r13 == null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0061, code lost:
    
        r13.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0065, code lost:
    
        r3 = r2.query("hits2", new java.lang.String[]{"hit_id", "hit_string", "hit_url"}, null, null, null, null, java.lang.String.format("%s ASC", "hit_id"), java.lang.Integer.toString(r17));
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0097, code lost:
    
        if (r3.moveToFirst() == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0099, code lost:
    
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00a6, code lost:
    
        if (((android.database.sqlite.SQLiteCursor) r3).getWindow().getNumRows() <= 0) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a8, code lost:
    
        ((defpackage.gk) r12.get(r4)).a(r3.getString(1));
        ((defpackage.gk) r12.get(r4)).b(r3.getString(2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00c4, code lost:
    
        r2 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00ca, code lost:
    
        if (r3.moveToNext() != false) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0187, code lost:
    
        r4 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0100, code lost:
    
        defpackage.gn.d(java.lang.String.format("HitString for hitId %d too large.  Hit will be deleted.", java.lang.Long.valueOf(((defpackage.gk) r12.get(r4)).b())));
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00cc, code lost:
    
        if (r3 == null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00ce, code lost:
    
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:?, code lost:
    
        return r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x011e, code lost:
    
        r2 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x011f, code lost:
    
        r13 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0120, code lost:
    
        defpackage.gn.d("Error in peekHits fetching hitString: " + r2.getMessage());
        r3 = new java.util.ArrayList();
        r4 = false;
        r5 = r12.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x014a, code lost:
    
        r2 = (defpackage.gk) r5.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0158, code lost:
    
        if (android.text.TextUtils.isEmpty(r2.a()) == false) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x015a, code lost:
    
        if (r4 != false) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0164, code lost:
    
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0165, code lost:
    
        r3.add(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x015c, code lost:
    
        if (r13 != null) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x015e, code lost:
    
        r13.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:?, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0170, code lost:
    
        r2 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0171, code lost:
    
        r13 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x016a, code lost:
    
        if (r13 != null) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x016c, code lost:
    
        r13.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x016f, code lost:
    
        throw r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0173, code lost:
    
        r2 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0169, code lost:
    
        r2 = th;
     */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00fc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<defpackage.gk> b(int r17) {
        /*
            Method dump skipped, instructions count: 394
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eo.b(int):java.util.List");
    }

    @Override // defpackage.fc
    public void c() {
        gn.c("Dispatch running...");
        if (this.c.a()) {
            List<gk> b = b(40);
            if (b.isEmpty()) {
                gn.c("...nothing to dispatch");
                this.d.a(true);
                return;
            }
            if (this.g == null) {
                this.g = new en("_t=dispatch&_v=ma4.0.3", true);
            }
            int a2 = this.c.a(b, this.g, b() <= b.size());
            gn.c("sent " + a2 + " of " + b.size() + " hits");
            a(b.subList(0, Math.min(a2, b.size())));
            if (a2 != b.size() || b() <= 0) {
                this.g = null;
            } else {
                dz.a(this.e).e();
            }
        }
    }

    @Override // defpackage.fc
    public fq d() {
        return this.c;
    }
}
