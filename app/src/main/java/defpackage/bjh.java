package defpackage;

import android.app.Activity;
import android.os.AsyncTask;
import com.thuuz.gtv.App;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.game_page.GamePageActivity;
import com.thuuz.gtv.data.FetchRequest;
import com.thuuz.gtv.data.GameData;
import com.thuuz.gtv.data.Locations;
import java.lang.ref.WeakReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class bjh extends AsyncTask<Integer, Void, bpo> {
    final WeakReference<GamePageActivity> a;
    private final boolean b;

    public /* synthetic */ bjh(GamePageActivity gamePageActivity, boolean z, bje bjeVar) {
        this(gamePageActivity, z);
    }

    private bjh(GamePageActivity gamePageActivity, boolean z) {
        this.a = new WeakReference<>(gamePageActivity);
        this.b = z;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        GamePageActivity gamePageActivity = this.a.get();
        if (gamePageActivity != null) {
            try {
                gamePageActivity.e(true);
            } catch (Throwable th) {
                gamePageActivity.e().log(Level.SEVERE, "pre execute problem: ", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public bpo doInBackground(Integer... numArr) {
        GamePageActivity gamePageActivity;
        IService F;
        bpo bpoVar;
        Integer num;
        if (numArr.length == 0 || (gamePageActivity = this.a.get()) == null || (F = gamePageActivity.F()) == null) {
            return null;
        }
        App j = gamePageActivity.j();
        Logger e = gamePageActivity.e();
        boolean a = gamePageActivity.j().a((Activity) gamePageActivity);
        try {
            num = numArr[0];
        } catch (Throwable th) {
            e.log(Level.SEVERE, "cannot get game: ", th);
            bpoVar = null;
        }
        if (num == null) {
            return null;
        }
        FetchRequest fetchRequest = new FetchRequest(num, FetchRequest.FORCING.FORCE, a);
        fetchRequest.a(true);
        F.i();
        byx byxVar = new byx(j, fetchRequest, F);
        byxVar.l();
        bpoVar = byxVar.c();
        return bpoVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(bpo bpoVar) {
        GameData gameData;
        bpu bpuVar;
        boolean z;
        bji bjiVar;
        GamePageActivity gamePageActivity = this.a.get();
        if (gamePageActivity != null) {
            try {
                if (gamePageActivity.isDestroyed()) {
                    return;
                }
                if (gamePageActivity.d()) {
                    if (bpoVar == null || bpoVar.b() || bpoVar.c().size() == 0) {
                        if (gamePageActivity.d()) {
                            gamePageActivity.a("Cannot Update Game", bbt.pls_try_again_later);
                        }
                        gameData = gamePageActivity.g;
                        if (gameData == null) {
                            gamePageActivity.aV();
                            return;
                        }
                    } else {
                        gamePageActivity.r = false;
                    }
                    if (bpoVar == null) {
                        return;
                    }
                    GameData k = bpoVar.k();
                    if (!bpoVar.b() && k != null) {
                        gamePageActivity.g = k;
                    }
                    gamePageActivity.e().info("refreshing after game fetch");
                    gamePageActivity.k(this.b);
                    Locations ax = gamePageActivity.j().ax();
                    bpuVar = gamePageActivity.l;
                    if (!ax.c(bpuVar)) {
                        gamePageActivity.k(this.b);
                        gamePageActivity.l = gamePageActivity.j().ax().a();
                    }
                    z = gamePageActivity.t;
                    if (z) {
                        gamePageActivity.t = false;
                        bjiVar = gamePageActivity.n;
                        bjiVar.b();
                    }
                    gamePageActivity.w = true;
                }
            } catch (Throwable th) {
                gamePageActivity.e().log(Level.SEVERE, "Cannot handle: " + th, th);
            } finally {
                gamePageActivity.h = null;
                gamePageActivity.e(false);
            }
        }
    }
}
