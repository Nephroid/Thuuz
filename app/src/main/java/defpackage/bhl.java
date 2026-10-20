package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.activity.friends.AddFbFriendsActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bhl extends AsyncTask<Void, Void, Integer> {
    final String a;
    final bpl b;
    final /* synthetic */ AddFbFriendsActivity c;

    public bhl(AddFbFriendsActivity addFbFriendsActivity, String str, bpl bplVar) {
        this.c = addFbFriendsActivity;
        this.a = str;
        this.b = bplVar;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        super.onPreExecute();
        this.c.e(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        int i;
        Throwable th;
        bzd bzdVar;
        try {
            bzdVar = new bzd(this.c.j(), this.a, this.b);
            i = bzdVar.l();
        } catch (Throwable th2) {
            i = -1;
            th = th2;
        }
        try {
            if (bzdVar.y()) {
                this.c.e().info("Facebook invite synced to server");
            } else {
                this.c.e().info("Facebook invite failed to sync to server.");
            }
        } catch (Throwable th3) {
            th = th3;
            this.c.e().log(Level.SEVERE, "problem: ", th);
            return Integer.valueOf(i);
        }
        return Integer.valueOf(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        this.c.e(false);
        if (num.intValue() != 0 && this.c.d()) {
            this.c.b("Failed to Invite", bbt.pls_try_again_later);
        }
    }
}
