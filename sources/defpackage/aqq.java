package defpackage;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;

/* loaded from: classes.dex */
public class aqq implements aqt {
    private Context a;

    private aqq(Context context) {
        this.a = context;
    }

    @Override // defpackage.aqt
    public Drawable a(int i) {
        return this.a.getResources().getDrawable(R.drawable.btn_default);
    }

    @Override // defpackage.aqt
    public boolean a() {
        return true;
    }
}
