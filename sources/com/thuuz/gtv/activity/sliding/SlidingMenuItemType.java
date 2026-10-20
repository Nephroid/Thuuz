package com.thuuz.gtv.activity.sliding;

import android.support.v4.os.EnvironmentCompat;
import com.thuuz.gtv.activity.sports.GuideActivity;
import com.thuuz.gtv.settings.SettingsActivity;
import defpackage.bzy;
import java.util.logging.Level;

/* loaded from: classes.dex */
public enum SlidingMenuItemType {
    UNKNOWN(-1, EnvironmentCompat.MEDIA_UNKNOWN, null),
    GUIDE(0, "Guide", GuideActivity.class),
    PROFILE(3, "Profile", SettingsActivity.class),
    SPORT(4, "", null),
    LEAGUE(5, "", null);

    private final Class clazz;
    private final String title;
    private final int type;

    SlidingMenuItemType(int i, String str, Class cls) {
        this.type = i;
        this.title = str;
        this.clazz = cls;
    }

    public Class a() {
        return this.clazz;
    }

    public String b() {
        return this.title;
    }

    public int c() {
        return this.type;
    }

    public static SlidingMenuItemType a(int i) {
        SlidingMenuItemType slidingMenuItemType = UNKNOWN;
        try {
            for (SlidingMenuItemType slidingMenuItemType2 : values()) {
                if (slidingMenuItemType2.c() == i) {
                    return slidingMenuItemType2;
                }
            }
        } catch (Throwable th) {
            bzy.getStaticLogger(SlidingMenuItemType.class).log(Level.SEVERE, "problem: ", th);
        }
        return slidingMenuItemType;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.title;
    }
}
