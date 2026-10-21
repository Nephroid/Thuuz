package org.jraf.android.backport.switchwidget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.CompoundButton;
import defpackage.cdl;
import defpackage.cdn;

/* loaded from: classes.dex */
public class Switch extends CompoundButton {
    private static final int[] A = {R.attr.state_checked};
    private final Drawable a;
    private final Drawable b;
    private final int c;
    private final int d;
    private final int e;
    private CharSequence f;
    private CharSequence g;
    private int h;
    private final int i;
    private float j;
    private float k;
    private final VelocityTracker l;
    private final int m;
    private float n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private final TextPaint v;
    private ColorStateList w;
    private Layout x;
    private Layout y;
    private final Rect z;

    public Switch(Context context) {
        this(context, null);
    }

    public Switch(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, cdl.switchStyle);
    }

    public Switch(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.l = VelocityTracker.obtain();
        this.z = new Rect();
        this.v = new TextPaint(1);
        Resources resources = getResources();
        this.v.density = resources.getDisplayMetrics().density;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, cdn.Switch, i, 0);
        this.a = obtainStyledAttributes.getDrawable(cdn.Switch_thumb);
        this.b = obtainStyledAttributes.getDrawable(cdn.Switch_track);
        this.f = obtainStyledAttributes.getText(cdn.Switch_textOn);
        this.g = obtainStyledAttributes.getText(cdn.Switch_textOff);
        this.c = obtainStyledAttributes.getDimensionPixelSize(cdn.Switch_thumbTextPadding, 0);
        this.d = obtainStyledAttributes.getDimensionPixelSize(cdn.Switch_switchMinWidth, 0);
        this.e = obtainStyledAttributes.getDimensionPixelSize(cdn.Switch_switchPadding, 0);
        int resourceId = obtainStyledAttributes.getResourceId(cdn.Switch_switchTextAppearance, 0);
        if (resourceId != 0) {
            setSwitchTextAppearance(context, resourceId);
        }
        obtainStyledAttributes.recycle();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.i = viewConfiguration.getScaledTouchSlop();
        this.m = viewConfiguration.getScaledMinimumFlingVelocity();
        refreshDrawableState();
        setChecked(isChecked());
    }

    public void setSwitchTextAppearance(Context context, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, cdn.Android);
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(cdn.Android_android_textColor);
        if (colorStateList != null) {
            this.w = colorStateList;
        } else {
            this.w = getTextColors();
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(cdn.Android_android_textSize, 0);
        if (dimensionPixelSize != 0 && dimensionPixelSize != this.v.getTextSize()) {
            this.v.setTextSize(dimensionPixelSize);
            requestLayout();
        }
        a(obtainStyledAttributes.getInt(cdn.Android_android_typeface, -1), obtainStyledAttributes.getInt(cdn.Android_android_textStyle, -1));
        obtainStyledAttributes.recycle();
    }

    private void a(int i, int i2) {
        Typeface typeface = null;
        switch (i) {
            case 1:
                typeface = Typeface.SANS_SERIF;
                break;
            case 2:
                typeface = Typeface.SERIF;
                break;
            case 3:
                typeface = Typeface.MONOSPACE;
                break;
        }
        setSwitchTypeface(typeface, i2);
    }

    public void setSwitchTypeface(Typeface typeface, int i) {
        Typeface create;
        if (i > 0) {
            if (typeface == null) {
                create = Typeface.defaultFromStyle(i);
            } else {
                create = Typeface.create(typeface, i);
            }
            setSwitchTypeface(create);
            int style = ((create != null ? create.getStyle() : 0) ^ (-1)) & i;
            this.v.setFakeBoldText((style & 1) != 0);
            this.v.setTextSkewX((style & 2) != 0 ? -0.25f : 0.0f);
            return;
        }
        this.v.setFakeBoldText(false);
        this.v.setTextSkewX(0.0f);
        setSwitchTypeface(typeface);
    }

    public void setSwitchTypeface(Typeface typeface) {
        if (this.v.getTypeface() != typeface) {
            this.v.setTypeface(typeface);
            requestLayout();
            invalidate();
        }
    }

    public CharSequence getTextOn() {
        return this.f;
    }

    public void setTextOn(CharSequence charSequence) {
        this.f = charSequence;
        requestLayout();
    }

    public CharSequence getTextOff() {
        return this.g;
    }

    public void setTextOff(CharSequence charSequence) {
        this.g = charSequence;
        requestLayout();
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        if (this.x == null) {
            this.x = a(this.f);
        }
        if (this.y == null) {
            this.y = a(this.g);
        }
        if (this.b != null) {
            this.b.getPadding(this.z);
        }
        int max = Math.max(this.x.getWidth(), this.y.getWidth());
        int max2 = Math.max(this.d, (max * 2) + (this.c * 4) + this.z.left + this.z.right);
        int i3 = 0;
        if (this.b != null) {
            i3 = this.b.getIntrinsicHeight();
        }
        this.q = max + (this.c * 2);
        this.o = max2;
        this.p = i3;
        super.onMeasure(i, i2);
        if (getMeasuredHeight() < i3) {
            setMeasuredDimension(getMeasuredWidth(), i3);
        }
    }

    private Layout a(CharSequence charSequence) {
        if (isInEditMode()) {
            if (charSequence == null) {
                return new StaticLayout("", this.v, 100, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
            }
            return new StaticLayout(charSequence, this.v, 100, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
        }
        return new StaticLayout(charSequence, this.v, (int) Math.ceil(Layout.getDesiredWidth(charSequence, this.v)), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
    }

    private boolean a(float f, float f2) {
        this.a.getPadding(this.z);
        int i = this.s - this.i;
        int i2 = (this.r + ((int) (this.n + 0.5f))) - this.i;
        return f > ((float) i2) && f < ((float) ((((this.q + i2) + this.z.left) + this.z.right) + this.i)) && f2 > ((float) i) && f2 < ((float) (this.u + this.i));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:12:0x0030. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x000b. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:3:0x000e A[FALL_THROUGH] */
    @Override // android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            r5 = 2
            r0 = 1
            android.view.VelocityTracker r1 = r6.l
            r1.addMovement(r7)
            int r1 = r7.getAction()
            switch(r1) {
                case 0: goto L13;
                case 1: goto L8f;
                case 2: goto L2e;
                case 3: goto L8f;
                default: goto Le;
            }
        Le:
            boolean r0 = super.onTouchEvent(r7)
        L12:
            return r0
        L13:
            float r1 = r7.getX()
            float r2 = r7.getY()
            boolean r3 = r6.isEnabled()
            if (r3 == 0) goto Le
            boolean r3 = r6.a(r1, r2)
            if (r3 == 0) goto Le
            r6.h = r0
            r6.j = r1
            r6.k = r2
            goto Le
        L2e:
            int r1 = r6.h
            switch(r1) {
                case 0: goto Le;
                case 1: goto L34;
                case 2: goto L68;
                default: goto L33;
            }
        L33:
            goto Le
        L34:
            float r1 = r7.getX()
            float r2 = r7.getY()
            float r3 = r6.j
            float r3 = r1 - r3
            float r3 = java.lang.Math.abs(r3)
            int r4 = r6.i
            float r4 = (float) r4
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 > 0) goto L5a
            float r3 = r6.k
            float r3 = r2 - r3
            float r3 = java.lang.Math.abs(r3)
            int r4 = r6.i
            float r4 = (float) r4
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 <= 0) goto Le
        L5a:
            r6.h = r5
            android.view.ViewParent r3 = r6.getParent()
            r3.requestDisallowInterceptTouchEvent(r0)
            r6.j = r1
            r6.k = r2
            goto L12
        L68:
            float r1 = r7.getX()
            float r2 = r6.j
            float r2 = r1 - r2
            r3 = 0
            float r4 = r6.n
            float r2 = r2 + r4
            int r4 = r6.getThumbScrollRange()
            float r4 = (float) r4
            float r2 = java.lang.Math.min(r2, r4)
            float r2 = java.lang.Math.max(r3, r2)
            float r3 = r6.n
            int r3 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r3 == 0) goto L12
            r6.n = r2
            r6.j = r1
            r6.invalidate()
            goto L12
        L8f:
            int r1 = r6.h
            if (r1 != r5) goto L98
            r6.b(r7)
            goto L12
        L98:
            r0 = 0
            r6.h = r0
            android.view.VelocityTracker r0 = r6.l
            r0.clear()
            goto Le
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jraf.android.backport.switchwidget.Switch.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private void a(MotionEvent motionEvent) {
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.setAction(3);
        super.onTouchEvent(obtain);
        obtain.recycle();
    }

    private void b(MotionEvent motionEvent) {
        boolean z = true;
        this.h = 0;
        boolean z2 = motionEvent.getAction() == 1 && isEnabled();
        a(motionEvent);
        if (z2) {
            this.l.computeCurrentVelocity(1000);
            float xVelocity = this.l.getXVelocity();
            if (Math.abs(xVelocity) <= this.m) {
                z = getTargetCheckedState();
            } else if (xVelocity <= 0.0f) {
                z = false;
            }
            a(z);
            return;
        }
        a(isChecked());
    }

    private void a(boolean z) {
        setChecked(z);
    }

    private boolean getTargetCheckedState() {
        return this.n >= ((float) (getThumbScrollRange() / 2));
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        super.setChecked(z);
        this.n = z ? getThumbScrollRange() : 0.0f;
        invalidate();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int height;
        int i5;
        super.onLayout(z, i, i2, i3, i4);
        this.n = isChecked() ? getThumbScrollRange() : 0.0f;
        int width = getWidth() - getPaddingRight();
        int i6 = width - this.o;
        switch (getGravity() & 112) {
            case 16:
                i5 = (((getPaddingTop() + getHeight()) - getPaddingBottom()) / 2) - (this.p / 2);
                height = this.p + i5;
                break;
            case 80:
                height = getHeight() - getPaddingBottom();
                i5 = height - this.p;
                break;
            default:
                i5 = getPaddingTop();
                height = this.p + i5;
                break;
        }
        this.r = i6;
        this.s = i5;
        this.u = height;
        this.t = width;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = this.r;
        int i2 = this.s;
        int i3 = this.t;
        int i4 = this.u;
        if (this.b != null) {
            this.b.setBounds(i, i2, i3, i4);
            this.b.draw(canvas);
        }
        canvas.save();
        if (this.b != null) {
            this.b.getPadding(this.z);
        }
        int i5 = i + this.z.left;
        int i6 = this.z.top + i2;
        int i7 = i3 - this.z.right;
        int i8 = i4 - this.z.bottom;
        canvas.clipRect(i5, i2, i7, i4);
        if (this.a != null) {
            this.a.getPadding(this.z);
        }
        int i9 = (int) (this.n + 0.5f);
        int i10 = (i5 - this.z.left) + i9;
        int i11 = this.z.right + i5 + i9 + this.q;
        if (this.a != null) {
            this.a.setBounds(i10, i2, i11, i4);
            this.a.draw(canvas);
        }
        if (this.w != null) {
            this.v.setColor(this.w.getColorForState(getDrawableState(), this.w.getDefaultColor()));
        }
        this.v.drawableState = getDrawableState();
        Layout layout = getTargetCheckedState() ? this.x : this.y;
        canvas.translate(((i10 + i11) / 2) - (layout.getWidth() / 2), ((i6 + i8) / 2) - (layout.getHeight() / 2));
        layout.draw(canvas);
        canvas.restore();
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingRight() {
        int compoundPaddingRight = super.getCompoundPaddingRight() + this.o;
        if (!TextUtils.isEmpty(getText())) {
            return compoundPaddingRight + this.e;
        }
        return compoundPaddingRight;
    }

    private int getThumbScrollRange() {
        if (this.b == null) {
            return 0;
        }
        this.b.getPadding(this.z);
        return ((this.o - this.q) - this.z.left) - this.z.right;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (isChecked()) {
            mergeDrawableStates(onCreateDrawableState, A);
        }
        return onCreateDrawableState;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        if (this.a != null) {
            this.a.setState(drawableState);
        }
        if (this.b != null) {
            this.b.setState(drawableState);
        }
        invalidate();
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.a || drawable == this.b;
    }
}
