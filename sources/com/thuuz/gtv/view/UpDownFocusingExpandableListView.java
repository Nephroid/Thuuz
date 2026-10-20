package com.thuuz.gtv.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListView;
import com.thuuz.gtv.App;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class UpDownFocusingExpandableListView extends ExpandableListView {
    public UpDownFocusingExpandableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public UpDownFocusingExpandableListView(Context context) {
        super(context);
    }

    @Override // android.view.View
    public int getNextFocusLeftId() {
        int nextFocusLeftId = super.getNextFocusLeftId();
        getLogger().info("next left: " + nextFocusLeftId);
        return nextFocusLeftId;
    }

    public Logger getLogger() {
        Logger logger = Logger.getLogger(ExpandableListView.class.getSimpleName());
        if (logger.getLevel() != App.aV()) {
            logger.setLevel(App.aV());
        }
        return logger;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View view, int i) {
        getLogger().info("focus search: " + i);
        if (isShown() && (17 == i || 66 == i)) {
            return null;
        }
        return super.focusSearch(view, i);
    }
}
