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
import defpackage.ccr;
import defpackage.ccs;
import defpackage.ccu;
import defpackage.ccv;
import defpackage.ccw;
import defpackage.ccx;
import defpackage.ccz;

/* loaded from: classes.dex */
public class LinePageIndicator extends View implements ccs {
    private final Paint a;
    private final Paint b;
    private ViewPager c;
    private ViewPager.OnPageChangeListener d;
    private int e;
    private boolean f;
    private float g;
    private float h;
    private int i;
    private float j;
    private int k;
    private boolean l;

    public LinePageIndicator(Context context) {
        this(context, null);
    }

    public LinePageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, ccu.vpiLinePageIndicatorStyle);
    }

    public LinePageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new Paint(1);
        this.b = new Paint(1);
        this.j = -1.0f;
        this.k = -1;
        if (!isInEditMode()) {
            Resources resources = getResources();
            int color = resources.getColor(ccw.default_line_indicator_selected_color);
            int color2 = resources.getColor(ccw.default_line_indicator_unselected_color);
            float dimension = resources.getDimension(ccx.default_line_indicator_line_width);
            float dimension2 = resources.getDimension(ccx.default_line_indicator_gap_width);
            float dimension3 = resources.getDimension(ccx.default_line_indicator_stroke_width);
            boolean z = resources.getBoolean(ccv.default_line_indicator_centered);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ccz.LinePageIndicator, i, 0);
            this.f = obtainStyledAttributes.getBoolean(ccz.LinePageIndicator_centered, z);
            this.g = obtainStyledAttributes.getDimension(ccz.LinePageIndicator_lineWidth, dimension);
            this.h = obtainStyledAttributes.getDimension(ccz.LinePageIndicator_gapWidth, dimension2);
            setStrokeWidth(obtainStyledAttributes.getDimension(ccz.LinePageIndicator_strokeWidth, dimension3));
            this.a.setColor(obtainStyledAttributes.getColor(ccz.LinePageIndicator_unselectedColor, color2));
            this.b.setColor(obtainStyledAttributes.getColor(ccz.LinePageIndicator_selectedColor, color));
            Drawable drawable = obtainStyledAttributes.getDrawable(ccz.LinePageIndicator_android_background);
            if (drawable != null) {
                setBackgroundDrawable(drawable);
            }
            obtainStyledAttributes.recycle();
            this.i = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
        }
    }

    public void setCentered(boolean z) {
        this.f = z;
        invalidate();
    }

    public void setUnselectedColor(int i) {
        this.a.setColor(i);
        invalidate();
    }

    public int getUnselectedColor() {
        return this.a.getColor();
    }

    public void setSelectedColor(int i) {
        this.b.setColor(i);
        invalidate();
    }

    public int getSelectedColor() {
        return this.b.getColor();
    }

    public void setLineWidth(float f) {
        this.g = f;
        invalidate();
    }

    public float getLineWidth() {
        return this.g;
    }

    public void setStrokeWidth(float f) {
        this.b.setStrokeWidth(f);
        this.a.setStrokeWidth(f);
        invalidate();
    }

    public float getStrokeWidth() {
        return this.b.getStrokeWidth();
    }

    public void setGapWidth(float f) {
        this.h = f;
        invalidate();
    }

    public float getGapWidth() {
        return this.h;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int count;
        super.onDraw(canvas);
        if (this.c != null && (count = this.c.getAdapter().getCount()) != 0) {
            if (this.e >= count) {
                setCurrentItem(count - 1);
                return;
            }
            float f = this.g + this.h;
            float f2 = (count * f) - this.h;
            float paddingTop = getPaddingTop();
            float paddingLeft = getPaddingLeft();
            float height = paddingTop + (((getHeight() - paddingTop) - getPaddingBottom()) / 2.0f);
            float width = this.f ? paddingLeft + ((((getWidth() - paddingLeft) - getPaddingRight()) / 2.0f) - (f2 / 2.0f)) : paddingLeft;
            int i = 0;
            while (i < count) {
                float f3 = width + (i * f);
                canvas.drawLine(f3, height, f3 + this.g, height, i == this.e ? this.b : this.a);
                i++;
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (super.onTouchEvent(motionEvent)) {
            return true;
        }
        if (this.c == null || this.c.getAdapter().getCount() == 0) {
            return false;
        }
        int action = motionEvent.getAction() & MotionEventCompat.ACTION_MASK;
        switch (action) {
            case 0:
                this.k = MotionEventCompat.getPointerId(motionEvent, 0);
                this.j = motionEvent.getX();
                return true;
            case 1:
            case 3:
                if (!this.l) {
                    int count = this.c.getAdapter().getCount();
                    int width = getWidth();
                    float f = width / 2.0f;
                    float f2 = width / 6.0f;
                    if (this.e > 0 && motionEvent.getX() < f - f2) {
                        if (action == 3) {
                            return true;
                        }
                        this.c.setCurrentItem(this.e - 1);
                        return true;
                    }
                    if (this.e < count - 1 && motionEvent.getX() > f2 + f) {
                        if (action == 3) {
                            return true;
                        }
                        this.c.setCurrentItem(this.e + 1);
                        return true;
                    }
                }
                this.l = false;
                this.k = -1;
                if (!this.c.isFakeDragging()) {
                    return true;
                }
                this.c.endFakeDrag();
                return true;
            case 2:
                float x = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.k));
                float f3 = x - this.j;
                if (!this.l && Math.abs(f3) > this.i) {
                    this.l = true;
                }
                if (!this.l) {
                    return true;
                }
                this.j = x;
                if (!this.c.isFakeDragging() && !this.c.beginFakeDrag()) {
                    return true;
                }
                this.c.fakeDragBy(f3);
                return true;
            case 4:
            default:
                return true;
            case 5:
                int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                this.j = MotionEventCompat.getX(motionEvent, actionIndex);
                this.k = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                return true;
            case 6:
                int actionIndex2 = MotionEventCompat.getActionIndex(motionEvent);
                if (MotionEventCompat.getPointerId(motionEvent, actionIndex2) == this.k) {
                    this.k = MotionEventCompat.getPointerId(motionEvent, actionIndex2 == 0 ? 1 : 0);
                }
                this.j = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.k));
                return true;
        }
    }

    public void setViewPager(ViewPager viewPager) {
        if (this.c != viewPager) {
            if (this.c != null) {
                this.c.setOnPageChangeListener(null);
            }
            if (viewPager.getAdapter() == null) {
                throw new IllegalStateException("ViewPager does not have adapter instance.");
            }
            this.c = viewPager;
            this.c.setOnPageChangeListener(this);
            invalidate();
        }
    }

    public void setViewPager(ViewPager viewPager, int i) {
        setViewPager(viewPager);
        setCurrentItem(i);
    }

    public void setCurrentItem(int i) {
        if (this.c == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        this.c.setCurrentItem(i);
        this.e = i;
        invalidate();
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        if (this.d != null) {
            this.d.onPageScrollStateChanged(i);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        if (this.d != null) {
            this.d.onPageScrolled(i, f, i2);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        this.e = i;
        invalidate();
        if (this.d != null) {
            this.d.onPageSelected(i);
        }
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.d = onPageChangeListener;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(a(i), b(i2));
    }

    private int a(int i) {
        float f;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824 || this.c == null) {
            f = size;
        } else {
            f = ((r0 - 1) * this.h) + getPaddingLeft() + getPaddingRight() + (this.c.getAdapter().getCount() * this.g);
            if (mode == Integer.MIN_VALUE) {
                f = Math.min(f, size);
            }
        }
        return (int) Math.ceil(f);
    }

    private int b(int i) {
        float strokeWidth;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            strokeWidth = size;
        } else {
            strokeWidth = this.b.getStrokeWidth() + getPaddingTop() + getPaddingBottom();
            if (mode == Integer.MIN_VALUE) {
                strokeWidth = Math.min(strokeWidth, size);
            }
        }
        return (int) Math.ceil(strokeWidth);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.e = savedState.a;
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.e;
        return savedState;
    }

    /* loaded from: classes.dex */
    public class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new ccr();
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
