package defpackage;

import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.fantasy.SettingsFantasyTeamsActivity;
import com.thuuz.gtv.data.FantasySite;

/* loaded from: classes.dex */
public class bgi extends bfm {
    final /* synthetic */ SettingsFantasyTeamsActivity b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bgi(SettingsFantasyTeamsActivity settingsFantasyTeamsActivity, FantasySite fantasySite, AbstractActivity abstractActivity) {
        super(fantasySite, abstractActivity);
        this.b = settingsFantasyTeamsActivity;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bfm, android.os.AsyncTask
    public void onPreExecute() {
        super.onPreExecute();
        this.b.d("Updating in progress...", "");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bfm, android.os.AsyncTask
    /* renamed from: a */
    public void onPostExecute(Integer num) {
        super.onPostExecute(num);
        if (this.a) {
            this.b.aF();
        }
    }
}
