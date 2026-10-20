package com.actionbarsherlock.internal.widget;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.widget.ExploreByTouchHelper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.actionbarsherlock.R;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.internal.ActionBarSherlockCompat;
import com.actionbarsherlock.internal.ResourcesCompat;
import com.actionbarsherlock.internal.view.menu.ActionMenuItem;
import com.actionbarsherlock.internal.view.menu.ActionMenuPresenter;
import com.actionbarsherlock.internal.view.menu.ActionMenuView;
import com.actionbarsherlock.internal.view.menu.MenuBuilder;
import com.actionbarsherlock.internal.view.menu.MenuItemImpl;
import com.actionbarsherlock.internal.view.menu.MenuPresenter;
import com.actionbarsherlock.internal.view.menu.MenuView;
import com.actionbarsherlock.internal.view.menu.SubMenuBuilder;
import com.actionbarsherlock.internal.widget.IcsAdapterView;
import com.actionbarsherlock.view.CollapsibleActionView;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;
import com.actionbarsherlock.widget.ActivityChooserView;
import com.samsung.android.sdk.richnotification.Utilities;

/* loaded from: classes.dex */
public class ActionBarView extends AbsActionBarView {
    private static final boolean DEBUG = false;
    private static final int DEFAULT_CUSTOM_GRAVITY = 19;
    public static final int DISPLAY_DEFAULT = 0;
    private static final int DISPLAY_RELAYOUT_MASK = 31;
    private static final String TAG = "ActionBarView";
    private ActionBar.OnNavigationListener mCallback;
    private ActionBarContextView mContextView;
    private View mCustomNavView;
    private int mDisplayOptions;
    View mExpandedActionView;
    private final View.OnClickListener mExpandedActionViewUpListener;
    private HomeView mExpandedHomeLayout;
    private ExpandedActionViewMenuPresenter mExpandedMenuPresenter;
    private HomeView mHomeLayout;
    private Drawable mIcon;
    private boolean mIncludeTabs;
    private int mIndeterminateProgressStyle;
    private IcsProgressBar mIndeterminateProgressView;
    private boolean mIsCollapsable;
    private boolean mIsCollapsed;
    private int mItemPadding;
    private IcsLinearLayout mListNavLayout;
    private Drawable mLogo;
    private ActionMenuItem mLogoNavItem;
    private final IcsAdapterView.OnItemSelectedListener mNavItemSelectedListener;
    private int mNavigationMode;
    private MenuBuilder mOptionsMenu;
    private int mProgressBarPadding;
    private int mProgressStyle;
    private IcsProgressBar mProgressView;
    private IcsSpinner mSpinner;
    private SpinnerAdapter mSpinnerAdapter;
    private CharSequence mSubtitle;
    private int mSubtitleStyleRes;
    private TextView mSubtitleView;
    private ScrollingTabContainerView mTabScrollView;
    private CharSequence mTitle;
    private LinearLayout mTitleLayout;
    private int mTitleStyleRes;
    private View mTitleUpView;
    private TextView mTitleView;
    private final View.OnClickListener mUpClickListener;
    private boolean mUserTitle;
    Window.Callback mWindowCallback;

