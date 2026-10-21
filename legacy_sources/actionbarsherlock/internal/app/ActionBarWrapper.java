package com.actionbarsherlock.internal.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.SpinnerAdapter;
import com.actionbarsherlock.app.ActionBar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public class ActionBarWrapper extends ActionBar implements ActionBar.OnMenuVisibilityListener, ActionBar.OnNavigationListener {
    private final android.app.ActionBar mActionBar;
    private final Activity mActivity;
    private FragmentTransaction mFragmentTransaction;
    private Set<ActionBar.OnMenuVisibilityListener> mMenuVisibilityListeners = new HashSet(1);
    private ActionBar.OnNavigationListener mNavigationListener;

    public ActionBarWrapper(Activity activity) {
        this.mActivity = activity;
        this.mActionBar = activity.getActionBar();
        if (this.mActionBar != null) {
            this.mActionBar.addOnMenuVisibilityListener(this);
        }
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setHomeButtonEnabled(boolean z) {
        this.mActionBar.setHomeButtonEnabled(z);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public Context getThemedContext() {
        return this.mActionBar.getThemedContext();
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setCustomView(View view) {
        this.mActionBar.setCustomView(view);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setCustomView(View view, ActionBar.LayoutParams layoutParams) {
        ActionBar.LayoutParams layoutParams2 = new ActionBar.LayoutParams(layoutParams);
        layoutParams2.gravity = layoutParams.gravity;
        layoutParams2.bottomMargin = layoutParams.bottomMargin;
        layoutParams2.topMargin = layoutParams.topMargin;
        layoutParams2.leftMargin = layoutParams.leftMargin;
        layoutParams2.rightMargin = layoutParams.rightMargin;
        this.mActionBar.setCustomView(view, layoutParams2);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setCustomView(int i) {
        this.mActionBar.setCustomView(i);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setIcon(int i) {
        this.mActionBar.setIcon(i);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setIcon(Drawable drawable) {
        this.mActionBar.setIcon(drawable);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setLogo(int i) {
        this.mActionBar.setLogo(i);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setLogo(Drawable drawable) {
        this.mActionBar.setLogo(drawable);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setListNavigationCallbacks(SpinnerAdapter spinnerAdapter, ActionBar.OnNavigationListener onNavigationListener) {
        this.mNavigationListener = onNavigationListener;
        android.app.ActionBar actionBar = this.mActionBar;
        if (onNavigationListener == null) {
            this = null;
        }
        actionBar.setListNavigationCallbacks(spinnerAdapter, this);
    }

    @Override // android.app.ActionBar.OnNavigationListener
    public boolean onNavigationItemSelected(int i, long j) {
        return this.mNavigationListener.onNavigationItemSelected(i, j);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setSelectedNavigationItem(int i) {
        this.mActionBar.setSelectedNavigationItem(i);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public int getSelectedNavigationIndex() {
        return this.mActionBar.getSelectedNavigationIndex();
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public int getNavigationItemCount() {
        return this.mActionBar.getNavigationItemCount();
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setTitle(CharSequence charSequence) {
        this.mActionBar.setTitle(charSequence);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setTitle(int i) {
        this.mActionBar.setTitle(i);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setSubtitle(CharSequence charSequence) {
        this.mActionBar.setSubtitle(charSequence);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setSubtitle(int i) {
        this.mActionBar.setSubtitle(i);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setDisplayOptions(int i) {
        this.mActionBar.setDisplayOptions(i);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setDisplayOptions(int i, int i2) {
        this.mActionBar.setDisplayOptions(i, i2);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setDisplayUseLogoEnabled(boolean z) {
        this.mActionBar.setDisplayUseLogoEnabled(z);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setDisplayShowHomeEnabled(boolean z) {
        this.mActionBar.setDisplayShowHomeEnabled(z);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setDisplayHomeAsUpEnabled(boolean z) {
        this.mActionBar.setDisplayHomeAsUpEnabled(z);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setDisplayShowTitleEnabled(boolean z) {
        this.mActionBar.setDisplayShowTitleEnabled(z);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setDisplayShowCustomEnabled(boolean z) {
        this.mActionBar.setDisplayShowCustomEnabled(z);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setBackgroundDrawable(Drawable drawable) {
        this.mActionBar.setBackgroundDrawable(drawable);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setStackedBackgroundDrawable(Drawable drawable) {
        this.mActionBar.setStackedBackgroundDrawable(drawable);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setSplitBackgroundDrawable(Drawable drawable) {
        this.mActionBar.setSplitBackgroundDrawable(drawable);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public View getCustomView() {
        return this.mActionBar.getCustomView();
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public CharSequence getTitle() {
        return this.mActionBar.getTitle();
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public CharSequence getSubtitle() {
        return this.mActionBar.getSubtitle();
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public int getNavigationMode() {
        return this.mActionBar.getNavigationMode();
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void setNavigationMode(int i) {
        this.mActionBar.setNavigationMode(i);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public int getDisplayOptions() {
        return this.mActionBar.getDisplayOptions();
    }

    /* loaded from: classes.dex */
    public class TabWrapper extends ActionBar.Tab implements ActionBar.TabListener {
        private ActionBar.TabListener mListener;
        final ActionBar.Tab mNativeTab;
        private Object mTag;

        public TabWrapper(ActionBar.Tab tab) {
            this.mNativeTab = tab;
            this.mNativeTab.setTag(this);
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public int getPosition() {
            return this.mNativeTab.getPosition();
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public Drawable getIcon() {
            return this.mNativeTab.getIcon();
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public CharSequence getText() {
            return this.mNativeTab.getText();
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public ActionBar.Tab setIcon(Drawable drawable) {
            this.mNativeTab.setIcon(drawable);
            return this;
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public ActionBar.Tab setIcon(int i) {
            this.mNativeTab.setIcon(i);
            return this;
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public ActionBar.Tab setText(CharSequence charSequence) {
            this.mNativeTab.setText(charSequence);
            return this;
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public ActionBar.Tab setText(int i) {
            this.mNativeTab.setText(i);
            return this;
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public ActionBar.Tab setCustomView(View view) {
            this.mNativeTab.setCustomView(view);
            return this;
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public ActionBar.Tab setCustomView(int i) {
            this.mNativeTab.setCustomView(i);
            return this;
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public View getCustomView() {
            return this.mNativeTab.getCustomView();
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public ActionBar.Tab setTag(Object obj) {
            this.mTag = obj;
            return this;
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public Object getTag() {
            return this.mTag;
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public ActionBar.Tab setTabListener(ActionBar.TabListener tabListener) {
            this.mNativeTab.setTabListener(tabListener != null ? this : null);
            this.mListener = tabListener;
            return this;
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public void select() {
            this.mNativeTab.select();
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public ActionBar.Tab setContentDescription(int i) {
            this.mNativeTab.setContentDescription(i);
            return this;
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public ActionBar.Tab setContentDescription(CharSequence charSequence) {
            this.mNativeTab.setContentDescription(charSequence);
            return this;
        }

        @Override // com.actionbarsherlock.app.ActionBar.Tab
        public CharSequence getContentDescription() {
            return this.mNativeTab.getContentDescription();
        }

        @Override // android.app.ActionBar.TabListener
        public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
            if (this.mListener != null) {
                FragmentTransaction fragmentTransaction2 = null;
                if (ActionBarWrapper.this.mActivity instanceof FragmentActivity) {
                    fragmentTransaction2 = ((FragmentActivity) ActionBarWrapper.this.mActivity).getSupportFragmentManager().beginTransaction().disallowAddToBackStack();
                }
                this.mListener.onTabReselected(this, fragmentTransaction2);
                if (fragmentTransaction2 != null && !fragmentTransaction2.isEmpty()) {
                    fragmentTransaction2.commit();
                }
            }
        }

        @Override // android.app.ActionBar.TabListener
        public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
            if (this.mListener != null) {
                if (ActionBarWrapper.this.mFragmentTransaction == null && (ActionBarWrapper.this.mActivity instanceof FragmentActivity)) {
                    ActionBarWrapper.this.mFragmentTransaction = ((FragmentActivity) ActionBarWrapper.this.mActivity).getSupportFragmentManager().beginTransaction().disallowAddToBackStack();
                }
                this.mListener.onTabSelected(this, ActionBarWrapper.this.mFragmentTransaction);
                if (ActionBarWrapper.this.mFragmentTransaction != null) {
                    if (!ActionBarWrapper.this.mFragmentTransaction.isEmpty()) {
                        ActionBarWrapper.this.mFragmentTransaction.commit();
                    }
                    ActionBarWrapper.this.mFragmentTransaction = null;
                }
            }
        }

        @Override // android.app.ActionBar.TabListener
        public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
            if (this.mListener != null) {
                FragmentTransaction fragmentTransaction2 = null;
                if (ActionBarWrapper.this.mActivity instanceof FragmentActivity) {
                    fragmentTransaction2 = ((FragmentActivity) ActionBarWrapper.this.mActivity).getSupportFragmentManager().beginTransaction().disallowAddToBackStack();
                    ActionBarWrapper.this.mFragmentTransaction = fragmentTransaction2;
                }
                this.mListener.onTabUnselected(this, fragmentTransaction2);
            }
        }
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public ActionBar.Tab newTab() {
        return new TabWrapper(this.mActionBar.newTab());
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void addTab(ActionBar.Tab tab) {
        this.mActionBar.addTab(((TabWrapper) tab).mNativeTab);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void addTab(ActionBar.Tab tab, boolean z) {
        this.mActionBar.addTab(((TabWrapper) tab).mNativeTab, z);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void addTab(ActionBar.Tab tab, int i) {
        this.mActionBar.addTab(((TabWrapper) tab).mNativeTab, i);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void addTab(ActionBar.Tab tab, int i, boolean z) {
        this.mActionBar.addTab(((TabWrapper) tab).mNativeTab, i, z);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void removeTab(ActionBar.Tab tab) {
        this.mActionBar.removeTab(((TabWrapper) tab).mNativeTab);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void removeTabAt(int i) {
        this.mActionBar.removeTabAt(i);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void removeAllTabs() {
        this.mActionBar.removeAllTabs();
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void selectTab(ActionBar.Tab tab) {
        this.mActionBar.selectTab(((TabWrapper) tab).mNativeTab);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public ActionBar.Tab getSelectedTab() {
        ActionBar.Tab selectedTab = this.mActionBar.getSelectedTab();
        if (selectedTab != null) {
            return (ActionBar.Tab) selectedTab.getTag();
        }
        return null;
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public ActionBar.Tab getTabAt(int i) {
        ActionBar.Tab tabAt = this.mActionBar.getTabAt(i);
        if (tabAt != null) {
            return (ActionBar.Tab) tabAt.getTag();
        }
        return null;
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public int getTabCount() {
        return this.mActionBar.getTabCount();
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public int getHeight() {
        return this.mActionBar.getHeight();
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void show() {
        this.mActionBar.show();
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void hide() {
        this.mActionBar.hide();
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public boolean isShowing() {
        return this.mActionBar.isShowing();
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void addOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener onMenuVisibilityListener) {
        this.mMenuVisibilityListeners.add(onMenuVisibilityListener);
    }

    @Override // com.actionbarsherlock.app.ActionBar
    public void removeOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener onMenuVisibilityListener) {
        this.mMenuVisibilityListeners.remove(onMenuVisibilityListener);
    }

    @Override // android.app.ActionBar.OnMenuVisibilityListener
    public void onMenuVisibilityChanged(boolean z) {
        Iterator<ActionBar.OnMenuVisibilityListener> it = this.mMenuVisibilityListeners.iterator();
        while (it.hasNext()) {
            it.next().onMenuVisibilityChanged(z);
        }
    }
}
