package com.thuuz.gtv.settings;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import com.facebook.internal.NativeProtocol;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.util.ThuuzWebViewClient;
import defpackage.bbf;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bkq;
import defpackage.bwh;
import defpackage.bwi;
import defpackage.bwj;
import defpackage.can;
import java.util.logging.Level;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.util.EncodingUtils;

/* loaded from: classes.dex */
public class WebViewFragment extends bbf implements bkq {
    private can client;
    private String failureText;
    private View focus_rect_lay;
    private boolean isSecure;
    private String loadedUrl;
    private LinearLayout loadingLayout;
    private int nextFocusRight;
    private String url;
    private WebView webView;
    private ThuuzWebViewClient webViewClient;

    public WebViewFragment() {
        this.loadedUrl = "";
    }

    public WebViewFragment(String str) {
        this(str, false);
    }

    public WebViewFragment(String str, boolean z) {
        this.loadedUrl = "";
        this.isSecure = z;
        this.url = str;
    }

    @Override // android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(NativeProtocol.IMAGE_URL_KEY, this.url);
        bundle.putBoolean(ClientCookie.SECURE_ATTR, this.isSecure);
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate;
        View view = null;
        try {
            inflate = layoutInflater.inflate(getLayoutId(), viewGroup, false);
        } catch (Throwable th) {
            th = th;
        }
        if (inflate == null) {
            return null;
        }
        if (bundle != null) {
            try {
                this.url = bundle.getString(NativeProtocol.IMAGE_URL_KEY);
                this.isSecure = bundle.getBoolean(ClientCookie.SECURE_ATTR);
            } catch (Throwable th2) {
                view = inflate;
                th = th2;
                getLogger().log(Level.SEVERE, "problem: ", th);
                return view;
            }
        }
        AbstractActivity fragsActivity = getFragsActivity();
        if (fragsActivity != null) {
            inflate.setId(fragsActivity.A());
            this.loadingLayout = (LinearLayout) inflate.findViewById(bbq.webview_loading_layout);
            this.loadingLayout.setVisibility(8);
            this.webView = (WebView) inflate.findViewById(bbq.webview);
            this.webView.setId(fragsActivity.A());
            if (fragsActivity.j().e()) {
                this.webView.setOnFocusChangeListener(new bwh(this));
                this.webView.setFocusable(true);
                if (this.nextFocusRight != 0) {
                    this.webView.setNextFocusRightId(this.nextFocusRight);
                }
                this.webView.setClickable(false);
                setupFocusRect(inflate);
            } else {
                this.webView.setFocusable(false);
            }
            this.webView.setBackgroundColor(0);
            WebSettings settings = this.webView.getSettings();
            if (settings != null) {
                settings.setJavaScriptEnabled(true);
                boolean z = fragsActivity.z();
                settings.setDatabaseEnabled(z);
                if (!z) {
                    settings.setCacheMode(2);
                }
                if (Build.VERSION.SDK_INT > 19) {
                    settings.setMixedContentMode(0);
                }
            }
            this.webViewClient = new bwj(this, fragsActivity);
            this.webView.setWebViewClient(this.webViewClient);
            this.client = new can();
            this.webView.setWebChromeClient(this.client);
            view = inflate;
            return view;
        }
        return inflate;
    }

    private void setupFocusRect(View view) {
        this.focus_rect_lay = view.findViewById(bbq.focus_rect_lay);
        this.focus_rect_lay.setPadding(3, 3, 3, 3);
        this.webView.setOnFocusChangeListener(new bwi(this));
    }

    protected int getLayoutId() {
        return bbr.webview_fragment;
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        getLogger().info("refreshing from resume");
        doRefresh(false);
    }

    @Override // defpackage.bbf
    public void doRefresh(boolean z) {
        AbstractActivity fragsActivity = getFragsActivity();
        if (fragsActivity != null && isActive()) {
            try {
                if (this.client.a() && this.loadedUrl.equals(this.url)) {
                    getLogger().info("skipping web refresh while one is ongoing...");
                    return;
                }
                boolean z2 = this.webViewClient.b() ? false : true;
                if (z2) {
                    this.loadingLayout.setVisibility(0);
                }
                if (z || z2) {
                    getLogger().info("web frag loading url: " + this.url);
                    this.client.a(true);
                    this.loadedUrl = this.url;
                    if (this.isSecure) {
                        this.webView.postUrl(this.url, EncodingUtils.getBytes("user_cookie=" + fragsActivity.j().bj(), "BASE64"));
                    } else {
                        this.webView.loadUrl(this.url);
                    }
                }
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "problem refreshing: ", th);
            }
        }
    }

    public void setNextFocusRight(int i) {
        this.nextFocusRight = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showContent() {
        this.loadingLayout.setVisibility(8);
        this.webView.setVisibility(0);
        this.webView.requestLayout();
        if (!this.webView.isShown()) {
            getLogger().warning("web view not shown; trying again");
            this.webView.setVisibility(0);
            this.webView.requestLayout();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bbf
    public boolean isTabbedView() {
        return false;
    }

    public void setFailureText(String str) {
        this.failureText = str;
    }

    @Override // defpackage.bkq
    public void setNextFocusDownId(int i) {
        if (this.webView == null) {
            getLogger().severe("cannot set focus id because of null view");
        } else {
            this.webView.setNextFocusDownId(i);
        }
    }

    @Override // defpackage.bkq
    public void setNextFocusUpId(int i) {
        if (this.webView == null) {
            getLogger().severe("cannot set focus id because of null view");
        } else {
            this.webView.setNextFocusUpId(i);
        }
    }

    @Override // defpackage.bkq
    public int getFirstViewId() {
        if (this.webView != null) {
            return this.webView.getId();
        }
        getLogger().severe("cannot set focus id because of null view");
        return -1;
    }

    @Override // defpackage.bkq
    public int getLastViewId() {
        if (this.webView != null) {
            return this.webView.getId();
        }
        getLogger().severe("cannot set focus id because of null view");
        return -1;
    }

    @Override // defpackage.bkq
    public boolean isRendered() {
        return this.webView.isShown();
    }
}
