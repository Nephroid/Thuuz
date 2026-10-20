package com.thuuz.gtv.activity.sports;

import com.thuuz.gtv.App;
import defpackage.bmq;
import defpackage.bmr;
import defpackage.bmy;
import java.util.Comparator;

/* loaded from: classes.dex */
public enum GameSortType {
    SORT_EQ(0, "eq"),
    SORT_CLOCK(1, "clock"),
    SORT_TV(2, "tv");

    private int intType;
    private String label;

    GameSortType(int i, String str) {
        this.intType = i;
        this.label = str;
    }

    public int a() {
        return this.intType;
    }

    public Comparator<Integer> a(GuideActivity guideActivity) {
        switch (this) {
            case SORT_TV:
                return new bmy(guideActivity);
            case SORT_CLOCK:
                return new bmq(guideActivity);
            default:
                return new bmr(guideActivity);
        }
    }

    public static GameSortType a(App app) {
        return a(app.bI());
    }

    private static GameSortType a(int i) {
        switch (i) {
            case 0:
                return SORT_EQ;
            case 1:
                return SORT_CLOCK;
            case 2:
                return SORT_TV;
            default:
                return SORT_EQ;
        }
    }
}
