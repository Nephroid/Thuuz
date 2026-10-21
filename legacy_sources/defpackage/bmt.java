package defpackage;

import android.view.View;
import com.thuuz.gtv.activity.AbstractActivity;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bmt implements View.OnClickListener {
    final /* synthetic */ bms a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bmt(bms bmsVar) {
        this.a = bmsVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        WeakReference weakReference;
        bmm bmmVar = (bmm) view.getTag();
        if (bmmVar != null) {
            weakReference = this.a.b;
            AbstractActivity abstractActivity = (AbstractActivity) weakReference.get();
            if (abstractActivity != null) {
                abstractActivity.startActivity(abstractActivity.b(bmmVar.i()));
                return;
            }
            return;
        }
        this.a.k().log(Level.SEVERE, "onClickListener() no ViewHolder set!");
    }
}
