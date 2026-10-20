package defpackage;

import android.view.View;
import android.widget.AdapterView;
import com.thuuz.gtv.activity.fantasy.FantasySiteSelectActivity;
import com.thuuz.gtv.data.FantasySite;
import java.util.List;

/* loaded from: classes.dex */
public class bfi implements AdapterView.OnItemClickListener {
    final /* synthetic */ FantasySiteSelectActivity a;

    private bfi(FantasySiteSelectActivity fantasySiteSelectActivity) {
        this.a = fantasySiteSelectActivity;
    }

    public /* synthetic */ bfi(FantasySiteSelectActivity fantasySiteSelectActivity, bfh bfhVar) {
        this(fantasySiteSelectActivity);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        List list;
        int i2;
        list = this.a.f;
        FantasySite fantasySite = (FantasySite) list.get(i);
        if (fantasySite != null) {
            if (fantasySite.f()) {
                if (this.a.F() == null) {
                    if (this.a.d()) {
                        this.a.b("Not Ready Yet", bbt.pls_try_again_later);
                        return;
                    }
                    return;
                } else {
                    this.a.d(this.a.getString(bbt.checking_for_updates), "");
                    new bfm(fantasySite, this.a).execute(new Void[0]);
                    return;
                }
            }
            if (fantasySite.g()) {
                this.a.c("Login Expired", "Please login again");
            }
            FantasySiteSelectActivity fantasySiteSelectActivity = this.a;
            i2 = this.a.h;
            fantasySiteSelectActivity.a(i2, fantasySite.a());
        }
    }
}
