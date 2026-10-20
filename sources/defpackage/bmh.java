package defpackage;

import android.view.View;
import android.widget.TextView;
import com.thuuz.gtv.data.GameData;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class bmh extends bzy {
    public Integer a;
    public boolean b;
    public int c;
    public int d = -1;
    public int e;
    public int f;

    public abstract void a(int i);

    public abstract void a(int i, int i2);

    public abstract void a(int i, String str);

    public abstract void a(GameData gameData);

    public abstract void a(GameData gameData, Set<Integer> set);

    public abstract void a(String str);

    public abstract void a(boolean z);

    public abstract void a(boolean z, String str);

    public abstract void b(int i);

    public abstract void b(int i, String str);

    public abstract void b(String str);

    public abstract void b(boolean z);

    public abstract void c(int i);

    public abstract void c(String str);

    public abstract void c(boolean z);

    public abstract void d(int i);

    public abstract void d(String str);

    public abstract void d(boolean z);

    public abstract void e(boolean z);

    public abstract void f();

    public abstract void f(boolean z);

    public abstract void g();

    public abstract void g(int i);

    public abstract void g(boolean z);

    public abstract TextView h(int i);

    public abstract void h(boolean z);

    public abstract boolean h();

    public abstract void i(boolean z);

    public bmh(boolean z) {
        this.b = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a() {
        return this.d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(int i) {
        this.d = i;
    }

    public void f(int i) {
    }

    public void a(String str, View.OnClickListener onClickListener, ba baVar) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int b() {
        return bbq.team_name;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int c() {
        return bbq.team2_name;
    }

    public int d() {
        return bbq.score;
    }

    public int e() {
        return bbq.score2;
    }

    public Integer i() {
        return this.a;
    }

    public void a(Integer num) {
        this.a = num;
    }

    public String toString() {
        return "holder for game: " + this.a;
    }
}
