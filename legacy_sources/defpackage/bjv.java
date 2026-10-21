package defpackage;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import com.thuuz.gtv.activity.game_page.GamePageActivity;
import com.thuuz.gtv.activity.game_page.GamePageTvFragment;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bjv implements LoaderManager.LoaderCallbacks<cbf<String, Throwable>> {
    final /* synthetic */ GamePageTvFragment a;
    private final bqc b;
    private final String c;
    private final View d;

    public bjv(GamePageTvFragment gamePageTvFragment, bqc bqcVar, String str, View view) {
        this.a = gamePageTvFragment;
        this.b = bqcVar;
        this.c = str;
        this.d = view;
    }

    public void a() {
        GamePageActivity fragsActivity = this.a.getFragsActivity();
        if (fragsActivity != null && !fragsActivity.isDestroyed()) {
            fragsActivity.e(true);
            if (this.d != null) {
                this.d.setEnabled(false);
            }
        }
    }

    @Override // android.support.v4.app.LoaderManager.LoaderCallbacks
    public Loader<cbf<String, Throwable>> onCreateLoader(int i, Bundle bundle) {
        a();
        GamePageActivity fragsActivity = this.a.getFragsActivity();
        if (fragsActivity == null) {
            return null;
        }
        return new bjd(fragsActivity, this.b, this.c, fragsActivity.getFilesDir());
    }

    @Override // android.support.v4.app.LoaderManager.LoaderCallbacks
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onLoadFinished(Loader<cbf<String, Throwable>> loader, cbf<String, Throwable> cbfVar) {
        Logger logger;
        JSONObject jSONObject;
        try {
            if (cbfVar.a()) {
                jSONObject = new JSONObject(cbfVar.b());
            } else {
                jSONObject = new JSONObject();
                jSONObject.put("code", -2);
                jSONObject.put("message", cbfVar.c().toString());
            }
            a(jSONObject);
        } catch (Throwable th) {
            logger = this.a.getLogger();
            logger.log(Level.SEVERE, "problem: ", th);
        }
    }

    @Override // android.support.v4.app.LoaderManager.LoaderCallbacks
    public void onLoaderReset(Loader<cbf<String, Throwable>> loader) {
    }

    protected void a(JSONObject jSONObject) {
        Logger logger;
        GamePageActivity fragsActivity = this.a.getFragsActivity();
        if (fragsActivity != null && !fragsActivity.isDestroyed()) {
            try {
                fragsActivity.e(false);
                if (jSONObject.getInt("code") != 1) {
                    fragsActivity.c("Cannot Change Channel", jSONObject.getString("message"));
                } else {
                    fragsActivity.d("Success: changed channel to " + this.c, "");
                }
                if (this.d != null) {
                    this.d.setEnabled(true);
                }
                fragsActivity.getSupportLoaderManager().destroyLoader(1);
            } catch (Throwable th) {
                logger = this.a.getLogger();
                logger.log(Level.SEVERE, "problem: ", th);
            }
        }
    }
}
