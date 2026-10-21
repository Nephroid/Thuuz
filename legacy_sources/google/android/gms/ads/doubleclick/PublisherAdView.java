package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import defpackage.cx;
import defpackage.db;
import defpackage.de;
import defpackage.uf;

/* loaded from: classes.dex */
public final class PublisherAdView extends ViewGroup {
    private final uf a;

    public PublisherAdView(Context context) {
        super(context);
        this.a = new uf(this);
    }

    public PublisherAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new uf(this, attributeSet, true);
    }

    public PublisherAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new uf(this, attributeSet, true);
    }

    public cx getAdListener() {
        return this.a.b();
    }

    public db getAdSize() {
        return this.a.c();
    }

    public db[] getAdSizes() {
        return this.a.d();
    }

    public String getAdUnitId() {
        return this.a.e();
    }

    public de getAppEventListener() {
        return this.a.f();
    }

    public String getMediationAdapterClassName() {
        return this.a.j();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(0);
        if (childAt == null || childAt.getVisibility() == 8) {
            return;
        }
        int measuredWidth = childAt.getMeasuredWidth();
        int measuredHeight = childAt.getMeasuredHeight();
        int i5 = ((i3 - i) - measuredWidth) / 2;
        int i6 = ((i4 - i2) - measuredHeight) / 2;
        childAt.layout(i5, i6, measuredWidth + i5, measuredHeight + i6);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        int i4 = 0;
        View childAt = getChildAt(0);
        db adSize = getAdSize();
        if (childAt != null && childAt.getVisibility() != 8) {
            measureChild(childAt, i, i2);
            i3 = childAt.getMeasuredWidth();
            i4 = childAt.getMeasuredHeight();
        } else if (adSize != null) {
            Context context = getContext();
            i3 = adSize.b(context);
            i4 = adSize.a(context);
        } else {
            i3 = 0;
        }
        setMeasuredDimension(View.resolveSize(Math.max(i3, getSuggestedMinimumWidth()), i), View.resolveSize(Math.max(i4, getSuggestedMinimumHeight()), i2));
    }

    public void setAdListener(cx cxVar) {
        this.a.a(cxVar);
    }

    public void setAdSizes(db... dbVarArr) {
        if (dbVarArr == null || dbVarArr.length < 1) {
            throw new IllegalArgumentException("The supported ad sizes must contain at least one valid ad size.");
        }
        this.a.b(dbVarArr);
    }

    public void setAdUnitId(String str) {
        this.a.a(str);
    }

    public void setAppEventListener(de deVar) {
        this.a.a(deVar);
    }
}
