package com.thuuz.gtv.activity.feed;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.FeedActivity;
import com.thuuz.gtv.activity.game_page.AbstractGamePageFragment;
import com.thuuz.gtv.util.ThuuzWebViewClient;
import defpackage.bbf;
import defpackage.bbn;
import defpackage.bbq;
import defpackage.bgq;
import defpackage.bgv;
import defpackage.bjl;
import defpackage.bph;
import defpackage.bra;
import defpackage.bzy;
import java.util.Set;
import java.util.logging.Level;
import org.apache.http.util.EncodingUtils;

/* loaded from: classes.dex */
public abstract class AbstractFeedFragment extends bbf implements bjl, bra {
    protected ThuuzWebViewClient client;
    private View comment_lay;
    protected FeedActivity.FRAGS frag;
    private boolean isPrefetch;
    private String mCookie;
    private Set<bph> mFantasy;
    private Set<Integer> mLeagueGroups;
    private Set<Integer> mSports;
    private Set<Integer> mTeams;
    private String mZipcode;
    private int scrollY;
    private Button spoilersButton;
    private WebView webView = null;

    public abstract String getUrl();

    protected abstract boolean isShowSpoilersOverride(AbstractActivity abstractActivity);

    public abstract void setShowSpoilersOk();

    @Override // android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.webView != null) {
            bundle.putInt("webViewId", this.webView.getId());
        }
        if (this.frag != null) {
            bundle.putInt("frag", this.frag.a());
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        try {
            super.onActivityCreated(bundle);
            if (this.frag == null && bundle != null) {
                this.frag = FeedActivity.FRAGS.a(bundle.getInt("frag"));
            }
            AbstractActivity fragsActivity = getFragsActivity();
            if (fragsActivity != null) {
                showHideCommentBox(fragsActivity);
            }
            if (this.webView == null && fragsActivity != null && bundle != null) {
                int i = bundle.getInt("webViewId");
                this.webView = (WebView) fragsActivity.findViewById(i);
                if (this.webView == null) {
                    getLogger().warning("cannot get webview for id: " + i);
                }
            }
            if (this.spoilersButton == null && fragsActivity != null) {
                this.spoilersButton = (Button) fragsActivity.findViewById(bbq.spoilers_button);
                if (this.spoilersButton != null) {
                    fragsActivity.expandWidthByDevice(this.spoilersButton);
                    this.spoilersButton.setVisibility(isDisplaySpoilersButton(fragsActivity) ? 0 : 8);
                    this.spoilersButton.setOnClickListener(new bgq(this));
                }
            }
            if (fragsActivity != null) {
                if (this.mSports == null) {
                    this.mSports = fragsActivity.j().o();
                }
                if (this.mTeams == null) {
                    this.mTeams = fragsActivity.j().x();
                }
                if (this.mLeagueGroups == null) {
                    this.mLeagueGroups = fragsActivity.j().r();
                }
                if (this.mFantasy == null) {
                    this.mFantasy = fragsActivity.j().t();
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    public void showHideCommentBox(AbstractActivity abstractActivity) {
        if (this.comment_lay != null && abstractActivity != null) {
            this.comment_lay.setVisibility(8);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() {
        stopFeedCountPolling();
        super.onPause();
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        stopFeedCountPolling();
        super.onDestroy();
    }

    @Override // android.support.v4.app.Fragment
    public void onDetach() {
        stopFeedCountPolling();
        stopWebview();
        super.onDetach();
    }

    public void stopFeedCountPolling() {
        try {
            if (this.webView != null) {
                String str = "";
                if (this.frag != null) {
                    str = this.frag.name();
                }
                getLogger().info("feed endTimer for frag: " + str);
                this.webView.loadUrl("javascript:ThuuzFeed.endTimer()");
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        AbstractActivity fragsActivity = getFragsActivity();
        if (fragsActivity != null) {
            if (!testActive(fragsActivity)) {
                getLogger().info("skipping feed refresh since activity isn't active");
                return;
            }
            if (!hasRefreshedOnce()) {
                getLogger().info("resuming feed after testing true for first time");
                doRefresh(false);
            } else {
                getLogger().info("ignoring feed refresh while initial fetch ongoing");
            }
            if (this.client != null && this.client.a()) {
                startFeedCountPolling();
            }
        }
    }

    private void startFeedCountPolling() {
        try {
            if (this.webView != null) {
                if (this.client.c()) {
                    getLogger().info("ignoring feed count start--refresh underway");
                    return;
                }
                String str = "";
                if (this.frag != null) {
                    str = this.frag.name();
                }
                getLogger().info("start feed count for: " + str);
                this.webView.loadUrl("javascript:ThuuzFeed.startTimer()");
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // defpackage.bbf
    public void doRefresh(boolean z) {
        try {
            AbstractActivity fragsActivity = getFragsActivity();
            if (fragsActivity != null) {
                if (!testActive(fragsActivity)) {
                    getLogger().info("skipping feed refresh for non-active fragment");
                    return;
                }
                if (this.spoilersButton != null) {
                    if (!isDisplaySpoilersButton(fragsActivity)) {
                        this.spoilersButton.setVisibility(8);
                    } else {
                        return;
                    }
                }
                if (this.webView != null) {
                    this.scrollY = this.webView.getScrollY();
                }
                checkEnvChanges();
                if (hasRefreshedOnce() && !z) {
                    if (this.client != null && this.client.c()) {
                        getLogger().info("skipping feed incr update since already in progress");
                        return;
                    } else {
                        doIncrementalJsRefresh(fragsActivity);
                        return;
                    }
                }
                if (!z && downloadInProgress()) {
                    getLogger().info("skipping feed load since download in progress");
                    return;
                }
                String url = getUrl();
                getLogger().info("feed loading: " + url);
                byte[] bytes = EncodingUtils.getBytes("user_cookie=" + fragsActivity.j().bj(), "BASE64");
                if (this.webView != null) {
                    if (this.client != null) {
                        this.client.b(url);
                        this.client.a(bytes);
                        if (!this.client.g()) {
                            getLogger().info("calling client.showSpinner in feed before postUrl as startPage replacement");
                            this.client.h();
                        }
                    }
                    this.webView.postUrl(url, bytes);
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    protected boolean testActive(AbstractActivity abstractActivity) {
        return abstractActivity.d();
    }

    private boolean downloadInProgress() {
        if (this.client != null) {
            return this.client.g();
        }
        return false;
    }

    private void doIncrementalJsRefresh(AbstractActivity abstractActivity) {
        boolean z = false;
        if (this.client != null ? this.client.c() : false) {
            getLogger().info("skipping feed js refresh since one is ongoing");
            return;
        }
        String jsRefreshUrl = getJsRefreshUrl();
        if (this.webView != null) {
            if (this.client != null) {
                this.client.b(true);
                z = true;
            }
            if (z) {
                abstractActivity.e(true);
            }
            getLogger().info("calling js: " + jsRefreshUrl);
            this.webView.loadUrl(jsRefreshUrl);
        }
    }

    private void checkEnvChanges() {
        try {
            AbstractActivity fragsActivity = getFragsActivity();
            if (fragsActivity != null && hasRefreshedOnce()) {
                if (this.mCookie == null) {
                    this.mCookie = fragsActivity.j().bj();
                } else if (!this.mCookie.equals(fragsActivity.j().bj())) {
                    hasRefreshedOnce(false);
                    this.mCookie = fragsActivity.j().bj();
                    return;
                }
                if (this.client != null && this.client.d()) {
                    hasRefreshedOnce(false);
                    return;
                }
                if (!this.mSports.equals(fragsActivity.j().o()) || !this.mTeams.equals(fragsActivity.j().x()) || !this.mLeagueGroups.equals(fragsActivity.j().r()) || !this.mFantasy.equals(fragsActivity.j().t())) {
                    this.mSports = fragsActivity.j().o();
                    this.mTeams = fragsActivity.j().x();
                    this.mLeagueGroups = fragsActivity.j().r();
                    this.mFantasy = fragsActivity.j().t();
                    hasRefreshedOnce(false);
                }
                if (this.mZipcode == null) {
                    this.mZipcode = fragsActivity.j().bp();
                } else if (!this.mZipcode.equals(fragsActivity.j().bp())) {
                    hasRefreshedOnce(false);
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getJsRefreshUrl() {
        return "javascript:ThuuzFeed.updateWithSinceRequest()";
    }

    public void setWebView(WebView webView, ThuuzWebViewClient thuuzWebViewClient) {
        this.client = thuuzWebViewClient;
        this.webView = webView;
        if (this.frag != null && thuuzWebViewClient != null) {
            thuuzWebViewClient.a(String.valueOf(this.frag.a()));
        }
    }

    @Override // defpackage.bra
    public void reScroll() {
        AbstractActivity fragsActivity;
        if (this.scrollY != 0 && (fragsActivity = getFragsActivity()) != null) {
            fragsActivity.aa().postDelayed(new bgv(0, this.webView, fragsActivity, this.scrollY), 100L);
        }
    }

    public static void adjustCommentPrompt(View view) {
        View findViewById;
        LinearLayout.LayoutParams layoutParams;
        if (view != null) {
            try {
                if (Build.VERSION.SDK_INT <= 10 && (findViewById = view.findViewById(bbq.white_background)) != null && (layoutParams = (LinearLayout.LayoutParams) findViewById.getLayoutParams()) != null) {
                    layoutParams.setMargins(layoutParams.leftMargin / 2, layoutParams.topMargin / 2, layoutParams.rightMargin / 2, layoutParams.bottomMargin / 2);
                    Resources resources = view.getResources();
                    if (resources != null) {
                        findViewById.setBackgroundColor(resources.getColor(bbn.dark_gray));
                    }
                }
            } catch (Throwable th) {
                bzy.getStaticLogger(AbstractFeedFragment.class).log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    public void setFrag(FeedActivity.FRAGS frags) {
        this.frag = frags;
        if (this.client != null) {
            this.client.a(String.valueOf(frags.a()));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setCommentLay(View view) {
        this.comment_lay = view;
    }

    public boolean hasRefreshedOnce() {
        if (this.client != null) {
            return this.client.a();
        }
        getLogger().warning("client not set");
        return false;
    }

    public void hasRefreshedOnce(boolean z) {
        AbstractActivity fragsActivity;
        if (this.client != null) {
            this.client.a(z);
            if (z && this.frag != null && (fragsActivity = getFragsActivity()) != null) {
                fragsActivity.j().k(this.frag.b());
                return;
            }
            return;
        }
        getLogger().warning("client not set");
    }

    public WebView getWebView() {
        return this.webView;
    }

    public void stopWebview() {
        try {
            getLogger().info("stopping webview by loading empty page");
            WebView webView = getWebView();
            if (webView != null) {
                webView.loadData("<html></html>", "text/html", "utf-8");
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // defpackage.bjl
    public void isPrefetch(boolean z) {
        this.isPrefetch = z;
    }

    @Override // defpackage.bjl
    public int getFragTypeNum() {
        return AbstractGamePageFragment.FragType.FEED.c();
    }

    @Override // defpackage.bjl
    public String getFragName() {
        return AbstractGamePageFragment.FragType.FEED.e();
    }

    @Override // defpackage.bjl
    public int getDynamicResNum() {
        return -1;
    }

    private boolean isDisplaySpoilersButton(AbstractActivity abstractActivity) {
        boolean z = true;
        if (abstractActivity != null && abstractActivity.j().aK()) {
            z = false;
        }
        if (z) {
            return isShowSpoilersOverride(abstractActivity);
        }
        return z;
    }
}
