package defpackage;

import com.thuuz.gtv.activity.game_page.ButtonData;
import com.thuuz.gtv.data.OnlineResourceCategory;
import com.thuuz.gtv.util.loader.StatefulLoader;

/* loaded from: classes.dex */
public /* synthetic */ class bju {
    static final /* synthetic */ int[] a;
    public static final /* synthetic */ int[] b;
    public static final /* synthetic */ int[] c = new int[StatefulLoader.State.values().length];

    static {
        try {
            c[StatefulLoader.State.Init.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            c[StatefulLoader.State.Finished.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            c[StatefulLoader.State.Loading.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        b = new int[OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.values().length];
        try {
            b[OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.WATCH_ONLINE.ordinal()] = 1;
        } catch (NoSuchFieldError e4) {
        }
        try {
            b[OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.WATCH_HIGHLIGHTS.ordinal()] = 2;
        } catch (NoSuchFieldError e5) {
        }
        try {
            b[OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.LISTEN.ordinal()] = 3;
        } catch (NoSuchFieldError e6) {
        }
        try {
            b[OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.GAME_TRACKER.ordinal()] = 4;
        } catch (NoSuchFieldError e7) {
        }
        try {
            b[OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.WATCH_ON_TV.ordinal()] = 5;
        } catch (NoSuchFieldError e8) {
        }
        a = new int[ButtonData.BUTTON_TYPE.values().length];
        try {
            a[ButtonData.BUTTON_TYPE.SET_TV_PROVIDER.ordinal()] = 1;
        } catch (NoSuchFieldError e9) {
        }
        try {
            a[ButtonData.BUTTON_TYPE.LIVE_TV.ordinal()] = 2;
        } catch (NoSuchFieldError e10) {
        }
        try {
            a[ButtonData.BUTTON_TYPE.TV_LISTINGS.ordinal()] = 3;
        } catch (NoSuchFieldError e11) {
        }
        try {
            a[ButtonData.BUTTON_TYPE.ONLINE_RESOURCE.ordinal()] = 4;
        } catch (NoSuchFieldError e12) {
        }
        try {
            a[ButtonData.BUTTON_TYPE.SLING.ordinal()] = 5;
        } catch (NoSuchFieldError e13) {
        }
        try {
            a[ButtonData.BUTTON_TYPE.DVR.ordinal()] = 6;
        } catch (NoSuchFieldError e14) {
        }
        try {
            a[ButtonData.BUTTON_TYPE.FIND_LOCAL_BAR.ordinal()] = 7;
        } catch (NoSuchFieldError e15) {
        }
    }
}
