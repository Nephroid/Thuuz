package defpackage;

import com.thuuz.gtv.IService;

/* loaded from: classes.dex */
public /* synthetic */ class bcl {
    public static final /* synthetic */ int[] a = new int[IService.API_REQUEST_GAME_STATUS.values().length];

    static {
        try {
            a[IService.API_REQUEST_GAME_STATUS.ALL.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[IService.API_REQUEST_GAME_STATUS.COMPLETED.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[IService.API_REQUEST_GAME_STATUS.LIVE.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[IService.API_REQUEST_GAME_STATUS.UPCOMING.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
    }
}
