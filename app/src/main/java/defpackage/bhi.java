package defpackage;

import android.view.View;
import android.widget.ListView;
import com.thuuz.gtv.activity.friends.AddFbFriendsActivity;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bhi implements View.OnClickListener {
    final /* synthetic */ AddFbFriendsActivity a;

    private bhi(AddFbFriendsActivity addFbFriendsActivity) {
        this.a = addFbFriendsActivity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ bhi(AddFbFriendsActivity addFbFriendsActivity, bhd bhdVar) {
        this(addFbFriendsActivity);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ListView listView;
        bhj bhjVar;
        Map map;
        Set set;
        try {
            listView = this.a.f;
            int positionForView = listView.getPositionForView(view);
            bhjVar = this.a.i;
            bhf item = bhjVar.getItem(positionForView);
            map = this.a.k;
            bpl bplVar = (bpl) map.get(item.b);
            if (bplVar != null) {
                set = this.a.l;
                if (set.contains(item.b)) {
                    view.setEnabled(false);
                    this.a.b(bplVar);
                } else {
                    view.setEnabled(false);
                    this.a.a(bplVar);
                }
            } else {
                this.a.e().warning("Cannot find friend w/ social id: " + item.b);
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
        }
    }
}
