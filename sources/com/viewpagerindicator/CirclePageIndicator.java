package com.viewpagerindicator;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import defpackage.ccm;
import defpackage.ccs;
import defpackage.ccu;
import defpackage.ccv;
import defpackage.ccw;
import defpackage.ccx;
import defpackage.ccy;
import defpackage.ccz;

/* loaded from: classes.dex */
public class CirclePageIndicator extends View implements ccs {
    private float a;
    private final Paint b;
    private final Paint c;
    private final Paint d;
    private ViewPager e;
    private ViewPager.OnPageChangeListener f;
    private int g;
    private int h;
    private float i;
    private int j;
    private int k;
    private boolean l;
    private boolean m;
    private int n;
    private float o;
    private int p;
    private boolean q;

    public CirclePageIndicator(Context context) {
        this(context, null);
    }

    public CirclePageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, ccu.vpiCirclePageIndicatorStyle);
    }

    public CirclePageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new Paint(1);
        this.c = new Paint(1);
        this.d = new Paint(1);
        this.o = -1.0f;
        this.p = -1;
        if (!isInEditMode()) {
            Resources resources = getResources();
            int color = resources.getColor(ccw.default_circle_indicator_page_color);
            int color2 = resources.getColor(ccw.default_circle_indicator_fill_color);
            int integer = resources.getInteger(ccy.default_circle_indicator_orientation);
            int color3 = resources.getColor(ccw.default_circle_indicator_stroke_color);
            float dimension = resources.getDimension(ccx.default_circle_indicator_stroke_width);
            float dimension2 = resources.getDimension(ccx.default_circle_indicator_radius);
            boolean z = resources.getBoolean(ccv.default_circle_indicator_centered);
            boolean z2 = resources.getBoolean(ccv.default_circle_indicator_snap);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ccz.CirclePageIndicator, i, 0);
            this.l = obtainStyledAttributes.getBoolean(ccz.CirclePageIndicator_centered, z);
            this.k = obtainStyledAttributes.getInt(ccz.CirclePageIndicator_android_orientation, integer);
            this.b.setStyle(Paint.Style.FILL);
            this.b.setColor(obtainStyledAttributes.getColor(ccz.CirclePageIndicator_pageColor, color));
            this.c.setStyle(Paint.Style.STROKE);
            this.c.setColor(obtainStyledAttributes.getColor(ccz.CirclePageIndicator_strokeColor, color3));
            this.c.setStrokeWidth(obtainStyledAttributes.getDimension(ccz.CirclePageIndicator_strokeWidth, dimension));
            this.d.setStyle(Paint.Style.FILL);
            this.d.setColor(obtainStyledAttributes.getColor(ccz.CirclePageIndicator_fillColor, color2));
            this.a = obtainStyledAttributes.getDimension(ccz.CirclePageIndicator_radius, dimension2);
            this.m = obtainStyledAttributes.getBoolean(ccz.CirclePageIndicator_snap, z2);
            Drawable drawable = obtainStyledAttributes.getDrawable(ccz.CirclePageIndicator_android_background);
            if (drawable != null) {
                setBackgroundDrawable(drawable);
            }
            obtainStyledAttributes.recycle();
            this.n = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
        }
    }

    public void setCentered(boolean z) {
        this.l = z;
        invalidate();
    }

    public void setPageColor(int i) {
        this.b.setColor(i);
        invalidate();
    }

    public int getPageColor() {
        return this.b.getColor();
    }

    public void setFillColor(int i) {
        this.d.setColor(i);
        invalidate();
    }

    public int getFillColor() {
        return this.d.getColor();
    }

    public void setOrientation(int i) {
        switch (i) {
            case 0:
            case 1:
                this.k = i;
                requestLayout();
                return;
            default:
                throw new IllegalArgumentException("Orientation must be either HORIZONTAL or VERTICAL.");
        }
    }

    public int getOrientation() {
        return this.k;
    }

    public void setStrokeColor(int i) {
        this.c.setColor(i);
        invalidate();
    }

    public int getStrokeColor() {
        return this.c.getColor();
    }

    public void setStrokeWidth(float f) {
        this.c.setStrokeWidth(f);
        invalidate();
    }

    public float getStrokeWidth() {
        return this.c.getStrokeWidth();
    }

    public void setRadius(float f) {
        this.a = f;
        invalidate();
    }

    public float getRadius() {
        return this.a;
    }

    public void setSnap(boolean z) {
        this.m = z;
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int count;
        int height;
        int paddingTop;
        int paddingBottom;
        int paddingLeft;
        float f;
        float f2;
        super.onDraw(canvas);
        if (this.e != null && (count = this.e.getAdapter().getCount()) != 0) {
            if (this.g >= count) {
                setCurrentItem(count - 1);
                return;
            }
            if (this.k == 0) {
                height = getWidth();
                paddingTop = getPaddingLeft();
                paddingBottom = getPaddingRight();
                paddingLeft = getPaddingTop();
            } else {
                height = getHeight();
                paddingTop = getPaddingTop();
                paddingBottom = getPaddingBottom();
                paddingLeft = getPaddingLeft();
            }
            float f3 = this.a * 3.0f;
            float f4 = this.a + paddingLeft;
            float f5 = paddingTop + this.a;
            if (this.l) {
                f5 += (((height - paddingTop) - paddingBottom) / 2.0f) - ((count * f3) / 2.0f);
            }
            float f6 = this.a;
            if (this.c.getStrokeWidth() > 0.0f) {
                f6 -= this.c.getStrokeWidth() / 2.0f;
            }
            for (int i = 0; i < count; i++) {
                float f7 = (i * f3) + f5;
                if (this.k == 0) {
                    f2 = f7;
                    f7 = f4;
                } else {
                    f2 = f4;
                }
                if (this.b.getAlpha() > 0) {
                    canvas.drawCircle(f2, f7, f6, this.b);
                }
                if (f6 != this.a) {
                    canvas.drawCircle(f2, f7, this.a, this.c);
                }
            }
            float f8 = (this.m ? this.h : this.g) * f3;
            if (!this.m) {
                f8 += this.i * f3;
            }
            if (this.k == 0) {
                f = f5 + f8;
            } else {
                float f9 = f5 + f8;
                f = f4;
                f4 = f9;
            }
            canvas.drawCircle(f, f4, this.a, this.d);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (super.onTouchEvent(motionEvent)) {
            return true;
        }
        if (this.e == null || this.e.getAdapter().getCount() == 0) {
            return false;
        }
        int action = motionEvent.getAction() & MotionEventCompat.ACTION_MASK;
        switch (action) {
            case 0:
                this.p = MotionEventCompat.getPointerId(motionEvent, 0);
                this.o = motionEvent.getX();
                return true;
            case 1:
            case 3:
                if (!this.q) {
                    int count = this.e.getAdapter().getCount();
                    int width = getWidth();
                    float f = width / 2.0f;
                    float f2 = width / 6.0f;
                    if (this.g > 0 && motionEvent.getX() < f - f2) {
                        if (action == 3) {
                            return true;
                        }
                        this.e.setCurrentItem(this.g - 1);
                        return true;
                    }
                    if (this.g < count - 1 && motionEvent.getX() > f2 + f) {
                        if (action == 3) {
                            return true;
                        }
                        this.e.setCurrentItem(this.g + 1);
                        return true;
                    }
                }
                this.q = false;
                this.p = -1;
                if (!this.e.isFakeDragging()) {
                    return true;
                }
                this.e.endFakeDrag();
                return true;
            case 2:
                float x = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.p));
                float f3 = x - this.o;
                if (!this.q && Math.abs(f3) > this.n) {
                    this.q = true;
                }
                if (!this.q) {
                    return true;
                }
                this.o = x;
                if (!this.e.isFakeDragging() && !this.e.beginFakeDrag()) {
                    return true;
                }
                this.e.fakeDragBy(f3);
                return true;
            case 4:
            default:
                return true;
            case 5:
                int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                this.o = MotionEventCompat.getX(motionEvent, actionIndex);
                this.p = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                return true;
            case 6:
                int actionIndex2 = MotionEventCompat.getActionIndex(motionEvent);
                if (MotionEventCompat.getPointerId(motionEvent, actionIndex2) == this.p) {
                    this.p = MotionEventCompat.getPointerId(motionEvent, actionIndex2 == 0 ? 1 : 0);
                }
                this.o = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.p));
                return true;
        }
    }

    public void setViewPager(ViewPager viewPager) {
        if (this.e != viewPager) {
            if (this.e != null) {
                this.e.setOnPageChangeListener(null);
            }
            if (viewPager.getAdapter() == null) {
                throw new IllegalStateException("ViewPager does not have adapter instance.");
            }
            this.e = viewPager;
            this.e.setOnPageChangeListener(this);
            invalidate();
        }
    }

    public void setViewPager(ViewPager viewPager, int i) {
        setViewPager(viewPager);
        setCurrentItem(i);
    }

    public void setCurrentItem(int i) {
        if (this.e == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        this.e.setCurrentItem(i);
        this.g = i;
        invalidate();
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        this.j = i;
        if (this.f != null) {
            this.f.onPageScrollStateChanged(i);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        this.g = i;
        this.i = f;
        invalidate();
        if (this.f != null) {
            this.f.onPageScrolled(i, f, i2);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        if (this.m || this.j == 0) {
            this.g = i;
            this.h = i;
            invalidate();
        }
        if (this.f != null) {
            this.f.onPageSelected(i);
        }
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.f = onPageChangeListener;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.k == 0) {
            setMeasuredDimension(a(i), b(i2));
        } else {
            setMeasuredDimension(b(i), a(i2));
        }
    }

    private int a(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824 || this.e == null) {
            return size;
        }
        int count = this.e.getAdapter().getCount();
        int paddingLeft = (int) (((count - 1) * this.a) + getPaddingLeft() + getPaddingRight() + (count * 2 * this.a) + 1.0f);
        if (mode == Integer.MIN_VALUE) {
            return Math.min(paddingLeft, size);
        }
        return paddingLeft;
    }

    private int b(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode != 1073741824) {
            int paddingTop = (int) ((2.0f * this.a) + getPaddingTop() + getPaddingBottom() + 1.0f);
            return mode == Integer.MIN_VALUE ? Math.min(paddingTop, size) : paddingTop;
        }
        return size;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.g = savedState.a;
        this.h = savedState.a;
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.g;
        return savedState;
    }

    /* loaded from: classes.dex */
    public class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new ccm();
        int a;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readInt();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
        }
    }
}
