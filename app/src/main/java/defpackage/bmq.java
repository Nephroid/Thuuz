package defpackage;

import com.thuuz.gtv.activity.sports.GuideActivity;
import com.thuuz.gtv.data.GameData;
import java.util.Comparator;

/* loaded from: classes.dex */
public class bmq extends bmi implements Comparator<Integer> {
    public bmq(GuideActivity guideActivity) {
        super(guideActivity);
    }

    @Override // defpackage.bmi
    protected int a(GameData gameData, GameData gameData2) {
        int compareTo = Boolean.valueOf(gameData2.a(a())).compareTo(Boolean.valueOf(gameData.a(a())));
        if (compareTo == 0) {
            String n = gameData.n();
            String n2 = gameData2.n();
            if (n != null) {
                compareTo = n.compareTo(n2);
            } else if (n2 != null) {
                compareTo = -1;
            }
            if (gameData.al() && gameData2.al()) {
                compareTo = Integer.valueOf(gameData.ab()).compareTo(Integer.valueOf(gameData2.ab()));
            }
            if (compareTo == 0) {
                return Integer.valueOf(gameData2.h(a())).compareTo(Integer.valueOf(gameData.h(a())));
            }
            return compareTo;
        }
        return compareTo;
    }
}
