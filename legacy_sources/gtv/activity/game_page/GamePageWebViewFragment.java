package com.thuuz.gtv.activity.game_page;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import com.actionbarsherlock.widget.ActivityChooserView;
// [removed legacy tracker import]
import com.thuuz.gtv.App;
import com.thuuz.gtv.activity.game_page.AbstractGamePageFragment;
import com.thuuz.gtv.util.ThuuzWebViewClient;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bgv;
import defpackage.bjk;
import defpackage.bki;
import defpackage.bkj;
import defpackage.bkk;
import defpackage.bkl;
import defpackage.bra;
import defpackage.can;
import java.util.logging.Level;
import org.apache.http.cookie.ClientCookie;

/* loaded from: classes.dex */
public class GamePageWebViewFragment extends AbstractGamePageFragment implements bra {
    private App app;
    private bkl fetcher;
    private View focus_rect_lay;
    private boolean isSecure;
    private LinearLayout loadingLayout;
    private int scrollY;
    private Button spoilersButton;
    private ThuuzWebViewClient thuuzWebViewClient;
    private String url;
    private WebView webView;

    public GamePageWebViewFragment() {
    }

    public GamePageWebViewFragment(int i, String str, int i2, App app) {
        this(i, str, i2, app, false);
    }

    public GamePageWebViewFragment(int i, String str, int i2, App app, boolean z) {
        super(i, i2);
        this.url = str;
        this.app = app;
        this.isSecure = z;
    }

    public GamePageWebViewFragment(int i, String str, int i2, App app, int i3) {
        this(i, str, i2, app);
        setDynResNum(i3);
    }

