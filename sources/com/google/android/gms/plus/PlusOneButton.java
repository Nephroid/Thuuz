package com.google.android.gms.plus;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import defpackage.aqn;
import defpackage.aqo;
import defpackage.aqy;
import defpackage.kd;

/* loaded from: classes.dex */
public final class PlusOneButton extends FrameLayout {
    private View a;
    private int b;
    private int c;
    private String d;
    private int e;
    private aqo f;

    public PlusOneButton(Context context) {
        this(context, null);
    }

    public PlusOneButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = a(context, attributeSet);
        this.c = b(context, attributeSet);
        this.e = -1;
        a(getContext());
        if (isInEditMode()) {
        }
    }

    protected static int a(Context context, AttributeSet attributeSet) {
        String a = kd.a("http://schemas.android.com/apk/lib/com.google.android.gms.plus", "size", context, attributeSet, true, false, "PlusOneButton");
        if ("SMALL".equalsIgnoreCase(a)) {
            return 0;
        }
        if ("MEDIUM".equalsIgnoreCase(a)) {
            return 1;
        }
        return "TALL".equalsIgnoreCase(a) ? 2 : 3;
    }

    private void a(Context context) {
        if (this.a != null) {
            removeView(this.a);
        }
        this.a = aqy.a(context, this.b, this.c, this.d, this.e);
        setOnPlusOneClickListener(this.f);
        addView(this.a);
    }

    protected static int b(Context context, AttributeSet attributeSet) {
        String a = kd.a("http://schemas.android.com/apk/lib/com.google.android.gms.plus", "annotation", context, attributeSet, true, false, "PlusOneButton");
        if ("INLINE".equalsIgnoreCase(a)) {
            return 2;
        }
        return !"NONE".equalsIgnoreCase(a) ? 1 : 0;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.a.layout(0, 0, i3 - i, i4 - i2);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        View view = this.a;
        measureChild(view, i, i2);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    public void setAnnotation(int i) {
        this.c = i;
        a(getContext());
    }

    public void setOnPlusOneClickListener(aqo aqoVar) {
        this.f = aqoVar;
        this.a.setOnClickListener(new aqn(this, aqoVar));
    }

    public void setSize(int i) {
        this.b = i;
        a(getContext());
    }
}
