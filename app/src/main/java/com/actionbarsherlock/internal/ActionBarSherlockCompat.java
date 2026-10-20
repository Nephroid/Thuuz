package com.actionbarsherlock.internal;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.actionbarsherlock.ActionBarSherlock;
import com.actionbarsherlock.R;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.internal.app.ActionBarImpl;
import com.actionbarsherlock.internal.view.StandaloneActionMode;
import com.actionbarsherlock.internal.view.menu.ActionMenuPresenter;
import com.actionbarsherlock.internal.view.menu.MenuBuilder;
import com.actionbarsherlock.internal.view.menu.MenuItemImpl;
import com.actionbarsherlock.internal.view.menu.MenuPresenter;
import com.actionbarsherlock.internal.widget.ActionBarContainer;
import com.actionbarsherlock.internal.widget.ActionBarContextView;
import com.actionbarsherlock.internal.widget.ActionBarView;
import com.actionbarsherlock.internal.widget.IcsProgressBar;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Window;
import com.samsung.android.sdk.richnotification.Utilities;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@ActionBarSherlock.Implementation(api = 7)
/* loaded from: classes.dex */
public class ActionBarSherlockCompat extends ActionBarSherlock implements MenuItem.OnMenuItemClickListener, MenuBuilder.Callback, MenuPresenter.Callback, Window.Callback {
    protected static final int DEFAULT_FEATURES = 0;
    private static final String PANELS_TAG = "sherlock:Panels";
    private ActionBarImpl aActionBar;
    private ActionMode mActionMode;
    private ActionBarContextView mActionModeView;
    private IcsProgressBar mCircularProgressBar;
    private boolean mClosingActionMenu;
    private ViewGroup mContentParent;
    private ViewGroup mDecor;
    private int mFeatures;
    private IcsProgressBar mHorizontalProgressBar;
    private boolean mIsDestroyed;
    private boolean mIsFloating;
    private boolean mIsTitleReady;
    private MenuBuilder mMenu;
    private Bundle mMenuFrozenActionViewState;
    private boolean mMenuIsPrepared;
    private boolean mMenuRefreshContent;
    protected HashMap<MenuItem, MenuItemImpl> mNativeItemMap;
    private boolean mReserveOverflow;
    private boolean mReserveOverflowSet;
    private CharSequence mTitle;
    private TextView mTitleView;
    private int mUiOptions;
    private ActionBarView wActionBar;

