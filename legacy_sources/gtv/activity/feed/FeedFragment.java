package com.thuuz.gtv.activity.feed;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.sports.ShareActivity;
import com.thuuz.gtv.settings.SettingsAccountActivity;
import com.thuuz.gtv.util.ServerChoice;
import com.thuuz.gtv.util.ThuuzWebViewClient;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bgr;
import defpackage.bgs;
import defpackage.bgt;
import defpackage.bgu;
import defpackage.can;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class FeedFragment extends AbstractFeedFragment {
    private WebView webView;

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate;
        ShareActivity.SHARE_TYPE share_type;
        SettingsAccountActivity.SOURCE source;
        AbstractActivity fragsActivity;
        View view = null;
        try {
            inflate = layoutInflater.inflate(bbr.feed_frag, viewGroup, false);
        } catch (Throwable th) {
            th = th;
        }
        if (inflate == null) {
            return null;
        }
        try {
            setCommentLay(inflate.findViewById(bbq.thuuz_it_container));
            share_type = ShareActivity.SHARE_TYPE.SHARE_TYPE_USERFEED;
            source = SettingsAccountActivity.SOURCE.SOURCE_SHARE;
            fragsActivity = getFragsActivity();
        } catch (Throwable th2) {
            view = inflate;
            th = th2;
            getLogger().log(Level.SEVERE, "problem: ", th);
            return view;
        }
        if (fragsActivity == null) {
            getLogger().severe("cannot get activity for feed webview");
            return inflate;
        }
        setupThuuzEditBox(inflate, share_type, source, fragsActivity);
        adjustCommentPrompt(inflate);
        this.webView = (WebView) inflate.findViewById(bbq.webview);
        this.webView.setBackgroundColor(0);
        WebSettings settings = this.webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(2);
        if (Build.VERSION.SDK_INT > 19) {
            settings.setMixedContentMode(0);
        }
        this.webView.setWebChromeClient(new can());
        this.webView.getSettings().setDatabaseEnabled(fragsActivity.z());
        this.client = new ThuuzWebViewClient(fragsActivity);
        this.webView.setWebViewClient(this.client);
        setWebView(this.webView, this.client);
        fragsActivity.s();
        view = inflate;
        return view;
    }

    private void setupThuuzEditBox(View view, ShareActivity.SHARE_TYPE share_type, SettingsAccountActivity.SOURCE source, AbstractActivity abstractActivity) {
        EditText editText = (EditText) view.findViewById(bbq.thuuz_edit);
        if (editText != null) {
            editText.setOnClickListener(new bgt(this, share_type, source));
            editText.setOnLongClickListener(new bgr(this, share_type, source));
            if (abstractActivity.j().e()) {
                editText.setOnKeyListener(new bgs(this, share_type, source));
            }
        }
    }

    @Override // com.thuuz.gtv.activity.feed.AbstractFeedFragment
    public String getUrl() {
        String str;
        String str2;
        String str3 = "hot";
        if (this.frag != null) {
            str3 = this.frag.c();
        }
        AbstractActivity fragsActivity = getFragsActivity();
        if (fragsActivity == null) {
            str = str3;
            str2 = "";
        } else {
            if ("my".equals(str3) && !fragsActivity.j().aC() && fragsActivity.j().t().size() == 0) {
                str3 = "hot";
            }
            str = str3;
            str2 = fragsActivity.e("?");
        }
        StringBuilder append = new StringBuilder(ServerChoice.n).append("app/").append("2.3/").append("feed/?type=").append(str).append(str2);
        if (fragsActivity != null && fragsActivity.j().ak()) {
            append.append("&hide_highlights=true");
        }
        return append.toString();
    }

    @Override // com.thuuz.gtv.activity.feed.AbstractFeedFragment, defpackage.bbf
    public void doRefresh(boolean z) {
        AbstractActivity fragsActivity = getFragsActivity();
        if (fragsActivity != null) {
            if (!fragsActivity.j().bn()) {
                new bgu(this, null).execute(new Void[0]);
            } else {
                super.doRefresh(false);
            }
        }
    }

    @Override // com.thuuz.gtv.activity.feed.AbstractFeedFragment
    public WebView getWebView() {
        return this.webView;
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
