package com.thuuz.gtv.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.thuuz.gtv.App;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class ListViewDpad extends ListView {
    private Map<String, int[]> a;

    public ListViewDpad(Context context) {
        super(context);
        this.a = new Hashtable();
    }

    public ListViewDpad(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new Hashtable();
    }

    public ListViewDpad(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new Hashtable();
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 20 || keyEvent.getKeyCode() == 19 || i == 21 || keyEvent.getKeyCode() == 22) {
        }
        if (keyEvent.getKeyCode() == 19) {
            a();
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 20 || keyEvent.getKeyCode() == 19 || i == 21 || keyEvent.getKeyCode() == 22) {
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    protected void onMeasure(int i, int i2) {
        try {
            int[] iArr = this.a.get(a(i, i2));
            if (iArr == null || getChildCount() == 1) {
                b(i, i2);
            } else {
                int i3 = iArr[0];
                int i4 = iArr[1];
                int i5 = iArr[2];
                if (i4 <= 0 || i5 != getCount()) {
                    b(i, i2);
                } else {
                    setMeasuredDimension(i3, i4);
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem with onMeasure: ", th);
        }
    }

    private String a(int i, int i2) {
        return i + " " + i2;
    }

    private void b(int i, int i2) {
        try {
            super.onMeasure(i, i2);
            this.a.put(a(i, i2), new int[]{getMeasuredWidth(), getMeasuredHeight(), getCount()});
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem with doOnMeasure: ", th);
        }
    }

    public void a() {
        this.a.clear();
    }

    public Logger getLogger() {
        Logger logger = Logger.getLogger(getClass().getSimpleName() + ":thuuz");
        if (logger.getLevel() != App.aV()) {
            logger.setLevel(App.aV());
        }
        return logger;
    }

    @Override // android.view.ViewGroup
    protected boolean addViewInLayout(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        a();
        return super.addViewInLayout(view, i, layoutParams, z);
    }

    @Override // android.view.ViewGroup
    protected boolean addViewInLayout(View view, int i, ViewGroup.LayoutParams layoutParams) {
        a();
        return super.addViewInLayout(view, i, layoutParams);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, int i2) {
        a();
        super.addView(view, i, i2);
    }

    @Override // android.widget.AdapterView, android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        a();
        super.addView(view, layoutParams);
    }

    @Override // android.widget.AdapterView, android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        a();
        super.addView(view, i, layoutParams);
    }

    @Override // android.widget.AdapterView, android.view.ViewGroup
    public void addView(View view) {
        a();
        super.addView(view);
    }

    @Override // android.widget.AdapterView, android.view.ViewGroup
    public void addView(View view, int i) {
        a();
        super.addView(view, i);
    }

    public ListAdapter getGameListAdapter() {
        if (getHeaderViewsCount() > 0 || (getAdapter() instanceof HeaderViewListAdapter)) {
            return ((HeaderViewListAdapter) getAdapter()).getWrappedAdapter();
        }
        return getAdapter();
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        try {
            super.dispatchDraw(canvas);
        } catch (IndexOutOfBoundsException e) {
            getLogger().warning("ignoring exception, assumed samsung list bug: " + e);
        } catch (NullPointerException e2) {
            getLogger().warning("ignoring exception, assumed hacked android list bug: " + e2);
        }
    }

    @Override // android.view.View
    public String toString() {
        return "listViewDpad: " + getTag();
    }
}
