package com.actionbarsherlock.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.actionbarsherlock.R;
import com.actionbarsherlock.internal.nineoldandroids.widget.NineFrameLayout;

/* loaded from: classes.dex */
public class ActionBarContainer extends NineFrameLayout {
    private ActionBarView mActionBarView;
    private Drawable mBackground;
    private boolean mIsSplit;
    private boolean mIsStacked;
    private boolean mIsTransitioning;
    private Drawable mSplitBackground;
    private Drawable mStackedBackground;
    private View mTabContainer;

    public ActionBarContainer(Context context) {
        this(context, null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        boolean z = true;
        setBackgroundDrawable(null);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SherlockActionBar);
        this.mBackground = obtainStyledAttributes.getDrawable(R.styleable.SherlockActionBar_background);
        this.mStackedBackground = obtainStyledAttributes.getDrawable(R.styleable.SherlockActionBar_backgroundStacked);
        if ((this.mStackedBackground instanceof ColorDrawable) && Build.VERSION.SDK_INT < 11) {
            Bitmap createBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
            this.mStackedBackground.draw(new Canvas(createBitmap));
            int pixel = createBitmap.getPixel(0, 0);
            createBitmap.recycle();
            this.mStackedBackground = new IcsColorDrawable(pixel);
        }
        if (getId() == R.id.abs__split_action_bar) {
            this.mIsSplit = true;
            this.mSplitBackground = obtainStyledAttributes.getDrawable(R.styleable.SherlockActionBar_backgroundSplit);
        }
        obtainStyledAttributes.recycle();
        if (this.mIsSplit) {
            if (this.mSplitBackground != null) {
                z = false;
            }
        } else if (this.mBackground != null || this.mStackedBackground != null) {
            z = false;
        }
        setWillNotDraw(z);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mActionBarView = (ActionBarView) findViewById(R.id.abs__action_bar);
    }

    public void setPrimaryBackground(Drawable drawable) {
        this.mBackground = drawable;
        invalidate();
    }

    public void setStackedBackground(Drawable drawable) {
        this.mStackedBackground = drawable;
        invalidate();
    }

    public void setSplitBackground(Drawable drawable) {
        this.mSplitBackground = drawable;
        invalidate();
    }

    public void setTransitioning(boolean z) {
        this.mIsTransitioning = z;
        setDescendantFocusability(z ? 393216 : 262144);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.mIsTransitioning || super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    public void setTabContainer(ScrollingTabContainerView scrollingTabContainerView) {
        if (this.mTabContainer != null) {
            removeView(this.mTabContainer);
        }
        this.mTabContainer = scrollingTabContainerView;
        if (scrollingTabContainerView != null) {
            addView(scrollingTabContainerView);
            ViewGroup.LayoutParams layoutParams = scrollingTabContainerView.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            scrollingTabContainerView.setAllowCollapse(false);
        }
    }

    public View getTabContainer() {
        return this.mTabContainer;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (getWidth() != 0 && getHeight() != 0) {
            if (this.mIsSplit) {
                if (this.mSplitBackground != null) {
                    this.mSplitBackground.draw(canvas);
                }
            } else {
                if (this.mBackground != null) {
                    this.mBackground.draw(canvas);
                }
                if (this.mStackedBackground != null && this.mIsStacked) {
                    this.mStackedBackground.draw(canvas);
                }
            }
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int measuredHeight;
        super.onMeasure(i, i2);
        if (this.mActionBarView != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mActionBarView.getLayoutParams();
            if (this.mActionBarView.isCollapsed()) {
                measuredHeight = 0;
            } else {
                measuredHeight = layoutParams.bottomMargin + this.mActionBarView.getMeasuredHeight() + layoutParams.topMargin;
            }
            if (this.mTabContainer != null && this.mTabContainer.getVisibility() != 8 && View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE) {
                setMeasuredDimension(getMeasuredWidth(), Math.min(measuredHeight + this.mTabContainer.getMeasuredHeight(), View.MeasureSpec.getSize(i2)));
            }
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2;
        boolean z3 = true;
        boolean z4 = false;
        super.onLayout(z, i, i2, i3, i4);
        boolean z5 = (this.mTabContainer == null || this.mTabContainer.getVisibility() == 8) ? false : true;
        if (this.mTabContainer != null && this.mTabContainer.getVisibility() != 8) {
            int measuredHeight = getMeasuredHeight();
            int measuredHeight2 = this.mTabContainer.getMeasuredHeight();
            if ((this.mActionBarView.getDisplayOptions() & 2) == 0) {
                int childCount = getChildCount();
                for (int i5 = 0; i5 < childCount; i5++) {
                    View childAt = getChildAt(i5);
                    if (childAt != this.mTabContainer && !this.mActionBarView.isCollapsed()) {
                        childAt.offsetTopAndBottom(measuredHeight2);
                    }
                }
                this.mTabContainer.layout(i, 0, i3, measuredHeight2);
            } else {
                this.mTabContainer.layout(i, measuredHeight - measuredHeight2, i3, measuredHeight);
            }
        }
        if (this.mIsSplit) {
            if (this.mSplitBackground != null) {
                this.mSplitBackground.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            } else {
                z3 = false;
            }
        } else {
            if (this.mBackground != null) {
                this.mBackground.setBounds(this.mActionBarView.getLeft(), this.mActionBarView.getTop(), this.mActionBarView.getRight(), this.mActionBarView.getBottom());
                z2 = true;
            } else {
                z2 = false;
            }
            if (z5 && this.mStackedBackground != null) {
                z4 = true;
            }
            this.mIsStacked = z4;
            if (z4) {
                this.mStackedBackground.setBounds(this.mTabContainer.getLeft(), this.mTabContainer.getTop(), this.mTabContainer.getRight(), this.mTabContainer.getBottom());
            } else {
                z3 = z2;
            }
        }
        if (z3) {
            invalidate();
        }
    }
}
