package defpackage;

import android.graphics.Bitmap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bd implements Runnable {
    final /* synthetic */ ba a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bd(ba baVar) {
        this.a = baVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        HashMap hashMap;
        HashMap hashMap2;
        LinkedList linkedList;
        bh bhVar;
        Bitmap bitmap;
        bh bhVar2;
        bh bhVar3;
        hashMap = this.a.e;
        for (be beVar : hashMap.values()) {
            linkedList = beVar.e;
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                bg bgVar = (bg) it.next();
                bhVar = bgVar.c;
                if (bhVar != null) {
                    if (beVar.a() != null) {
                        bhVar3 = bgVar.c;
                        bhVar3.a(beVar.a());
                    } else {
                        bitmap = beVar.c;
                        bgVar.b = bitmap;
                        bhVar2 = bgVar.c;
                        bhVar2.a(bgVar, false);
                    }
                }
            }
        }
        hashMap2 = this.a.e;
        hashMap2.clear();
        this.a.g = null;
    }
}
