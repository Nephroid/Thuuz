package com.actionbarsherlock.internal.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;
import java.util.Locale;

/* loaded from: classes.dex */
public class CapitalizingButton extends Button {
    private static final boolean IS_GINGERBREAD;
    private static final int[] R_styleable_Button;
    private static final int R_styleable_Button_textAllCaps = 0;
    private static final boolean SANS_ICE_CREAM;
    private boolean mAllCaps;

    static {
        SANS_ICE_CREAM = Build.VERSION.SDK_INT < 14;
        IS_GINGERBREAD = Build.VERSION.SDK_INT >= 9;
        R_styleable_Button = new int[]{R.attr.textAllCaps};
    }

    public CapitalizingButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R_styleable_Button);
        this.mAllCaps = obtainStyledAttributes.getBoolean(0, true);
        obtainStyledAttributes.recycle();
    }

    public void setTextCompat(CharSequence charSequence) {
        if (SANS_ICE_CREAM && this.mAllCaps && charSequence != null) {
            if (IS_GINGERBREAD) {
                setText(charSequence.toString().toUpperCase(Locale.ROOT));
                return;
            } else {
                setText(charSequence.toString().toUpperCase());
                return;
            }
        }
        setText(charSequence);
    }
}
