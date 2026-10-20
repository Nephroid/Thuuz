package defpackage;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.data.GameData;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bod extends bmn {
    public bod(GameData gameData, AbstractActivity abstractActivity) {
        super(gameData, abstractActivity);
    }

    @Override // defpackage.bmn
    protected void b(bmh bmhVar) {
        if (f().ak() || !k()) {
            f(bmhVar);
            bmhVar.a(bbq.team_name, f().e());
            return;
        }
        Integer b = f().b(0);
        String w = f().w();
        if (bmhVar.h()) {
            a(bbq.team_name, bmhVar, b, w);
        } else {
            a(((bmm) bmhVar).i, w, b);
        }
    }

    @Override // defpackage.bmn
    protected void c(bmh bmhVar) {
        if (f().ak() || !k()) {
            f(bmhVar);
            bmhVar.a(bbq.team2_name, f().c());
            bmhVar.a(bbq.team2_name, f().D().getResources().getColor(bbn.losing_team_text));
            return;
        }
        Integer b = f().b(1);
        String x = f().x();
        if (bmhVar.h()) {
            a(bbq.team2_name, bmhVar, b, x);
        } else {
            a(((bmm) bmhVar).j, x, b);
        }
    }

    private void a(TextView textView, String str, Integer num) {
        if (textView != null) {
            try {
                AbstractActivity a = a();
                if (a != null) {
                    StringBuilder sb = new StringBuilder();
                    textView.setTextColor(a.getResources().getColor(bbn.white_f1));
                    if (num != null) {
                        String str2 = num.toString() + ".  ";
                        sb.append(str2);
                        int length = str2.length() - 1;
                        sb.append(str);
                        SpannableString spannableString = new SpannableString(sb.toString());
                        spannableString.setSpan(new ForegroundColorSpan(-7829368), 0, length, 0);
                        textView.setText(spannableString, TextView.BufferType.SPANNABLE);
                    } else {
                        textView.setText(str);
                    }
                }
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "Cannot render team name: ", th);
            }
        }
    }

    @Override // defpackage.bmn
    public String e() {
        return f().e() + ", " + f().A();
    }

    @Override // defpackage.bmn
    public String g() {
        String ah = f().ah();
        return ah.length() > 0 ? f().d() + " - " + ah : f().d();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean k() {
        return h() && a(f().u()) && a(f().v()) && f().S();
    }

    private boolean a(String str) {
        return (str == null || str.length() == 0 || "TBD".equals(str)) ? false : true;
    }

    private void a(int i, bmh bmhVar, Integer num, String str) {
        StringBuilder sb = new StringBuilder();
        bmhVar.a(i, f().D().getResources().getColor(bbn.white_f1));
        if (num != null) {
            sb.append(num.toString() + ".  ");
        }
        sb.append(str);
        bmhVar.a(i, sb.toString());
    }
}
