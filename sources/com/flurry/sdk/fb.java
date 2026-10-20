package com.flurry.sdk;

import android.content.Context;

/* loaded from: classes.dex */
public class fb implements fc {
    private final fc a;

    public fb(fc fcVar) {
        this.a = fcVar;
    }

    @Override // com.flurry.sdk.fc
    public void f(Context context) {
        if (this.a != null) {
            this.a.f(context);
        }
    }

    @Override // com.flurry.sdk.fc
    public void g(Context context) {
        if (this.a != null) {
            this.a.g(context);
        }
    }
}
