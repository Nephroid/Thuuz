package defpackage;

import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ate implements atf {
    final /* synthetic */ atc a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ate(atc atcVar) {
        this.a = atcVar;
    }

    @Override // defpackage.atf
    public void a(ata ataVar, Set<asz> set, Set<asz> set2, asx asxVar) {
        set.addAll(ataVar.c());
        set2.addAll(ataVar.d());
        asxVar.e().a(ataVar.c(), ataVar.f());
        asxVar.f().a(ataVar.d(), ataVar.g());
    }
}
