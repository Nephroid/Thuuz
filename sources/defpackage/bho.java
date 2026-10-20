package defpackage;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import com.thuuz.gtv.activity.friends.AddFbFriendsActivity;
import java.util.Map;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bho implements View.OnKeyListener {
    final /* synthetic */ AddFbFriendsActivity a;

    private bho(AddFbFriendsActivity addFbFriendsActivity) {
        this.a = addFbFriendsActivity;
    }

    public /* synthetic */ bho(AddFbFriendsActivity addFbFriendsActivity, bhd bhdVar) {
        this(addFbFriendsActivity);
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        ListView listView;
        Map map;
        View.OnClickListener onClickListener;
        if (keyEvent.getAction() == 0) {
            return false;
        }
        if (i == 23 || i == 66) {
            try {
                listView = this.a.f;
                if (listView.getPositionForView(view) == -1) {
                    return false;
                }
                if (this.a.j().e()) {
                    onClickListener = this.a.t;
                    onClickListener.onClick(view);
                } else {
                    bhp bhpVar = (bhp) view.getTag();
                    if (bhpVar != null) {
                        map = this.a.k;
                        this.a.d(((bpl) map.get(bhpVar.g)).a());
                    }
                }
                return true;
            } catch (Throwable th) {
                this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
            }
        }
        return false;
    }
}
