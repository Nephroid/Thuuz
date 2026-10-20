package com.actionbarsherlock.app;

import android.app.ExpandableListActivity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.actionbarsherlock.ActionBarSherlock;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.MenuInflater;

/* loaded from: classes.dex */
public abstract class SherlockExpandableListActivity extends ExpandableListActivity implements ActionBarSherlock.OnActionModeFinishedListener, ActionBarSherlock.OnActionModeStartedListener, ActionBarSherlock.OnCreatePanelMenuListener, ActionBarSherlock.OnMenuItemSelectedListener, ActionBarSherlock.OnPreparePanelListener {
    private ActionBarSherlock mSherlock;

    protected final ActionBarSherlock getSherlock() {
        if (this.mSherlock == null) {
            this.mSherlock = ActionBarSherlock.wrap(this, 1);
        }
        return this.mSherlock;
    }

    public ActionBar getSupportActionBar() {
        return getSherlock().getActionBar();
    }

    public ActionMode startActionMode(ActionMode.Callback callback) {
        return getSherlock().startActionMode(callback);
    }

    @Override // com.actionbarsherlock.ActionBarSherlock.OnActionModeStartedListener
    public void onActionModeStarted(ActionMode actionMode) {
    }

    @Override // com.actionbarsherlock.ActionBarSherlock.OnActionModeFinishedListener
    public void onActionModeFinished(ActionMode actionMode) {
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        getSherlock().dispatchConfigurationChanged(configuration);
    }

    @Override // android.app.Activity
    protected void onPostResume() {
        super.onPostResume();
        getSherlock().dispatchPostResume();
    }

    @Override // android.app.Activity
    protected void onPause() {
        getSherlock().dispatchPause();
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onStop() {
        getSherlock().dispatchStop();
        super.onStop();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        getSherlock().dispatchDestroy();
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onPostCreate(Bundle bundle) {
        getSherlock().dispatchPostCreate(bundle);
        super.onPostCreate(bundle);
    }

    @Override // android.app.Activity
    protected void onTitleChanged(CharSequence charSequence, int i) {
        getSherlock().dispatchTitleChanged(charSequence, i);
        super.onTitleChanged(charSequence, i);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean onMenuOpened(int i, Menu menu) {
        if (getSherlock().dispatchMenuOpened(i, menu)) {
            return true;
        }
        return super.onMenuOpened(i, menu);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        getSherlock().dispatchPanelClosed(i, menu);
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (getSherlock().dispatchKeyEvent(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public MenuInflater getSupportMenuInflater() {
        return getSherlock().getMenuInflater();
    }

    @Override // android.app.Activity
    public void invalidateOptionsMenu() {
        getSherlock().dispatchInvalidateOptionsMenu();
    }

    public void supportInvalidateOptionsMenu() {
        invalidateOptionsMenu();
    }

    @Override // android.app.Activity
    public final boolean onCreateOptionsMenu(Menu menu) {
        return getSherlock().dispatchCreateOptionsMenu(menu);
    }

    @Override // android.app.Activity
    public final boolean onPrepareOptionsMenu(Menu menu) {
        return getSherlock().dispatchPrepareOptionsMenu(menu);
    }

    @Override // android.app.Activity
    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        return getSherlock().dispatchOptionsItemSelected(menuItem);
    }

    @Override // android.app.Activity
    public void openOptionsMenu() {
        if (!getSherlock().dispatchOpenOptionsMenu()) {
            super.openOptionsMenu();
        }
    }

    @Override // android.app.Activity
    public void closeOptionsMenu() {
        if (!getSherlock().dispatchCloseOptionsMenu()) {
            super.closeOptionsMenu();
        }
    }

    @Override // com.actionbarsherlock.ActionBarSherlock.OnCreatePanelMenuListener
    public boolean onCreatePanelMenu(int i, com.actionbarsherlock.view.Menu menu) {
        if (i == 0) {
            return onCreateOptionsMenu(menu);
        }
        return false;
    }

    public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
        return true;
    }

    @Override // com.actionbarsherlock.ActionBarSherlock.OnPreparePanelListener
    public boolean onPreparePanel(int i, View view, com.actionbarsherlock.view.Menu menu) {
        if (i == 0) {
            return onPrepareOptionsMenu(menu);
        }
        return false;
    }

    public boolean onPrepareOptionsMenu(com.actionbarsherlock.view.Menu menu) {
        return true;
    }

    @Override // com.actionbarsherlock.ActionBarSherlock.OnMenuItemSelectedListener
    public boolean onMenuItemSelected(int i, com.actionbarsherlock.view.MenuItem menuItem) {
        if (i == 0) {
            return onOptionsItemSelected(menuItem);
        }
        return false;
    }

    public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem menuItem) {
        return false;
    }

    @Override // android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        getSherlock().addContentView(view, layoutParams);
    }

    @Override // android.app.Activity
    public void setContentView(int i) {
        getSherlock().setContentView(i);
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        getSherlock().setContentView(view, layoutParams);
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        getSherlock().setContentView(view);
    }

    public void requestWindowFeature(long j) {
        getSherlock().requestFeature((int) j);
    }

    public void setSupportProgress(int i) {
        getSherlock().setProgress(i);
    }

    public void setSupportProgressBarIndeterminate(boolean z) {
        getSherlock().setProgressBarIndeterminate(z);
    }

    public void setSupportProgressBarIndeterminateVisibility(boolean z) {
        getSherlock().setProgressBarIndeterminateVisibility(z);
    }

    public void setSupportProgressBarVisibility(boolean z) {
        getSherlock().setProgressBarVisibility(z);
    }

    public void setSupportSecondaryProgress(int i) {
        getSherlock().setSecondaryProgress(i);
    }
}
