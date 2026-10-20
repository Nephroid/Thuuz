package com.actionbarsherlock.internal.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageButton;
import com.actionbarsherlock.R;
import com.actionbarsherlock.internal.ResourcesCompat;
import com.actionbarsherlock.internal.view.View_HasStateListenerSupport;
import com.actionbarsherlock.internal.view.View_OnAttachStateChangeListener;
import com.actionbarsherlock.internal.view.menu.ActionMenuView;
import com.actionbarsherlock.internal.view.menu.MenuPresenter;
import com.actionbarsherlock.internal.view.menu.MenuView;
import com.actionbarsherlock.view.ActionProvider;
import com.actionbarsherlock.view.MenuItem;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public class ActionMenuPresenter extends BaseMenuPresenter implements ActionProvider.SubUiVisibilityListener {
    private final SparseBooleanArray mActionButtonGroups;
    private ActionButtonSubmenu mActionButtonPopup;
    private int mActionItemWidthLimit;
    private boolean mExpandedActionViewsExclusive;
    private int mMaxItems;
    private boolean mMaxItemsSet;
    private int mMinCellSize;
    int mOpenSubMenuId;
    private View mOverflowButton;
    private OverflowPopup mOverflowPopup;
    final PopupPresenterCallback mPopupPresenterCallback;
    private OpenOverflowRunnable mPostedOpenRunnable;
    private boolean mReserveOverflow;
    private boolean mReserveOverflowSet;
    private View mScrapActionButtonView;
    private boolean mStrictWidthLimit;
    private int mWidthLimit;
    private boolean mWidthLimitSet;

    public ActionMenuPresenter(Context context) {
        super(context, R.layout.abs__action_menu_layout, R.layout.abs__action_menu_item_layout);
        this.mActionButtonGroups = new SparseBooleanArray();
        this.mPopupPresenterCallback = new PopupPresenterCallback();
    }

    @Override // com.actionbarsherlock.internal.view.menu.BaseMenuPresenter, com.actionbarsherlock.internal.view.menu.MenuPresenter
    public void initForMenu(Context context, MenuBuilder menuBuilder) {
        super.initForMenu(context, menuBuilder);
        Resources resources = context.getResources();
        if (!this.mReserveOverflowSet) {
            this.mReserveOverflow = reserveOverflow(this.mContext);
        }
        if (!this.mWidthLimitSet) {
            this.mWidthLimit = resources.getDisplayMetrics().widthPixels / 2;
        }
        if (!this.mMaxItemsSet) {
            this.mMaxItems = ResourcesCompat.getResources_getInteger(context, R.integer.abs__max_action_buttons);
        }
        int i = this.mWidthLimit;
        if (this.mReserveOverflow) {
            if (this.mOverflowButton == null) {
                this.mOverflowButton = new OverflowMenuButton(this.mSystemContext);
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.mOverflowButton.measure(makeMeasureSpec, makeMeasureSpec);
            }
            i -= this.mOverflowButton.getMeasuredWidth();
        } else {
            this.mOverflowButton = null;
        }
        this.mActionItemWidthLimit = i;
        this.mMinCellSize = (int) (56.0f * resources.getDisplayMetrics().density);
        this.mScrapActionButtonView = null;
    }

    public static boolean reserveOverflow(Context context) {
        return Build.VERSION.SDK_INT < 14 ? Build.VERSION.SDK_INT >= 11 : !HasPermanentMenuKey.get(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class HasPermanentMenuKey {
        private HasPermanentMenuKey() {
        }

        public static boolean get(Context context) {
            return ViewConfiguration.get(context).hasPermanentMenuKey();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (!this.mMaxItemsSet) {
            this.mMaxItems = ResourcesCompat.getResources_getInteger(this.mContext, R.integer.abs__max_action_buttons);
            if (this.mMenu != null) {
                this.mMenu.onItemsChanged(true);
            }
        }
    }

    public void setWidthLimit(int i, boolean z) {
        this.mWidthLimit = i;
        this.mStrictWidthLimit = z;
        this.mWidthLimitSet = true;
    }

    public void setReserveOverflow(boolean z) {
        this.mReserveOverflow = z;
        this.mReserveOverflowSet = true;
    }

    public void setItemLimit(int i) {
        this.mMaxItems = i;
        this.mMaxItemsSet = true;
    }

    public void setExpandedActionViewsExclusive(boolean z) {
        this.mExpandedActionViewsExclusive = z;
    }

    @Override // com.actionbarsherlock.internal.view.menu.BaseMenuPresenter, com.actionbarsherlock.internal.view.menu.MenuPresenter
    public MenuView getMenuView(ViewGroup viewGroup) {
        MenuView menuView = super.getMenuView(viewGroup);
        ((ActionMenuView) menuView).setPresenter(this);
        return menuView;
    }

    @Override // com.actionbarsherlock.internal.view.menu.BaseMenuPresenter
    public View getItemView(MenuItemImpl menuItemImpl, View view, ViewGroup viewGroup) {
        View actionView = menuItemImpl.getActionView();
        if (actionView == null || menuItemImpl.hasCollapsibleActionView()) {
            if (!(view instanceof ActionMenuItemView)) {
                view = null;
            }
            actionView = super.getItemView(menuItemImpl, view, viewGroup);
        }
        actionView.setVisibility(menuItemImpl.isActionViewExpanded() ? 8 : 0);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    @Override // com.actionbarsherlock.internal.view.menu.BaseMenuPresenter
    public void bindItemView(MenuItemImpl menuItemImpl, MenuView.ItemView itemView) {
        itemView.initialize(menuItemImpl, 0);
        ((ActionMenuItemView) itemView).setItemInvoker((ActionMenuView) this.mMenuView);
    }

    @Override // com.actionbarsherlock.internal.view.menu.BaseMenuPresenter
    public boolean shouldIncludeItem(int i, MenuItemImpl menuItemImpl) {
        return menuItemImpl.isActionButton();
    }

    @Override // com.actionbarsherlock.internal.view.menu.BaseMenuPresenter, com.actionbarsherlock.internal.view.menu.MenuPresenter
    public void updateMenuView(boolean z) {
        boolean z2 = false;
        super.updateMenuView(z);
        if (this.mMenu != null) {
            ArrayList<MenuItemImpl> actionItems = this.mMenu.getActionItems();
            int size = actionItems.size();
            for (int i = 0; i < size; i++) {
                ActionProvider actionProvider = actionItems.get(i).getActionProvider();
                if (actionProvider != null) {
                    actionProvider.setSubUiVisibilityListener(this);
                }
            }
        }
        ArrayList<MenuItemImpl> nonActionItems = this.mMenu != null ? this.mMenu.getNonActionItems() : null;
        if (this.mReserveOverflow && nonActionItems != null) {
            int size2 = nonActionItems.size();
            if (size2 == 1) {
                z2 = !nonActionItems.get(0).isActionViewExpanded();
            } else {
                z2 = size2 > 0;
            }
        }
        if (z2) {
            if (this.mOverflowButton == null) {
                this.mOverflowButton = new OverflowMenuButton(this.mSystemContext);
            }
            ViewGroup viewGroup = (ViewGroup) this.mOverflowButton.getParent();
            if (viewGroup != this.mMenuView) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.mOverflowButton);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.mMenuView;
                actionMenuView.addView(this.mOverflowButton, actionMenuView.generateOverflowButtonLayoutParams());
            }
        } else if (this.mOverflowButton != null && this.mOverflowButton.getParent() == this.mMenuView) {
            ((ViewGroup) this.mMenuView).removeView(this.mOverflowButton);
        }
        ((ActionMenuView) this.mMenuView).setOverflowReserved(this.mReserveOverflow);
    }

    @Override // com.actionbarsherlock.internal.view.menu.BaseMenuPresenter
    public boolean filterLeftoverView(ViewGroup viewGroup, int i) {
        if (viewGroup.getChildAt(i) == this.mOverflowButton) {
            return false;
        }
        return super.filterLeftoverView(viewGroup, i);
    }

    @Override // com.actionbarsherlock.internal.view.menu.BaseMenuPresenter, com.actionbarsherlock.internal.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        SubMenuBuilder subMenuBuilder2 = subMenuBuilder;
        while (subMenuBuilder2.getParentMenu() != this.mMenu) {
            subMenuBuilder2 = (SubMenuBuilder) subMenuBuilder2.getParentMenu();
        }
        View findViewForItem = findViewForItem(subMenuBuilder2.getItem());
        if (findViewForItem == null) {
            if (this.mOverflowButton == null) {
                return false;
            }
            findViewForItem = this.mOverflowButton;
        }
        this.mOpenSubMenuId = subMenuBuilder.getItem().getItemId();
        this.mActionButtonPopup = new ActionButtonSubmenu(this.mContext, subMenuBuilder);
        this.mActionButtonPopup.setAnchorView(findViewForItem);
        this.mActionButtonPopup.show();
        super.onSubMenuSelected(subMenuBuilder);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private View findViewForItem(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup) this.mMenuView;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if ((childAt instanceof MenuView.ItemView) && ((MenuView.ItemView) childAt).getItemData() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    public boolean showOverflowMenu() {
        if (!this.mReserveOverflow || isOverflowMenuShowing() || this.mMenu == null || this.mMenuView == null || this.mPostedOpenRunnable != null || this.mMenu.getNonActionItems().isEmpty()) {
            return false;
        }
        this.mPostedOpenRunnable = new OpenOverflowRunnable(new OverflowPopup(this.mContext, this.mMenu, this.mOverflowButton, true));
        ((View) this.mMenuView).post(this.mPostedOpenRunnable);
        super.onSubMenuSelected(null);
        return true;
    }

    public boolean hideOverflowMenu() {
        if (this.mPostedOpenRunnable != null && this.mMenuView != null) {
            ((View) this.mMenuView).removeCallbacks(this.mPostedOpenRunnable);
            this.mPostedOpenRunnable = null;
            return true;
        }
        OverflowPopup overflowPopup = this.mOverflowPopup;
        if (overflowPopup != null) {
            overflowPopup.dismiss();
            return true;
        }
        return false;
    }

    public boolean dismissPopupMenus() {
        return hideOverflowMenu() | hideSubMenus();
    }

    public boolean hideSubMenus() {
        if (this.mActionButtonPopup == null) {
            return false;
        }
        this.mActionButtonPopup.dismiss();
        return true;
    }

    public boolean isOverflowMenuShowing() {
        return this.mOverflowPopup != null && this.mOverflowPopup.isShowing();
    }

    public boolean isOverflowReserved() {
        return this.mReserveOverflow;
    }

    @Override // com.actionbarsherlock.internal.view.menu.BaseMenuPresenter, com.actionbarsherlock.internal.view.menu.MenuPresenter
    public boolean flagActionItems() {
        int i;
        int i2;
        int i3;
        int i4;
        boolean z;
        int i5;
        int i6;
        int i7;
        int i8;
        boolean z2;
        ArrayList<MenuItemImpl> visibleItems = this.mMenu.getVisibleItems();
        int size = visibleItems.size();
        int i9 = this.mMaxItems;
        int i10 = this.mActionItemWidthLimit;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) this.mMenuView;
        int i11 = 0;
        int i12 = 0;
        boolean z3 = false;
        int i13 = 0;
        while (i13 < size) {
            MenuItemImpl menuItemImpl = visibleItems.get(i13);
            if (menuItemImpl.requiresActionButton()) {
                i11++;
            } else if (menuItemImpl.requestsActionButton()) {
                i12++;
            } else {
                z3 = true;
            }
            i13++;
            i9 = (this.mExpandedActionViewsExclusive && menuItemImpl.isActionViewExpanded()) ? 0 : i9;
        }
        if (this.mReserveOverflow && (z3 || i11 + i12 > i9)) {
            i9--;
        }
        int i14 = i9 - i11;
        SparseBooleanArray sparseBooleanArray = this.mActionButtonGroups;
        sparseBooleanArray.clear();
        int i15 = 0;
        if (!this.mStrictWidthLimit) {
            i = 0;
        } else {
            i15 = i10 / this.mMinCellSize;
            i = ((i10 % this.mMinCellSize) / i15) + this.mMinCellSize;
        }
        int i16 = 0;
        int i17 = 0;
        int i18 = i15;
        while (i16 < size) {
            MenuItemImpl menuItemImpl2 = visibleItems.get(i16);
            if (menuItemImpl2.requiresActionButton()) {
                View itemView = getItemView(menuItemImpl2, this.mScrapActionButtonView, viewGroup);
                if (this.mScrapActionButtonView == null) {
                    this.mScrapActionButtonView = itemView;
                }
                if (this.mStrictWidthLimit) {
                    i18 -= ActionMenuView.measureChildForCells(itemView, i, i18, makeMeasureSpec, 0);
                } else {
                    itemView.measure(makeMeasureSpec, makeMeasureSpec);
                }
                i2 = itemView.getMeasuredWidth();
                int i19 = i10 - i2;
                if (i17 != 0) {
                    i2 = i17;
                }
                int groupId = menuItemImpl2.getGroupId();
                if (groupId != 0) {
                    sparseBooleanArray.put(groupId, true);
                }
                menuItemImpl2.setIsActionButton(true);
                i3 = i19;
                i4 = i14;
            } else if (menuItemImpl2.requestsActionButton()) {
                int groupId2 = menuItemImpl2.getGroupId();
                boolean z4 = sparseBooleanArray.get(groupId2);
                boolean z5 = (i14 > 0 || z4) && i10 > 0 && (!this.mStrictWidthLimit || i18 > 0);
                if (z5) {
                    View itemView2 = getItemView(menuItemImpl2, this.mScrapActionButtonView, viewGroup);
                    if (this.mScrapActionButtonView == null) {
                        this.mScrapActionButtonView = itemView2;
                    }
                    if (this.mStrictWidthLimit) {
                        int measureChildForCells = ActionMenuView.measureChildForCells(itemView2, i, i18, makeMeasureSpec, 0);
                        int i20 = i18 - measureChildForCells;
                        z2 = measureChildForCells == 0 ? false : z5;
                        i8 = i20;
                    } else {
                        itemView2.measure(makeMeasureSpec, makeMeasureSpec);
                        boolean z6 = z5;
                        i8 = i18;
                        z2 = z6;
                    }
                    int measuredWidth = itemView2.getMeasuredWidth();
                    i10 -= measuredWidth;
                    if (i17 == 0) {
                        i17 = measuredWidth;
                    }
                    if (this.mStrictWidthLimit) {
                        z = z2 & (i10 >= 0);
                        i5 = i17;
                        i6 = i8;
                    } else {
                        z = z2 & (i10 + i17 > 0);
                        i5 = i17;
                        i6 = i8;
                    }
                } else {
                    z = z5;
                    i5 = i17;
                    i6 = i18;
                }
                if (z && groupId2 != 0) {
                    sparseBooleanArray.put(groupId2, true);
                    i7 = i14;
                } else if (z4) {
                    sparseBooleanArray.put(groupId2, false);
                    int i21 = i14;
                    for (int i22 = 0; i22 < i16; i22++) {
                        MenuItemImpl menuItemImpl3 = visibleItems.get(i22);
                        if (menuItemImpl3.getGroupId() == groupId2) {
                            if (menuItemImpl3.isActionButton()) {
                                i21++;
                            }
                            menuItemImpl3.setIsActionButton(false);
                        }
                    }
                    i7 = i21;
                } else {
                    i7 = i14;
                }
                if (z) {
                    i7--;
                }
                menuItemImpl2.setIsActionButton(z);
                i2 = i5;
                i3 = i10;
                int i23 = i6;
                i4 = i7;
                i18 = i23;
            } else {
                i2 = i17;
                i3 = i10;
                i4 = i14;
            }
            i16++;
            i10 = i3;
            i14 = i4;
            i17 = i2;
        }
        return true;
    }

    @Override // com.actionbarsherlock.internal.view.menu.BaseMenuPresenter, com.actionbarsherlock.internal.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        dismissPopupMenus();
        super.onCloseMenu(menuBuilder, z);
    }

    @Override // com.actionbarsherlock.internal.view.menu.MenuPresenter
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState();
        savedState.openSubMenuId = this.mOpenSubMenuId;
        return savedState;
    }

    @Override // com.actionbarsherlock.internal.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem findItem;
        SavedState savedState = (SavedState) parcelable;
        if (savedState.openSubMenuId > 0 && (findItem = this.mMenu.findItem(savedState.openSubMenuId)) != null) {
            onSubMenuSelected((SubMenuBuilder) findItem.getSubMenu());
        }
    }

    @Override // com.actionbarsherlock.view.ActionProvider.SubUiVisibilityListener
    public void onSubUiVisibilityChanged(boolean z) {
        if (z) {
            super.onSubMenuSelected(null);
        } else {
            this.mMenu.close(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.actionbarsherlock.internal.view.menu.ActionMenuPresenter.SavedState.1
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
        public int openSubMenuId;

        SavedState() {
        }

        SavedState(Parcel parcel) {
            this.openSubMenuId = parcel.readInt();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.openSubMenuId);
        }
    }

    /* loaded from: classes.dex */
    class OverflowMenuButton extends ImageButton implements View_HasStateListenerSupport, ActionMenuView.ActionMenuChildView {
        private final Set<View_OnAttachStateChangeListener> mListeners;

        public OverflowMenuButton(Context context) {
            super(context, null, R.attr.actionOverflowButtonStyle);
            this.mListeners = new HashSet();
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
        }

        @Override // android.view.View
        public boolean performClick() {
            if (!super.performClick()) {
                playSoundEffect(0);
                ActionMenuPresenter.this.showOverflowMenu();
            }
            return true;
        }

        @Override // com.actionbarsherlock.internal.view.menu.ActionMenuView.ActionMenuChildView
        public boolean needsDividerBefore() {
            return false;
        }

        @Override // com.actionbarsherlock.internal.view.menu.ActionMenuView.ActionMenuChildView
        public boolean needsDividerAfter() {
            return false;
        }

        @Override // android.widget.ImageView, android.view.View
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            Iterator<View_OnAttachStateChangeListener> it = this.mListeners.iterator();
            while (it.hasNext()) {
                it.next().onViewAttachedToWindow(this);
            }
        }

        @Override // android.widget.ImageView, android.view.View
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            Iterator<View_OnAttachStateChangeListener> it = this.mListeners.iterator();
            while (it.hasNext()) {
                it.next().onViewDetachedFromWindow(this);
            }
            if (ActionMenuPresenter.this.mOverflowPopup != null) {
                ActionMenuPresenter.this.mOverflowPopup.dismiss();
            }
        }

        @Override // com.actionbarsherlock.internal.view.View_HasStateListenerSupport
        public void addOnAttachStateChangeListener(View_OnAttachStateChangeListener view_OnAttachStateChangeListener) {
            this.mListeners.add(view_OnAttachStateChangeListener);
        }

        @Override // com.actionbarsherlock.internal.view.View_HasStateListenerSupport
        public void removeOnAttachStateChangeListener(View_OnAttachStateChangeListener view_OnAttachStateChangeListener) {
            this.mListeners.remove(view_OnAttachStateChangeListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class OverflowPopup extends MenuPopupHelper {
        public OverflowPopup(Context context, MenuBuilder menuBuilder, View view, boolean z) {
            super(context, menuBuilder, view, z);
            setCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
        }

        @Override // com.actionbarsherlock.internal.view.menu.MenuPopupHelper, android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            super.onDismiss();
            ActionMenuPresenter.this.mMenu.close();
            ActionMenuPresenter.this.mOverflowPopup = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ActionButtonSubmenu extends MenuPopupHelper {
        public ActionButtonSubmenu(Context context, SubMenuBuilder subMenuBuilder) {
            super(context, subMenuBuilder);
            boolean z;
            if (!((MenuItemImpl) subMenuBuilder.getItem()).isActionButton()) {
                setAnchorView(ActionMenuPresenter.this.mOverflowButton == null ? (View) ActionMenuPresenter.this.mMenuView : ActionMenuPresenter.this.mOverflowButton);
            }
            setCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
            int size = subMenuBuilder.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    z = false;
                    break;
                }
                MenuItem item = subMenuBuilder.getItem(i);
                if (!item.isVisible() || item.getIcon() == null) {
                    i++;
                } else {
                    z = true;
                    break;
                }
            }
            setForceShowIcon(z);
        }

        @Override // com.actionbarsherlock.internal.view.menu.MenuPopupHelper, android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            super.onDismiss();
            ActionMenuPresenter.this.mActionButtonPopup = null;
            ActionMenuPresenter.this.mOpenSubMenuId = 0;
        }
    }

    /* loaded from: classes.dex */
    class PopupPresenterCallback implements MenuPresenter.Callback {
        private PopupPresenterCallback() {
        }

        @Override // com.actionbarsherlock.internal.view.menu.MenuPresenter.Callback
        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            if (menuBuilder != null) {
                ActionMenuPresenter.this.mOpenSubMenuId = ((SubMenuBuilder) menuBuilder).getItem().getItemId();
            }
            return false;
        }

        @Override // com.actionbarsherlock.internal.view.menu.MenuPresenter.Callback
        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            if (menuBuilder instanceof SubMenuBuilder) {
                ((SubMenuBuilder) menuBuilder).getRootMenu().close(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class OpenOverflowRunnable implements Runnable {
        private OverflowPopup mPopup;

        public OpenOverflowRunnable(OverflowPopup overflowPopup) {
            this.mPopup = overflowPopup;
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionMenuPresenter.this.mMenu.changeMenuMode();
            View view = (View) ActionMenuPresenter.this.mMenuView;
            if (view != null && view.getWindowToken() != null && this.mPopup.tryShow()) {
                ActionMenuPresenter.this.mOverflowPopup = this.mPopup;
            }
            ActionMenuPresenter.this.mPostedOpenRunnable = null;
        }
    }
}
