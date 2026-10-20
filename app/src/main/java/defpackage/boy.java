package defpackage;

import android.os.Build;
import android.widget.RemoteViews;
import android.widget.TextView;
import com.thuuz.gtv.data.GameData;
import java.util.Set;

/* loaded from: classes.dex */
public class boy extends bmh {
    public int g;
    public int h;
    public int i;
    private final RemoteViews j;

    public boy(RemoteViews remoteViews) {
        super(true);
        this.j = remoteViews;
    }

    @Override // defpackage.bmh
    public void a(boolean z) {
        if (!z) {
            this.h = 0;
        }
        a(this.h);
    }

    @Override // defpackage.bmh
    public void a(int i) {
        this.h = i;
        if (Build.VERSION.SDK_INT >= 16) {
            this.j.setTextViewCompoundDrawables(bbq.team_name, 0, 0, this.h, 0);
        }
    }

    @Override // defpackage.bmh
    public void b(boolean z) {
        if (!z) {
            this.i = 0;
        }
        b(this.i);
    }

    @Override // defpackage.bmh
    public void b(int i) {
        this.i = i;
        if (Build.VERSION.SDK_INT >= 16) {
            this.j.setTextViewCompoundDrawables(bbq.team2_name, 0, 0, this.i, 0);
        }
    }

    @Override // defpackage.bmh
    public void c(boolean z) {
        this.j.setViewVisibility(bbq.teaser, z ? 0 : 8);
    }

    @Override // defpackage.bmh
    public void d(boolean z) {
        this.j.setViewVisibility(bbq.score, z ? 0 : 8);
        this.j.setViewVisibility(bbq.score2, z ? 0 : 8);
    }

    @Override // defpackage.bmh
    public void e(boolean z) {
        this.j.setViewVisibility(bbq.clock, z ? 0 : 8);
    }

    @Override // defpackage.bmh
    public void f(boolean z) {
        this.j.setViewVisibility(bbq.tv_icon, z ? 0 : 8);
    }

    @Override // defpackage.bmh
    public void a(String str) {
        this.j.setTextViewText(bbq.teaser, str);
    }

    @Override // defpackage.bmh
    public void a(GameData gameData, Set<Integer> set) {
        this.j.setTextViewText(bbq.gec_text, String.valueOf(gameData.h(set)));
        this.j.setViewVisibility(bbq.gec_text, 0);
        this.j.setImageViewResource(bbq.gec_img, gameData.a(false, set));
        this.j.setViewVisibility(bbq.gec_img, 0);
        this.j.setTextColor(bbq.gec_text, gameData.D().getResources().getColor(gameData.l(set)));
        int i = bbn.transparent;
        if (gameData.a(set)) {
            i = bbn.fav_blue;
        }
        i(i);
    }

    @Override // defpackage.bmh
    public void g(int i) {
        this.j.setImageViewResource(bbq.game_icon, i);
        this.j.setViewVisibility(bbq.game_icon, 0);
        this.g = i;
    }

    @Override // defpackage.bmh
    public void b(String str) {
        this.j.setTextViewText(bbq.league, str);
    }

    @Override // defpackage.bmh
    public void c(String str) {
        this.j.setTextViewText(bbq.title, str);
    }

    @Override // defpackage.bmh
    public void d(String str) {
        this.j.setTextViewText(bbq.clock, str);
    }

    @Override // defpackage.bmh
    public void a(GameData gameData) {
    }

    @Override // defpackage.bmh
    public void a(int i, int i2) {
        this.j.setTextColor(i, i2);
    }

    @Override // defpackage.bmh
    public void a(int i, String str) {
        this.j.setTextViewText(i, str);
    }

    @Override // defpackage.bmh
    public TextView h(int i) {
        return null;
    }

    @Override // defpackage.bmh
    public void i(boolean z) {
        this.j.setViewVisibility(bbq.score, z ? 0 : 8);
        this.j.setViewVisibility(bbq.score2, z ? 0 : 8);
    }

    @Override // defpackage.bmh
    public void f() {
    }

    @Override // defpackage.bmh
    public void g() {
        this.j.setViewVisibility(bbq.team_name, 0);
        this.j.setViewVisibility(bbq.team2_name, 0);
    }

    @Override // defpackage.bmh
    public void a(boolean z, String str) {
        if (z) {
            this.j.setViewVisibility(bbq.tourney_row, 0);
            this.j.setTextViewText(bbq.tourney, str);
        } else {
            this.j.setViewVisibility(bbq.tourney_row, 8);
        }
    }

    @Override // defpackage.bmh
    public boolean h() {
        return true;
    }

    @Override // defpackage.bmh
    public void c(int i) {
        this.j.setImageViewResource(bbq.fan_icon, i);
    }

    @Override // defpackage.bmh
    public void d(int i) {
        this.j.setImageViewResource(bbq.fan_icon2, i);
    }

    @Override // defpackage.bmh
    public void g(boolean z) {
        this.j.setViewVisibility(bbq.fan_icon, z ? 0 : 4);
    }

    private void i(int i) {
        this.j.setInt(bbq.top_box, "setBackgroundResource", i);
        this.j.setInt(bbq.game_rlay, "setBackgroundResource", i);
    }

    @Override // defpackage.bmh
    public void h(boolean z) {
        this.j.setViewVisibility(bbq.fan_icon2, z ? 0 : 4);
    }

    @Override // defpackage.bmh
    public void b(int i, String str) {
        switch (i) {
            case 0:
                this.j.setTextViewText(bbq.score, str);
                return;
            case 1:
                this.j.setTextViewText(bbq.score2, str);
                return;
            default:
                getLogger().severe("unknown score index: " + i);
                return;
        }
    }
}
