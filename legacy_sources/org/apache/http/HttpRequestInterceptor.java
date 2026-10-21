package org.apache.http;

import org.apache.http.protocol.HttpContext;

@Deprecated
/* loaded from: classes.dex */
public interface HttpRequestInterceptor {
    void process(HttpRequest httpRequest, HttpContext httpContext);
}
