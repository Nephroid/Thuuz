package android.support.v4.app;

import android.view.View;
import com.actionbarsherlock.ActionBarSherlock;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import java.util.ArrayList;

/* loaded from: classes.dex */
public abstract class Watson extends FragmentActivity implements ActionBarSherlock.OnCreatePanelMenuListener, ActionBarSherlock.OnMenuItemSelectedListener, ActionBarSherlock.OnPreparePanelListener {
    private static final boolean DEBUG = false;
    private static final String TAG = "Watson";
    private ArrayList<Fragment> mCreatedMenus;

    /* loaded from: classes.dex */
    public interface OnCreateOptionsMenuListener {
        void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater);
    }

    /* loaded from: classes.dex */
    public interface OnOptionsItemSelectedListener {
        boolean onOptionsItemSelected(MenuItem menuItem);
    }

    /* loaded from: classes.dex */
    public interface OnPrepareOptionsMenuListener {
        void onPrepareOptionsMenu(Menu menu);
    }

    public abstract MenuInflater getSupportMenuInflater();

    public abstract boolean onCreateOptionsMenu(Menu menu);

    public abstract boolean onOptionsItemSelected(MenuItem menuItem);

    public abstract boolean onPrepareOptionsMenu(Menu menu);

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14, types: [android.support.v4.app.Fragment, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.util.ArrayList] */
    @Override // com.actionbarsherlock.ActionBarSherlock.OnCreatePanelMenuListener
    public boolean onCreatePanelMenu(int i, Menu menu) {
        boolean z;
        boolean z2;
        if (i != 0) {
            return false;
        }
        boolean onCreateOptionsMenu = onCreateOptionsMenu(menu);
        MenuInflater supportMenuInflater = getSupportMenuInflater();
        ArrayList<Fragment> arrayList = null;
        if (this.mFragments.mAdded != null) {
            int i2 = 0;
            z = false;
            while (i2 < this.mFragments.mAdded.size()) {
                Fragment fragment = this.mFragments.mAdded.get(i2);
                if (fragment != 0 && !fragment.mHidden && fragment.mHasMenu && fragment.mMenuVisible && (fragment instanceof OnCreateOptionsMenuListener)) {
                    ((OnCreateOptionsMenuListener) fragment).onCreateOptionsMenu(menu, supportMenuInflater);
                    ?? arrayList2 = arrayList == null ? new ArrayList() : arrayList;
                    arrayList2.add(fragment);
                    arrayList = arrayList2;
                    z2 = true;
                } else {
                    z2 = z;
                }
                i2++;
                z = z2;
            }
        } else {
            z = false;
        }
        if (this.mCreatedMenus != null) {
            for (int i3 = 0; i3 < this.mCreatedMenus.size(); i3++) {
                Fragment fragment2 = this.mCreatedMenus.get(i3);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.onDestroyOptionsMenu();
                }
            }
        }
        this.mCreatedMenus = arrayList;
        return onCreateOptionsMenu | z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.actionbarsherlock.ActionBarSherlock.OnPreparePanelListener
    public boolean onPreparePanel(int i, View view, Menu menu) {
        boolean z;
        if (i != 0) {
            return false;
        }
        boolean onPrepareOptionsMenu = onPrepareOptionsMenu(menu);
        if (this.mFragments.mAdded != null) {
            z = false;
            for (int i2 = 0; i2 < this.mFragments.mAdded.size(); i2++) {
                Fragment fragment = this.mFragments.mAdded.get(i2);
                if (fragment != 0 && !fragment.mHidden && fragment.mHasMenu && fragment.mMenuVisible && (fragment instanceof OnPrepareOptionsMenuListener)) {
                    z = true;
                    ((OnPrepareOptionsMenuListener) fragment).onPrepareOptionsMenu(menu);
                }
            }
        } else {
            z = false;
        }
        return (onPrepareOptionsMenu | z) & menu.hasVisibleItems();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.actionbarsherlock.ActionBarSherlock.OnMenuItemSelectedListener
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (i != 0) {
            return false;
        }
        if (onOptionsItemSelected(menuItem)) {
            return true;
        }
        if (this.mFragments.mAdded == null) {
            return false;
        }
        for (int i2 = 0; i2 < this.mFragments.mAdded.size(); i2++) {
            Fragment fragment = this.mFragments.mAdded.get(i2);
            if (fragment != 0 && !fragment.mHidden && fragment.mHasMenu && fragment.mMenuVisible && (fragment instanceof OnOptionsItemSelectedListener) && ((OnOptionsItemSelectedListener) fragment).onOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }
}
