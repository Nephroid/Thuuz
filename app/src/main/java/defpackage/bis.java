package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.activity.friends.SearchFriendsActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bis extends AsyncTask<Void, Void, Integer> {
    final /* synthetic */ SearchFriendsActivity a;
    private final String b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bis(SearchFriendsActivity searchFriendsActivity, String str) {
        this.a = searchFriendsActivity;
        this.b = str;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        try {
            this.a.e(true);
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        int i = -1;
        try {
            bzd bzdVar = new bzd(this.a.j(), this.b);
            bzdVar.l();
            i = bzdVar.v();
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
        return Integer.valueOf(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        try {
            if (this.a.d()) {
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        } finally {
            this.a.q = null;
            this.a.e(false);
        }
    }
}
