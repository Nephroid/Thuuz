package defpackage;

import com.thuuz.gtv.data.OnlineResourceCategory;

/* loaded from: classes.dex */
public /* synthetic */ class biy {
    public static final /* synthetic */ int[] a = new int[OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.values().length];

    static {
        try {
            a[OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.WATCH_ONLINE.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.WATCH_HIGHLIGHTS.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.LISTEN.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.GAME_TRACKER.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
    }
}
