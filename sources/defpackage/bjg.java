package defpackage;

import com.thuuz.gtv.activity.game_page.AbstractGamePageFragment;
import com.thuuz.gtv.data.GameData;

/* loaded from: classes.dex */
public /* synthetic */ class bjg {
    public static final /* synthetic */ int[] a;
    public static final /* synthetic */ int[] b = new int[AbstractGamePageFragment.FragType.values().length];

    static {
        try {
            b[AbstractGamePageFragment.FragType.GAME_MOBILE.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            b[AbstractGamePageFragment.FragType.GAME_TABLET.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            b[AbstractGamePageFragment.FragType.PREVIEW.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            b[AbstractGamePageFragment.FragType.RECAP.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            b[AbstractGamePageFragment.FragType.STATS.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            b[AbstractGamePageFragment.FragType.GRAPH.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
        try {
            b[AbstractGamePageFragment.FragType.DYNAMIC.ordinal()] = 7;
        } catch (NoSuchFieldError e7) {
        }
        a = new int[GameData.GAME_STATUS.values().length];
        try {
            a[GameData.GAME_STATUS.UPCOMING.ordinal()] = 1;
        } catch (NoSuchFieldError e8) {
        }
        try {
            a[GameData.GAME_STATUS.IN_PROGRESS.ordinal()] = 2;
        } catch (NoSuchFieldError e9) {
        }
        try {
            a[GameData.GAME_STATUS.COMPLETED.ordinal()] = 3;
        } catch (NoSuchFieldError e10) {
        }
    }
}