    public ActionBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int loadLogoFromManifest;
        this.mDisplayOptions = -1;
        this.mNavItemSelectedListener = new IcsAdapterView.OnItemSelectedListener() { // from class: com.actionbarsherlock.internal.widget.ActionBarView.1
            @Override // com.actionbarsherlock.internal.widget.IcsAdapterView.OnItemSelectedListener
            public void onItemSelected(IcsAdapterView icsAdapterView, View view, int i, long j) {
                if (ActionBarView.this.mCallback != null) {
                    ActionBarView.this.mCallback.onNavigationItemSelected(i, j);
                }
            }

            @Override // com.actionbarsherlock.internal.widget.IcsAdapterView.OnItemSelectedListener
            public void onNothingSelected(IcsAdapterView icsAdapterView) {
            }
        };
        this.mExpandedActionViewUpListener = new View.OnClickListener() { // from class: com.actionbarsherlock.internal.widget.ActionBarView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MenuItemImpl menuItemImpl = ActionBarView.this.mExpandedMenuPresenter.mCurrentExpandedItem;
                if (menuItemImpl != null) {
                    menuItemImpl.collapseActionView();
                }
            }
        };
        this.mUpClickListener = new View.OnClickListener() { // from class: com.actionbarsherlock.internal.widget.ActionBarView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ActionBarView.this.mWindowCallback.onMenuItemSelected(0, ActionBarView.this.mLogoNavItem);
            }
        };
        setBackgroundResource(0);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SherlockActionBar, R.attr.actionBarStyle, 0);
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        PackageManager packageManager = context.getPackageManager();
        this.mNavigationMode = obtainStyledAttributes.getInt(R.styleable.SherlockActionBar_navigationMode, 0);
        this.mTitle = obtainStyledAttributes.getText(R.styleable.SherlockActionBar_title);
        this.mSubtitle = obtainStyledAttributes.getText(R.styleable.SherlockActionBar_subtitle);
        this.mLogo = obtainStyledAttributes.getDrawable(R.styleable.SherlockActionBar_logo);
        if (this.mLogo == null) {
            if (Build.VERSION.SDK_INT < 11) {
                if ((context instanceof Activity) && (loadLogoFromManifest = loadLogoFromManifest((Activity) context)) != 0) {
                    this.mLogo = context.getResources().getDrawable(loadLogoFromManifest);
                }
            } else {
                if (context instanceof Activity) {
                    try {
                        this.mLogo = packageManager.getActivityLogo(((Activity) context).getComponentName());
                    } catch (PackageManager.NameNotFoundException e) {
                        Log.e(TAG, "Activity component name not found!", e);
                    }
                }
                if (this.mLogo == null) {
                    this.mLogo = applicationInfo.loadLogo(packageManager);
                }
            }
        }
        this.mIcon = obtainStyledAttributes.getDrawable(R.styleable.SherlockActionBar_icon);
        if (this.mIcon == null) {
            if (context instanceof Activity) {
                try {
                    this.mIcon = packageManager.getActivityIcon(((Activity) context).getComponentName());
                } catch (PackageManager.NameNotFoundException e2) {
                    Log.e(TAG, "Activity component name not found!", e2);
                }
            }
            if (this.mIcon == null) {
                this.mIcon = applicationInfo.loadIcon(packageManager);
            }
        }
        LayoutInflater from = LayoutInflater.from(context);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.SherlockActionBar_homeLayout, R.layout.abs__action_bar_home);
        this.mHomeLayout = (HomeView) from.inflate(resourceId, (ViewGroup) this, false);
        this.mExpandedHomeLayout = (HomeView) from.inflate(resourceId, (ViewGroup) this, false);
        this.mExpandedHomeLayout.setUp(true);
        this.mExpandedHomeLayout.setOnClickListener(this.mExpandedActionViewUpListener);
        this.mExpandedHomeLayout.setContentDescription(getResources().getText(R.string.abs__action_bar_up_description));
        this.mTitleStyleRes = obtainStyledAttributes.getResourceId(R.styleable.SherlockActionBar_titleTextStyle, 0);
        this.mSubtitleStyleRes = obtainStyledAttributes.getResourceId(R.styleable.SherlockActionBar_subtitleTextStyle, 0);
        this.mProgressStyle = obtainStyledAttributes.getResourceId(R.styleable.SherlockActionBar_progressBarStyle, 0);
        this.mIndeterminateProgressStyle = obtainStyledAttributes.getResourceId(R.styleable.SherlockActionBar_indeterminateProgressStyle, 0);
        this.mProgressBarPadding = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.SherlockActionBar_progressBarPadding, 0);
        this.mItemPadding = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.SherlockActionBar_itemPadding, 0);
        setDisplayOptions(obtainStyledAttributes.getInt(R.styleable.SherlockActionBar_displayOptions, 0));
        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.SherlockActionBar_customNavigationLayout, 0);
        if (resourceId2 != 0) {
            this.mCustomNavView = from.inflate(resourceId2, (ViewGroup) this, false);
            this.mNavigationMode = 0;
            setDisplayOptions(this.mDisplayOptions | 16);
        }
        this.mContentHeight = obtainStyledAttributes.getLayoutDimension(R.styleable.SherlockActionBar_height, 0);
        obtainStyledAttributes.recycle();
        this.mLogoNavItem = new ActionMenuItem(context, 0, android.R.id.home, 0, 0, this.mTitle);
        this.mHomeLayout.setOnClickListener(this.mUpClickListener);
        this.mHomeLayout.setClickable(true);
        this.mHomeLayout.setFocusable(true);
    }

    private static int loadLogoFromManifest(Activity activity) {
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
                                        if ("logo".equals(attributeName)) {
                                            num = Integer.valueOf(openXmlResourceParser.getAttributeResourceValue(attributeCount, 0));
                                        } else if (Utilities.DB_KEY_IMAGE_NAME.equals(attributeName)) {
                                            str2 = ActionBarSherlockCompat.cleanActivityName(str, openXmlResourceParser.getAttributeValue(attributeCount));
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
                                    if ("logo".equals(openXmlResourceParser.getAttributeName(attributeCount2))) {
                                        i = openXmlResourceParser.getAttributeResourceValue(attributeCount2, 0);
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

    @Override // com.actionbarsherlock.internal.widget.AbsActionBarView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mTitleView = null;
        this.mSubtitleView = null;
        this.mTitleUpView = null;
        if (this.mTitleLayout != null && this.mTitleLayout.getParent() == this) {
            removeView(this.mTitleLayout);
        }
        this.mTitleLayout = null;
        if ((this.mDisplayOptions & 8) != 0) {
            initTitle();
        }
        if (this.mTabScrollView != null && this.mIncludeTabs) {
            ViewGroup.LayoutParams layoutParams = this.mTabScrollView.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.width = -2;
                layoutParams.height = -1;
            }
            this.mTabScrollView.setAllowCollapse(true);
        }
    }

    public void setWindowCallback(Window.Callback callback) {
        this.mWindowCallback = callback;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.hideOverflowMenu();
            this.mActionMenuPresenter.hideSubMenus();
        }
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public void initProgress() {
        this.mProgressView = new IcsProgressBar(this.mContext, null, 0, this.mProgressStyle);
        this.mProgressView.setId(R.id.abs__progress_horizontal);
        this.mProgressView.setMax(10000);
        addView(this.mProgressView);
    }

    public void initIndeterminateProgress() {
        this.mIndeterminateProgressView = new IcsProgressBar(this.mContext, null, 0, this.mIndeterminateProgressStyle);
        this.mIndeterminateProgressView.setId(R.id.abs__progress_circular);
        addView(this.mIndeterminateProgressView);
    }

    @Override // com.actionbarsherlock.internal.widget.AbsActionBarView
    public void setSplitActionBar(boolean z) {
        if (this.mSplitActionBar != z) {
            if (this.mMenuView != null) {
                ViewGroup viewGroup = (ViewGroup) this.mMenuView.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView(this.mMenuView);
                }
                if (z) {
                    if (this.mSplitView != null) {
                        this.mSplitView.addView(this.mMenuView);
                    }
                } else {
                    addView(this.mMenuView);
                }
            }
            if (this.mSplitView != null) {
                this.mSplitView.setVisibility(z ? 0 : 8);
            }
            super.setSplitActionBar(z);
        }
    }

    public boolean isSplitActionBar() {
        return this.mSplitActionBar;
    }

    public boolean hasEmbeddedTabs() {
        return this.mIncludeTabs;
    }

    public void setEmbeddedTabView(ScrollingTabContainerView scrollingTabContainerView) {
        if (this.mTabScrollView != null) {
            removeView(this.mTabScrollView);
        }
        this.mTabScrollView = scrollingTabContainerView;
        this.mIncludeTabs = scrollingTabContainerView != null;
        if (this.mIncludeTabs && this.mNavigationMode == 2) {
            addView(this.mTabScrollView);
            ViewGroup.LayoutParams layoutParams = this.mTabScrollView.getLayoutParams();
            layoutParams.width = -2;
            layoutParams.height = -1;
            scrollingTabContainerView.setAllowCollapse(true);
        }
    }

    public void setCallback(ActionBar.OnNavigationListener onNavigationListener) {
        this.mCallback = onNavigationListener;
    }

    public void setMenu(Menu menu, MenuPresenter.Callback callback) {
        ActionMenuView actionMenuView;
        ViewGroup viewGroup;
        if (menu != this.mOptionsMenu) {
            if (this.mOptionsMenu != null) {
                this.mOptionsMenu.removeMenuPresenter(this.mActionMenuPresenter);
                this.mOptionsMenu.removeMenuPresenter(this.mExpandedMenuPresenter);
            }
            MenuBuilder menuBuilder = (MenuBuilder) menu;
            this.mOptionsMenu = menuBuilder;
            if (this.mMenuView != null && (viewGroup = (ViewGroup) this.mMenuView.getParent()) != null) {
                viewGroup.removeView(this.mMenuView);
            }
            if (this.mActionMenuPresenter == null) {
                this.mActionMenuPresenter = new ActionMenuPresenter(this.mContext);
                this.mActionMenuPresenter.setCallback(callback);
                this.mActionMenuPresenter.setId(R.id.abs__action_menu_presenter);
                this.mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter();
            }
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
            if (!this.mSplitActionBar) {
                this.mActionMenuPresenter.setExpandedActionViewsExclusive(ResourcesCompat.getResources_getBoolean(getContext(), R.bool.abs__action_bar_expanded_action_views_exclusive));
                configPresenters(menuBuilder);
                actionMenuView = (ActionMenuView) this.mActionMenuPresenter.getMenuView(this);
                ViewGroup viewGroup2 = (ViewGroup) actionMenuView.getParent();
                if (viewGroup2 != null && viewGroup2 != this) {
                    viewGroup2.removeView(actionMenuView);
                }
                addView(actionMenuView, layoutParams);
            } else {
                this.mActionMenuPresenter.setExpandedActionViewsExclusive(false);
                this.mActionMenuPresenter.setWidthLimit(getContext().getResources().getDisplayMetrics().widthPixels, true);
                this.mActionMenuPresenter.setItemLimit(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
                layoutParams.width = -1;
                configPresenters(menuBuilder);
                actionMenuView = (ActionMenuView) this.mActionMenuPresenter.getMenuView(this);
                if (this.mSplitView != null) {
                    ViewGroup viewGroup3 = (ViewGroup) actionMenuView.getParent();
                    if (viewGroup3 != null && viewGroup3 != this.mSplitView) {
                        viewGroup3.removeView(actionMenuView);
                    }
                    actionMenuView.setVisibility(getAnimatedVisibility());
                    this.mSplitView.addView(actionMenuView, layoutParams);
                } else {
                    actionMenuView.setLayoutParams(layoutParams);
                }
            }
            this.mMenuView = actionMenuView;
        }
    }

    private void configPresenters(MenuBuilder menuBuilder) {
        if (menuBuilder != null) {
            menuBuilder.addMenuPresenter(this.mActionMenuPresenter);
            menuBuilder.addMenuPresenter(this.mExpandedMenuPresenter);
        } else {
            this.mActionMenuPresenter.initForMenu(this.mContext, null);
            this.mExpandedMenuPresenter.initForMenu(this.mContext, null);
            this.mActionMenuPresenter.updateMenuView(true);
            this.mExpandedMenuPresenter.updateMenuView(true);
        }
    }

    public boolean hasExpandedActionView() {
        return (this.mExpandedMenuPresenter == null || this.mExpandedMenuPresenter.mCurrentExpandedItem == null) ? false : true;
    }

    public void collapseActionView() {
        MenuItemImpl menuItemImpl = this.mExpandedMenuPresenter == null ? null : this.mExpandedMenuPresenter.mCurrentExpandedItem;
        if (menuItemImpl != null) {
            menuItemImpl.collapseActionView();
        }
    }

    public void setCustomNavigationView(View view) {
        boolean z = (this.mDisplayOptions & 16) != 0;
        if (this.mCustomNavView != null && z) {
            removeView(this.mCustomNavView);
        }
        this.mCustomNavView = view;
        if (this.mCustomNavView != null && z) {
            addView(this.mCustomNavView);
        }
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public void setTitle(CharSequence charSequence) {
        this.mUserTitle = true;
        setTitleImpl(charSequence);
    }

    public void setWindowTitle(CharSequence charSequence) {
        if (!this.mUserTitle) {
            setTitleImpl(charSequence);
        }
    }

    private void setTitleImpl(CharSequence charSequence) {
        this.mTitle = charSequence;
        if (this.mTitleView != null) {
            this.mTitleView.setText(charSequence);
            this.mTitleLayout.setVisibility(this.mExpandedActionView == null && (this.mDisplayOptions & 8) != 0 && (!TextUtils.isEmpty(this.mTitle) || !TextUtils.isEmpty(this.mSubtitle)) ? 0 : 8);
        }
        if (this.mLogoNavItem != null) {
            this.mLogoNavItem.setTitle(charSequence);
        }
    }

    public CharSequence getSubtitle() {
        return this.mSubtitle;
    }

    public void setSubtitle(CharSequence charSequence) {
        this.mSubtitle = charSequence;
        if (this.mSubtitleView != null) {
            this.mSubtitleView.setText(charSequence);
            this.mSubtitleView.setVisibility(charSequence != null ? 0 : 8);
            this.mTitleLayout.setVisibility(this.mExpandedActionView == null && (this.mDisplayOptions & 8) != 0 && (!TextUtils.isEmpty(this.mTitle) || !TextUtils.isEmpty(this.mSubtitle)) ? 0 : 8);
        }
    }

    public void setHomeButtonEnabled(boolean z) {
        this.mHomeLayout.setEnabled(z);
        this.mHomeLayout.setFocusable(z);
        if (!z) {
            this.mHomeLayout.setContentDescription(null);
        } else if ((this.mDisplayOptions & 4) != 0) {
            this.mHomeLayout.setContentDescription(this.mContext.getResources().getText(R.string.abs__action_bar_up_description));
        } else {
            this.mHomeLayout.setContentDescription(this.mContext.getResources().getText(R.string.abs__action_bar_home_description));
        }
    }

    public void setDisplayOptions(int i) {
        int i2 = 8;
        int i3 = this.mDisplayOptions != -1 ? this.mDisplayOptions ^ i : -1;
        this.mDisplayOptions = i;
        if ((i3 & DISPLAY_RELAYOUT_MASK) != 0) {
            boolean z = (i & 2) != 0;
            this.mHomeLayout.setVisibility((z && this.mExpandedActionView == null) ? 0 : 8);
            if ((i3 & 4) != 0) {
                boolean z2 = (i & 4) != 0;
                this.mHomeLayout.setUp(z2);
                if (z2) {
                    setHomeButtonEnabled(true);
                }
            }
            if ((i3 & 1) != 0) {
                this.mHomeLayout.setIcon(this.mLogo != null && (i & 1) != 0 ? this.mLogo : this.mIcon);
            }
            if ((i3 & 8) != 0) {
                if ((i & 8) != 0) {
                    initTitle();
                } else {
                    removeView(this.mTitleLayout);
                }
            }
            if (this.mTitleLayout != null && (i3 & 6) != 0) {
                boolean z3 = (this.mDisplayOptions & 4) != 0;
                View view = this.mTitleUpView;
                if (!z) {
                    i2 = z3 ? 0 : 4;
                }
                view.setVisibility(i2);
                this.mTitleLayout.setEnabled(!z && z3);
            }
            if ((i3 & 16) != 0 && this.mCustomNavView != null) {
                if ((i & 16) != 0) {
                    addView(this.mCustomNavView);
                } else {
                    removeView(this.mCustomNavView);
                }
            }
            requestLayout();
        } else {
            invalidate();
        }
        if (!this.mHomeLayout.isEnabled()) {
            this.mHomeLayout.setContentDescription(null);
        } else if ((i & 4) != 0) {
            this.mHomeLayout.setContentDescription(this.mContext.getResources().getText(R.string.abs__action_bar_up_description));
        } else {
            this.mHomeLayout.setContentDescription(this.mContext.getResources().getText(R.string.abs__action_bar_home_description));
        }
    }

    public void setIcon(Drawable drawable) {
        this.mIcon = drawable;
        if (drawable != null) {
            if ((this.mDisplayOptions & 1) == 0 || this.mLogo == null) {
                this.mHomeLayout.setIcon(drawable);
            }
        }
    }

    public void setIcon(int i) {
        setIcon(this.mContext.getResources().getDrawable(i));
    }

    public void setLogo(Drawable drawable) {
        this.mLogo = drawable;
        if (drawable != null && (this.mDisplayOptions & 1) != 0) {
            this.mHomeLayout.setIcon(drawable);
        }
    }

    public void setLogo(int i) {
        setLogo(this.mContext.getResources().getDrawable(i));
    }

    public void setNavigationMode(int i) {
        int i2 = this.mNavigationMode;
        if (i != i2) {
            switch (i2) {
                case 1:
                    if (this.mListNavLayout != null) {
                        removeView(this.mListNavLayout);
                        break;
                    }
                    break;
                case 2:
                    if (this.mTabScrollView != null && this.mIncludeTabs) {
                        removeView(this.mTabScrollView);
                        break;
                    }
                    break;
            }
            switch (i) {
                case 1:
                    if (this.mSpinner == null) {
                        this.mSpinner = new IcsSpinner(this.mContext, null, R.attr.actionDropDownStyle);
                        this.mListNavLayout = (IcsLinearLayout) LayoutInflater.from(this.mContext).inflate(R.layout.abs__action_bar_tab_bar_view, (ViewGroup) null);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
                        layoutParams.gravity = 17;
                        this.mListNavLayout.addView(this.mSpinner, layoutParams);
                    }
                    if (this.mSpinner.getAdapter() != this.mSpinnerAdapter) {
                        this.mSpinner.setAdapter(this.mSpinnerAdapter);
                    }
                    this.mSpinner.setOnItemSelectedListener(this.mNavItemSelectedListener);
                    addView(this.mListNavLayout);
                    break;
                case 2:
                    if (this.mTabScrollView != null && this.mIncludeTabs) {
                        addView(this.mTabScrollView);
                        break;
                    }
                    break;
            }
            this.mNavigationMode = i;
            requestLayout();
        }
    }

    public void setDropdownAdapter(SpinnerAdapter spinnerAdapter) {
        this.mSpinnerAdapter = spinnerAdapter;
        if (this.mSpinner != null) {
            this.mSpinner.setAdapter(spinnerAdapter);
        }
    }

    public SpinnerAdapter getDropdownAdapter() {
        return this.mSpinnerAdapter;
    }

    public void setDropdownSelectedPosition(int i) {
        this.mSpinner.setSelection(i);
    }

    public int getDropdownSelectedPosition() {
        return this.mSpinner.getSelectedItemPosition();
    }

    public View getCustomNavigationView() {
        return this.mCustomNavView;
    }

    public int getNavigationMode() {
        return this.mNavigationMode;
    }

    public int getDisplayOptions() {
        return this.mDisplayOptions;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ActionBar.LayoutParams(19);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        ViewParent parent;
        super.onFinishInflate();
        addView(this.mHomeLayout);
        if (this.mCustomNavView != null && (this.mDisplayOptions & 16) != 0 && (parent = this.mCustomNavView.getParent()) != this) {
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.mCustomNavView);
            }
            addView(this.mCustomNavView);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initTitle() {
        int i;
        if (this.mTitleLayout == null) {
            this.mTitleLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.abs__action_bar_title_item, (ViewGroup) this, false);
            this.mTitleView = (TextView) this.mTitleLayout.findViewById(R.id.abs__action_bar_title);
            this.mSubtitleView = (TextView) this.mTitleLayout.findViewById(R.id.abs__action_bar_subtitle);
            this.mTitleUpView = this.mTitleLayout.findViewById(R.id.abs__up);
            this.mTitleLayout.setOnClickListener(this.mUpClickListener);
            if (this.mTitleStyleRes != 0) {
                this.mTitleView.setTextAppearance(this.mContext, this.mTitleStyleRes);
            }
            if (this.mTitle != null) {
                this.mTitleView.setText(this.mTitle);
            }
            if (this.mSubtitleStyleRes != 0) {
                this.mSubtitleView.setTextAppearance(this.mContext, this.mSubtitleStyleRes);
            }
            if (this.mSubtitle != null) {
                this.mSubtitleView.setText(this.mSubtitle);
                this.mSubtitleView.setVisibility(0);
            }
            boolean z = (this.mDisplayOptions & 4) != 0;
            boolean z2 = (this.mDisplayOptions & 2) != 0;
            View view = this.mTitleUpView;
            if (z2) {
                i = 8;
            } else {
                i = z ? 0 : 4;
            }
            view.setVisibility(i);
            this.mTitleLayout.setEnabled(z && !z2);
        }
        addView(this.mTitleLayout);
        if (this.mExpandedActionView != null || (TextUtils.isEmpty(this.mTitle) && TextUtils.isEmpty(this.mSubtitle))) {
            this.mTitleLayout.setVisibility(8);
        }
    }

    public void setContextView(ActionBarContextView actionBarContextView) {
        this.mContextView = actionBarContextView;
    }

    public void setCollapsable(boolean z) {
        this.mIsCollapsable = z;
    }

    public boolean isCollapsed() {
        return this.mIsCollapsed;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:105:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0364 A[Catch: Throwable -> 0x0079, TryCatch #0 {Throwable -> 0x0079, blocks: (B:2:0x0000, B:6:0x0013, B:8:0x0021, B:10:0x0027, B:14:0x0031, B:13:0x0033, B:19:0x0038, B:22:0x0045, B:24:0x0052, B:25:0x0078, B:26:0x0082, B:28:0x008a, B:29:0x00b0, B:30:0x00b1, B:32:0x00bb, B:33:0x00c0, B:35:0x00e6, B:36:0x00eb, B:38:0x00f3, B:40:0x00fb, B:41:0x0101, B:42:0x0120, B:44:0x0126, B:46:0x0132, B:47:0x014b, B:49:0x0151, B:51:0x015d, B:52:0x0177, B:54:0x017d, B:56:0x0189, B:59:0x0193, B:61:0x0199, B:62:0x019d, B:63:0x0292, B:66:0x029a, B:67:0x02a0, B:68:0x01a1, B:70:0x01a8, B:72:0x01af, B:74:0x01bd, B:77:0x01c6, B:78:0x01d2, B:81:0x01db, B:83:0x01e1, B:84:0x01e7, B:87:0x01f6, B:89:0x01fb, B:90:0x0201, B:92:0x0208, B:93:0x020a, B:95:0x020f, B:97:0x0214, B:98:0x021a, B:99:0x022e, B:102:0x0236, B:103:0x0258, B:107:0x0263, B:111:0x0271, B:114:0x0359, B:115:0x035e, B:117:0x0364, B:118:0x036f, B:120:0x0375, B:122:0x0381, B:126:0x03a2, B:131:0x033d, B:139:0x0324, B:141:0x032c, B:143:0x0332, B:145:0x02d6, B:146:0x02db, B:149:0x02e3, B:150:0x02e9, B:151:0x031f, B:155:0x0284, B:157:0x027d, B:158:0x0276), top: B:1:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x03a2 A[Catch: Throwable -> 0x0079, TRY_LEAVE, TryCatch #0 {Throwable -> 0x0079, blocks: (B:2:0x0000, B:6:0x0013, B:8:0x0021, B:10:0x0027, B:14:0x0031, B:13:0x0033, B:19:0x0038, B:22:0x0045, B:24:0x0052, B:25:0x0078, B:26:0x0082, B:28:0x008a, B:29:0x00b0, B:30:0x00b1, B:32:0x00bb, B:33:0x00c0, B:35:0x00e6, B:36:0x00eb, B:38:0x00f3, B:40:0x00fb, B:41:0x0101, B:42:0x0120, B:44:0x0126, B:46:0x0132, B:47:0x014b, B:49:0x0151, B:51:0x015d, B:52:0x0177, B:54:0x017d, B:56:0x0189, B:59:0x0193, B:61:0x0199, B:62:0x019d, B:63:0x0292, B:66:0x029a, B:67:0x02a0, B:68:0x01a1, B:70:0x01a8, B:72:0x01af, B:74:0x01bd, B:77:0x01c6, B:78:0x01d2, B:81:0x01db, B:83:0x01e1, B:84:0x01e7, B:87:0x01f6, B:89:0x01fb, B:90:0x0201, B:92:0x0208, B:93:0x020a, B:95:0x020f, B:97:0x0214, B:98:0x021a, B:99:0x022e, B:102:0x0236, B:103:0x0258, B:107:0x0263, B:111:0x0271, B:114:0x0359, B:115:0x035e, B:117:0x0364, B:118:0x036f, B:120:0x0375, B:122:0x0381, B:126:0x03a2, B:131:0x033d, B:139:0x0324, B:141:0x032c, B:143:0x0332, B:145:0x02d6, B:146:0x02db, B:149:0x02e3, B:150:0x02e9, B:151:0x031f, B:155:0x0284, B:157:0x027d, B:158:0x0276), top: B:1:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:138:0x03af  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0324 A[Catch: Throwable -> 0x0079, TryCatch #0 {Throwable -> 0x0079, blocks: (B:2:0x0000, B:6:0x0013, B:8:0x0021, B:10:0x0027, B:14:0x0031, B:13:0x0033, B:19:0x0038, B:22:0x0045, B:24:0x0052, B:25:0x0078, B:26:0x0082, B:28:0x008a, B:29:0x00b0, B:30:0x00b1, B:32:0x00bb, B:33:0x00c0, B:35:0x00e6, B:36:0x00eb, B:38:0x00f3, B:40:0x00fb, B:41:0x0101, B:42:0x0120, B:44:0x0126, B:46:0x0132, B:47:0x014b, B:49:0x0151, B:51:0x015d, B:52:0x0177, B:54:0x017d, B:56:0x0189, B:59:0x0193, B:61:0x0199, B:62:0x019d, B:63:0x0292, B:66:0x029a, B:67:0x02a0, B:68:0x01a1, B:70:0x01a8, B:72:0x01af, B:74:0x01bd, B:77:0x01c6, B:78:0x01d2, B:81:0x01db, B:83:0x01e1, B:84:0x01e7, B:87:0x01f6, B:89:0x01fb, B:90:0x0201, B:92:0x0208, B:93:0x020a, B:95:0x020f, B:97:0x0214, B:98:0x021a, B:99:0x022e, B:102:0x0236, B:103:0x0258, B:107:0x0263, B:111:0x0271, B:114:0x0359, B:115:0x035e, B:117:0x0364, B:118:0x036f, B:120:0x0375, B:122:0x0381, B:126:0x03a2, B:131:0x033d, B:139:0x0324, B:141:0x032c, B:143:0x0332, B:145:0x02d6, B:146:0x02db, B:149:0x02e3, B:150:0x02e9, B:151:0x031f, B:155:0x0284, B:157:0x027d, B:158:0x0276), top: B:1:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01a8 A[Catch: Throwable -> 0x0079, TryCatch #0 {Throwable -> 0x0079, blocks: (B:2:0x0000, B:6:0x0013, B:8:0x0021, B:10:0x0027, B:14:0x0031, B:13:0x0033, B:19:0x0038, B:22:0x0045, B:24:0x0052, B:25:0x0078, B:26:0x0082, B:28:0x008a, B:29:0x00b0, B:30:0x00b1, B:32:0x00bb, B:33:0x00c0, B:35:0x00e6, B:36:0x00eb, B:38:0x00f3, B:40:0x00fb, B:41:0x0101, B:42:0x0120, B:44:0x0126, B:46:0x0132, B:47:0x014b, B:49:0x0151, B:51:0x015d, B:52:0x0177, B:54:0x017d, B:56:0x0189, B:59:0x0193, B:61:0x0199, B:62:0x019d, B:63:0x0292, B:66:0x029a, B:67:0x02a0, B:68:0x01a1, B:70:0x01a8, B:72:0x01af, B:74:0x01bd, B:77:0x01c6, B:78:0x01d2, B:81:0x01db, B:83:0x01e1, B:84:0x01e7, B:87:0x01f6, B:89:0x01fb, B:90:0x0201, B:92:0x0208, B:93:0x020a, B:95:0x020f, B:97:0x0214, B:98:0x021a, B:99:0x022e, B:102:0x0236, B:103:0x0258, B:107:0x0263, B:111:0x0271, B:114:0x0359, B:115:0x035e, B:117:0x0364, B:118:0x036f, B:120:0x0375, B:122:0x0381, B:126:0x03a2, B:131:0x033d, B:139:0x0324, B:141:0x032c, B:143:0x0332, B:145:0x02d6, B:146:0x02db, B:149:0x02e3, B:150:0x02e9, B:151:0x031f, B:155:0x0284, B:157:0x027d, B:158:0x0276), top: B:1:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01af A[Catch: Throwable -> 0x0079, TryCatch #0 {Throwable -> 0x0079, blocks: (B:2:0x0000, B:6:0x0013, B:8:0x0021, B:10:0x0027, B:14:0x0031, B:13:0x0033, B:19:0x0038, B:22:0x0045, B:24:0x0052, B:25:0x0078, B:26:0x0082, B:28:0x008a, B:29:0x00b0, B:30:0x00b1, B:32:0x00bb, B:33:0x00c0, B:35:0x00e6, B:36:0x00eb, B:38:0x00f3, B:40:0x00fb, B:41:0x0101, B:42:0x0120, B:44:0x0126, B:46:0x0132, B:47:0x014b, B:49:0x0151, B:51:0x015d, B:52:0x0177, B:54:0x017d, B:56:0x0189, B:59:0x0193, B:61:0x0199, B:62:0x019d, B:63:0x0292, B:66:0x029a, B:67:0x02a0, B:68:0x01a1, B:70:0x01a8, B:72:0x01af, B:74:0x01bd, B:77:0x01c6, B:78:0x01d2, B:81:0x01db, B:83:0x01e1, B:84:0x01e7, B:87:0x01f6, B:89:0x01fb, B:90:0x0201, B:92:0x0208, B:93:0x020a, B:95:0x020f, B:97:0x0214, B:98:0x021a, B:99:0x022e, B:102:0x0236, B:103:0x0258, B:107:0x0263, B:111:0x0271, B:114:0x0359, B:115:0x035e, B:117:0x0364, B:118:0x036f, B:120:0x0375, B:122:0x0381, B:126:0x03a2, B:131:0x033d, B:139:0x0324, B:141:0x032c, B:143:0x0332, B:145:0x02d6, B:146:0x02db, B:149:0x02e3, B:150:0x02e9, B:151:0x031f, B:155:0x0284, B:157:0x027d, B:158:0x0276), top: B:1:0x0000 }] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onMeasure(int r20, int r21) {
        /*
            Method dump skipped, instructions count: 964
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.actionbarsherlock.internal.widget.ActionBarView.onMeasure(int, int):void");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:82:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0155  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onLayout(boolean r11, int r12, int r13, int r14, int r15) {
        /*
            Method dump skipped, instructions count: 510
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.actionbarsherlock.internal.widget.ActionBarView.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ActionBar.LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams == null) {
            return generateDefaultLayoutParams();
        }
        return layoutParams;
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.mExpandedMenuPresenter != null && this.mExpandedMenuPresenter.mCurrentExpandedItem != null) {
            savedState.expandedMenuItemId = this.mExpandedMenuPresenter.mCurrentExpandedItem.getItemId();
        }
        savedState.isOverflowOpen = isOverflowMenuShowing();
        return savedState;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem findItem;
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.expandedMenuItemId != 0 && this.mExpandedMenuPresenter != null && this.mOptionsMenu != null && (findItem = this.mOptionsMenu.findItem(savedState.expandedMenuItemId)) != null) {
            findItem.expandActionView();
        }
        if (savedState.isOverflowOpen) {
            postShowOverflowMenu();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.actionbarsherlock.internal.widget.ActionBarView.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int expandedMenuItemId;
        boolean isOverflowOpen;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.expandedMenuItemId = parcel.readInt();
            this.isOverflowOpen = parcel.readInt() != 0;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.expandedMenuItemId);
            parcel.writeInt(this.isOverflowOpen ? 1 : 0);
        }
    }

    /* loaded from: classes.dex */
    public class HomeView extends FrameLayout {
        private ImageView mIconView;
        private View mUpView;
        private int mUpWidth;

        public HomeView(Context context) {
            this(context, null);
        }

        public HomeView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public void setUp(boolean z) {
            this.mUpView.setVisibility(z ? 0 : 8);
        }

        public void setIcon(Drawable drawable) {
            this.mIconView.setImageDrawable(drawable);
        }

        @Override // android.view.View
        public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            onPopulateAccessibilityEvent(accessibilityEvent);
            return true;
        }

        @Override // android.view.View
        public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            if (Build.VERSION.SDK_INT >= 14) {
                super.onPopulateAccessibilityEvent(accessibilityEvent);
            }
            CharSequence contentDescription = getContentDescription();
            if (!TextUtils.isEmpty(contentDescription)) {
                accessibilityEvent.getText().add(contentDescription);
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchHoverEvent(MotionEvent motionEvent) {
            return onHoverEvent(motionEvent);
        }

        @Override // android.view.View
        protected void onFinishInflate() {
            this.mUpView = findViewById(R.id.abs__up);
            this.mIconView = (ImageView) findViewById(R.id.abs__home);
        }

        public int getLeftOffset() {
            if (this.mUpView.getVisibility() == 8) {
                return this.mUpWidth;
            }
            return 0;
        }

        @Override // android.widget.FrameLayout, android.view.View
        protected void onMeasure(int i, int i2) {
            measureChildWithMargins(this.mUpView, i, 0, i2, 0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mUpView.getLayoutParams();
            this.mUpWidth = layoutParams.leftMargin + this.mUpView.getMeasuredWidth() + layoutParams.rightMargin;
            int i3 = this.mUpView.getVisibility() == 8 ? 0 : this.mUpWidth;
            int measuredHeight = layoutParams.topMargin + this.mUpView.getMeasuredHeight() + layoutParams.bottomMargin;
            measureChildWithMargins(this.mIconView, i, i3, i2, 0);
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.mIconView.getLayoutParams();
            int measuredWidth = i3 + layoutParams2.leftMargin + this.mIconView.getMeasuredWidth() + layoutParams2.rightMargin;
            int max = Math.max(measuredHeight, layoutParams2.bottomMargin + layoutParams2.topMargin + this.mIconView.getMeasuredHeight());
            int mode = View.MeasureSpec.getMode(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i);
            int size2 = View.MeasureSpec.getSize(i2);
            switch (mode) {
                case ExploreByTouchHelper.INVALID_ID /* -2147483648 */:
                    size = Math.min(measuredWidth, size);
                    break;
                case 1073741824:
                    break;
                default:
                    size = measuredWidth;
                    break;
            }
            switch (mode2) {
                case ExploreByTouchHelper.INVALID_ID /* -2147483648 */:
                    size2 = Math.min(max, size2);
                    break;
                case 1073741824:
                    break;
                default:
                    size2 = max;
                    break;
            }
            setMeasuredDimension(size, size2);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            int i5 = 0;
            int i6 = (i4 - i2) / 2;
            if (this.mUpView.getVisibility() != 8) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mUpView.getLayoutParams();
                int measuredHeight = this.mUpView.getMeasuredHeight();
                int measuredWidth = this.mUpView.getMeasuredWidth();
                int i7 = i6 - (measuredHeight / 2);
                this.mUpView.layout(0, i7, measuredWidth, measuredHeight + i7);
                int i8 = layoutParams.rightMargin + layoutParams.leftMargin + measuredWidth;
                i += i8;
                i5 = i8;
            }
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.mIconView.getLayoutParams();
            int measuredHeight2 = this.mIconView.getMeasuredHeight();
            int measuredWidth2 = this.mIconView.getMeasuredWidth();
            int max = i5 + Math.max(layoutParams2.leftMargin, ((i3 - i) / 2) - (measuredWidth2 / 2));
            int max2 = Math.max(layoutParams2.topMargin, i6 - (measuredHeight2 / 2));
            this.mIconView.layout(max, max2, measuredWidth2 + max, measuredHeight2 + max2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ExpandedActionViewMenuPresenter implements MenuPresenter {
        MenuItemImpl mCurrentExpandedItem;
        MenuBuilder mMenu;

        private ExpandedActionViewMenuPresenter() {
        }

        @Override // com.actionbarsherlock.internal.view.menu.MenuPresenter
        public void initForMenu(Context context, MenuBuilder menuBuilder) {
            if (this.mMenu != null && this.mCurrentExpandedItem != null) {
                this.mMenu.collapseItemActionView(this.mCurrentExpandedItem);
            }
            this.mMenu = menuBuilder;
        }

        @Override // com.actionbarsherlock.internal.view.menu.MenuPresenter
        public MenuView getMenuView(ViewGroup viewGroup) {
            return null;
        }

        @Override // com.actionbarsherlock.internal.view.menu.MenuPresenter
        public void updateMenuView(boolean z) {
            boolean z2 = false;
            if (this.mCurrentExpandedItem != null) {
                if (this.mMenu != null) {
                    int size = this.mMenu.size();
                    int i = 0;
                    while (true) {
                        if (i >= size) {
                            break;
                        }
                        if (this.mMenu.getItem(i) != this.mCurrentExpandedItem) {
                            i++;
                        } else {
                            z2 = true;
                            break;
                        }
                    }
                }
                if (!z2) {
                    collapseItemActionView(this.mMenu, this.mCurrentExpandedItem);
                }
            }
        }

        @Override // com.actionbarsherlock.internal.view.menu.MenuPresenter
        public void setCallback(MenuPresenter.Callback callback) {
        }

        @Override // com.actionbarsherlock.internal.view.menu.MenuPresenter
        public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
            return false;
        }

        @Override // com.actionbarsherlock.internal.view.menu.MenuPresenter
        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        }

        @Override // com.actionbarsherlock.internal.view.menu.MenuPresenter
        public boolean flagActionItems() {
            return false;
        }

        @Override // com.actionbarsherlock.internal.view.menu.MenuPresenter
        public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
            ActionBarView.this.mExpandedActionView = menuItemImpl.getActionView();
            ActionBarView.this.mExpandedHomeLayout.setIcon(ActionBarView.this.mIcon.getConstantState().newDrawable());
            this.mCurrentExpandedItem = menuItemImpl;
            if (ActionBarView.this.mExpandedActionView.getParent() != ActionBarView.this) {
                ActionBarView.this.addView(ActionBarView.this.mExpandedActionView);
            }
            if (ActionBarView.this.mExpandedHomeLayout.getParent() != ActionBarView.this) {
                ActionBarView.this.addView(ActionBarView.this.mExpandedHomeLayout);
            }
            ActionBarView.this.mHomeLayout.setVisibility(8);
            if (ActionBarView.this.mTitleLayout != null) {
                ActionBarView.this.mTitleLayout.setVisibility(8);
            }
            if (ActionBarView.this.mTabScrollView != null) {
                ActionBarView.this.mTabScrollView.setVisibility(8);
            }
            if (ActionBarView.this.mSpinner != null) {
                ActionBarView.this.mSpinner.setVisibility(8);
            }
            if (ActionBarView.this.mCustomNavView != null) {
                ActionBarView.this.mCustomNavView.setVisibility(8);
            }
            ActionBarView.this.requestLayout();
            menuItemImpl.setActionViewExpanded(true);
            if (ActionBarView.this.mExpandedActionView instanceof CollapsibleActionView) {
                ((CollapsibleActionView) ActionBarView.this.mExpandedActionView).onActionViewExpanded();
            }
            return true;
        }

        @Override // com.actionbarsherlock.internal.view.menu.MenuPresenter
        public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
            if (ActionBarView.this.mExpandedActionView instanceof CollapsibleActionView) {
                ((CollapsibleActionView) ActionBarView.this.mExpandedActionView).onActionViewCollapsed();
            }
            ActionBarView.this.removeView(ActionBarView.this.mExpandedActionView);
            ActionBarView.this.removeView(ActionBarView.this.mExpandedHomeLayout);
            ActionBarView.this.mExpandedActionView = null;
            if ((ActionBarView.this.mDisplayOptions & 2) != 0) {
                ActionBarView.this.mHomeLayout.setVisibility(0);
            }
            if ((ActionBarView.this.mDisplayOptions & 8) != 0) {
                if (ActionBarView.this.mTitleLayout == null) {
                    ActionBarView.this.initTitle();
                } else {
                    ActionBarView.this.mTitleLayout.setVisibility(0);
                }
            }
            if (ActionBarView.this.mTabScrollView != null && ActionBarView.this.mNavigationMode == 2) {
                ActionBarView.this.mTabScrollView.setVisibility(0);
            }
            if (ActionBarView.this.mSpinner != null && ActionBarView.this.mNavigationMode == 1) {
                ActionBarView.this.mSpinner.setVisibility(0);
            }
            if (ActionBarView.this.mCustomNavView != null && (ActionBarView.this.mDisplayOptions & 16) != 0) {
                ActionBarView.this.mCustomNavView.setVisibility(0);
            }
            ActionBarView.this.mExpandedHomeLayout.setIcon(null);
            this.mCurrentExpandedItem = null;
            ActionBarView.this.requestLayout();
            menuItemImpl.setActionViewExpanded(false);
            return true;
        }

        @Override // com.actionbarsherlock.internal.view.menu.MenuPresenter
        public int getId() {
            return 0;
        }

        @Override // com.actionbarsherlock.internal.view.menu.MenuPresenter
        public Parcelable onSaveInstanceState() {
            return null;
        }

        @Override // com.actionbarsherlock.internal.view.menu.MenuPresenter
        public void onRestoreInstanceState(Parcelable parcelable) {
        }
    }
}
