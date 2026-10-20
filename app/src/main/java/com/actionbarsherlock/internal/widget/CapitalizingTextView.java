package com.actionbarsherlock.internal.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;
import java.util.Locale;

/* loaded from: classes.dex */
public class CapitalizingTextView extends TextView {
    private static final boolean IS_GINGERBREAD;
    private static final int[] R_styleable_TextView;
    private static final int R_styleable_TextView_textAllCaps = 0;
    private static final boolean SANS_ICE_CREAM;
    private boolean mAllCaps;

    static {
        SANS_ICE_CREAM = Build.VERSION.SDK_INT < 14;
        IS_GINGERBREAD = Build.VERSION.SDK_INT >= 9;
        R_styleable_TextView = new int[]{R.attr.textAllCaps};
    }

    public CapitalizingTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CapitalizingTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R_styleable_TextView, i, 0);
        this.mAllCaps = obtainStyledAttributes.getBoolean(0, true);
        obtainStyledAttributes.recycle();
    }

    public void setTextCompat(CharSequence charSequence) {
        if (SANS_ICE_CREAM && this.mAllCaps && charSequence != null) {
            if (IS_GINGERBREAD) {
                try {
                    setText(charSequence.toString().toUpperCase(Locale.ROOT));
                    return;
                } catch (NoSuchFieldError e) {
                    setText(charSequence.toString().toUpperCase());
                    return;
                }
            }
            setText(charSequence.toString().toUpperCase());
            return;
        }
        setText(charSequence);
    }
}
