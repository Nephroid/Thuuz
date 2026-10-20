package defpackage;

import com.thuuz.gtv.data.FetchRequest;
import com.thuuz.gtv.data.GameData;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class bpo {
    private int a = -1;
    private List<Integer> b = new ArrayList();
    private boolean c;
    private Integer d;
    private Integer e;
    private boolean f;
    private FetchRequest g;
    private GameData h;
    private String i;

    public bpo(FetchRequest fetchRequest) {
        this.g = fetchRequest;
        if (fetchRequest != null) {
            this.d = fetchRequest.k();
            this.e = fetchRequest.l();
            this.f = fetchRequest.m();
        }
    }

    public int a() {
        return this.a;
    }

    public boolean b() {
        return this.a != 0;
    }

    public void a(int i) {
        this.a = i;
    }

    public List<Integer> c() {
        return this.b;
    }

    public boolean d() {
        return this.c;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public boolean e() {
        return this.d != null;
    }

    public Integer f() {
        return this.e;
    }

    public Integer g() {
        return this.d;
    }

    public boolean h() {
        return this.f;
    }

    public FetchRequest i() {
        return this.g;
    }

    public boolean j() {
        return this.g != null && this.g.r();
    }

    public GameData k() {
        return this.h;
    }

    public void a(GameData gameData) {
        this.h = gameData;
    }

    public void a(String str) {
        this.i = str;
    }

    public String l() {
        return this.i;
    }

    public String toString() {
        return "request for code " + g() + "-" + f();
    }
}
