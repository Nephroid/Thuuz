package com.thuuz.gtv.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

/* loaded from: classes.dex */
public class PlayTriangleView extends View {
    Point a;
    Point b;
    Point c;
    Paint d;
    Path e;

    public PlayTriangleView(Context context) {
        super(context);
        this.a = new Point(0, 0);
        this.b = new Point(0, 0);
        this.c = new Point(0, 0);
        this.d = new Paint(1);
        this.e = new Path();
    }

    public PlayTriangleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new Point(0, 0);
        this.b = new Point(0, 0);
        this.c = new Point(0, 0);
        this.d = new Paint(1);
        this.e = new Path();
    }

    public PlayTriangleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new Point(0, 0);
        this.b = new Point(0, 0);
        this.c = new Point(0, 0);
        this.d = new Paint(1);
        this.e = new Path();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        this.a.set(0, 0);
        this.b.set(width, height / 2);
        this.c.set(0, height);
        this.d.setStrokeWidth(2.0f);
        this.d.setColor(-16711936);
        this.d.setStyle(Paint.Style.FILL_AND_STROKE);
        this.d.setAntiAlias(true);
        this.e.reset();
        this.e.setFillType(Path.FillType.EVEN_ODD);
        this.e.moveTo(this.a.x, this.a.y);
        this.e.lineTo(this.b.x, this.b.y);
        this.e.lineTo(this.c.x, this.c.y);
        this.e.lineTo(this.a.x, this.a.y);
        this.e.close();
        canvas.drawPath(this.e, this.d);
    }
}
