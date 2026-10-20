package org.apache.http;

@Deprecated
/* loaded from: classes.dex */
public interface Header {
    HeaderElement[] getElements();

    String getName();

    String getValue();
}
