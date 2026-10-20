package defpackage;

import java.util.Comparator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class tg implements Comparator<tk> {
    final /* synthetic */ te a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public tg(te teVar) {
        this.a = teVar;
    }

    @Override // java.util.Comparator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(tk tkVar, tk tkVar2) {
        return (int) (tkVar.a - tkVar2.a);
    }
}
