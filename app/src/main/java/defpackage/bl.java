package defpackage;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;

/* loaded from: classes.dex */
public class bl implements bh {
    final /* synthetic */ boolean a;
    final /* synthetic */ NetworkImageView b;

    public bl(NetworkImageView networkImageView, boolean z) {
        this.b = networkImageView;
        this.a = z;
    }

    @Override // defpackage.ai
    public void a(VolleyError volleyError) {
        int i;
        int i2;
        i = this.b.c;
        if (i != 0) {
            NetworkImageView networkImageView = this.b;
            i2 = this.b.c;
            networkImageView.setImageResource(i2);
        }
    }

    @Override // defpackage.bh
    public void a(bg bgVar, boolean z) {
        int i;
        int i2;
        if (z && this.a) {
            this.b.post(new bm(this, bgVar));
            return;
        }
        if (bgVar.b() == null) {
            i = this.b.b;
            if (i != 0) {
                NetworkImageView networkImageView = this.b;
                i2 = this.b.b;
                networkImageView.setImageResource(i2);
                return;
            }
            return;
        }
        this.b.setImageBitmap(bgVar.b());
    }
}
