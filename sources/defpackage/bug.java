package defpackage;

import android.content.Intent;
import android.os.AsyncTask;
import java.util.logging.Level;

/* loaded from: classes.dex */
class bug extends AsyncTask<Void, Void, Integer> {
    final /* synthetic */ buf a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bug(buf bufVar) {
        this.a = bufVar;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        try {
            super.onPreExecute();
            if (!this.a.a.isDestroyed()) {
                this.a.a.e(true);
            }
        } catch (Throwable th) {
            this.a.a.e().log(Level.SEVERE, "problem: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        try {
            this.a.a.j().aY().a(false);
            bzk bzkVar = new bzk(this.a.a.j());
            bzkVar.l();
            if (this.a.a.j().aX()) {
                this.a.a.e().info("logout causes google push change");
                this.a.a.j().ap();
            } else {
                this.a.a.j().ar();
            }
            Intent intent = new Intent("fantasy_sync");
            intent.putExtra("result", 0);
            this.a.a.j().sendBroadcast(intent, "com.thuuz.permission.local.broadcast");
            return Integer.valueOf(bzkVar.v());
        } catch (Throwable th) {
            this.a.a.e().log(Level.SEVERE, "problem: ", th);
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        try {
            if (num.intValue() != 0 && this.a.a.d()) {
                this.a.a.b("Cannot Reset", bbt.pls_try_again_later);
            }
        } catch (Throwable th) {
            this.a.a.e().log(Level.SEVERE, "problem: ", th);
        } finally {
            this.a.a.finish();
        }
    }
}
