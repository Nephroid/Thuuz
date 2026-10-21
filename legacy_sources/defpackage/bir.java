package defpackage;

import android.os.AsyncTask;
import android.widget.EditText;
// [removed legacy tracker import]
import com.thuuz.gtv.activity.friends.SearchFriendsActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bir extends AsyncTask<Void, Void, Integer> {
    final /* synthetic */ SearchFriendsActivity a;
    private final int b;

    public bir(SearchFriendsActivity searchFriendsActivity, int i) {
        this.a = searchFriendsActivity;
        this.b = i;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        super.onPreExecute();
        this.a.e(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        int i = -1;
        try {
            byv byvVar = new byv(this.a.j(), this.b);
            byvVar.l();
            i = byvVar.v();
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        }
        return Integer.valueOf(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        String aM;
        EditText editText;
        try {
            if (this.a.d()) {
                if (num.intValue() == 0) {
                    aM = this.a.aM();
                    editText = this.a.i;
                    editText.setText("");
                    this.a.aN();
                    this.a.d("Success", "You are now following: " + aM);
                    this.a.j().a(NativeProtocol.AUDIENCE_FRIENDS, "follow", "search", 0L);
                } else if (this.a.d()) {
                    this.a.b("Problem with Search", bbt.pls_try_again_later);
                }
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        } finally {
            this.a.q = null;
            this.a.e(false);
        }
    }
}
