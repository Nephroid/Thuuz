package defpackage;

import android.graphics.Bitmap;
import com.thuuz.gtv.activity.AbstractActivity;
import java.lang.ref.WeakReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bmv implements bf {
    final /* synthetic */ bms a;

    private bmv(bms bmsVar) {
        this.a = bmsVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ bmv(bms bmsVar, bmt bmtVar) {
        this(bmsVar);
    }

    @Override // defpackage.bf
    public void a(String str, Bitmap bitmap) {
        cbb cbbVar;
        cbbVar = this.a.f;
        cbbVar.a(str, bitmap);
    }

    @Override // defpackage.bf
    public Bitmap a(String str) {
        cbb cbbVar;
        cbb cbbVar2;
        WeakReference weakReference;
        String str2;
        if (str != null) {
            cbbVar = this.a.f;
            Bitmap bitmap = (Bitmap) cbbVar.c(str);
            if (bitmap != null) {
                weakReference = this.a.b;
                AbstractActivity abstractActivity = (AbstractActivity) weakReference.get();
                if (abstractActivity != null) {
                    if (!str.startsWith("#") || str.length() <= 6) {
                        str2 = str;
                    } else {
                        String[] split = bms.a.split(str);
                        if (split.length != 2) {
                            this.a.k().severe("unexpected url prefix: " + str);
                            str2 = str;
                        } else {
                            str2 = split[1];
                        }
                    }
                    bmw bmwVar = new bmw(this.a, str);
                    abstractActivity.j().bW().a(new bi(str2, bmwVar, bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig(), bmwVar));
                    return bitmap;
                }
                return bitmap;
            }
            cbbVar2 = this.a.f;
            return (Bitmap) cbbVar2.a(str);
        }
        return null;
    }
}
