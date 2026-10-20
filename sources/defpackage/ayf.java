package defpackage;

import java.util.Map;

/* loaded from: classes.dex */
public final class ayf<K, V> implements Map.Entry<K, V> {
    public ayf<K, V> a;
    public ayf<K, V> b;
    public ayf<K, V> c;
    public ayf<K, V> d;
    public ayf<K, V> e;
    public final K f;
    public V g;
    public int h;

    public ayf() {
        this.f = null;
        this.e = this;
        this.d = this;
    }

    public ayf(ayf<K, V> ayfVar, K k, ayf<K, V> ayfVar2, ayf<K, V> ayfVar3) {
        this.a = ayfVar;
        this.f = k;
        this.h = 1;
        this.d = ayfVar2;
        this.e = ayfVar3;
        ayfVar3.d = this;
        ayfVar2.e = this;
    }

    @Override // java.util.Map.Entry
    public K getKey() {
        return this.f;
    }

    @Override // java.util.Map.Entry
    public V getValue() {
        return this.g;
    }

    @Override // java.util.Map.Entry
    public V setValue(V v) {
        V v2 = this.g;
        this.g = v;
        return v2;
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (this.f == null) {
            if (entry.getKey() != null) {
                return false;
            }
        } else if (!this.f.equals(entry.getKey())) {
            return false;
        }
        if (this.g == null) {
            if (entry.getValue() != null) {
                return false;
            }
        } else if (!this.g.equals(entry.getValue())) {
            return false;
        }
        return true;
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        return (this.f == null ? 0 : this.f.hashCode()) ^ (this.g != null ? this.g.hashCode() : 0);
    }

    public String toString() {
        return this.f + "=" + this.g;
    }

    public ayf<K, V> a() {
        for (ayf<K, V> ayfVar = this.b; ayfVar != null; ayfVar = ayfVar.b) {
            this = ayfVar;
        }
        return this;
    }

    public ayf<K, V> b() {
        for (ayf<K, V> ayfVar = this.c; ayfVar != null; ayfVar = ayfVar.c) {
            this = ayfVar;
        }
        return this;
    }
}
