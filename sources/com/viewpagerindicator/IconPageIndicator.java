package com.viewpagerindicator;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import defpackage.ccn;
import defpackage.cco;
import defpackage.ccp;
import defpackage.ccs;
import defpackage.ccu;

/* loaded from: classes.dex */
public class IconPageIndicator extends HorizontalScrollView implements ccs {
    private final ccp a;
    private ViewPager b;
    private ViewPager.OnPageChangeListener c;
    private Runnable d;
    private int e;

    public IconPageIndicator(Context context) {
        this(context, null);
    }

    public IconPageIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setHorizontalScrollBarEnabled(false);
        this.a = new ccp(context, ccu.vpiIconPageIndicatorStyle);
        addView(this.a, new FrameLayout.LayoutParams(-2, -1, 17));
    }

    private void a(int i) {
        View childAt = this.a.getChildAt(i);
        if (this.d != null) {
            removeCallbacks(this.d);
        }
        this.d = new ccn(this, childAt);
        post(this.d);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.d != null) {
            post(this.d);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.d != null) {
            removeCallbacks(this.d);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        if (this.c != null) {
            this.c.onPageScrollStateChanged(i);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        if (this.c != null) {
            this.c.onPageScrolled(i, f, i2);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        setCurrentItem(i);
        if (this.c != null) {
            this.c.onPageSelected(i);
        }
    }

    public void setViewPager(ViewPager viewPager) {
        if (this.b != viewPager) {
            if (this.b != null) {
                this.b.setOnPageChangeListener(null);
            }
            if (viewPager.getAdapter() == null) {
                throw new IllegalStateException("ViewPager does not have adapter instance.");
            }
            this.b = viewPager;
            viewPager.setOnPageChangeListener(this);
            a();
        }
    }

    public void a() {
        this.a.removeAllViews();
        cco ccoVar = (cco) this.b.getAdapter();
        int a = ccoVar.a();
        for (int i = 0; i < a; i++) {
            ImageView imageView = new ImageView(getContext(), null, ccu.vpiIconPageIndicatorStyle);
            imageView.setImageResource(ccoVar.a(i));
            this.a.addView(imageView);
        }
        if (this.e > a) {
            this.e = a - 1;
        }
        setCurrentItem(this.e);
        requestLayout();
    }

    public void setViewPager(ViewPager viewPager, int i) {
        setViewPager(viewPager);
        setCurrentItem(i);
    }

    public void setCurrentItem(int i) {
        if (this.b == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        this.e = i;
        this.b.setCurrentItem(i);
        int childCount = this.a.getChildCount();
        int i2 = 0;
        while (i2 < childCount) {
            View childAt = this.a.getChildAt(i2);
            boolean z = i2 == i;
            childAt.setSelected(z);
            if (z) {
                a(i);
            }
            i2++;
        }
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.c = onPageChangeListener;
    }
}
