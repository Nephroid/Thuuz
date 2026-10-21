package defpackage;

import com.google.gson.internal.LinkedTreeMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* loaded from: classes.dex */
public class aya<K, V> extends AbstractSet<Map.Entry<K, V>> {
    final /* synthetic */ LinkedTreeMap a;

    public aya(LinkedTreeMap linkedTreeMap) {
        this.a = linkedTreeMap;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.a.size;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<Map.Entry<K, V>> iterator() {
        return new ayb(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return (obj instanceof Map.Entry) && this.a.a((Map.Entry<?, ?>) obj) != null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        ayf<K, V> a;
        if (!(obj instanceof Map.Entry) || (a = this.a.a((Map.Entry<?, ?>) obj)) == null) {
            return false;
        }
        this.a.a((ayf) a, true);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.a.clear();
    }
}
