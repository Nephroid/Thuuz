package defpackage;

import android.net.http.Headers;
import android.os.SystemClock;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
// [removed legacy tracker import]
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.cookie.DateUtils;

/* loaded from: classes.dex */
public class ap implements ac {
    protected static final boolean a = am.b;
    private static int d = Constants.YOZIO_SMS_SEND_LIMIT;
    private static int e = 4096;
    protected final ax b;
    protected final aq c;

    public ap(ax axVar) {
        this(axVar, new aq(e));
    }

    public ap(ax axVar, aq aqVar) {
        this.b = axVar;
        this.c = aqVar;
    }

    @Override // defpackage.ac
    public ae a(Request<?> request) {
        byte[] bArr;
        byte[] bArr2;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        while (true) {
            HttpResponse httpResponse = null;
            Map<String, String> emptyMap = Collections.emptyMap();
            try {
                try {
                    HashMap hashMap = new HashMap();
                    a(hashMap, request.e());
                    HttpResponse a2 = this.b.a(request, hashMap);
                    try {
                        StatusLine statusLine = a2.getStatusLine();
                        int statusCode = statusLine.getStatusCode();
                        emptyMap = a(a2.getAllHeaders());
                        if (statusCode == 304) {
                            v e2 = request.e();
                            if (e2 == null) {
                                return new ae(304, null, emptyMap, true, SystemClock.elapsedRealtime() - elapsedRealtime);
                            }
                            e2.f.putAll(emptyMap);
                            return new ae(304, e2.a, e2.f, true, SystemClock.elapsedRealtime() - elapsedRealtime);
                        }
                        if (a2.getEntity() != null) {
                            bArr2 = a(a2.getEntity());
                        } else {
                            bArr2 = new byte[0];
                        }
                        try {
                            a(SystemClock.elapsedRealtime() - elapsedRealtime, request, bArr2, statusLine);
                            if (statusCode < 200 || statusCode > 299) {
                                throw new IOException();
                            }
                            return new ae(statusCode, bArr2, emptyMap, false, SystemClock.elapsedRealtime() - elapsedRealtime);
                        } catch (IOException e3) {
                            e = e3;
                            bArr = bArr2;
                            httpResponse = a2;
                            if (httpResponse != null) {
                                int statusCode2 = httpResponse.getStatusLine().getStatusCode();
                                am.c("Unexpected response code %d for %s", Integer.valueOf(statusCode2), request.c());
                                if (bArr != null) {
                                    ae aeVar = new ae(statusCode2, bArr, emptyMap, false, SystemClock.elapsedRealtime() - elapsedRealtime);
                                    if (statusCode2 == 401 || statusCode2 == 403) {
                                        a("auth", request, new AuthFailureError(aeVar));
                                    } else {
                                        throw new ServerError(aeVar);
                                    }
                                } else {
                                    throw new NetworkError((ae) null);
                                }
                            } else {
                                throw new NoConnectionError(e);
                            }
                        }
                    } catch (IOException e4) {
                        e = e4;
                        bArr = null;
                        httpResponse = a2;
                    }
                } catch (IOException e5) {
                    e = e5;
                    bArr = null;
                }
            } catch (MalformedURLException e6) {
                throw new RuntimeException("Bad URL " + request.c(), e6);
            } catch (SocketTimeoutException e7) {
                a("socket", request, new TimeoutError());
            } catch (ConnectTimeoutException e8) {
                a(Headers.CONN_DIRECTIVE, request, new TimeoutError());
            }
        }
    }

    private void a(long j, Request<?> request, byte[] bArr, StatusLine statusLine) {
        if (a || j > d) {
            Object[] objArr = new Object[5];
            objArr[0] = request;
            objArr[1] = Long.valueOf(j);
            objArr[2] = bArr != null ? Integer.valueOf(bArr.length) : "null";
            objArr[3] = Integer.valueOf(statusLine.getStatusCode());
            objArr[4] = Integer.valueOf(request.t().b());
            am.b("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", objArr);
        }
    }

    private static void a(String str, Request<?> request, VolleyError volleyError) {
        al t = request.t();
        int s = request.s();
        try {
            t.a(volleyError);
            request.a(String.format("%s-retry [timeout=%s]", str, Integer.valueOf(s)));
        } catch (VolleyError e2) {
            request.a(String.format("%s-timeout-giveup [timeout=%s]", str, Integer.valueOf(s)));
            throw e2;
        }
    }

    private void a(Map<String, String> map, v vVar) {
        if (vVar != null) {
            if (vVar.b != null) {
                map.put("If-None-Match", vVar.b);
            }
            if (vVar.c > 0) {
                map.put("If-Modified-Since", DateUtils.formatDate(new Date(vVar.c)));
            }
        }
    }

    private byte[] a(HttpEntity httpEntity) {
        bn bnVar = new bn(this.c, (int) httpEntity.getContentLength());
        try {
            InputStream content = httpEntity.getContent();
            if (content == null) {
                throw new ServerError();
            }
            byte[] a2 = this.c.a(1024);
            while (true) {
                int read = content.read(a2);
                if (read == -1) {
                    break;
                }
                bnVar.write(a2, 0, read);
            }
            byte[] byteArray = bnVar.toByteArray();
            try {
                httpEntity.consumeContent();
            } catch (IOException e2) {
                am.a("Error occured when calling consumingContent", new Object[0]);
            }
            this.c.a(a2);
            bnVar.close();
            return byteArray;
        } catch (Throwable th) {
            try {
                httpEntity.consumeContent();
            } catch (IOException e3) {
                am.a("Error occured when calling consumingContent", new Object[0]);
            }
            this.c.a((byte[]) null);
            bnVar.close();
            throw th;
        }
    }

    protected static Map<String, String> a(Header[] headerArr) {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < headerArr.length; i++) {
            treeMap.put(headerArr[i].getName(), headerArr[i].getValue());
        }
        return treeMap;
    }
}
