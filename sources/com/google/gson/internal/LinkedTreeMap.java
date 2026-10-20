package com.google.gson.internal;

import defpackage.axz;
import defpackage.aya;
import defpackage.ayc;
import defpackage.ayf;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class LinkedTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
    static final /* synthetic */ boolean a;
    private static final Comparator<Comparable> b;
    Comparator<? super K> comparator;

    /* JADX WARN: Incorrect inner types in field signature: Lcom/google/gson/internal/LinkedTreeMap<TK;TV;>.aya; */
    private aya entrySet;
    public final ayf<K, V> header;

    /* JADX WARN: Incorrect inner types in field signature: Lcom/google/gson/internal/LinkedTreeMap<TK;TV;>.ayc; */
    private ayc keySet;
    public int modCount;
    ayf<K, V> root;
    public int size;

    static {
        a = !LinkedTreeMap.class.desiredAssertionStatus();
        b = new axz();
    }

    public LinkedTreeMap() {
        this(b);
    }

    public LinkedTreeMap(Comparator<? super K> comparator) {
        this.size = 0;
        this.modCount = 0;
        this.header = new ayf<>();
        this.comparator = comparator == null ? b : comparator;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        ayf<K, V> a2 = a(obj);
        if (a2 != null) {
            return a2.g;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return a(obj) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        ayf<K, V> a2 = a((LinkedTreeMap<K, V>) k, true);
        V v2 = a2.g;
        a2.g = v;
        return v2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.root = null;
        this.size = 0;
        this.modCount++;
        ayf<K, V> ayfVar = this.header;
        ayfVar.e = ayfVar;
        ayfVar.d = ayfVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        ayf<K, V> b2 = b(obj);
        if (b2 != null) {
            return b2.g;
        }
        return null;
    }

    ayf<K, V> a(K k, boolean z) {
        ayf<K, V> ayfVar;
        int i;
        ayf<K, V> ayfVar2;
        Comparator<? super K> comparator = this.comparator;
        ayf<K, V> ayfVar3 = this.root;
        if (ayfVar3 == null) {
            ayfVar = ayfVar3;
            i = 0;
        } else {
            Comparable comparable = comparator == b ? (Comparable) k : null;
            while (true) {
                int compareTo = comparable != null ? comparable.compareTo(ayfVar3.f) : comparator.compare(k, ayfVar3.f);
                if (compareTo == 0) {
                    return ayfVar3;
                }
                ayf<K, V> ayfVar4 = compareTo < 0 ? ayfVar3.b : ayfVar3.c;
                if (ayfVar4 == null) {
                    int i2 = compareTo;
                    ayfVar = ayfVar3;
                    i = i2;
                    break;
                }
                ayfVar3 = ayfVar4;
            }
        }
        if (!z) {
            return null;
        }
        ayf<K, V> ayfVar5 = this.header;
        if (ayfVar == null) {
            if (comparator == b && !(k instanceof Comparable)) {
                throw new ClassCastException(k.getClass().getName() + " is not Comparable");
            }
            ayfVar2 = new ayf<>(ayfVar, k, ayfVar5, ayfVar5.e);
            this.root = ayfVar2;
        } else {
            ayfVar2 = new ayf<>(ayfVar, k, ayfVar5, ayfVar5.e);
            if (i < 0) {
                ayfVar.b = ayfVar2;
            } else {
                ayfVar.c = ayfVar2;
            }
            b(ayfVar, true);
        }
        this.size++;
        this.modCount++;
        return ayfVar2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    ayf<K, V> a(Object obj) {
        if (obj == 0) {
            return null;
        }
        try {
            return a((LinkedTreeMap<K, V>) obj, false);
        } catch (ClassCastException e) {
            return null;
        }
    }

    public ayf<K, V> a(Map.Entry<?, ?> entry) {
        ayf<K, V> a2 = a(entry.getKey());
        if (a2 != null && a(a2.g, entry.getValue())) {
            return a2;
        }
        return null;
    }

    private boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public void a(ayf<K, V> ayfVar, boolean z) {
        int i;
        int i2 = 0;
        if (z) {
            ayfVar.e.d = ayfVar.d;
            ayfVar.d.e = ayfVar.e;
        }
        ayf<K, V> ayfVar2 = ayfVar.b;
        ayf<K, V> ayfVar3 = ayfVar.c;
        ayf<K, V> ayfVar4 = ayfVar.a;
        if (ayfVar2 != null && ayfVar3 != null) {
            ayf<K, V> b2 = ayfVar2.h > ayfVar3.h ? ayfVar2.b() : ayfVar3.a();
            a((ayf) b2, false);
            ayf<K, V> ayfVar5 = ayfVar.b;
            if (ayfVar5 != null) {
                i = ayfVar5.h;
                b2.b = ayfVar5;
                ayfVar5.a = b2;
                ayfVar.b = null;
            } else {
                i = 0;
            }
            ayf<K, V> ayfVar6 = ayfVar.c;
            if (ayfVar6 != null) {
                i2 = ayfVar6.h;
                b2.c = ayfVar6;
                ayfVar6.a = b2;
                ayfVar.c = null;
            }
            b2.h = Math.max(i, i2) + 1;
            a((ayf) ayfVar, (ayf) b2);
            return;
        }
        if (ayfVar2 != null) {
            a((ayf) ayfVar, (ayf) ayfVar2);
            ayfVar.b = null;
        } else if (ayfVar3 != null) {
            a((ayf) ayfVar, (ayf) ayfVar3);
            ayfVar.c = null;
        } else {
            a((ayf) ayfVar, (ayf) null);
        }
        b(ayfVar4, false);
        this.size--;
        this.modCount++;
    }

    public ayf<K, V> b(Object obj) {
        ayf<K, V> a2 = a(obj);
        if (a2 != null) {
            a((ayf) a2, true);
        }
        return a2;
    }

    private void a(ayf<K, V> ayfVar, ayf<K, V> ayfVar2) {
        ayf<K, V> ayfVar3 = ayfVar.a;
        ayfVar.a = null;
        if (ayfVar2 != null) {
            ayfVar2.a = ayfVar3;
        }
        if (ayfVar3 != null) {
            if (ayfVar3.b == ayfVar) {
                ayfVar3.b = ayfVar2;
                return;
            } else {
                if (!a && ayfVar3.c != ayfVar) {
                    throw new AssertionError();
                }
                ayfVar3.c = ayfVar2;
                return;
            }
        }
        this.root = ayfVar2;
    }

    private void b(ayf<K, V> ayfVar, boolean z) {
        while (ayfVar != null) {
            ayf<K, V> ayfVar2 = ayfVar.b;
            ayf<K, V> ayfVar3 = ayfVar.c;
            int i = ayfVar2 != null ? ayfVar2.h : 0;
            int i2 = ayfVar3 != null ? ayfVar3.h : 0;
            int i3 = i - i2;
            if (i3 == -2) {
                ayf<K, V> ayfVar4 = ayfVar3.b;
                ayf<K, V> ayfVar5 = ayfVar3.c;
                int i4 = (ayfVar4 != null ? ayfVar4.h : 0) - (ayfVar5 != null ? ayfVar5.h : 0);
                if (i4 == -1 || (i4 == 0 && !z)) {
                    a((ayf) ayfVar);
                } else {
                    if (!a && i4 != 1) {
                        throw new AssertionError();
                    }
                    b((ayf) ayfVar3);
                    a((ayf) ayfVar);
                }
                if (z) {
                    return;
                }
            } else if (i3 == 2) {
                ayf<K, V> ayfVar6 = ayfVar2.b;
                ayf<K, V> ayfVar7 = ayfVar2.c;
                int i5 = (ayfVar6 != null ? ayfVar6.h : 0) - (ayfVar7 != null ? ayfVar7.h : 0);
                if (i5 == 1 || (i5 == 0 && !z)) {
                    b((ayf) ayfVar);
                } else {
                    if (!a && i5 != -1) {
                        throw new AssertionError();
                    }
                    a((ayf) ayfVar2);
                    b((ayf) ayfVar);
                }
                if (z) {
                    return;
                }
            } else if (i3 == 0) {
                ayfVar.h = i + 1;
                if (z) {
                    return;
                }
            } else {
                if (!a && i3 != -1 && i3 != 1) {
                    throw new AssertionError();
                }
                ayfVar.h = Math.max(i, i2) + 1;
                if (!z) {
                    return;
                }
            }
            ayfVar = ayfVar.a;
        }
    }

    private void a(ayf<K, V> ayfVar) {
        ayf<K, V> ayfVar2 = ayfVar.b;
        ayf<K, V> ayfVar3 = ayfVar.c;
        ayf<K, V> ayfVar4 = ayfVar3.b;
        ayf<K, V> ayfVar5 = ayfVar3.c;
        ayfVar.c = ayfVar4;
        if (ayfVar4 != null) {
            ayfVar4.a = ayfVar;
        }
        a((ayf) ayfVar, (ayf) ayfVar3);
        ayfVar3.b = ayfVar;
        ayfVar.a = ayfVar3;
        ayfVar.h = Math.max(ayfVar2 != null ? ayfVar2.h : 0, ayfVar4 != null ? ayfVar4.h : 0) + 1;
        ayfVar3.h = Math.max(ayfVar.h, ayfVar5 != null ? ayfVar5.h : 0) + 1;
    }

    private void b(ayf<K, V> ayfVar) {
        ayf<K, V> ayfVar2 = ayfVar.b;
        ayf<K, V> ayfVar3 = ayfVar.c;
        ayf<K, V> ayfVar4 = ayfVar2.b;
        ayf<K, V> ayfVar5 = ayfVar2.c;
        ayfVar.b = ayfVar5;
        if (ayfVar5 != null) {
            ayfVar5.a = ayfVar;
        }
        a((ayf) ayfVar, (ayf) ayfVar2);
        ayfVar2.c = ayfVar;
        ayfVar.a = ayfVar2;
        ayfVar.h = Math.max(ayfVar3 != null ? ayfVar3.h : 0, ayfVar5 != null ? ayfVar5.h : 0) + 1;
        ayfVar2.h = Math.max(ayfVar.h, ayfVar4 != null ? ayfVar4.h : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        aya ayaVar = this.entrySet;
        if (ayaVar != null) {
            return ayaVar;
        }
        aya ayaVar2 = new aya(this);
        this.entrySet = ayaVar2;
        return ayaVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        ayc aycVar = this.keySet;
        if (aycVar != null) {
            return aycVar;
        }
        ayc aycVar2 = new ayc(this);
        this.keySet = aycVar2;
        return aycVar2;
    }

    private Object writeReplace() {
        return new LinkedHashMap(this);
    }
}
