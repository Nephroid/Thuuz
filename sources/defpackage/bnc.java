package defpackage;

import android.content.DialogInterface;
import android.content.Intent;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.sports.GuideActivity;
import com.thuuz.gtv.activity.sports.StandingsActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bnc implements DialogInterface.OnClickListener {
    final /* synthetic */ GuideActivity a;

    public bnc(GuideActivity guideActivity) {
        this.a = guideActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            IService F = this.a.F();
            if (F != null) {
                ArrayList arrayList = new ArrayList();
                Iterator<bpx> it = F.b().iterator();
                while (it.hasNext()) {
                    for (bpr bprVar : F.a(it.next().a(), false)) {
                        if (bprVar.j()) {
                            arrayList.add(bprVar);
                        }
                    }
                }
                if (i >= 0 && i < arrayList.size()) {
                    bpr bprVar2 = (bpr) arrayList.get(i);
                    Intent intent = new Intent(this.a.k(), (Class<?>) StandingsActivity.class);
                    intent.putExtra("league", bprVar2.e());
                    intent.putExtra("sport_slug", bprVar2.c().b());
                    this.a.startActivity(intent);
                }
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem clicking standings list: ", th);
        }
    }
}
