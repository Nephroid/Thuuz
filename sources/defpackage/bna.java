package defpackage;

import com.thuuz.gtv.data.GameData;

/* loaded from: classes.dex */
/* synthetic */ class bna {
    static final /* synthetic */ int[] a = new int[GameData.GAME_STATUS.values().length];

    static {
        try {
            a[GameData.GAME_STATUS.UPCOMING.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[GameData.GAME_STATUS.COMPLETED.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
    }
}
