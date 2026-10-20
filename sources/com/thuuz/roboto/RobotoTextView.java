package com.thuuz.roboto;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;
import defpackage.cci;
import defpackage.cck;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class RobotoTextView extends TextView {
    private static final Map<Integer, Typeface> a = Collections.synchronizedMap(new HashMap(4));

    /* loaded from: classes.dex */
    public enum FACES {
        THIN(0, "sans-serif-condensed"),
        LIGHT(2, "sans-serif-light"),
        REGULAR(6, "sans-serif"),
        MEDIUM(6, "sans-serif", true);

        private boolean bold;
        private final int i;
        private final String name;

        FACES(int i, String str, boolean z) {
            this(i, str);
            this.bold = z;
        }

        FACES(int i, String str) {
            this.i = i;
            this.name = str;
        }

        public static FACES a(int i) {
            for (FACES faces : values()) {
                if (faces.i == i) {
                    return faces;
                }
            }
            return null;
        }
    }

    public RobotoTextView(Context context) {
        super(context);
    }

    public RobotoTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            a(context, attributeSet);
        }
    }

    public RobotoTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (!isInEditMode()) {
            a(context, attributeSet);
        }
    }

    private void a(Context context, AttributeSet attributeSet) {
        Typeface a2;
        TypedArray typedArray = null;
        try {
            try {
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, cck.RobotoTextView);
                if (obtainStyledAttributes == null) {
                    if (obtainStyledAttributes != null) {
                        obtainStyledAttributes.recycle();
                        return;
                    }
                    return;
                }
                int i = 4;
                if (obtainStyledAttributes.hasValue(cck.RobotoTextView_typeface)) {
                    i = obtainStyledAttributes.getInt(cck.RobotoTextView_typeface, 0);
                } else {
                    cci.a(RobotoTextView.class).severe("Can't find 'typeface' attribute");
                }
                if (i != 16 && (a2 = a(context, i)) != null) {
                    setGecTypeface(a2);
                }
                if (obtainStyledAttributes != null) {
                    obtainStyledAttributes.recycle();
                }
            } catch (Throwable th) {
                cci.a(RobotoTextView.class).log(Level.SEVERE, "problem: ", th);
                if (0 != 0) {
                    typedArray.recycle();
                }
            }
        } catch (Throwable th2) {
            if (0 != 0) {
                typedArray.recycle();
            }
            throw th2;
        }
    }

    private void setGecTypeface(Typeface typeface) {
        setTypeface(typeface);
    }

    private synchronized Typeface a(Context context, int i) {
        Typeface typeface;
        typeface = a.get(Integer.valueOf(i));
        if (typeface == null) {
            if (Build.VERSION.SDK_INT > 15) {
                FACES a2 = FACES.a(i);
                if (a2 == null) {
                    typeface = Typeface.defaultFromStyle(0);
                } else {
                    Integer num = 0;
                    if (a2.bold) {
                        num = 1;
                    }
                    typeface = Typeface.create(a2.name, num.intValue());
                }
            } else {
                typeface = b(context, i);
            }
            if (typeface != null) {
                a.put(Integer.valueOf(i), typeface);
            }
        }
        return typeface;
    }

    private Typeface b(Context context, int i) {
        try {
            switch (i) {
                case 0:
                    return Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Thin.ttf");
                case 1:
                case 3:
                case 5:
                default:
                    throw new IllegalArgumentException("Unknown `typeface` attribute value " + i);
                case 2:
                    return Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
                case 4:
                    return Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
                case 6:
                    return Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf");
            }
        } catch (Throwable th) {
            if (Build.VERSION.SDK_INT > 9) {
                cci.a(RobotoTextView.class).info("problem creating typeface " + i + "; reverting to normal: " + th);
            }
            return Typeface.defaultFromStyle(0);
        }
    }

    public void setTypeface(Context context, int i) {
        setTypeface(a(context, i));
    }
}
