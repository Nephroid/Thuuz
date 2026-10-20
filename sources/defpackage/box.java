package defpackage;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TableLayout;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.data.GameData;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class box extends bmn {
    public box(GameData gameData, AbstractActivity abstractActivity) {
        super(gameData, abstractActivity);
    }

    @Override // defpackage.bmn
    protected void d(bmh bmhVar) {
        if (bmhVar != null) {
            try {
                f(bmhVar);
                if (bmhVar.h(bbq.score) == null) {
                    bmhVar.i(true);
                    bmhVar.a(bbq.score, f().d(0));
                    bmhVar.a(bbq.score2, f().d(1));
                    return;
                }
                bmm bmmVar = (bmm) bmhVar;
                bmmVar.i(false);
                AbstractActivity a = a();
                if (a != null) {
                    if (bmmVar.B == null) {
                        bmmVar.B = (TableLayout) ((LayoutInflater) a.k().getSystemService("layout_inflater")).inflate(bbr.tennis_scores, (ViewGroup) bmmVar.n, false);
                        if (bmmVar.B != null) {
                            bmmVar.l.addView(bmmVar.B);
                        }
                    }
                    if (bmmVar.A == null) {
                        bmmVar.A = (TableLayout) ((LayoutInflater) a.k().getSystemService("layout_inflater")).inflate(bbr.tennis_scores, (ViewGroup) bmmVar.l, false);
                        if (bmmVar.A != null) {
                            bmmVar.n.addView(bmmVar.A);
                        }
                    }
                    f().a(bmmVar.B, bmmVar.A);
                }
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "Cannot render scores: ", th);
            }
        }
    }
}
