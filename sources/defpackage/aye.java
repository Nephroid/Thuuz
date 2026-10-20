package defpackage;

import com.google.gson.internal.LinkedTreeMap;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
public abstract class aye<T> implements Iterator<T> {
    ayf<K, V> b;
    ayf<K, V> c;
    int d;
    final /* synthetic */ LinkedTreeMap e;

    private aye(LinkedTreeMap linkedTreeMap) {
        this.e = linkedTreeMap;
        this.b = this.e.header.d;
        this.c = null;
        this.d = this.e.modCount;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ aye(LinkedTreeMap linkedTreeMap, axz axzVar) {
        this(linkedTreeMap);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.b != this.e.header;
    }

    final ayf<K, V> b() {
        ayf<K, V> ayfVar = this.b;
        if (ayfVar == this.e.header) {
            throw new NoSuchElementException();
        }
        if (this.e.modCount != this.d) {
            throw new ConcurrentModificationException();
        }
        this.b = ayfVar.d;
        this.c = ayfVar;
        return ayfVar;
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (this.c == null) {
            throw new IllegalStateException();
        }
        this.e.a((ayf) this.c, true);
        this.c = null;
        this.d = this.e.modCount;
    }
}
