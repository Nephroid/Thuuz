package defpackage;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import com.thuuz.gtv.activity.game_page.ButtonData;
import com.thuuz.gtv.activity.game_page.GamePageActivity;
import com.thuuz.gtv.activity.game_page.GamePageTvFragment;
import com.thuuz.gtv.data.GameData;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bkg implements LoaderManager.LoaderCallbacks<cbf<String, Throwable>> {
    final /* synthetic */ GamePageTvFragment a;
    private final bqc b;
    private final View c;
    private ButtonData d;
    private final bpa e;

    public bkg(GamePageTvFragment gamePageTvFragment, bqc bqcVar, bpa bpaVar, View view, ButtonData buttonData) {
        this.a = gamePageTvFragment;
        this.b = bqcVar;
        this.e = bpaVar;
        this.c = view;
        this.d = buttonData;
    }

    public void a() {
        GamePageActivity fragsActivity = this.a.getFragsActivity();
        if (fragsActivity != null && !fragsActivity.isDestroyed()) {
            fragsActivity.e(true);
            if (this.c != null) {
                this.c.setEnabled(false);
                View findViewById = this.c.findViewById(bbq.button_lay);
                if (findViewById != null) {
                    findViewById.setClickable(false);
                    findViewById.setEnabled(false);
                }
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
        return new bkr(fragsActivity, this.b, this.e, fragsActivity.getFilesDir());
    }

    @Override // android.support.v4.app.LoaderManager.LoaderCallbacks
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onLoadFinished(Loader<cbf<String, Throwable>> loader, cbf<String, Throwable> cbfVar) {
        Logger logger;
        JSONObject jSONObject;
        try {
            if (cbfVar.a()) {
                String b = cbfVar.b();
                if (b != null && b.length() > 0) {
                    jSONObject = new JSONObject(b);
                } else {
                    jSONObject = new JSONObject();
                    jSONObject.put("code", -2);
                    jSONObject.put("message", "Unknown problem.");
                }
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
        Logger logger2;
        Logger logger3;
        GamePageActivity fragsActivity = this.a.getFragsActivity();
        if (fragsActivity != null && !fragsActivity.isDestroyed()) {
            try {
                fragsActivity.e(false);
                int i = jSONObject.getInt("code");
                if (i != 1) {
                    logger2 = this.a.getLogger();
                    logger2.info("record fail: " + jSONObject.toString());
                    try {
                        StringBuilder sb = new StringBuilder(jSONObject.getString("message"));
                        if (i == 57 || i == 58) {
                            fragsActivity.d(sb.toString(), "");
                            return;
                        }
                        sb.append("\n\nPlease go directly to your DVR to schedule the recording.");
                        AlertDialog.Builder builder = new AlertDialog.Builder(fragsActivity);
                        builder.setTitle("Cannot Record");
                        builder.setMessage(sb.toString());
                        builder.setCancelable(true);
                        builder.setPositiveButton("Ok", new bkh(this));
                        AlertDialog create = builder.create();
                        fragsActivity.a(create);
                        create.show();
                    } catch (Throwable th) {
                        logger3 = this.a.getLogger();
                        logger3.log(Level.SEVERE, "cannot handle: ", th);
                    }
                } else {
                    StringBuilder sb2 = new StringBuilder("Success: ");
                    GameData game = this.a.getGame();
                    if (game != null && game.al()) {
                        sb2.append("recording on ");
                    } else {
                        sb2.append("will record on ");
                    }
                    sb2.append(this.e.i());
                    fragsActivity.d(sb2.toString(), "");
                }
                if (this.c != null) {
                    this.c.setEnabled(true);
                    View findViewById = this.c.findViewById(bbq.button_lay);
                    if (findViewById != null) {
                        findViewById.setClickable(true);
                        findViewById.setEnabled(true);
                    }
                }
                fragsActivity.getSupportLoaderManager().destroyLoader(2);
            } catch (Throwable th2) {
                logger = this.a.getLogger();
                logger.log(Level.SEVERE, "problem: ", th2);
            }
        }
    }
}
