package com.actionbarsherlock.internal.widget;

import android.view.CollapsibleActionView;
import android.view.View;
import android.widget.FrameLayout;

/* loaded from: classes.dex */
public class CollapsibleActionViewWrapper extends FrameLayout implements CollapsibleActionView {
    private final com.actionbarsherlock.view.CollapsibleActionView child;

    /* JADX WARN: Multi-variable type inference failed */
    public CollapsibleActionViewWrapper(View view) {
        super(view.getContext());
        this.child = (com.actionbarsherlock.view.CollapsibleActionView) view;
        addView(view);
    }

    @Override // android.view.CollapsibleActionView
    public void onActionViewExpanded() {
        this.child.onActionViewExpanded();
    }

    @Override // android.view.CollapsibleActionView
    public void onActionViewCollapsed() {
        this.child.onActionViewCollapsed();
    }

    public View unwrap() {
        return getChildAt(0);
    }
}
