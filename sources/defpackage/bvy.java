package defpackage;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import com.thuuz.gtv.App;
import com.thuuz.gtv.settings.SettingsTeamSelectActivity;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bvy extends AsyncTask<Void, Void, Boolean> {
    final WeakReference<SettingsTeamSelectActivity> a;

    public /* synthetic */ bvy(SettingsTeamSelectActivity settingsTeamSelectActivity, bvv bvvVar) {
        this(settingsTeamSelectActivity);
    }

    private bvy(SettingsTeamSelectActivity settingsTeamSelectActivity) {
        this.a = new WeakReference<>(settingsTeamSelectActivity);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        SettingsTeamSelectActivity settingsTeamSelectActivity = this.a.get();
        if (settingsTeamSelectActivity != null) {
            try {
                settingsTeamSelectActivity.e(true);
                settingsTeamSelectActivity.invalidateOptionsMenu();
            } catch (Throwable th) {
                settingsTeamSelectActivity.e().log(Level.SEVERE, "Cannot handle: ", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Boolean doInBackground(Void... voidArr) {
        int i;
        List list;
        List list2;
        List list3;
        try {
            SettingsTeamSelectActivity settingsTeamSelectActivity = this.a.get();
            if (settingsTeamSelectActivity != null) {
                App j = settingsTeamSelectActivity.j();
                i = settingsTeamSelectActivity.f;
                bzw bzwVar = new bzw(j, i);
                bzwVar.l();
                Boolean valueOf = Boolean.valueOf(bzwVar.y());
                if (valueOf.booleanValue()) {
                    list = settingsTeamSelectActivity.i;
                    list.clear();
                    list2 = settingsTeamSelectActivity.i;
                    list2.addAll(bzwVar.b());
                    list3 = settingsTeamSelectActivity.i;
                    Collections.sort(list3);
                }
                return valueOf;
            }
            return false;
        } catch (Throwable th) {
            return Boolean.FALSE;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Boolean bool) {
        ProgressBar progressBar;
        SettingsTeamSelectActivity settingsTeamSelectActivity = this.a.get();
        try {
            if (settingsTeamSelectActivity != null) {
                if (bool.booleanValue()) {
                    settingsTeamSelectActivity.aK();
                } else if (settingsTeamSelectActivity.d()) {
                    settingsTeamSelectActivity.c(bbt.unable_to_update, bbt.pls_try_again_later);
                }
            }
        } catch (Throwable th) {
            settingsTeamSelectActivity.e().log(Level.SEVERE, "Cannot handle: ", th);
        } finally {
            settingsTeamSelectActivity.e(false);
            progressBar = settingsTeamSelectActivity.j;
            progressBar.setVisibility(8);
        }
    }
}
