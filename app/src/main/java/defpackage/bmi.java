package defpackage;

import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.data.GameData;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeSet;
import java.util.logging.Level;

/* loaded from: classes.dex */
public abstract class bmi extends bzy implements Comparator<Integer> {
    private final TreeSet<Integer> a;
    private Map<Integer, GameData> b;
    private IService c;

    protected abstract int a(GameData gameData, GameData gameData2);

    public bmi(App app, Map<Integer, GameData> map) {
        this.a = app.x();
        this.b = map;
    }

    public bmi(AbstractActivity abstractActivity) {
        this.c = abstractActivity.F();
        this.a = abstractActivity.j().x();
    }

    @Override // java.util.Comparator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(Integer num, Integer num2) {
        try {
            GameData a = a(num);
            GameData a2 = a(num2);
            if (a == null || a2 == null) {
                return 0;
            }
            return a(a, a2);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "cannot handle game comparison: ", th);
            return 0;
        }
    }

    private GameData a(Integer num) {
        if (this.b != null) {
            return this.b.get(num);
        }
        if (this.c != null) {
            return this.c.b(num);
        }
        return null;
    }

    public TreeSet<Integer> a() {
        return this.a;
    }
}
