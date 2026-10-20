package com.actionbarsherlock.internal.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.actionbarsherlock.R;
import com.actionbarsherlock.internal.ResourcesCompat;
import com.actionbarsherlock.internal.nineoldandroids.animation.Animator;
import com.actionbarsherlock.internal.nineoldandroids.animation.AnimatorSet;
import com.actionbarsherlock.internal.nineoldandroids.animation.ObjectAnimator;
import com.actionbarsherlock.internal.nineoldandroids.view.NineViewGroup;
import com.actionbarsherlock.internal.view.menu.ActionMenuPresenter;
import com.actionbarsherlock.internal.view.menu.ActionMenuView;

/* loaded from: classes.dex */
public abstract class AbsActionBarView extends NineViewGroup {
    private static final int FADE_DURATION = 200;
    private static final Interpolator sAlphaInterpolator = new DecelerateInterpolator();
    protected ActionMenuPresenter mActionMenuPresenter;
    protected int mContentHeight;
    final Context mContext;
    protected ActionMenuView mMenuView;
    protected boolean mSplitActionBar;
    protected ActionBarContainer mSplitView;
    protected boolean mSplitWhenNarrow;
    protected final VisibilityAnimListener mVisAnimListener;
    protected Animator mVisibilityAnim;

    public AbsActionBarView(Context context) {
        super(context);
        this.mVisAnimListener = new VisibilityAnimListener();
        this.mContext = context;
    }

