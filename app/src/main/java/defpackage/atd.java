package defpackage;

import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class atd implements atf {
    final /* synthetic */ Map a;
    final /* synthetic */ Map b;
    final /* synthetic */ Map c;
    final /* synthetic */ Map d;
    final /* synthetic */ atc e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public atd(atc atcVar, Map map, Map map2, Map map3, Map map4) {
        this.e = atcVar;
        this.a = map;
        this.b = map2;
        this.c = map3;
        this.d = map4;
    }

    @Override // defpackage.atf
    public void a(ata ataVar, Set<asz> set, Set<asz> set2, asx asxVar) {
        List<asz> list = (List) this.a.get(ataVar);
        List<String> list2 = (List) this.b.get(ataVar);
        if (list != null) {
            set.addAll(list);
            asxVar.c().a(list, list2);
        }
        List<asz> list3 = (List) this.c.get(ataVar);
        List<String> list4 = (List) this.d.get(ataVar);
        if (list3 != null) {
            set2.addAll(list3);
            asxVar.d().a(list3, list4);
        }
    }
}
