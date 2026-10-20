package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.widget.ImageView;
import defpackage.afe;

/* loaded from: classes.dex */
public final class iy extends ImageView {
    private int a;
    private afe b;
    private int c;
    private float d;

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.b != null) {
            canvas.clipPath(this.b.a(getWidth(), getHeight()));
        }
        super.onDraw(canvas);
        if (this.a != 0) {
            canvas.drawColor(this.a);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        int measuredWidth;
        int i3;
        super.onMeasure(i, i2);
        switch (this.c) {
            case 1:
                i3 = getMeasuredHeight();
                measuredWidth = (int) (i3 * this.d);
                break;
            case 2:
                measuredWidth = getMeasuredWidth();
                i3 = (int) (measuredWidth / this.d);
                break;
            default:
                return;
        }
        setMeasuredDimension(measuredWidth, i3);
    }
}
