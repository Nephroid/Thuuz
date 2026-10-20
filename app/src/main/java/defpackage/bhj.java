package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.thuuz.gtv.activity.friends.AddFbFriendsActivity;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class bhj extends ArrayAdapter<bhf> {
    int a;
    final /* synthetic */ AddFbFriendsActivity b;

    public /* synthetic */ bhj(AddFbFriendsActivity addFbFriendsActivity, Context context, bhd bhdVar) {
        this(addFbFriendsActivity, context);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private bhj(com.thuuz.gtv.activity.friends.AddFbFriendsActivity r3, android.content.Context r4) {
        /*
            r2 = this;
            r2.b = r3
            int r0 = defpackage.bbr.add_fb_friend_list_item
            java.util.List r1 = com.thuuz.gtv.activity.friends.AddFbFriendsActivity.h(r3)
            r2.<init>(r4, r0, r1)
            bqk r0 = com.thuuz.gtv.activity.friends.AddFbFriendsActivity.i(r3)
            r0.a(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bhj.<init>(com.thuuz.gtv.activity.friends.AddFbFriendsActivity, android.content.Context):void");
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        Throwable th;
        View view2;
        OutOfMemoryError outOfMemoryError;
        View view3;
        View inflate;
        List list;
        Map map;
        if (view == null) {
            try {
                inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(bbr.add_fb_friend_list_item, (ViewGroup) null);
                try {
                    a(inflate);
                } catch (OutOfMemoryError e) {
                    outOfMemoryError = e;
                    view3 = inflate;
                    System.gc();
                    System.gc();
                    this.b.a(outOfMemoryError);
                    return view3;
                } catch (Throwable th2) {
                    th = th2;
                    view2 = inflate;
                    this.b.e().log(Level.SEVERE, "Cannot handle: ", th);
                    return view2;
                }
            } catch (OutOfMemoryError e2) {
                outOfMemoryError = e2;
                view3 = view;
                System.gc();
                System.gc();
                this.b.a(outOfMemoryError);
                return view3;
            } catch (Throwable th3) {
                th = th3;
                view2 = view;
                this.b.e().log(Level.SEVERE, "Cannot handle: ", th);
                return view2;
            }
        } else {
            inflate = view;
        }
        if (inflate == null) {
            return null;
        }
        bhp bhpVar = (bhp) inflate.getTag();
        list = this.b.j;
        bhf bhfVar = (bhf) list.get(i);
        map = this.b.k;
        bpl bplVar = (bpl) map.get(bhfVar.b);
        if (bplVar != null) {
            a(bhfVar, bplVar, bhpVar, i);
        } else {
            this.b.e().log(Level.SEVERE, "Cannot find Facebook friend for list view item : ", bhfVar.b);
        }
        inflate.setNextFocusRightId(bbq.webview);
        return inflate;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00de  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(defpackage.bhf r10, defpackage.bpl r11, defpackage.bhp r12, int r13) {
        /*
            Method dump skipped, instructions count: 340
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bhj.a(bhf, bpl, bhp, int):void");
    }

    private void a(View view) {
        View.OnKeyListener onKeyListener;
        if (view != null) {
            bhp bhpVar = new bhp(null);
            view.setTag(bhpVar);
            bhpVar.a = (TextView) view.findViewById(bbq.separator);
            bhpVar.b = (ImageView) view.findViewById(bbq.friend_pic);
            bhpVar.c = (TextView) view.findViewById(bbq.friend_name);
            bhpVar.d = (Button) view.findViewById(bbq.friend_button);
            bhpVar.e = (ImageView) view.findViewById(bbq.checkmark);
            bhpVar.f = (TextView) view.findViewById(bbq.right_text);
            bhpVar.h = view.findViewById(bbq.selectable_item);
            View view2 = bhpVar.h;
            onKeyListener = this.b.u;
            view2.setOnKeyListener(onKeyListener);
            if (!this.b.j().e()) {
                view.setEnabled(false);
                bhpVar.h.setEnabled(false);
                bhpVar.h.setBackgroundDrawable(this.b.getResources().getDrawable(bbp.guidebg_shape));
            }
        }
    }
}
