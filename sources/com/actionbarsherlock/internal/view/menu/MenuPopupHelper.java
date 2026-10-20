package com.actionbarsherlock.internal.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import com.actionbarsherlock.R;
import com.actionbarsherlock.internal.view.View_HasStateListenerSupport;
import com.actionbarsherlock.internal.view.View_OnAttachStateChangeListener;
import com.actionbarsherlock.internal.view.menu.MenuPresenter;
import com.actionbarsherlock.internal.view.menu.MenuView;
import com.actionbarsherlock.internal.widget.IcsListPopupWindow;
import com.actionbarsherlock.view.MenuItem;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class MenuPopupHelper implements View.OnKeyListener, ViewTreeObserver.OnGlobalLayoutListener, AdapterView.OnItemClickListener, PopupWindow.OnDismissListener, View_OnAttachStateChangeListener, MenuPresenter {
    static final int ITEM_LAYOUT = R.layout.abs__popup_menu_item_layout;
    private MenuAdapter mAdapter;
    private View mAnchorView;
    private Context mContext;
    boolean mForceShowIcon;
    private LayoutInflater mInflater;
    private ViewGroup mMeasureParent;
    private MenuBuilder mMenu;
    private boolean mOverflowOnly;
    private IcsListPopupWindow mPopup;
    private int mPopupMaxWidth;
    private MenuPresenter.Callback mPresenterCallback;
    private ViewTreeObserver mTreeObserver;

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder) {
        this(context, menuBuilder, null, false);
    }

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder, View view) {
        this(context, menuBuilder, view, false);
    }

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder, View view, boolean z) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mMenu = menuBuilder;
        this.mOverflowOnly = z;
        Resources resources = context.getResources();
        this.mPopupMaxWidth = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abs__config_prefDialogWidth));
        this.mAnchorView = view;
        menuBuilder.addMenuPresenter(this);
    }

    public void setAnchorView(View view) {
        this.mAnchorView = view;
    }

    public void setForceShowIcon(boolean z) {
        this.mForceShowIcon = z;
    }

    public void show() {
        if (!tryShow()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean tryShow() {
        this.mPopup = new IcsListPopupWindow(this.mContext, null, R.attr.popupMenuStyle);
        this.mPopup.setOnDismissListener(this);
        this.mPopup.setOnItemClickListener(this);
        this.mAdapter = new MenuAdapter(this.mMenu);
        this.mPopup.setAdapter(this.mAdapter);
        this.mPopup.setModal(true);
        View view = this.mAnchorView;
        if (view == 0) {
            return false;
        }
        boolean z = this.mTreeObserver == null;
        this.mTreeObserver = view.getViewTreeObserver();
        if (z) {
            this.mTreeObserver.addOnGlobalLayoutListener(this);
        }
        ((View_HasStateListenerSupport) view).addOnAttachStateChangeListener(this);
        this.mPopup.setAnchorView(view);
        this.mPopup.setContentWidth(Math.min(measureContentWidth(this.mAdapter), this.mPopupMaxWidth));
        this.mPopup.setInputMethodMode(2);
        this.mPopup.show();
        this.mPopup.getListView().setOnKeyListener(this);
        return true;
    }

    public void dismiss() {
        if (isShowing()) {
            this.mPopup.dismiss();
        }
    }

    public void onDismiss() {
        this.mPopup = null;
        this.mMenu.close();
        if (this.mTreeObserver != null) {
            if (!this.mTreeObserver.isAlive()) {
                this.mTreeObserver = this.mAnchorView.getViewTreeObserver();
            }
            this.mTreeObserver.removeGlobalOnLayoutListener(this);
            this.mTreeObserver = null;
        }
        ((View_HasStateListenerSupport) this.mAnchorView).removeOnAttachStateChangeListener(this);
    }

    public boolean isShowing() {
        return this.mPopup != null && this.mPopup.isShowing();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        MenuAdapter menuAdapter = this.mAdapter;
        menuAdapter.mAdapterMenu.performItemAction(menuAdapter.getItem(i), 0);
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    private int measureContentWidth(ListAdapter listAdapter) {
        View view;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int count = listAdapter.getCount();
        int i = 0;
        int i2 = 0;
        View view2 = null;
        int i3 = 0;
        while (i < count) {
            int itemViewType = listAdapter.getItemViewType(i);
            if (itemViewType != i2) {
                view = null;
            } else {
                itemViewType = i2;
                view = view2;
            }
            if (this.mMeasureParent == null) {
                this.mMeasureParent = new FrameLayout(this.mContext);
            }
            view2 = listAdapter.getView(i, view, this.mMeasureParent);
            view2.measure(makeMeasureSpec, makeMeasureSpec2);
            i3 = Math.max(i3, view2.getMeasuredWidth());
            i++;
            i2 = itemViewType;
        }
        return i3;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        if (isShowing()) {
            View view = this.mAnchorView;
            if (view == null || !view.isShown()) {
                dismiss();
            } else if (isShowing()) {
                this.mPopup.show();
            }
        }
    }

    @Override // com.actionbarsherlock.internal.view.View_OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.actionbarsherlock.internal.view.View_OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        if (this.mTreeObserver != null) {
            if (!this.mTreeObserver.isAlive()) {
                this.mTreeObserver = view.getViewTreeObserver();
            }
            this.mTreeObserver.removeGlobalOnLayoutListener(this);
        }
        ((View_HasStateListenerSupport) view).removeOnAttachStateChangeListener(this);
    }

    @Override // com.actionbarsherlock.internal.view.menu.MenuPresenter
    public void initForMenu(Context context, MenuBuilder menuBuilder) {
    }

    @Override // com.actionbarsherlock.internal.view.menu.MenuPresenter
    public MenuView getMenuView(ViewGroup viewGroup) {
        throw new UnsupportedOperationException("MenuPopupHelpers manage their own views");
    }

    @Override // com.actionbarsherlock.internal.view.menu.MenuPresenter
    public void updateMenuView(boolean z) {
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.actionbarsherlock.internal.view.menu.MenuPresenter
    public void setCallback(MenuPresenter.Callback callback) {
        this.mPresenterCallback = callback;
    }

    @Override // com.actionbarsherlock.internal.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        boolean z;
        if (subMenuBuilder.hasVisibleItems()) {
            MenuPopupHelper menuPopupHelper = new MenuPopupHelper(this.mContext, subMenuBuilder, this.mAnchorView, false);
            menuPopupHelper.setCallback(this.mPresenterCallback);
            int size = subMenuBuilder.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    z = false;
                    break;
                }
                MenuItem item = subMenuBuilder.getItem(i);
                if (item.isVisible() && item.getIcon() != null) {
                    z = true;
                    break;
                }
                i++;
            }
            menuPopupHelper.setForceShowIcon(z);
            if (menuPopupHelper.tryShow()) {
                if (this.mPresenterCallback == null) {
                    return true;
                }
                this.mPresenterCallback.onOpenSubMenu(subMenuBuilder);
                return true;
            }
        }
        return false;
    }

    @Override // com.actionbarsherlock.internal.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        if (menuBuilder == this.mMenu) {
            dismiss();
            if (this.mPresenterCallback != null) {
                this.mPresenterCallback.onCloseMenu(menuBuilder, z);
            }
        }
    }

    @Override // com.actionbarsherlock.internal.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    @Override // com.actionbarsherlock.internal.view.menu.MenuPresenter
    public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // com.actionbarsherlock.internal.view.menu.MenuPresenter
    public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class MenuAdapter extends BaseAdapter {
        private MenuBuilder mAdapterMenu;
        private int mExpandedIndex = -1;

        public MenuAdapter(MenuBuilder menuBuilder) {
            this.mAdapterMenu = menuBuilder;
            registerDataSetObserver(new ExpandedIndexObserver());
            findExpandedIndex();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            ArrayList<MenuItemImpl> nonActionItems = MenuPopupHelper.this.mOverflowOnly ? this.mAdapterMenu.getNonActionItems() : this.mAdapterMenu.getVisibleItems();
            if (this.mExpandedIndex < 0) {
                return nonActionItems.size();
            }
            return nonActionItems.size() - 1;
        }

        @Override // android.widget.Adapter
        public MenuItemImpl getItem(int i) {
            ArrayList<MenuItemImpl> nonActionItems = MenuPopupHelper.this.mOverflowOnly ? this.mAdapterMenu.getNonActionItems() : this.mAdapterMenu.getVisibleItems();
            if (this.mExpandedIndex >= 0 && i >= this.mExpandedIndex) {
                i++;
            }
            return nonActionItems.get(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = view == null ? MenuPopupHelper.this.mInflater.inflate(MenuPopupHelper.ITEM_LAYOUT, viewGroup, false) : view;
            MenuView.ItemView itemView = (MenuView.ItemView) inflate;
            if (MenuPopupHelper.this.mForceShowIcon) {
                ((ListMenuItemView) inflate).setForceShowIcon(true);
            }
            itemView.initialize(getItem(i), 0);
            return inflate;
        }

        void findExpandedIndex() {
            MenuItemImpl expandedItem = MenuPopupHelper.this.mMenu.getExpandedItem();
            if (expandedItem != null) {
                ArrayList<MenuItemImpl> nonActionItems = MenuPopupHelper.this.mMenu.getNonActionItems();
                int size = nonActionItems.size();
                for (int i = 0; i < size; i++) {
                    if (nonActionItems.get(i) == expandedItem) {
                        this.mExpandedIndex = i;
                        return;
                    }
                }
            }
            this.mExpandedIndex = -1;
        }
    }

    /* loaded from: classes.dex */
    class ExpandedIndexObserver extends DataSetObserver {
        private ExpandedIndexObserver() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            MenuPopupHelper.this.mAdapter.findExpandedIndex();
        }
    }
}
