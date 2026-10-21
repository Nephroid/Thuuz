package defpackage;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/* loaded from: classes.dex */
public class ccp extends LinearLayout {
    private static final int[] a = {R.attr.divider, R.attr.showDividers, R.attr.dividerPadding};
    private Drawable b;
    private int c;
    private int d;
    private int e;
    private int f;

    public ccp(Context context, int i) {
        super(context);
        if (!isInEditMode()) {
            try {
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, a, i, 0);
                setDividerDrawable(obtainStyledAttributes.getDrawable(0));
                this.f = obtainStyledAttributes.getDimensionPixelSize(2, 0);
                this.e = obtainStyledAttributes.getInteger(1, 0);
                obtainStyledAttributes.recycle();
            } catch (Throwable th) {
                Log.w("thuuz", "problem in view pager library: " + th);
            }
        }
    }

    @Override // android.widget.LinearLayout
    public void setDividerDrawable(Drawable drawable) {
        if (drawable != this.b) {
            this.b = drawable;
            if (drawable != null) {
                this.c = drawable.getIntrinsicWidth();
                this.d = drawable.getIntrinsicHeight();
            } else {
                this.c = 0;
                this.d = 0;
            }
            setWillNotDraw(drawable == null);
            requestLayout();
        }
    }

    @Override // android.view.ViewGroup
    protected void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        int indexOfChild = indexOfChild(view);
        int orientation = getOrientation();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (a(indexOfChild)) {
            if (orientation == 1) {
                layoutParams.topMargin = this.d;
            } else {
                layoutParams.leftMargin = this.c;
            }
        }
        int childCount = getChildCount();
        if (indexOfChild == childCount - 1 && a(childCount)) {
            if (orientation == 1) {
                layoutParams.bottomMargin = this.d;
            } else {
                layoutParams.rightMargin = this.c;
            }
        }
        super.measureChildWithMargins(view, i, i2, i3, i4);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.b != null) {
            if (getOrientation() == 1) {
                a(canvas);
            } else {
                b(canvas);
            }
        }
        super.onDraw(canvas);
    }

    private void a(Canvas canvas) {
        int bottom;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt != null && childAt.getVisibility() != 8 && a(i)) {
                a(canvas, childAt.getTop() - ((LinearLayout.LayoutParams) childAt.getLayoutParams()).topMargin);
            }
        }
        if (a(childCount)) {
            View childAt2 = getChildAt(childCount - 1);
            if (childAt2 == null) {
                bottom = (getHeight() - getPaddingBottom()) - this.d;
            } else {
                bottom = childAt2.getBottom();
            }
            a(canvas, bottom);
        }
    }

    private void b(Canvas canvas) {
        int right;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt != null && childAt.getVisibility() != 8 && a(i)) {
                b(canvas, childAt.getLeft() - ((LinearLayout.LayoutParams) childAt.getLayoutParams()).leftMargin);
            }
        }
        if (a(childCount)) {
            View childAt2 = getChildAt(childCount - 1);
            if (childAt2 == null) {
                right = (getWidth() - getPaddingRight()) - this.c;
            } else {
                right = childAt2.getRight();
            }
            b(canvas, right);
        }
    }

    private void a(Canvas canvas, int i) {
        this.b.setBounds(getPaddingLeft() + this.f, i, (getWidth() - getPaddingRight()) - this.f, this.d + i);
        this.b.draw(canvas);
    }

    private void b(Canvas canvas, int i) {
        this.b.setBounds(i, getPaddingTop() + this.f, this.c + i, (getHeight() - getPaddingBottom()) - this.f);
        this.b.draw(canvas);
    }

    private boolean a(int i) {
        if (i == 0 || i == getChildCount() || (this.e & 2) == 0) {
            return false;
        }
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (getChildAt(i2).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }
}
