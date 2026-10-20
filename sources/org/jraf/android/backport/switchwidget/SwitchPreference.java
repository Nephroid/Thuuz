package org.jraf.android.backport.switchwidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import defpackage.cdl;
import defpackage.cdm;
import defpackage.cdn;
import defpackage.cdp;

/* loaded from: classes.dex */
public class SwitchPreference extends TwoStatePreference {
    private CharSequence b;
    private CharSequence c;
    private final cdp d;

    public SwitchPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new cdp(this);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, cdn.SwitchPreference, cdl.switchPreferenceStyle, 0);
        c(obtainStyledAttributes.getString(cdn.SwitchPreference_summaryOn));
        d(obtainStyledAttributes.getString(cdn.SwitchPreference_summaryOff));
        a(obtainStyledAttributes.getString(cdn.SwitchPreference_switchTextOn));
        b(obtainStyledAttributes.getString(cdn.SwitchPreference_switchTextOff));
        b(obtainStyledAttributes.getBoolean(cdn.SwitchPreference_disableDependentsState, false));
        obtainStyledAttributes.recycle();
    }

    public SwitchPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, cdl.switchPreferenceStyle);
    }

    @Override // android.preference.Preference
    protected void onBindView(View view) {
        super.onBindView(view);
        Switch r0 = (Switch) view.findViewById(cdm.switchWidget);
        if (r0 != null) {
            r0.setChecked(this.a);
            r0.setTextOn(this.b);
            r0.setTextOff(this.c);
            r0.setOnCheckedChangeListener(this.d);
        }
        a(view);
    }

    public void a(CharSequence charSequence) {
        this.b = charSequence;
        notifyChanged();
    }

    public void b(CharSequence charSequence) {
        this.c = charSequence;
        notifyChanged();
    }
}
