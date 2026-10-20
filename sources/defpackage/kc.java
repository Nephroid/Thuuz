package defpackage;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import com.google.android.gms.dynamic.g;

/* loaded from: classes.dex */
public final class kc extends g<jv> {
    private static final kc a = new kc();

    private kc() {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }

    public static View a(Context context, int i, int i2) {
        return a.b(context, i, i2);
    }

    private View b(Context context, int i, int i2) {
        try {
            return (View) og.a(a(context).a(og.a(context), i, i2));
        } catch (Exception e) {
            throw new g.a("Could not get button with size " + i + " and color " + i2, e);
        }
    }

    @Override // com.google.android.gms.dynamic.g
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public jv b(IBinder iBinder) {
        return jw.a(iBinder);
    }
}
