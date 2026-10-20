package defpackage;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import com.thuuz.gtv.activity.AbstractActivity;
import com.thuuz.gtv.data.GameData;
import java.util.ArrayList;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bmz extends bod {
    public bmz(GameData gameData, AbstractActivity abstractActivity) {
        super(gameData, abstractActivity);
    }

    @Override // defpackage.bmn
    protected void d(bmh bmhVar) {
        try {
            e(bmhVar);
            if (!f().ak() && k()) {
                if (bmhVar.h()) {
                    bmhVar.a(true, f().e());
                    ArrayList<Integer> R = f().R();
                    if (R.size() > 1) {
                        if (f().am()) {
                            bmhVar.b(0, a(a(R, 0)));
                            bmhVar.b(1, a(a(R, 1)));
                            return;
                        } else {
                            a(R, 0, bmhVar);
                            a(R, 1, bmhVar);
                            return;
                        }
                    }
                    return;
                }
                bmm bmmVar = (bmm) bmhVar;
                if (bmmVar.C == null) {
                    bmmVar.C = bmmVar.E.findViewById(bbq.tourney_row);
                }
                bmmVar.C.setVisibility(0);
                ((TextView) bmmVar.C.findViewById(bbq.tourney)).setText(f().e());
                ArrayList<Integer> R2 = f().R();
                if (R2.size() > 1) {
                    if (f().am()) {
                        a(a(R2, 0), bmmVar.k);
                        a(a(R2, 1), bmmVar.m);
                    } else {
                        a(R2, 0, bmmVar.k);
                        a(R2, 1, bmmVar.m);
                    }
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot render scores: ", th);
        }
    }

    private void a(ArrayList<Integer> arrayList, int i, bmh bmhVar) {
        boolean z = false;
        String c = f().c(i);
        try {
            if (c.length() > 0) {
                if (Integer.parseInt(c) >= 18) {
                    z = true;
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle golf through: " + c, th);
        }
        if (z) {
            bmhVar.b(i, a(a(arrayList, i)));
        } else {
            bmhVar.b(i, a(arrayList, i));
        }
    }

    private String a(String str) {
        return str + " (F)";
    }

    private void a(ArrayList<Integer> arrayList, int i, TextView textView) {
        boolean z = false;
        String c = f().c(i);
        try {
            if (c.length() > 0) {
                if (Integer.parseInt(c) >= 18) {
                    z = true;
                }
            }
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot handle: ", th);
        }
        if (z) {
            a(a(arrayList, i), textView);
        } else {
            textView.setText(a(arrayList, i));
        }
    }

    private void a(String str, TextView textView) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" ");
        int length = sb.length();
        sb.append("(F)");
        int length2 = sb.length();
        SpannableString spannableString = new SpannableString(sb.toString());
        spannableString.setSpan(new ForegroundColorSpan(-7829368), length, length2, 0);
        textView.setText(spannableString, TextView.BufferType.SPANNABLE);
    }

    private String a(ArrayList<Integer> arrayList, int i) {
        String str = "";
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(arrayList.get(i));
            String c = f().c(i);
            if (!f().am() && c.length() > 0) {
                sb.append(" thru ");
                sb.append(c);
            }
            str = sb.toString();
            return str;
        } catch (Throwable th) {
            getLogger().log(Level.SEVERE, "Cannot get golf score: ", th);
            return str;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bmn
    public void b(bmh bmhVar, String str) {
        super.b(bmhVar, "");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bmn
    public void c(bmh bmhVar, String str) {
        StringBuilder sb = new StringBuilder(f().A());
        if (sb.length() == 0) {
            super.c(bmhVar, "");
            return;
        }
        switch (bna.a[f().j().ordinal()]) {
            case 1:
                String ai = f().ai();
                if (ai != null && ai.length() != 0) {
                    sb.append(", ");
                    sb.append(GameData.d(ai));
                    break;
                }
                break;
        }
        bmhVar.a(bbq.clock, sb.toString());
    }

    @Override // defpackage.bmn
    public void a(bmm bmmVar, View view, GameData gameData) {
        if (view != null) {
            try {
                view.setBackgroundResource(bbn.game_cell_bg);
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "Cannot handle: ", th);
                return;
            }
        }
        if (bmmVar != null && gameData != null) {
            switch (bna.a[gameData.j().ordinal()]) {
                case 1:
                    String a = gameData.a(GameData.DateRequestType.SHORT_DATETIME, false);
                    String A = gameData.A();
                    if (A.length() != 0) {
                        a = A + ", " + a;
                    }
                    bmmVar.v.setText(a);
                    return;
                case 2:
                    bmmVar.v.setText(gameData.a(GameData.DateRequestType.DATE_SUPER_SHORT, false) + " " + gameData.A());
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // defpackage.bmn
    public void a(bmh bmhVar, String str) {
        super.a(bmhVar, str);
        if (str == null || str.trim().length() == 0) {
            bmhVar.c(false);
        }
    }
}
