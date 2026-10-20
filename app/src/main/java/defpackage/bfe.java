package defpackage;

import android.view.View;
import android.widget.Button;
import com.thuuz.gtv.App;
import com.thuuz.gtv.activity.fantasy.FantasyInviteActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bfe implements View.OnClickListener {
    final /* synthetic */ FantasyInviteActivity a;

    private bfe(FantasyInviteActivity fantasyInviteActivity) {
        this.a = fantasyInviteActivity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Button button;
        int i;
        try {
            button = this.a.f;
            button.setEnabled(false);
            new bff(this.a).execute(new Void[0]);
            App j = this.a.j();
            i = this.a.g;
            j.a("fantasy_invite", "team", String.valueOf(i), 0L);
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
