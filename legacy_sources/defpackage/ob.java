package defpackage;

import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [T] */
/* loaded from: classes.dex */
public class ob<T> implements oh<T> {
    final /* synthetic */ oa a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ob(oa oaVar) {
        this.a = oaVar;
    }

    /* JADX WARN: Incorrect types in method signature: (TT;)V */
    @Override // defpackage.oh
    public void a(nz nzVar) {
        LinkedList linkedList;
        LinkedList linkedList2;
        nz nzVar2;
        this.a.a = nzVar;
        linkedList = this.a.c;
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            oc ocVar = (oc) it.next();
            nzVar2 = this.a.a;
            ocVar.a(nzVar2);
        }
        linkedList2 = this.a.c;
        linkedList2.clear();
        this.a.b = null;
    }
}
