package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.App;
import com.thuuz.gtv.activity.friends.UserProfileActivity;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class biv extends AsyncTask<Void, Void, Integer> {
    final /* synthetic */ UserProfileActivity a;

    private biv(UserProfileActivity userProfileActivity) {
        this.a = userProfileActivity;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        this.a.e(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        boolean z;
        byv byvVar;
        int i;
        int i2;
        try {
            z = this.a.i;
            if (z) {
                App j = this.a.j();
                i2 = this.a.g;
                byvVar = new byv(j, i2, true);
            } else {
                App j2 = this.a.j();
                i = this.a.g;
                byvVar = new byv(j2, i);
            }
            byvVar.l();
            return Integer.valueOf(byvVar.v());
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        boolean z;
        boolean z2;
        boolean z3;
        try {
            if (num.intValue() == 0) {
                UserProfileActivity userProfileActivity = this.a;
                z2 = this.a.i;
                userProfileActivity.i = !z2;
                UserProfileActivity userProfileActivity2 = this.a;
                z3 = this.a.i;
                userProfileActivity2.i(z3 ? false : true);
                this.a.c();
            } else {
                StringBuilder sb = new StringBuilder("Failed to ");
                z = this.a.i;
                if (z) {
                    sb.append("Unfollow");
                } else {
                    sb.append("Follow");
                }
                if (this.a.d()) {
                    this.a.b(sb.toString(), bbt.pls_try_again_later);
                }
            }
        } catch (Throwable th) {
            this.a.e().log(Level.SEVERE, "problem: ", th);
        } finally {
            this.a.e(false);
        }
    }
}
