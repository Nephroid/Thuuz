package com.google.android.gms.ads;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.internal.dr;
import defpackage.adn;
import defpackage.xu;

/* loaded from: classes.dex */
public final class AdActivity extends Activity {
    private xu a;

    private void a() {
        if (this.a != null) {
            try {
                this.a.j();
            } catch (RemoteException e) {
                adn.d("Could not forward setContentViewSet to ad overlay:", e);
            }
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = dr.a((Activity) this);
        if (this.a == null) {
            adn.e("Could not create ad overlay.");
            finish();
            return;
        }
        try {
            this.a.a(bundle);
        } catch (RemoteException e) {
            adn.d("Could not forward onCreate to ad overlay:", e);
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        try {
            if (this.a != null) {
                this.a.i();
            }
        } catch (RemoteException e) {
            adn.d("Could not forward onDestroy to ad overlay:", e);
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onPause() {
        try {
            if (this.a != null) {
                this.a.g();
            }
        } catch (RemoteException e) {
            adn.d("Could not forward onPause to ad overlay:", e);
            finish();
        }
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        try {
            if (this.a != null) {
                this.a.d();
            }
        } catch (RemoteException e) {
            adn.d("Could not forward onRestart to ad overlay:", e);
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        try {
            if (this.a != null) {
                this.a.f();
            }
        } catch (RemoteException e) {
            adn.d("Could not forward onResume to ad overlay:", e);
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        try {
            if (this.a != null) {
                this.a.b(bundle);
            }
        } catch (RemoteException e) {
            adn.d("Could not forward onSaveInstanceState to ad overlay:", e);
            finish();
        }
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        try {
            if (this.a != null) {
                this.a.e();
            }
        } catch (RemoteException e) {
            adn.d("Could not forward onStart to ad overlay:", e);
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        try {
            if (this.a != null) {
                this.a.h();
            }
        } catch (RemoteException e) {
            adn.d("Could not forward onStop to ad overlay:", e);
            finish();
        }
        super.onStop();
    }

    @Override // android.app.Activity
    public void setContentView(int i) {
        super.setContentView(i);
        a();
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        super.setContentView(view);
        a();
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        a();
    }
}
