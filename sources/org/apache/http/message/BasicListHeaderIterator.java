package org.apache.http.message;

import java.util.List;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;

@Deprecated
/* loaded from: classes.dex */
public class BasicListHeaderIterator implements HeaderIterator {
    protected final List allHeaders;
    protected int currentIndex;
    protected String headerName;
    protected int lastIndex;

    public BasicListHeaderIterator(List list, String str) {
        throw new RuntimeException("Stub!");
    }

    protected int findNext(int i) {
        throw new RuntimeException("Stub!");
    }

    protected boolean filterHeader(int i) {
        throw new RuntimeException("Stub!");
    }

    @Override // org.apache.http.HeaderIterator, java.util.Iterator
    public boolean hasNext() {
        throw new RuntimeException("Stub!");
    }

    @Override // org.apache.http.HeaderIterator
    public Header nextHeader() {
        throw new RuntimeException("Stub!");
    }

    @Override // java.util.Iterator
    public final Object next() {
        throw new RuntimeException("Stub!");
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new RuntimeException("Stub!");
    }
}
