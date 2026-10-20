package defpackage;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import com.thuuz.gtv.data.GameData;
import com.thuuz.gtv.view.GecCircleView;
import com.thuuz.gtv.view.PlayThumbnail;
import com.thuuz.gtv.widget.NetworkListenableImageView;
import java.util.Set;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bmm extends bmh {
    public TableLayout A;
    public TableLayout B;
    public View C;
    public TextView D;
    public View E;
    public GecCircleView g;
    public PlayThumbnail h;
    public TextView i;
    public TextView j;
    public TextView k;
    public RelativeLayout l;
    public TextView m;
    public RelativeLayout n;
    public ImageView o;
    public ImageView p;
    public TextView q;
    public ImageView r;
    public ImageView s;
    public TextView t;
    public TextView u;
    public TextView v;
    public NetworkListenableImageView w;
    public NetworkListenableImageView x;
    public NetworkListenableImageView y;
    public int z;

    public bmm(View view, boolean z) {
        super(z);
        if (view == null) {
            getLogger().severe("null view in gameCellHolder");
            return;
        }
        this.E = view;
        view.setTag(this);
        this.g = (GecCircleView) view.findViewById(bbq.game_gec);
        this.h = (PlayThumbnail) view.findViewById(bbq.highlight_layout);
        if (this.h != null) {
            this.h.setTag(this);
        }
        this.i = (TextView) view.findViewById(bbq.team_name);
        this.k = (TextView) view.findViewById(bbq.score);
        this.l = (RelativeLayout) view.findViewById(bbq.score_lay);
        this.j = (TextView) view.findViewById(bbq.team2_name);
        this.m = (TextView) view.findViewById(bbq.score2);
        this.n = (RelativeLayout) view.findViewById(bbq.score2_lay);
        this.o = (ImageView) view.findViewById(bbq.fan_icon);
        this.p = (ImageView) view.findViewById(bbq.fan_icon2);
        this.q = (TextView) view.findViewById(bbq.teaser);
        this.r = (ImageView) view.findViewById(bbq.tv_icon);
        this.s = (ImageView) view.findViewById(bbq.game_icon);
        this.t = (TextView) view.findViewById(bbq.league);
        this.u = (TextView) view.findViewById(bbq.title);
        this.v = (TextView) view.findViewById(bbq.clock);
        this.w = (NetworkListenableImageView) view.findViewById(bbq.gec_plot);
        this.w.setTag(null);
        this.x = (NetworkListenableImageView) view.findViewById(bbq.team_icon2);
        this.y = (NetworkListenableImageView) view.findViewById(bbq.team_icon);
        this.C = view.findViewById(bbq.tourney_row);
    }

    @Override // defpackage.bmh
    public void a(boolean z) {
        if (!z) {
            this.e = 0;
        }
        if (this.i != null) {
            this.i.setCompoundDrawablesWithIntrinsicBounds(0, 0, this.e, 0);
        }
    }

    @Override // defpackage.bmh
    public void a(int i) {
        this.e = i;
    }

    @Override // defpackage.bmh
    public void b(boolean z) {
        if (!z) {
            this.f = 0;
        }
        if (this.j != null) {
            this.j.setCompoundDrawablesWithIntrinsicBounds(0, 0, this.f, 0);
        }
    }

    @Override // defpackage.bmh
    public void b(int i) {
        this.f = i;
    }

    @Override // defpackage.bmh
    public void c(boolean z) {
        if (z) {
            this.q.setVisibility(0);
        } else {
            this.q.setText(" ");
        }
    }

    @Override // defpackage.bmh
    public void d(boolean z) {
        this.k.setVisibility(z ? 0 : 8);
        this.m.setVisibility(z ? 0 : 8);
    }

    @Override // defpackage.bmh
    public void e(boolean z) {
        this.v.setVisibility(z ? 0 : 8);
    }

    @Override // defpackage.bmh
    public void f(boolean z) {
        this.r.setVisibility(z ? 0 : 8);
    }

    @Override // defpackage.bmh
    public void a(String str) {
        if (this.q != null) {
            this.q.setText(str);
        }
    }

    @Override // defpackage.bmh
    public void g(boolean z) {
        if (this.o != null) {
            this.o.setVisibility(z ? 0 : 4);
        }
    }

    @Override // defpackage.bmh
    public void h(boolean z) {
        if (this.p != null) {
            this.p.setVisibility(z ? 0 : 4);
        }
    }

    @Override // defpackage.bmh
    public void c(int i) {
        if (this.o != null) {
            this.o.setImageResource(i);
        }
    }

    @Override // defpackage.bmh
    public void d(int i) {
        if (this.p != null) {
            this.p.setImageResource(i);
        }
    }

    @Override // defpackage.bmh
    public void a(GameData gameData, Set<Integer> set) {
        this.g.setCircleType(gameData.ak() ? 1 : 0);
        this.g.setCircleColor(gameData.k(set));
        this.g.setVisibility(0);
        int l = gameData.l(set);
        if (l != a()) {
            this.g.setTextColor(gameData.D().getResources().getColor(l));
            e(l);
        }
        this.g.setText(gameData.g(set));
    }

    @Override // defpackage.bmh
    public void a(String str, View.OnClickListener onClickListener, ba baVar) {
        if (this.h != null) {
            this.h.a(str);
            if (baVar != null && str != null && str.length() > 0) {
                this.h.setImageUrl(str, baVar);
            }
            this.h.setOnClickListener(onClickListener);
        }
    }

    @Override // defpackage.bmh
    public void f(int i) {
        if (this.h != null) {
            this.h.setVisibility(i);
            if (i == 8) {
                this.h.a((String) null);
            }
        }
    }

    @Override // defpackage.bmh
    public void g(int i) {
        this.s.setImageResource(i);
        this.s.setVisibility(0);
        this.z = i;
    }

    @Override // defpackage.bmh
    public void b(String str) {
        this.t.setText(str);
    }

    @Override // defpackage.bmh
    public void c(String str) {
        this.u.setText(str);
    }

    @Override // defpackage.bmh
    public void d(String str) {
        this.v.setText(str);
    }

    @Override // defpackage.bmh
    public void a(GameData gameData) {
        Object tag = this.w.getTag();
        if (tag != null && (tag instanceof Integer) && ((Integer) this.w.getTag()).intValue() != gameData.i()) {
            this.w.setImageBitmap(null);
            this.w.setTag(null);
        }
    }

    @Override // defpackage.bmh
    public void a(int i, int i2) {
        TextView h = h(i);
        if (h != null) {
            h.setTextColor(i2);
        }
    }

    @Override // defpackage.bmh
    public TextView h(int i) {
        if (this.i.getId() == i) {
            return this.i;
        }
        if (this.j.getId() == i) {
            return this.j;
        }
        View findViewById = this.E.findViewById(i);
        if (findViewById != null && (findViewById instanceof TextView)) {
            return (TextView) findViewById;
        }
        return null;
    }

    @Override // defpackage.bmh
    public void a(int i, String str) {
        TextView h = h(i);
        if (h != null) {
            h.setText(str);
        }
    }

    @Override // defpackage.bmh
    public void i(boolean z) {
        this.k.setVisibility(z ? 0 : 8);
        this.m.setVisibility(z ? 0 : 8);
    }

    @Override // defpackage.bmh
    public void f() {
        if (this.B != null) {
            try {
                this.l.removeView(this.B);
            } catch (Throwable th) {
                getLogger().log(Level.SEVERE, "problem: ", th);
            }
            this.B = null;
        }
        if (this.A != null) {
            try {
                this.n.removeView(this.A);
            } catch (Throwable th2) {
                getLogger().log(Level.SEVERE, "problem: ", th2);
            }
            this.A = null;
        }
    }

    @Override // defpackage.bmh
    public void g() {
        this.i.setVisibility(0);
        this.j.setVisibility(0);
    }

    @Override // defpackage.bmh
    public void a(boolean z, String str) {
        if (z) {
            if (this.C != null) {
                this.C.setVisibility(0);
                if (this.D != null) {
                    this.D.setText(str);
                    return;
                }
                return;
            }
            return;
        }
        if (this.C != null) {
            this.C.setVisibility(8);
        }
    }

    @Override // defpackage.bmh
    public boolean h() {
        return false;
    }

    @Override // defpackage.bmh
    public void b(int i, String str) {
        switch (i) {
            case 0:
                this.k.setText(str);
                return;
            case 1:
                this.m.setText(str);
                return;
            default:
                getLogger().severe("unknown score index: " + i);
                return;
        }
    }
}
