package defpackage;

import android.app.AlertDialog;
import android.widget.CompoundButton;
import java.util.logging.Level;

/* loaded from: classes.dex */
class brt implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ brs a;

    private brt(brs brsVar) {
        this.a = brsVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ brt(brs brsVar, brm brmVar) {
        this(brsVar);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton != null) {
            try {
                brw brwVar = (brw) compoundButton.getTag();
                if (brwVar.d) {
                    if (this.a.a.j().aK()) {
                        a(compoundButton, z, brwVar);
                    } else {
                        a(z, brwVar);
                    }
                }
            } catch (Throwable th) {
                this.a.a.e().log(Level.SEVERE, "Cannot handle: ", th);
            }
        }
    }

    private void a(CompoundButton compoundButton, boolean z, brw brwVar) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.a.getContext());
            builder.setTitle("Turn off scores?");
            builder.setMessage("Are you sure you want to hide all the scores in all the games in Thuuz, along with the 'teaser' text in the game lists?");
            builder.setCancelable(true);
            AlertDialog create = builder.create();
            create.setButton(-1, "Scores OFF", new bru(this, z, brwVar, create));
            create.setButton(-2, "Cancel", new brv(this, compoundButton, create));
            this.a.a.a(create);
            if (!this.a.a.isDestroyed()) {
                create.show();
            }
        } catch (Throwable th) {
            this.a.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, brw brwVar) {
        this.a.a.e().info("display scores: " + String.valueOf(z));
        this.a.a.j().e(z);
        this.a.a.aH();
        this.a.a.j().a("display_scores", "set", brwVar.c.isChecked() ? "ON" : "OFF", 0L);
        this.a.a.j().bm();
    }
}
