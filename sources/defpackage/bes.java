package defpackage;

import android.os.AsyncTask;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.fantasy.FantasyCompetitionsActivity;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bes extends AsyncTask<Void, Void, Integer> {
    private final WeakReference<FantasyCompetitionsActivity> a;

    public bes(FantasyCompetitionsActivity fantasyCompetitionsActivity) {
        this.a = new WeakReference<>(fantasyCompetitionsActivity);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        FantasyCompetitionsActivity fantasyCompetitionsActivity = this.a.get();
        if (fantasyCompetitionsActivity != null && !fantasyCompetitionsActivity.isDestroyed()) {
            fantasyCompetitionsActivity.e(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        int i;
        FantasyCompetitionsActivity fantasyCompetitionsActivity = this.a.get();
        if (fantasyCompetitionsActivity == null || fantasyCompetitionsActivity.isDestroyed()) {
            return null;
        }
        IService F = fantasyCompetitionsActivity.F();
        if (F == null) {
            return -1;
        }
        try {
            i = new byi(fantasyCompetitionsActivity.j(), F).l();
        } catch (Throwable th) {
            fantasyCompetitionsActivity.e().log(Level.SEVERE, "problem: ", th);
            i = -1;
        }
        return Integer.valueOf(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0019, code lost:
    
        if (r8.intValue() != 0) goto L10;
     */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onPostExecute(java.lang.Integer r8) {
        /*
            r7 = this;
            r6 = 0
            r5 = 0
            java.lang.ref.WeakReference<com.thuuz.gtv.activity.fantasy.FantasyCompetitionsActivity> r0 = r7.a
            java.lang.Object r0 = r0.get()
            com.thuuz.gtv.activity.fantasy.FantasyCompetitionsActivity r0 = (com.thuuz.gtv.activity.fantasy.FantasyCompetitionsActivity) r0
            if (r0 == 0) goto L12
            boolean r1 = r0.isDestroyed()
            if (r1 == 0) goto L13
        L12:
            return
        L13:
            if (r8 == 0) goto L1b
            int r1 = r8.intValue()     // Catch: java.lang.Throwable -> L32 java.lang.Throwable -> L45
            if (r1 == 0) goto L28
        L1b:
            boolean r1 = r0.d()     // Catch: java.lang.Throwable -> L32 java.lang.Throwable -> L45
            if (r1 == 0) goto L28
            int r1 = defpackage.bbt.unable_to_update     // Catch: java.lang.Throwable -> L32 java.lang.Throwable -> L45
            int r2 = defpackage.bbt.pls_try_again_later     // Catch: java.lang.Throwable -> L32 java.lang.Throwable -> L45
            r0.c(r1, r2)     // Catch: java.lang.Throwable -> L32 java.lang.Throwable -> L45
        L28:
            com.thuuz.gtv.activity.fantasy.FantasyCompetitionsActivity.b(r0)     // Catch: java.lang.Throwable -> L32 java.lang.Throwable -> L45
            com.thuuz.gtv.activity.fantasy.FantasyCompetitionsActivity.a(r0, r6)
            r0.e(r5)
            goto L12
        L32:
            r1 = move-exception
            java.util.logging.Logger r2 = r0.e()     // Catch: java.lang.Throwable -> L45
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE     // Catch: java.lang.Throwable -> L45
            java.lang.String r4 = "problem: "
            r2.log(r3, r4, r1)     // Catch: java.lang.Throwable -> L45
            com.thuuz.gtv.activity.fantasy.FantasyCompetitionsActivity.a(r0, r6)
            r0.e(r5)
            goto L12
        L45:
            r1 = move-exception
            com.thuuz.gtv.activity.fantasy.FantasyCompetitionsActivity.a(r0, r6)
            r0.e(r5)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bes.onPostExecute(java.lang.Integer):void");
    }
}
