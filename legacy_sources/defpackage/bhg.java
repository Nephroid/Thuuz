package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.activity.friends.AddFbFriendsActivity;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bhg extends AsyncTask<Void, Void, Integer> {
    private final WeakReference<AbstractActivity> a;

    public /* synthetic */ bhg(AbstractActivity abstractActivity, bhd bhdVar) {
        this(abstractActivity);
    }

    private bhg(AbstractActivity abstractActivity) {
        this.a = new WeakReference<>(abstractActivity);
    }

    private AbstractActivity a() {
        return this.a.get();
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        AbstractActivity a = a();
        if (a != null) {
            a.e(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        int i;
        Set set;
        Set set2;
        Map map;
        Map map2;
        Set set3;
        int i2 = -1;
        AddFbFriendsActivity addFbFriendsActivity = (AddFbFriendsActivity) a();
        if (addFbFriendsActivity == null) {
            return -1;
        }
        try {
            byu byuVar = new byu(addFbFriendsActivity.j());
            i2 = byuVar.l();
            if (byuVar.y()) {
                set = addFbFriendsActivity.l;
                set.clear();
                addFbFriendsActivity.e().info("Thuuz server reports friends: " + byuVar.b().size());
                for (bqb bqbVar : byuVar.b()) {
                    map2 = addFbFriendsActivity.k;
                    bpl bplVar = (bpl) map2.get(bqbVar.b());
                    if (bplVar != null) {
                        bplVar.a(bqbVar.a());
                        bplVar.a(bqbVar.c());
                    }
                    if (!bqbVar.c()) {
                        set3 = addFbFriendsActivity.l;
                        set3.add(bqbVar.b());
                    }
                }
                set2 = addFbFriendsActivity.m;
                set2.clear();
                for (String str : byuVar.c()) {
                    map = addFbFriendsActivity.k;
                    bpl bplVar2 = (bpl) map.get(str);
                    if (bplVar2 != null) {
                        bplVar2.c(true);
                    }
                }
            }
            i = i2;
        } catch (Throwable th) {
            addFbFriendsActivity.e().log(Level.SEVERE, "problem: ", th);
            i = i2;
        }
        return Integer.valueOf(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        AddFbFriendsActivity addFbFriendsActivity = (AddFbFriendsActivity) a();
        try {
            if (addFbFriendsActivity == null) {
                return;
            }
            if (addFbFriendsActivity.d()) {
                if (addFbFriendsActivity.isDestroyed()) {
                    return;
                }
                if (num.intValue() == 0) {
                    addFbFriendsActivity.p = true;
                    addFbFriendsActivity.aL();
                } else if (addFbFriendsActivity.d()) {
                    addFbFriendsActivity.a("Problem Fetching Friends", bbt.pls_try_again_later);
                }
            }
        } catch (Throwable th) {
            addFbFriendsActivity.e().log(Level.SEVERE, "problem: ", th);
        } finally {
            addFbFriendsActivity.e(false);
        }
    }
}
