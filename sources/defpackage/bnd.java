package defpackage;

import android.content.DialogInterface;
import com.thuuz.gtv.App;
import com.thuuz.gtv.activity.sports.GameSortType;
import com.thuuz.gtv.activity.sports.GuideActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bnd implements DialogInterface.OnClickListener {
    final /* synthetic */ GuideActivity a;

    public bnd(GuideActivity guideActivity) {
        this.a = guideActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        GameSortType gameSortType;
        GameSortType gameSortType2;
        try {
            switch (i) {
                case 0:
                    this.a.n = GameSortType.SORT_EQ;
                    App j = this.a.j();
                    gameSortType2 = this.a.n;
                    j.u(gameSortType2.a());
                    break;
                case 1:
                    this.a.n = GameSortType.SORT_CLOCK;
                    App j2 = this.a.j();
                    gameSortType = this.a.n;
                    j2.u(gameSortType.a());
                    break;
                default:
                    this.a.e().severe("unexpected sort choice: " + i);
                    break;
            }
            dialogInterface.dismiss();
            this.a.e(true);
            this.a.bn();
            this.a.e(false);
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }
}
