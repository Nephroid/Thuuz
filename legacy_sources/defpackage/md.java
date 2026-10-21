package defpackage;

import android.os.Bundle;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class md<T> implements ly<T> {
    private final String a;
    private final Set<String> b;
    private final Set<String> c;
    private final int d;

    /* JADX INFO: Access modifiers changed from: protected */
    public md(String str, int i) {
        this.a = (String) kb.a(str, "fieldName");
        this.b = Collections.singleton(str);
        this.c = Collections.emptySet();
        this.d = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public md(String str, Collection<String> collection, Collection<String> collection2, int i) {
        this.a = (String) kb.a(str, "fieldName");
        this.b = Collections.unmodifiableSet(new HashSet(collection));
        this.c = Collections.unmodifiableSet(new HashSet(collection2));
        this.d = i;
    }

    @Override // defpackage.ly
    public final T a(Bundle bundle) {
        kb.a(bundle, "bundle");
        if (bundle.get(a()) != null) {
            return b(bundle);
        }
        return null;
    }

    @Override // defpackage.ly
    public final String a() {
        return this.a;
    }

    protected abstract T b(Bundle bundle);

    public String toString() {
        return this.a;
    }
}
