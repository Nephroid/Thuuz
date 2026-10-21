package defpackage;

import android.content.Context;
import com.google.android.gms.internal.gt;
import java.util.concurrent.Future;

@zy
/* loaded from: classes.dex */
public class so {
    public Future<sm> a(Context context, gt gtVar, String str) {
        ada adaVar = new ada();
        adm.a.post(new sp(this, context, gtVar, adaVar, str));
        return adaVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public sm a(Context context, gt gtVar, ada<sm> adaVar) {
        sr srVar = new sr(context, gtVar);
        srVar.a(new sq(this, adaVar, srVar));
        return srVar;
    }
}
