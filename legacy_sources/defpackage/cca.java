package defpackage;

import com.android.volley.VolleyError;
import com.thuuz.gtv.widget.NetworkListenableImageView;

/* loaded from: classes.dex */
public class cca implements bh {
    final /* synthetic */ boolean a;
    final /* synthetic */ NetworkListenableImageView b;

    public cca(NetworkListenableImageView networkListenableImageView, boolean z) {
        this.b = networkListenableImageView;
        this.a = z;
    }

    @Override // defpackage.ai
    public void a(VolleyError volleyError) {
        int i;
        ccc cccVar;
        ccc cccVar2;
        int i2;
        i = this.b.c;
        if (i != 0) {
            NetworkListenableImageView networkListenableImageView = this.b;
            i2 = this.b.c;
            networkListenableImageView.setImageResource(i2);
        }
        cccVar = this.b.f;
        if (cccVar != null) {
            cccVar2 = this.b.f;
            cccVar2.a(this.b, volleyError);
        }
    }

    @Override // defpackage.bh
    public void a(bg bgVar, boolean z) {
        int i;
        int i2;
        ccd ccdVar;
        ccd ccdVar2;
        if (z && this.a) {
            this.b.post(new ccb(this, bgVar));
            return;
        }
        if (bgVar.b() == null) {
            i = this.b.b;
            if (i != 0) {
                NetworkListenableImageView networkListenableImageView = this.b;
                i2 = this.b.b;
                networkListenableImageView.setImageResource(i2);
            }
        } else {
            this.b.setImageBitmap(bgVar.b());
        }
        ccdVar = this.b.g;
        if (ccdVar != null) {
            ccdVar2 = this.b.g;
            ccdVar2.a(this.b);
        }
    }
}
