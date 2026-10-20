package defpackage;

import com.thuuz.gtv.activity.sports.GuideActivity;
import com.thuuz.gtv.data.GameData;

/* loaded from: classes.dex */
public class bmy extends bmi {
    public bmy(GuideActivity guideActivity) {
        super(guideActivity);
    }

    @Override // defpackage.bmi
    protected int a(GameData gameData, GameData gameData2) {
        int compareTo = Boolean.valueOf(gameData2.a(a())).compareTo(Boolean.valueOf(gameData.a(a())));
        if (compareTo == 0) {
            int compareTo2 = Boolean.valueOf(gameData2.I()).compareTo(Boolean.valueOf(gameData.I()));
            if (compareTo2 == 0) {
                return Integer.valueOf(gameData2.h(a())).compareTo(Integer.valueOf(gameData.h(a())));
            }
            return compareTo2;
        }
        return compareTo;
    }
}
