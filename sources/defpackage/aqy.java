package defpackage;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import com.google.android.gms.dynamic.g;
import com.google.android.gms.plus.PlusOneDummyView;

/* loaded from: classes.dex */
public final class aqy extends g<aqu> {
    private static final aqy a = new aqy();

    private aqy() {
        super("com.google.android.gms.plus.plusone.PlusOneButtonCreatorImpl");
    }

    public static View a(Context context, int i, int i2, String str, int i3) {
        try {
            if (str == null) {
                throw new NullPointerException();
            }
            return (View) og.a(a.a(context).a(og.a(context), i, i2, str, i3));
        } catch (Exception e) {
            return new PlusOneDummyView(context, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.dynamic.g
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public aqu b(IBinder iBinder) {
        return aqv.a(iBinder);
    }
}
