package com.thuuz.gtv.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import defpackage.ba;
import defpackage.bg;
import defpackage.cca;
import defpackage.ccc;
import defpackage.ccd;

/* loaded from: classes.dex */
public class NetworkListenableImageView extends ImageView {
    private String a;
    private int b;
    private int c;
    private ba d;
    private bg e;
    private ccc f;
    private ccd g;

    public NetworkListenableImageView(Context context) {
        this(context, null);
    }

    public NetworkListenableImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NetworkListenableImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setImageUrl(String str, ba baVar) {
        this.a = str;
        this.d = baVar;
        a(false);
    }

    public void setDefaultImageResId(int i) {
        this.b = i;
    }

    public void setErrorImageResId(int i) {
        this.c = i;
    }

    void a(boolean z) {
        boolean z2;
        boolean z3;
        int width = getWidth();
        int height = getHeight();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            z3 = layoutParams.width == -2;
            z2 = layoutParams.height == -2;
        } else {
            z2 = false;
            z3 = false;
        }
        boolean z4 = z3 && z2;
        if (width != 0 || height != 0 || z4) {
            if (TextUtils.isEmpty(this.a)) {
                if (this.e != null) {
                    this.e.a();
                    this.e = null;
                }
                a();
                return;
            }
            if (this.e != null && this.e.c() != null) {
                if (!this.e.c().equals(this.a)) {
                    this.e.a();
                    a();
                } else {
                    return;
                }
            }
            int i = z3 ? 0 : width;
            int i2 = z2 ? 0 : height;
            if (this.d != null) {
                this.e = this.d.a(this.a, new cca(this, z), i, i2);
            }
        }
    }

    private void a() {
        if (this.b != 0) {
            setImageResource(this.b);
        } else {
            setImageBitmap(null);
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        a(true);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        if (this.e != null) {
            this.e.a();
            setImageBitmap(null);
            this.e = null;
        }
        super.onDetachedFromWindow();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    public void setErrorListener(ccc cccVar) {
        this.f = cccVar;
    }

    public void setSuccessListener(ccd ccdVar) {
        this.g = ccdVar;
    }

    public String getUrl() {
        return this.a;
    }
}
