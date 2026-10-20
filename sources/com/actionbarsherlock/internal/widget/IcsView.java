package com.actionbarsherlock.internal.widget;

import android.support.v4.view.ViewCompat;
import android.view.View;

/* loaded from: classes.dex */
final class IcsView {
    private IcsView() {
    }

    public static int getMeasuredStateInt(View view) {
        return (view.getMeasuredWidth() & ViewCompat.MEASURED_STATE_MASK) | ((view.getMeasuredHeight() >> 16) & (-256));
    }
}
