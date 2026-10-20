package com.actionbarsherlock.app;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Watson;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.actionbarsherlock.ActionBarSherlock;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.MenuInflater;

/* loaded from: classes.dex */
public class SherlockFragmentActivity extends Watson implements ActionBarSherlock.OnActionModeFinishedListener, ActionBarSherlock.OnActionModeStartedListener {
    private static final boolean DEBUG = false;
    private static final String TAG = "SherlockFragmentActivity";
    private boolean mIgnoreNativeCreate = false;
    private boolean mIgnoreNativePrepare = false;
    private boolean mIgnoreNativeSelected = false;
    private ActionBarSherlock mSherlock;

    /* JADX INFO: Access modifiers changed from: protected */
    public final ActionBarSherlock getSherlock() {
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

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        getSherlock().dispatchConfigurationChanged(configuration);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        getSherlock().dispatchPostResume();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        getSherlock().dispatchPause();
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        getSherlock().dispatchStop();
        super.onStop();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        getSherlock().dispatchDestroy();
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onPostCreate(Bundle bundle) {
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

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        getSherlock().dispatchPanelClosed(i, menu);
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (getSherlock().dispatchKeyEvent(keyEvent)) {
            return true;
        }
        try {
            return super.dispatchKeyEvent(keyEvent);
        } catch (Throwable th) {
            Log.e("thuuz", "cannot handle key: ", th);
            return true;
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        getSherlock().dispatchSaveInstanceState(bundle);
    }

    @Override // android.app.Activity
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        getSherlock().dispatchRestoreInstanceState(bundle);
    }

    @Override // android.support.v4.app.Watson
    public MenuInflater getSupportMenuInflater() {
        return getSherlock().getMenuInflater();
    }

    @Override // android.app.Activity
    public void invalidateOptionsMenu() {
        getSherlock().dispatchInvalidateOptionsMenu();
    }

    @Override // android.support.v4.app.FragmentActivity
    public void supportInvalidateOptionsMenu() {
        invalidateOptionsMenu();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0 || this.mIgnoreNativeCreate) {
            return super.onCreatePanelMenu(i, menu);
        }
        this.mIgnoreNativeCreate = true;
        boolean dispatchCreateOptionsMenu = getSherlock().dispatchCreateOptionsMenu(menu);
        this.mIgnoreNativeCreate = false;
        return dispatchCreateOptionsMenu;
    }

    @Override // android.app.Activity
    public final boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean onPreparePanel(int i, View view, Menu menu) {
        if (i != 0 || this.mIgnoreNativePrepare) {
            return super.onPreparePanel(i, view, menu);
        }
        this.mIgnoreNativePrepare = true;
        boolean dispatchPrepareOptionsMenu = getSherlock().dispatchPrepareOptionsMenu(menu);
        this.mIgnoreNativePrepare = false;
        return dispatchPrepareOptionsMenu;
    }

    @Override // android.app.Activity
    public final boolean onPrepareOptionsMenu(Menu menu) {
        return true;
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (i != 0 || this.mIgnoreNativeSelected) {
            return super.onMenuItemSelected(i, menuItem);
        }
        this.mIgnoreNativeSelected = true;
        boolean dispatchOptionsItemSelected = getSherlock().dispatchOptionsItemSelected(menuItem);
        this.mIgnoreNativeSelected = false;
        return dispatchOptionsItemSelected;
    }

    @Override // android.app.Activity
    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        return false;
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

    @Override // android.support.v4.app.Watson
    public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
        return true;
    }

    @Override // android.support.v4.app.Watson
    public boolean onPrepareOptionsMenu(com.actionbarsherlock.view.Menu menu) {
        return true;
    }

    @Override // android.support.v4.app.Watson
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