    @Override // com.thuuz.gtv.activity.game_page.AbstractGamePageFragment, android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(NativeProtocol.IMAGE_URL_KEY, this.url);
        bundle.putBoolean(ClientCookie.SECURE_ATTR, this.isSecure);
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate;
        ViewGroup.LayoutParams layoutParams;
        View view = null;
        if (viewGroup == null) {
            return null;
        }
        try {
            inflate = layoutInflater.inflate(bbr.game_page_webview_fragment, viewGroup, false);
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
                th = th2;
                view = inflate;
                getLogger().log(Level.SEVERE, "problem: ", th);
                return view;
            }
        }
        this.spoilersButton = (Button) inflate.findViewById(bbq.spoilers_button);
        this.loadingLayout = (LinearLayout) inflate.findViewById(bbq.webview_loading_layout);
        this.webView = (WebView) inflate.findViewById(bbq.webview);
        GamePageActivity fragsActivity = getFragsActivity();
        if (fragsActivity != null) {
            if (Build.VERSION.SDK_INT >= 11 && !fragsActivity.j().e()) {
                this.webView.setLayerType(1, null);
            }
            if (this.app == null) {
                this.app = fragsActivity.j();
            }
            inflate.setId(fragsActivity.A());
            fragsActivity.expandWidthByDevice(this.spoilersButton);
            this.spoilersButton.setVisibility(isDisplaySpoilersButton(fragsActivity) ? 0 : 8);
            this.spoilersButton.setOnClickListener(new bki(this));
            this.loadingLayout.setVisibility(8);
            this.webView.setBackgroundColor(0);
            this.thuuzWebViewClient = new bkk(this, fragsActivity);
            this.webView.setWebViewClient(this.thuuzWebViewClient);
            this.webView.setWebChromeClient(new can());
            if (fragsActivity.j().e()) {
                this.webView.setScrollBarStyle(33554432);
                this.webView.setNextFocusDownId(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
                setupFocusRect(inflate);
            }
            WebSettings settings = this.webView.getSettings();
            settings.setJavaScriptEnabled(true);
            boolean z = fragsActivity.z();
            settings.setDatabaseEnabled(z);
            if (!z) {
                settings.setCacheMode(2);
            }
            if (Build.VERSION.SDK_INT > 19) {
                settings.setMixedContentMode(0);
            }
            if (isEqGraph() && (layoutParams = this.webView.getLayoutParams()) != null) {
                layoutParams.height = -1;
            }
            String refreshState = getRefreshState();
            if (refreshState != null) {
                getLogger().info("filling webview in onCreateView from saved state");
                this.webView.loadDataWithBaseURL(getRefreshUrl(), refreshState, getMime(), getCharset(), getRefreshUrl());
            }
            view = inflate;
            return view;
        }
        return inflate;
    }

    private boolean isDisplaySpoilersButton(GamePageActivity gamePageActivity) {
        boolean z = !isEqGraph();
        if (z) {
            if (this.app != null) {
                z = !this.app.aK();
            }
            if (z && gamePageActivity != null) {
                return !gamePageActivity.aN();
            }
        }
        return z;
    }

    private boolean isEqGraph() {
        return AbstractGamePageFragment.FragType.GRAPH.c() == getFragTypeNum();
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        getLogger().info("refreshing from resume");
        doRefresh(false);
    }

    @Override // defpackage.bbf
    public void doRefresh(boolean z) {
        boolean z2 = false;
        try {
            GamePageActivity fragsActivity = getFragsActivity();
            if (isPrefetch() && getRefreshState() == null) {
                if (this.app != null) {
                    getLogger().info("prefetching...");
                } else {
                    return;
                }
            } else if (!isActive()) {
                return;
            }
            if (!isDisplaySpoilersButton(fragsActivity)) {
                this.spoilersButton.setVisibility(8);
                if (!this.thuuzWebViewClient.b() && getRefreshState() == null) {
                    z2 = true;
                }
                if (z2) {
                    this.loadingLayout.setVisibility(0);
                }
                String a = fragsActivity != null ? fragsActivity.a(getFragTypeNum(), (bjk) null) : null;
                if (z || (a != null && !a.equals(this.url))) {
                    this.url = a;
                    z2 = true;
                }
                if (z2) {
                    if (isEqGraph()) {
                        if (this.thuuzWebViewClient.i() != null && !this.thuuzWebViewClient.b()) {
                            getLogger().info("graph loading data from saved html");
                            this.webView.loadDataWithBaseURL(this.url, this.thuuzWebViewClient.i(), this.thuuzWebViewClient.l(), this.thuuzWebViewClient.k(), this.url);
                        }
                        showContent();
                        int measuredWidth = this.webView.getMeasuredWidth();
                        int measuredHeight = this.webView.getMeasuredHeight();
                        if (!this.webView.isShown() || measuredWidth == 0 || measuredHeight == 0) {
                            getLogger().info("skipping graph until non-zero size: " + measuredWidth + "/" + measuredHeight);
                            this.url = "";
                            return;
                        }
                        getLogger().info("rendering graph size: " + measuredWidth + "/" + measuredHeight);
                    }
                    if (this.fetcher != null) {
                        getLogger().info(getFragName() + " skipping fetch because ongoing");
                        return;
                    } else {
                        this.fetcher = startFetch(fragsActivity, z);
                        return;
                    }
                }
                if (fragsActivity != null && fragsActivity.aQ() != null && fragsActivity.aQ().al()) {
                    getLogger().info("refreshing existing because we are live");
                    this.fetcher = startFetch(fragsActivity, false);
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem refreshing: ", th);
        }
    }

    private bkl startFetch(GamePageActivity gamePageActivity, boolean z) {
        bkl bklVar;
        bki bkiVar = null;
        getLogger().info(getCategoryTypeText() + " loading url: " + this.url);
        if (this.isSecure) {
            bklVar = new bkl(this, z, this.url, gamePageActivity, this.webView, this.thuuzWebViewClient, true, bkiVar);
        } else {
            bklVar = new bkl(this, z, this.url, gamePageActivity, this.webView, this.thuuzWebViewClient, bkiVar);
        }
        bklVar.execute(new Void[0]);
        return bklVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getCategoryTypeText() {
        AbstractGamePageFragment.FragType a;
        return (getFragsActivity() == null || (a = AbstractGamePageFragment.FragType.a(getFragTypeNum())) == null) ? "" : a.d();
    }

    @Override // defpackage.bra
    public void reScroll() {
        GamePageActivity fragsActivity;
        if (this.scrollY != 0 && (fragsActivity = getFragsActivity()) != null) {
            fragsActivity.aa().postDelayed(new bgv(0, this.webView, fragsActivity, this.scrollY), 100L);
        }
    }

    public View getWebView() {
        return this.webView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showContent() {
        FragmentActivity activity;
        this.loadingLayout.setVisibility(8);
        this.webView.setVisibility(0);
        if (!this.webView.isShown() && (activity = getActivity()) != null && !activity.isFinishing()) {
            this.webView.setVisibility(0);
            this.webView.requestLayout();
        }
    }

    private void setupFocusRect(View view) {
        this.focus_rect_lay = view.findViewById(bbq.focus_rect_lay);
        this.focus_rect_lay.setPadding(3, 3, 3, 3);
        this.webView.setOnFocusChangeListener(new bkj(this));
    }

    @Override // defpackage.bbf
    public boolean hasFocus() {
        return this.webView.hasFocus();
    }

    @Override // defpackage.bbf
    public boolean requestFocus() {
        this.webView.requestFocus();
        return true;
    }
}
