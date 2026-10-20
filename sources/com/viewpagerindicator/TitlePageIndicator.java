package com.viewpagerindicator;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import defpackage.ccs;
import defpackage.ccu;
import defpackage.ccv;
import defpackage.ccw;
import defpackage.ccx;
import defpackage.ccy;
import defpackage.ccz;
import defpackage.cdf;
import defpackage.cdg;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class TitlePageIndicator extends View implements ccs {
    private cdf A;
    private ViewPager a;
    private ViewPager.OnPageChangeListener b;
    private int c;
    private float d;
    private int e;
    private final Paint f;
    private boolean g;
    private int h;
    private int i;
    private Path j;
    private final Rect k;
    private final Paint l;
    private IndicatorStyle m;
    private LinePosition n;
    private final Paint o;
    private float p;
    private float q;
    private float r;
    private float s;
    private float t;
    private float u;
    private float v;
    private int w;
    private float x;
    private int y;
    private boolean z;

    /* loaded from: classes.dex */
    public enum IndicatorStyle {
        None(0),
        Triangle(1),
        Underline(2);

        public final int value;

        IndicatorStyle(int i) {
            this.value = i;
        }

        public static IndicatorStyle a(int i) {
            for (IndicatorStyle indicatorStyle : values()) {
                if (indicatorStyle.value == i) {
                    return indicatorStyle;
                }
            }
            return null;
        }
    }

    /* loaded from: classes.dex */
    public enum LinePosition {
        Bottom(0),
        Top(1);

        public final int value;

        LinePosition(int i) {
            this.value = i;
        }

        public static LinePosition a(int i) {
            for (LinePosition linePosition : values()) {
                if (linePosition.value == i) {
                    return linePosition;
                }
            }
            return null;
        }
    }

    public TitlePageIndicator(Context context) {
        this(context, null);
    }

    public TitlePageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, ccu.vpiTitlePageIndicatorStyle);
    }

    public TitlePageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = -1;
        this.f = new Paint();
        this.j = new Path();
        this.k = new Rect();
        this.l = new Paint();
        this.o = new Paint();
        this.x = -1.0f;
        this.y = -1;
        if (!isInEditMode()) {
            Resources resources = getResources();
            int color = resources.getColor(ccw.default_title_indicator_footer_color);
            float dimension = resources.getDimension(ccx.default_title_indicator_footer_line_height);
            int integer = resources.getInteger(ccy.default_title_indicator_footer_indicator_style);
            float dimension2 = resources.getDimension(ccx.default_title_indicator_footer_indicator_height);
            float dimension3 = resources.getDimension(ccx.default_title_indicator_footer_indicator_underline_padding);
            float dimension4 = resources.getDimension(ccx.default_title_indicator_footer_padding);
            int integer2 = resources.getInteger(ccy.default_title_indicator_line_position);
            int color2 = resources.getColor(ccw.default_title_indicator_selected_color);
            boolean z = resources.getBoolean(ccv.default_title_indicator_selected_bold);
            int color3 = resources.getColor(ccw.default_title_indicator_text_color);
            float dimension5 = resources.getDimension(ccx.default_title_indicator_text_size);
            float dimension6 = resources.getDimension(ccx.default_title_indicator_title_padding);
            float dimension7 = resources.getDimension(ccx.default_title_indicator_clip_padding);
            float dimension8 = resources.getDimension(ccx.default_title_indicator_top_padding);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ccz.TitlePageIndicator, i, 0);
            this.v = obtainStyledAttributes.getDimension(ccz.TitlePageIndicator_footerLineHeight, dimension);
            this.m = IndicatorStyle.a(obtainStyledAttributes.getInteger(ccz.TitlePageIndicator_footerIndicatorStyle, integer));
            this.p = obtainStyledAttributes.getDimension(ccz.TitlePageIndicator_footerIndicatorHeight, dimension2);
            this.q = obtainStyledAttributes.getDimension(ccz.TitlePageIndicator_footerIndicatorUnderlinePadding, dimension3);
            this.r = obtainStyledAttributes.getDimension(ccz.TitlePageIndicator_footerPadding, dimension4);
            this.n = LinePosition.a(obtainStyledAttributes.getInteger(ccz.TitlePageIndicator_linePosition, integer2));
            this.t = obtainStyledAttributes.getDimension(ccz.TitlePageIndicator_topPadding, dimension8);
            this.s = obtainStyledAttributes.getDimension(ccz.TitlePageIndicator_titlePadding, dimension6);
            this.u = obtainStyledAttributes.getDimension(ccz.TitlePageIndicator_clipPadding, dimension7);
            this.i = obtainStyledAttributes.getColor(ccz.TitlePageIndicator_selectedColor, color2);
            this.h = obtainStyledAttributes.getColor(ccz.TitlePageIndicator_android_textColor, color3);
            this.g = obtainStyledAttributes.getBoolean(ccz.TitlePageIndicator_selectedBold, z);
            float dimension9 = obtainStyledAttributes.getDimension(ccz.TitlePageIndicator_android_textSize, dimension5);
            int color4 = obtainStyledAttributes.getColor(ccz.TitlePageIndicator_footerColor, color);
            this.f.setTextSize(dimension9);
            this.f.setAntiAlias(true);
            this.l.setStyle(Paint.Style.FILL_AND_STROKE);
            this.l.setStrokeWidth(this.v);
            this.l.setColor(color4);
            this.o.setStyle(Paint.Style.FILL_AND_STROKE);
            this.o.setColor(color4);
            Drawable drawable = obtainStyledAttributes.getDrawable(ccz.TitlePageIndicator_android_background);
            if (drawable != null) {
                setBackgroundDrawable(drawable);
            }
            obtainStyledAttributes.recycle();
            this.w = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
        }
    }

    public int getFooterColor() {
        return this.l.getColor();
    }

    public void setFooterColor(int i) {
        this.l.setColor(i);
        this.o.setColor(i);
        invalidate();
    }

    public float getFooterLineHeight() {
        return this.v;
    }

    public void setFooterLineHeight(float f) {
        this.v = f;
        this.l.setStrokeWidth(this.v);
        invalidate();
    }

    public float getFooterIndicatorHeight() {
        return this.p;
    }

    public void setFooterIndicatorHeight(float f) {
        this.p = f;
        invalidate();
    }

    public float getFooterIndicatorPadding() {
        return this.r;
    }

    public void setFooterIndicatorPadding(float f) {
        this.r = f;
        invalidate();
    }

    public IndicatorStyle getFooterIndicatorStyle() {
        return this.m;
    }

    public void setFooterIndicatorStyle(IndicatorStyle indicatorStyle) {
        this.m = indicatorStyle;
        invalidate();
    }

    public LinePosition getLinePosition() {
        return this.n;
    }

    public void setLinePosition(LinePosition linePosition) {
        this.n = linePosition;
        invalidate();
    }

    public int getSelectedColor() {
        return this.i;
    }

    public void setSelectedColor(int i) {
        this.i = i;
        invalidate();
    }

    public void setSelectedBold(boolean z) {
        this.g = z;
        invalidate();
    }

    public int getTextColor() {
        return this.h;
    }

    public void setTextColor(int i) {
        this.f.setColor(i);
        this.h = i;
        invalidate();
    }

    public float getTextSize() {
        return this.f.getTextSize();
    }

    public void setTextSize(float f) {
        this.f.setTextSize(f);
        invalidate();
    }

    public float getTitlePadding() {
        return this.s;
    }

    public void setTitlePadding(float f) {
        this.s = f;
        invalidate();
    }

    public float getTopPadding() {
        return this.t;
    }

    public void setTopPadding(float f) {
        this.t = f;
        invalidate();
    }

    public float getClipPadding() {
        return this.u;
    }

    public void setClipPadding(float f) {
        this.u = f;
        invalidate();
    }

    public void setTypeface(Typeface typeface) {
        this.f.setTypeface(typeface);
        invalidate();
    }

    public Typeface getTypeface() {
        return this.f.getTypeface();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int count;
        int i;
        float f;
        int i2;
        float f2;
        float f3;
        super.onDraw(canvas);
        if (this.a != null && (count = this.a.getAdapter().getCount()) != 0) {
            if (this.c == -1 && this.a != null) {
                this.c = this.a.getCurrentItem();
            }
            ArrayList<Rect> a = a(this.f);
            int size = a.size();
            if (this.c >= size) {
                setCurrentItem(size - 1);
                return;
            }
            int i3 = count - 1;
            float width = getWidth() / 2.0f;
            int left = getLeft();
            float f4 = left + this.u;
            int width2 = getWidth();
            int height = getHeight();
            int i4 = left + width2;
            float f5 = i4 - this.u;
            int i5 = this.c;
            if (this.d <= 0.5d) {
                i = i5;
                f = this.d;
            } else {
                i = i5 + 1;
                f = 1.0f - this.d;
            }
            boolean z = f <= 0.25f;
            boolean z2 = f <= 0.05f;
            float f6 = (0.25f - f) / 0.25f;
            Rect rect = a.get(this.c);
            float f7 = rect.right - rect.left;
            if (rect.left < f4) {
                b(rect, f7, left);
            }
            if (rect.right > f5) {
                a(rect, f7, i4);
            }
            if (this.c > 0) {
                for (int i6 = this.c - 1; i6 >= 0; i6--) {
                    Rect rect2 = a.get(i6);
                    if (rect2.left < f4) {
                        int i7 = rect2.right - rect2.left;
                        b(rect2, i7, left);
                        Rect rect3 = a.get(i6 + 1);
                        if (rect2.right + this.s > rect3.left) {
                            rect2.left = (int) ((rect3.left - i7) - this.s);
                            rect2.right = rect2.left + i7;
                        }
                    }
                }
            }
            if (this.c < i3) {
                int i8 = this.c + 1;
                while (true) {
                    int i9 = i8;
                    if (i9 >= count) {
                        break;
                    }
                    Rect rect4 = a.get(i9);
                    if (rect4.right > f5) {
                        int i10 = rect4.right - rect4.left;
                        a(rect4, i10, i4);
                        Rect rect5 = a.get(i9 - 1);
                        if (rect4.left - this.s < rect5.right) {
                            rect4.left = (int) (rect5.right + this.s);
                            rect4.right = rect4.left + i10;
                        }
                    }
                    i8 = i9 + 1;
                }
            }
            int i11 = this.h >>> 24;
            int i12 = 0;
            while (true) {
                int i13 = i12;
                if (i13 >= count) {
                    break;
                }
                Rect rect6 = a.get(i13);
                if ((rect6.left > left && rect6.left < i4) || (rect6.right > left && rect6.right < i4)) {
                    boolean z3 = i13 == i;
                    CharSequence a2 = a(i13);
                    this.f.setFakeBoldText(z3 && z2 && this.g);
                    this.f.setColor(this.h);
                    if (z3 && z) {
                        this.f.setAlpha(i11 - ((int) (i11 * f6)));
                    }
                    if (i13 < size - 1) {
                        Rect rect7 = a.get(i13 + 1);
                        if (rect6.right + this.s > rect7.left) {
                            int i14 = rect6.right - rect6.left;
                            rect6.left = (int) ((rect7.left - i14) - this.s);
                            rect6.right = rect6.left + i14;
                        }
                    }
                    canvas.drawText(a2, 0, a2.length(), rect6.left, this.t + rect6.bottom, this.f);
                    if (z3 && z) {
                        this.f.setColor(this.i);
                        this.f.setAlpha((int) ((this.i >>> 24) * f6));
                        canvas.drawText(a2, 0, a2.length(), rect6.left, this.t + rect6.bottom, this.f);
                    }
                }
                i12 = i13 + 1;
            }
            float f8 = this.v;
            float f9 = this.p;
            if (this.n == LinePosition.Top) {
                i2 = 0;
                float f10 = -f9;
                f3 = -f8;
                f2 = f10;
            } else {
                i2 = height;
                f2 = f9;
                f3 = f8;
            }
            this.j.reset();
            this.j.moveTo(0.0f, i2 - (f3 / 2.0f));
            this.j.lineTo(width2, i2 - (f3 / 2.0f));
            this.j.close();
            canvas.drawPath(this.j, this.l);
            float f11 = i2 - f3;
            switch (this.m) {
                case Triangle:
                    this.j.reset();
                    this.j.moveTo(width, f11 - f2);
                    this.j.lineTo(width + f2, f11);
                    this.j.lineTo(width - f2, f11);
                    this.j.close();
                    canvas.drawPath(this.j, this.o);
                    return;
                case Underline:
                    if (z && i < size) {
                        Rect rect8 = a.get(i);
                        float f12 = rect8.right + this.q;
                        float f13 = rect8.left - this.q;
                        float f14 = f11 - f2;
                        this.j.reset();
                        this.j.moveTo(f13, f11);
                        this.j.lineTo(f12, f11);
                        this.j.lineTo(f12, f14);
                        this.j.lineTo(f13, f14);
                        this.j.close();
                        this.o.setAlpha((int) (255.0f * f6));
                        canvas.drawPath(this.j, this.o);
                        this.o.setAlpha(MotionEventCompat.ACTION_MASK);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
        } catch (Throwable th) {
            Log.e("thuuz", "problem: ", th);
            return false;
        }
        if (super.onTouchEvent(motionEvent)) {
            return true;
        }
        if (this.a == null || this.a.getAdapter().getCount() == 0) {
            return false;
        }
        int action = motionEvent.getAction() & MotionEventCompat.ACTION_MASK;
        switch (action) {
            case 0:
                this.y = MotionEventCompat.getPointerId(motionEvent, 0);
                this.x = motionEvent.getX();
                return true;
            case 1:
            case 3:
                if (!this.z) {
                    int count = this.a.getAdapter().getCount();
                    int width = getWidth();
                    float f = width / 2.0f;
                    float f2 = width / 6.0f;
                    float f3 = f - f2;
                    float f4 = f2 + f;
                    float x = motionEvent.getX();
                    if (x < f3) {
                        if (this.c > 0) {
                            if (action == 3) {
                                return true;
                            }
                            this.a.setCurrentItem(this.c - 1);
                            return true;
                        }
                    } else if (x > f4) {
                        if (this.c < count - 1) {
                            if (action == 3) {
                                return true;
                            }
                            this.a.setCurrentItem(this.c + 1);
                            return true;
                        }
                    } else if (this.A != null && action != 3) {
                        this.A.a(this.c);
                    }
                }
                this.z = false;
                this.y = -1;
                if (!this.a.isFakeDragging()) {
                    return true;
                }
                this.a.endFakeDrag();
                return true;
            case 2:
                float x2 = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.y));
                float f5 = x2 - this.x;
                if (!this.z && Math.abs(f5) > this.w) {
                    this.z = true;
                }
                if (!this.z) {
                    return true;
                }
                this.x = x2;
                if (!this.a.isFakeDragging() && !this.a.beginFakeDrag()) {
                    return true;
                }
                try {
                    this.a.fakeDragBy(f5);
                    return true;
                } catch (Throwable th2) {
                    Log.e("thuuz", "problem dragging: " + th2);
                    return true;
                }
            case 4:
            default:
                return true;
            case 5:
                int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                this.x = MotionEventCompat.getX(motionEvent, actionIndex);
                this.y = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                return true;
            case 6:
                int actionIndex2 = MotionEventCompat.getActionIndex(motionEvent);
                if (MotionEventCompat.getPointerId(motionEvent, actionIndex2) == this.y) {
                    this.y = MotionEventCompat.getPointerId(motionEvent, actionIndex2 == 0 ? 1 : 0);
                }
                this.x = MotionEventCompat.getX(motionEvent, MotionEventCompat.findPointerIndex(motionEvent, this.y));
                return true;
        }
        Log.e("thuuz", "problem: ", th);
        return false;
    }

    private void a(Rect rect, float f, int i) {
        rect.right = (int) (i - this.u);
        rect.left = (int) (rect.right - f);
    }

    private void b(Rect rect, float f, int i) {
        rect.left = (int) (i + this.u);
        rect.right = (int) (this.u + f);
    }

    private ArrayList<Rect> a(Paint paint) {
        ArrayList<Rect> arrayList = new ArrayList<>();
        int count = this.a.getAdapter().getCount();
        int width = getWidth();
        int i = width / 2;
        for (int i2 = 0; i2 < count; i2++) {
            Rect a = a(i2, paint);
            int i3 = a.right - a.left;
            int i4 = a.bottom - a.top;
            a.left = (int) ((i - (i3 / 2.0f)) + (((i2 - this.c) - this.d) * width));
            a.right = i3 + a.left;
            a.top = 0;
            a.bottom = i4;
            arrayList.add(a);
        }
        return arrayList;
    }

    private Rect a(int i, Paint paint) {
        Rect rect = new Rect();
        CharSequence a = a(i);
        rect.right = (int) paint.measureText(a, 0, a.length());
        rect.bottom = (int) (paint.descent() - paint.ascent());
        return rect;
    }

    public void setViewPager(ViewPager viewPager) {
        if (this.a != viewPager) {
            if (this.a != null) {
                this.a.setOnPageChangeListener(null);
            }
            if (viewPager.getAdapter() == null) {
                throw new IllegalStateException("ViewPager does not have adapter instance.");
            }
            this.a = viewPager;
            this.a.setOnPageChangeListener(this);
            invalidate();
        }
    }

    public void setViewPager(ViewPager viewPager, int i) {
        setViewPager(viewPager);
        setCurrentItem(i);
    }

    public void setOnCenterItemClickListener(cdf cdfVar) {
        this.A = cdfVar;
    }

    public void setCurrentItem(int i) {
        if (this.a == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        this.a.setCurrentItem(i);
        this.c = i;
        invalidate();
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        this.e = i;
        if (this.b != null) {
            this.b.onPageScrollStateChanged(i);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        this.c = i;
        this.d = f;
        invalidate();
        if (this.b != null) {
            this.b.onPageScrolled(i, f, i2);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        if (this.e == 0) {
            this.c = i;
            invalidate();
        }
        if (this.b != null) {
            this.b.onPageSelected(i);
        }
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.b = onPageChangeListener;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        float f;
        int size = View.MeasureSpec.getSize(i);
        if (View.MeasureSpec.getMode(i2) == 1073741824) {
            f = View.MeasureSpec.getSize(i2);
        } else {
            this.k.setEmpty();
            this.k.bottom = (int) (this.f.descent() - this.f.ascent());
            f = (this.k.bottom - this.k.top) + this.v + this.r + this.t;
            if (this.m != IndicatorStyle.None) {
                f += this.p;
            }
        }
        setMeasuredDimension(size, (int) f);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.c = savedState.a;
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.c;
        return savedState;
    }

    /* loaded from: classes.dex */
    public class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new cdg();
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

    private CharSequence a(int i) {
        CharSequence pageTitle = this.a.getAdapter().getPageTitle(i);
        if (pageTitle == null) {
            return "";
        }
        return pageTitle;
    }
}
