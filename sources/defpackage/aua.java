package defpackage;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import java.util.HashSet;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class aua extends SQLiteOpenHelper {
    final /* synthetic */ atx a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public aua(atx atxVar, Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        this.a = atxVar;
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM datalayer WHERE 0", null);
        HashSet hashSet = new HashSet();
        try {
            for (String str : rawQuery.getColumnNames()) {
                hashSet.add(str);
            }
            rawQuery.close();
            if (!hashSet.remove("key") || !hashSet.remove("value") || !hashSet.remove("ID") || !hashSet.remove("expires")) {
                throw new SQLiteException("Database column missing");
            }
            if (!hashSet.isEmpty()) {
                throw new SQLiteException("Database has extra columns");
            }
        } catch (Throwable th) {
            rawQuery.close();
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean a(java.lang.String r11, android.database.sqlite.SQLiteDatabase r12) {
        /*
            r10 = this;
            r8 = 0
            r9 = 0
            java.lang.String r1 = "SQLITE_MASTER"
            r0 = 1
            java.lang.String[] r2 = new java.lang.String[r0]     // Catch: android.database.sqlite.SQLiteException -> L26 java.lang.Throwable -> L45
            r0 = 0
            java.lang.String r3 = "name"
            r2[r0] = r3     // Catch: android.database.sqlite.SQLiteException -> L26 java.lang.Throwable -> L45
            java.lang.String r3 = "name=?"
            r0 = 1
            java.lang.String[] r4 = new java.lang.String[r0]     // Catch: android.database.sqlite.SQLiteException -> L26 java.lang.Throwable -> L45
            r0 = 0
            r4[r0] = r11     // Catch: android.database.sqlite.SQLiteException -> L26 java.lang.Throwable -> L45
            r5 = 0
            r6 = 0
            r7 = 0
            r0 = r12
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch: android.database.sqlite.SQLiteException -> L26 java.lang.Throwable -> L45
            boolean r0 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L4c android.database.sqlite.SQLiteException -> L53
            if (r1 == 0) goto L25
            r1.close()
        L25:
            return r0
        L26:
            r0 = move-exception
            r0 = r9
        L28:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4f
            r1.<init>()     // Catch: java.lang.Throwable -> L4f
            java.lang.String r2 = "Error querying for table "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L4f
            java.lang.StringBuilder r1 = r1.append(r11)     // Catch: java.lang.Throwable -> L4f
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L4f
            defpackage.asj.b(r1)     // Catch: java.lang.Throwable -> L4f
            if (r0 == 0) goto L43
            r0.close()
        L43:
            r0 = r8
            goto L25
        L45:
            r0 = move-exception
        L46:
            if (r9 == 0) goto L4b
            r9.close()
        L4b:
            throw r0
        L4c:
            r0 = move-exception
            r9 = r1
            goto L46
        L4f:
            r1 = move-exception
            r9 = r0
            r0 = r1
            goto L46
        L53:
            r0 = move-exception
            r0 = r1
            goto L28
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.aua.a(java.lang.String, android.database.sqlite.SQLiteDatabase):boolean");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public SQLiteDatabase getWritableDatabase() {
        Context context;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = super.getWritableDatabase();
        } catch (SQLiteException e) {
            context = this.a.c;
            context.getDatabasePath("google_tagmanager.db").delete();
        }
        return sQLiteDatabase == null ? super.getWritableDatabase() : sQLiteDatabase;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        ase.a(sQLiteDatabase.getPath());
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        String str;
        if (Build.VERSION.SDK_INT < 15) {
            Cursor rawQuery = sQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", null);
            try {
                rawQuery.moveToFirst();
            } finally {
                rawQuery.close();
            }
        }
        if (a("datalayer", sQLiteDatabase)) {
            a(sQLiteDatabase);
        } else {
            str = atx.a;
            sQLiteDatabase.execSQL(str);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
