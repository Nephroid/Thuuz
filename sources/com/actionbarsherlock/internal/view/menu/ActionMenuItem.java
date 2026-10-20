package com.actionbarsherlock.internal.view.menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.ContextMenu;
import android.view.View;
import com.actionbarsherlock.view.ActionProvider;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

/* loaded from: classes.dex */
public class ActionMenuItem implements MenuItem {
    private static final int CHECKABLE = 1;
    private static final int CHECKED = 2;
    private static final int ENABLED = 16;
    private static final int EXCLUSIVE = 4;
    private static final int HIDDEN = 8;
    private MenuItem.OnMenuItemClickListener mClickListener;
    private Context mContext;
    private int mFlags = 16;
    private final int mGroup;
    private Drawable mIconDrawable;
    private final int mId;
    private Intent mIntent;
    private final int mOrdering;
    private char mShortcutAlphabeticChar;
    private char mShortcutNumericChar;
    private CharSequence mTitle;
    private CharSequence mTitleCondensed;

    public ActionMenuItem(Context context, int i, int i2, int i3, int i4, CharSequence charSequence) {
        this.mContext = context;
        this.mId = i2;
        this.mGroup = i;
        this.mOrdering = i4;
        this.mTitle = charSequence;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.mShortcutAlphabeticChar;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public int getGroupId() {
        return this.mGroup;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public Drawable getIcon() {
        return this.mIconDrawable;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public Intent getIntent() {
        return this.mIntent;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public int getItemId() {
        return this.mId;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public char getNumericShortcut() {
        return this.mShortcutNumericChar;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public int getOrder() {
        return this.mOrdering;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public SubMenu getSubMenu() {
        return null;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public CharSequence getTitle() {
        return this.mTitle;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public CharSequence getTitleCondensed() {
        return this.mTitleCondensed;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public boolean hasSubMenu() {
        return false;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public boolean isCheckable() {
        return (this.mFlags & 1) != 0;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public boolean isChecked() {
        return (this.mFlags & 2) != 0;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public boolean isEnabled() {
        return (this.mFlags & 16) != 0;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public boolean isVisible() {
        return (this.mFlags & 8) == 0;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c) {
        this.mShortcutAlphabeticChar = c;
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public MenuItem setCheckable(boolean z) {
        this.mFlags = (z ? 1 : 0) | (this.mFlags & (-2));
        return this;
    }

    public ActionMenuItem setExclusiveCheckable(boolean z) {
        this.mFlags = (z ? 4 : 0) | (this.mFlags & (-5));
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public MenuItem setChecked(boolean z) {
        this.mFlags = (z ? 2 : 0) | (this.mFlags & (-3));
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public MenuItem setEnabled(boolean z) {
        this.mFlags = (z ? 16 : 0) | (this.mFlags & (-17));
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.mIconDrawable = drawable;
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public MenuItem setIcon(int i) {
        this.mIconDrawable = this.mContext.getResources().getDrawable(i);
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.mIntent = intent;
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public MenuItem setNumericShortcut(char c) {
        this.mShortcutNumericChar = c;
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.mClickListener = onMenuItemClickListener;
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public MenuItem setShortcut(char c, char c2) {
        this.mShortcutNumericChar = c;
        this.mShortcutAlphabeticChar = c2;
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public MenuItem setTitle(int i) {
        this.mTitle = this.mContext.getResources().getString(i);
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.mTitleCondensed = charSequence;
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public MenuItem setVisible(boolean z) {
        this.mFlags = (z ? 0 : 8) | (this.mFlags & 8);
        return this;
    }

    public boolean invoke() {
        if (this.mClickListener != null && this.mClickListener.onMenuItemClick(this)) {
            return true;
        }
        if (this.mIntent != null) {
            this.mContext.startActivity(this.mIntent);
            return true;
        }
        return false;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public void setShowAsAction(int i) {
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public MenuItem setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public View getActionView() {
        return null;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public MenuItem setActionView(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public ActionProvider getActionProvider() {
        return null;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public MenuItem setShowAsActionFlags(int i) {
        setShowAsAction(i);
        return this;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public boolean expandActionView() {
        return false;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public boolean collapseActionView() {
        return false;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public boolean isActionViewExpanded() {
        return false;
    }

    @Override // com.actionbarsherlock.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        return this;
    }
}
