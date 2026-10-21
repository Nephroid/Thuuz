package com.actionbarsherlock.internal;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.actionbarsherlock.ActionBarSherlock;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.internal.app.ActionBarWrapper;
import com.actionbarsherlock.internal.view.menu.MenuWrapper;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.MenuInflater;

@ActionBarSherlock.Implementation(api = 14)
/* loaded from: classes.dex */
public class ActionBarSherlockNative extends ActionBarSherlock {
    private ActionBarWrapper mActionBar;
    private ActionModeWrapper mActionMode;
    private MenuWrapper mMenu;

    public ActionBarSherlockNative(Activity activity, int i) {
        super(activity, i);
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public ActionBar getActionBar() {
        initActionBar();
        return this.mActionBar;
    }

    private void initActionBar() {
        if (this.mActionBar == null && this.mActivity.getActionBar() != null) {
            this.mActionBar = new ActionBarWrapper(this.mActivity);
        }
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void dispatchInvalidateOptionsMenu() {
        this.mActivity.getWindow().invalidatePanelMenu(0);
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public boolean dispatchCreateOptionsMenu(Menu menu) {
        if (this.mMenu == null || menu != this.mMenu.unwrap()) {
            this.mMenu = new MenuWrapper(menu);
        }
        return callbackCreateOptionsMenu(this.mMenu);
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public boolean dispatchPrepareOptionsMenu(Menu menu) {
        return callbackPrepareOptionsMenu(this.mMenu);
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public boolean dispatchOptionsItemSelected(MenuItem menuItem) {
        return callbackOptionsItemSelected(this.mMenu.findItem(menuItem));
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public boolean hasFeature(int i) {
        return this.mActivity.getWindow().hasFeature(i);
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public boolean requestFeature(int i) {
        return this.mActivity.getWindow().requestFeature(i);
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void setUiOptions(int i) {
        this.mActivity.getWindow().setUiOptions(i);
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void setUiOptions(int i, int i2) {
        this.mActivity.getWindow().setUiOptions(i, i2);
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void setContentView(int i) {
        this.mActivity.getWindow().setContentView(i);
        initActionBar();
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        this.mActivity.getWindow().setContentView(view, layoutParams);
        initActionBar();
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        this.mActivity.getWindow().addContentView(view, layoutParams);
        initActionBar();
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void setTitle(CharSequence charSequence) {
        this.mActivity.getWindow().setTitle(charSequence);
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void setProgressBarVisibility(boolean z) {
        this.mActivity.setProgressBarVisibility(z);
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void setProgressBarIndeterminateVisibility(boolean z) {
        this.mActivity.setProgressBarIndeterminateVisibility(z);
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void setProgressBarIndeterminate(boolean z) {
        this.mActivity.setProgressBarIndeterminate(z);
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void setProgress(int i) {
        this.mActivity.setProgress(i);
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void setSecondaryProgress(int i) {
        this.mActivity.setSecondaryProgress(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.actionbarsherlock.ActionBarSherlock
    public Context getThemedContext() {
        Activity activity = this.mActivity;
        TypedValue typedValue = new TypedValue();
        this.mActivity.getTheme().resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
        return typedValue.resourceId != 0 ? new ContextThemeWrapper(activity, typedValue.resourceId) : activity;
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public ActionMode startActionMode(ActionMode.Callback callback) {
        if (this.mActionMode != null) {
            this.mActionMode.finish();
        }
        if (this.mActivity.startActionMode(callback != null ? new ActionModeCallbackWrapper(callback) : null) == null) {
            this.mActionMode = null;
        }
        if ((this.mActivity instanceof ActionBarSherlock.OnActionModeStartedListener) && this.mActionMode != null) {
            ((ActionBarSherlock.OnActionModeStartedListener) this.mActivity).onActionModeStarted(this.mActionMode);
        }
        return this.mActionMode;
    }

    /* loaded from: classes.dex */
    class ActionModeCallbackWrapper implements ActionMode.Callback {
        private final ActionMode.Callback mCallback;

        public ActionModeCallbackWrapper(ActionMode.Callback callback) {
            this.mCallback = callback;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(android.view.ActionMode actionMode, Menu menu) {
            ActionBarSherlockNative.this.mActionMode = new ActionModeWrapper(actionMode);
            return this.mCallback.onCreateActionMode(ActionBarSherlockNative.this.mActionMode, ActionBarSherlockNative.this.mActionMode.getMenu());
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(android.view.ActionMode actionMode, Menu menu) {
            return this.mCallback.onPrepareActionMode(ActionBarSherlockNative.this.mActionMode, ActionBarSherlockNative.this.mActionMode.getMenu());
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(android.view.ActionMode actionMode, MenuItem menuItem) {
            return this.mCallback.onActionItemClicked(ActionBarSherlockNative.this.mActionMode, ActionBarSherlockNative.this.mActionMode.getMenu().findItem(menuItem));
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(android.view.ActionMode actionMode) {
            this.mCallback.onDestroyActionMode(ActionBarSherlockNative.this.mActionMode);
            if (ActionBarSherlockNative.this.mActivity instanceof ActionBarSherlock.OnActionModeFinishedListener) {
                ((ActionBarSherlock.OnActionModeFinishedListener) ActionBarSherlockNative.this.mActivity).onActionModeFinished(ActionBarSherlockNative.this.mActionMode);
            }
        }
    }

    /* loaded from: classes.dex */
    class ActionModeWrapper extends com.actionbarsherlock.view.ActionMode {
        private final android.view.ActionMode mActionMode;
        private MenuWrapper mMenu = null;

        ActionModeWrapper(android.view.ActionMode actionMode) {
            this.mActionMode = actionMode;
        }

        @Override // com.actionbarsherlock.view.ActionMode
        public void setTitle(CharSequence charSequence) {
            this.mActionMode.setTitle(charSequence);
        }

        @Override // com.actionbarsherlock.view.ActionMode
        public void setTitle(int i) {
            this.mActionMode.setTitle(i);
        }

        @Override // com.actionbarsherlock.view.ActionMode
        public void setSubtitle(CharSequence charSequence) {
            this.mActionMode.setSubtitle(charSequence);
        }

        @Override // com.actionbarsherlock.view.ActionMode
        public void setSubtitle(int i) {
            this.mActionMode.setSubtitle(i);
        }

        @Override // com.actionbarsherlock.view.ActionMode
        public void setCustomView(View view) {
            this.mActionMode.setCustomView(view);
        }

        @Override // com.actionbarsherlock.view.ActionMode
        public void invalidate() {
            this.mActionMode.invalidate();
        }

        @Override // com.actionbarsherlock.view.ActionMode
        public void finish() {
            this.mActionMode.finish();
        }

        @Override // com.actionbarsherlock.view.ActionMode
        public MenuWrapper getMenu() {
            if (this.mMenu == null) {
                this.mMenu = new MenuWrapper(this.mActionMode.getMenu());
            }
            return this.mMenu;
        }

        @Override // com.actionbarsherlock.view.ActionMode
        public CharSequence getTitle() {
            return this.mActionMode.getTitle();
        }

        @Override // com.actionbarsherlock.view.ActionMode
        public CharSequence getSubtitle() {
            return this.mActionMode.getSubtitle();
        }

        @Override // com.actionbarsherlock.view.ActionMode
        public View getCustomView() {
            return this.mActionMode.getCustomView();
        }

        @Override // com.actionbarsherlock.view.ActionMode
        public MenuInflater getMenuInflater() {
            return ActionBarSherlockNative.this.getMenuInflater();
        }

        @Override // com.actionbarsherlock.view.ActionMode
        public void setTag(Object obj) {
            this.mActionMode.setTag(obj);
        }

        @Override // com.actionbarsherlock.view.ActionMode
        public Object getTag() {
            return this.mActionMode.getTag();
        }
    }
}
