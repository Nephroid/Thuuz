package defpackage;

import com.thuuz.gtv.activity.fantasy.FantasyCompListFrag;

/* loaded from: classes.dex */
public class bep {
    public boolean a;
    boolean b;
    public int c;
    final /* synthetic */ FantasyCompListFrag d;
    private final bpg e;

    public static /* synthetic */ bpg a(bep bepVar) {
        return bepVar.e;
    }

    public bep(FantasyCompListFrag fantasyCompListFrag, boolean z, bpg bpgVar, boolean z2) {
        this.d = fantasyCompListFrag;
        this.a = z;
        this.c = bpgVar.a();
        this.e = bpgVar;
        this.b = z2;
    }

    public boolean a() {
        return this.c == -2 || this.c == -1;
    }
}
