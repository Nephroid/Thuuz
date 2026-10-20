package defpackage;

import com.thuuz.gtv.data.FetchRequest;

/* loaded from: classes.dex */
/* synthetic */ class bzb {
    static final /* synthetic */ int[] a = new int[FetchRequest.REQ_TYPE.values().length];

    static {
        try {
            a[FetchRequest.REQ_TYPE.RIGHTBAR.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[FetchRequest.REQ_TYPE.WIDGET.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[FetchRequest.REQ_TYPE.RIGHTBAR_FANTASY.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
    }
}
