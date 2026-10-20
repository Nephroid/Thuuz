package defpackage;

import android.content.IntentSender;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: if, reason: invalid class name */
/* loaded from: classes.dex */
public class Cif implements Runnable {
    final /* synthetic */ ib a;
    private final int b;
    private final gw c;

    public Cif(ib ibVar, int i, gw gwVar) {
        this.a = ibVar;
        this.b = i;
        this.c = gwVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.c.a()) {
            try {
                this.c.a(this.a.getActivity(), ((this.a.getActivity().getSupportFragmentManager().getFragments().indexOf(this.a) + 1) << 16) + 1);
                return;
            } catch (IntentSender.SendIntentException e) {
                this.a.a();
                return;
            }
        }
        if (hb.b(this.c.c())) {
            hb.a(this.c.c(), this.a.getActivity(), this.a, 2, this.a);
        } else {
            this.a.b(this.b, this.c);
        }
    }
}
