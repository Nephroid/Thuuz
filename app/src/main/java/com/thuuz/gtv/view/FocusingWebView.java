package com.thuuz.gtv.view;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import com.thuuz.gtv.App;
import defpackage.cbk;

/* loaded from: classes.dex */
public class FocusingWebView extends WebView {
    public FocusingWebView(Context context) {
        super(context);
        a(context);
    }

    public FocusingWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public FocusingWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        boolean z;
        if (context.getApplicationContext() != null) {
            z = !((App) context.getApplicationContext()).e();
        } else {
            z = true;
        }
        setFocusable(true);
        if (z) {
            setFocusableInTouchMode(true);
            setOnTouchListener(new cbk(this));
        }
        if (!isInEditMode()) {
            setBackgroundResource(R.color.transparent);
        }
    }
}
