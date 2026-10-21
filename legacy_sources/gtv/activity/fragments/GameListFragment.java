package com.thuuz.gtv.activity.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.data.FetchRequest;
import defpackage.bbf;
import defpackage.bbl;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bgw;
import defpackage.bgx;
import defpackage.bms;
import defpackage.bpo;
import java.util.ArrayList;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class GameListFragment extends bbf implements bbl {
    private bms adapter;
    private TextView empty;
    private boolean inProgress;
    private boolean isFantasy;
    private ListView list;
    private TextView title;

    public GameListFragment() {
    }

    public GameListFragment(boolean z) {
        this.isFantasy = z;
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate;
        AbstractActivity fragsActivity;
        View view = null;
        try {
            inflate = layoutInflater.inflate(bbr.game_list_frag, viewGroup, false);
        } catch (Throwable th) {
            th = th;
        }
        if (inflate == null) {
            return null;
        }
        try {
            this.title = (TextView) inflate.findViewById(bbq.live_title);
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
        if (this.isFantasy) {
            this.title.setText("My Fantasy Games - Live");
        } else if (fragsActivity.j().az() || fragsActivity.j().aA()) {
            this.title.setText("My Sports - Live");
        } else {
            this.title.setText("All Sports - Live");
        }
        this.list = (ListView) inflate.findViewById(bbq.list);
        this.list.setItemsCanFocus(true);
        if (fragsActivity.j().e()) {
            this.list.setDividerHeight(0);
            int q = fragsActivity.j().q(8);
            this.list.setPadding(0, q, 0, q);
        }
        this.adapter = new bms(fragsActivity, new ArrayList());
        this.list.setAdapter((ListAdapter) this.adapter);
        this.empty = (TextView) inflate.findViewById(bbq.empty);
        setRetainInstance(true);
        view = inflate;
        return view;
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        doRefresh(false);
    }

    @Override // defpackage.bbf
    public void doRefresh(boolean z) {
        try {
            AbstractActivity fragsActivity = getFragsActivity();
            if (fragsActivity != null && fragsActivity.d() && this.list.isShown()) {
                if (this.inProgress) {
                    getLogger().info("ignoring sidebar game update--in progress");
                    return;
                }
                IService service = getService();
                if (service != null) {
                    FetchRequest.REQ_TYPE req_type = FetchRequest.REQ_TYPE.RIGHTBAR;
                    if (this.isFantasy) {
                        req_type = FetchRequest.REQ_TYPE.RIGHTBAR_FANTASY;
                    }
                    service.a(new FetchRequest(req_type, this, fragsActivity.j().a((Activity) fragsActivity)));
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTaskFinished() {
        this.inProgress = false;
    }

    @Override // defpackage.bbl
    public void onStartFetch() {
        AbstractActivity fragsActivity = getFragsActivity();
        if (fragsActivity != null) {
            this.inProgress = true;
            fragsActivity.runOnUiThread(new bgw(this));
        }
    }

    @Override // defpackage.bbl
    public void onConcludeFetch(bpo bpoVar) {
        AbstractActivity fragsActivity = getFragsActivity();
        if (fragsActivity != null) {
            fragsActivity.runOnUiThread(new bgx(this, bpoVar));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bbf
    public boolean isTabbedView() {
        return false;
    }
}
