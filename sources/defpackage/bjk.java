package defpackage;

import com.thuuz.gtv.activity.game_page.AbstractGamePageFragment;
import com.thuuz.gtv.activity.game_page.GamePageActivity;

/* loaded from: classes.dex */
public class bjk {
    String a;
    public AbstractGamePageFragment.FragType b;
    public int c;
    final /* synthetic */ GamePageActivity d;

    public bjk(GamePageActivity gamePageActivity, AbstractGamePageFragment.FragType fragType) {
        this.d = gamePageActivity;
        this.c = -1;
        this.b = fragType;
    }

    public bjk(GamePageActivity gamePageActivity, AbstractGamePageFragment.FragType fragType, String str, int i) {
        this.d = gamePageActivity;
        this.c = -1;
        this.b = fragType;
        this.a = str;
        this.c = i;
    }

    public String a() {
        return this.b == AbstractGamePageFragment.FragType.DYNAMIC ? this.b.e() + this.c : this.b.e();
    }

    public CharSequence b() {
        return this.b == AbstractGamePageFragment.FragType.DYNAMIC ? this.a : this.b.d();
    }

    public long c() {
        int c = this.b.c();
        if (this.c >= 0) {
            c = (c * 100) + this.c;
        }
        return c;
    }
}
