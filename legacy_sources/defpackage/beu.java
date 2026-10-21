package defpackage;

import android.view.View;
import android.widget.AdapterView;
import com.thuuz.gtv.activity.fantasy.FantasyCompetitionsActivity;
import com.thuuz.gtv.settings.SettingsAccountActivity;
import java.util.ArrayList;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class beu implements AdapterView.OnItemClickListener {
    final /* synthetic */ FantasyCompetitionsActivity a;

    private beu(FantasyCompetitionsActivity fantasyCompetitionsActivity) {
        this.a = fantasyCompetitionsActivity;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        ArrayList arrayList;
        try {
            arrayList = this.a.f;
            bpe bpeVar = (bpe) arrayList.get(i);
            if (bpeVar != null) {
                if (!bpeVar.f()) {
                    this.a.d(this.a.getString(bbt.coming_soon));
                } else {
                    Integer valueOf = Integer.valueOf(bpeVar.a());
                    if (!this.a.j().i()) {
                        this.a.i = valueOf;
                        this.a.a(SettingsAccountActivity.SOURCE.SOURCE_FANTASY, 3300);
                    } else {
                        this.a.c(valueOf);
                    }
                }
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
