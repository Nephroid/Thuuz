package com.thuuz.gtv.activity.fantasy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.samsung.android.sdk.richnotification.Utilities;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.data.FantasySite;
import defpackage.bbf;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bem;
import defpackage.ben;
import defpackage.beo;
import defpackage.bep;
import defpackage.bpe;
import defpackage.bpg;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class FantasyCompListFrag extends bbf {
    private int compId;
    private bpe competition;
    private ben mClickListener;
    private beo mKeyListener;
    private ListView mListView;
    private TextView mNoneFound;
    private List<bep> mTeamItems;
    private bem mTeamsAdapter;

    public FantasyCompListFrag() {
        this.mTeamItems = new ArrayList();
        this.mTeamsAdapter = null;
        this.compId = -1;
    }

    public FantasyCompListFrag(bpe bpeVar) {
        this.mTeamItems = new ArrayList();
        this.mTeamsAdapter = null;
        this.compId = -1;
        this.competition = bpeVar;
        if (this.competition != null) {
            this.compId = this.competition.a();
        }
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate;
        View view = null;
        try {
            inflate = layoutInflater.inflate(bbr.fantasy_comp_list_frag, viewGroup, false);
        } catch (Throwable th) {
            th = th;
        }
        if (inflate == null) {
            return null;
        }
        if (bundle != null) {
            try {
                this.compId = bundle.getInt("id");
                getLogger().info("restoring from comp id: " + this.compId);
            } catch (Throwable th2) {
                view = inflate;
                th = th2;
                getLogger().log(Level.SEVERE, "problem: ", th);
                return view;
            }
        }
        this.mListView = (ListView) inflate.findViewById(bbq.list);
        this.mListView.setItemsCanFocus(true);
        AbstractActivity fragsActivity = getFragsActivity();
        if (fragsActivity == null) {
            getLogger().severe("cannot get activity");
            return inflate;
        }
        this.mTeamsAdapter = new bem(this, fragsActivity);
        this.mListView.setAdapter((ListAdapter) this.mTeamsAdapter);
        fragsActivity.a(this.mListView, true);
        this.mNoneFound = (TextView) inflate.findViewById(bbq.none_found);
        this.mClickListener = new ben(this);
        this.mKeyListener = new beo(this);
        view = inflate;
        return view;
    }

    @Override // android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("id", this.compId);
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        doRefresh(false);
    }

    @Override // defpackage.bbf
    public void doRefresh(boolean z) {
        try {
            if (isActive()) {
                assembleTeamList();
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "cannot handle: ", th);
        }
    }

    public synchronized void assembleTeamList() {
        try {
            IService service = getService();
            if (service != null) {
                ArrayList arrayList = new ArrayList();
                if (this.competition == null) {
                    getLogger().info("attempting to get comp from id: " + this.compId);
                    this.competition = service.j(this.compId);
                }
                if (this.competition == null) {
                    getLogger().severe("null competition from id: " + this.compId);
                } else {
                    Iterator<FantasySite> it = service.k(this.competition.a()).iterator();
                    boolean z = true;
                    while (it.hasNext()) {
                        FantasySite next = it.next();
                        ArrayList arrayList2 = new ArrayList(service.a(next.a(), next.b()));
                        if (next.g() || (next.f() && arrayList2.size() == 0)) {
                            arrayList2.add(0, createEmptyTeam(next, next.g()));
                        }
                        Iterator it2 = arrayList2.iterator();
                        boolean z2 = true;
                        while (it2.hasNext()) {
                            arrayList.add(new bep(this, z2, (bpg) it2.next(), z));
                            z2 = false;
                        }
                        z = false;
                    }
                }
                this.mTeamItems.clear();
                this.mTeamItems.addAll(arrayList);
                if (this.mTeamItems.size() == 0) {
                    if (!this.competition.f()) {
                        this.mNoneFound.setText("Coming soon...");
                    }
                    this.mNoneFound.setVisibility(0);
                } else {
                    this.mNoneFound.setVisibility(8);
                }
                this.mTeamsAdapter.notifyDataSetChanged();
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Problem: ", th);
        }
    }

    private bpg createEmptyTeam(FantasySite fantasySite, boolean z) {
        JSONObject jSONObject = new JSONObject();
        int i = -1;
        String str = "(no teams)";
        if (z) {
            str = " Reconnect";
            i = -2;
        }
        jSONObject.put(Utilities.DB_KEY_IMAGE_NAME, str);
        jSONObject.put("league_name", "");
        jSONObject.put("site_id", fantasySite.a());
        jSONObject.put("id", i);
        jSONObject.put("competition_id", fantasySite.b());
        return new bpg(jSONObject);
    }

    public bpe getCompetition() {
        return this.competition;
    }

    public boolean isNeedReconnect(bpg bpgVar) {
        return -2 == bpgVar.a();
    }

    public void handleItemSelection(int i) {
        AbstractActivity fragsActivity;
        bep bepVar;
        bpg bpgVar;
        bpg bpgVar2;
        bpg bpgVar3;
        try {
            IService service = getService();
            if (service != null && (fragsActivity = getFragsActivity()) != null && (bepVar = this.mTeamItems.get(i)) != null) {
                if (!bepVar.a()) {
                    bpg l = service.l(bepVar.c);
                    if (l != null) {
                        fragsActivity.b(l.a());
                    }
                } else {
                    bpgVar = bepVar.e;
                    if (isNeedReconnect(bpgVar)) {
                        try {
                            bpgVar2 = bepVar.e;
                            Integer valueOf = Integer.valueOf(bpgVar2.b());
                            bpgVar3 = bepVar.e;
                            fragsActivity.a(valueOf.intValue(), bpgVar3.c());
                        } catch (Throwable th) {
                            getLogger().log(Level.SEVERE, "problem: ", th);
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th2);
        }
    }
}
