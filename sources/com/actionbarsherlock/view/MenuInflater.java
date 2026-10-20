package com.actionbarsherlock.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import android.view.InflateException;
import android.view.View;
import com.actionbarsherlock.R;
import com.actionbarsherlock.internal.view.menu.MenuItemImpl;
import com.actionbarsherlock.view.MenuItem;
import java.io.IOException;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class MenuInflater {
    private static final String LOG_TAG = "MenuInflater";
    private static final int NO_ID = 0;
    private static final String XML_GROUP = "group";
    private static final String XML_ITEM = "item";
    private static final String XML_MENU = "menu";
    private final Object[] mActionProviderConstructorArguments;
    private final Object[] mActionViewConstructorArguments;
    private Context mContext;
    private Object mRealOwner;
    private static final Class<?>[] ACTION_VIEW_CONSTRUCTOR_SIGNATURE = {Context.class};
    private static final Class<?>[] ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE = ACTION_VIEW_CONSTRUCTOR_SIGNATURE;

    public MenuInflater(Context context) {
        this.mContext = context;
        this.mRealOwner = context;
        this.mActionViewConstructorArguments = new Object[]{context};
        this.mActionProviderConstructorArguments = this.mActionViewConstructorArguments;
    }

    public MenuInflater(Context context, Object obj) {
        this.mContext = context;
        this.mRealOwner = obj;
        this.mActionViewConstructorArguments = new Object[]{context};
        this.mActionProviderConstructorArguments = this.mActionViewConstructorArguments;
    }

    public void inflate(int i, Menu menu) {
        XmlResourceParser xmlResourceParser = null;
        try {
            try {
                try {
                    xmlResourceParser = this.mContext.getResources().getLayout(i);
                    parseMenu(xmlResourceParser, Xml.asAttributeSet(xmlResourceParser), menu);
                } catch (IOException e) {
                    throw new InflateException("Error inflating menu XML", e);
                }
            } catch (XmlPullParserException e2) {
                throw new InflateException("Error inflating menu XML", e2);
            }
        } finally {
            if (xmlResourceParser != null) {
                xmlResourceParser.close();
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x0025. Please report as an issue. */
    private void parseMenu(XmlPullParser xmlPullParser, AttributeSet attributeSet, Menu menu) {
        boolean z;
        MenuState menuState = new MenuState(menu);
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if (name.equals(XML_MENU)) {
                    eventType = xmlPullParser.next();
                } else {
                    throw new RuntimeException("Expecting menu, got " + name);
                }
            } else {
                eventType = xmlPullParser.next();
                if (eventType == 1) {
                    break;
                }
            }
        }
        Object obj = null;
        boolean z2 = false;
        int i = eventType;
        boolean z3 = false;
        while (!z3) {
            switch (i) {
                case 1:
                    throw new RuntimeException("Unexpected end of document");
                case 2:
                    if (z2) {
                        z = z2;
                    } else {
                        String name2 = xmlPullParser.getName();
                        if (name2.equals(XML_GROUP)) {
                            menuState.readGroup(attributeSet);
                            z = z2;
                        } else if (name2.equals(XML_ITEM)) {
                            menuState.readItem(attributeSet);
                            z = z2;
                        } else if (name2.equals(XML_MENU)) {
                            parseMenu(xmlPullParser, attributeSet, menuState.addSubMenuItem());
                            z = z2;
                        } else {
                            obj = name2;
                            z = true;
                        }
                    }
                    boolean z4 = z;
                    i = xmlPullParser.next();
                    z2 = z4;
                case 3:
                    String name3 = xmlPullParser.getName();
                    if (z2 && name3.equals(obj)) {
                        obj = null;
                        z = false;
                    } else if (name3.equals(XML_GROUP)) {
                        menuState.resetGroup();
                        z = z2;
                    } else if (name3.equals(XML_ITEM)) {
                        if (!menuState.hasAddedItem()) {
                            if (menuState.itemActionProvider != null && menuState.itemActionProvider.hasSubMenu()) {
                                menuState.addSubMenuItem();
                                z = z2;
                            } else {
                                menuState.addItem();
                                z = z2;
                            }
                        }
                    } else if (name3.equals(XML_MENU)) {
                        z3 = true;
                        z = z2;
                    }
                    boolean z42 = z;
                    i = xmlPullParser.next();
                    z2 = z42;
                    break;
                default:
                    z = z2;
                    boolean z422 = z;
                    i = xmlPullParser.next();
                    z2 = z422;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class InflatedOnMenuItemClickListener implements MenuItem.OnMenuItemClickListener {
        private static final Class<?>[] PARAM_TYPES = {MenuItem.class};
        private Method mMethod;
        private Object mRealOwner;

        public InflatedOnMenuItemClickListener(Object obj, String str) {
            this.mRealOwner = obj;
            Class<?> cls = obj.getClass();
            try {
                this.mMethod = cls.getMethod(str, PARAM_TYPES);
            } catch (Exception e) {
                InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + cls.getName());
                inflateException.initCause(e);
                throw inflateException;
            }
        }

        @Override // com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.mMethod.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.mMethod.invoke(this.mRealOwner, menuItem)).booleanValue();
                }
                this.mMethod.invoke(this.mRealOwner, menuItem);
                return true;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class MenuState {
        private static final int defaultGroupId = 0;
        private static final int defaultItemCategory = 0;
        private static final int defaultItemCheckable = 0;
        private static final boolean defaultItemChecked = false;
        private static final boolean defaultItemEnabled = true;
        private static final int defaultItemId = 0;
        private static final int defaultItemOrder = 0;
        private static final boolean defaultItemVisible = true;
        private int groupCategory;
        private int groupCheckable;
        private boolean groupEnabled;
        private int groupId;
        private int groupOrder;
        private boolean groupVisible;
        private ActionProvider itemActionProvider;
        private String itemActionProviderClassName;
        private String itemActionViewClassName;
        private int itemActionViewLayout;
        private boolean itemAdded;
        private char itemAlphabeticShortcut;
        private int itemCategoryOrder;
        private int itemCheckable;
        private boolean itemChecked;
        private boolean itemEnabled;
        private int itemIconResId;
        private int itemId;
        private String itemListenerMethodName;
        private char itemNumericShortcut;
        private int itemShowAsAction;
        private CharSequence itemTitle;
        private CharSequence itemTitleCondensed;
        private boolean itemVisible;
        private Menu menu;

        public MenuState(Menu menu) {
            this.menu = menu;
            resetGroup();
        }

        public void resetGroup() {
            this.groupId = 0;
            this.groupCategory = 0;
            this.groupOrder = 0;
            this.groupCheckable = 0;
            this.groupVisible = true;
            this.groupEnabled = true;
        }

        public void readGroup(AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = MenuInflater.this.mContext.obtainStyledAttributes(attributeSet, R.styleable.SherlockMenuGroup);
            this.groupId = obtainStyledAttributes.getResourceId(R.styleable.SherlockMenuGroup_android_id, 0);
            this.groupCategory = obtainStyledAttributes.getInt(R.styleable.SherlockMenuGroup_android_menuCategory, 0);
            this.groupOrder = obtainStyledAttributes.getInt(R.styleable.SherlockMenuGroup_android_orderInCategory, 0);
            this.groupCheckable = obtainStyledAttributes.getInt(R.styleable.SherlockMenuGroup_android_checkableBehavior, 0);
            this.groupVisible = obtainStyledAttributes.getBoolean(R.styleable.SherlockMenuGroup_android_visible, true);
            this.groupEnabled = obtainStyledAttributes.getBoolean(R.styleable.SherlockMenuGroup_android_enabled, true);
            obtainStyledAttributes.recycle();
        }

        public void readItem(AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = MenuInflater.this.mContext.obtainStyledAttributes(attributeSet, R.styleable.SherlockMenuItem);
            this.itemId = obtainStyledAttributes.getResourceId(R.styleable.SherlockMenuItem_android_id, 0);
            this.itemCategoryOrder = (obtainStyledAttributes.getInt(R.styleable.SherlockMenuItem_android_menuCategory, this.groupCategory) & (-65536)) | (obtainStyledAttributes.getInt(R.styleable.SherlockMenuItem_android_orderInCategory, this.groupOrder) & 65535);
            this.itemTitle = obtainStyledAttributes.getText(R.styleable.SherlockMenuItem_android_title);
            this.itemTitleCondensed = obtainStyledAttributes.getText(R.styleable.SherlockMenuItem_android_titleCondensed);
            this.itemIconResId = obtainStyledAttributes.getResourceId(R.styleable.SherlockMenuItem_android_icon, 0);
            this.itemAlphabeticShortcut = getShortcut(obtainStyledAttributes.getString(R.styleable.SherlockMenuItem_android_alphabeticShortcut));
            this.itemNumericShortcut = getShortcut(obtainStyledAttributes.getString(R.styleable.SherlockMenuItem_android_numericShortcut));
            if (obtainStyledAttributes.hasValue(R.styleable.SherlockMenuItem_android_checkable)) {
                this.itemCheckable = obtainStyledAttributes.getBoolean(R.styleable.SherlockMenuItem_android_checkable, false) ? 1 : 0;
            } else {
                this.itemCheckable = this.groupCheckable;
            }
            this.itemChecked = obtainStyledAttributes.getBoolean(R.styleable.SherlockMenuItem_android_checked, false);
            this.itemVisible = obtainStyledAttributes.getBoolean(R.styleable.SherlockMenuItem_android_visible, this.groupVisible);
            this.itemEnabled = obtainStyledAttributes.getBoolean(R.styleable.SherlockMenuItem_android_enabled, this.groupEnabled);
            TypedValue typedValue = new TypedValue();
            obtainStyledAttributes.getValue(R.styleable.SherlockMenuItem_android_showAsAction, typedValue);
            this.itemShowAsAction = typedValue.type == 17 ? typedValue.data : -1;
            this.itemListenerMethodName = obtainStyledAttributes.getString(R.styleable.SherlockMenuItem_android_onClick);
            this.itemActionViewLayout = obtainStyledAttributes.getResourceId(R.styleable.SherlockMenuItem_android_actionLayout, 0);
            TypedValue typedValue2 = new TypedValue();
            obtainStyledAttributes.getValue(R.styleable.SherlockMenuItem_android_actionViewClass, typedValue2);
            this.itemActionViewClassName = typedValue2.type == 3 ? typedValue2.string.toString() : null;
            TypedValue typedValue3 = new TypedValue();
            obtainStyledAttributes.getValue(R.styleable.SherlockMenuItem_android_actionProviderClass, typedValue3);
            this.itemActionProviderClassName = typedValue3.type == 3 ? typedValue3.string.toString() : null;
            boolean z = this.itemActionProviderClassName != null;
            if (z && this.itemActionViewLayout == 0 && this.itemActionViewClassName == null) {
                this.itemActionProvider = (ActionProvider) newInstance(this.itemActionProviderClassName, MenuInflater.ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE, MenuInflater.this.mActionProviderConstructorArguments);
            } else {
                if (z) {
                    Log.w(MenuInflater.LOG_TAG, "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.itemActionProvider = null;
            }
            obtainStyledAttributes.recycle();
            this.itemAdded = false;
        }

        private char getShortcut(String str) {
            if (str == null) {
                return (char) 0;
            }
            return str.charAt(0);
        }

        private void setItem(MenuItem menuItem) {
            boolean z = true;
            menuItem.setChecked(this.itemChecked).setVisible(this.itemVisible).setEnabled(this.itemEnabled).setCheckable(this.itemCheckable >= 1).setTitleCondensed(this.itemTitleCondensed).setIcon(this.itemIconResId).setAlphabeticShortcut(this.itemAlphabeticShortcut).setNumericShortcut(this.itemNumericShortcut);
            if (this.itemShowAsAction >= 0) {
                menuItem.setShowAsAction(this.itemShowAsAction);
            }
            if (this.itemListenerMethodName != null) {
                if (MenuInflater.this.mContext.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                menuItem.setOnMenuItemClickListener(new InflatedOnMenuItemClickListener(MenuInflater.this.mRealOwner, this.itemListenerMethodName));
            }
            if (this.itemCheckable >= 2) {
                if (menuItem instanceof MenuItemImpl) {
                    ((MenuItemImpl) menuItem).setExclusiveCheckable(true);
                } else {
                    this.menu.setGroupCheckable(this.groupId, true, true);
                }
            }
            if (this.itemActionViewClassName != null) {
                menuItem.setActionView((View) newInstance(this.itemActionViewClassName, MenuInflater.ACTION_VIEW_CONSTRUCTOR_SIGNATURE, MenuInflater.this.mActionViewConstructorArguments));
            } else {
                z = false;
            }
            if (this.itemActionViewLayout > 0) {
                if (!z) {
                    menuItem.setActionView(this.itemActionViewLayout);
                } else {
                    Log.w(MenuInflater.LOG_TAG, "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
                }
            }
            if (this.itemActionProvider != null) {
                menuItem.setActionProvider(this.itemActionProvider);
            }
        }

        public void addItem() {
            this.itemAdded = true;
            setItem(this.menu.add(this.groupId, this.itemId, this.itemCategoryOrder, this.itemTitle));
        }

        public SubMenu addSubMenuItem() {
            this.itemAdded = true;
            SubMenu addSubMenu = this.menu.addSubMenu(this.groupId, this.itemId, this.itemCategoryOrder, this.itemTitle);
            setItem(addSubMenu.getItem());
            return addSubMenu;
        }

        public boolean hasAddedItem() {
            return this.itemAdded;
        }

        private <T> T newInstance(String str, Class<?>[] clsArr, Object[] objArr) {
            try {
                return (T) MenuInflater.this.mContext.getClassLoader().loadClass(str).getConstructor(clsArr).newInstance(objArr);
            } catch (Exception e) {
                Log.w(MenuInflater.LOG_TAG, "Cannot instantiate class: " + str, e);
                return null;
            }
        }
    }
}
