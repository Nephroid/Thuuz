package defpackage;

import com.google.gson.internal.LinkedTreeMap;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* loaded from: classes.dex */
class ayb<K, V> extends LinkedTreeMap<K, V>.aye<Map.Entry<K, V>> {
    final /* synthetic */ aya a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ayb(aya ayaVar) {
        super(ayaVar.a, null);
        this.a = ayaVar;
    }

    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Map.Entry<K, V> next() {
        return b();
    }
}
