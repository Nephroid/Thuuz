package defpackage;

import com.thuuz.gtv.App;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.data.GameData;
import java.util.Map;

/* loaded from: classes.dex */
public class bmr extends bmi {
    public bmr(App app, Map<Integer, GameData> map) {
        super(app, map);
    }

    public bmr(AbstractActivity abstractActivity) {
        super(abstractActivity);
    }

    @Override // defpackage.bmi
    protected int a(GameData gameData, GameData gameData2) {
        int compareTo = Boolean.valueOf(gameData2.a(a())).compareTo(Boolean.valueOf(gameData.a(a())));
        if (compareTo == 0) {
            int compareTo2 = Integer.valueOf(gameData2.e(a())).compareTo(Integer.valueOf(gameData.e(a())));
            if (compareTo2 == 0) {
                return gameData.n().compareTo(gameData2.n());
            }
            return compareTo2;
        }
        return compareTo;
    }
}
