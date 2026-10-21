package com.actionbarsherlock.internal.view;

import android.view.ActionProvider;
import android.view.SubMenu;
import android.view.View;
import com.actionbarsherlock.internal.view.menu.SubMenuWrapper;

/* loaded from: classes.dex */
public class ActionProviderWrapper extends ActionProvider {
    private final com.actionbarsherlock.view.ActionProvider mProvider;

    public ActionProviderWrapper(com.actionbarsherlock.view.ActionProvider actionProvider) {
        super(null);
        this.mProvider = actionProvider;
    }

    public com.actionbarsherlock.view.ActionProvider unwrap() {
        return this.mProvider;
    }

    @Override // android.view.ActionProvider
    public View onCreateActionView() {
        return this.mProvider.onCreateActionView();
    }

    @Override // android.view.ActionProvider
    public boolean hasSubMenu() {
        return this.mProvider.hasSubMenu();
    }

    @Override // android.view.ActionProvider
    public boolean onPerformDefaultAction() {
        return this.mProvider.onPerformDefaultAction();
    }

    @Override // android.view.ActionProvider
    public void onPrepareSubMenu(SubMenu subMenu) {
        this.mProvider.onPrepareSubMenu(new SubMenuWrapper(subMenu));
    }
}
