package com.thuuz.gtv.activity;

import android.app.AlertDialog;
import android.text.method.PasswordTransformationMethod;
import android.widget.AdapterView;
import android.widget.EditText;
import com.actionbarsherlock.view.Menu;
import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.data.AlertSettings;
import com.thuuz.gtv.settings.SettingsActivity;
import com.thuuz.gtv.settings.WebViewFragment;
import defpackage.bbf;
import defpackage.bds;
import defpackage.bdt;
import defpackage.bdu;
import defpackage.bdv;
import defpackage.bdw;
import defpackage.bdx;
import defpackage.bpr;
import defpackage.bps;
import defpackage.bpt;
import defpackage.bpz;
import java.util.logging.Level;

/* loaded from: classes.dex */
public abstract class AbstractSettings extends AbstractActivity {
    public abstract String D_();

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractSettings(String str) {
        super(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void E_() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(k());
            builder.setTitle("Please choose your favorites");
            builder.setMessage("Choose your favorites here, then hit the 'back' button to view game info about them.");
            builder.setCancelable(true);
            AlertDialog create = builder.create();
            create.setButton(-1, "Ok", new bds(this, create));
            a(create);
            if (!isDestroyed()) {
                create.show();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void F_() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(k());
            builder.setTitle("Alerts OFF");
            builder.setMessage("Your global alert preference is set to OFF.");
            builder.setCancelable(true);
            AlertDialog create = builder.create();
            create.setButton(-1, "Turn alerts ON", new bdt(this, create));
            a(create);
            if (!isDestroyed()) {
                create.show();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public Class<? extends AbstractActivity> P() {
        return SettingsActivity.class;
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity
    public void c() {
    }

    public static void a(bpz bpzVar, IService iService, App app) {
        bpr c;
        bps d;
        if (iService != null && !app.l(bpzVar.d()) && !app.n(bpzVar.e()) && (c = iService.c(bpzVar.e())) != null) {
            if (c.k()) {
                app.a(bpt.a(Integer.valueOf(bpzVar.e()), (AlertSettings) null));
                app.o(bpzVar.e());
                return;
            }
            for (bps bpsVar : iService.g(c.g())) {
                if (bpsVar.h() && !app.l(bpsVar.c())) {
                    app.b(bpt.a(Integer.valueOf(bpsVar.c()), iService.e(bpsVar.c())));
                    app.m(bpsVar.c());
                }
            }
            if (bpzVar.d() > 0 && (d = iService.d(bpzVar.d())) != null) {
                app.b(bpt.a(Integer.valueOf(d.c()), iService.e(d.c())));
                app.m(d.c());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(int i, AdapterView.OnItemClickListener onItemClickListener) {
        bpz a;
        try {
            IService F = F();
            if (F != null && (a = F.a(Integer.valueOf(i))) != null) {
                String a2 = a.a();
                if (a2 == null) {
                    a2 = "";
                }
                String format = String.format("Remove %s from favorites?", a2.trim());
                AlertDialog.Builder builder = new AlertDialog.Builder(k());
                builder.setMessage(format).setCancelable(true).setPositiveButton("Yes", new bdv(this, i, onItemClickListener)).setNegativeButton("No", new bdu(this));
                AlertDialog create = builder.create();
                a(create);
                if (!isDestroyed()) {
                    create.show();
                }
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot delete: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(int i, boolean z, String str, AdapterView.OnItemClickListener onItemClickListener) {
        try {
            String format = String.format("Remove %s from favorites?", str);
            AlertDialog.Builder builder = new AlertDialog.Builder(k());
            builder.setMessage(format).setCancelable(true).setPositiveButton("Yes", new bdx(this, z, i, onItemClickListener)).setNegativeButton("No", new bdw(this));
            AlertDialog create = builder.create();
            a(create);
            if (!isDestroyed()) {
                create.show();
            }
        } catch (Throwable th) {
            e().log(Level.SEVERE, "Cannot delete: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(EditText editText, boolean z) {
        if (editText != null) {
            try {
                int selectionStart = editText.getSelectionStart();
                int selectionEnd = editText.getSelectionEnd();
                if (z) {
                    editText.setInputType(128);
                    editText.setTransformationMethod(new PasswordTransformationMethod());
                } else {
                    editText.setInputType(144);
                    editText.setTransformationMethod(null);
                }
                if (selectionEnd >= selectionStart && selectionStart >= 0 && selectionEnd >= 0) {
                    editText.setSelection(selectionStart, selectionEnd);
                }
            } catch (Throwable th) {
                e().log(Level.SEVERE, "problem toggling password mask: ", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.thuuz.gtv.activity.AbstractActivity
    public bbf au() {
        return new WebViewFragment(b(D_()));
    }

    @Override // com.thuuz.gtv.activity.AbstractActivity, com.actionbarsherlock.app.SherlockFragmentActivity, android.support.v4.app.Watson
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
