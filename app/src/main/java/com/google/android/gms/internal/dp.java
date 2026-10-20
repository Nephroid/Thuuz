package com.google.android.gms.internal;

import android.R;
import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import defpackage.adm;
import defpackage.zy;

@zy
/* loaded from: classes.dex */
public final class dp extends FrameLayout implements View.OnClickListener {
    private final Activity a;
    private final ImageButton b;

    public dp(Activity activity, int i) {
        super(activity);
        this.a = activity;
        setOnClickListener(this);
        this.b = new ImageButton(activity);
        this.b.setImageResource(R.drawable.btn_dialog);
        this.b.setBackgroundColor(0);
        this.b.setOnClickListener(this);
        this.b.setPadding(0, 0, 0, 0);
        this.b.setContentDescription("Interstitial close button");
        int a = adm.a(activity, i);
        addView(this.b, new FrameLayout.LayoutParams(a, a, 17));
    }

    public void a(boolean z) {
        this.b.setVisibility(z ? 4 : 0);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.a.finish();
    }
}
