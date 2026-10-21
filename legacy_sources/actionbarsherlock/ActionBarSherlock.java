package com.actionbarsherlock;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.internal.ActionBarSherlockCompat;
import com.actionbarsherlock.internal.ActionBarSherlockNative;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public abstract class ActionBarSherlock {
    protected static final boolean DEBUG = false;
    public static final int FLAG_DELEGATE = 1;
    protected static final String TAG = "ActionBarSherlock";
    protected final Activity mActivity;
    protected final boolean mIsDelegate;
    protected MenuInflater mMenuInflater;
    private static final Class<?>[] CONSTRUCTOR_ARGS = {Activity.class, Integer.TYPE};
    private static final HashMap<Implementation, Class<? extends ActionBarSherlock>> IMPLEMENTATIONS = new HashMap<>();

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes.dex */
    public @interface Implementation {
        public static final int DEFAULT_API = -1;
        public static final int DEFAULT_DPI = -1;

        int api() default -1;

        int dpi() default -1;
    }

    /* loaded from: classes.dex */
    public interface OnActionModeFinishedListener {
        void onActionModeFinished(ActionMode actionMode);
    }

    /* loaded from: classes.dex */
    public interface OnActionModeStartedListener {
        void onActionModeStarted(ActionMode actionMode);
    }

    /* loaded from: classes.dex */
    public interface OnCreateOptionsMenuListener {
        boolean onCreateOptionsMenu(Menu menu);
    }

    /* loaded from: classes.dex */
    public interface OnCreatePanelMenuListener {
        boolean onCreatePanelMenu(int i, Menu menu);
    }

    /* loaded from: classes.dex */
    public interface OnMenuItemSelectedListener {
        boolean onMenuItemSelected(int i, MenuItem menuItem);
    }

    /* loaded from: classes.dex */
    public interface OnOptionsItemSelectedListener {
        boolean onOptionsItemSelected(MenuItem menuItem);
    }

    /* loaded from: classes.dex */
    public interface OnPrepareOptionsMenuListener {
        boolean onPrepareOptionsMenu(Menu menu);
    }

    /* loaded from: classes.dex */
    public interface OnPreparePanelListener {
        boolean onPreparePanel(int i, View view, Menu menu);
    }

    public abstract void addContentView(View view, ViewGroup.LayoutParams layoutParams);

    public abstract boolean dispatchCreateOptionsMenu(android.view.Menu menu);

    public abstract void dispatchInvalidateOptionsMenu();

    public abstract boolean dispatchOptionsItemSelected(android.view.MenuItem menuItem);

    public abstract boolean dispatchPrepareOptionsMenu(android.view.Menu menu);

    public abstract ActionBar getActionBar();

    public abstract Context getThemedContext();

    public abstract boolean hasFeature(int i);

    public abstract boolean requestFeature(int i);

    public abstract void setContentView(int i);

    public abstract void setContentView(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void setProgress(int i);

    public abstract void setProgressBarIndeterminate(boolean z);

    public abstract void setProgressBarIndeterminateVisibility(boolean z);

    public abstract void setProgressBarVisibility(boolean z);

    public abstract void setSecondaryProgress(int i);

    public abstract void setTitle(CharSequence charSequence);

    public abstract void setUiOptions(int i);

    public abstract void setUiOptions(int i, int i2);

    public abstract ActionMode startActionMode(ActionMode.Callback callback);

    static {
        registerImplementation(ActionBarSherlockCompat.class);
        registerImplementation(ActionBarSherlockNative.class);
    }

    public static void registerImplementation(Class<? extends ActionBarSherlock> cls) {
        if (!cls.isAnnotationPresent(Implementation.class)) {
            throw new IllegalArgumentException("Class " + cls.getSimpleName() + " is not annotated with @Implementation");
        }
        if (!IMPLEMENTATIONS.containsValue(cls)) {
            IMPLEMENTATIONS.put((Implementation) cls.getAnnotation(Implementation.class), cls);
        }
    }

    public static boolean unregisterImplementation(Class<? extends ActionBarSherlock> cls) {
        return IMPLEMENTATIONS.values().remove(cls);
    }

    public static ActionBarSherlock wrap(Activity activity) {
        return wrap(activity, 0);
    }

    public static ActionBarSherlock wrap(Activity activity, int i) {
        boolean z;
        boolean z2;
        int i2 = 0;
        HashMap hashMap = new HashMap(IMPLEMENTATIONS);
        Iterator it = hashMap.keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            if (((Implementation) it.next()).dpi() == 213) {
                z = true;
                break;
            }
        }
        if (z) {
            boolean z3 = activity.getResources().getDisplayMetrics().densityDpi == 213;
            Iterator it2 = hashMap.keySet().iterator();
            while (it2.hasNext()) {
                int dpi = ((Implementation) it2.next()).dpi();
                if ((z3 && dpi != 213) || (!z3 && dpi == 213)) {
                    it2.remove();
                }
            }
        }
        Iterator it3 = hashMap.keySet().iterator();
        while (true) {
            if (!it3.hasNext()) {
                z2 = false;
                break;
            }
            if (((Implementation) it3.next()).api() != -1) {
                z2 = true;
                break;
            }
        }
        if (z2) {
            int i3 = Build.VERSION.SDK_INT;
            Iterator it4 = hashMap.keySet().iterator();
            while (it4.hasNext()) {
                int api = ((Implementation) it4.next()).api();
                if (api > i3) {
                    it4.remove();
                } else if (api > i2) {
                    i2 = api;
                }
            }
            Iterator it5 = hashMap.keySet().iterator();
            while (it5.hasNext()) {
                if (((Implementation) it5.next()).api() != i2) {
                    it5.remove();
                }
            }
        }
        if (hashMap.size() > 1) {
            throw new IllegalStateException("More than one implementation matches configuration.");
        }
        if (hashMap.isEmpty()) {
            throw new IllegalStateException("No implementations match configuration.");
        }
        try {
            return (ActionBarSherlock) ((Class) hashMap.values().iterator().next()).getConstructor(CONSTRUCTOR_ARGS).newInstance(activity, Integer.valueOf(i));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e2) {
            throw new RuntimeException(e2);
        } catch (InstantiationException e3) {
            throw new RuntimeException(e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException(e4);
        } catch (InvocationTargetException e5) {
            throw new RuntimeException(e5);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ActionBarSherlock(Activity activity, int i) {
        this.mActivity = activity;
        this.mIsDelegate = (i & 1) != 0;
    }

    public void dispatchConfigurationChanged(Configuration configuration) {
    }

    public void dispatchPostResume() {
    }

    public void dispatchPause() {
    }

    public void dispatchStop() {
    }

    public boolean dispatchOpenOptionsMenu() {
        return false;
    }

    public boolean dispatchCloseOptionsMenu() {
        return false;
    }

    public void dispatchPostCreate(Bundle bundle) {
    }

    public void dispatchTitleChanged(CharSequence charSequence, int i) {
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return false;
    }

    public boolean dispatchMenuOpened(int i, android.view.Menu menu) {
        return false;
    }

    public void dispatchPanelClosed(int i, android.view.Menu menu) {
    }

    public void dispatchDestroy() {
    }

    public void dispatchSaveInstanceState(Bundle bundle) {
    }

    public void dispatchRestoreInstanceState(Bundle bundle) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean callbackCreateOptionsMenu(Menu menu) {
        if (this.mActivity instanceof OnCreatePanelMenuListener) {
            return ((OnCreatePanelMenuListener) this.mActivity).onCreatePanelMenu(0, menu);
        }
        if (!(this.mActivity instanceof OnCreateOptionsMenuListener)) {
            return true;
        }
        return ((OnCreateOptionsMenuListener) this.mActivity).onCreateOptionsMenu(menu);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean callbackPrepareOptionsMenu(Menu menu) {
        if (this.mActivity instanceof OnPreparePanelListener) {
            return ((OnPreparePanelListener) this.mActivity).onPreparePanel(0, null, menu);
        }
        if (!(this.mActivity instanceof OnPrepareOptionsMenuListener)) {
            return true;
        }
        return ((OnPrepareOptionsMenuListener) this.mActivity).onPrepareOptionsMenu(menu);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean callbackOptionsItemSelected(MenuItem menuItem) {
        if (this.mActivity instanceof OnMenuItemSelectedListener) {
            return ((OnMenuItemSelectedListener) this.mActivity).onMenuItemSelected(0, menuItem);
        }
        if (this.mActivity instanceof OnOptionsItemSelectedListener) {
            return ((OnOptionsItemSelectedListener) this.mActivity).onOptionsItemSelected(menuItem);
        }
        return false;
    }

    public void setContentView(View view) {
        setContentView(view, new ViewGroup.LayoutParams(-1, -1));
    }

    public void setTitle(int i) {
        setTitle(this.mActivity.getString(i));
    }

    public MenuInflater getMenuInflater() {
        if (this.mMenuInflater == null) {
            if (getActionBar() != null) {
                this.mMenuInflater = new MenuInflater(getThemedContext(), this.mActivity);
            } else {
                this.mMenuInflater = new MenuInflater(this.mActivity);
            }
        }
        return this.mMenuInflater;
    }
}
