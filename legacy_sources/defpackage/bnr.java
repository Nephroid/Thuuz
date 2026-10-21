package defpackage;

import com.thuuz.gtv.activity.sports.GuideActivity;
import java.util.Comparator;

/* loaded from: classes.dex */
public class bnr implements Comparator<Integer> {
    final /* synthetic */ GuideActivity a;

    public bnr(GuideActivity guideActivity) {
        this.a = guideActivity;
    }

    @Override // java.util.Comparator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(Integer num, Integer num2) {
        int compareTo = Integer.valueOf(Math.abs(num.intValue())).compareTo(Integer.valueOf(Math.abs(num2.intValue())));
        if (compareTo == 0) {
            return num2.compareTo(num);
        }
        return compareTo;
    }
}
