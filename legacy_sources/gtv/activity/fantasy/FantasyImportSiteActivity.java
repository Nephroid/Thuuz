package com.thuuz.gtv.activity.fantasy;

import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.data.FantasySite;
import com.thuuz.gtv.settings.AbstractLoginRequiredSettingsActivity;
import defpackage.bbq;
import defpackage.bbr;
import defpackage.bew;
import defpackage.bex;
import defpackage.bfb;
import defpackage.bfx;

/* loaded from: classes.dex */
public class FantasyImportSiteActivity extends AbstractLoginRequiredSettingsActivity implements bfx {
    private int f;
    private int g;
    private EditText h;
    private EditText i;
    private Button j;
    private boolean k;
    private String l;
    private String m;
    private String n;

    public static /* synthetic */ EditText a(FantasyImportSiteActivity fantasyImportSiteActivity) {
        return fantasyImportSiteActivity.i;
    }

    public static /* synthetic */ String a(FantasyImportSiteActivity fantasyImportSiteActivity, String str) {
        fantasyImportSiteActivity.l = str;
        return str;
    }

    public static /* synthetic */ boolean a(FantasyImportSiteActivity fantasyImportSiteActivity, boolean z) {
        fantasyImportSiteActivity.k = z;
        return z;
    }

    public static /* synthetic */ Button d(FantasyImportSiteActivity fantasyImportSiteActivity) {
        return fantasyImportSiteActivity.j;
    }

    public static /* synthetic */ EditText e(FantasyImportSiteActivity fantasyImportSiteActivity) {
        return fantasyImportSiteActivity.h;
    }

    public static /* synthetic */ int f(FantasyImportSiteActivity fantasyImportSiteActivity) {
        return fantasyImportSiteActivity.f;
    }

    public static /* synthetic */ int g(FantasyImportSiteActivity fantasyImportSiteActivity) {
        return fantasyImportSiteActivity.g;
    }

    public static /* synthetic */ String h(FantasyImportSiteActivity fantasyImportSiteActivity) {
        return fantasyImportSiteActivity.aG();
    }

    public static /* synthetic */ String i(FantasyImportSiteActivity fantasyImportSiteActivity) {
        return fantasyImportSiteActivity.l;
    }

    public FantasyImportSiteActivity() {
        super("Import Teams");
        this.f = -1;
        this.g = -1;
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void a(Bundle bundle) {
        setContentView(bbr.fantasy_import_site_activity);
        this.f = getIntent().getIntExtra("competition_id", -1);
        if (this.f == -1) {
            if (bundle != null) {
                this.f = bundle.getInt("competition_id", -1);
            }
            if (this.f == -1) {
                e().info("no competition_id for fantasy import. bailing.");
                finish();
                return;
            }
        }
        this.g = getIntent().getIntExtra("site_id", -1);
        if (this.g == -1) {
            if (bundle != null) {
                this.g = bundle.getInt("site_id", -1);
            }
            if (this.g == -1) {
                e().info("no site_id for fantasy import. bailing.");
                finish();
                return;
            }
        }
        this.h = (EditText) findViewById(bbq.username);
        this.i = (EditText) findViewById(bbq.password);
        this.h.addTextChangedListener(new bfb(this, null));
        this.i.addTextChangedListener(new bfb(this, null));
        this.j = (Button) findViewById(bbq.sign_in);
        this.j.setOnClickListener(new bew(this));
        this.j.setEnabled(false);
        ((CheckBox) findViewById(bbq.mask)).setOnCheckedChangeListener(new bex(this));
    }

    @Override // com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("competition_id", this.f);
        bundle.putInt("site_id", this.g);
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        j().k("/fantasy/import/account");
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void m() {
        IService F = F();
        if (F != null) {
            FantasySite b = F.b(this.g, this.f);
            if (b != null) {
                int i = b.i();
                if (i == 0) {
                    this.h.setHint("Username");
                } else if (i == 1) {
                    this.h.setHint("Email");
                } else {
                    this.h.setHint("Username/Email");
                }
                this.m = b.h();
                this.n = b.c();
            }
            c();
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractSettings, com.thuuz.gtv.activity.AbstractActivity
    public void c() {
        FantasySite b;
        IService F = F();
        if (d() && F != null && (b = F.b(this.g, this.f)) != null) {
            a("Import teams from " + b.c());
        }
    }

    public boolean aF() {
        Editable text = this.h.getText();
        return text != null && text.toString().trim().length() > 0 && text.toString().length() > 0;
    }

    public String aG() {
        String str = "<li>Go to the web site for this league</li>";
        if (this.m != null && this.n != null) {
            str = "<li>Go to the web site for this league</li>".replace("this league", this.n).replace("the web site", "<a href=\"" + this.m + "\">the web site</a>");
        }
        return "<html><head><style>body { background-color: #000000; color: #f1f1f1; padding: 5px; margin: 0; } a { color: #2EA4DE; } </style></head><body><p>Sorry, the league web site requires your attention. Please:</p><ol>REPLACE_THIS_LINE<li>Sign out (if you already are signed in)</li><li>Sign in</li></ol></body></html>".replace("REPLACE_THIS_LINE", str);
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public boolean G() {
        return false;
    }

    @Override // com.thuuz.gtv.activity.AbstractSettings
    public String D_() {
        return "#022";
    }
}
