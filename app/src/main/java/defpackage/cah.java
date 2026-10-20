package defpackage;

import android.os.AsyncTask;
import android.webkit.WebView;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.util.ThuuzWebViewClient;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/* loaded from: classes.dex */
public class cah extends AsyncTask<Void, Void, String> {
    private final boolean a;
    private String b;
    private WeakReference<AbstractActivity> c;
    private WebView d;
    private ThuuzWebViewClient e;
    private String f;
    private String g;
    private int h;
    private String i;

    public cah(String str, AbstractActivity abstractActivity, WebView webView, ThuuzWebViewClient thuuzWebViewClient, boolean z) {
        this.h = -1;
        this.b = str;
        this.c = new WeakReference<>(abstractActivity);
        this.d = webView;
        this.e = thuuzWebViewClient;
        this.a = z;
    }

    public cah(String str, AbstractActivity abstractActivity, WebView webView, ThuuzWebViewClient thuuzWebViewClient) {
        this(str, abstractActivity, webView, thuuzWebViewClient, false);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        AbstractActivity a = a();
        if (a != null) {
            a.e(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String doInBackground(Void... voidArr) {
        AbstractActivity abstractActivity;
        HttpResponse execute;
        String str = null;
        try {
            abstractActivity = a();
            if (abstractActivity != null) {
                try {
                    if (this.a) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(new BasicNameValuePair("auth_code", "ec30a1d911e08264"));
                        arrayList.add(new BasicNameValuePair("user_cookie", abstractActivity.j().bj()));
                        execute = a(abstractActivity, arrayList);
                        a(execute);
                        if (this.h != 200) {
                            abstractActivity.e().warning("failed " + this.b + ": " + this.h + ": " + this.i);
                        }
                        str = EntityUtils.toString(execute.getEntity(), this.g);
                    } else {
                        execute = abstractActivity.j().aS().execute(new HttpGet(this.b));
                        a(execute);
                        if (this.h != 200) {
                            abstractActivity.e().warning("failed " + this.b + ": " + this.h + ": " + this.i);
                        }
                        str = EntityUtils.toString(execute.getEntity(), this.g);
                    }
                } catch (Throwable th) {
                    th = th;
                    if (abstractActivity != null) {
                        abstractActivity.e().log(Level.WARNING, "problem fetching: " + th);
                    }
                    return str;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            abstractActivity = null;
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(String str) {
        AbstractActivity a = a();
        try {
            try {
                if (str == null) {
                    this.e.onReceivedError(this.d, d(), f(), b());
                } else {
                    this.d.loadDataWithBaseURL(b(), str, e(), c(), b());
                    this.e.a(str, e(), c());
                }
                if (a != null) {
                    a.e(false);
                }
            } catch (Throwable th) {
                if (a != null) {
                    a.e().log(Level.SEVERE, "problem: ", th);
                }
                if (a != null) {
                    a.e(false);
                }
            }
        } catch (Throwable th2) {
            if (a != null) {
                a.e(false);
            }
            throw th2;
        }
    }

    public AbstractActivity a() {
        return this.c.get();
    }

    public String b() {
        return this.b;
    }

    private HttpResponse a(AbstractActivity abstractActivity, List<? extends NameValuePair> list) {
        DefaultHttpClient aS = abstractActivity.j().aS();
        HttpPost httpPost = new HttpPost(this.b);
        httpPost.setEntity(new UrlEncodedFormEntity(list, HTTP.UTF_8));
        HttpResponse execute = aS.execute(httpPost);
        a(execute);
        return execute;
    }

    private void a(HttpResponse httpResponse) {
        NameValuePair parameterByName;
        try {
            if (httpResponse == null) {
                if (this.g == null) {
                    this.g = HTTP.UTF_8;
                }
                if (this.f == null) {
                    this.f = "text/html";
                }
                if (this.h == -1) {
                    this.h = 404;
                }
                if (this.i == null) {
                    this.i = "not found";
                    return;
                }
                return;
            }
            try {
                StatusLine statusLine = httpResponse.getStatusLine();
                if (statusLine != null) {
                    this.h = statusLine.getStatusCode();
                    this.i = statusLine.getReasonPhrase();
                }
                if (httpResponse.getEntity() != null && httpResponse.getEntity().getContentType() != null) {
                    Header contentType = httpResponse.getEntity().getContentType();
                    String str = (String) httpResponse.getParams().getParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET);
                    if (str != null) {
                        this.g = str;
                    } else {
                        HeaderElement[] elements = contentType.getElements();
                        if (elements.length > 0 && (parameterByName = elements[0].getParameterByName("charset")) != null) {
                            this.g = parameterByName.getValue();
                        }
                    }
                    if (contentType.getValue() != null) {
                        this.f = contentType.getValue();
                        if (contentType.getValue().contains(";")) {
                            String[] split = contentType.getValue().split(";");
                            if (split.length > 0) {
                                this.f = split[0].trim();
                            }
                        }
                    }
                }
                if (this.g == null) {
                    this.g = HTTP.UTF_8;
                }
                if (this.f == null) {
                    this.f = "text/html";
                }
                if (this.h == -1) {
                    this.h = 404;
                }
                if (this.i == null) {
                    this.i = "not found";
                }
            } catch (Throwable th) {
                AbstractActivity a = a();
                if (a != null) {
                    a.e().log(Level.SEVERE, "Cannot handle http parsing: ", th);
                }
                if (this.g == null) {
                    this.g = HTTP.UTF_8;
                }
                if (this.f == null) {
                    this.f = "text/html";
                }
                if (this.h == -1) {
                    this.h = 404;
                }
                if (this.i == null) {
                    this.i = "not found";
                }
            }
        } catch (Throwable th2) {
            if (this.g == null) {
                this.g = HTTP.UTF_8;
            }
            if (this.f == null) {
                this.f = "text/html";
            }
            if (this.h == -1) {
                this.h = 404;
            }
            if (this.i == null) {
                this.i = "not found";
            }
            throw th2;
        }
    }

    public String c() {
        return this.g;
    }

    public int d() {
        return this.h;
    }

    public String e() {
        return this.f;
    }

    public String f() {
        return this.i;
    }

    public WebView g() {
        return this.d;
    }
}
