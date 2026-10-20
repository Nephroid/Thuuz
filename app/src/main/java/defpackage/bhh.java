package defpackage;

import android.os.AsyncTask;
import com.facebook.internal.NativeProtocol;
import com.thuuz.gtv.activity.friends.AddFbFriendsActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bhh extends AsyncTask<Void, Void, Integer> {
    final bpl a;
    final /* synthetic */ AddFbFriendsActivity b;

    public bhh(AddFbFriendsActivity addFbFriendsActivity, bpl bplVar) {
        this.b = addFbFriendsActivity;
        this.a = bplVar;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        this.b.e(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        if (this.a == null) {
            this.b.e().severe("cannot find friend in Unfollow");
            return -1;
        }
        try {
            byv byvVar = new byv(this.b.j(), this.a.a());
            byvVar.l();
            return Integer.valueOf(byvVar.v());
        } catch (Throwable th) {
            this.b.e().log(Level.SEVERE, "problem: ", th);
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        bhj bhjVar;
        try {
            if (this.b.d()) {
                if (this.b.isDestroyed()) {
                    return;
                }
                if (num.intValue() == 0) {
                    this.a.b(true);
                    bhjVar = this.b.i;
                    bhjVar.notifyDataSetChanged();
                    this.b.j().a(NativeProtocol.AUDIENCE_FRIENDS, "follow", "facebook", 0L);
                } else {
                    this.b.b("Failed to Follow", bbt.pls_try_again_later);
                }
            }
        } catch (Throwable th) {
            this.b.e().log(Level.SEVERE, "problem: ", th);
        } finally {
            this.b.e(false);
            this.b.aM();
        }
    }
}
