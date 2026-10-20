package com.actionbarsherlock.internal.view.menu;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import com.actionbarsherlock.internal.view.ActionProviderWrapper;
import com.actionbarsherlock.internal.widget.CollapsibleActionViewWrapper;
import com.actionbarsherlock.view.ActionProvider;
import com.actionbarsherlock.view.CollapsibleActionView;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

/* loaded from: classes.dex */
public class MenuItemWrapper implements MenuItem.OnMenuItemClickListener, com.actionbarsherlock.view.MenuItem {
    private final MenuItem mNativeItem;
    private SubMenu mSubMenu = null;
    private MenuItem.OnMenuItemClickListener mMenuItemClickListener = null;
    private MenuItem.OnActionExpandListener mActionExpandListener = null;
    private MenuItem.OnActionExpandListener mNativeActionExpandListener = null;

    public MenuItemWrapper(android.view.MenuItem menuItem) {
        if (menuItem == null) {
            throw new IllegalStateException("Wrapped menu item cannot be null.");
        }
        this.mNativeItem = menuItem;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public int getItemId() {
        return this.mNativeItem.getItemId();
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public int getGroupId() {
        return this.mNativeItem.getGroupId();
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public int getOrder() {
        return this.mNativeItem.getOrder();
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public com.actionbarsherlock.view.MenuItem setTitle(CharSequence charSequence) {
        this.mNativeItem.setTitle(charSequence);
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public com.actionbarsherlock.view.MenuItem setTitle(int i) {
        this.mNativeItem.setTitle(i);
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public CharSequence getTitle() {
        return this.mNativeItem.getTitle();
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public com.actionbarsherlock.view.MenuItem setTitleCondensed(CharSequence charSequence) {
        this.mNativeItem.setTitleCondensed(charSequence);
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public CharSequence getTitleCondensed() {
        return this.mNativeItem.getTitleCondensed();
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public com.actionbarsherlock.view.MenuItem setIcon(Drawable drawable) {
        this.mNativeItem.setIcon(drawable);
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public com.actionbarsherlock.view.MenuItem setIcon(int i) {
        this.mNativeItem.setIcon(i);
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public Drawable getIcon() {
        return this.mNativeItem.getIcon();
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public com.actionbarsherlock.view.MenuItem setIntent(Intent intent) {
        this.mNativeItem.setIntent(intent);
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public Intent getIntent() {
        return this.mNativeItem.getIntent();
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public com.actionbarsherlock.view.MenuItem setShortcut(char c, char c2) {
        this.mNativeItem.setShortcut(c, c2);
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public com.actionbarsherlock.view.MenuItem setNumericShortcut(char c) {
        this.mNativeItem.setNumericShortcut(c);
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public char getNumericShortcut() {
        return this.mNativeItem.getNumericShortcut();
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public com.actionbarsherlock.view.MenuItem setAlphabeticShortcut(char c) {
        this.mNativeItem.setAlphabeticShortcut(c);
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.mNativeItem.getAlphabeticShortcut();
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public com.actionbarsherlock.view.MenuItem setCheckable(boolean z) {
        this.mNativeItem.setCheckable(z);
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public boolean isCheckable() {
        return this.mNativeItem.isCheckable();
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public com.actionbarsherlock.view.MenuItem setChecked(boolean z) {
        this.mNativeItem.setChecked(z);
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public boolean isChecked() {
        return this.mNativeItem.isChecked();
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public com.actionbarsherlock.view.MenuItem setVisible(boolean z) {
        this.mNativeItem.setVisible(z);
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public boolean isVisible() {
        return this.mNativeItem.isVisible();
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public com.actionbarsherlock.view.MenuItem setEnabled(boolean z) {
        this.mNativeItem.setEnabled(z);
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public boolean isEnabled() {
        return this.mNativeItem.isEnabled();
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public boolean hasSubMenu() {
        return this.mNativeItem.hasSubMenu();
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public SubMenu getSubMenu() {
        if (hasSubMenu() && this.mSubMenu == null) {
            this.mSubMenu = new SubMenuWrapper(this.mNativeItem.getSubMenu());
        }
        return this.mSubMenu;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public com.actionbarsherlock.view.MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.mMenuItemClickListener = onMenuItemClickListener;
        this.mNativeItem.setOnMenuItemClickListener(this);
        return this;
    }

    @Override // android.view.MenuItem.OnMenuItemClickListener
    public boolean onMenuItemClick(android.view.MenuItem menuItem) {
        if (this.mMenuItemClickListener != null) {
            return this.mMenuItemClickListener.onMenuItemClick(this);
        }
        return false;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.mNativeItem.getMenuInfo();
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public void setShowAsAction(int i) {
        this.mNativeItem.setShowAsAction(i);
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public com.actionbarsherlock.view.MenuItem setShowAsActionFlags(int i) {
        this.mNativeItem.setShowAsActionFlags(i);
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public com.actionbarsherlock.view.MenuItem setActionView(View view) {
        if (view != null && (view instanceof CollapsibleActionView)) {
            view = new CollapsibleActionViewWrapper(view);
        }
        this.mNativeItem.setActionView(view);
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public com.actionbarsherlock.view.MenuItem setActionView(int i) {
        this.mNativeItem.setActionView(i);
        if (i != 0) {
            View actionView = this.mNativeItem.getActionView();
            if (actionView instanceof CollapsibleActionView) {
                this.mNativeItem.setActionView(new CollapsibleActionViewWrapper(actionView));
            }
        }
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public View getActionView() {
        View actionView = this.mNativeItem.getActionView();
        if (actionView instanceof CollapsibleActionViewWrapper) {
            return ((CollapsibleActionViewWrapper) actionView).unwrap();
        }
        return actionView;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public com.actionbarsherlock.view.MenuItem setActionProvider(ActionProvider actionProvider) {
        this.mNativeItem.setActionProvider(new ActionProviderWrapper(actionProvider));
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public ActionProvider getActionProvider() {
        android.view.ActionProvider actionProvider = this.mNativeItem.getActionProvider();
        if (actionProvider == null || !(actionProvider instanceof ActionProviderWrapper)) {
            return null;
        }
        return ((ActionProviderWrapper) actionProvider).unwrap();
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public boolean expandActionView() {
        return this.mNativeItem.expandActionView();
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public boolean collapseActionView() {
        return this.mNativeItem.collapseActionView();
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public boolean isActionViewExpanded() {
        return this.mNativeItem.isActionViewExpanded();
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public com.actionbarsherlock.view.MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.mActionExpandListener = onActionExpandListener;
        if (this.mNativeActionExpandListener == null) {
            this.mNativeActionExpandListener = new MenuItem.OnActionExpandListener() { // from class: com.actionbarsherlock.internal.view.menu.MenuItemWrapper.1
                @Override // android.view.MenuItem.OnActionExpandListener
                public boolean onMenuItemActionExpand(android.view.MenuItem menuItem) {
                    if (MenuItemWrapper.this.mActionExpandListener != null) {
                        return MenuItemWrapper.this.mActionExpandListener.onMenuItemActionExpand(MenuItemWrapper.this);
                    }
                    return false;
                }

                @Override // android.view.MenuItem.OnActionExpandListener
                public boolean onMenuItemActionCollapse(android.view.MenuItem menuItem) {
                    if (MenuItemWrapper.this.mActionExpandListener != null) {
                        return MenuItemWrapper.this.mActionExpandListener.onMenuItemActionCollapse(MenuItemWrapper.this);
                    }
                    return false;
                }
            };
            this.mNativeItem.setOnActionExpandListener(this.mNativeActionExpandListener);
        }
        return this;
    }
}
