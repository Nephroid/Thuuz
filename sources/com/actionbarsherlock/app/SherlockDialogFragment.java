package com.actionbarsherlock.app;

import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Watson;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.actionbarsherlock.internal.view.menu.MenuItemWrapper;
import com.actionbarsherlock.internal.view.menu.MenuWrapper;

/* loaded from: classes.dex */
public class SherlockDialogFragment extends DialogFragment implements Watson.OnCreateOptionsMenuListener, Watson.OnOptionsItemSelectedListener, Watson.OnPrepareOptionsMenuListener {
    private SherlockFragmentActivity mActivity;

    public SherlockFragmentActivity getSherlockActivity() {
        return this.mActivity;
    }

    @Override // android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public void onAttach(Activity activity) {
        if (!(activity instanceof SherlockFragmentActivity)) {
            throw new IllegalStateException(getClass().getSimpleName() + " must be attached to a SherlockFragmentActivity.");
        }
        this.mActivity = (SherlockFragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override // android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public void onDetach() {
        this.mActivity = null;
        super.onDetach();
    }

    @Override // android.support.v4.app.Fragment
    public final void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        onCreateOptionsMenu(new MenuWrapper(menu), this.mActivity.getSupportMenuInflater());
    }

    @Override // android.support.v4.app.Watson.OnCreateOptionsMenuListener
    public void onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu, com.actionbarsherlock.view.MenuInflater menuInflater) {
    }

    @Override // android.support.v4.app.Fragment
    public final void onPrepareOptionsMenu(Menu menu) {
        onPrepareOptionsMenu(new MenuWrapper(menu));
    }

    @Override // android.support.v4.app.Watson.OnPrepareOptionsMenuListener
    public void onPrepareOptionsMenu(com.actionbarsherlock.view.Menu menu) {
    }

    @Override // android.support.v4.app.Fragment
    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        return onOptionsItemSelected(new MenuItemWrapper(menuItem));
    }

    @Override // android.support.v4.app.Watson.OnOptionsItemSelectedListener
    public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem menuItem) {
        return false;
    }
}