    public ActionBarSherlockCompat(Activity activity, int i) {
        super(activity, i);
        this.mReserveOverflowSet = false;
        this.mIsTitleReady = false;
        this.mIsDestroyed = false;
        this.mFeatures = 0;
        this.mUiOptions = 0;
        this.mTitle = null;
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public ActionBar getActionBar() {
        initActionBar();
        return this.aActionBar;
    }

    private void initActionBar() {
        if (this.mDecor == null) {
            installDecor();
        }
        if (this.aActionBar == null && hasFeature(8) && !hasFeature(1) && !this.mActivity.isChild()) {
            this.aActionBar = new ActionBarImpl(this.mActivity, this.mFeatures);
            if (!this.mIsDelegate) {
                this.wActionBar.setWindowTitle(this.mActivity.getTitle());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.actionbarsherlock.ActionBarSherlock
    public Context getThemedContext() {
        return this.aActionBar.getThemedContext();
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void setTitle(CharSequence charSequence) {
        dispatchTitleChanged(charSequence, 0);
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public ActionMode startActionMode(ActionMode.Callback callback) {
        ViewStub viewStub;
        if (this.mActionMode != null) {
            this.mActionMode.finish();
        }
        ActionModeCallbackWrapper actionModeCallbackWrapper = new ActionModeCallbackWrapper(callback);
        initActionBar();
        ActionMode startActionMode = this.aActionBar != null ? this.aActionBar.startActionMode(actionModeCallbackWrapper) : null;
        if (startActionMode != null) {
            this.mActionMode = startActionMode;
        } else {
            if (this.mActionModeView == null && (viewStub = (ViewStub) this.mDecor.findViewById(R.id.abs__action_mode_bar_stub)) != null) {
                this.mActionModeView = (ActionBarContextView) viewStub.inflate();
            }
            if (this.mActionModeView != null) {
                this.mActionModeView.killMode();
                StandaloneActionMode standaloneActionMode = new StandaloneActionMode(this.mActivity, this.mActionModeView, actionModeCallbackWrapper, true);
                if (callback.onCreateActionMode(standaloneActionMode, standaloneActionMode.getMenu())) {
                    standaloneActionMode.invalidate();
                    this.mActionModeView.initForMode(standaloneActionMode);
                    this.mActionModeView.setVisibility(0);
                    this.mActionMode = standaloneActionMode;
                    this.mActionModeView.sendAccessibilityEvent(32);
                } else {
                    this.mActionMode = null;
                }
            }
        }
        if (this.mActionMode != null && (this.mActivity instanceof ActionBarSherlock.OnActionModeStartedListener)) {
            ((ActionBarSherlock.OnActionModeStartedListener) this.mActivity).onActionModeStarted(this.mActionMode);
        }
        return this.mActionMode;
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void dispatchConfigurationChanged(Configuration configuration) {
        if (this.aActionBar != null) {
            this.aActionBar.onConfigurationChanged(configuration);
        }
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void dispatchPostResume() {
        if (this.aActionBar != null) {
            this.aActionBar.setShowHideAnimationEnabled(true);
        }
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void dispatchPause() {
        if (this.wActionBar != null && this.wActionBar.isOverflowMenuShowing()) {
            this.wActionBar.hideOverflowMenu();
        }
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void dispatchStop() {
        if (this.aActionBar != null) {
            this.aActionBar.setShowHideAnimationEnabled(false);
        }
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void dispatchInvalidateOptionsMenu() {
        if (this.mMenu != null) {
            Bundle bundle = new Bundle();
            this.mMenu.saveActionViewStates(bundle);
            if (bundle.size() > 0) {
                this.mMenuFrozenActionViewState = bundle;
            }
            this.mMenu.stopDispatchingItemsChanged();
            this.mMenu.clear();
        }
        this.mMenuRefreshContent = true;
        if (this.wActionBar != null) {
            this.mMenuIsPrepared = false;
            preparePanel();
        }
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public boolean dispatchOpenOptionsMenu() {
        if (isReservingOverflow()) {
            return this.wActionBar.showOverflowMenu();
        }
        return false;
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public boolean dispatchCloseOptionsMenu() {
        if (isReservingOverflow() && this.wActionBar != null) {
            return this.wActionBar.hideOverflowMenu();
        }
        return false;
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void dispatchPostCreate(Bundle bundle) {
        if (this.mIsDelegate) {
            this.mIsTitleReady = true;
        }
        if (this.mDecor == null) {
            initActionBar();
        }
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public boolean dispatchCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public boolean dispatchPrepareOptionsMenu(Menu menu) {
        if (this.mActionMode != null) {
            return false;
        }
        this.mMenuIsPrepared = false;
        if (!preparePanel() || isReservingOverflow()) {
            return false;
        }
        if (this.mNativeItemMap == null) {
            this.mNativeItemMap = new HashMap<>();
        } else {
            this.mNativeItemMap.clear();
        }
        if (this.mMenu != null) {
            return this.mMenu.bindNativeOverflow(menu, this, this.mNativeItemMap);
        }
        return false;
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public boolean dispatchOptionsItemSelected(MenuItem menuItem) {
        throw new IllegalStateException("Native callback invoked. Create a test case and report!");
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public boolean dispatchMenuOpened(int i, Menu menu) {
        if (i != 8 && i != 0) {
            return false;
        }
        if (this.aActionBar == null) {
            return true;
        }
        this.aActionBar.dispatchMenuVisibilityChanged(true);
        return true;
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void dispatchPanelClosed(int i, Menu menu) {
        if ((i == 8 || i == 0) && this.aActionBar != null) {
            this.aActionBar.dispatchMenuVisibilityChanged(false);
        }
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void dispatchTitleChanged(CharSequence charSequence, int i) {
        if (!this.mIsDelegate || this.mIsTitleReady) {
            if (this.mTitleView != null) {
                this.mTitleView.setText(charSequence);
            } else if (this.wActionBar != null) {
                this.wActionBar.setWindowTitle(charSequence);
            }
        }
        this.mTitle = charSequence;
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4) {
            int action = keyEvent.getAction();
            if (this.mActionMode != null) {
                if (action != 1) {
                    return true;
                }
                this.mActionMode.finish();
                return true;
            }
            if (this.wActionBar != null && this.wActionBar.hasExpandedActionView()) {
                if (action != 1) {
                    return true;
                }
                this.wActionBar.collapseActionView();
                return true;
            }
        }
        return false;
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void dispatchDestroy() {
        this.mIsDestroyed = true;
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void dispatchSaveInstanceState(Bundle bundle) {
        if (this.mMenu != null) {
            this.mMenuFrozenActionViewState = new Bundle();
            this.mMenu.saveActionViewStates(this.mMenuFrozenActionViewState);
        }
        bundle.putParcelable(PANELS_TAG, this.mMenuFrozenActionViewState);
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void dispatchRestoreInstanceState(Bundle bundle) {
        this.mMenuFrozenActionViewState = (Bundle) bundle.getParcelable(PANELS_TAG);
    }

    private boolean preparePanel() {
        if (this.mMenuIsPrepared) {
            return true;
        }
        if (this.mMenu == null || this.mMenuRefreshContent) {
            if (this.mMenu == null && (!initializePanelMenu() || this.mMenu == null)) {
                return false;
            }
            if (this.wActionBar != null) {
                this.wActionBar.setMenu(this.mMenu, this);
            }
            this.mMenu.stopDispatchingItemsChanged();
            if (!callbackCreateOptionsMenu(this.mMenu)) {
                this.mMenu = null;
                if (this.wActionBar == null) {
                    return false;
                }
                this.wActionBar.setMenu(null, this);
                return false;
            }
            this.mMenuRefreshContent = false;
        }
        this.mMenu.stopDispatchingItemsChanged();
        if (this.mMenuFrozenActionViewState != null) {
            this.mMenu.restoreActionViewStates(this.mMenuFrozenActionViewState);
            this.mMenuFrozenActionViewState = null;
        }
        if (!callbackPrepareOptionsMenu(this.mMenu)) {
            if (this.wActionBar != null) {
                this.wActionBar.setMenu(null, this);
            }
            this.mMenu.startDispatchingItemsChanged();
            return false;
        }
        this.mMenu.setQwertyMode(KeyCharacterMap.load(-1).getKeyboardType() != 1);
        this.mMenu.startDispatchingItemsChanged();
        this.mMenuIsPrepared = true;
        return true;
    }

    @Override // com.actionbarsherlock.internal.view.menu.MenuBuilder.Callback
    public boolean onMenuItemSelected(MenuBuilder menuBuilder, com.actionbarsherlock.view.MenuItem menuItem) {
        return callbackOptionsItemSelected(menuItem);
    }

    @Override // com.actionbarsherlock.internal.view.menu.MenuBuilder.Callback
    public void onMenuModeChange(MenuBuilder menuBuilder) {
        reopenMenu(true);
    }

    private void reopenMenu(boolean z) {
        if (this.wActionBar != null && this.wActionBar.isOverflowReserved()) {
            if (!this.wActionBar.isOverflowMenuShowing() || !z) {
                if (this.wActionBar.getVisibility() == 0 && callbackPrepareOptionsMenu(this.mMenu)) {
                    this.wActionBar.showOverflowMenu();
                    return;
                }
                return;
            }
            this.wActionBar.hideOverflowMenu();
        }
    }

    private boolean initializePanelMenu() {
        ContextThemeWrapper contextThemeWrapper;
        Activity activity = this.mActivity;
        if (this.wActionBar != null) {
            TypedValue typedValue = new TypedValue();
            activity.getTheme().resolveAttribute(R.attr.actionBarWidgetTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                contextThemeWrapper = new ContextThemeWrapper(activity, i);
                this.mMenu = new MenuBuilder(contextThemeWrapper);
                this.mMenu.setCallback(this);
                return true;
            }
        }
        contextThemeWrapper = activity;
        this.mMenu = new MenuBuilder(contextThemeWrapper);
        this.mMenu.setCallback(this);
        return true;
    }

    void checkCloseActionMenu(com.actionbarsherlock.view.Menu menu) {
        if (!this.mClosingActionMenu) {
            this.mClosingActionMenu = true;
            this.wActionBar.dismissPopupMenus();
            this.mClosingActionMenu = false;
        }
    }

    @Override // com.actionbarsherlock.internal.view.menu.MenuPresenter.Callback
    public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
        return true;
    }

    @Override // com.actionbarsherlock.internal.view.menu.MenuPresenter.Callback
    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        checkCloseActionMenu(menuBuilder);
    }

    @Override // android.view.MenuItem.OnMenuItemClickListener
    public boolean onMenuItemClick(MenuItem menuItem) {
        MenuItemImpl menuItemImpl = this.mNativeItemMap.get(menuItem);
        if (menuItemImpl != null) {
            menuItemImpl.invoke();
            return true;
        }
        Log.e("ActionBarSherlock", "Options item \"" + menuItem + "\" not found in mapping");
        return true;
    }

    @Override // com.actionbarsherlock.view.Window.Callback
    public boolean onMenuItemSelected(int i, com.actionbarsherlock.view.MenuItem menuItem) {
        return callbackOptionsItemSelected(menuItem);
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void setProgressBarVisibility(boolean z) {
        setFeatureInt(2, z ? -1 : -2);
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void setProgressBarIndeterminateVisibility(boolean z) {
        setFeatureInt(5, z ? -1 : -2);
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void setProgressBarIndeterminate(boolean z) {
        setFeatureInt(2, z ? -3 : -4);
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void setProgress(int i) {
        setFeatureInt(2, i + 0);
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void setSecondaryProgress(int i) {
        setFeatureInt(2, i + 20000);
    }

    private void setFeatureInt(int i, int i2) {
        updateInt(i, i2, false);
    }

    private void updateInt(int i, int i2, boolean z) {
        if (this.mContentParent != null) {
            if (((1 << i) & getFeatures()) != 0 || z) {
                onIntChanged(i, i2);
            }
        }
    }

    private void onIntChanged(int i, int i2) {
        if (i == 2 || i == 5) {
            updateProgressBars(i2);
        }
    }

    private void updateProgressBars(int i) {
        IcsProgressBar circularProgressBar = getCircularProgressBar(true);
        IcsProgressBar horizontalProgressBar = getHorizontalProgressBar(true);
        int i2 = this.mFeatures;
        if (i == -1) {
            if ((i2 & 4) != 0) {
                horizontalProgressBar.setVisibility((horizontalProgressBar.isIndeterminate() || horizontalProgressBar.getProgress() < 10000) ? 0 : 4);
            }
            if ((i2 & 32) != 0) {
                circularProgressBar.setVisibility(0);
                return;
            }
            return;
        }
        if (i == -2) {
            if ((i2 & 4) != 0) {
                horizontalProgressBar.setVisibility(8);
            }
            if ((i2 & 32) != 0) {
                circularProgressBar.setVisibility(8);
                return;
            }
            return;
        }
        if (i == -3) {
            horizontalProgressBar.setIndeterminate(true);
            return;
        }
        if (i == -4) {
            horizontalProgressBar.setIndeterminate(false);
            return;
        }
        if (i >= 0 && i <= 10000) {
            horizontalProgressBar.setProgress(i + 0);
            if (i < 10000) {
                showProgressBars(horizontalProgressBar, circularProgressBar);
                return;
            } else {
                hideProgressBars(horizontalProgressBar, circularProgressBar);
                return;
            }
        }
        if (20000 <= i && i <= 30000) {
            horizontalProgressBar.setSecondaryProgress(i - 20000);
            showProgressBars(horizontalProgressBar, circularProgressBar);
        }
    }

    private void showProgressBars(IcsProgressBar icsProgressBar, IcsProgressBar icsProgressBar2) {
        int i = this.mFeatures;
        if ((i & 32) != 0 && icsProgressBar2.getVisibility() == 4) {
            icsProgressBar2.setVisibility(0);
        }
        if ((i & 4) != 0 && icsProgressBar.getProgress() < 10000) {
            icsProgressBar.setVisibility(0);
        }
    }

    private void hideProgressBars(IcsProgressBar icsProgressBar, IcsProgressBar icsProgressBar2) {
        int i = this.mFeatures;
        Animation loadAnimation = AnimationUtils.loadAnimation(this.mActivity, android.R.anim.fade_out);
        loadAnimation.setDuration(1000L);
        if ((i & 32) != 0 && icsProgressBar2.getVisibility() == 0) {
            icsProgressBar2.startAnimation(loadAnimation);
            icsProgressBar2.setVisibility(4);
        }
        if ((i & 4) != 0 && icsProgressBar.getVisibility() == 0) {
            icsProgressBar.startAnimation(loadAnimation);
            icsProgressBar.setVisibility(4);
        }
    }

    private IcsProgressBar getCircularProgressBar(boolean z) {
        if (this.mCircularProgressBar != null) {
            return this.mCircularProgressBar;
        }
        if (this.mContentParent == null && z) {
            installDecor();
        }
        this.mCircularProgressBar = (IcsProgressBar) this.mDecor.findViewById(R.id.abs__progress_circular);
        if (this.mCircularProgressBar != null) {
            this.mCircularProgressBar.setVisibility(4);
        }
        return this.mCircularProgressBar;
    }

    private IcsProgressBar getHorizontalProgressBar(boolean z) {
        if (this.mHorizontalProgressBar != null) {
            return this.mHorizontalProgressBar;
        }
        if (this.mContentParent == null && z) {
            installDecor();
        }
        this.mHorizontalProgressBar = (IcsProgressBar) this.mDecor.findViewById(R.id.abs__progress_horizontal);
        if (this.mHorizontalProgressBar != null) {
            this.mHorizontalProgressBar.setVisibility(4);
        }
        return this.mHorizontalProgressBar;
    }

    private int getFeatures() {
        return this.mFeatures;
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public boolean hasFeature(int i) {
        return (this.mFeatures & (1 << i)) != 0;
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public boolean requestFeature(int i) {
        if (this.mContentParent != null) {
            throw new AndroidRuntimeException("requestFeature() must be called before adding content");
        }
        switch (i) {
            case 1:
            case 2:
            case 5:
            case 8:
            case 9:
            case 10:
                this.mFeatures |= 1 << i;
                return true;
            case 3:
            case 4:
            case 6:
            case 7:
            default:
                return false;
        }
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void setUiOptions(int i) {
        this.mUiOptions = i;
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void setUiOptions(int i, int i2) {
        this.mUiOptions = (this.mUiOptions & (i2 ^ (-1))) | (i & i2);
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void setContentView(int i) {
        if (this.mContentParent == null) {
            installDecor();
        } else {
            this.mContentParent.removeAllViews();
        }
        this.mActivity.getLayoutInflater().inflate(i, this.mContentParent);
        Window.Callback callback = this.mActivity.getWindow().getCallback();
        if (callback != null) {
            callback.onContentChanged();
        }
        initActionBar();
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        if (this.mContentParent == null) {
            installDecor();
        } else {
            this.mContentParent.removeAllViews();
        }
        this.mContentParent.addView(view, layoutParams);
        Window.Callback callback = this.mActivity.getWindow().getCallback();
        if (callback != null) {
            callback.onContentChanged();
        }
        initActionBar();
    }

    @Override // com.actionbarsherlock.ActionBarSherlock
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        if (this.mContentParent == null) {
            installDecor();
        }
        this.mContentParent.addView(view, layoutParams);
        initActionBar();
    }

    private void installDecor() {
        ArrayList arrayList;
        boolean z;
        if (this.mDecor == null) {
            this.mDecor = (ViewGroup) this.mActivity.getWindow().getDecorView().findViewById(android.R.id.content);
        }
        if (this.mContentParent == null) {
            if (this.mDecor.getChildCount() > 0) {
                arrayList = new ArrayList(1);
                int childCount = this.mDecor.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = this.mDecor.getChildAt(0);
                    this.mDecor.removeView(childAt);
                    arrayList.add(childAt);
                }
            } else {
                arrayList = null;
            }
            this.mContentParent = generateLayout();
            if (arrayList != null) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    this.mContentParent.addView((View) it.next());
                }
            }
            this.mTitleView = (TextView) this.mDecor.findViewById(android.R.id.title);
            if (this.mTitleView != null) {
                if (hasFeature(1)) {
                    this.mTitleView.setVisibility(8);
                    if (this.mContentParent instanceof FrameLayout) {
                        ((FrameLayout) this.mContentParent).setForeground(null);
                        return;
                    }
                    return;
                }
                this.mTitleView.setText(this.mTitle);
                return;
            }
            this.wActionBar = (ActionBarView) this.mDecor.findViewById(R.id.abs__action_bar);
            if (this.wActionBar != null) {
                this.wActionBar.setWindowCallback(this);
                if (this.wActionBar.getTitle() == null) {
                    this.wActionBar.setWindowTitle(this.mActivity.getTitle());
                }
                if (hasFeature(2)) {
                    this.wActionBar.initProgress();
                }
                if (hasFeature(5)) {
                    this.wActionBar.initIndeterminateProgress();
                }
                int loadUiOptionsFromManifest = loadUiOptionsFromManifest(this.mActivity);
                if (loadUiOptionsFromManifest != 0) {
                    this.mUiOptions = loadUiOptionsFromManifest;
                }
                boolean z2 = (this.mUiOptions & 1) != 0;
                if (z2) {
                    z = ResourcesCompat.getResources_getBoolean(this.mActivity, R.bool.abs__split_action_bar_is_narrow);
                } else {
                    z = this.mActivity.getTheme().obtainStyledAttributes(R.styleable.SherlockTheme).getBoolean(R.styleable.SherlockTheme_windowSplitActionBar, false);
                }
                ActionBarContainer actionBarContainer = (ActionBarContainer) this.mDecor.findViewById(R.id.abs__split_action_bar);
                if (actionBarContainer != null) {
                    this.wActionBar.setSplitView(actionBarContainer);
                    this.wActionBar.setSplitActionBar(z);
                    this.wActionBar.setSplitWhenNarrow(z2);
                    this.mActionModeView = (ActionBarContextView) this.mDecor.findViewById(R.id.abs__action_context_bar);
                    this.mActionModeView.setSplitView(actionBarContainer);
                    this.mActionModeView.setSplitActionBar(z);
                    this.mActionModeView.setSplitWhenNarrow(z2);
                } else if (z) {
                    Log.e("ActionBarSherlock", "Requested split action bar with incompatible window decor! Ignoring request.");
                }
                this.mDecor.post(new Runnable() { // from class: com.actionbarsherlock.internal.ActionBarSherlockCompat.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (!ActionBarSherlockCompat.this.mIsDestroyed && !ActionBarSherlockCompat.this.mActivity.isFinishing() && ActionBarSherlockCompat.this.mMenu == null) {
                            ActionBarSherlockCompat.this.dispatchInvalidateOptionsMenu();
                        }
                    }
                });
            }
        }
    }

    private ViewGroup generateLayout() {
        int i;
        IcsProgressBar circularProgressBar;
        TypedArray obtainStyledAttributes = this.mActivity.getTheme().obtainStyledAttributes(R.styleable.SherlockTheme);
        this.mIsFloating = obtainStyledAttributes.getBoolean(R.styleable.SherlockTheme_android_windowIsFloating, false);
        if (!obtainStyledAttributes.hasValue(R.styleable.SherlockTheme_windowActionBar)) {
            throw new IllegalStateException("You must use Theme.Sherlock, Theme.Sherlock.Light, Theme.Sherlock.Light.DarkActionBar, or a derivative.");
        }
        if (obtainStyledAttributes.getBoolean(R.styleable.SherlockTheme_windowNoTitle, false)) {
            requestFeature(1);
        } else if (obtainStyledAttributes.getBoolean(R.styleable.SherlockTheme_windowActionBar, false)) {
            requestFeature(8);
        }
        if (obtainStyledAttributes.getBoolean(R.styleable.SherlockTheme_windowActionBarOverlay, false)) {
            requestFeature(9);
        }
        if (obtainStyledAttributes.getBoolean(R.styleable.SherlockTheme_windowActionModeOverlay, false)) {
            requestFeature(10);
        }
        obtainStyledAttributes.recycle();
        if (!hasFeature(1)) {
            if (this.mIsFloating) {
                this.mDecor = (ViewGroup) this.mDecor.getParent();
                this.mDecor.removeAllViews();
                i = R.layout.abs__dialog_title_holo;
            } else if (hasFeature(9)) {
                i = R.layout.abs__screen_action_bar_overlay;
            } else {
                i = R.layout.abs__screen_action_bar;
            }
        } else if (hasFeature(10) && !hasFeature(1)) {
            i = R.layout.abs__screen_simple_overlay_action_mode;
        } else {
            i = R.layout.abs__screen_simple;
        }
        this.mDecor.addView(this.mActivity.getLayoutInflater().inflate(i, (ViewGroup) null), new ViewGroup.LayoutParams(-1, -1));
        ViewGroup viewGroup = (ViewGroup) this.mDecor.findViewById(R.id.abs__content);
        if (viewGroup == null) {
            throw new RuntimeException("Couldn't find content container view");
        }
        this.mDecor.setId(-1);
        viewGroup.setId(android.R.id.content);
        if (hasFeature(5) && (circularProgressBar = getCircularProgressBar(false)) != null) {
            circularProgressBar.setIndeterminate(true);
        }
        return viewGroup;
    }

    private boolean isReservingOverflow() {
        if (!this.mReserveOverflowSet) {
            this.mReserveOverflow = ActionMenuPresenter.reserveOverflow(this.mActivity);
            this.mReserveOverflowSet = true;
        }
        return this.mReserveOverflow;
    }

    private static int loadUiOptionsFromManifest(Activity activity) {
        Exception e;
        int i;
        int i2;
        try {
            String name = activity.getClass().getName();
            String str = activity.getApplicationInfo().packageName;
            XmlResourceParser openXmlResourceParser = activity.createPackageContext(str, 0).getAssets().openXmlResourceParser("AndroidManifest.xml");
            i = 0;
            for (int eventType = openXmlResourceParser.getEventType(); eventType != 1; eventType = openXmlResourceParser.nextToken()) {
                try {
                    if (eventType == 2) {
                        try {
                            String name2 = openXmlResourceParser.getName();
                            if (!"application".equals(name2)) {
                                if ("activity".equals(name2)) {
                                    boolean z = false;
                                    String str2 = null;
                                    Integer num = null;
                                    for (int attributeCount = openXmlResourceParser.getAttributeCount() - 1; attributeCount >= 0; attributeCount--) {
                                        String attributeName = openXmlResourceParser.getAttributeName(attributeCount);
                                        if ("uiOptions".equals(attributeName)) {
                                            num = Integer.valueOf(openXmlResourceParser.getAttributeIntValue(attributeCount, 0));
                                        } else if (Utilities.DB_KEY_IMAGE_NAME.equals(attributeName)) {
                                            str2 = cleanActivityName(str, openXmlResourceParser.getAttributeValue(attributeCount));
                                            if (!name.equals(str2)) {
                                                break;
                                            }
                                            z = true;
                                        }
                                        if (num != null && str2 != null) {
                                            i = num.intValue();
                                        }
                                    }
                                    if (z) {
                                        break;
                                    }
                                }
                            } else {
                                int attributeCount2 = openXmlResourceParser.getAttributeCount() - 1;
                                while (true) {
                                    if (attributeCount2 < 0) {
                                        break;
                                    }
                                    if ("uiOptions".equals(openXmlResourceParser.getAttributeName(attributeCount2))) {
                                        i = openXmlResourceParser.getAttributeIntValue(attributeCount2, 0);
                                        break;
                                    }
                                    attributeCount2--;
                                }
                                i2 = i;
                                i = i2;
                            }
                        } catch (Exception e2) {
                            e = e2;
                            e.printStackTrace();
                            return i;
                        }
                    }
                    i = i2;
                } catch (Exception e3) {
                    i = i2;
                    e = e3;
                    e.printStackTrace();
                    return i;
                }
                i2 = i;
            }
        } catch (Exception e4) {
            e = e4;
            i = 0;
        }
        return i;
    }

    public static String cleanActivityName(String str, String str2) {
        if (str2.charAt(0) == '.') {
            return str + str2;
        }
        if (str2.indexOf(46, 1) == -1) {
            return str + "." + str2;
        }
        return str2;
    }

    /* loaded from: classes.dex */
    class ActionModeCallbackWrapper implements ActionMode.Callback {
        private final ActionMode.Callback mWrapped;

        public ActionModeCallbackWrapper(ActionMode.Callback callback) {
            this.mWrapped = callback;
        }

        @Override // com.actionbarsherlock.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode actionMode, com.actionbarsherlock.view.Menu menu) {
            return this.mWrapped.onCreateActionMode(actionMode, menu);
        }

        @Override // com.actionbarsherlock.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode actionMode, com.actionbarsherlock.view.Menu menu) {
            return this.mWrapped.onPrepareActionMode(actionMode, menu);
        }

        @Override // com.actionbarsherlock.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode actionMode, com.actionbarsherlock.view.MenuItem menuItem) {
            return this.mWrapped.onActionItemClicked(actionMode, menuItem);
        }

        @Override // com.actionbarsherlock.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode actionMode) {
            this.mWrapped.onDestroyActionMode(actionMode);
            if (ActionBarSherlockCompat.this.mActionModeView != null) {
                ActionBarSherlockCompat.this.mActionModeView.setVisibility(8);
                ActionBarSherlockCompat.this.mActionModeView.removeAllViews();
            }
            if (ActionBarSherlockCompat.this.mActivity instanceof ActionBarSherlock.OnActionModeFinishedListener) {
                ((ActionBarSherlock.OnActionModeFinishedListener) ActionBarSherlockCompat.this.mActivity).onActionModeFinished(ActionBarSherlockCompat.this.mActionMode);
            }
            ActionBarSherlockCompat.this.mActionMode = null;
        }
    }
}
