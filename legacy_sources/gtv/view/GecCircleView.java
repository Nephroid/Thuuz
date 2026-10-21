package com.thuuz.gtv.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import com.thuuz.gtv.App;
import com.thuuz.roboto.RobotoTextView;
import defpackage.bbn;
import defpackage.bbv;
import defpackage.cci;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class GecCircleView extends RobotoTextView {
    public int a;
    public int b;
    private Paint c;

    public GecCircleView(Context context) {
        super(context);
        this.c = new Paint(1);
        this.a = 0;
        this.b = bbn.red;
    }

    public GecCircleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = new Paint(1);
        this.a = 0;
        this.b = bbn.red;
        a(context, attributeSet);
    }

    public GecCircleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new Paint(1);
        this.a = 0;
        this.b = bbn.red;
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes;
        if (!isInEditMode() && (obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, bbv.GecCircleView)) != null) {
            this.b = obtainStyledAttributes.getInt(bbv.GecCircleView_gec_color, bbn.red);
        }
    }

    public void setCircleType(int i) {
        this.a = i;
    }

    public void setCircleColor(int i) {
        this.b = i;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x0029. Please report as an issue. */
    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        float f;
        float strokeWidth;
        Context context;
        App app;
        try {
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            float f2 = measuredWidth / 2;
            if (measuredWidth > measuredHeight) {
                f2 = measuredHeight / 2;
            }
            if (isInEditMode()) {
                Color.parseColor("#e42f28");
            } else {
                Resources resources = getResources();
                if (resources != null) {
                    this.c.setColor(resources.getColor(this.b));
                }
            }
            this.c.setAntiAlias(true);
            this.c.setStrokeWidth(0.0f);
            switch (this.a) {
                case 0:
                    this.c.setStyle(Paint.Style.FILL);
                    strokeWidth = f2;
                    canvas.drawCircle(measuredWidth / 2, measuredHeight / 2, strokeWidth, this.c);
                    super.onDraw(canvas);
                    return;
                case 1:
                    this.c.setStyle(Paint.Style.STROKE);
                    try {
                        context = getContext();
                    } catch (Throwable th) {
                        cci.a(GecCircleView.class).log(Level.SEVERE, "problem with gec stroke width: ", th);
                    }
                    if (context != null && (app = (App) context.getApplicationContext()) != null) {
                        f = app.q(2);
                        this.c.setStrokeWidth(f);
                        strokeWidth = f2 - (this.c.getStrokeWidth() / 2.0f);
                        canvas.drawCircle(measuredWidth / 2, measuredHeight / 2, strokeWidth, this.c);
                        super.onDraw(canvas);
                        return;
                    }
                    f = 3.0f;
                    this.c.setStrokeWidth(f);
                    strokeWidth = f2 - (this.c.getStrokeWidth() / 2.0f);
                    canvas.drawCircle(measuredWidth / 2, measuredHeight / 2, strokeWidth, this.c);
                    super.onDraw(canvas);
                    return;
                default:
                    strokeWidth = f2;
                    canvas.drawCircle(measuredWidth / 2, measuredHeight / 2, strokeWidth, this.c);
                    super.onDraw(canvas);
                    return;
            }
        } catch (Throwable th2) {
            cci.a(GecCircleView.class).log(Level.SEVERE, "problem: ", th2);
        }
    }
}
