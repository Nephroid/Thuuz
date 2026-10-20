package defpackage;

import android.widget.CompoundButton;
import android.widget.ListView;
import com.thuuz.gtv.App;
import com.thuuz.gtv.activity.fantasy.SettingsFantasyTeamDetailActivity;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bgd implements CompoundButton.OnCheckedChangeListener {
    final /* synthetic */ SettingsFantasyTeamDetailActivity a;

    private bgd(SettingsFantasyTeamDetailActivity settingsFantasyTeamDetailActivity) {
        this.a = settingsFantasyTeamDetailActivity;
    }

    public /* synthetic */ bgd(SettingsFantasyTeamDetailActivity settingsFantasyTeamDetailActivity, bfy bfyVar) {
        this(settingsFantasyTeamDetailActivity);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        bph bphVar;
        ListView listView;
        List list;
        bph bphVar2;
        bph bphVar3;
        bph bphVar4;
        bph bphVar5;
        bph bphVar6;
        int i;
        bph bphVar7;
        try {
            bphVar = this.a.k;
            if (bphVar != null) {
                boolean bF = this.a.j().bF();
                listView = this.a.g;
                int positionForView = listView.getPositionForView(compoundButton);
                list = this.a.j;
                switch (bfz.a[((bga) list.get(positionForView)).a.ordinal()]) {
                    case 1:
                        bphVar5 = this.a.k;
                        bphVar5.b(z);
                        break;
                    case 2:
                        bphVar4 = this.a.k;
                        bphVar4.c(z);
                        break;
                    case 3:
                        bphVar3 = this.a.k;
                        bphVar3.d(z);
                        break;
                    case 4:
                        bphVar2 = this.a.k;
                        bphVar2.a(z);
                        break;
                }
                App j = this.a.j();
                bphVar6 = this.a.k;
                j.a(bphVar6);
                App j2 = this.a.j();
                i = this.a.f;
                j2.s(i);
                this.a.g(bF);
                if (!this.a.j().bD()) {
                    bphVar7 = this.a.k;
                    if (bphVar7.f() && z) {
                        this.a.F_();
                    }
                }
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
