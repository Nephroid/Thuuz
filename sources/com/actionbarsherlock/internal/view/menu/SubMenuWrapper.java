package com.actionbarsherlock.internal.view.menu;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

/* loaded from: classes.dex */
public class SubMenuWrapper extends MenuWrapper implements SubMenu {
    private MenuItem mItem;
    private final android.view.SubMenu mNativeSubMenu;

    public SubMenuWrapper(android.view.SubMenu subMenu) {
        super(subMenu);
        this.mItem = null;
        this.mNativeSubMenu = subMenu;
    }

    @Override // com.actionbarsherlock.view.SubMenu
    public SubMenu setHeaderTitle(int i) {
        this.mNativeSubMenu.setHeaderTitle(i);
        return this;
    }

    @Override // com.actionbarsherlock.view.SubMenu
    public SubMenu setHeaderTitle(CharSequence charSequence) {
        this.mNativeSubMenu.setHeaderTitle(charSequence);
        return this;
    }

    @Override // com.actionbarsherlock.view.SubMenu
    public SubMenu setHeaderIcon(int i) {
        this.mNativeSubMenu.setHeaderIcon(i);
        return this;
    }

    @Override // com.actionbarsherlock.view.SubMenu
    public SubMenu setHeaderIcon(Drawable drawable) {
        this.mNativeSubMenu.setHeaderIcon(drawable);
        return this;
    }

    @Override // com.actionbarsherlock.view.SubMenu
    public SubMenu setHeaderView(View view) {
        this.mNativeSubMenu.setHeaderView(view);
        return this;
    }

    @Override // com.actionbarsherlock.view.SubMenu
    public void clearHeader() {
        this.mNativeSubMenu.clearHeader();
    }

    @Override // com.actionbarsherlock.view.SubMenu
    public SubMenu setIcon(int i) {
        this.mNativeSubMenu.setIcon(i);
        return this;
    }

    @Override // com.actionbarsherlock.view.SubMenu
    public SubMenu setIcon(Drawable drawable) {
        this.mNativeSubMenu.setIcon(drawable);
        return this;
    }

    @Override // com.actionbarsherlock.view.SubMenu
    public MenuItem getItem() {
        if (this.mItem == null) {
            this.mItem = new MenuItemWrapper(this.mNativeSubMenu.getItem());
        }
        return this.mItem;
    }
}
