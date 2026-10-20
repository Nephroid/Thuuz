package com.thuuz.gtv.activity.nobar;

import android.graphics.drawable.Drawable;
import android.view.View;
import defpackage.bbp;

/* loaded from: classes.dex */
public class AbstractOnboard extends AbstractNoActionBarActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.nobar.AbstractNoActionBarActivity, com.thuuz.gtv.activity.AbstractActivity
    public boolean G() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(View view) {
        if (view != null) {
            Drawable drawable = getResources().getDrawable(bbp.splash);
            drawable.setAlpha(50);
            view.setBackgroundDrawable(drawable);
        }
    }
}
