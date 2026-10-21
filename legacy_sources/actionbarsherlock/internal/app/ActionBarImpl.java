package com.actionbarsherlock.internal.app;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.internal.ResourcesCompat;
import com.actionbarsherlock.internal.nineoldandroids.animation.Animator;
import com.actionbarsherlock.internal.nineoldandroids.animation.AnimatorListenerAdapter;
import com.actionbarsherlock.internal.nineoldandroids.animation.AnimatorSet;
import com.actionbarsherlock.internal.nineoldandroids.animation.ObjectAnimator;
import com.actionbarsherlock.internal.nineoldandroids.widget.NineFrameLayout;
import com.actionbarsherlock.internal.view.menu.MenuBuilder;
import com.actionbarsherlock.internal.view.menu.MenuPopupHelper;
import com.actionbarsherlock.internal.view.menu.SubMenuBuilder;
import com.actionbarsherlock.internal.widget.ActionBarContainer;
import com.actionbarsherlock.internal.widget.ActionBarContextView;
import com.actionbarsherlock.internal.widget.ActionBarView;
import com.actionbarsherlock.internal.widget.ScrollingTabContainerView;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ActionBarImpl extends ActionBar {
    private static final int CONTEXT_DISPLAY_NORMAL = 0;
    private static final int CONTEXT_DISPLAY_SPLIT = 1;
    private static final int INVALID_POSITION = -1;
    ActionModeImpl mActionMode;
    private ActionBarView mActionView;
    private Activity mActivity;
    private ActionBarContainer mContainerView;
    private NineFrameLayout mContentView;
    private Context mContext;
    private int mContextDisplayMode;
    private ActionBarContextView mContextView;
    private Animator mCurrentModeAnim;
    private Animator mCurrentShowAnim;
    ActionMode mDeferredDestroyActionMode;
    ActionMode.Callback mDeferredModeDestroyCallback;
    private boolean mHasEmbeddedTabs;
    private boolean mLastMenuVisibility;
    private TabImpl mSelectedTab;
    private boolean mShowHideAnimationEnabled;
    private ActionBarContainer mSplitView;
    private ScrollingTabContainerView mTabScrollView;
    Runnable mTabSelector;
    private Context mThemedContext;
    boolean mWasHiddenBeforeMode;
    private ArrayList<TabImpl> mTabs = new ArrayList<>();
    private int mSavedTabPosition = -1;
    private ArrayList<ActionBar.OnMenuVisibilityListener> mMenuVisibilityListeners = new ArrayList<>();
    final Handler mHandler = new Handler();
    final Animator.AnimatorListener mHideListener = new AnimatorListenerAdapter() { // from class: com.actionbarsherlock.internal.app.ActionBarImpl.1
        @Override // com.actionbarsherlock.internal.nineoldandroids.animation.AnimatorListenerAdapter, com.actionbarsherlock.internal.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (ActionBarImpl.this.mContentView != null) {
                ActionBarImpl.this.mContentView.setTranslationY(0.0f);
                ActionBarImpl.this.mContainerView.setTranslationY(0.0f);
            }
            if (ActionBarImpl.this.mSplitView != null && ActionBarImpl.this.mContextDisplayMode == 1) {
                ActionBarImpl.this.mSplitView.setVisibility(8);
            }
            ActionBarImpl.this.mContainerView.setVisibility(8);
            ActionBarImpl.this.mContainerView.setTransitioning(false);
            ActionBarImpl.this.mCurrentShowAnim = null;
            ActionBarImpl.this.completeDeferredDestroyActionMode();
        }
    };
    final Animator.AnimatorListener mShowListener = new AnimatorListenerAdapter() { // from class: com.actionbarsherlock.internal.app.ActionBarImpl.2
        @Override // com.actionbarsherlock.internal.nineoldandroids.animation.AnimatorListenerAdapter, com.actionbarsherlock.internal.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            ActionBarImpl.this.mCurrentShowAnim = null;
            ActionBarImpl.this.mContainerView.requestLayout();
        }
    };

    public ActionBarImpl(Activity activity, int i) {
        this.mActivity = activity;
        View decorView = activity.getWindow().getDecorView();
        init(decorView);
        if ((i & 512) == 0) {
            this.mContentView = (NineFrameLayout) decorView.findViewById(R.id.content);
        }
    }

    public ActionBarImpl(Dialog dialog) {
        init(dialog.getWindow().getDecorView());
    }

    private void init(View view) {
        this.mContext = view.getContext();
        this.mActionView = (ActionBarView) view.findViewById(com.actionbarsherlock.R.id.abs__action_bar);
        this.mContextView = (ActionBarContextView) view.findViewById(com.actionbarsherlock.R.id.abs__action_context_bar);
        this.mContainerView = (ActionBarContainer) view.findViewById(com.actionbarsherlock.R.id.abs__action_bar_container);
        this.mSplitView = (ActionBarContainer) view.findViewById(com.actionbarsherlock.R.id.abs__split_action_bar);
        if (this.mActionView == null || this.mContextView == null || this.mContainerView == null) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.mActionView.setContextView(this.mContextView);
        this.mContextDisplayMode = this.mActionView.isSplitActionBar() ? 1 : 0;
        setHomeButtonEnabled(this.mContext.getApplicationInfo().targetSdkVersion < 14);
        setHasEmbeddedTabs(ResourcesCompat.getResources_getBoolean(this.mContext, com.actionbarsherlock.R.bool.abs__action_bar_embed_tabs));
    }

    public void onConfigurationChanged(Configuration configuration) {
        setHasEmbeddedTabs(ResourcesCompat.getResources_getBoolean(this.mContext, com.actionbarsherlock.R.bool.abs__action_bar_embed_tabs));
        if (Build.VERSION.SDK_INT < 8) {
            this.mActionView.onConfigurationChanged(configuration);
            if (this.mContextView != null) {
                this.mContextView.onConfigurationChanged(configuration);
            }
        }
    }

    private void setHasEmbeddedTabs(boolean z) {
        this.mHasEmbeddedTabs = z;
        if (!this.mHasEmbeddedTabs) {
            this.mActionView.setEmbeddedTabView(null);
            this.mContainerView.setTabContainer(this.mTabScrollView);
        } else {
            this.mContainerView.setTabContainer(null);
            this.mActionView.setEmbeddedTabView(this.mTabScrollView);
        }
        boolean z2 = getNavigationMode() == 2;
        if (this.mTabScrollView != null) {
            this.mTabScrollView.setVisibility(z2 ? 0 : 8);
        }
        this.mActionView.setCollapsable(!this.mHasEmbeddedTabs && z2);
    }

    private void ensureTabsExist() {
        if (this.mTabScrollView == null) {
            ScrollingTabContainerView scrollingTabContainerView = new ScrollingTabContainerView(this.mContext);
            if (this.mHasEmbeddedTabs) {
                scrollingTabContainerView.setVisibility(0);
                this.mActionView.setEmbeddedTabView(scrollingTabContainerView);
            } else {
                scrollingTabContainerView.setVisibility(getNavigationMode() != 2 ? 8 : 0);
                this.mContainerView.setTabContainer(scrollingTabContainerView);
            }
            this.mTabScrollView = scrollingTabContainerView;
        }
    }

    void completeDeferredDestroyActionMode() {
        if (this.mDeferredModeDestroyCallback != null) {
            this.mDeferredModeDestroyCallback.onDestroyActionMode(this.mDeferredDestroyActionMode);
            this.mDeferredDestroyActionMode = null;
            this.mDeferredModeDestroyCallback = null;
        }
    }

    public void setShowHideAnimationEnabled(boolean z) {
        this.mShowHideAnimationEnabled = z;
        if (!z && this.mCurrentShowAnim != null) {
            this.mCurrentShowAnim.end();
        }
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void addOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener onMenuVisibilityListener) {
        this.mMenuVisibilityListeners.add(onMenuVisibilityListener);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void removeOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener onMenuVisibilityListener) {
        this.mMenuVisibilityListeners.remove(onMenuVisibilityListener);
    }

    public void dispatchMenuVisibilityChanged(boolean z) {
        if (z != this.mLastMenuVisibility) {
            this.mLastMenuVisibility = z;
            int size = this.mMenuVisibilityListeners.size();
            for (int i = 0; i < size; i++) {
                this.mMenuVisibilityListeners.get(i).onMenuVisibilityChanged(z);
            }
        }
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setCustomView(int i) {
        setCustomView(LayoutInflater.from(getThemedContext()).inflate(i, (ViewGroup) this.mActionView, false));
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setDisplayUseLogoEnabled(boolean z) {
        setDisplayOptions(z ? 1 : 0, 1);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setDisplayShowHomeEnabled(boolean z) {
        setDisplayOptions(z ? 2 : 0, 2);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setDisplayHomeAsUpEnabled(boolean z) {
        setDisplayOptions(z ? 4 : 0, 4);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setDisplayShowTitleEnabled(boolean z) {
        setDisplayOptions(z ? 8 : 0, 8);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setDisplayShowCustomEnabled(boolean z) {
        setDisplayOptions(z ? 16 : 0, 16);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setHomeButtonEnabled(boolean z) {
        this.mActionView.setHomeButtonEnabled(z);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setTitle(int i) {
        setTitle(this.mContext.getString(i));
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setSubtitle(int i) {
        setSubtitle(this.mContext.getString(i));
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setSelectedNavigationItem(int i) {
        switch (this.mActionView.getNavigationMode()) {
            case 1:
                this.mActionView.setDropdownSelectedPosition(i);
                return;
            case 2:
                selectTab(this.mTabs.get(i));
                return;
            default:
                throw new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");
        }
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void removeAllTabs() {
        cleanupTabs();
    }

    private void cleanupTabs() {
        if (this.mSelectedTab != null) {
            selectTab(null);
        }
        this.mTabs.clear();
        if (this.mTabScrollView != null) {
            this.mTabScrollView.removeAllTabs();
        }
        this.mSavedTabPosition = -1;
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setTitle(CharSequence charSequence) {
        this.mActionView.setTitle(charSequence);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setSubtitle(CharSequence charSequence) {
        this.mActionView.setSubtitle(charSequence);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setDisplayOptions(int i) {
        this.mActionView.setDisplayOptions(i);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setDisplayOptions(int i, int i2) {
        this.mActionView.setDisplayOptions((this.mActionView.getDisplayOptions() & (i2 ^ (-1))) | (i & i2));
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setBackgroundDrawable(Drawable drawable) {
        this.mContainerView.setPrimaryBackground(drawable);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setStackedBackgroundDrawable(Drawable drawable) {
        this.mContainerView.setStackedBackground(drawable);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setSplitBackgroundDrawable(Drawable drawable) {
        if (this.mSplitView != null) {
            this.mSplitView.setSplitBackground(drawable);
        }
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public View getCustomView() {
        return this.mActionView.getCustomNavigationView();
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public CharSequence getTitle() {
        return this.mActionView.getTitle();
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public CharSequence getSubtitle() {
        return this.mActionView.getSubtitle();
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public int getNavigationMode() {
        return this.mActionView.getNavigationMode();
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public int getDisplayOptions() {
        return this.mActionView.getDisplayOptions();
    }

    public ActionMode startActionMode(ActionMode.Callback callback) {
        boolean z;
        if (this.mActionMode != null) {
            z = this.mWasHiddenBeforeMode;
            this.mActionMode.finish();
        } else {
            z = false;
        }
        this.mContextView.killMode();
        ActionModeImpl actionModeImpl = new ActionModeImpl(callback);
        if (actionModeImpl.dispatchOnCreate()) {
            this.mWasHiddenBeforeMode = !isShowing() || z;
            actionModeImpl.invalidate();
            this.mContextView.initForMode(actionModeImpl);
            animateToMode(true);
            if (this.mSplitView != null && this.mContextDisplayMode == 1) {
                this.mSplitView.setVisibility(0);
            }
            this.mContextView.sendAccessibilityEvent(32);
            this.mActionMode = actionModeImpl;
            return actionModeImpl;
        }
        return null;
    }

    private void configureTab(ActionBar.Tab tab, int i) {
        TabImpl tabImpl = (TabImpl) tab;
        if (tabImpl.getCallback() == null) {
            throw new IllegalStateException("Action Bar Tab must have a Callback");
        }
        tabImpl.setPosition(i);
        this.mTabs.add(i, tabImpl);
        int size = this.mTabs.size();
        for (int i2 = i + 1; i2 < size; i2++) {
            this.mTabs.get(i2).setPosition(i2);
        }
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void addTab(ActionBar.Tab tab) {
        addTab(tab, this.mTabs.isEmpty());
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void addTab(ActionBar.Tab tab, int i) {
        addTab(tab, i, this.mTabs.isEmpty());
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void addTab(ActionBar.Tab tab, boolean z) {
        ensureTabsExist();
        this.mTabScrollView.addTab(tab, z);
        configureTab(tab, this.mTabs.size());
        if (z) {
            selectTab(tab);
        }
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void addTab(ActionBar.Tab tab, int i, boolean z) {
        ensureTabsExist();
        this.mTabScrollView.addTab(tab, i, z);
        configureTab(tab, i);
        if (z) {
            selectTab(tab);
        }
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public ActionBar.Tab newTab() {
        return new TabImpl();
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void removeTab(ActionBar.Tab tab) {
        removeTabAt(tab.getPosition());
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void removeTabAt(int i) {
        if (this.mTabScrollView != null) {
            int position = this.mSelectedTab != null ? this.mSelectedTab.getPosition() : this.mSavedTabPosition;
            this.mTabScrollView.removeTabAt(i);
            TabImpl remove = this.mTabs.remove(i);
            if (remove != null) {
                remove.setPosition(-1);
            }
            int size = this.mTabs.size();
            for (int i2 = i; i2 < size; i2++) {
                this.mTabs.get(i2).setPosition(i2);
            }
            if (position == i) {
                selectTab(this.mTabs.isEmpty() ? null : this.mTabs.get(Math.max(0, i - 1)));
            }
        }
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void selectTab(ActionBar.Tab tab) {
        if (getNavigationMode() != 2) {
            this.mSavedTabPosition = tab != null ? tab.getPosition() : -1;
            return;
        }
        FragmentTransaction fragmentTransaction = null;
        if (this.mActivity instanceof FragmentActivity) {
            fragmentTransaction = ((FragmentActivity) this.mActivity).getSupportFragmentManager().beginTransaction().disallowAddToBackStack();
        }
        if (this.mSelectedTab == tab) {
            if (this.mSelectedTab != null) {
                this.mSelectedTab.getCallback().onTabReselected(this.mSelectedTab, fragmentTransaction);
                this.mTabScrollView.animateToTab(tab.getPosition());
            }
        } else {
            this.mTabScrollView.setTabSelected(tab != null ? tab.getPosition() : -1);
            if (this.mSelectedTab != null) {
                this.mSelectedTab.getCallback().onTabUnselected(this.mSelectedTab, fragmentTransaction);
            }
            this.mSelectedTab = (TabImpl) tab;
            if (this.mSelectedTab != null) {
                this.mSelectedTab.getCallback().onTabSelected(this.mSelectedTab, fragmentTransaction);
            }
        }
        if (fragmentTransaction != null && !fragmentTransaction.isEmpty()) {
            fragmentTransaction.commit();
        }
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public ActionBar.Tab getSelectedTab() {
        return this.mSelectedTab;
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public int getHeight() {
        return this.mContainerView.getHeight();
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void show() {
        show(true);
    }

    void show(boolean z) {
        if (this.mCurrentShowAnim != null) {
            this.mCurrentShowAnim.end();
        }
        if (this.mContainerView.getVisibility() == 0) {
            if (z) {
                this.mWasHiddenBeforeMode = false;
                return;
            }
            return;
        }
        this.mContainerView.setVisibility(0);
        if (this.mShowHideAnimationEnabled) {
            this.mContainerView.setAlpha(0.0f);
            AnimatorSet animatorSet = new AnimatorSet();
            AnimatorSet.Builder play = animatorSet.play(ObjectAnimator.ofFloat(this.mContainerView, "alpha", 1.0f));
            if (this.mContentView != null) {
                play.with(ObjectAnimator.ofFloat(this.mContentView, "translationY", -this.mContainerView.getHeight(), 0.0f));
                this.mContainerView.setTranslationY(-this.mContainerView.getHeight());
                play.with(ObjectAnimator.ofFloat(this.mContainerView, "translationY", 0.0f));
            }
            if (this.mSplitView != null && this.mContextDisplayMode == 1) {
                this.mSplitView.setAlpha(0.0f);
                this.mSplitView.setVisibility(0);
                play.with(ObjectAnimator.ofFloat(this.mSplitView, "alpha", 1.0f));
            }
            animatorSet.addListener(this.mShowListener);
            this.mCurrentShowAnim = animatorSet;
            animatorSet.start();
            return;
        }
        this.mContainerView.setAlpha(1.0f);
        this.mContainerView.setTranslationY(0.0f);
        this.mShowListener.onAnimationEnd(null);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void hide() {
        if (this.mCurrentShowAnim != null) {
            this.mCurrentShowAnim.end();
        }
        if (this.mContainerView.getVisibility() != 8) {
            if (this.mShowHideAnimationEnabled) {
                this.mContainerView.setAlpha(1.0f);
                this.mContainerView.setTransitioning(true);
                AnimatorSet animatorSet = new AnimatorSet();
                AnimatorSet.Builder play = animatorSet.play(ObjectAnimator.ofFloat(this.mContainerView, "alpha", 0.0f));
                if (this.mContentView != null) {
                    play.with(ObjectAnimator.ofFloat(this.mContentView, "translationY", 0.0f, -this.mContainerView.getHeight()));
                    play.with(ObjectAnimator.ofFloat(this.mContainerView, "translationY", -this.mContainerView.getHeight()));
                }
                if (this.mSplitView != null && this.mSplitView.getVisibility() == 0) {
                    this.mSplitView.setAlpha(1.0f);
                    play.with(ObjectAnimator.ofFloat(this.mSplitView, "alpha", 0.0f));
                }
                animatorSet.addListener(this.mHideListener);
                this.mCurrentShowAnim = animatorSet;
                animatorSet.start();
                return;
            }
            this.mHideListener.onAnimationEnd(null);
        }
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public boolean isShowing() {
        return this.mContainerView.getVisibility() == 0;
    }

    void animateToMode(boolean z) {
        if (z) {
            show(false);
        }
        if (this.mCurrentModeAnim != null) {
            this.mCurrentModeAnim.end();
        }
        this.mActionView.animateToVisibility(z ? 8 : 0);
        this.mContextView.animateToVisibility(z ? 0 : 8);
        if (this.mTabScrollView != null && !this.mActionView.hasEmbeddedTabs() && this.mActionView.isCollapsed()) {
            this.mTabScrollView.animateToVisibility(z ? 8 : 0);
        }
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public Context getThemedContext() {
        if (this.mThemedContext == null) {
            TypedValue typedValue = new TypedValue();
            this.mContext.getTheme().resolveAttribute(com.actionbarsherlock.R.attr.actionBarWidgetTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                this.mThemedContext = new ContextThemeWrapper(this.mContext, i);
            } else {
                this.mThemedContext = this.mContext;
            }
        }
        return this.mThemedContext;
    }

    /* loaded from: classes.dex */
    public class ActionModeImpl extends ActionMode implements MenuBuilder.Callback {
        private ActionMode.Callback mCallback;
        private WeakReference<View> mCustomView;
        private MenuBuilder mMenu;

        public ActionModeImpl(ActionMode.Callback callback) {
            this.mCallback = callback;
            this.mMenu = new MenuBuilder(ActionBarImpl.this.getThemedContext()).setDefaultShowAsAction(1);
            this.mMenu.setCallback(this);
        }

        @Override // com.actionbarsherlock.view.ActionMode
        public MenuInflater getMenuInflater() {
            return new MenuInflater(ActionBarImpl.this.getThemedContext());
        }

        @Override // com.actionbarsherlock.view.ActionMode
        public Menu getMenu() {
            return this.mMenu;
        }

        @Override // com.actionbarsherlock.view.ActionMode
        public void finish() {
            if (ActionBarImpl.this.mActionMode == this) {
                if (ActionBarImpl.this.mWasHiddenBeforeMode) {
                    ActionBarImpl.this.mDeferredDestroyActionMode = this;
                    ActionBarImpl.this.mDeferredModeDestroyCallback = this.mCallback;
                } else {
                    this.mCallback.onDestroyActionMode(this);
                }
                this.mCallback = null;
                ActionBarImpl.this.animateToMode(false);
                ActionBarImpl.this.mContextView.closeMode();
                ActionBarImpl.this.mActionView.sendAccessibilityEvent(32);
                ActionBarImpl.this.mActionMode = null;
                if (ActionBarImpl.this.mWasHiddenBeforeMode) {
                    ActionBarImpl.this.hide();
                }
            }
        }

        @Override // com.actionbarsherlock.view.ActionMode
        public void invalidate() {
            this.mMenu.stopDispatchingItemsChanged();
            try {
                this.mCallback.onPrepareActionMode(this, this.mMenu);
            } finally {
                this.mMenu.startDispatchingItemsChanged();
            }
        }

        public boolean dispatchOnCreate() {
            this.mMenu.stopDispatchingItemsChanged();
            try {
                return this.mCallback.onCreateActionMode(this, this.mMenu);
            } finally {
                this.mMenu.startDispatchingItemsChanged();
            }
        }

        @Override // com.actionbarsherlock.view.ActionMode
        public void setCustomView(View view) {
            ActionBarImpl.this.mContextView.setCustomView(view);
            this.mCustomView = new WeakReference<>(view);
        }

        @Override // com.actionbarsherlock.view.ActionMode
        public void setSubtitle(CharSequence charSequence) {
            ActionBarImpl.this.mContextView.setSubtitle(charSequence);
        }

        @Override // com.actionbarsherlock.view.ActionMode
        public void setTitle(CharSequence charSequence) {
            ActionBarImpl.this.mContextView.setTitle(charSequence);
        }

        @Override // com.actionbarsherlock.view.ActionMode
        public void setTitle(int i) {
            setTitle(ActionBarImpl.this.mContext.getResources().getString(i));
        }

        @Override // com.actionbarsherlock.view.ActionMode
        public void setSubtitle(int i) {
            setSubtitle(ActionBarImpl.this.mContext.getResources().getString(i));
        }

        @Override // com.actionbarsherlock.view.ActionMode
        public CharSequence getTitle() {
            return ActionBarImpl.this.mContextView.getTitle();
        }

        @Override // com.actionbarsherlock.view.ActionMode
        public CharSequence getSubtitle() {
            return ActionBarImpl.this.mContextView.getSubtitle();
        }

        @Override // com.actionbarsherlock.view.ActionMode
        public View getCustomView() {
            if (this.mCustomView != null) {
                return this.mCustomView.get();
            }
            return null;
        }

        @Override // com.actionbarsherlock.internal.view.menu.MenuBuilder.Callback
        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            if (this.mCallback != null) {
                return this.mCallback.onActionItemClicked(this, menuItem);
            }
            return false;
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        }

        public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
            if (this.mCallback == null) {
                return false;
            }
            if (!subMenuBuilder.hasVisibleItems()) {
                return true;
            }
            new MenuPopupHelper(ActionBarImpl.this.getThemedContext(), subMenuBuilder).show();
            return true;
        }

        public void onCloseSubMenu(SubMenuBuilder subMenuBuilder) {
        }

        @Override // com.actionbarsherlock.internal.view.menu.MenuBuilder.Callback
        public void onMenuModeChange(MenuBuilder menuBuilder) {
            if (this.mCallback != null) {
                invalidate();
                ActionBarImpl.this.mContextView.showOverflowMenu();
            }
        }
    }

    /* loaded from: classes.dex */
    public class TabImpl extends ActionBar.Tab {
        private ActionBar.TabListener mCallback;
        private CharSequence mContentDesc;
        private View mCustomView;
        private Drawable mIcon;
        private int mPosition = -1;
        private Object mTag;
        private CharSequence mText;

        public TabImpl() {
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public Object getTag() {
            return this.mTag;
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public ActionBar.Tab setTag(Object obj) {
            this.mTag = obj;
            return this;
        }

        public ActionBar.TabListener getCallback() {
            return this.mCallback;
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public ActionBar.Tab setTabListener(ActionBar.TabListener tabListener) {
            this.mCallback = tabListener;
            return this;
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public View getCustomView() {
            return this.mCustomView;
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public ActionBar.Tab setCustomView(View view) {
            this.mCustomView = view;
            if (this.mPosition >= 0) {
                ActionBarImpl.this.mTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public ActionBar.Tab setCustomView(int i) {
            return setCustomView(LayoutInflater.from(ActionBarImpl.this.getThemedContext()).inflate(i, (ViewGroup) null));
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public Drawable getIcon() {
            return this.mIcon;
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public int getPosition() {
            return this.mPosition;
        }

        public void setPosition(int i) {
            this.mPosition = i;
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public CharSequence getText() {
            return this.mText;
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public ActionBar.Tab setIcon(Drawable drawable) {
            this.mIcon = drawable;
            if (this.mPosition >= 0) {
                ActionBarImpl.this.mTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public ActionBar.Tab setIcon(int i) {
            return setIcon(ActionBarImpl.this.mContext.getResources().getDrawable(i));
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public ActionBar.Tab setText(CharSequence charSequence) {
            this.mText = charSequence;
            if (this.mPosition >= 0) {
                ActionBarImpl.this.mTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public ActionBar.Tab setText(int i) {
            return setText(ActionBarImpl.this.mContext.getResources().getText(i));
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public void select() {
            ActionBarImpl.this.selectTab(this);
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public ActionBar.Tab setContentDescription(int i) {
            return setContentDescription(ActionBarImpl.this.mContext.getResources().getText(i));
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public ActionBar.Tab setContentDescription(CharSequence charSequence) {
            this.mContentDesc = charSequence;
            if (this.mPosition >= 0) {
                ActionBarImpl.this.mTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public CharSequence getContentDescription() {
            return this.mContentDesc;
        }
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setCustomView(View view) {
        this.mActionView.setCustomNavigationView(view);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setCustomView(View view, ActionBar.LayoutParams layoutParams) {
        view.setLayoutParams(layoutParams);
        this.mActionView.setCustomNavigationView(view);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setListNavigationCallbacks(SpinnerAdapter spinnerAdapter, ActionBar.OnNavigationListener onNavigationListener) {
        this.mActionView.setDropdownAdapter(spinnerAdapter);
        this.mActionView.setCallback(onNavigationListener);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public int getSelectedNavigationIndex() {
        switch (this.mActionView.getNavigationMode()) {
            case 1:
                return this.mActionView.getDropdownSelectedPosition();
            case 2:
                if (this.mSelectedTab != null) {
                    return this.mSelectedTab.getPosition();
                }
                return -1;
            default:
                return -1;
        }
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public int getNavigationItemCount() {
        switch (this.mActionView.getNavigationMode()) {
            case 1:
                SpinnerAdapter dropdownAdapter = this.mActionView.getDropdownAdapter();
                if (dropdownAdapter != null) {
                    return dropdownAdapter.getCount();
                }
                return 0;
            case 2:
                return this.mTabs.size();
            default:
                return 0;
        }
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public int getTabCount() {
        return this.mTabs.size();
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setNavigationMode(int i) {
        boolean z = false;
        switch (this.mActionView.getNavigationMode()) {
            case 2:
                this.mSavedTabPosition = getSelectedNavigationIndex();
                selectTab(null);
                this.mTabScrollView.setVisibility(8);
                break;
        }
        this.mActionView.setNavigationMode(i);
        switch (i) {
            case 2:
                ensureTabsExist();
                this.mTabScrollView.setVisibility(0);
                if (this.mSavedTabPosition != -1) {
                    setSelectedNavigationItem(this.mSavedTabPosition);
                    this.mSavedTabPosition = -1;
                    break;
                }
                break;
        }
        ActionBarView actionBarView = this.mActionView;
        if (i == 2 && !this.mHasEmbeddedTabs) {
            z = true;
        }
        actionBarView.setCollapsable(z);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public ActionBar.Tab getTabAt(int i) {
        return this.mTabs.get(i);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setIcon(int i) {
        this.mActionView.setIcon(i);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setIcon(Drawable drawable) {
        this.mActionView.setIcon(drawable);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setLogo(int i) {
        this.mActionView.setLogo(i);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setLogo(Drawable drawable) {
        this.mActionView.setLogo(drawable);
    }
}
