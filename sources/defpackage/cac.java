package defpackage;

import com.thuuz.gtv.util.ThuuzWebViewClient;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public /* synthetic */ class cac {
    static final /* synthetic */ int[] a = new int[ThuuzWebViewClient.CONN_RETRY_STATE.values().length];

    static {
        try {
            a[ThuuzWebViewClient.CONN_RETRY_STATE.RETRY.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[ThuuzWebViewClient.CONN_RETRY_STATE.DO_NOTHING.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[ThuuzWebViewClient.CONN_RETRY_STATE.ERROR.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
    }
}
