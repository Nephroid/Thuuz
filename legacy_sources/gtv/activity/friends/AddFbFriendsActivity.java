package com.thuuz.gtv.activity.friends;

import android.R;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.actionbarsherlock.view.Menu;
// [removed legacy tracker import]
// [removed legacy tracker import]
// [removed legacy tracker import]
// [removed legacy tracker import]
// [removed legacy tracker import]
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.AbstractFbSettings;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bbt;
import defpackage.bhd;
import defpackage.bhe;
import defpackage.bhf;
import defpackage.bhg;
import defpackage.bhh;
import defpackage.bhj;
import defpackage.bhk;
import defpackage.bhl;
import defpackage.bhm;
import defpackage.bhn;
import defpackage.bho;
import defpackage.bhp;
import defpackage.bpj;
import defpackage.bpl;
import defpackage.bqe;
import defpackage.bqg;
import defpackage.bqj;
import defpackage.bqk;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class AddFbFriendsActivity extends AbstractFbSettings implements WebDialog.OnCompleteListener {
    private ListView f;
    private View g;
    private View h;
    private bhj i;
    private final List<bhf> j;
    private final Map<String, bpl> k;
    private final Set<String> l;
    private final Set<String> m;
    private final Set<String> n;
    private boolean o;
    private boolean p;
    private final Handler q;
    private final bqk r;
    private bqj s;
    private final View.OnClickListener t;
    private final View.OnKeyListener u;
    private String v;
    private boolean w;
    private boolean x;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddFbFriendsActivity() {
        super("Facebook Friends");
        bhd bhdVar = null;
        this.j = new ArrayList();
        this.k = Collections.synchronizedMap(new HashMap());
        this.l = Collections.synchronizedSet(new HashSet());
        this.m = Collections.synchronizedSet(new HashSet());
        this.n = Collections.synchronizedSet(new HashSet());
        this.o = false;
        this.p = false;
        this.q = new Handler();
        this.r = new bqk(this.q);
        this.t = new bhn(this, bhdVar);
        this.u = new bho(this, bhdVar);
        this.x = true;
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.add_fb_friends_activity);
        this.g = findViewById(bbq.loading_layout);
        this.h = findViewById(bbq.friends_layout);
        this.i = new bhj(this, this, null);
        this.f = (ListView) findViewById(bbq.list_view);
        a(this.f);
        this.f.setAdapter((ListAdapter) this.i);
        this.f.setEmptyView(findViewById(bbq.empty));
        this.f.setItemsCanFocus(true);
        this.f.setNextFocusRightId(bbq.webview);
        Object lastCustomNonConfigurationInstance = getLastCustomNonConfigurationInstance();
        if (lastCustomNonConfigurationInstance != null) {
            bhk bhkVar = (bhk) lastCustomNonConfigurationInstance;
            this.j.addAll(bhkVar.a);
            this.k.putAll(bhkVar.b);
            this.m.addAll(bhk.a(bhkVar));
            this.n.addAll(bhk.b(bhkVar));
            this.l.addAll(bhk.c(bhkVar));
            this.o = true;
            this.p = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractFbSettings, com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        try {
            super.onResume();
            c();
            j().k("/friends/add/facebook");
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractFbSettings, com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        try {
            this.r.a();
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
        super.onDestroy();
    }

    @Override // android.support.v4.app.FragmentActivity
    public Object onRetainCustomNonConfigurationInstance() {
        return new bhk(this, null);
    }

    @Override // com.thuuz.gtv.activity.AbstractSettings, com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        try {
            if (d()) {
                if (this.j.size() == 0) {
                    this.h.setVisibility(4);
                    this.g.setVisibility(0);
                } else {
                    aL();
                }
                bqe aY = j().aY();
                if (aY.a()) {
                    aK();
                    if (!j().aZ()) {
                        aG().d();
                        aG().d(false);
                        aG().a(Session.getActiveSession());
                        return;
                    }
                    return;
                }
                if (this.w) {
                    e().info("finishing addFbFriends because we tried previously to FB auth");
                    finish();
                } else {
                    this.w = true;
                    aY.a(aG(), this);
                }
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aK() {
        if (this.s != null) {
            e().info("refresh in progress; ignoring");
            return;
        }
        this.o = false;
        this.p = false;
        this.s = new bqj(this);
        j().aY().a(this, this.s);
    }

    public void a(List<bpj> list) {
        try {
            this.s = null;
            for (bpj bpjVar : list) {
                this.k.put(bpjVar.b(), bpjVar);
            }
            this.o = true;
            aL();
            if (j().aZ()) {
                new bhg(this, null).execute(new Void[0]);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        } finally {
            this.s = null;
        }
    }

    public void g(String str) {
        this.s = null;
        c("Facebook Request Failed: ", str);
        if (this.j.size() == 0) {
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(bpl bplVar) {
        this.v = bplVar.b();
        j().aY().a(k(), this.v, this);
        j().a(NativeProtocol.AUDIENCE_FRIENDS, "invite", "facebook", 0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(bpl bplVar) {
        try {
            new bhh(this, bplVar).execute(new Void[0]);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // com.facebook.widget.WebDialog.OnCompleteListener
    public void onComplete(Bundle bundle, FacebookException facebookException) {
        try {
            e().info("fb invite completion w/ values: " + bundle);
            if (facebookException != null) {
                if (!(facebookException instanceof FacebookOperationCanceledException)) {
                    e().info("fb invite exception: " + facebookException);
                    c("Facebook Invite Failed:", facebookException.toString());
                }
            } else {
                if (bundle.isEmpty()) {
                    return;
                }
                e().info("success invite w/ values: " + bundle);
                String string = bundle.getString("request");
                if (string == null || string.length() == 0) {
                    e().log(Level.WARNING, "Facebook invite returned invalid request_id.");
                    c("Facebook Invite Failed:", "Invalid request_id");
                    return;
                }
                bpl bplVar = this.k.get(this.v);
                if (bplVar != null) {
                    new bhl(this, string, bplVar).execute(new Void[0]);
                }
                this.n.add(this.v);
                this.i.notifyDataSetChanged();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        } finally {
            this.v = null;
            aM();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aL() {
        boolean z;
        boolean z2;
        try {
            if (this.o && this.p) {
                if (this.l.size() == 0) {
                    new AlertDialog.Builder(this).setMessage("None of your Facebook friends use Thuuz. Spread the word!").setPositiveButton(R.string.yes, new bhd(this)).setIcon(R.drawable.ic_dialog_alert).show();
                    return;
                }
                this.g.setVisibility(8);
                this.h.setVisibility(0);
                ArrayList arrayList = new ArrayList();
                ArrayList<bpl> arrayList2 = new ArrayList();
                Iterator<String> it = this.l.iterator();
                while (it.hasNext()) {
                    bpl bplVar = this.k.get(it.next());
                    if (bplVar != null) {
                        arrayList2.add(bplVar);
                    }
                }
                Collections.sort(arrayList2);
                boolean z3 = true;
                for (bpl bplVar2 : arrayList2) {
                    if (bplVar2.d()) {
                        z2 = z3;
                    } else {
                        arrayList.add(new bhf(this, Boolean.valueOf(z3), bplVar2.b()));
                        z2 = false;
                    }
                    z3 = z2;
                }
                ArrayList<bpl> arrayList3 = new ArrayList(this.k.values());
                Collections.sort(arrayList3);
                boolean z4 = true;
                for (bpl bplVar3 : arrayList3) {
                    if (!bplVar3.d()) {
                        String b = bplVar3.b();
                        if (this.l.contains(b)) {
                            z = z4;
                        } else {
                            arrayList.add(new bhf(this, Boolean.valueOf(z4), b));
                            z = false;
                        }
                        z4 = z;
                    }
                }
                this.j.clear();
                this.j.addAll(arrayList);
                this.i.notifyDataSetChanged();
                aa().postDelayed(new bhe(this), 90L);
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aM() {
        bhp bhpVar;
        try {
            int childCount = this.f.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = this.f.getChildAt(i);
                if (childAt != null && (bhpVar = (bhp) childAt.getTag()) != null && bhpVar.d != null) {
                    bhpVar.d.setEnabled(true);
                }
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public boolean G() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings, com.thuuz.gtv.activity.AbstractActivity
    public Class<? extends AbstractActivity> P() {
        return FriendsActivity.class;
    }

    @Override // com.thuuz.gtv.activity.AbstractSettings, com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.Watson
    public boolean onCreateOptionsMenu(Menu menu) {
        a(menu);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#008";
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void ad() {
        if (d()) {
            b("Failed to Associate Fb", bbt.pls_try_again_later);
        }
        if (!this.p || !this.o) {
            finish();
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void ac() {
        e().info("got associate, running fetch...");
        aK();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractFbSettings
    public bqg aF() {
        return new bhm(this);
    }
}
