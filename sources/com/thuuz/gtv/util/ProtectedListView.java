package com.thuuz.gtv.util;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ListView;
import defpackage.bzy;

/* loaded from: classes.dex */
public class ProtectedListView extends ListView {
    public ProtectedListView(Context context) {
        super(context);
    }

    public ProtectedListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ProtectedListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        try {
            super.dispatchDraw(canvas);
        } catch (IndexOutOfBoundsException e) {
            bzy.getStaticLogger(ProtectedListView.class).warning("ignoring exception, assumed samsung list bug: " + e);
        }
    }
}
