package com.thuuz.gtv.view;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/* loaded from: classes.dex */
public class VerticalTextView extends TextView {
    public VerticalTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public VerticalTextView(Context context) {
        super(context);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i2, i);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        TextPaint paint = getPaint();
        paint.setColor(getCurrentTextColor());
        paint.drawableState = getDrawableState();
        canvas.save();
        canvas.translate(0.0f, getHeight());
        canvas.rotate(-90.0f);
        canvas.translate(getCompoundPaddingLeft(), getExtendedPaddingTop());
        Layout layout = getLayout();
        if (layout != null) {
            layout.draw(canvas);
        }
        canvas.restore();
    }
}
