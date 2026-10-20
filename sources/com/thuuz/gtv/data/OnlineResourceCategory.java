package com.thuuz.gtv.data;

import defpackage.bpq;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class OnlineResourceCategory extends bpq implements Comparable<OnlineResourceCategory> {

    /* loaded from: classes.dex */
    public enum ONLINE_RESOURCE_CATEGORY_TYPE {
        UNKNOWN(0),
        PREVIEW(1),
        RECAP(2),
        SUMMARY(3),
        STATS(4),
        TRACK(5),
        WATCH_ON_TV(6),
        WATCH_ONLINE(7),
        WATCH_HIGHLIGHTS(8),
        LISTEN(9),
        GAME_TRACKER(10),
        DYN_TAB(11);

        private static ONLINE_RESOURCE_CATEGORY_TYPE[] m = {UNKNOWN, PREVIEW, RECAP, SUMMARY, STATS, TRACK, WATCH_ON_TV, WATCH_ONLINE, WATCH_HIGHLIGHTS, LISTEN, GAME_TRACKER, DYN_TAB};
        private int mType;

        ONLINE_RESOURCE_CATEGORY_TYPE(int i) {
            this.mType = i;
        }

        public int a() {
            return this.mType;
        }

        public static ONLINE_RESOURCE_CATEGORY_TYPE a(int i) {
            ONLINE_RESOURCE_CATEGORY_TYPE online_resource_category_type = UNKNOWN;
            for (ONLINE_RESOURCE_CATEGORY_TYPE online_resource_category_type2 : m) {
                if (online_resource_category_type2.mType == i) {
                    return online_resource_category_type2;
                }
            }
            return online_resource_category_type;
        }
    }

    public OnlineResourceCategory(JSONObject jSONObject) {
        super(jSONObject);
    }

    public Integer a() {
        return Integer.valueOf(f("id"));
    }

    public Integer b() {
        return Integer.valueOf(f("sort_order"));
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(OnlineResourceCategory onlineResourceCategory) {
        return b().compareTo(onlineResourceCategory.b());
    }
}
