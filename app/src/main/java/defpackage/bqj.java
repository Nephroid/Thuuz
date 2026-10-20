package defpackage;
// [removed legacy tracker import]
// [removed legacy tracker import]
// [removed legacy tracker import]
// [removed legacy tracker import]
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.friends.AddFbFriendsActivity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bqj extends bzy implements Request.GraphUserListCallback {
    private final WeakReference<AbstractActivity> a;
    private List<bpj> b = new ArrayList();

    public bqj(AbstractActivity abstractActivity) {
        this.a = new WeakReference<>(abstractActivity);
    }

    @Override // com.facebook.Request.GraphUserListCallback
    public void onCompleted(List<GraphUser> list, Response response) {
        try {
            FacebookRequestError error = response.getError();
            AddFbFriendsActivity addFbFriendsActivity = (AddFbFriendsActivity) a();
            if (addFbFriendsActivity != null) {
                if (error == null) {
                    getLogger().info("fb callback has friends: " + list.size());
                    Iterator<GraphUser> it = list.iterator();
                    while (it.hasNext()) {
                        bpj bpjVar = new bpj(it.next());
                        if (bpjVar.b() == null) {
                            getLogger().severe("thuuzer with null social id: " + bpjVar.toString());
                        } else {
                            this.b.add(bpjVar);
                        }
                    }
                    addFbFriendsActivity.a(this.b);
                    return;
                }
                getLogger().info("failed friends request: " + error);
                addFbFriendsActivity.g(addFbFriendsActivity.a(error));
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "problem: ", th);
        }
    }

    private AbstractActivity a() {
        return this.a.get();
    }
}
