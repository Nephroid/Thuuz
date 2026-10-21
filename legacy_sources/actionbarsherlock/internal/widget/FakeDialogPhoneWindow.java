package com.actionbarsherlock.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;
import com.actionbarsherlock.R;

/* loaded from: classes.dex */
public class FakeDialogPhoneWindow extends LinearLayout {
    final TypedValue mMinWidthMajor;
    final TypedValue mMinWidthMinor;

    public FakeDialogPhoneWindow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMinWidthMajor = new TypedValue();
        this.mMinWidthMinor = new TypedValue();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SherlockTheme);
        obtainStyledAttributes.getValue(R.styleable.SherlockTheme_windowMinWidthMajor, this.mMinWidthMajor);
        obtainStyledAttributes.getValue(R.styleable.SherlockTheme_windowMinWidthMinor, this.mMinWidthMinor);
        obtainStyledAttributes.recycle();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    @Override // android.widget.LinearLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onMeasure(int r10, int r11) {
        /*
            r9 = this;
            r8 = 1073741824(0x40000000, float:2.0)
            r1 = 1
            r2 = 0
            android.content.Context r0 = r9.getContext()
            android.content.res.Resources r0 = r0.getResources()
            android.util.DisplayMetrics r4 = r0.getDisplayMetrics()
            int r0 = r4.widthPixels
            int r3 = r4.heightPixels
            if (r0 >= r3) goto L41
            r0 = r1
        L17:
            super.onMeasure(r10, r11)
            int r5 = r9.getMeasuredWidth()
            int r3 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r8)
            if (r0 == 0) goto L43
            android.util.TypedValue r0 = r9.mMinWidthMinor
        L26:
            int r6 = r0.type
            if (r6 == 0) goto L59
            int r6 = r0.type
            r7 = 5
            if (r6 != r7) goto L46
            float r0 = r0.getDimension(r4)
            int r0 = (int) r0
        L34:
            if (r5 >= r0) goto L59
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r8)
            r2 = r1
        L3b:
            if (r2 == 0) goto L40
            super.onMeasure(r0, r11)
        L40:
            return
        L41:
            r0 = r2
            goto L17
        L43:
            android.util.TypedValue r0 = r9.mMinWidthMajor
            goto L26
        L46:
            int r6 = r0.type
            r7 = 6
            if (r6 != r7) goto L57
            int r6 = r4.widthPixels
            float r6 = (float) r6
            int r4 = r4.widthPixels
            float r4 = (float) r4
            float r0 = r0.getFraction(r6, r4)
            int r0 = (int) r0
            goto L34
        L57:
            r0 = r2
            goto L34
        L59:
            r0 = r3
            goto L3b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.actionbarsherlock.internal.widget.FakeDialogPhoneWindow.onMeasure(int, int):void");
    }
}
