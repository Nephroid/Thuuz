package defpackage;

import com.thuuz.gtv.util.ServerChoice;

/* loaded from: classes.dex */
public /* synthetic */ class cai {
    public static final /* synthetic */ int[] a = new int[ServerChoice.values().length];

    static {
        try {
            a[ServerChoice.SERVER_TYPE_DEV.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[ServerChoice.SERVER_TYPE_STAGE.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[ServerChoice.SERVER_TYPE_PROD.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
    }
}
