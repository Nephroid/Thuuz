package com.thuuz.gtv.activity.game_page;

import android.app.Activity;
import android.content.ComponentCallbacks;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import com.actionbarsherlock.widget.ActivityChooserView;
// [removed legacy tracker import]
import com.thuuz.gtv.data.GameData;
import com.thuuz.gtv.settings.WebViewFragment;
import com.thuuz.gtv.util.ServerChoice;
import defpackage.bbf;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bjm;
import defpackage.bjn;
import defpackage.bjo;
import defpackage.bkq;
import defpackage.bmm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class GamePageGameTabFragment extends AbstractGamePageFragment {
    private TextView away_team_view;
    private TextView home_team_view;
    private View page;
    private ScrollView scroller;
    private int setNextFocusAttempts;
    private View watch_layout;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum FRAGS {
        tv("tv"),
        high("high"),
        fantasy("fant"),
        friends(NativeProtocol.AUDIENCE_FRIENDS),
        feed("game_feed");

        private final String tag;

        FRAGS(String str) {
            this.tag = str;
        }

        public static List<FRAGS> a() {
            ArrayList arrayList = new ArrayList();
            for (FRAGS frags : values()) {
                if (!"game_feed".equals(frags.tag)) {
                    arrayList.add(frags);
                }
            }
            return arrayList;
        }
    }

    public GamePageGameTabFragment() {
    }

    public GamePageGameTabFragment(int i, int i2) {
        super(i, i2);
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view;
        Throwable th;
        try {
            view = layoutInflater.inflate(bbr.game_page_fragment, viewGroup, false);
            if (view == null) {
                return null;
            }
            try {
                if (view instanceof ScrollView) {
                    this.scroller = (ScrollView) view;
                } else {
                    this.scroller = (ScrollView) view.findViewById(bbq.scroller);
                }
                this.page = view.findViewById(bbq.game_details);
                this.game_layout = view.findViewById(bbq.game_layout);
                this.game_layout.setClickable(false);
                this.game_layout.setFocusable(false);
                this.watch_layout = view.findViewById(bbq.watch_lay);
                this.away_team_view = (TextView) view.findViewById(bbq.team_name);
                this.home_team_view = (TextView) view.findViewById(bbq.team2_name);
                addEasterEggs();
                TextView textView = (TextView) view.findViewById(bbq.teaser);
                if (textView != null) {
                    textView.setSingleLine(false);
                    textView.setEllipsize(null);
                    textView.setTextSize(1, getTeaserTextSize());
                }
                addTabletFrags();
                GamePageActivity fragsActivity = getFragsActivity();
                if (fragsActivity != null) {
                    View findViewById = this.game_layout.findViewById(bbq.gec_plot);
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) findViewById.getLayoutParams();
                    if (marginLayoutParams != null) {
                        marginLayoutParams.topMargin = fragsActivity.j().q(5);
                    }
                    getLogger().info("1d gec plot vis/size:" + findViewById.isShown() + "/" + findViewById.getHeight());
                }
                this.setNextFocusAttempts = 0;
                return view;
            } catch (Throwable th2) {
                th = th2;
                getLogger().log(Level.SEVERE, "problem: ", th);
                return view;
            }
        } catch (Throwable th3) {
            view = null;
            th = th3;
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        doRefresh(false);
    }

    @Override // defpackage.bbf
    public void doRefresh(boolean z) {
        try {
            GamePageActivity fragsActivity = getFragsActivity();
            if (fragsActivity != null && !fragsActivity.isDestroyed()) {
                if (this.game_layout != null) {
                    if (this.game_layout.getTag() == null) {
                        fragsActivity.a(this.game_layout, true);
                    }
                    set1dGecPlotUrl(fragsActivity.a(this.game_layout, Integer.valueOf(fragsActivity.aO())));
                }
                showHideOptionalFrags();
                FragmentManager childFragmentManager = getChildFragmentManager();
                for (FRAGS frags : FRAGS.values()) {
                    bbf bbfVar = (bbf) childFragmentManager.findFragmentByTag(frags.tag);
                    if (bbfVar != null) {
                        bbfVar.doRefresh(z);
                        if (frags == FRAGS.tv) {
                            if (((GamePageTvFragment) bbfVar).isEmpty()) {
                                this.watch_layout.setVisibility(8);
                            } else {
                                this.watch_layout.setVisibility(0);
                            }
                        }
                    }
                }
                setNextFocuses(childFragmentManager);
                if (this.scroller != null && fragsActivity.j().a((Activity) fragsActivity) && this.page.getHeight() > this.scroller.getHeight()) {
                    this.scroller.setScrollbarFadingEnabled(false);
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem refreshing game tab: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int setNextFocuses(FragmentManager fragmentManager) {
        bkq bkqVar = null;
        int i = bbq.list;
        GamePageActivity fragsActivity = getFragsActivity();
        if (fragsActivity == null || !fragsActivity.j().e()) {
            return i;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<FRAGS> it = FRAGS.a().iterator();
        while (it.hasNext()) {
            ComponentCallbacks componentCallbacks = (bbf) fragmentManager.findFragmentByTag(it.next().tag);
            if (componentCallbacks != null && (componentCallbacks instanceof bkq)) {
                arrayList.add((bkq) componentCallbacks);
            }
        }
        Iterator it2 = arrayList.iterator();
        boolean z = true;
        int i2 = i;
        bkq bkqVar2 = null;
        while (it2.hasNext()) {
            bkq bkqVar3 = (bkq) it2.next();
            if (bkqVar2 != null) {
                bkqVar3.setNextFocusUpId(bkqVar2.getLastViewId());
            } else {
                bkqVar3.setNextFocusUpId(fragsActivity.aH());
                i2 = bkqVar3.getFirstViewId();
            }
            z = z && bkqVar3.isRendered();
            bkqVar2 = bkqVar3;
        }
        Collections.reverse(arrayList);
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            bkq bkqVar4 = (bkq) it3.next();
            if (bkqVar != null) {
                bkqVar4.setNextFocusDownId(bkqVar.getFirstViewId());
            } else {
                bkqVar4.setNextFocusDownId(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
            }
            z = z && bkqVar4.isRendered();
            bkqVar = bkqVar4;
        }
        if (z) {
            this.setNextFocusAttempts = 0;
            return i2;
        }
        this.setNextFocusAttempts++;
        if (this.setNextFocusAttempts >= 100) {
            getLogger().warning("cannot get setNextFocus after 100 attempts");
            return i2;
        }
        fragsActivity.aa().postDelayed(new bjm(this), 500L);
        return i2;
    }

    private void set1dGecPlotUrl(bmm bmmVar) {
        GameData aQ;
        GamePageActivity fragsActivity = getFragsActivity();
        if (fragsActivity != null && (aQ = fragsActivity.aQ()) != null && bmmVar != null && bmmVar.w != null) {
            if (aQ.ak()) {
                bmmVar.w.setTag(null);
                bmmVar.w.setImageBitmap(null);
                return;
            }
            String o = aQ.o(fragsActivity.j().x());
            if (o != null && o.length() != 0 && !o.equals((String) bmmVar.w.getTag())) {
                getLogger().info("setting url for gec plot: " + o);
                bmmVar.w.setImageUrl(o, fragsActivity.aE());
                bmmVar.w.setTag(o);
            }
        }
    }

    protected int getTeaserTextSize() {
        return 18;
    }

    void addEasterEggs() {
        if (this.away_team_view != null) {
            this.away_team_view.setClickable(true);
            this.away_team_view.setOnClickListener(new bjn(this));
        }
        if (this.home_team_view == null) {
            getLogger().severe("cannot find home team");
        } else {
            this.home_team_view.setClickable(true);
            this.home_team_view.setOnClickListener(new bjo(this));
        }
    }

    protected void addTvFrag() {
        try {
            FragmentManager childFragmentManager = getChildFragmentManager();
            Fragment findFragmentByTag = childFragmentManager.findFragmentByTag("tv");
            if (findFragmentByTag == null) {
                findFragmentByTag = new GamePageTvFragment();
            }
            if (!findFragmentByTag.isAdded()) {
                childFragmentManager.beginTransaction().add(bbq.watch_lay, findFragmentByTag, "tv").commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    protected void addRemoveHighlightFrag(View view) {
        if (view != null) {
            try {
                boolean isShowHighlightFrag = isShowHighlightFrag();
                View findViewById = view.findViewById(bbq.highlight_lay);
                if (findViewById != null) {
                    FragmentManager childFragmentManager = getChildFragmentManager();
                    Fragment findFragmentByTag = childFragmentManager.findFragmentByTag("high");
                    if (isShowHighlightFrag) {
                        findViewById.setVisibility(0);
                        if (findFragmentByTag == null) {
                            childFragmentManager.beginTransaction().add(bbq.highlight_lay, new HighlightsFragment(), "high").commitAllowingStateLoss();
                        }
                    } else {
                        findViewById.setVisibility(8);
                        if (findFragmentByTag != null && findFragmentByTag.isAdded()) {
                            childFragmentManager.beginTransaction().remove(findFragmentByTag).commitAllowingStateLoss();
                        }
                    }
                }
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    private boolean isShowHighlightFrag() {
        GameData aQ;
        GamePageActivity fragsActivity = getFragsActivity();
        return fragsActivity != null && (aQ = fragsActivity.aQ()) != null && aQ.a() && fragsActivity.j().cb();
    }

    protected void addRemoveFriendsFrag(View view) {
        if (view != null) {
            try {
                boolean isShowFriendsFrag = isShowFriendsFrag();
                View findViewById = view.findViewById(bbq.friends_lay);
                if (findViewById != null) {
                    FragmentManager childFragmentManager = getChildFragmentManager();
                    WebViewFragment webViewFragment = (WebViewFragment) childFragmentManager.findFragmentByTag(NativeProtocol.AUDIENCE_FRIENDS);
                    if (isShowFriendsFrag) {
                        findViewById.setVisibility(0);
                        if (webViewFragment == null) {
                            WebViewFragment webViewFragment2 = new WebViewFragment(getFriendsUrl(), true);
                            webViewFragment2.setFailureText("Friends checked into this game");
                            webViewFragment2.setNextFocusRight(bbq.list);
                            childFragmentManager.beginTransaction().add(bbq.friends_lay, webViewFragment2, NativeProtocol.AUDIENCE_FRIENDS).commitAllowingStateLoss();
                        }
                    } else {
                        findViewById.setVisibility(8);
                        if (webViewFragment != null && webViewFragment.isAdded()) {
                            childFragmentManager.beginTransaction().remove(webViewFragment).commitAllowingStateLoss();
                        }
                    }
                }
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    private boolean isShowFriendsFrag() {
        GameData aQ;
        GamePageActivity fragsActivity = getFragsActivity();
        if (fragsActivity == null || !fragsActivity.j().i() || (aQ = fragsActivity.aQ()) == null) {
            return false;
        }
        return aQ.ao();
    }

    private String getFriendsUrl() {
        StringBuilder append = new StringBuilder(ServerChoice.n).append("app/").append("2.3/").append("game/").append(this.gameId).append("/friends/");
        GamePageActivity fragsActivity = getFragsActivity();
        if (fragsActivity != null) {
            append.append(fragsActivity.e(append.toString()));
            if (fragsActivity.j().a((Activity) fragsActivity)) {
                append.append("&page_padding=false");
            }
        }
        return append.toString();
    }

    protected void addTabletFrags() {
        addTvFrag();
        showHideOptionalFrags();
    }

    private void showHideOptionalFrags() {
        addRemoveHighlightFrag(this.page);
        addRemoveFriendsFrag(this.page);
    }

    @Override // defpackage.bbf
    public boolean hasFocus() {
        FragmentManager childFragmentManager = getChildFragmentManager();
        for (FRAGS frags : FRAGS.values()) {
            bbf bbfVar = (bbf) childFragmentManager.findFragmentByTag(frags.tag);
            if (bbfVar != null && bbfVar.hasFocus()) {
                return true;
            }
        }
        return false;
    }

    @Override // defpackage.bbf
    public boolean requestFocus() {
        FragmentManager childFragmentManager = getChildFragmentManager();
        FRAGS[] values = FRAGS.values();
        int length = values.length;
        int i = 0;
        boolean z = false;
        while (i < length) {
            bbf bbfVar = (bbf) childFragmentManager.findFragmentByTag(values[i].tag);
            i++;
            z = bbfVar != null ? bbfVar.requestFocus() : z;
        }
        return z;
    }
}
