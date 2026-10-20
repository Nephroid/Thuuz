package org.apache.http.conn.scheme;

import java.net.InetAddress;

/* loaded from: classes.dex */
public interface HostNameResolver {
    InetAddress resolve(String str);
}
