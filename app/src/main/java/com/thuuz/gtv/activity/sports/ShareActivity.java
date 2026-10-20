package com.thuuz.gtv.activity.sports;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.actionbarsherlock.view.Menu;
// [removed legacy tracker import]
// [removed legacy tracker import]
// [removed legacy tracker import]
// [removed legacy tracker import]
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.data.GameData;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bbt;
import defpackage.bbu;
import defpackage.bof;
import defpackage.bog;
import defpackage.boh;
import defpackage.boi;
import defpackage.boj;
import defpackage.bok;
import defpackage.bol;
import defpackage.bom;
import defpackage.bon;
import defpackage.boo;
import defpackage.bop;
import defpackage.boq;
import defpackage.bor;
import defpackage.bos;
import defpackage.bot;
import defpackage.bou;
import defpackage.bpf;
import defpackage.bpi;
import defpackage.bqd;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import twitter4j.conf.PropertyConfiguration;

/* loaded from: classes.dex */
public class ShareActivity extends AbstractActivity implements Request.Callback {
    private final List<String> A;
    private final List<Integer> B;
    private ArrayAdapter<String> C;
    private CheckBox f;
    private TextView g;
    private int h;
    private GameData i;
    private UiLifecycleHelper j;
    private boolean k;
    private boq l;
    private boolean m;
    private AsyncTask<Void, Void, String> n;
    private SHARE_TYPE o;
    private int p;
    private String q;
    private String r;
    private EditText s;
    private Spinner t;
    private CheckBox u;
    private Button v;
    private View w;
    private final HashSet<SHARE_TO> x;
    private final Map<SHARE_TO, bot> y;
    private final Map<SHARE_TO, bot> z;

    /* loaded from: classes.dex */
    public enum SHARE_TYPE {
        SHARE_TYPE_GAME(1, "game"),
        SHARE_TYPE_USERFEED(2, PropertyConfiguration.USER),
        SHARE_TYPE_FANTASY(3, "fantasy");

        private final int intType;
        private final String label;

        SHARE_TYPE(int i, String str) {
            this.intType = i;
            this.label = str;
        }

        public int a() {
            return this.intType;
        }

        public static SHARE_TYPE a(int i) {
            switch (i) {
                case 1:
                    return SHARE_TYPE_GAME;
                case 2:
                    return SHARE_TYPE_USERFEED;
                case 3:
                    return SHARE_TYPE_FANTASY;
                default:
                    return SHARE_TYPE_GAME;
            }
        }

        public String b() {
            return this.label;
        }
    }

    /* loaded from: classes.dex */
    public enum SHARE_TO {
        SHARE_TO_FOLLOWERS("Followers"),
        SHARE_TO_FACEBOOK("Facebook"),
        SHARE_TO_TWITTER("Twitter");

        private final String label;

        SHARE_TO(String str) {
            this.label = str;
        }

        public String a() {
            return this.label;
        }
    }

