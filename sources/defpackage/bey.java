package defpackage;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.text.Editable;
import android.view.View;
import android.webkit.WebView;
import com.thuuz.gtv.IService;
import com.thuuz.gtv.activity.fantasy.FantasyImportSiteActivity;
import java.lang.ref.WeakReference;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bey extends AsyncTask<Void, Void, Integer> {
    private final WeakReference<FantasyImportSiteActivity> a;
    private String b;

    public bey(FantasyImportSiteActivity fantasyImportSiteActivity) {
        this.a = new WeakReference<>(fantasyImportSiteActivity);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        FantasyImportSiteActivity fantasyImportSiteActivity = this.a.get();
        if (fantasyImportSiteActivity != null && !fantasyImportSiteActivity.isDestroyed()) {
            fantasyImportSiteActivity.e(true);
            FantasyImportSiteActivity.a(fantasyImportSiteActivity, true);
            FantasyImportSiteActivity.d(fantasyImportSiteActivity).setEnabled(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer doInBackground(Void... voidArr) {
        Throwable th;
        int i;
        Editable text;
        FantasyImportSiteActivity fantasyImportSiteActivity = this.a.get();
        if (fantasyImportSiteActivity == null || fantasyImportSiteActivity.isDestroyed()) {
            return -1;
        }
        IService F = fantasyImportSiteActivity.F();
        try {
            text = FantasyImportSiteActivity.e(fantasyImportSiteActivity).getText();
        } catch (Throwable th2) {
            th = th2;
            i = -1;
        }
        if (text == null) {
            return -1;
        }
        String trim = text.toString().trim();
        Editable text2 = FantasyImportSiteActivity.a(fantasyImportSiteActivity).getText();
        if (text2 == null) {
            return -1;
        }
        byo byoVar = new byo(fantasyImportSiteActivity.j(), F, FantasyImportSiteActivity.f(fantasyImportSiteActivity), FantasyImportSiteActivity.g(fantasyImportSiteActivity), fantasyImportSiteActivity.j().d(trim, text2.toString()));
        int l = byoVar.l();
        try {
            FantasyImportSiteActivity.a(fantasyImportSiteActivity, byoVar.B());
            if (byoVar.y()) {
                car carVar = new car();
                carVar.a(new byl(fantasyImportSiteActivity.j()));
                carVar.a(new byr(fantasyImportSiteActivity.j(), F, FantasyImportSiteActivity.f(fantasyImportSiteActivity)));
                try {
                    carVar.a(60000L);
                } catch (InterruptedException e) {
                    fantasyImportSiteActivity.e().log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
                } finally {
                    carVar.a();
                }
            } else {
                String b = byoVar.b();
                if (b != null) {
                    this.b = b;
                }
            }
            i = l;
        } catch (Throwable th3) {
            i = l;
            th = th3;
            fantasyImportSiteActivity.e().log(Level.SEVERE, "problem: ", th);
            return Integer.valueOf(i);
        }
        return Integer.valueOf(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Integer num) {
        FantasyImportSiteActivity fantasyImportSiteActivity = this.a.get();
        if (fantasyImportSiteActivity != null) {
            try {
                if (fantasyImportSiteActivity.isDestroyed()) {
                    return;
                }
                switch (num.intValue()) {
                    case -86:
                        try {
                            AlertDialog.Builder builder = new AlertDialog.Builder(fantasyImportSiteActivity.k());
                            String str = this.b;
                            if (str == null) {
                                str = FantasyImportSiteActivity.h(fantasyImportSiteActivity);
                            }
                            View a = a(str);
                            if (a != null) {
                                builder.setTitle("Help Required for Login").setCancelable(true).setView(a);
                                AlertDialog create = builder.create();
                                create.setButton(-1, "Ok", new bez(this, create));
                                fantasyImportSiteActivity.a(create);
                                if (!fantasyImportSiteActivity.isDestroyed()) {
                                    create.show();
                                    break;
                                } else {
                                    return;
                                }
                            } else {
                                return;
                            }
                        } catch (Throwable th) {
                            fantasyImportSiteActivity.e().log(Level.SEVERE, "problem: ", th);
                            break;
                        }
                    case -84:
                        fantasyImportSiteActivity.c("Sign-in Invalid", "Please confirm your username/password on the league's web site");
                        break;
                    case -83:
                        fantasyImportSiteActivity.e().warning("account creation error");
                        if (fantasyImportSiteActivity.d()) {
                            fantasyImportSiteActivity.b("Problem with League Server", bbt.pls_try_again_later);
                            break;
                        }
                        break;
                    case 0:
                        fantasyImportSiteActivity.f();
                        fantasyImportSiteActivity.finish();
                        break;
                    default:
                        fantasyImportSiteActivity.e().warning("signin error code: " + String.valueOf(num));
                        if (fantasyImportSiteActivity.d()) {
                            if (FantasyImportSiteActivity.i(fantasyImportSiteActivity) != null && FantasyImportSiteActivity.i(fantasyImportSiteActivity).length() > 0) {
                                fantasyImportSiteActivity.c("Problem with Login", "Message: " + FantasyImportSiteActivity.i(fantasyImportSiteActivity));
                                break;
                            } else {
                                fantasyImportSiteActivity.b("Cannot Contact Server", bbt.pls_try_again_later);
                                break;
                            }
                        }
                        break;
                }
            } catch (Throwable th2) {
                fantasyImportSiteActivity.e().log(Level.SEVERE, "problem: ", th2);
            } finally {
                fantasyImportSiteActivity.e(false);
                FantasyImportSiteActivity.a(fantasyImportSiteActivity, false);
                FantasyImportSiteActivity.d(fantasyImportSiteActivity).setEnabled(true);
            }
        }
    }

    public View a(String str) {
        FantasyImportSiteActivity fantasyImportSiteActivity = this.a.get();
        if (fantasyImportSiteActivity == null || fantasyImportSiteActivity.isDestroyed()) {
            return null;
        }
        WebView webView = new WebView(fantasyImportSiteActivity.k());
        webView.getSettings().setDatabaseEnabled(false);
        webView.getSettings().setCacheMode(2);
        webView.setScrollBarStyle(33554432);
        webView.loadData(str, "text/html", "utf-8");
        webView.setWebViewClient(new bfa(this));
        webView.setPadding(14, 2, 10, 12);
        return webView;
    }
}
