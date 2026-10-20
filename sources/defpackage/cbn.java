package defpackage;

import android.R;
import android.content.Context;
import android.graphics.Shader;
import android.text.TextPaint;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.thuuz.gtv.view.HorizColumnScroll;
import com.thuuz.gtv.view.ListViewDpad;
import java.util.Date;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class cbn {
    public Integer a;
    public TextView b;
    public TextView c;
    public TextView d;
    public ListViewDpad e;
    public RelativeLayout f;
    public TextView g;
    public TextView h;
    public Date i;

    public /* synthetic */ cbn(Integer num, View view, boolean z, Context context, cbl cblVar) {
        this(num, view, z, context);
    }

    private cbn(Integer num, View view, boolean z, Context context) {
        Shader k;
        Shader l;
        this.a = num;
        this.e = (ListViewDpad) view.findViewById(bbq.earlier);
        this.e.setTag(this);
        this.b = (TextView) view.findViewById(bbq.list1_title);
        this.f = (RelativeLayout) view.findViewById(bbq.live_indic_layout);
        this.g = (TextView) view.findViewById(bbq.live_count);
        this.h = (TextView) view.findViewById(bbq.live_count_bg);
        this.h.setVisibility(4);
        this.c = (TextView) view.findViewById(bbq.left_arrow);
        if (z) {
            this.c.setText("<");
            try {
                this.c.setTextAppearance(context, R.style.TextAppearance.Medium);
            } catch (Throwable th) {
                bzy.getStaticLogger(getClass()).log(Level.SEVERE, "problem: ", th);
            }
        } else {
            this.c.setText("Earlier");
            int height = this.c.getHeight();
            TextPaint paint = this.c.getPaint();
            k = HorizColumnScroll.k(height);
            paint.setShader(k);
        }
        this.d = (TextView) view.findViewById(bbq.right_arrow);
        if (z) {
            this.d.setText(">");
            try {
                this.d.setTextAppearance(context, R.style.TextAppearance.Medium);
                return;
            } catch (Throwable th2) {
                bzy.getStaticLogger(getClass()).log(Level.SEVERE, "problem: ", th2);
                return;
            }
        }
        this.d.setText("Later");
        int height2 = this.d.getHeight();
        TextPaint paint2 = this.d.getPaint();
        l = HorizColumnScroll.l(height2);
        paint2.setShader(l);
    }

    public String toString() {
        String str = "holder for: " + this.a;
        if (this.b != null && this.b.getText() != null) {
            return str + ", " + this.b.getText().toString();
        }
        return str;
    }

    public void a(View.OnClickListener onClickListener) {
        this.d.setOnClickListener(onClickListener);
    }

    public void b(View.OnClickListener onClickListener) {
        this.c.setOnClickListener(onClickListener);
    }
}
