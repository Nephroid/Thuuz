package defpackage;

import com.thuuz.gtv.data.GameData;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public /* synthetic */ class bmp {
    static final /* synthetic */ int[] a = new int[GameData.GAME_STATUS.values().length];

    static {
        try {
            a[GameData.GAME_STATUS.COMPLETED.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[GameData.GAME_STATUS.UPCOMING.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
    }
}