    public ShareActivity() {
        super("Check In");
        this.h = 140;
        this.p = -1;
        this.x = new HashSet<>();
        this.y = Collections.synchronizedMap(new HashMap());
        this.z = Collections.synchronizedMap(new HashMap());
        this.A = new ArrayList();
        this.B = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        if (!((getResources().getConfiguration().screenLayout & 15) >= 3)) {
            setTheme(bbu.Theme_Thuuz);
        }
        super.onCreate(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.j != null) {
            this.j.onPause();
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        try {
            if (this.j != null) {
                this.j.onDestroy();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
        super.onDestroy();
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        bof bofVar = null;
        boolean z = false;
        if (bundle != null) {
            this.o = SHARE_TYPE.a(bundle.getInt("share_type"));
            this.p = bundle.getInt("share_game_id", -1);
            this.q = bundle.getString("share_game_title");
            this.r = bundle.getString("share_game_link");
        } else {
            this.o = SHARE_TYPE.a(getIntent().getIntExtra("share_type", -1));
            this.p = getIntent().getIntExtra("share_game_id", -1);
            this.q = getIntent().getStringExtra("share_game_title");
            this.r = getIntent().getStringExtra("share_game_link");
        }
        if (this.o == SHARE_TYPE.SHARE_TYPE_FANTASY) {
            setContentView(bbr.smack_game_activity);
        } else {
            setContentView(bbr.share_game_activity);
        }
        if (this.o == SHARE_TYPE.SHARE_TYPE_GAME || this.o == SHARE_TYPE.SHARE_TYPE_FANTASY) {
            if (this.p <= 0) {
                e().warning("bailing: cannot get object id for " + this.o.b() + "share");
                setResult(0);
                finish();
                return;
            }
        } else if (this.o == SHARE_TYPE.SHARE_TYPE_USERFEED && Integer.valueOf(new bqd(j().bj()).a()).intValue() <= 0) {
            e().warning("bailing: cannot get user id for user share");
            setResult(0);
            finish();
            return;
        }
        x();
        if (j().e()) {
            View findViewById = findViewById(bbq.share_layout);
            findViewById.setFocusableInTouchMode(false);
            findViewById.setFocusable(false);
        }
        TextView textView = (TextView) findViewById(bbq.game_title);
        this.s = (EditText) findViewById(bbq.message);
        this.s.setOnEditorActionListener(new boo(this, bofVar));
        this.t = (Spinner) findViewById(bbq.tag_a_rival);
        this.t.setEnabled(false);
        this.C = new ArrayAdapter<>(this, bbr.simple_list_item, bbq.text, this.A);
        aH();
        this.t.setAdapter((SpinnerAdapter) this.C);
        this.t.setVisibility(this.o == SHARE_TYPE.SHARE_TYPE_FANTASY ? 0 : 8);
        this.u = (CheckBox) findViewById(bbq.alert_my_friends);
        this.u.setVisibility(8);
        this.f = (CheckBox) findViewById(bbq.post_to_twitter);
        this.v = (Button) findViewById(bbq.share_now);
        this.w = findViewById(bbq.progress);
        this.v.setEnabled(false);
        this.g = (TextView) findViewById(bbq.url);
        this.u.setChecked(true);
        this.f.setOnClickListener(new bor(this, bofVar));
        textView.setText(this.q);
        this.v.setOnClickListener(new bos(this, bofVar));
        this.v.setOnFocusChangeListener(new bof(this));
        boolean ag = ag();
        Button button = (Button) findViewById(bbq.share_other);
        button.setOnClickListener(new bog(this));
        if (!ag) {
            button.setVisibility(4);
        }
        switch (bon.a[this.o.ordinal()]) {
            case 1:
                a("Smack Talk");
                button.setVisibility(8);
                this.v.setText("Smack");
                this.s.setHint("Boast your way to glory.\nPost smack and tag a rival.");
                break;
            case 2:
                a("Check In");
                if (ag) {
                    button.setVisibility(0);
                }
                if (!j().a((Activity) this)) {
                    button.setText("Share to other");
                    break;
                }
                break;
            case 3:
                a("Check In");
                button.setVisibility(8);
                break;
            default:
                e().severe("unexpected share type");
                break;
        }
        if (j().bE() && j().bO()) {
            z = true;
        }
        this.f.setChecked(z);
        this.l = new boq(this);
        this.j = new UiLifecycleHelper(this, this.l);
        this.j.onCreate(bundle);
        j().a("thuuz_it_opened", this.o.b(), "", 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        try {
            super.onResume();
            if (this.j != null) {
                this.j.onResume();
            }
            c();
            if (this.f != null && this.f.isChecked()) {
                if (j().bO()) {
                    if (!this.m && this.r != null) {
                        aM();
                    }
                } else {
                    this.f.setChecked(false);
                }
            }
            f(false);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("share_type", this.o.a());
        bundle.putInt("share_game_id", this.p);
        if (this.q != null) {
            bundle.putString("share_game_title", this.q);
        }
        if (this.r != null) {
            bundle.putString("share_game_link", this.r);
        }
        if (this.j != null) {
            this.j.onSaveInstanceState(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.j != null) {
            this.j.onActivityResult(i, i2, intent);
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        IService F = F();
        if (F != null && this.o == SHARE_TYPE.SHARE_TYPE_GAME) {
            this.i = F.b(Integer.valueOf(this.p));
        }
        c();
        this.v.setEnabled(true);
        this.w.setVisibility(8);
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        IService F = F();
        if (d() && F != null) {
            aH();
        }
    }

    private void aH() {
        if (this.o == SHARE_TYPE.SHARE_TYPE_FANTASY) {
            this.A.clear();
            this.B.clear();
            this.A.add("Tag a rival");
            this.B.add(-1);
            IService F = F();
            if (F != null) {
                bpf n = F.n(this.p);
                if (n == null) {
                    new bop(this, F).execute(new Void[0]);
                } else {
                    this.t.setEnabled(true);
                    for (bpi bpiVar : n.b()) {
                        this.A.add(bpiVar.a());
                        this.B.add(Integer.valueOf(bpiVar.a));
                    }
                }
            }
            this.C.notifyDataSetChanged();
            this.t.setSelection(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aI() {
        if (!j().bO()) {
            aO();
        } else if (this.r != null) {
            aM();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aJ() {
        try {
            new boh(this).execute(new Void[0]);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    public void aF() {
        try {
            new bou(this, null).execute(new Void[0]);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    public void aG() {
        String str = "";
        if (this.o == SHARE_TYPE.SHARE_TYPE_GAME) {
            str = this.r + "?r=" + new Random().nextInt(10000);
        }
        if (this.s.getText() != null) {
            j().aY().a(str, this.s.getText().toString().trim(), this, this, this.l);
        }
    }

    @Override // com.facebook.Request.Callback
    public void onCompleted(Response response) {
        try {
            FacebookRequestError error = response.getError();
            String str = null;
            if (error != null) {
                str = a(error);
                if (str == null) {
                    str = "";
                }
                e().info("problem posting: " + str);
                if (str.length() > 150) {
                    str = str.substring(0, 150);
                }
            }
            g(str);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(String str) {
        bot botVar;
        if (str == null) {
            botVar = new bot(this, true, null);
        } else {
            botVar = new bot(this, false, "FAILED posting to facebook - " + str);
        }
        this.y.put(SHARE_TO.SHARE_TO_FACEBOOK, botVar);
        aK();
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void ac() {
        e().info("Thuuz account has been associated with FB.");
        runOnUiThread(new boi(this));
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void ad() {
        runOnUiThread(new boj(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void aK() {
        try {
            if (this.y.size() >= this.x.size()) {
                e(false);
                Iterator<SHARE_TO> it = this.x.iterator();
                while (it.hasNext()) {
                    SHARE_TO next = it.next();
                    bot botVar = this.y.get(next);
                    if (botVar != null) {
                        if (botVar.b) {
                            if (next == SHARE_TO.SHARE_TO_FACEBOOK) {
                                j().a("thuuz_it_completed_facebook", this.o.b(), String.valueOf(this.p), 0L);
                            } else if (next == SHARE_TO.SHARE_TO_TWITTER) {
                                j().a("thuuz_it_completed_twitter", this.o.b(), String.valueOf(this.p), 0L);
                            } else if (next == SHARE_TO.SHARE_TO_FOLLOWERS) {
                                String str = "alert:off";
                                if (this.u.isChecked()) {
                                    str = "alert:on";
                                }
                                j().a("thuuz_it_completed", this.o.b(), str, 0L);
                            }
                            this.z.remove(next);
                        } else {
                            j().a("thuuz_it_failed", this.o.b(), String.valueOf(this.p), 0L);
                            this.z.put(next, botVar);
                        }
                    }
                }
                if (this.z.size() == 0) {
                    d("Success!", "");
                    setResult(-1);
                    finish();
                } else {
                    if (this.z.size() == this.x.size() && this.z.get(SHARE_TO.SHARE_TO_FOLLOWERS) != null) {
                        if (d()) {
                            b("Failed to Connect", bbt.pls_try_again_later);
                        }
                    } else {
                        this.f.setEnabled(false);
                        this.u.setEnabled(false);
                        if (this.z.containsKey(SHARE_TO.SHARE_TO_TWITTER)) {
                            this.f.setEnabled(true);
                        }
                        StringBuilder sb = new StringBuilder();
                        Iterator<SHARE_TO> it2 = this.z.keySet().iterator();
                        while (it2.hasNext()) {
                            String str2 = this.z.get(it2.next()).a;
                            sb.append(str2.substring(0, Math.min(150, str2.length())));
                            sb.append("\n\n");
                        }
                        sb.append("Note: trying again will only affect ");
                        Iterator<SHARE_TO> it3 = this.z.keySet().iterator();
                        int i = 0;
                        while (it3.hasNext()) {
                            sb.append(it3.next().a());
                            if (i < this.z.size() - 1) {
                                sb.append(" and/or ");
                            }
                            i++;
                        }
                        try {
                            AlertDialog.Builder builder = new AlertDialog.Builder(k());
                            builder.setTitle("Partial Success").setCancelable(true).setMessage(sb.toString());
                            AlertDialog create = builder.create();
                            create.setButton(-1, "Ok", new bok(this, create));
                            a(create);
                            if (!isDestroyed()) {
                                create.show();
                            }
                        } catch (Throwable th) {
                            e().log(Level.SEVERE, "problem: ", th);
                        }
                    }
                    this.v.setEnabled(true);
                    this.w.setVisibility(8);
                }
            }
        } catch (Throwable th2) {
            e().log(Level.SEVERE, "problem: ", th2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aL() {
        j().q(j().aY().b());
        j().aY().a(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public boolean G() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aM() {
        if (this.n != null) {
            e().info("not shortening again--in progress");
        } else {
            this.n = new bol(this);
            this.n.execute(new Void[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String aN() {
        GameData b;
        ArrayList arrayList = new ArrayList();
        try {
            IService F = F();
            if (F != null && (b = F.b(Integer.valueOf(this.p))) != null) {
                String w = b.w();
                String x = b.x();
                arrayList.add(0, w);
                arrayList.add(0, "vs");
                arrayList.add(0, x);
                String ac = b.ac();
                String ad = b.ad();
                if (ac != null && ac.length() > 0) {
                    arrayList.add(ac);
                } else if (ad != null && ad.length() > 0) {
                    arrayList.add(ad);
                }
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
        arrayList.add("#thuuz");
        StringBuilder sb = new StringBuilder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            sb.append((String) it.next());
            sb.append(" ");
        }
        return sb.toString();
    }

    private void aO() {
        new bom(this).execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        g(str, str2);
        this.g.setText(str2 + str);
    }

    private void g(String str, String str2) {
        if (str.length() == 0) {
            this.h = 140;
        } else {
            this.h = 115;
        }
        this.h -= str2.length();
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.Watson
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String aP() {
        StringBuilder sb = new StringBuilder();
        try {
            if (this.i == null) {
                sb.append("Thuuz Sports: ");
            } else {
                sb.append(this.i.L());
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String aQ() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(aR());
        try {
            if (this.i != null) {
                spannableStringBuilder.append((CharSequence) this.i.L());
                spannableStringBuilder.append((CharSequence) "\n");
                int length = spannableStringBuilder.length();
                spannableStringBuilder.append((CharSequence) this.i.W());
                spannableStringBuilder.setSpan(new URLSpan(this.i.W()), length, spannableStringBuilder.length(), 33);
            }
            spannableStringBuilder.append((CharSequence) "\n\nNever miss an exciting game. Get the free Thuuz Sports app now.\n");
            int length2 = spannableStringBuilder.length();
            spannableStringBuilder.append((CharSequence) "http://thuuz.com/");
            spannableStringBuilder.setSpan(new URLSpan("http://thuuz.com/"), length2, spannableStringBuilder.length(), 33);
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot handle: ", th);
        }
        return spannableStringBuilder.toString();
    }

    private String aR() {
        if (this.i == null) {
            return "";
        }
        switch (bon.b[this.i.j().ordinal()]) {
            case 1:
                return "I plan to watch ";
            case 2:
                return "I'm watching ";
            case 3:
                return "I watched ";
            default:
                return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void O() {
        finish();
    }

    public void h(boolean z) {
        this.k = z;
    }
}
