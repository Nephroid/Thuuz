package defpackage;

import com.thuuz.gtv.data.GameData;

/* loaded from: classes.dex */
public /* synthetic */ class bpm {
    public static final /* synthetic */ int[] a;
    public static final /* synthetic */ int[] b;
    public static final /* synthetic */ int[] c = new int[GameData.DateRequestType.values().length];

    static {
        try {
            c[GameData.DateRequestType.WEEKDAY.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            c[GameData.DateRequestType.DATE_SUPER_SHORT.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            c[GameData.DateRequestType.DATE.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            c[GameData.DateRequestType.TIME.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            c[GameData.DateRequestType.SHORT_DATETIME.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            c[GameData.DateRequestType.DAY_DATETIME.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
        try {
            c[GameData.DateRequestType.DATETIME.ordinal()] = 7;
        } catch (NoSuchFieldError e7) {
        }
        b = new int[GameData.GAME_STATUS.values().length];
        try {
            b[GameData.GAME_STATUS.UPCOMING.ordinal()] = 1;
        } catch (NoSuchFieldError e8) {
        }
        try {
            b[GameData.GAME_STATUS.IN_PROGRESS.ordinal()] = 2;
        } catch (NoSuchFieldError e9) {
        }
        try {
            b[GameData.GAME_STATUS.COMPLETED.ordinal()] = 3;
        } catch (NoSuchFieldError e10) {
        }
        a = new int[GameData.GAME_RATING.values().length];
        try {
            a[GameData.GAME_RATING.RATING_OK.ordinal()] = 1;
        } catch (NoSuchFieldError e11) {
        }
        try {
            a[GameData.GAME_RATING.RATING_GOOD.ordinal()] = 2;
        } catch (NoSuchFieldError e12) {
        }
        try {
            a[GameData.GAME_RATING.RATING_GREAT.ordinal()] = 3;
        } catch (NoSuchFieldError e13) {
        }
    }
}
