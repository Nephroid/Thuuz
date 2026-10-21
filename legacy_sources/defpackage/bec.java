package defpackage;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableStringBuilder;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.thuuz.gtv.activity.FeedActivity;
import com.thuuz.gtv.activity.feed.FeedFragment;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bec extends FragmentPagerAdapter {
    final /* synthetic */ FeedActivity a;
    private FeedFragment b;
    private FeedFragment c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bec(FeedActivity feedActivity, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.a = feedActivity;
    }

    @Override // android.support.v4.app.FragmentPagerAdapter, android.support.v4.view.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        boolean z;
        int i2;
        int i3;
        int i4;
        Object instantiateItem = super.instantiateItem(viewGroup, i);
        FeedFragment feedFragment = (FeedFragment) instantiateItem;
        if (FeedActivity.FRAGS.FEED.a() == i) {
            z = this.b == null;
            this.b = feedFragment;
        } else if (FeedActivity.FRAGS.HIGHLIGHTS.a() == i) {
            z = this.c == null;
            this.c = feedFragment;
        } else {
            this.a.e().severe("unknown fragment requested for pos: " + i);
            z = false;
        }
        WebView webView = feedFragment.getWebView();
        if (webView != null) {
            webView.setOnFocusChangeListener(new bed(this.a, null));
        }
        if (z) {
            int count = getCount();
            i2 = this.a.i;
            if (count == i2) {
                Logger e = this.a.e();
                StringBuilder append = new StringBuilder().append("setting initial to: ");
                i3 = this.a.i;
                e.info(append.append(i3).toString());
                FeedActivity feedActivity = this.a;
                i4 = this.a.i;
                feedActivity.f(i4);
            }
        }
        return instantiateItem;
    }

    @Override // android.support.v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        if (i == FeedActivity.FRAGS.FEED.a()) {
            FeedFragment feedFragment = new FeedFragment();
            feedFragment.setFrag(FeedActivity.FRAGS.FEED);
            return feedFragment;
        }
        if (i == FeedActivity.FRAGS.HIGHLIGHTS.a()) {
            FeedFragment feedFragment2 = new FeedFragment();
            feedFragment2.setFrag(FeedActivity.FRAGS.HIGHLIGHTS);
            return feedFragment2;
        }
        this.a.e().severe("unknown feed item requested for: " + i);
        return null;
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        if (!this.a.j().cb() || (!this.a.j().e() && this.a.j().a((Activity) this.a.k()))) {
            return 1;
        }
        return FeedActivity.FRAGS.values().length;
    }

    @Override // android.support.v4.view.PagerAdapter
    public CharSequence getPageTitle(int i) {
        if (i == FeedActivity.FRAGS.FEED.a()) {
            return a("FEED");
        }
        if (i == FeedActivity.FRAGS.HIGHLIGHTS.a()) {
            if (this.a.j().i()) {
                return a("HIGHLIGHTS");
            }
            return a("HIGHLIGHTS");
        }
        return "???";
    }

    private String a(String str) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) str);
        return spannableStringBuilder.toString();
    }

    public FeedFragment a(int i) {
        if (i == FeedActivity.FRAGS.FEED.a()) {
            return this.b;
        }
        if (i != FeedActivity.FRAGS.HIGHLIGHTS.a()) {
            return null;
        }
        return this.c;
    }
}
