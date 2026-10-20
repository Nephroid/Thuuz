package defpackage;

import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.fantasy.SettingsFantasyTeamDetailActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bfy extends bzt {
    final /* synthetic */ SettingsFantasyTeamDetailActivity a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bfy(SettingsFantasyTeamDetailActivity settingsFantasyTeamDetailActivity, AbstractActivity abstractActivity) {
        super(abstractActivity);
        this.a = settingsFantasyTeamDetailActivity;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bzt, android.os.AsyncTask
    /* renamed from: a */
    public void onPostExecute(Integer num) {
        SettingsFantasyTeamDetailActivity settingsFantasyTeamDetailActivity;
        try {
            if (num.intValue() == 0 && (settingsFantasyTeamDetailActivity = (SettingsFantasyTeamDetailActivity) a()) != null) {
                boolean z = false;
                IService F = settingsFantasyTeamDetailActivity.F();
                if (F != null) {
                    z = F.g();
                }
                settingsFantasyTeamDetailActivity.a(z, F);
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "sportinfo async post problem", th);
        }
    }
}
