package com.google.android.gms.plus;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.FrameLayout;
import defpackage.aqq;
import defpackage.aqr;
import defpackage.aqs;
import defpackage.aqt;

/* loaded from: classes.dex */
public class PlusOneDummyView extends FrameLayout {
    public PlusOneDummyView(Context context, int i) {
        super(context);
        Button button = new Button(context);
        button.setEnabled(false);
        button.setBackgroundDrawable(a().a(i));
        Point a = a(i);
        addView(button, new FrameLayout.LayoutParams(a.x, a.y, 17));
    }

    private Point a(int i) {
        int i2 = 24;
        int i3 = 20;
        Point point = new Point();
        switch (i) {
            case 0:
                i3 = 14;
                break;
            case 1:
                i2 = 32;
                break;
            case 2:
                i2 = 50;
                break;
            default:
                i2 = 38;
                i3 = 24;
                break;
        }
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float applyDimension = TypedValue.applyDimension(1, i2, displayMetrics);
        float applyDimension2 = TypedValue.applyDimension(1, i3, displayMetrics);
        point.x = (int) (applyDimension + 0.5d);
        point.y = (int) (applyDimension2 + 0.5d);
        return point;
    }

    private aqt a() {
        aqt aqrVar = new aqr(getContext());
        if (!aqrVar.a()) {
            aqrVar = new aqs(getContext());
        }
        return !aqrVar.a() ? new aqq(getContext()) : aqrVar;
    }
}
