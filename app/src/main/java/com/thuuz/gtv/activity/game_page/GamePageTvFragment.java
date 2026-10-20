package com.thuuz.gtv.activity.game_page;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
// [removed legacy tracker import]
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.game_page.ButtonData;
import com.thuuz.gtv.data.GameData;
import com.thuuz.gtv.data.OnlineResource;
import com.thuuz.gtv.data.OnlineResourceCategory;
import com.thuuz.gtv.util.ThuuzWebViewClient;
import com.thuuz.gtv.util.loader.StatefulLoader;
import defpackage.bbn;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bbt;
import defpackage.bjd;
import defpackage.bjp;
import defpackage.bjq;
import defpackage.bjr;
import defpackage.bjs;
import defpackage.bjt;
import defpackage.bju;
import defpackage.bjv;
import defpackage.bjw;
import defpackage.bjx;
import defpackage.bjy;
import defpackage.bkg;
import defpackage.bkr;
import defpackage.bpa;
import defpackage.bpu;
import defpackage.bqc;
import defpackage.cak;
import defpackage.can;
import defpackage.cap;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import org.apache.http.protocol.HTTP;

/* loaded from: classes.dex */
public class GamePageTvFragment extends AbstractTvFragment {
    private ListView buttonListView;
    private bjy button_adaptor;
    private ImageView dish_logo;
    private boolean hasFocused;
    private boolean isRefreshed;
    private boolean isRefreshing;
    private bpu location;
    private final View.OnClickListener seeAllBroadcastsClickListener = new bjt(this);
    private ListView tvButtonListView;
    private bjy tv_button_adaptor;
    private View tv_frag_view;
    private String tv_listings_cache;
    private TextView watchFrom;
    private View webLay;
    private TextView webLayTitle;
    private TextView webShowAllLink;
    private WebView webView;
    private ThuuzWebViewClient webViewClient;

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        GamePageActivity fragsActivity;
        if (viewGroup == null) {
            return null;
        }
        this.tv_frag_view = null;
        try {
            this.tv_frag_view = layoutInflater.inflate(bbr.game_page_tv_frag, viewGroup, false);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "cannot handle: ", th);
        }
        if (this.tv_frag_view == null || (fragsActivity = getFragsActivity()) == null) {
            return null;
        }
        this.tv_frag_view.setId(fragsActivity.A());
        setupComponents(this.tv_frag_view, fragsActivity);
        return this.tv_frag_view;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setupComponents(View view, GamePageActivity gamePageActivity) {
        this.watchFrom = (TextView) view.findViewById(bbq.start_watching);
        this.buttonListView = (ListView) view.findViewById(bbq.button_list);
        this.button_adaptor = new bjy(this, gamePageActivity, this.buttons);
        this.buttonListView.setAdapter((ListAdapter) this.button_adaptor);
        this.buttonListView.setOnKeyListener(new cap());
        this.buttonListView.setNextFocusRightId(bbq.list);
        this.tvButtonListView = (ListView) view.findViewById(bbq.tv_button_list);
        this.tv_button_adaptor = new bjy(this, gamePageActivity, this.tv_buttons);
        this.tvButtonListView.setAdapter((ListAdapter) this.tv_button_adaptor);
        this.tvButtonListView.setOnKeyListener(new cap());
        this.tvButtonListView.setNextFocusRightId(bbq.list);
        setupScheduleWebview(view, gamePageActivity);
        this.dish_logo = (ImageView) view.findViewById(bbq.dish_logo);
        if (gamePageActivity != null) {
            this.location = gamePageActivity.j().ax().a();
        }
    }

    protected void setupScheduleWebview(View view, GamePageActivity gamePageActivity) {
        boolean z;
        this.webLay = view.findViewById(bbq.web_lay);
        this.webLayTitle = (TextView) view.findViewById(bbq.section_title);
        this.webShowAllLink = (TextView) view.findViewById(bbq.web_show_all_link);
        this.webShowAllLink.setOnClickListener(this.seeAllBroadcastsClickListener);
        this.webView = (WebView) view.findViewById(bbq.sched);
        if (gamePageActivity != null) {
            this.webViewClient = new ThuuzWebViewClient(gamePageActivity);
        }
        this.webView.setWebViewClient(this.webViewClient);
        this.webView.setWebChromeClient(new can());
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.getSettings().setCacheMode(2);
        if (Build.VERSION.SDK_INT > 19) {
            this.webView.getSettings().setMixedContentMode(0);
        }
        if (gamePageActivity != null) {
            z = gamePageActivity.z();
            this.webView.setBackgroundColor(gamePageActivity.getResources().getColor(bbn.game_cell_bg));
            if (gamePageActivity.j().e()) {
                this.webView.setFocusable(true);
            }
        } else {
            z = false;
        }
        this.webView.getSettings().setDatabaseEnabled(z);
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        getLogger().info("refreshing from resume");
        doRefresh(false);
    }

    @Override // defpackage.bbf
    public void doRefresh(boolean z) {
        GamePageActivity fragsActivity = getFragsActivity();
        if (fragsActivity != null && getService() != null) {
            if (!fragsActivity.j().ax().c(this.location)) {
                this.location = fragsActivity.j().ax().a();
                onLocationChanged();
            }
            refreshFrag();
        }
    }

    private void refreshFrag() {
        boolean z;
        if (!this.isRefreshing) {
            try {
                this.isRefreshing = true;
                refreshFragBasic();
                this.tvButtonListView.setVisibility(this.tv_buttons.size() > 0 ? 0 : 8);
                this.buttonListView.setVisibility(this.buttons.size() > 0 ? 0 : 8);
                Iterator<ButtonData> it = this.tv_buttons.iterator();
                while (it.hasNext()) {
                    it.next().a(false);
                }
                ButtonData buttonData = null;
                int i = 0;
                for (ButtonData buttonData2 : this.buttons) {
                    if (i > 0) {
                        z = buttonData.c() != buttonData2.c() && buttonData2.a();
                    } else {
                        z = true;
                    }
                    buttonData2.a(z);
                    i++;
                    buttonData = buttonData2;
                }
                GamePageActivity fragsActivity = getFragsActivity();
                if (fragsActivity != null) {
                    cak.a(fragsActivity, this.tvButtonListView);
                    cak.a(fragsActivity, this.buttonListView);
                }
                this.button_adaptor.notifyDataSetChanged();
                this.tv_button_adaptor.notifyDataSetChanged();
                if (!this.isRefreshed) {
                    reinitializeLoaderIfNecessary();
                    this.isRefreshed = true;
                }
                if (fragsActivity != null && fragsActivity.j().e() && !this.hasFocused) {
                    fragsActivity.aa().postDelayed(new bjp(this), 400L);
                }
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "problem: ", th);
            } finally {
                this.isRefreshing = false;
            }
        }
    }

    public void renderWatchOnTv() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        try {
            GamePageActivity fragsActivity = getFragsActivity();
            if (fragsActivity != null) {
                if (!hasProviderPrefs()) {
                    this.tv_buttons.clear();
                    putButton(ButtonData.BUTTON_TYPE.SET_TV_PROVIDER);
                    return;
                }
                removeAny(ButtonData.BUTTON_TYPE.SET_TV_PROVIDER);
                ArrayList<bpa> showingNowBroadcastsFiltered = getShowingNowBroadcastsFiltered();
                boolean z5 = showingNowBroadcastsFiltered.size() > 0;
                ArrayList<bpa> broadcastsFiltered = getBroadcastsFiltered();
                boolean z6 = broadcastsFiltered.size() != 0;
                GameData game = getGame();
                boolean canShowChannelChangeButtons = canShowChannelChangeButtons();
                boolean e = fragsActivity.j().e();
                boolean z7 = canShowChannelChangeButtons && fragsActivity.j().aW() && !e;
                if (z5) {
                    boolean z8 = !e;
                    if (canShowChannelChangeButtons) {
                        putTvButton(ButtonData.BUTTON_TYPE.LIVE_TV);
                        if (z7) {
                            putTvButton(ButtonData.BUTTON_TYPE.DVR);
                        }
                        boolean aW = fragsActivity.j().aW();
                        fragsActivity.i(false);
                        z2 = !e && showingNowBroadcastsFiltered.size() > 1;
                        z3 = false;
                        z4 = z8;
                        z = aW;
                    } else {
                        removeAny(ButtonData.BUTTON_TYPE.LIVE_TV);
                        removeAny(ButtonData.BUTTON_TYPE.DVR);
                        z2 = false;
                        z3 = false;
                        z4 = z8;
                        z = false;
                    }
                } else {
                    removeAny(ButtonData.BUTTON_TYPE.LIVE_TV);
                    removeAny(ButtonData.BUTTON_TYPE.DVR);
                    if (z6) {
                        boolean z9 = !e;
                        if (z7) {
                            putTvButton(ButtonData.BUTTON_TYPE.DVR, Boolean.FALSE);
                            boolean aW2 = fragsActivity.j().aW();
                            fragsActivity.i(false);
                            z = aW2;
                            z2 = false;
                            z3 = false;
                            z4 = z9;
                        } else {
                            z = false;
                            z2 = false;
                            z3 = false;
                            z4 = z9;
                        }
                    } else if (game == null || game.am()) {
                        z = false;
                        z2 = false;
                        z3 = false;
                        z4 = false;
                    } else {
                        z = false;
                        z2 = false;
                        z3 = true;
                        z4 = false;
                    }
                }
                if (e && (z5 || z6)) {
                    putTvButton(ButtonData.BUTTON_TYPE.TV_LISTINGS);
                } else {
                    removeAny(ButtonData.BUTTON_TYPE.TV_LISTINGS);
                }
                this.webLayTitle.setText("Watch on TV");
                if (game != null && game.am()) {
                    this.webLayTitle.setText("Watch Replay on TV");
                }
                boolean z10 = this.webShowAllLink.getTag() != null;
                if (!fragsActivity.aR() && (z4 || z3 || z10)) {
                    this.webLay.setVisibility(0);
                    this.webLayTitle.setVisibility(0);
                    if (z4) {
                        int i = z10 ? -1 : 2;
                        String str = "";
                        if (game != null) {
                            str = getGame().a(broadcastsFiltered, i);
                        }
                        setHtml(str);
                        if (!z10 && broadcastsFiltered.size() > 2) {
                            this.webShowAllLink.setVisibility(0);
                        } else {
                            this.webShowAllLink.setVisibility(8);
                        }
                    }
                    if (z3) {
                        this.webShowAllLink.setVisibility(8);
                        String str2 = "";
                        if (game != null) {
                            str2 = game.a(getService());
                        }
                        setHtml(str2);
                    }
                } else {
                    this.webLay.setVisibility(8);
                }
                if (z2 && !z10) {
                    this.webLay.setVisibility(0);
                    this.webView.setVisibility(8);
                    this.webLayTitle.setVisibility(4);
                    this.webShowAllLink.setVisibility(0);
                }
                this.dish_logo.setVisibility(z ? 0 : 8);
                if (this.tv_buttons.size() == 0) {
                    this.tvButtonListView.setVisibility(8);
                } else {
                    this.tvButtonListView.setVisibility(0);
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    private void setHtml(String str) {
        if (str != null) {
            if (!this.webViewClient.b() || !str.equals(this.tv_listings_cache)) {
                this.webView.setVisibility(0);
                this.webView.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
                this.tv_listings_cache = str;
                this.webView.requestLayout();
            }
        }
    }

    public void renderWatchOnline() {
        boolean z;
        try {
            GamePageActivity fragsActivity = getFragsActivity();
            if (fragsActivity != null) {
                if (hasProviderPrefs() && fragsActivity.aT()) {
                    Iterator<ButtonData> it = this.tv_buttons.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = false;
                            break;
                        } else {
                            if (ButtonData.BUTTON_TYPE.LIVE_TV == it.next().c()) {
                                z = true;
                                break;
                            }
                        }
                    }
                    if (!z) {
                        z = getShowingNowBroadcastsFiltered().size() > 0;
                    }
                    if (z) {
                        putButton(ButtonData.BUTTON_TYPE.SLING);
                    }
                }
                if (fragsActivity.j().cb() && !fragsActivity.j().ak() && getGame() != null) {
                    Iterator<OnlineResource> it2 = getGame().a(fragsActivity.j(), OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.WATCH_ONLINE).iterator();
                    while (it2.hasNext()) {
                        putButton(ButtonData.BUTTON_TYPE.ONLINE_RESOURCE, it2.next());
                    }
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    public void renderWatchHighlightsOnlineButtons() {
        try {
            GamePageActivity fragsActivity = getFragsActivity();
            if (fragsActivity != null && getGame() != null) {
                Iterator<OnlineResource> it = getGame().a(fragsActivity.j(), OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.WATCH_HIGHLIGHTS).iterator();
                while (it.hasNext()) {
                    putButton(ButtonData.BUTTON_TYPE.ONLINE_RESOURCE, it.next());
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    public void renderListenOnline() {
        GameData game;
        try {
            GamePageActivity fragsActivity = getFragsActivity();
            if (fragsActivity != null && (game = getGame()) != null) {
                Iterator<OnlineResource> it = game.a(fragsActivity.j(), OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.LISTEN).iterator();
                while (it.hasNext()) {
                    putButton(ButtonData.BUTTON_TYPE.ONLINE_RESOURCE, it.next());
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    public void renderGameTracker() {
        GameData game;
        try {
            GamePageActivity fragsActivity = getFragsActivity();
            if (fragsActivity != null && (game = getGame()) != null) {
                Iterator<OnlineResource> it = game.a(fragsActivity.j(), OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.GAME_TRACKER).iterator();
                while (it.hasNext()) {
                    putButton(ButtonData.BUTTON_TYPE.ONLINE_RESOURCE, it.next());
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    public boolean isEmpty() {
        return this.buttons.size() == 0 && this.tv_buttons.size() == 0 && this.watchFrom.getVisibility() == 8 && this.webView.getVisibility() == 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleRecord(bpa bpaVar, View view) {
        try {
            GamePageActivity fragsActivity = getFragsActivity();
            if (fragsActivity != null) {
                String a = bpaVar.a();
                String trim = bpaVar.b().split(",")[0].trim();
                if (((WifiManager) fragsActivity.getSystemService("wifi")).getConnectionInfo() == null) {
                    fragsActivity.c("Wifi Disconnected", "Please connect to wifi and try again");
                    return;
                }
                String substring = a.contains(" ") ? a.substring(0, a.indexOf(" ")) : a;
                bpu a2 = fragsActivity.j().ax().a();
                if (a2 == null) {
                    fragsActivity.c("TV Info Unknown", "Please set TV provider and try again");
                    return;
                }
                bqc m = a2.m();
                if (m == null) {
                    fragsActivity.c("No Device Associated", "Please edit your TV Info and add a device");
                    return;
                }
                getLogger().info("attempting recording of: " + substring + " : " + trim);
                fragsActivity.getSupportLoaderManager().destroyLoader(2);
                fragsActivity.getSupportLoaderManager().initLoader(2, null, new bkg(this, m, bpaVar, view, (ButtonData) this.buttonListView.getItemAtPosition(this.buttonListView.getPositionForView(view))));
                GameData game = getGame();
                if (game != null) {
                    fragsActivity.j().a("record", String.valueOf(game.i()), a2.k(), 0L);
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // defpackage.bbf
    public GamePageActivity getFragsActivity() {
        return (GamePageActivity) getActivity();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSeeAllTVSchedule(View view) {
        this.webShowAllLink.setTag("");
        view.setVisibility(4);
        GameData game = getGame();
        String str = "";
        if (game != null) {
            str = getGame().a(getBroadcastsFiltered(), -1);
        }
        this.webView.setVisibility(0);
        this.webView.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
        this.webView.reload();
        this.webLay.setVisibility(0);
        this.webShowAllLink.setVisibility(8);
        this.webView.requestLayout();
        this.webLay.requestLayout();
        GamePageActivity fragsActivity = getFragsActivity();
        if (fragsActivity != null) {
            if (game != null) {
                fragsActivity.j().a("miscellaneous", "see_all_broadcasts", String.valueOf(game.i()), 0L);
            }
            refreshFrag();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleWatchNow(ArrayList<bpa> arrayList, View view) {
        if (arrayList == null) {
            getLogger().severe("onClickListener cannot get live_broadcasts.");
            return;
        }
        try {
            if (arrayList.size() > 1) {
                handleMultipleWatchNow(arrayList, new bjq(this));
            } else if (arrayList.size() == 0) {
                getLogger().severe("called live button w/ NO broadcasts; hiding button");
                removeAny(ButtonData.BUTTON_TYPE.LIVE_TV);
            } else {
                handleChangeChannel(arrayList.get(0), view);
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleMultipleWatchNow(ArrayList<bpa> arrayList, bjx bjxVar) {
        GamePageActivity fragsActivity = getFragsActivity();
        if (fragsActivity != null) {
            try {
                String[] strArr = new String[arrayList.size()];
                for (int i = 0; i < arrayList.size(); i++) {
                    bpa bpaVar = arrayList.get(i);
                    strArr[i] = bpaVar.b() + " - " + bpaVar.a();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(fragsActivity);
                builder.setTitle(fragsActivity.getResources().getString(bbt.select_channel));
                builder.setAdapter(new ArrayAdapter(fragsActivity, bbr.simple_list_item, bbq.text, strArr), null);
                AlertDialog create = builder.create();
                create.setCancelable(true);
                create.getListView().setOnItemClickListener(new bjr(this, arrayList, bjxVar, create));
                fragsActivity.a(create);
                if (!fragsActivity.isDestroyed()) {
                    create.show();
                }
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "cannot handle: ", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleMultipleRecord(ArrayList<bpa> arrayList) {
        GamePageActivity fragsActivity = getFragsActivity();
        if (fragsActivity != null) {
            try {
                String[] strArr = new String[arrayList.size()];
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, h:mm a");
                for (int i = 0; i < arrayList.size(); i++) {
                    bpa bpaVar = arrayList.get(i);
                    strArr[i] = GameData.d(simpleDateFormat.format(bpaVar.d())) + ": " + bpaVar.a() + " (" + bpaVar.b() + ")";
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(fragsActivity);
                builder.setTitle(fragsActivity.getResources().getString(bbt.select_channel));
                builder.setAdapter(new ArrayAdapter(fragsActivity, bbr.simple_list_item, bbq.text, strArr), null);
                AlertDialog create = builder.create();
                create.setCancelable(true);
                create.getListView().setOnItemClickListener(new bjs(this, arrayList, create));
                fragsActivity.a(create);
                if (!fragsActivity.isDestroyed()) {
                    create.show();
                }
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "cannot handle: ", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleChangeChannel(bpa bpaVar, View view) {
        try {
            GamePageActivity fragsActivity = getFragsActivity();
            if (fragsActivity != null) {
                String a = bpaVar.a();
                String b = bpaVar.b();
                if (fragsActivity.j().f()) {
                    if (a.contains(" ")) {
                        a = a.substring(0, a.indexOf(" "));
                    }
                    String tuningUri = getTuningUri(a, bpaVar, b, fragsActivity.j().ax().a());
                    if (fragsActivity.j().e() && tuningUri != null && tuningUri.length() > 0) {
                        getLogger().info("attempting channel change to: " + a + " : " + tuningUri);
                        fragsActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(tuningUri)));
                        String str = "";
                        String[] m = getGame().m();
                        if (m != null) {
                            str = m[0] + "/" + m[1] + "/";
                        }
                        fragsActivity.j().a("watch", "channel", str + a, getGame().i());
                        return;
                    }
                    fragsActivity.c("Failed to Change", "Changing to " + a + " (" + b + ") failed. Please try manually.");
                    return;
                }
                if (((WifiManager) fragsActivity.getSystemService("wifi")).getConnectionInfo() == null) {
                    fragsActivity.c("Wifi Disconnected", "Please connect to wifi and try again");
                    return;
                }
                String substring = a.contains(" ") ? a.substring(0, a.indexOf(" ")) : a;
                bpu a2 = fragsActivity.j().ax().a();
                if (a2 == null) {
                    fragsActivity.c("TV Info Unknown", "Please set TV provider and try again");
                    return;
                }
                bqc m2 = a2.m();
                if (m2 == null) {
                    fragsActivity.c("No Device Associated", "Please edit your TV Info and add a device");
                    return;
                }
                String trim = b.split(",")[0].trim();
                getLogger().info("attempting channel change to: " + substring + " : " + trim);
                fragsActivity.getSupportLoaderManager().destroyLoader(1);
                fragsActivity.getSupportLoaderManager().initLoader(1, null, new bjv(this, m2, trim, view));
                GameData game = getGame();
                if (game != null) {
                    fragsActivity.j().a("channel", String.valueOf(game.i()), a2.k(), 0L);
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    private void reinitializeLoaderIfNecessary() {
        checkChannelChangeLoader();
        checkRecordLoader();
    }

    private void checkChannelChangeLoader() {
        bjd bjdVar;
        View view;
        GamePageActivity fragsActivity = getFragsActivity();
        if (fragsActivity != null && (bjdVar = (bjd) fragsActivity.getSupportLoaderManager().getLoader(1)) != null && bjdVar.d() != StatefulLoader.State.Finished) {
            String b = bjdVar.b();
            int i = 0;
            Iterator<ButtonData> it = this.tv_buttons.iterator();
            while (true) {
                int i2 = i;
                if (!it.hasNext()) {
                    view = null;
                    break;
                } else {
                    if (it.next().c() == ButtonData.BUTTON_TYPE.LIVE_TV) {
                        view = this.tvButtonListView.getChildAt(i2);
                        break;
                    }
                    i = i2 + 1;
                }
            }
            if (view != null) {
                bjv bjvVar = new bjv(this, bjdVar.c(), b, view);
                if (bjdVar.d() != StatefulLoader.State.Finished) {
                    fragsActivity.getSupportLoaderManager().initLoader(1, null, bjvVar);
                    switch (bju.c[bjdVar.d().ordinal()]) {
                        case 1:
                        case 2:
                        default:
                            return;
                        case 3:
                            bjvVar.a();
                            return;
                    }
                }
            }
        }
    }

    private void checkRecordLoader() {
        bkr bkrVar;
        ButtonData buttonData;
        View view;
        GamePageActivity fragsActivity = getFragsActivity();
        if (fragsActivity != null && (bkrVar = (bkr) fragsActivity.getSupportLoaderManager().getLoader(2)) != null && bkrVar.d() != StatefulLoader.State.Finished) {
            int i = 0;
            Iterator<ButtonData> it = this.tv_buttons.iterator();
            while (true) {
                int i2 = i;
                if (!it.hasNext()) {
                    buttonData = null;
                    view = null;
                    break;
                } else {
                    ButtonData next = it.next();
                    if (next.c() == ButtonData.BUTTON_TYPE.DVR) {
                        view = this.tvButtonListView.getChildAt(i2);
                        buttonData = view != null ? next : null;
                    } else {
                        i = i2 + 1;
                    }
                }
            }
            if (buttonData != null) {
                bkg bkgVar = new bkg(this, bkrVar.b(), bkrVar.c(), view, buttonData);
                if (bkrVar.d() != StatefulLoader.State.Finished) {
                    fragsActivity.getSupportLoaderManager().initLoader(2, null, bkgVar);
                    switch (bju.c[bkrVar.d().ordinal()]) {
                        case 1:
                        case 2:
                        default:
                            return;
                        case 3:
                            bkgVar.a();
                            return;
                    }
                }
            }
        }
    }

    public void renderWatchNearby() {
        GameData game = getGame();
        if (game != null && !game.am()) {
            GamePageActivity fragsActivity = getFragsActivity();
            if (fragsActivity == null || !fragsActivity.j().e()) {
                putButton(ButtonData.BUTTON_TYPE.FIND_LOCAL_BAR);
            }
        }
    }

    protected void renderWatchFromTime() {
        try {
            if (getFragsActivity() != null) {
                GameData game = getGame();
                String str = "";
                if (game != null) {
                    str = getGame().G();
                }
                if ((game != null && !game.am()) || str.length() == 0) {
                    this.watchFrom.setVisibility(8);
                } else {
                    this.watchFrom.setVisibility(0);
                    this.watchFrom.setText(str);
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    protected boolean canShowChannelChangeButtons() {
        try {
            GamePageActivity fragsActivity = getFragsActivity();
            if (fragsActivity != null) {
                return fragsActivity.aF();
            }
            return false;
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
            return false;
        }
    }

    public void onLocationChanged() {
        this.tv_listings_cache = null;
    }

    protected void refreshFragBasic() {
        IService service;
        try {
            GamePageActivity fragsActivity = getFragsActivity();
            if (fragsActivity != null && (service = getService()) != null && getGame() != null) {
                clearButtons();
                renderWatchFromTime();
                Iterator<OnlineResourceCategory> it = service.q().iterator();
                while (it.hasNext()) {
                    switch (bju.b[OnlineResourceCategory.ONLINE_RESOURCE_CATEGORY_TYPE.a(it.next().a().intValue()).ordinal()]) {
                        case 1:
                            renderWatchOnline();
                            break;
                        case 2:
                            if (!fragsActivity.j().a((Activity) fragsActivity)) {
                                renderWatchHighlightsOnlineButtons();
                                break;
                            } else {
                                break;
                            }
                        case 3:
                            renderListenOnline();
                            break;
                        case 4:
                            renderGameTracker();
                            break;
                        case 5:
                            renderWatchOnTv();
                            break;
                    }
                }
                renderWatchNearby();
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem refreshing track frag: ", th);
        }
    }

    @Override // defpackage.bkq
    public void setNextFocusDownId(int i) {
        try {
            if (this.tv_frag_view == null) {
                getLogger().severe("cannot set focus id because of null view");
            } else {
                this.tv_frag_view.setNextFocusDownId(i);
                setNextFocusDownWithinListItems(setNextFocusDownWithinListItems(i, this.buttons, this.buttonListView).intValue(), this.tv_buttons, this.tvButtonListView);
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    @Override // defpackage.bkq
    public void setNextFocusUpId(int i) {
        try {
            if (this.tv_frag_view == null) {
                getLogger().severe("cannot set focus id because of null view");
            } else {
                this.tv_frag_view.setNextFocusUpId(i);
                setNextFocusUpWithinListItems(setNextFocusUpWithinListItems(i, this.tv_buttons, this.tvButtonListView).intValue(), this.buttons, this.buttonListView);
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }

    private Integer setNextFocusUpWithinListItems(int i, List<ButtonData> list, ListView listView) {
        boolean z;
        boolean z2 = false;
        try {
            ArrayList<View> arrayList = new ArrayList();
            int i2 = 0;
            for (ButtonData buttonData : list) {
                View childAt = listView.getChildAt(i2);
                if (childAt != null) {
                    arrayList.add(childAt);
                }
                i2++;
            }
            View view = null;
            int i3 = 0;
            for (View view2 : arrayList) {
                bjw bjwVar = (bjw) view2.getTag();
                if (view == null) {
                    bjwVar.d.setNextFocusUpId(i);
                    z = true;
                } else {
                    bjwVar.d.setNextFocusUpId(view.getId());
                    z = z2;
                }
                view = view2;
                i3++;
                z2 = z;
            }
            if (z2 && view != null) {
                return Integer.valueOf(view.getId());
            }
            return Integer.valueOf(i);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
            return Integer.valueOf(i);
        }
    }

    private Integer setNextFocusDownWithinListItems(int i, List<ButtonData> list, ListView listView) {
        boolean z;
        boolean z2 = false;
        try {
            ArrayList<View> arrayList = new ArrayList();
            int i2 = 0;
            for (ButtonData buttonData : list) {
                View childAt = listView.getChildAt(i2);
                if (childAt != null) {
                    arrayList.add(childAt);
                }
                i2++;
            }
            Collections.reverse(arrayList);
            View view = null;
            int i3 = 0;
            for (View view2 : arrayList) {
                bjw bjwVar = (bjw) view2.getTag();
                if (view == null) {
                    bjwVar.d.setNextFocusDownId(i);
                    z = true;
                } else {
                    bjwVar.d.setNextFocusDownId(view.getId());
                    z = z2;
                }
                view = view2;
                i3++;
                z2 = z;
            }
            if (z2 && view != null) {
                return Integer.valueOf(view.getId());
            }
            return Integer.valueOf(i);
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
            return Integer.valueOf(i);
        }
    }

    @Override // defpackage.bkq
    public int getFirstViewId() {
        View childAt;
        if (this.tv_buttons.size() > 0) {
            View childAt2 = this.tvButtonListView.getChildAt(0);
            if (childAt2 == null) {
                return -1;
            }
            return ((bjw) childAt2.getTag()).d.getId();
        }
        if (this.buttons.size() <= 0 || (childAt = this.buttonListView.getChildAt(0)) == null) {
            return -1;
        }
        return ((bjw) childAt.getTag()).d.getId();
    }

    @Override // defpackage.bkq
    public int getLastViewId() {
        View childAt;
        if (this.buttons.size() > 0) {
            View childAt2 = this.buttonListView.getChildAt(this.buttonListView.getLastVisiblePosition());
            if (childAt2 == null) {
                return -1;
            }
            return ((bjw) childAt2.getTag()).d.getId();
        }
        if (this.tv_buttons.size() <= 0 || (childAt = this.tvButtonListView.getChildAt(this.tvButtonListView.getFirstVisiblePosition())) == null) {
            return -1;
        }
        return ((bjw) childAt.getTag()).d.getId();
    }

    private String getTuningUri(String str, bpa bpaVar, String str2, bpu bpuVar) {
        if (getFragsActivity() == null || bpuVar == null) {
            return null;
        }
        try {
            if (str.contains(" ")) {
                str = str.substring(0, str.indexOf(" "));
            }
            String channelApiUri = getChannelApiUri(str, str2, bpaVar != null ? bpaVar.h() : null);
            IService service = getService();
            if (channelApiUri != null) {
                return channelApiUri;
            }
            if (service == null || service.w().d()) {
                return createProgramApiUri(bpaVar, str, str2, bpuVar.d());
            }
            return channelApiUri;
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
            return null;
        }
    }

    private String getChannelApiUri(String str, String str2, List<String> list) {
        IService service;
        if (getFragsActivity() == null || (service = getService()) == null) {
            return null;
        }
        String b = service.w().b(str2);
        if (b == null) {
            b = service.w().a(str);
        }
        StringBuilder sb = new StringBuilder();
        if (b == null && list != null && list.size() > 0) {
            for (String str3 : list) {
                String a = service.w().a(str3);
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(str3);
                if (a != null && a.length() > 0) {
                    return a;
                }
                b = a;
            }
            return b;
        }
        return b;
    }

    private String createProgramApiUri(bpa bpaVar, String str, String str2, String str3) {
        try {
            String K = getGame().K();
            StringBuilder sb = new StringBuilder("tv://program?eventName=");
            sb.append(URLEncoder.encode(K, HTTP.UTF_8));
            sb.append("&programId=bar&tid=x");
            long time = bpaVar.d().getTime() / 1000;
            sb.append("&startTime=");
            sb.append(time);
            sb.append("&endTime=");
            long f = time + (bpaVar.f() * 60);
            if (getGame().al()) {
                Date date = new Date(f * 1000);
                Date date2 = new Date();
                if (date2.after(date)) {
                    f = (date2.getTime() / 1000) + 600;
                }
            }
            sb.append(f);
            String[] split = str2.split(",");
            String str4 = AppEventsConstants.EVENT_PARAM_VALUE_NO;
            if (split.length > 0) {
                str4 = split[0];
            }
            sb.append("&channel=");
            sb.append(str);
            sb.append("&channelNumber=");
            sb.append(str4);
            sb.append("&lineupId=");
            sb.append(str3);
            return sb.toString();
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
            return null;
        }
    }

    @Override // defpackage.bkq
    public boolean isRendered() {
        boolean z = this.buttons.size() > 0 || this.tv_buttons.size() > 0;
        boolean z2 = z && this.buttons.size() == this.buttonListView.getChildCount() && this.tv_buttons.size() == this.tvButtonListView.getChildCount();
        if (z) {
            return z2;
        }
        GamePageActivity fragsActivity = getFragsActivity();
        return fragsActivity != null && fragsActivity.aG();
    }

    @Override // defpackage.bbf
    public boolean requestFocus() {
        View childAt;
        boolean z = true;
        if (hasFocus()) {
            return false;
        }
        try {
            if (this.tv_buttons.size() > 0 && this.tvButtonListView.getChildCount() > 0) {
                View childAt2 = this.tvButtonListView.getChildAt(0);
                if (childAt2 != null) {
                    getLogger().info("requesting focus for: " + this.tv_buttons.get(0));
                    childAt2.requestFocus();
                } else {
                    z = false;
                }
                return z;
            }
            if (this.buttons.size() <= 0 || this.buttonListView.getChildCount() <= 0 || (childAt = this.buttonListView.getChildAt(0)) == null) {
                return false;
            }
            getLogger().info("requesting focus for: " + this.buttons.get(0));
            childAt.requestFocus();
            return true;
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
            return false;
        }
    }

    @Override // defpackage.bbf
    public boolean hasFocus() {
        try {
            if (!this.tvButtonListView.hasFocus()) {
                if (!this.buttonListView.hasFocus()) {
                    return false;
                }
            }
            return true;
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
            return false;
        }
    }
}
