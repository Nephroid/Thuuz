package com.thuuz.gtv.util;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.friends.WebviewActivity;
import com.thuuz.gtv.activity.game_page.GamePageActivity;
import com.thuuz.gtv.activity.nobar.MP4VideoActivity;
import com.thuuz.gtv.settings.SettingsTwitterOauthActivity;
import defpackage.bbt;
import defpackage.bra;
import defpackage.bya;
import defpackage.bzy;
import defpackage.cab;
import defpackage.cah;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Pattern;
import org.apache.http.HttpHost;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ThuuzWebViewClient extends WebViewClient {
    public static final Pattern b = Pattern.compile(".*espn.*\\.com", 2);
    private String a;
    private byte[] c;
    private cab d;
    private String e;
    private String f;
    private String g;
    private final WeakReference<AbstractActivity> h;
    private boolean i;
    private bra j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private WeakReference<WebView> o;
    private String p;

    /* loaded from: classes.dex */
    public enum CONN_RETRY_STATE {
        DO_NOTHING,
        RETRY,
        ERROR
    }

    public ThuuzWebViewClient(AbstractActivity abstractActivity) {
        this.h = new WeakReference<>(abstractActivity);
    }

    public ThuuzWebViewClient(AbstractActivity abstractActivity, bra braVar) {
        this(abstractActivity);
        a(braVar);
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        this.o = new WeakReference<>(webView);
        AbstractActivity abstractActivity = this.h.get();
        if (abstractActivity != null) {
            abstractActivity.e().info("onPageStarted: " + str);
            if (!this.n) {
                this.n = true;
                abstractActivity.e(true);
            }
        }
        this.i = false;
        this.l = false;
        this.m = false;
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        AbstractActivity abstractActivity = this.h.get();
        if (abstractActivity != null) {
            abstractActivity.e().info("finished: " + str);
            abstractActivity.e(false);
        }
        if (this.n) {
            this.n = false;
        }
        this.k = true;
        if (this.j != null) {
            try {
                this.j.reScroll();
            } catch (Throwable th) {
                bzy.getStaticLogger(ThuuzWebViewClient.class).log(Level.SEVERE, "Cannot handle rescroll listener: ", th);
            }
        }
        if ("data:text/html,<html><head><style>body { background-color: #000000; color: #f1f1f1; } </style></head><body><h2>Download failed; please try again later</h2></body></html>".equals(str)) {
            this.i = true;
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        this.i = true;
        this.k = false;
        bzy.getStaticLogger(ThuuzWebViewClient.class).warning("webview onReceivedError: " + i + "; " + str);
        AbstractActivity abstractActivity = this.h.get();
        if (abstractActivity != null) {
            if (!bya.a(abstractActivity.j())) {
                if (this.d == null) {
                    this.d = new cab(false, abstractActivity, this);
                    this.d.execute(new Void[0]);
                    return;
                } else {
                    abstractActivity.e().info("skipping conn retry since already started");
                    return;
                }
            }
            if (abstractActivity.d()) {
                abstractActivity.a("Download Failed", bbt.pls_try_again_later);
            }
            abstractActivity.e().info("loading 404 text");
            webView.loadDataWithBaseURL(null, "<html><head><style>body { background-color: #000000; color: #f1f1f1; } </style></head><body><h2>Download failed; please try again later</h2></body></html>", "text/html", "utf-8", null);
            this.i = true;
        }
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        WebView webView2;
        String str2;
        int i = 0;
        AbstractActivity abstractActivity = this.h.get();
        if (abstractActivity == null) {
            return true;
        }
        if (str == null) {
            abstractActivity.e().warning("null url");
            return true;
        }
        try {
            Uri parse = Uri.parse(str);
            String scheme = parse.getScheme();
            if (scheme == null || (!scheme.equalsIgnoreCase("thuuz") && !scheme.equalsIgnoreCase("tt"))) {
                if ("mailto".equalsIgnoreCase(scheme)) {
                    try {
                        if (abstractActivity.j().a(parse)) {
                            abstractActivity.startActivity(new Intent("android.intent.action.VIEW", parse));
                        } else {
                            abstractActivity.c("Cannot find app for email", "Please install an email app");
                        }
                    } catch (Throwable th) {
                        abstractActivity.e().log(Level.SEVERE, "problem launching mailto url: ", th);
                    }
                    return true;
                }
                if (abstractActivity.getIntent() != null && abstractActivity.getIntent().getBooleanExtra("use_httpclient", false)) {
                    if (abstractActivity instanceof WebviewActivity) {
                        WebviewActivity webviewActivity = (WebviewActivity) abstractActivity;
                        webviewActivity.g(str);
                        webviewActivity.c();
                        return true;
                    }
                    if (this.o != null && (webView2 = this.o.get()) != null) {
                        new cah(str, abstractActivity, webView2, this).execute(new Void[0]);
                        return true;
                    }
                }
                return false;
            }
            String host = parse.getHost();
            if (host == null) {
                abstractActivity.e().severe("null host in Thuuz schema: " + str);
                return true;
            }
            try {
                if (host.equalsIgnoreCase("game")) {
                    List<String> pathSegments = parse.getPathSegments();
                    if (pathSegments != null && !pathSegments.isEmpty()) {
                        int intValue = Integer.valueOf(pathSegments.get(0)).intValue();
                        String queryParameter = parse.getQueryParameter("tab");
                        if (queryParameter != null) {
                            abstractActivity.a(Integer.valueOf(intValue), queryParameter);
                        } else {
                            abstractActivity.a(Integer.valueOf(intValue));
                        }
                        abstractActivity.j().a(n(), "click", "game", intValue);
                    }
                } else if (host.equalsIgnoreCase("profile")) {
                    List<String> pathSegments2 = parse.getPathSegments();
                    if (pathSegments2 != null && !pathSegments2.isEmpty()) {
                        int intValue2 = Integer.valueOf(pathSegments2.get(0)).intValue();
                        abstractActivity.d(intValue2);
                        abstractActivity.j().a(n(), "click", "profile", intValue2);
                    } else {
                        abstractActivity.e().severe("empty segments in profile url: " + str);
                    }
                } else if (host.equalsIgnoreCase("pickFavorites")) {
                    abstractActivity.i();
                    abstractActivity.j().a(n(), "click", "pickFavorites", 0L);
                } else if (host.equalsIgnoreCase("log")) {
                    o();
                    List<String> pathSegments3 = parse.getPathSegments();
                    if (pathSegments3 != null && !pathSegments3.isEmpty()) {
                        String str3 = pathSegments3.get(0);
                        if (!str3.contains("ThuuzFeed is not defined")) {
                            abstractActivity.e().info(str3);
                        }
                    }
                } else if (host.equalsIgnoreCase("newItemsCount")) {
                    o();
                    List<String> pathSegments4 = parse.getPathSegments();
                    if (pathSegments4 != null && !pathSegments4.isEmpty()) {
                        abstractActivity.a(this.p, "newItemsCount", String.valueOf(Integer.valueOf(pathSegments4.get(0)).intValue()));
                    }
                } else if (host.equalsIgnoreCase("request_failure")) {
                    o();
                    abstractActivity.e().info("got ajax failure");
                    if (this.l) {
                        b(false);
                        abstractActivity.e(false);
                        if (!bya.a(abstractActivity.j())) {
                            if (this.d != null) {
                                this.d = new cab(true, abstractActivity, this);
                                this.d.execute(new Void[0]);
                            } else {
                                abstractActivity.e().info("skipping reconn attempt while one ongoing");
                            }
                        } else {
                            abstractActivity.a(this.p, "request_failure", "");
                        }
                    }
                } else if (host.equalsIgnoreCase("request_success")) {
                    o();
                    abstractActivity.e().info("got ajax success");
                    if (this.l) {
                        b(false);
                        abstractActivity.e(false);
                    }
                    abstractActivity.a(this.p, "request_success", "");
                } else if (host.equalsIgnoreCase("closeme")) {
                    abstractActivity.finish();
                } else if (host.equalsIgnoreCase("player")) {
                    List<String> pathSegments5 = parse.getPathSegments();
                    if (pathSegments5 != null && !pathSegments5.isEmpty()) {
                        int intValue3 = Integer.valueOf(pathSegments5.get(0)).intValue();
                        String str4 = null;
                        String query = parse.getQuery();
                        if (query != null) {
                            String[] split = query.split("&");
                            if (split.length > 0) {
                                JSONObject jSONObject = new JSONObject();
                                int length = split.length;
                                while (i < length) {
                                    String[] split2 = split[i].split("=");
                                    if (split2.length >= 2) {
                                        jSONObject.put(split2[0], split2[1]);
                                    }
                                    i++;
                                }
                                str4 = jSONObject.toString();
                            }
                        }
                        abstractActivity.b(intValue3, str4);
                        abstractActivity.j().a(n(), "click", "player", intValue3);
                    } else {
                        abstractActivity.e().severe("empty segments in player url: " + str);
                    }
                } else if (host.equalsIgnoreCase("fantasy_team")) {
                    List<String> pathSegments6 = parse.getPathSegments();
                    if (pathSegments6 != null && !pathSegments6.isEmpty()) {
                        int intValue4 = Integer.valueOf(pathSegments6.get(0)).intValue();
                        abstractActivity.b(intValue4);
                        abstractActivity.j().a(n(), "click", "fantasy_team", intValue4);
                    } else {
                        abstractActivity.e().severe("empty segments in fantasy team url: " + str);
                    }
                } else if (host.equalsIgnoreCase("fantasy_import")) {
                    abstractActivity.g();
                } else if (host.equalsIgnoreCase("fantasy_teams") || host.equals("fantasy")) {
                    abstractActivity.f();
                } else if (host.equalsIgnoreCase("fantasy_talk")) {
                    List<String> pathSegments7 = parse.getPathSegments();
                    if (pathSegments7 != null && !pathSegments7.isEmpty()) {
                        int intValue5 = Integer.valueOf(pathSegments7.get(0)).intValue();
                        abstractActivity.c(intValue5);
                        abstractActivity.j().a(n(), "click", "smack_talk", intValue5);
                    } else {
                        abstractActivity.e().severe("empty segments in fantasy talk url: " + str);
                    }
                } else if (!host.equalsIgnoreCase("page_params")) {
                    if (host.equalsIgnoreCase("webview")) {
                        String queryParameter2 = parse.getQueryParameter(NativeProtocol.IMAGE_URL_KEY);
                        if (queryParameter2 != null) {
                            try {
                                String queryParameter3 = parse.getQueryParameter("title");
                                if (queryParameter2.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
                                    str2 = queryParameter2;
                                } else {
                                    str2 = ServerChoice.q + ((!queryParameter2.startsWith("/") || queryParameter2.length() <= 1) ? queryParameter2 : queryParameter2.substring(1));
                                }
                                String queryParameter4 = parse.getQueryParameter("external");
                                if (queryParameter4 == null || !ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equalsIgnoreCase(queryParameter4)) {
                                    abstractActivity.b(str2, queryParameter3);
                                } else {
                                    abstractActivity.e(str2, queryParameter3);
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                str = queryParameter2;
                                abstractActivity.e().log(Level.SEVERE, "problem handling URL: " + str, th);
                                return true;
                            }
                        } else {
                            abstractActivity.e().severe("empty url in webview url: " + str);
                        }
                    } else if (host.equalsIgnoreCase("doc_ready")) {
                        abstractActivity.e().info("js is loaded");
                        o();
                    } else if (host.equalsIgnoreCase("twitter_oauth")) {
                        SettingsTwitterOauthActivity.a(parse, abstractActivity);
                    } else if (host.equalsIgnoreCase("media")) {
                        String queryParameter5 = parse.getQueryParameter(NativeProtocol.IMAGE_URL_KEY);
                        if (queryParameter5 != null) {
                            String queryParameter6 = parse.getQueryParameter("external");
                            if (queryParameter6 != null && queryParameter6.equalsIgnoreCase(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
                                i = 1;
                            }
                            Uri parse2 = Uri.parse(queryParameter5);
                            String host2 = parse2.getHost();
                            if (abstractActivity.j().ak() && host2 != null && b.matcher(host2).matches()) {
                                abstractActivity.c("No viewer available", "");
                                return true;
                            }
                            if (abstractActivity.j().A(parse2.getPath()) && i == 0) {
                                abstractActivity.e().info("launching internal viewer for: " + str);
                                Intent intent = new Intent(abstractActivity, (Class<?>) MP4VideoActivity.class);
                                intent.putExtra(NativeProtocol.IMAGE_URL_KEY, queryParameter5);
                                abstractActivity.startActivity(intent);
                            } else {
                                abstractActivity.e(queryParameter5, "");
                            }
                            abstractActivity.j().a(n(), "click", "highlight", 0L);
                        } else {
                            abstractActivity.e().severe("empty url in media url: " + str);
                        }
                    } else if (host.equalsIgnoreCase("fantasy_reconnect")) {
                        abstractActivity.a(Integer.valueOf(parse.getQueryParameter("comp_id")).intValue(), Integer.valueOf(parse.getQueryParameter("site_id")).intValue());
                    } else if (host.equalsIgnoreCase("fantasy_settings")) {
                        abstractActivity.C();
                    } else {
                        abstractActivity.e().info("ignoring unexpected thuuz url: " + host);
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
            return true;
        } catch (Throwable th4) {
            abstractActivity.e().log(Level.SEVERE, "problem: ", th4);
            return true;
        }
    }

    private String n() {
        return p() ? "gameFeed" : "feed";
    }

    private void o() {
        this.m = true;
    }

    public void a(bra braVar) {
        this.j = braVar;
    }

    public boolean a() {
        return this.k && this.m;
    }

    public void a(boolean z) {
        this.k = z;
    }

    public boolean b() {
        return this.k && !this.i;
    }

    public boolean c() {
        return this.l;
    }

    public void b(boolean z) {
        this.l = z;
    }

    public boolean d() {
        return this.i;
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        this.i = true;
        bzy.getStaticLogger(ThuuzWebViewClient.class).warning("SSL error: " + sslError);
        if (sslErrorHandler != null) {
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
        }
    }

    public void a(String str) {
        this.p = str;
    }

    private boolean p() {
        AbstractActivity abstractActivity = this.h.get();
        return abstractActivity != null && abstractActivity.getClass().equals(GamePageActivity.class);
    }

    public void e() {
        this.d = null;
    }

    public void b(String str) {
        this.a = str;
    }

    public void a(byte[] bArr) {
        this.c = bArr;
    }

    public byte[] f() {
        return this.c;
    }

    public boolean g() {
        return this.n;
    }

    public void h() {
        AbstractActivity abstractActivity;
        if (!this.n && (abstractActivity = this.h.get()) != null) {
            abstractActivity.e(true);
            this.n = true;
        }
    }

    public String i() {
        return this.e;
    }

    public String j() {
        return this.a;
    }

    public void a(String str, String str2, String str3) {
        this.e = str;
        this.g = str2;
        this.f = str3;
    }

    public String k() {
        return this.f;
    }

    public String l() {
        return this.g;
    }

    public WeakReference<WebView> m() {
        return this.o;
    }
}
