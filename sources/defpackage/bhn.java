package defpackage;

import android.view.View;
import android.widget.ListView;
import com.thuuz.gtv.activity.friends.AddFbFriendsActivity;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class bhn implements View.OnClickListener {
    final /* synthetic */ AddFbFriendsActivity a;

    private bhn(AddFbFriendsActivity addFbFriendsActivity) {
        this.a = addFbFriendsActivity;
    }

    public /* synthetic */ bhn(AddFbFriendsActivity addFbFriendsActivity, bhd bhdVar) {
        this(addFbFriendsActivity);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ListView listView;
        bhj bhjVar;
        Set set;
        Map map;
        Map map2;
        Set set2;
        String str;
        listView = this.a.f;
        int positionForView = listView.getPositionForView(view);
        bhjVar = this.a.i;
        bhf item = bhjVar.getItem(positionForView);
        if (this.a.j().e()) {
            map2 = this.a.k;
            bpl bplVar = (bpl) map2.get(item.b);
            if (bplVar != null) {
                set2 = this.a.l;
                if (!set2.contains(item.b)) {
                    str = this.a.v;
                    if (str != null) {
                        this.a.b("Last action still ongoing...", bbt.pls_try_again_later);
                        return;
                    } else {
                        view.setEnabled(false);
                        this.a.a(bplVar);
                        return;
                    }
                }
                view.setEnabled(false);
                this.a.b(bplVar);
                return;
            }
            this.a.e().warning("Cannot find friend w/ social id: " + item.b);
            return;
        }
        set = this.a.l;
        if (set.contains(item.b)) {
            map = this.a.k;
            int a = ((bpl) map.get(item.b)).a();
            if (a != -1) {
                this.a.d(a);
                return;
            }
            return;
        }
        this.a.c("No Profile Available", "");
    }
}
