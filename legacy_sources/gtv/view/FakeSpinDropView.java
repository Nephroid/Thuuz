package com.thuuz.gtv.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Spinner;

/* loaded from: classes.dex */
public class FakeSpinDropView extends Spinner {
    private View.OnClickListener a;

    public FakeSpinDropView(Context context) {
        super(context);
    }

    public FakeSpinDropView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FakeSpinDropView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.AdapterView, android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.a = onClickListener;
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean performClick() {
        this.a.onClick(this);
        return true;
    }
}
