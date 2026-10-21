package defpackage;

import com.google.gson.internal.LinkedTreeMap;

/* JADX INFO: Add missing generic type declarations: [K] */
/* loaded from: classes.dex */
class ayd<K> extends LinkedTreeMap<K, V>.aye<K> {
    final /* synthetic */ ayc a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ayd(ayc aycVar) {
        super(aycVar.a, null);
        this.a = aycVar;
    }

    public K next() {
        return b().f;
    }
}
