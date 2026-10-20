package defpackage;

import com.thuuz.gtv.data.FantasySettingsCustomizations;

/* loaded from: classes.dex */
public /* synthetic */ class bfz {
    static final /* synthetic */ int[] a = new int[FantasySettingsCustomizations.ALERT_TYPE.values().length];

    static {
        try {
            a[FantasySettingsCustomizations.ALERT_TYPE.ALERT_TYPE_COMPETITION.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[FantasySettingsCustomizations.ALERT_TYPE.ALERT_TYPE_SCORING_POSITION.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[FantasySettingsCustomizations.ALERT_TYPE.ALERT_TYPE_HOT_PLAYER.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[FantasySettingsCustomizations.ALERT_TYPE.ALERT_TYPE_INJURY.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
    }
}
