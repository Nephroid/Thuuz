package defpackage;

import com.thuuz.gtv.data.FantasySite;

/* loaded from: classes.dex */
public /* synthetic */ class bfh {
    static final /* synthetic */ int[] a = new int[FantasySite.FantasySiteStatus.values().length];

    static {
        try {
            a[FantasySite.FantasySiteStatus.CONNECTED.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[FantasySite.FantasySiteStatus.DISCONNECTED.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
    }
}
