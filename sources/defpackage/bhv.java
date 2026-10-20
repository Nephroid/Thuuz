package defpackage;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.thuuz.gtv.activity.friends.ExpertsActivity;
import java.util.List;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bhv extends ArrayAdapter<bpc> {
    final /* synthetic */ ExpertsActivity a;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public bhv(com.thuuz.gtv.activity.friends.ExpertsActivity r3, android.content.Context r4) {
        /*
            r2 = this;
            r2.a = r3
            int r0 = defpackage.bbr.experts_list_item
            java.util.List r1 = com.thuuz.gtv.activity.friends.ExpertsActivity.a(r3)
            r2.<init>(r4, r0, r1)
            bqk r0 = r3.aF()
            r0.a(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bhv.<init>(com.thuuz.gtv.activity.friends.ExpertsActivity, android.content.Context):void");
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        Throwable th;
        View view2;
        OutOfMemoryError outOfMemoryError;
        View view3;
        View inflate;
        List list;
        List list2;
        View.OnClickListener onClickListener;
        if (view == null) {
            try {
                inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(bbr.experts_list_item, (ViewGroup) null);
                try {
                    a(inflate);
                } catch (OutOfMemoryError e) {
                    outOfMemoryError = e;
                    view3 = inflate;
                    System.gc();
                    System.gc();
                    this.a.a(outOfMemoryError);
                    return view3;
                } catch (Throwable th2) {
                    th = th2;
                    view2 = inflate;
                    this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
                    return view2;
                }
            } catch (OutOfMemoryError e2) {
                outOfMemoryError = e2;
                view3 = view;
                System.gc();
                System.gc();
                this.a.a(outOfMemoryError);
                return view3;
            } catch (Throwable th3) {
                th = th3;
                view2 = view;
                this.a.e().log(Level.SEVERE, "Cannot handle: ", th);
                return view2;
            }
        } else {
            inflate = view;
        }
        if (inflate == null) {
            return null;
        }
        if (i >= 0) {
            list = this.a.f;
            if (i < list.size()) {
                list2 = this.a.f;
                bpc bpcVar = (bpc) list2.get(i);
                bia biaVar = (bia) inflate.getTag();
                if (bpcVar.d()) {
                    biaVar.a.setVisibility(0);
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) biaVar.a.getLayoutParams();
                    if (marginLayoutParams != null) {
                        if (i == 0) {
                            marginLayoutParams.topMargin = 0;
                        } else {
                            marginLayoutParams.topMargin = this.a.getResources().getDimensionPixelSize(bbo.list_sep_top_margin);
                        }
                    }
                    bpd c = bpcVar.c();
                    if (c != null) {
                        biaVar.a.setText(c.a());
                    }
                } else {
                    biaVar.a.setVisibility(8);
                }
                biaVar.b.setText(bpcVar.b());
                biaVar.c.setText(bpcVar.f());
                int a = bpcVar.a();
                Bitmap a2 = this.a.aF().a(String.valueOf(a), this.a.j().n(String.valueOf(a)));
                if (a2 != null) {
                    biaVar.d.setImageBitmap(a2);
                } else {
                    biaVar.d.setImageBitmap(null);
                }
                if (bpcVar.e()) {
                    biaVar.e.setVisibility(8);
                    biaVar.f.setVisibility(0);
                    biaVar.f.setImageResource(bbp.bluecheck);
                    biaVar.h.setVisibility(8);
                } else if (this.a.j().e()) {
                    biaVar.e.setVisibility(8);
                    biaVar.f.setVisibility(0);
                    biaVar.f.setImageResource(bbp.ic_action_user_add);
                    biaVar.h.setVisibility(0);
                } else {
                    biaVar.e.setVisibility(0);
                    biaVar.e.setOnClickListener(new bhy(this.a));
                    biaVar.e.setTag(bpcVar);
                    biaVar.f.setVisibility(8);
                    biaVar.h.setVisibility(8);
                }
                biaVar.g.setTag(bpcVar);
                View view4 = biaVar.g;
                onClickListener = this.a.k;
                view4.setOnClickListener(onClickListener);
                return inflate;
            }
        }
        return inflate;
    }

    private void a(View view) {
        bia biaVar = new bia(this.a);
        biaVar.a = (TextView) view.findViewById(bbq.separator);
        biaVar.b = (TextView) view.findViewById(bbq.name);
        biaVar.c = (TextView) view.findViewById(bbq.title);
        biaVar.d = (ImageView) view.findViewById(bbq.friend_pic);
        biaVar.e = (Button) view.findViewById(bbq.friend_button);
        biaVar.f = (ImageView) view.findViewById(bbq.checkmark);
        biaVar.g = view.findViewById(bbq.selectable_item);
        biaVar.h = view.findViewById(bbq.right_text);
        view.setTag(biaVar);
    }
}