    public AbsActionBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mVisAnimListener = new VisibilityAnimListener();
        this.mContext = context;
    }

    public AbsActionBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mVisAnimListener = new VisibilityAnimListener();
        this.mContext = context;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        if (Build.VERSION.SDK_INT >= 8) {
            super.onConfigurationChanged(configuration);
        } else if (this.mMenuView != null) {
            this.mMenuView.onConfigurationChanged(configuration);
        }
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(null, R.styleable.SherlockActionBar, R.attr.actionBarStyle, 0);
        setContentHeight(obtainStyledAttributes.getLayoutDimension(R.styleable.SherlockActionBar_height, 0));
        obtainStyledAttributes.recycle();
        if (this.mSplitWhenNarrow) {
            setSplitActionBar(ResourcesCompat.getResources_getBoolean(getContext(), R.bool.abs__split_action_bar_is_narrow));
        }
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.onConfigurationChanged(configuration);
        }
    }

    public void setSplitActionBar(boolean z) {
        this.mSplitActionBar = z;
    }

    public void setSplitWhenNarrow(boolean z) {
        this.mSplitWhenNarrow = z;
    }

    public void setContentHeight(int i) {
        this.mContentHeight = i;
        requestLayout();
    }

    public int getContentHeight() {
        return this.mContentHeight;
    }

    public void setSplitView(ActionBarContainer actionBarContainer) {
        this.mSplitView = actionBarContainer;
    }

    public int getAnimatedVisibility() {
        return this.mVisibilityAnim != null ? this.mVisAnimListener.mFinalVisibility : getVisibility();
    }

    public void animateToVisibility(int i) {
        if (this.mVisibilityAnim != null) {
            this.mVisibilityAnim.cancel();
        }
        if (i == 0) {
            if (getVisibility() != 0) {
                setAlpha(0.0f);
                if (this.mSplitView != null && this.mMenuView != null) {
                    this.mMenuView.setAlpha(0.0f);
                }
            }
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "alpha", 1.0f);
            ofFloat.setDuration(200L);
            ofFloat.setInterpolator(sAlphaInterpolator);
            if (this.mSplitView != null && this.mMenuView != null) {
                AnimatorSet animatorSet = new AnimatorSet();
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.mMenuView, "alpha", 1.0f);
                ofFloat2.setDuration(200L);
                animatorSet.addListener(this.mVisAnimListener.withFinalVisibility(i));
                animatorSet.play(ofFloat).with(ofFloat2);
                animatorSet.start();
                return;
            }
            ofFloat.addListener(this.mVisAnimListener.withFinalVisibility(i));
            ofFloat.start();
            return;
        }
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this, "alpha", 0.0f);
        ofFloat3.setDuration(200L);
        ofFloat3.setInterpolator(sAlphaInterpolator);
        if (this.mSplitView != null && this.mMenuView != null) {
            AnimatorSet animatorSet2 = new AnimatorSet();
            ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.mMenuView, "alpha", 0.0f);
            ofFloat4.setDuration(200L);
            animatorSet2.addListener(this.mVisAnimListener.withFinalVisibility(i));
            animatorSet2.play(ofFloat3).with(ofFloat4);
            animatorSet2.start();
            return;
        }
        ofFloat3.addListener(this.mVisAnimListener.withFinalVisibility(i));
        ofFloat3.start();
    }

    @Override // com.actionbarsherlock.internal.nineoldandroids.view.NineViewGroup, android.view.View
    public void setVisibility(int i) {
        if (this.mVisibilityAnim != null) {
            this.mVisibilityAnim.end();
        }
        super.setVisibility(i);
    }

    public boolean showOverflowMenu() {
        if (this.mActionMenuPresenter != null) {
            return this.mActionMenuPresenter.showOverflowMenu();
        }
        return false;
    }

    public void postShowOverflowMenu() {
        post(new Runnable() { // from class: com.actionbarsherlock.internal.widget.AbsActionBarView.1
            @Override // java.lang.Runnable
            public void run() {
                AbsActionBarView.this.showOverflowMenu();
            }
        });
    }

    public boolean hideOverflowMenu() {
        if (this.mActionMenuPresenter != null) {
            return this.mActionMenuPresenter.hideOverflowMenu();
        }
        return false;
    }

    public boolean isOverflowMenuShowing() {
        if (this.mActionMenuPresenter != null) {
            return this.mActionMenuPresenter.isOverflowMenuShowing();
        }
        return false;
    }

    public boolean isOverflowReserved() {
        return this.mActionMenuPresenter != null && this.mActionMenuPresenter.isOverflowReserved();
    }

    public void dismissPopupMenus() {
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.dismissPopupMenus();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int measureChildView(View view, int i, int i2, int i3) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i, ExploreByTouchHelper.INVALID_ID), i2);
        return Math.max(0, (i - view.getMeasuredWidth()) - i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int positionChild(View view, int i, int i2, int i3) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i4 = ((i3 - measuredHeight) / 2) + i2;
        view.layout(i, i4, i + measuredWidth, measuredHeight + i4);
        return measuredWidth;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int positionChildInverse(View view, int i, int i2, int i3) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i4 = ((i3 - measuredHeight) / 2) + i2;
        view.layout(i - measuredWidth, i4, i, measuredHeight + i4);
        return measuredWidth;
    }

    /* loaded from: classes.dex */
    public class VisibilityAnimListener implements Animator.AnimatorListener {
        private boolean mCanceled = false;
        int mFinalVisibility;

        protected VisibilityAnimListener() {
        }

        public VisibilityAnimListener withFinalVisibility(int i) {
            this.mFinalVisibility = i;
            return this;
        }

        @Override // com.actionbarsherlock.internal.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            AbsActionBarView.this.setVisibility(0);
            AbsActionBarView.this.mVisibilityAnim = animator;
            this.mCanceled = false;
        }

        @Override // com.actionbarsherlock.internal.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (!this.mCanceled) {
                AbsActionBarView.this.mVisibilityAnim = null;
                AbsActionBarView.this.setVisibility(this.mFinalVisibility);
                if (AbsActionBarView.this.mSplitView != null && AbsActionBarView.this.mMenuView != null) {
                    AbsActionBarView.this.mMenuView.setVisibility(this.mFinalVisibility);
                }
            }
        }

        @Override // com.actionbarsherlock.internal.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.mCanceled = true;
        }

        @Override // com.actionbarsherlock.internal.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }
    }
}
