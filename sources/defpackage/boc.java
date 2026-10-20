package defpackage;

import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.data.GameData;
import java.util.Set;

/* loaded from: classes.dex */
public class boc extends bmn {
    public boc(GameData gameData, AbstractActivity abstractActivity) {
        super(gameData, abstractActivity);
    }

    @Override // defpackage.bmn
    public String e() {
        GameData f = f();
        String w = f.w();
        return f().x() + " " + f.aq() + " " + w;
    }

    @Override // defpackage.bmn
    public String g() {
        GameData f = f();
        String w = f.w();
        return f.x() + " " + f.aq() + " " + w;
    }

    @Override // defpackage.bmn
    protected void c(bmh bmhVar) {
        a(bmhVar.c(), f().w(), f().P(), c(), bmhVar);
    }

    @Override // defpackage.bmn
    protected void b(bmh bmhVar) {
        a(bmhVar.b(), f().x(), f().Q(), d(), bmhVar);
    }

    @Override // defpackage.bmn
    protected boolean b() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bmn
    public boolean a(Set<Integer> set) {
        return super.b(set);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bmn
    public boolean b(Set<Integer> set) {
        return super.a(set);
    }
}
