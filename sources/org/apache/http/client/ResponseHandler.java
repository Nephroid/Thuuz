package org.apache.http.client;

import org.apache.http.HttpResponse;

@Deprecated
/* loaded from: classes.dex */
public interface ResponseHandler<T> {
    T handleResponse(HttpResponse httpResponse);
}
