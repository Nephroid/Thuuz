package com.viewpagerindicator;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import defpackage.cco;
import defpackage.ccp;
import defpackage.ccs;
import defpackage.ccu;
import defpackage.cda;
import defpackage.cdb;
import defpackage.cdc;
import defpackage.cdd;

/* loaded from: classes.dex */
public class TabPageIndicator extends HorizontalScrollView implements ccs {
    private static final CharSequence a = "";
    private Runnable b;
    private final View.OnClickListener c;
    private final ccp d;
    private ViewPager e;
    private ViewPager.OnPageChangeListener f;
    private int g;
    private cdc h;

    public TabPageIndicator(Context context) {
        this(context, null);
    }

    public TabPageIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = new cda(this);
        setHorizontalScrollBarEnabled(false);
        this.d = new ccp(context, ccu.vpiTabPageIndicatorStyle);
        addView(this.d, new ViewGroup.LayoutParams(-2, -1));
    }

    public void setOnTabReselectedListener(cdc cdcVar) {
        this.h = cdcVar;
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        boolean z = View.MeasureSpec.getMode(i) == 1073741824;
        setFillViewport(z);
        int measuredWidth = getMeasuredWidth();
        super.onMeasure(i, i2);
        int measuredWidth2 = getMeasuredWidth();
        if (z && measuredWidth != measuredWidth2) {
            setCurrentItem(this.g);
        }
    }

    private void a(int i) {
        View childAt = this.d.getChildAt(i);
        if (this.b != null) {
            removeCallbacks(this.b);
        }
        this.b = new cdb(this, childAt);
        post(this.b);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.b != null) {
            post(this.b);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.b != null) {
            removeCallbacks(this.b);
        }
    }

    private void a(int i, CharSequence charSequence, int i2) {
        cdd cddVar = new cdd(this, getContext());
        cddVar.b = i;
        cddVar.setFocusable(true);
        cddVar.setOnClickListener(this.c);
        cddVar.setText(charSequence);
        if (i2 != 0) {
            cddVar.setCompoundDrawablesWithIntrinsicBounds(i2, 0, 0, 0);
        }
        this.d.addView(cddVar, new LinearLayout.LayoutParams(-2, -1, 1.0f));
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        if (this.f != null) {
            this.f.onPageScrollStateChanged(i);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        if (this.f != null) {
            this.f.onPageScrolled(i, f, i2);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        setCurrentItem(i);
        if (this.f != null) {
            this.f.onPageSelected(i);
        }
    }

    public void setViewPager(ViewPager viewPager) {
        if (this.e != viewPager) {
            if (this.e != null) {
                this.e.setOnPageChangeListener(null);
            }
            if (viewPager.getAdapter() == null) {
                throw new IllegalStateException("ViewPager does not have adapter instance.");
            }
            this.e = viewPager;
            viewPager.setOnPageChangeListener(this);
            a();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void a() {
        this.d.removeAllViews();
        PagerAdapter adapter = this.e.getAdapter();
        cco ccoVar = null;
        if (adapter instanceof cco) {
            ccoVar = (cco) adapter;
        }
        int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            CharSequence pageTitle = adapter.getPageTitle(i);
            a(i, pageTitle == null ? a : pageTitle, ccoVar != null ? ccoVar.a(i) : 0);
        }
        if (this.g > count) {
            this.g = count - 1;
        }
        setCurrentItem(this.g);
        requestLayout();
    }

    public void setViewPager(ViewPager viewPager, int i) {
        setViewPager(viewPager);
        setCurrentItem(i);
    }

    public void setCurrentItem(int i) {
        if (this.e == null && !isInEditMode()) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        this.g = i;
        if (this.e != null) {
            this.e.setCurrentItem(i);
        }
        if (this.d != null) {
            int childCount = this.d.getChildCount();
            int i2 = 0;
            while (i2 < childCount) {
                View childAt = this.d.getChildAt(i2);
                if (childAt != null) {
                    boolean z = i2 == i;
                    childAt.setSelected(z);
                    if (z) {
                        a(i);
                    }
                }
                i2++;
            }
        }
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.f = onPageChangeListener;
    }
}
