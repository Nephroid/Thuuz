package org.apache.http.impl.io;

import org.apache.http.Header;
import org.apache.http.HttpMessage;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.LineParser;
import org.apache.http.params.HttpParams;

@Deprecated
/* loaded from: classes.dex */
public abstract class AbstractMessageParser implements HttpMessageParser {
    protected final LineParser lineParser;

    public abstract HttpMessage parseHead(SessionInputBuffer sessionInputBuffer);

    public AbstractMessageParser(SessionInputBuffer sessionInputBuffer, LineParser lineParser, HttpParams httpParams) {
        throw new RuntimeException("Stub!");
    }

    public static Header[] parseHeaders(SessionInputBuffer sessionInputBuffer, int i, int i2, LineParser lineParser) {
        throw new RuntimeException("Stub!");
    }

    @Override // org.apache.http.io.HttpMessageParser
    public HttpMessage parse() {
        throw new RuntimeException("Stub!");
    }
}
