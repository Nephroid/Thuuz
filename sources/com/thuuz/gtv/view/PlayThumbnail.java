package com.thuuz.gtv.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import com.thuuz.gtv.App;
import com.thuuz.gtv.widget.NetworkListenableImageView;
import defpackage.cbt;
import defpackage.cbu;
import defpackage.cci;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class PlayThumbnail extends NetworkListenableImageView {
    Point a;
    Point b;
    Point c;
    Point d;
    Path e;
    private Paint f;
    private String g;

    private void a() {
        setErrorListener(new cbt(this));
        setSuccessListener(new cbu(this));
    }

    public PlayThumbnail(Context context) {
        super(context);
        this.f = new Paint(1);
        this.a = new Point(0, 0);
        this.b = new Point(0, 0);
        this.c = new Point(0, 0);
        this.d = new Point(0, 0);
        this.e = new Path();
        a();
    }

    public PlayThumbnail(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = new Paint(1);
        this.a = new Point(0, 0);
        this.b = new Point(0, 0);
        this.c = new Point(0, 0);
        this.d = new Point(0, 0);
        this.e = new Path();
        a();
    }

    public PlayThumbnail(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = new Paint(1);
        this.a = new Point(0, 0);
        this.b = new Point(0, 0);
        this.c = new Point(0, 0);
        this.d = new Point(0, 0);
        this.e = new Path();
        a();
    }

    public void a(String str) {
        try {
            if (!b(str)) {
                this.g = str;
                setImageBitmap(null);
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    private boolean b(String str) {
        if (this.g == null) {
            if (str == null) {
                return true;
            }
            return false;
        }
        return this.g.equals(str);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            float a = a(measuredWidth, measuredHeight);
            this.a.set(measuredWidth / 2, measuredHeight / 2);
            this.f.setAntiAlias(true);
            this.f.setStrokeWidth(2.0f);
            this.f.setStyle(Paint.Style.FILL);
            this.f.setColor(Color.parseColor("#56000000"));
            canvas.drawCircle(measuredWidth / 2, measuredHeight / 2, a, this.f);
            this.f.setColor(-1);
            this.f.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(measuredWidth / 2, measuredHeight / 2, a, this.f);
            double d = a / 2.0d;
            double sqrt = Math.sqrt((a * a) - (d * d));
            double d2 = 0.4d * sqrt;
            this.b.set(this.a.x - ((int) Math.round(d2)), this.a.y - ((int) d));
            this.c.set(((int) Math.round(sqrt * 0.6d)) + this.a.x, this.a.y);
            this.d.set(this.a.x - ((int) Math.round(d2)), ((int) d) + this.a.y);
            this.e.reset();
            this.e.setFillType(Path.FillType.EVEN_ODD);
            this.e.moveTo(this.b.x, this.b.y);
            this.e.lineTo(this.c.x, this.c.y);
            this.e.lineTo(this.d.x, this.d.y);
            this.e.lineTo(this.b.x, this.b.y);
            this.e.close();
            this.f.setStyle(Paint.Style.FILL);
            canvas.drawPath(this.e, this.f);
        } catch (Throwable th) {
            cci.a(PlayThumbnail.class).log(Level.SEVERE, "problem: ", th);
        }
    }

    private float a(int i, int i2) {
        float f = i / 2;
        if (i > i2) {
            f = i2 / 2;
        }
        float f2 = (float) (f * 0.4d);
        int i3 = 11;
        int i4 = 23;
        App app = getApp();
        if (app != null) {
            i3 = app.q(11);
            i4 = app.q(23);
        }
        return Math.max(Math.min(f2, i4), i3);
    }

    @Override // com.thuuz.gtv.widget.NetworkListenableImageView
    public String getUrl() {
        return this.g;
    }

    public Logger getLogger() {
        Logger logger = Logger.getLogger(getClass().getSimpleName() + ":thuuz");
        if (logger.getLevel() != App.aV()) {
            logger.setLevel(App.aV());
        }
        return logger;
    }

    public App getApp() {
        Context context = getContext();
        if (context == null) {
            return null;
        }
        return (App) context.getApplicationContext();
    }
}
