package com.thuuz.gtv.activity.fantasy;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.feed.AbstractFeedFragment;
import com.thuuz.gtv.util.ServerChoice;
import com.thuuz.gtv.util.ThuuzWebViewClient;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bft;
import defpackage.bfu;
import defpackage.bfw;
import defpackage.bfx;
import defpackage.can;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class FantasyTeamWebFragment extends AbstractFeedFragment implements bfx {
    private PageType mPageType;
    private int teamId;

    public FantasyTeamWebFragment() {
        this.mPageType = PageType.PAGE_TYPE_PLAYER;
    }

    public FantasyTeamWebFragment(PageType pageType, int i) {
        this.mPageType = PageType.PAGE_TYPE_PLAYER;
        this.mPageType = pageType;
        this.teamId = i;
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.mPageType = PageType.a(bundle.getInt("type"));
        }
    }

    @Override // com.thuuz.gtv.activity.feed.AbstractFeedFragment, android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("type", this.mPageType.mType);
        bundle.putInt("teamId", this.teamId);
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        bft bftVar = null;
        if (bundle != null) {
            this.teamId = bundle.getInt("teamId");
            this.mPageType = PageType.a(bundle.getInt("type"));
        }
        View inflate = layoutInflater.inflate(bbr.fantasy_team_web_fragment, viewGroup, false);
        if (inflate == null) {
            return null;
        }
        ((LinearLayout) inflate.findViewById(bbq.thuuz_it_container)).setVisibility(this.mPageType == PageType.PAGE_TYPE_TALK ? 0 : 8);
        AbstractActivity fragsActivity = getFragsActivity();
        if (fragsActivity == null) {
            return inflate;
        }
        EditText editText = (EditText) inflate.findViewById(bbq.thuuz_edit);
        if (editText != null) {
            bfw bfwVar = new bfw(this, bftVar);
            editText.setOnClickListener(bfwVar);
            editText.setOnLongClickListener(new bft(this, bfwVar));
            if (fragsActivity.j().e()) {
                editText.setOnKeyListener(new bfu(this));
            }
        }
        adjustCommentPrompt(inflate);
        WebView webView = (WebView) inflate.findViewById(bbq.webview);
        webView.setBackgroundColor(0);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        inflate.setTag("smackTalk");
        if (Build.VERSION.SDK_INT > 19) {
            settings.setMixedContentMode(0);
        }
        if (this.mPageType == PageType.PAGE_TYPE_TALK) {
            try {
                settings.setDatabasePath("/data/data/" + fragsActivity.getPackageName() + "/databases/gamefeed");
            } catch (Throwable th) {
                getLogger().log(Level.INFO, "Cannot set webview db path; no problem." + th);
            }
            settings.setDomStorageEnabled(true);
        }
        ThuuzWebViewClient thuuzWebViewClient = new ThuuzWebViewClient(fragsActivity, this);
        webView.setWebViewClient(thuuzWebViewClient);
        setWebView(webView, thuuzWebViewClient);
        boolean z = fragsActivity.z();
        settings.setDatabaseEnabled(z);
        if (!z) {
            settings.setCacheMode(2);
        }
        webView.setWebChromeClient(new can());
        webView.setScrollBarStyle(33554432);
        return inflate;
    }

    @Override // com.thuuz.gtv.activity.feed.AbstractFeedFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        AbstractActivity fragsActivity = getFragsActivity();
        if (fragsActivity != null) {
            fragsActivity.j().k(getTrack());
        }
    }

    private String getTrack() {
        StringBuilder sb = new StringBuilder();
        switch (this.mPageType) {
            case PAGE_TYPE_PLAYER:
                sb.append("/fantasy/players");
                break;
            case PAGE_TYPE_LEAGUE:
                sb.append("/fantasy/league");
                break;
            case PAGE_TYPE_TALK:
                sb.append("/fantasy/smack");
                break;
        }
        return sb.toString();
    }

    @Override // com.thuuz.gtv.activity.feed.AbstractFeedFragment
    public String getUrl() {
        String ay;
        StringBuilder sb = new StringBuilder(ServerChoice.n + "app/2.3/fantasy/");
        switch (this.mPageType) {
            case PAGE_TYPE_PLAYER:
                sb.append("team/");
                break;
            case PAGE_TYPE_LEAGUE:
                sb.append("league/");
                break;
            case PAGE_TYPE_TALK:
                sb.append("talk/");
                break;
        }
        sb.append(String.valueOf(this.teamId));
        sb.append("/?");
        FantasyTeamActivity fantasyTeamAct = getFantasyTeamAct();
        if (fantasyTeamAct == null) {
            return sb.toString();
        }
        if (this.mPageType == PageType.PAGE_TYPE_PLAYER && (ay = fantasyTeamAct.j().ay()) != null && ay.length() > 0) {
            sb.append("tv_provider=");
            sb.append(ay);
        }
        sb.append(fantasyTeamAct.e(sb.toString()));
        return sb.toString();
    }

    /* loaded from: classes.dex */
    public enum PageType {
        PAGE_TYPE_PLAYER(0),
        PAGE_TYPE_LEAGUE(1),
        PAGE_TYPE_TALK(2);

        final int mType;

        PageType(int i) {
            this.mType = i;
        }

        public static PageType a(int i) {
            for (PageType pageType : values()) {
                if (pageType.mType == i) {
                    return pageType;
                }
            }
            return PAGE_TYPE_PLAYER;
        }
    }

    @Override // com.thuuz.gtv.activity.feed.AbstractFeedFragment, defpackage.bbf
    public void doRefresh(boolean z) {
        if (this.mPageType == PageType.PAGE_TYPE_LEAGUE) {
            hasRefreshedOnce(false);
        }
        super.doRefresh(z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.feed.AbstractFeedFragment
    public String getJsRefreshUrl() {
        return this.mPageType == PageType.PAGE_TYPE_PLAYER ? "javascript: refresh_latest_roster()" : super.getJsRefreshUrl();
    }

    public FantasyTeamActivity getFantasyTeamAct() {
        return (FantasyTeamActivity) getActivity();
    }

    @Override // com.thuuz.gtv.activity.feed.AbstractFeedFragment
    protected boolean isShowSpoilersOverride(AbstractActivity abstractActivity) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.feed.AbstractFeedFragment
    public void setShowSpoilersOk() {
    }
}
