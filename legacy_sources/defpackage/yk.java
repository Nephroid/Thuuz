package defpackage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* loaded from: classes.dex */
public class yk extends SQLiteOpenHelper {
    final /* synthetic */ yj a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public yk(yj yjVar, Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 4);
        this.a = yjVar;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        String str;
        str = yj.a;
        sQLiteDatabase.execSQL(str);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        adn.c("Database updated from version " + i + " to version " + i2);
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS InAppPurchase");
        onCreate(sQLiteDatabase);
    }
}
