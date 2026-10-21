package org.apache.http;

import org.apache.http.protocol.HttpContext;

@Deprecated
/* loaded from: classes.dex */
public interface HttpResponseInterceptor {
    void process(HttpResponse httpResponse, HttpContext httpContext);
}
