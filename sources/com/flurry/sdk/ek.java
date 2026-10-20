package com.flurry.sdk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class ek<K, V> {
    private final Map<K, List<V>> a = new HashMap();
    private int b;

    public List<V> a(K k) {
        if (k == null) {
            return null;
        }
        return a((ek<K, V>) k, false);
    }

    public void a(K k, V v) {
        if (k != null) {
            a((ek<K, V>) k, true).add(v);
        }
    }

    public boolean b(K k, V v) {
        List<V> a;
        boolean z = false;
        if (k != null && (a = a((ek<K, V>) k, false)) != null) {
            z = a.remove(v);
            if (a.size() == 0) {
                this.a.remove(k);
            }
        }
        return z;
    }

    private List<V> a(K k, boolean z) {
        List<V> list = this.a.get(k);
        if (z && list == null) {
            if (this.b > 0) {
                list = new ArrayList<>(this.b);
            } else {
                list = new ArrayList<>();
            }
            this.a.put(k, list);
        }
        return list;
    }
}
