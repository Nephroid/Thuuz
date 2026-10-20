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
import defpackage.ccs;
import defpackage.ccu;
import defpackage.ccv;
import defpackage.ccw;
import defpackage.ccy;
import defpackage.ccz;
import defpackage.cdh;
import defpackage.cdi;
import defpackage.cdj;

/* loaded from: classes.dex */
public class UnderlinePageIndicator extends View implements ccs {
    private final Paint a;
    private boolean b;
    private int c;
    private int d;
    private int e;
    private ViewPager f;
    private ViewPager.OnPageChangeListener g;
    private int h;
    private int i;
    private float j;
    private int k;
    private float l;
    private int m;
    private boolean n;
    private final Runnable o;

    public UnderlinePageIndicator(Context context) {
        this(context, null);
    }

    public UnderlinePageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, ccu.vpiUnderlinePageIndicatorStyle);
    }

    public UnderlinePageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new Paint(1);
        this.l = -1.0f;
        this.m = -1;
        this.o = new cdh(this);
        if (!isInEditMode()) {
            Resources resources = getResources();
            boolean z = resources.getBoolean(ccv.default_underline_indicator_fades);
            int integer = resources.getInteger(ccy.default_underline_indicator_fade_delay);
            int integer2 = resources.getInteger(ccy.default_underline_indicator_fade_length);
            int color = resources.getColor(ccw.default_underline_indicator_selected_color);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ccz.UnderlinePageIndicator, i, 0);
            setFades(obtainStyledAttributes.getBoolean(ccz.UnderlinePageIndicator_fades, z));
            setSelectedColor(obtainStyledAttributes.getColor(ccz.UnderlinePageIndicator_selectedColor, color));
            setFadeDelay(obtainStyledAttributes.getInteger(ccz.UnderlinePageIndicator_fadeDelay, integer));
            setFadeLength(obtainStyledAttributes.getInteger(ccz.UnderlinePageIndicator_fadeLength, integer2));
            Drawable drawable = obtainStyledAttributes.getDrawable(ccz.UnderlinePageIndicator_android_background);
            if (drawable != null) {
                setBackgroundDrawable(drawable);
            }
            obtainStyledAttributes.recycle();
            this.k = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
        }
    }

    public boolean getFades() {
        return this.b;
    }

    public void setFades(boolean z) {
        if (z != this.b) {
            this.b = z;
            if (z) {
                post(this.o);
                return;
            }
            removeCallbacks(this.o);
            this.a.setAlpha(MotionEventCompat.ACTION_MASK);
            invalidate();
        }
    }

    public int getFadeDelay() {
        return this.c;
    }

    public void setFadeDelay(int i) {
        this.c = i;
    }

    public int getFadeLength() {
        return this.d;
    }

    public void setFadeLength(int i) {
        this.d = i;
        this.e = MotionEventCompat.ACTION_MASK / (this.d / 30);
    }

    public int getSelectedColor() {
        return this.a.getColor();
    }

    public void setSelectedColor(int i) {
        this.a.setColor(i);
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int count;
        super.onDraw(canvas);
        if (this.f != null && (count = this.f.getAdapter().getCount()) != 0) {
            if (this.i >= count) {
                setCurrentItem(count - 1);
                return;
            }
            float width = ((getWidth() - r1) - getPaddingRight()) / (count * 1.0f);
            float paddingLeft = getPaddingLeft() + ((this.i + this.j) * width);
            canvas.drawRect(paddingLeft, getPaddingTop(), paddingLeft + width, getHeight() - getPaddingBottom(), this.a);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (super.onTouchEvent(motionEvent)) {
            return true;
        }
        if (this.f == null || this.f.getAdapter().getCount() == 0) {
            return false;
        }
        int action = motionEvent.getAction() & MotionEventCompat.ACTION_MASK;
        switch (action) {
            case 0:
                this.m = MotionEventCompat.getPointerId(motionEvent, 0);
                this.l = motionEvent.getX();
                return true;
            case 1:
            case 3:
                if (!this.n) {
                    int count = this.f.getAdapter().getCount();
                    int width = getWidth();
                    float f = width / 2.0f;
                    float f2 = width / 6.0f;
                    if (this.i > 0 && motionEvent.getX() < f - f2) {
                        if (action == 3) {
                            return true;
                        }
                        this.f.setCurrentItem(this.i - 1);
                        return true;
                    }
                    if (this.i < count - 1 && motionEvent.getX() > f2 + f) {
                        if (action == 3) {
                            return true;
                        }
                        this.f.setCurrentItem(this.i + 1);
                        return true;
                    }
                }
                this.n = false;
                this.m = -1;
                if (!this.f.isFakeDragging()) {
                    return true;
                }
                this.f.endFakeDrag();
                return true;
            case 2:
                float x = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.m));
                float f3 = x - this.l;
                if (!this.n && Math.abs(f3) > this.k) {
                    this.n = true;
                }
                if (!this.n) {
                    return true;
                }
                this.l = x;
                if (!this.f.isFakeDragging() && !this.f.beginFakeDrag()) {
                    return true;
                }
                this.f.fakeDragBy(f3);
                return true;
            case 4:
            default:
                return true;
            case 5:
                int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                this.l = MotionEventCompat.getX(motionEvent, actionIndex);
                this.m = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                return true;
            case 6:
                int actionIndex2 = MotionEventCompat.getActionIndex(motionEvent);
                if (MotionEventCompat.getPointerId(motionEvent, actionIndex2) == this.m) {
                    this.m = MotionEventCompat.getPointerId(motionEvent, actionIndex2 == 0 ? 1 : 0);
                }
                this.l = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.m));
                return true;
        }
    }

    public void setViewPager(ViewPager viewPager) {
        if (this.f != viewPager) {
            if (this.f != null) {
                this.f.setOnPageChangeListener(null);
            }
            if (viewPager.getAdapter() == null) {
                throw new IllegalStateException("ViewPager does not have adapter instance.");
            }
            this.f = viewPager;
            this.f.setOnPageChangeListener(this);
            invalidate();
            post(new cdi(this));
        }
    }

    public void setViewPager(ViewPager viewPager, int i) {
        setViewPager(viewPager);
        setCurrentItem(i);
    }

    public void setCurrentItem(int i) {
        if (this.f == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        this.f.setCurrentItem(i);
        this.i = i;
        invalidate();
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        this.h = i;
        if (this.g != null) {
            this.g.onPageScrollStateChanged(i);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        this.i = i;
        this.j = f;
        if (this.b) {
            if (i2 > 0) {
                removeCallbacks(this.o);
                this.a.setAlpha(MotionEventCompat.ACTION_MASK);
            } else if (this.h != 1) {
                postDelayed(this.o, this.c);
            }
        }
        invalidate();
        if (this.g != null) {
            this.g.onPageScrolled(i, f, i2);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        if (this.h == 0) {
            this.i = i;
            this.j = 0.0f;
            invalidate();
            this.o.run();
        }
        if (this.g != null) {
            this.g.onPageSelected(i);
        }
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.g = onPageChangeListener;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.i = savedState.a;
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.i;
        return savedState;
    }

    /* loaded from: classes.dex */
    public class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new cdj();
        int a;

        public /* synthetic */ SavedState(Parcel parcel, cdh cdhVar) {
            this(parcel);
        }

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
